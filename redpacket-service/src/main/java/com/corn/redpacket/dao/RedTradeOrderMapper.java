package com.corn.redpacket.dao;

import com.corn.redpacket.model.RedTradeOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-09-17
 */
public interface RedTradeOrderMapper extends BaseMapper<RedTradeOrder> {
    Integer updateTradeOrderStatus(@Param("status") String status,@Param("orderId") String orderId);

}
