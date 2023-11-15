package com.baidubce.services.tsdb.model;

import static com.baidubce.services.tsdb.TsdbConstants.DateFormat.DATETIME_FORMAT;

import java.math.BigDecimal;
import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Represent the response for creating database.
 */
public class CreateDatabaseResponse extends AbstractBceResponse {

    private String databaseId;
    private BigDecimal charge;
    private String orderId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT, timezone = "UTC")
    private Date expiredTime;

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
