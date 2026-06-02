package com.baidubce.services.bec.model.appblbv2.backendbind;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
/**
 * @Author wangjiang07
 * @Since 2025-08-04 10:10
 * @Version v1.0
 * the response for getting app blb server groups
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBecAppBlbServerGroupsResponse extends AbstractBceResponse {

    private List<BackendServer> appServerGroupList;
    private Boolean isTruncated;
    private String marker;
    private Integer maxKeys;
    private String nextMarker;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class BackendServer{
        private String id;
        private String name;
        private String desc;
        List<ServerGroupPortRes> portList;
    }
}
