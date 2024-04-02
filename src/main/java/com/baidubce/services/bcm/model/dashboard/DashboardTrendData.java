package com.baidubce.services.bcm.model.dashboard;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardTrendData extends AbstractBceResponse {
    private List<List<Double>> data;
    private int denominator;
    private String dimensions;
    private Object legend;
    private String metric;
    private String metricType;
    private String metricUnit;
    private String metricUnitTransformation;
    private String name;
    private String namespace;
    private int numerator;
    private Object product;
    private String scope;
    private Object statistics;
    private Object time;
    private String transPolicy;
    private Object hostName;
    private Object internalIp;
}
