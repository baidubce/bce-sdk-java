package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to suggest data migration instances.
 */
@Data
public class DataMigrationSuggestRequest extends AbstractBesV2Request {
    private String clusterId;
    private String moduleType;
    private Integer migrateCount;
}
