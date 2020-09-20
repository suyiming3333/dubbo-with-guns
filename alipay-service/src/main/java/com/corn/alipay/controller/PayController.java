package com.corn.alipay.controller;


import com.corn.alipay.CombinePayServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(value = "/pay/")
public class PayController {

    @Autowired
    private CombinePayServiceAPI combinePayServiceAPI;

    @RequestMapping(value = "test",method = RequestMethod.POST)
    public void testCombinePay(@RequestParam("orderId") String orderId,
                               @RequestParam("userId") long userId,
                               @RequestParam("redPacketPayAmount") BigDecimal redPacketPayAmount,
                               @RequestParam("capitalPayAmount") BigDecimal capitalPayAmount){

        combinePayServiceAPI.combinePay(orderId, userId, redPacketPayAmount, capitalPayAmount);
//        log.info("支付结果：{}",result);
    }
}
