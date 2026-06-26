package com.baidubce.services.scs.model.instance;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response of scs price query.
 */
public class ScsPriceResponse extends AbstractBceResponse {

    private String price;
    private String discountPrice;
    private String originalPrice;
    private String orderId;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
