package com.corn.redpacket.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.corn.redpacket.RedPacketTradeOrderService;
import com.corn.redpacket.dao.RedRedPacketAccountMapper;
import com.corn.redpacket.dao.RedTradeOrderMapper;
import com.corn.redpacket.model.RedRedPacketAccount;
import com.corn.redpacket.model.RedTradeOrder;
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
@Service(interfaceClass = RedPacketTradeOrderService.class,group = "default")
@Transactional(rollbackFor = Exception.class,transactionManager = "platformTransactionManager")
public class RedPacketTradeOrderServiceImpl implements RedPacketTradeOrderService {

    @Autowired
    private RedTradeOrderMapper redTradeOrderMapper;

    @Autowired
    private RedRedPacketAccountMapper redRedPacketAccountMapper;

    @Override
    @Compensable(confirmMethod = "confirmRecord", cancelMethod = "cancelRecord", transactionContextEditor = DubboTransactionContextEditor.class)
    public void payRecord(RedPacketTradeOrderDto redPacketTradeOrderDto) {
        System.out.println("try red called");

        RedTradeOrder redTradeOrder = new RedTradeOrder();
        redTradeOrder.setMerchantOrderNo(redPacketTradeOrderDto.getMerchantOrderNo());
        RedTradeOrder foundRecord = redTradeOrderMapper.selectOne(redTradeOrder);

        //未记录过的支付
        if(foundRecord == null){
            //交易订单设置为草稿状态
            redTradeOrder.setStatus("DRAFT");
            redTradeOrder.setAmount(redPacketTradeOrderDto.getAmount());
            redTradeOrder.setSelfUserId(redPacketTradeOrderDto.getSelfUserId());
            try{
                //保存红包交易记录
                redTradeOrderMapper.insert(redTradeOrder);
                //查询用户可用红包余额
                RedRedPacketAccount redPacketAccount = redRedPacketAccountMapper.getRedPacketAccountByUserId(redTradeOrder.getSelfUserId() + "");
                //红包余额大于即将扣款的红包金额
                //try阶段先不扣减红包余额，放到confirm阶段进行扣款，todo 可以记录到扣款记录，并设置状态
//                if((redTradeOrder.getAmount().compareTo(redPacketAccount.getBalanceAmount()) == -1)){
//                    //减扣红包金额
//                    redRedPacketAccountMapper.descRedPacketAmount(redTradeOrder.getAmount(),redTradeOrder.getSelfUserId()+"");
//                }else {
//                    throw new RuntimeException("红包余额不足");
//                }
            }catch (DataIntegrityViolationException e){
                //this exception may happen when insert trade order concurrently, if happened, ignore this insert operation.
            }

        }
//        throw new RuntimeException("red packet");
    }

    public void confirmRecord(RedPacketTradeOrderDto redPacketTradeOrderDto) {
        System.out.println("confirmRecord red called");

        RedTradeOrder redTradeOrder = new RedTradeOrder();
        redTradeOrder.setMerchantOrderNo(redPacketTradeOrderDto.getMerchantOrderNo());
        RedTradeOrder foundRecord = redTradeOrderMapper.selectOne(redTradeOrder);

        //交易记录查询不为空，而且状态为草稿状态
        if(foundRecord != null && "DRAFT".equals(foundRecord.getStatus())){
            //交易订单设置为草稿状态
            redTradeOrder.setStatus("CONFIRM");
            //更新交易记录状态
            redTradeOrderMapper.updateTradeOrderStatus(redTradeOrder.getStatus(),redTradeOrder.getMerchantOrderNo());
            //查询用户可用红包余额
            RedRedPacketAccount redPacketAccount = redRedPacketAccountMapper.getRedPacketAccountByUserId(redPacketTradeOrderDto.getSelfUserId() + "");
            //confirm阶段真正的扣红包金额
            //红包余额大于即将扣款的红包金额
            if((redPacketTradeOrderDto.getAmount().compareTo(redPacketAccount.getBalanceAmount()) == -1)){
                //减扣红包金额
                redRedPacketAccountMapper.descRedPacketAmount(redPacketTradeOrderDto.getAmount(),redPacketTradeOrderDto.getSelfUserId()+"");
            }else {
                throw new RuntimeException("红包余额不足");
            }
        }
    }

    public void cancelRecord(RedPacketTradeOrderDto redPacketTradeOrderDto) {
        System.out.println("cancel red called");
        RedTradeOrder redTradeOrder = new RedTradeOrder();
        redTradeOrder.setMerchantOrderNo(redPacketTradeOrderDto.getMerchantOrderNo());
        RedTradeOrder foundRecord = redTradeOrderMapper.selectOne(redTradeOrder);

        //交易记录查询不为空，而且状态为草稿状态
        if(foundRecord != null && "DRAFT".equals(foundRecord.getStatus())){
            //交易订单设置为草稿状态
            redTradeOrder.setStatus("CANCEL");
            //更新交易记录状态
            redTradeOrderMapper.updateTradeOrderStatus(redTradeOrder.getStatus(),redTradeOrder.getMerchantOrderNo());
        }
    }
}
