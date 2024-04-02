package com.baidubce.services.oos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatorSpec {
    private String name;
    private String label;
    private String description;
    private String operator;
    private int retries;
    private int retryInterval;
    private int timeout;
    private double parallelismRatio;
    private int parallelismCount;
    private double allowedFailureRatio;
    private int allowedFailureCount;
    private boolean manually;
    private int scheduleDelayMilli;
    private boolean pauseOnFailure;
    private List<Property> properties;
    private Map<String, Object> initContext;
}
