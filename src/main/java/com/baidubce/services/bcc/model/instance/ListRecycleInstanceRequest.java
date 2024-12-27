package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Data;

import java.util.Date;

@Data
public class ListRecycleInstanceRequest extends ListRequest {
    private String instanceId;
    private String name;
    private PaymentTiming paymentTiming;
    @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "Asia/Shanghai"
    )
    private Date recycleBegin;
    @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "Asia/Shanghai"
    )
    private Date recycleEnd;

    public ListRecycleInstanceRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public ListRecycleInstanceRequest withName(String name) {
        this.name = name;
        return this;
    }

    public ListRecycleInstanceRequest withPaymentTiming(PaymentTiming paymentTiming) {
        this.paymentTiming = paymentTiming;
        return this;
    }

    public ListRecycleInstanceRequest withRecycleBegin(Date recycleBegin) {
        this.recycleBegin = recycleBegin;
        return this;
    }

    public ListRecycleInstanceRequest withRecycleEnd(Date recycleEnd) {
        this.recycleEnd = recycleEnd;
        return this;
    }
    @Override
    public ListRecycleInstanceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
