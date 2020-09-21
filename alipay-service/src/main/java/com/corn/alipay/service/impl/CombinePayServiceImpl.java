package com.corn.alipay.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.corn.alipay.CombinePayServiceAPI;
import com.corn.capital.CapitalTradeOrderService;
import com.corn.capital.vo.CapitalTradeOrderDto;
import com.corn.order.OrderServiceAPI;
import com.corn.order.vo.OrderVO;
import com.corn.redpacket.RedPacketTradeOrderService;
import com.corn.redpacket.vo.RedPacketTradeOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
@Slf4j
public class CombinePayServiceImpl implements CombinePayServiceAPI {

    @Reference(
            interfaceClass = RedPacketTradeOrderService.class,
            check = false,
            group = "default")
    private RedPacketTradeOrderService redPacketTradeOrderService;

    @Reference(
            interfaceClass = CapitalTradeOrderService.class,
            check = false,
            group = "default")
    private CapitalTradeOrderService capitalTradeOrderService;

    @Reference(
            interfaceClass = OrderServiceAPI.class,
            check = false,
            group = "default")
    private OrderServiceAPI orderServiceAPI;


    /**主业务先，需要声明分布式事务Compensable注解**/
    @Override
    @Compensable(confirmMethod = "confirmCombinePay", cancelMethod = "cancelCombinePay", asyncConfirm = true)
    public void combinePay(String orderId,long userId, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {

        /**1、根据订单号获取订单信息，如果订单是草稿状态，则进行支付操作(确保幂等性)**/
        //执行主业务：如果订单是草稿状态，更新订单状态为支付中(如果订单状态不是草稿状态，则直接执行分支业务)
        OrderVO orderVO = orderServiceAPI.getOrderInfoById(orderId);
        if(orderVO !=null && "DRAFT".equals(orderVO.getOrderStatus())){
            //记录组合支付金额，更新订单状态为支付中
            log.info("订单id:{},红包支付金额：{},账户支付金额：{},支付状态：PAYING",orderId,redPacketPayAmount.toString(),capitalPayAmount.toString());
            orderServiceAPI.updateOrderStatus("PAYING",orderId);
        }

        /**2、执行分支业务，调用红包服务、余额支付进行组合支付**/
        //调用红包服务
        redPacketTradeOrderService.payRecord(createRedTradeOrder(orderId,userId,redPacketPayAmount));

        //调用余额服务
        capitalTradeOrderService.payRecord(createCapTradeOrder(orderId,userId,redPacketPayAmount));


//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        throw new RuntimeException("test exception");
    }

    public void confirmCombinePay(String orderId,long userId, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {
        OrderVO orderVO = orderServiceAPI.getOrderInfoById(orderId);
        if(orderVO !=null && "PAYING".equals(orderVO.getOrderStatus())){
            //记录组合支付金额，更新订单状态为支付中
            log.info("订单id:{},红包支付金额：{},账户支付金额：{},支付状态：CONFIRMED",orderId,redPacketPayAmount.toString(),capitalPayAmount.toString());
            orderServiceAPI.updateOrderStatus("CONFIRMED",orderId);
        }
    }

    public void cancelCombinePay(String orderId,long userId, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {
        OrderVO orderVO = orderServiceAPI.getOrderInfoById(orderId);
        if(orderVO !=null && "PAYING".equals(orderVO.getOrderStatus())){
            //记录组合支付金额，更新订单状态为支付中
            log.info("订单id:{},红包支付金额：{},账户支付金额：{},支付状态：PAY_FAILED",orderId,redPacketPayAmount.toString(),capitalPayAmount.toString());
            orderServiceAPI.updateOrderStatus("PAY_FAILED",orderId);
        }
    }

    private RedPacketTradeOrderDto createRedTradeOrder(String orderId,Long userId,BigDecimal amount){
        RedPacketTradeOrderDto redPacketTradeOrderDto = new RedPacketTradeOrderDto();
        redPacketTradeOrderDto.setAmount(amount);
        redPacketTradeOrderDto.setMerchantOrderNo(orderId);
        redPacketTradeOrderDto.setSelfUserId(userId);
        return redPacketTradeOrderDto;
    }

    private CapitalTradeOrderDto createCapTradeOrder(String orderId, Long userId, BigDecimal amount){
        CapitalTradeOrderDto capitalTradeOrderDto = new CapitalTradeOrderDto();
        capitalTradeOrderDto.setAmount(amount);
        capitalTradeOrderDto.setMerchantOrderNo(orderId);
        capitalTradeOrderDto.setSelfUserId(userId);
        return capitalTradeOrderDto;
    }
}
