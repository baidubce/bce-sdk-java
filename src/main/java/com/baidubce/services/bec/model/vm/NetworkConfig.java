package com.baidubce.services.bec.model.vm;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class NetworkConfig {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nodeType;

    private List<Networks> networksList;

    @Data
    public static class Networks {
        private String netType;
        private String netName;
        private int nicIndex;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String eniId;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String mac;
        private IpAddress ipv4;
        private IpAddress ipv6;
        private List<String> reserveIps;
    }

    @Data
    public static class IpAddress {
        private String ip;
        private String gw;
        private String cidr;
        private String mask;

    }


}
