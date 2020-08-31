package com.corn.order.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.corn.order.model.MoocOrder2018T;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.corn.order.vo.OrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author jiangzh
 * @since 2018-09-24
 */
public interface MoocOrder2018TMapper extends BaseMapper<MoocOrder2018T> {

    String getSeatsByFieldId(@Param("fieldId") String fieldId);

    OrderVO getOrderInfoById(@Param("orderId") String orderId);

    List<OrderVO> getOrdersByUserId(@Param("userId")Integer userId, Page<OrderVO> page);

    String getSoldSeatsByFieldId(@Param("fieldId")Integer fieldId);


}
