package com.baidubce.services.bec.model.vo;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

import static com.baidubce.BceConstants.DEFAULT_DATETIME_FORMAT;

/**
 * DeploySet
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeploySetVo extends AbstractBceResponse {

    /**
     * The deployment set id of the deployment set.
     */
    private String deploysetId;

    /**
     * The name of the deployment set.
     */
    private String name;

    /**
     * The description of the deployment set.
     */
    private String desc;

    /**
     * The maximum of concurrency of the deployment set.
     */
    private Integer concurrency;

    /**
     * The instance count of the deployment set.
     */
    private Integer instanceCount;

    /**
     * The total instances of the deployment set.
     */
    private Integer instanceTotal;

    /**
     * Creation time.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DEFAULT_DATETIME_FORMAT, timezone = "GMT-8")
    private Date createTime;
}
