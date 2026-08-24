package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to rollback data migration.
 */
@Data
public class DataMigrationRollbackRequest extends AbstractBesV2Request {
    private String clusterId;
    private String requestId;
}
