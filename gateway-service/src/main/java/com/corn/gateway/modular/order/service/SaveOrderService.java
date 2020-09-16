package com.corn.gateway.modular.order.service;

import com.corn.order.vo.OrderVO;

public interface SaveOrderService {

    OrderVO saveOrderInfo(Integer fieldId, String soldSeats, String seatsName, Integer userId);

}
