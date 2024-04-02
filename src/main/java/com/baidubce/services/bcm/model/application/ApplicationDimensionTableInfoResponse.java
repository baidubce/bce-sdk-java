package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * @author gongjiaming
 */
@Data
public class ApplicationDimensionTableInfoResponse extends AbstractBceResponse {
    private String userId;
    private String appName;
    private String mapContentJson;
    private String tableName;
}
