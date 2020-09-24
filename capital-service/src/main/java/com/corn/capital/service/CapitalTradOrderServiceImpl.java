package com.corn.capital.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.corn.capital.CapitalTradeOrderService;
import com.corn.capital.dao.CapCapitalAccountMapper;
import com.corn.capital.dao.CapTradeOrderMapper;
import com.corn.capital.model.CapCapitalAccount;
import com.corn.capital.model.CapTradeOrder;
import com.corn.capital.vo.CapitalTradeOrderDto;
import com.corn.redpacket.RedPacketTradeOrderService;
import com.corn.redpacket.vo.RedPacketTradeOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Service(interfaceClass = CapitalTradeOrderService.class, group = "default")
//@Transactional(rollbackFor = Exception.class,transactionManager = "platformTransactionManager")
public class CapitalTradOrderServiceImpl implements CapitalTradeOrderService {

    @Autowired
    private CapCapitalAccountMapper capCapitalAccountMapper;

    @Autowired
    private CapTradeOrderMapper capTradeOrderMapper;

    @Override
    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
    public void payRecord(CapitalTradeOrderDto tradeOrderDto) {
        System.out.println("try Capital called");
        CapTradeOrder capTradeOrder = new CapTradeOrder();
        capTradeOrder.setMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());
        CapTradeOrder foundRecord = capTradeOrderMapper.selectOne(capTradeOrder);

        //未记录过的支付
        if (foundRecord == null) {
            //交易订单设置为草稿状态
            capTradeOrder.setStatus("DRAFT");
            capTradeOrder.setAmount(tradeOrderDto.getAmount());
            capTradeOrder.setSelfUserId(tradeOrderDto.getSelfUserId());
            try {
                //保存红包交易记录
                capTradeOrderMapper.insert(capTradeOrder);
                //查询用户可用账户余额
                CapCapitalAccount capCapitalAccount = capCapitalAccountMapper.getCapitalAccountByUserId(capTradeOrder.getSelfUserId() + "");
            } catch (DataIntegrityViolationException e) {
                //this exception may happen when insert trade order concurrently, if happened, ignore this insert operation.
            }

        }
//        throw new RuntimeException("capital service");
    }

    public void confirmRecord(CapitalTradeOrderDto tradeOrderDto) {
        System.out.println("confirmRecord Capital called");
        CapTradeOrder capTradeOrder = new CapTradeOrder();
        capTradeOrder.setMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());
        CapTradeOrder foundRecord = capTradeOrderMapper.selectOne(capTradeOrder);

        //交易记录查询不为空，而且状态为草稿状态
        if (foundRecord != null && "DRAFT".equals(foundRecord.getStatus())) {
            //交易订单设置为草稿状态
            capTradeOrder.setStatus("CONFIRM");
            //更新交易记录状态
            capTradeOrderMapper.updateTradeOrderStatus(capTradeOrder.getStatus(), capTradeOrder.getMerchantOrderNo());
            //查询用户可用账号余额
            CapCapitalAccount capCapitalAccount = capCapitalAccountMapper.getCapitalAccountByUserId(tradeOrderDto.getSelfUserId() + "");
            //confirm阶段真正的扣红包金额
            //红包余额大于即将扣款的红包金额
            if ((tradeOrderDto.getAmount().compareTo(capCapitalAccount.getBalanceAmount()) == -1)) {
                //减扣账户金额
                capCapitalAccountMapper.descCapitalAmount(tradeOrderDto.getAmount(), tradeOrderDto.getSelfUserId() + "");
            } else {
                throw new RuntimeException("账户余额不足");
            }
        }
    }

    public void cancelRecord(CapitalTradeOrderDto tradeOrderDto) {
        System.out.println("cancel Capital called");

        CapTradeOrder capTradeOrder = new CapTradeOrder();
        capTradeOrder.setMerchantOrderNo(tradeOrderDto.getMerchantOrderNo());
        CapTradeOrder foundRecord = capTradeOrderMapper.selectOne(capTradeOrder);

        //交易记录查询不为空，而且状态为草稿状态
        if (foundRecord != null && "DRAFT".equals(foundRecord.getStatus())) {
            //交易订单设置为草稿状态
            capTradeOrder.setStatus("CANCEL");
            //更新交易记录状态
            capTradeOrderMapper.updateTradeOrderStatus(capTradeOrder.getStatus(), capTradeOrder.getMerchantOrderNo());
        }
    }
}
