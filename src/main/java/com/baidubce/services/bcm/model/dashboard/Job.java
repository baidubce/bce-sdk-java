package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private String alias;
    private boolean bcmSource;
    private String contrast;
    private Object decimals;
    private String displayName;
    private int endTime;
    private boolean flatten;
    private Object instanceID;
    private String instanceName;
    private Object intranetIP;
    private List<Item> items;
    private String metricName;
    private String namespace;
    private int offset;
    private int originalPeriod;
    private int period;
    private String product;
    private int startTime;
    private String statistics;
    private String tags;
    private List<TagForTsdb> tagsForTsdb;
    private String unit;
}