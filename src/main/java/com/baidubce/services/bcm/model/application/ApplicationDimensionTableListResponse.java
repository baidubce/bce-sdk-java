package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * @author gongjiaming
 */
@Data
public class ApplicationDimensionTableListResponse extends AbstractBceResponse {
    private List<ApplicationDimensionTableInfoResponse> applicationDimensionTableInfoList;
}
