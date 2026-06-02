package com.baidubce.services.rocketmq.model;

import lombok.Data;

import java.util.List;

@Data
public class Billing {
    private int timeLength;
    private String timeUnit;
    private boolean autoRenewEnabled;
    private int autoRenewTimeLength;
    private String autoRenewTimeUnit;
    private List<String> couponIds;
    private boolean isAutoPay;
}
