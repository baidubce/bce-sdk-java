package com.baidubce.services.bec.model.vm.network;

import com.baidubce.services.bec.model.vm.NetworkConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmNetworkConfig extends NetworkConfig {

    /**
     * Whether the user need intranet network.
     */
    private Boolean needPrivateNetwork;

    /**
     * Whether the user need public network.
     */
    private Boolean needPublicNetwork;

    /**
     * The private network name.
     */
    private String privateNetworkName;

    /**
     * The public network name.
     */
    private String publicNetworkName;

    /**
     * The  public network China Mobile name.
     */
    private String publicNetworkChinaMobileName;

    /**
     * The  public network China Unicom name.
     */
    private String publicNetworkChinaUnicomName;

    /**
     * The private network China Telecom name.
     */
    private String publicNetworkChinaTelecomName;
}
