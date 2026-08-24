/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cas.model.CasConstants;
import com.baidubce.services.cas.model.type.TimeUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Detail of a certificate.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertDetails extends AbstractBceResponse {
    /**
     * Resource id of the certificate.
     */
    @JsonProperty("productId")
    private String certId;
    /**
     * Product name.
     */
    private String productName;
    /**
     * Certificate brand.
     */
    @JsonProperty("brand")
    private String certBrand;
    /**
     * Certificate validation level.
     */
    private String certType;
    /**
     * Certificate product type.
     */
    private String productType;
    /**
     * Number of standard domains.
     */
    @JsonProperty("domainNumber")
    private int standardDomainNum;
    /**
     * Number of wildcard domains.
     */
    @JsonProperty("wildcardNumber")
    private int wildcardDomainNum;
    /**
     * Certificate status.
     */
    @JsonProperty("status")
    private String certStatus;
    /**
     * Application status.
     */
    @JsonProperty("processStatus")
    private String applyStatus;
    /**
     * Time when the certificate was applied for.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    private Timestamp applyTime;
    /**
     * Time when the certificate took effect.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    private Timestamp effectTime;
    /**
     * Time when the application was submitted.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    @JsonProperty("submitTime")
    private Timestamp uploadTime;
    /**
     * Time when the order expires.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    private Timestamp expireTime;
    /**
     * Time when the latest certificate took effect.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    private Timestamp latestEffectTime;
    /**
     * Time when the latest certificate expires.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    private Timestamp latestExpireTime;
    /**
     * Creation time.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CasConstants.UTC_DATE, timezone = CasConstants.UTC)
    private Timestamp createTime;
    /**
     * Reason why the application failed.
     */
    @JsonProperty("errorMessage")
    private String failedReason;
    /**
     * Domain control validation method.
     */
    @JsonProperty("verifyMode")
    private String authType;
    /**
     * Primary domain.
     */
    @JsonProperty("domainName")
    private String primaryDomain;
    /**
     * Validation value.
     */
    private String value;
    /**
     * Url of the validation file.
     */
    private String fileUrl;
    /**
     * Dns validation prefix.
     */
    private String dnsName;
    /**
     * Remaining time of the validation, in seconds.
     */
    @JsonProperty("leftTime")
    private Long authLeftTime;
    /**
     * Whether the certificate can be downloaded.
     */
    private Boolean downloadSupported;
    /**
     * Whether the certificate uses the new download format.
     */
    private Boolean newFormat = true;
    /**
     * Whether a password is required to open the downloaded package.
     */
    private Boolean needCertPassword = false;
    /**
     * Additional domains.
     */
    private List<String> bindDomains;
    /**
     * File validation information of each domain.
     */
    private List<DcvInfo> dcvs = new ArrayList<>();
    /**
     * Order id at the certificate authority.
     */
    private String preOrderId;
    /**
     * Order id of the re-issue request.
     */
    private String replaceOrderId;
    /**
     * Whether the certificate can be re-issued.
     */
    private Boolean canApplyReplace = false;
    /**
     * Host name of the CNAME validation record.
     */
    private String host;
    /**
     * Value of the CNAME validation record.
     */
    private String cnamevalue;
    /**
     * Validation type.
     */
    private String type;
    /**
     * Name of the validation file.
     */
    private String httpFilename;
    /**
     * Content of the validation file.
     */
    private String httpvalue;
    /**
     * Purchase duration.
     */
    private int duration;
    /**
     * Unit of the purchase duration.
     */
    private TimeUnit timeUnit;
    /**
     * Whether the certificate is issued by Baidu.
     */
    private boolean fromBaidu;
    /**
     * Whether the domain is validated automatically.
     */
    private boolean autoAuth = false;
    /**
     * Resource id of the renewed certificate.
     */
    private String renewCertId;
    /**
     * Whether the certificate can be downloaded right after being re-issued.
     */
    private Boolean downloadNow;
    /**
     * Supported download format. 0 for the legacy format, 1 for the current format.
     */
    private Integer downloadFormat = 0;
    /**
     * Whether the certificate can be renewed.
     */
    private Boolean canRenew;

    public void addDcvs(String domain, String fileUrl) {
        this.dcvs.add(new DcvInfo(domain, fileUrl));
    }
}
