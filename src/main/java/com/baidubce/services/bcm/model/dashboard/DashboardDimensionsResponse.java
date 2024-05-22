package com.baidubce.services.bcm.model.dashboard;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDimensionsResponse extends AbstractBceResponse {
    private Map<String, Set<String>> data;
}
