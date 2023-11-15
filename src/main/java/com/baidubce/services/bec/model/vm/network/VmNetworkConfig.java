package com.baidubce.services.bec.model.vm.network;

import com.baidubce.services.bec.model.vm.NetworkConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmNetworkConfig extends NetworkConfig {

    private Boolean needPrivateNetwork;

    private Boolean needPublicNetwork;

    private String privateNetworkName;

    private String publicNetworkName;

    private String publicNetworkChinaMobileName;

    private String publicNetworkChinaUnicomName;

    private String publicNetworkChinaTelecomName;
}
