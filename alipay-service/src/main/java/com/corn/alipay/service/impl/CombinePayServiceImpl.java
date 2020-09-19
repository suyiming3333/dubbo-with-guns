package com.corn.alipay.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.corn.alipay.CombinePayServiceAPI;
import com.corn.order.OrderServiceAPI;
import com.corn.order.vo.OrderVO;
import com.corn.redpacket.RedPacketTradeOrderService;
import com.corn.redpacket.vo.RedPacketTradeOrderDto;

import java.math.BigDecimal;

public class CombinePayServiceImpl implements CombinePayServiceAPI {

    @Reference(
            interfaceClass = RedPacketTradeOrderService.class,
            check = false,
            group = "default")
    private RedPacketTradeOrderService redPacketTradeOrderService;

    @Reference(
            interfaceClass = OrderServiceAPI.class,
            check = false,
            group = "default")
    private OrderServiceAPI orderServiceAPI;


    /**主业务先，需要声明分布式事务Compensable注解**/
    @Override
    public boolean combinePay(String orderId,long userId, BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount) {

        /**1、根据订单号获取订单信息，如果订单是草稿状态，则进行支付操作(确保幂等性)**/
        //执行主业务：如果订单是草稿状态，更新订单状态为支付中(如果订单状态不是草稿状态，则直接执行分支业务)
        OrderVO orderVO = orderServiceAPI.getOrderInfoById(orderId);
        if(orderVO !=null && "DRAFT".equals(orderVO.getOrderStatus())){
            //记录组合支付金额，更新订单状态为支付中
        }

        /**2、执行分支业务，调用红包服务、余额支付进行组合支付**/
        //调用红包服务
        String redPayResult = redPacketTradeOrderService.payRecord(createRedTradeOrder(orderId,userId,redPacketPayAmount));
        //调用余额服务


        return false;
    }

    private RedPacketTradeOrderDto createRedTradeOrder(String orderId,Long userId,BigDecimal amount){
        RedPacketTradeOrderDto redPacketTradeOrderDto = new RedPacketTradeOrderDto();
        redPacketTradeOrderDto.setAmount(amount);
        redPacketTradeOrderDto.setMerchantOrderNo(orderId);
        redPacketTradeOrderDto.setSelfUserId(userId);
        return redPacketTradeOrderDto;
    }
}
