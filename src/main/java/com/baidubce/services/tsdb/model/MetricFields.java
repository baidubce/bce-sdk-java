package com.baidubce.services.tsdb.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Metric fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricFields {

    private String metric;

    private List<String> fields;

}
