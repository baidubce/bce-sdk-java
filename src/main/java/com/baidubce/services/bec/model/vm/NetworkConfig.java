package com.baidubce.services.bec.model.vm;

import com.baidubce.services.bec.model.enums.NetTypeEnum;
import com.baidubce.services.bec.model.enums.NodeTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NetworkConfig {

    /**
     * The node type.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private NodeTypeEnum nodeType;

    /**
     * The network list.
     */
    private List<Networks> networksList;

    @Data
    public static class Networks {

        /**
         * The network type.
         */
        private NetTypeEnum netType;

        /**
         * The network name.
         */
        private String netName;

        /**
         * The network interface card index.
         */
        private Integer nicIndex;

        /**
         * The id of elastic network interface.
         */
        private String eniId;

        /**
         * The mac address.
         */
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String mac;

        /**
         * The IPv4 info.
         */
        private IpAddress ipv4;

        /**
         * The IPv6 info.
         */
        private IpAddress ipv6;

        /**
         * The auxiliary ips.
         */
        @Deprecated
        private List<String> reserveIps;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class IpAddress {
        private String ip;
        private String gw;
        private String cidr;
        private String mask;
    }
}
