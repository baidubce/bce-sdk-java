package com.baidubce.services.bec.model.network;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * SecurityGroupRule
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityGroupRuleVo {

    /**
     * The id of the securityGroup.
     */
    private String securityGroupId;

    /**
     * The id of the securityGroup rule.
     */
    private String securityGroupRuleId;

    /**
     * Direction, eg:ingress
     */
    private String direction;

    /**
     * Protocol
     */
    private String protocol;

    /**
     * PortRange
     */
    private String portRange;

    /**
     * Remark
     */
    private String remark;

    /**
     * EtherType
     */
    private String etherType;

    /**
     * SourceGroupId
     */
    private String sourceGroupId;

    /**
     * SourceIp
     */
    private String sourceIp;

    /**
     * DestGroupId
     */
    private String destGroupId;

    /**
     * DestIp
     */
    private String destIp;

    /**
     * CreatedTime
     */
    private String createdTime;

    /**
     * UpdatedTime
     */
    private String updatedTime;

    /**
     * IsValid
     */
    private Boolean isValid;
}
