package com.baidubce.services.tsdb.model;

import static com.baidubce.services.tsdb.TsdbConstants.DateFormat.DATETIME_FORMAT;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Represent the response for getting database.
 */
@Data
public class GetDatabaseResponse extends AbstractBceResponse {

    private String databaseId;
    private String databaseName;
    private String description;
    private String endpoint;
    private DatabaseQuotaInfo quota;
    private String status;
    private Boolean autoExport;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT, timezone = "UTC")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT, timezone = "UTC")
    private Date expiredTime;

}
