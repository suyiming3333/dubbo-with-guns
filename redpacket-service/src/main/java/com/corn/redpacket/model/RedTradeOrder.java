package com.corn.redpacket.model;

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
 * @since 2020-09-17
 */
@TableName("red_trade_order")
public class RedTradeOrder extends Model<RedTradeOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    @TableField("SELF_USER_ID")
    private Long selfUserId;
    @TableField("OPPOSITE_USER_ID")
    private Long oppositeUserId;
    @TableField("MERCHANT_ORDER_NO")
    private String merchantOrderNo;
    @TableField("AMOUNT")
    private BigDecimal amount;
    @TableField("STATUS")
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Long selfUserId) {
        this.selfUserId = selfUserId;
    }

    public Long getOppositeUserId() {
        return oppositeUserId;
    }

    public void setOppositeUserId(Long oppositeUserId) {
        this.oppositeUserId = oppositeUserId;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RedTradeOrder{" +
        "id=" + id +
        ", selfUserId=" + selfUserId +
        ", oppositeUserId=" + oppositeUserId +
        ", merchantOrderNo=" + merchantOrderNo +
        ", amount=" + amount +
        ", status=" + status +
        "}";
    }
}
