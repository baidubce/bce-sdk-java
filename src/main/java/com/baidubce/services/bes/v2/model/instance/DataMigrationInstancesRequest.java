package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to list data migration candidate instances.
 */
@Data
public class DataMigrationInstancesRequest extends AbstractBesV2Request {
    private String clusterId;
    private String moduleType;
}
