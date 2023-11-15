package com.baidubce.services.bec.model.network;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * SecurityGroup
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityGroupVo {

    /**
     * The id of the SecurityGroup.
     */
    private String id;

    /**
     * The name of the SecurityGroup.
     */
    private String name;

    /**
     * The desc of the SecurityGroup.
     */
    private String desc;

    /**
     * SecurityGroupRuleVo list
     */
    private List<SecurityGroupRuleVo> rules;

    /**
     * isDefault
     */
    private Boolean isDefault;

    /**
     * instanceCount
     */
    private Integer instanceCount;

    /**
     * referCount
     */
    private Integer referCount;

    /**
     * createdTime
     */
    private Date createdTime;
}
