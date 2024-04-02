package com.baidubce.services.bcm.model.dashboard;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardBaseResponse extends AbstractBceResponse {
    public Integer code;

    public String message;

    public String traceInfo;

    public Boolean success;
}
