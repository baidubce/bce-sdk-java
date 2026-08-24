package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to confirm data migration.
 */
@Data
public class DataMigrationConfirmRequest extends AbstractBesV2Request {
    private String clusterId;
    private String moduleType;
    private List<String> instanceIdList;
}
