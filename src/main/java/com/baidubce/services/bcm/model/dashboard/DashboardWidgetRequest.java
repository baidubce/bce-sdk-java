package com.baidubce.services.bcm.model.dashboard;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardWidgetRequest extends AbstractBceRequest {

    private String widgetName;

    private String title;

    private String type;

    private String userId;

    private String dashboardName;

    private DashboardWidgetConfigure configure;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
