package com.corn.alipay;


import com.corn.alipay.vo.AliPayInfoVO;
import com.corn.alipay.vo.AliPayResultVO;

public interface AliPayServiceAPI {

    AliPayInfoVO getQRCode(String orderId);

    AliPayResultVO getOrderStatus(String orderId);

}
