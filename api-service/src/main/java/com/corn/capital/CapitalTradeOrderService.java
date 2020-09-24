package com.corn.capital;


import com.corn.capital.vo.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

/**
 * Created by changming.xie on 4/1/16.
 */
public interface CapitalTradeOrderService {

    @Compensable
    void payRecord(CapitalTradeOrderDto tradeOrderDto);

}
