package com.baidubce.services.gaiadb.model;

import java.math.BigDecimal;

public class PriceResponse extends GenericGaiadbResponse {
    private BigDecimal price;
    private BigDecimal catalogPrice;

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
}
