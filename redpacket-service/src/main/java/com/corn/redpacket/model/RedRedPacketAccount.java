package com.corn.redpacket.model;

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
 * @since 2020-09-17
 */
@TableName("red_red_packet_account")
public class RedRedPacketAccount extends Model<RedRedPacketAccount> {

    private static final long serialVersionUID = 1L;

    @TableId("RED_PACKET_ACCOUNT_ID")
    private Integer redPacketAccountId;
    @TableField("BALANCE_AMOUNT")
    private BigDecimal balanceAmount;
    @TableField("USER_ID")
    private Integer userId;


    public Integer getRedPacketAccountId() {
        return redPacketAccountId;
    }

    public void setRedPacketAccountId(Integer redPacketAccountId) {
        this.redPacketAccountId = redPacketAccountId;
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
        return this.redPacketAccountId;
    }

    @Override
    public String toString() {
        return "RedRedPacketAccount{" +
        "redPacketAccountId=" + redPacketAccountId +
        ", balanceAmount=" + balanceAmount +
        ", userId=" + userId +
        "}";
    }
}
