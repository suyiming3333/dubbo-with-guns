package com.corn.redpacket;

import com.corn.redpacket.vo.RedPacketTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

public interface RedPacketTradeOrderService {

    /**记录支付记录**/
    @Compensable
    void payRecord(RedPacketTradeOrderDto redPacketTradeOrderDto);
}
