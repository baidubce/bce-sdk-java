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
 * Subnet instance
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubnetVo extends AbstractBceResponse {

    /**
     * The subnetId of the subnet instance.
     */
    private String subnetId;

    /**
     * The name of the subnet instance.
     */
    private String name;

    /**
     * The cidr of the subnet instance.
     */
    private String cidr;

    /**
     * The description of the subnet instance.
     */
    private String description;

    /**
     * The region id of the subnet instance.
     */
    private String regionId;

    /**
     * The long resource uuid of the subnet instance.
     */
    private String uuid;

    /**
     * The vpc id.
     */
    private String vpcId;

    /**
     * The vpc name.
     */
    private String vpcName;

    /**
     * The ipv6 cidr.
     */
    private String ipv6Cidr;

    /**
     * Whether enable ipv6.
     */
    private Boolean enableIpv6;

    /**
     * The ipv6 subnet id of the subnet instance.
     */
    private String ipv6SubnetId;

    /**
     * The available ip count.
     */
    private Integer availableIp;

    /**
     * The used ip count.
     */
    private Integer usedIp;

    /**
     * The ip type version.
     */
    private Integer ipVersion;

    /**
     * Creation time.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "GMT-8")
    private Date createdTime;

    /**
     * Whether default
     */
    @JsonProperty("isDefault")
    private Boolean isDefault;

    /**
     * The status of the subnet instance.
     */
    private String status;

    /**
     * The tags.
     */
    private List<Tag> tags;

    /**
     * The type of the subnet instance, like bm/common.
     */
    private String subnetType;
}
