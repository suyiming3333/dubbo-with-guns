package com.corn.capital.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2020-09-19
 */
@TableName("cap_capital_account")
public class CapCapitalAccount extends Model<CapCapitalAccount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CAPITAL_ACCOUNT_ID", type = IdType.AUTO)
    private Integer capitalAccountId;
    @TableField("BALANCE_AMOUNT")
    private BigDecimal balanceAmount;
    @TableField("USER_ID")
    private Integer userId;


    public Integer getCapitalAccountId() {
        return capitalAccountId;
    }

    public void setCapitalAccountId(Integer capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.capitalAccountId;
    }

    @Override
    public String toString() {
        return "CapCapitalAccount{" +
        "capitalAccountId=" + capitalAccountId +
        ", balanceAmount=" + balanceAmount +
        ", userId=" + userId +
        "}";
    }
}
