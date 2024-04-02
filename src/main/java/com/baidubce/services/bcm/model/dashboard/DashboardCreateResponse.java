package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardCreateResponse extends DashboardBaseResponse {
    private Map<String,Object> data;
}
