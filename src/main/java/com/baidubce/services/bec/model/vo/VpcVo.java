package com.baidubce.services.bec.model.vo;

import com.baidubce.BceConstants;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Vpc instance
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VpcVo extends AbstractBceResponse {

    /**
     * The vpcId of the vpc instance.
     */
    private String vpcId;

    /**
     * The name of the vpc instance.
     */
    private String name;

    /**
     * The cidr of the vpc instance.
     */
    private String cidr;

    /**
     * The ipv6 cidr of the vpc.
     */
    private String ipv6Cidr;

    /**
     * The description of the vpc instance.
     */
    private String description;

    /**
     * Whether default.
     */
    @JsonProperty("isDefault")
    private Boolean isDefault;

    /**
     * The subnets of the vpc.
     */
    private List<SubnetVo> subnets;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "GMT-8")
    private Date createdTime;

    /**
     * The region id of the vpc.
     */
    private String regionId;

    /**
     * The status of the vpc.
     */
    private String status;

    /**
     * The id of csn which the vpc is bound to.
     */
    private String csnId;

    /**
     * The name of csn which the vpc is bound to.
     */
    private String csnName;

    /**
     * The tags of the vpc.
     */
    private List<Tag> tags;
}
