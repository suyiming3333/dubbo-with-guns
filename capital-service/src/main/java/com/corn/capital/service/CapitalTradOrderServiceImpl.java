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
public class CapitalTradOrderServiceImpl implements CapitalTradeOrderService {

    @Autowired
    private CapCapitalAccountMapper capCapitalAccountMapper;

    @Autowired
    private CapTradeOrderMapper capTradeOrderMapper;

    @Override
    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
    @Transactional
    public String payRecord(CapitalTradeOrderDto tradeOrderDto) {
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
        return "success";
    }

    @Transactional
    public void confirmRecord(CapitalTradeOrderDto tradeOrderDto) {
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
            CapCapitalAccount capCapitalAccount = capCapitalAccountMapper.getCapitalAccountByUserId(capTradeOrder.getSelfUserId() + "");
            //confirm阶段真正的扣红包金额
            //红包余额大于即将扣款的红包金额
            if ((capTradeOrder.getAmount().compareTo(capCapitalAccount.getBalanceAmount()) == -1)) {
                //减扣账户金额
                capCapitalAccountMapper.descRedPacketAmount(capTradeOrder.getAmount(), capTradeOrder.getSelfUserId() + "");
            } else {
                throw new RuntimeException("账户余额不足");
            }
        }
    }

    @Transactional
    public void cancel(CapitalTradeOrderDto tradeOrderDto) {
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
