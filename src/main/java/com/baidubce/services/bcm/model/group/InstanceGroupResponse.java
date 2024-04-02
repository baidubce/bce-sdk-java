package com.baidubce.services.bcm.model.group;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceGroupResponse extends AbstractBceResponse {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String serviceName;
    @NotNull
    private String typeName;
    @NotNull
    private String region;

    private String userId;

    private String uuid;

    private int count;

    private String serviceNameAlias;

    private String typeNameAlias;

    private String regionAlias;

    private String tagKey = "";

}