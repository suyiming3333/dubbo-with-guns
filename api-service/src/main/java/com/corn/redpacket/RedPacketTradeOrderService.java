package com.corn.redpacket;

import com.corn.redpacket.vo.RedPacketTradeOrderDto;

public interface RedPacketTradeOrderService {

    /**记录支付记录**/
    String payRecord(RedPacketTradeOrderDto redPacketTradeOrderDto);
}
