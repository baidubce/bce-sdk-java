package com.baidubce.services.bcc.model.reversed;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: lilu24
 * @Date: 2024-06-20
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyReservedInstancesRequest extends AbstractBceRequest {


    private List<ReservedInstance> reservedInstances;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @Data
    public static class ReservedInstance {

        private String reservedInstanceId;

        private String zoneName;


        private String reservedInstanceName;

        private String ehcClusterId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
