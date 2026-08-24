package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to create a cluster.
 */
@Data
public class CreateClusterRequest extends AbstractBesV2Request {
    private String name;
    private String password;
    private String securityGroupId;
    private String subnetUuid;
    private String availableZone;
    private String vpcId;
    private Boolean isOldPackage;
    private String version;
    private List<ModuleInfo> modules;
    private Billing billing;
    private List<Tag> tags;
    private Boolean enableSSL;
    private String resGroupId;

}
