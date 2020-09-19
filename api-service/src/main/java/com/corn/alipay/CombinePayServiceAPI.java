package com.corn.alipay;


import com.corn.alipay.vo.AliPayInfoVO;
import com.corn.alipay.vo.AliPayResultVO;

import java.math.BigDecimal;

public interface CombinePayServiceAPI {
    /**
     * 组合支付
     * 红包支付+余额支付
     * @param orderId
     * @param redPacketPayAmount
     * @param capitalPayAmount
     * @return
     */
    boolean combinePay(String orderId, long userId,BigDecimal redPacketPayAmount, BigDecimal capitalPayAmount);

}
