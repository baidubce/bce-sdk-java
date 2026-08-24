package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to delete instances.
 */
@Data
public class InstanceDeleteRequest extends AbstractBesV2Request {
    private String clusterId;
    private List<String> instanceIdList;
    private String moduleType;
}
