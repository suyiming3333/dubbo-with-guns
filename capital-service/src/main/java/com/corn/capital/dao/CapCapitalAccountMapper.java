package com.corn.capital.dao;

import com.corn.capital.model.CapCapitalAccount;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-09-19
 */
public interface CapCapitalAccountMapper extends BaseMapper<CapCapitalAccount> {

    CapCapitalAccount getCapitalAccountByUserId(@Param("userId") String userId);

    Integer descCapitalAmount(@Param("amount") BigDecimal amount, @Param("userId") String userId);


}
