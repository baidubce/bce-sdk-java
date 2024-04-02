package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDimensionsData {
    private String name;
    private String product;
    private String comment;
    private List<DimensionValue> dimensionValues;
}
