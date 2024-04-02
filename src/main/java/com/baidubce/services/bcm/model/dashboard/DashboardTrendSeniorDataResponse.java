package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardTrendSeniorDataResponse extends DashboardBaseResponse {
    private List<DashboardTrendSeniorData> data;
}
