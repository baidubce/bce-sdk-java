package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request for cluster capacity assessment.
 */
@Data
public class ClusterAssessmentRequest extends AbstractBesV2Request {
    private String useType;
    private Integer originDataSize;
    private String originDataSizeUnit;
    private Integer dataAdd;
    private String dataAddUnit;
    private Integer storageDays;
    private Integer writeThroughput;
    private Integer readThroughput;
    private String replica;
    private Boolean needBos;
    private Integer bosStorageDays;
    private Boolean needVector;
    private Integer vectorDims;
    private String vectorType;
}
