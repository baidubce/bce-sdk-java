package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Billing {

    private String payment;

    private int timeLength;

    private String timeUnit = "month";

    private String expireTime;

    private boolean autoRenewEnabled;

    private int autoRenewTimeLength;

    private String autoRenewTimeUnit = "month";

    private List<String> couponIds;

    private Boolean isAutoPay = true;
}
