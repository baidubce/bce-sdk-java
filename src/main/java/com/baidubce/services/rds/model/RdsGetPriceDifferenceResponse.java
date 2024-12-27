package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * The response of Obtain the price difference for expansion in prepaid modev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetPriceDifferenceResponse extends AbstractBceResponse {
    private BigDecimal price;
    private BigDecimal backupInGB;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBackupInGB() {
        return backupInGB;
    }

    public void setBackupInGB(BigDecimal backupInGB) {
        this.backupInGB = backupInGB;
    }
}
