/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.response;

import java.sql.Timestamp;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cas.model.CasConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * PKI information of a certificate.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertPkiResponse extends AbstractBceResponse {
    @JsonProperty("domainName")
    private String commonName;
    private String company;
    @JsonProperty("department")
    private String division;
    private String address;
    private Region region;
    private String algorithm;
    private String strength;
    private String csrPem;
    private String certPem;
    private String certCaPem;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    @JsonProperty("startTime")
    private Timestamp effectTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    private Timestamp expireTime;

    /**
     * Region of the company.
     */
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Region {
        private String province;
        private String city;
        private String country;
    }
}
