package com.corn.capital.dao;

import com.corn.capital.model.CapTradeOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-09-19
 */
public interface CapTradeOrderMapper extends BaseMapper<CapTradeOrder> {
    Integer updateTradeOrderStatus(@Param("status") String status, @Param("orderId") String orderId);
}
