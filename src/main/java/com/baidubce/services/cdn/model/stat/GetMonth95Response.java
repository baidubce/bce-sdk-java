package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMonth95Response extends CdnResponse {

    @JsonProperty("billing_details")
    private BillingDetail billingDetails;

    public GetMonth95Response() {
    }

    public BillingDetail getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetail billingDetails) {
        this.billingDetails = billingDetails;
    }
}
