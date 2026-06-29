package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

/**
 * Generic order-creation request wrapper used by MongoDB order APIs (create instance,
 * resize instance, create/resize node). The actual config payload is carried by the
 * {@link Item#config} field.
 *
 * @param <T> the config payload type
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateOrderRequest<T> extends GenericMongodbRequest {

    private Set<MongodbPaymentModel> paymentMethod;

    private List<Item<T>> items;

    public Set<MongodbPaymentModel> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Set<MongodbPaymentModel> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Item<T>> getItems() {
        return items;
    }

    public void setItems(List<Item<T>> items) {
        this.items = items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item<T> {
        private T config;

        private Set<MongodbPaymentModel> paymentMethod;

        private int purchaseOrder;

        public Item() {
        }

        public Item(T config) {
            this.config = config;
        }

        public T getConfig() {
            return config;
        }

        public void setConfig(T config) {
            this.config = config;
        }

        public Set<MongodbPaymentModel> getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(Set<MongodbPaymentModel> paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public int getPurchaseOrder() {
            return purchaseOrder;
        }

        public void setPurchaseOrder(int purchaseOrder) {
            this.purchaseOrder = purchaseOrder;
        }
    }
}
