package com.baidubce.services.bcm.model.site;

import lombok.Data;

import java.util.List;

/**
 * create by pangyangyang on 2021/02/22
 */
@Data
public class SiteAlarmRule {

    private Long id;
    private int index;
    private String metric;
    private String metricAlias;
    private String statistics;
    private String threshold;
    private String comparisonOperator;
    private int cycle;
    private int count;
    private String function;
    private int sequence;
    // 当前报警规则作用与的探测点，如果是全网均值，需要传 AVERAGE
    private List<String> actOnIdcs;
    // 当前报警规则作用与的运营商
    private List<String> actOnIsps;
    // 标示哪些规则可以合并为一个的报警规则
    private String versionSite;
    private String unitName;
}
