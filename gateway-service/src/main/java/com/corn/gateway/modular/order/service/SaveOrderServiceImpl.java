package com.corn.gateway.modular.order.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.corn.order.OrderServiceAPI;
import com.corn.order.vo.OrderVO;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.dubbo.context.DubboTransactionContextEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

@Controller
public class SaveOrderServiceImpl implements SaveOrderService {
//這裡的分組不生效暫時注釋
//    @Reference(
//            interfaceClass = OrderServiceAPI.class,
//            check = false,
//            group = "order2018")
//    private OrderServiceAPI orderServiceAPI2018;
//
//
//    @Reference(
//            interfaceClass = OrderServiceAPI.class,
//            check = false,
//            group = "order2017")
//    private OrderServiceAPI orderServiceAPI2017;

    @Reference(
            interfaceClass = OrderServiceAPI.class,
            check = false,
            group = "default")
    private OrderServiceAPI orderServiceAPI;

    @Compensable(confirmMethod = "confirmSaveOrder", cancelMethod = "cancelSaveOrder", asyncConfirm = true)
    public OrderVO saveOrderInfo(Integer fieldId, String soldSeats, String seatsName, Integer userId) {
        System.out.println("创建一个待支付状态的订单");
        boolean isTrue = orderServiceAPI.isTrueSeats(fieldId+"",soldSeats);
        boolean isNotSold = orderServiceAPI.isNotSoldSeats(fieldId+"",soldSeats);
        return null;
    }

    public OrderVO confirmSaveOrder(Integer fieldId, String soldSeats, String seatsName, Integer userId) {
        System.out.println("将订单修改为支付中");
        return null;
    }

    public OrderVO cancelSaveOrder(Integer fieldId, String soldSeats, String seatsName, Integer userId) {
        System.out.println("将订单修改为已关闭");
        return null;
    }
}
