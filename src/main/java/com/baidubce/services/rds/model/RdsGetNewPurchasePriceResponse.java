package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * The Response of Obtain new purchase price
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetNewPurchasePriceResponse extends AbstractBceResponse {
    private BigDecimal price;
    private BigDecimal catalogPrice;
    private BigDecimal backupInGB;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCatalogPrice() {
        return catalogPrice;
    }

    public void setCatalogPrice(BigDecimal catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    public BigDecimal getBackupInGB() {
        return backupInGB;
    }

    public void setBackupInGB(BigDecimal backupInGB) {
        this.backupInGB = backupInGB;
    }
}
