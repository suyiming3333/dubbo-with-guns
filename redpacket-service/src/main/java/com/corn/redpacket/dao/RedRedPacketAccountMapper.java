package com.corn.redpacket.dao;

import com.corn.redpacket.model.RedRedPacketAccount;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-09-17
 */
public interface RedRedPacketAccountMapper extends BaseMapper<RedRedPacketAccount> {

    /**根据用户id获取红包账户信息**/
    RedRedPacketAccount getRedPacketAccountByUserId(@Param("userId") String userId);

    /**减扣用户红包**/
    Integer descRedPacketAmount(@Param("amount")BigDecimal amount,@Param("userId") String userId);

}
