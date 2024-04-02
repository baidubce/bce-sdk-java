package com.baidubce.services.oos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operator {
    private String name;
    private String description;
    private String operator;
    private int retries;
    private int retryInterval = 300000;
    private int timeout = 6 * 3600 * 1000;
    private RateControl parallelismControl;
    private RateControl allowedFailureControl;
    private boolean manually;
    private int scheduleDelayMilli;
    private boolean pauseOnFailure;
    private Map<String, Object> properties = new HashMap<String, Object>();
    private Map<String, Object> initContext = new HashMap<String, Object>();
}
