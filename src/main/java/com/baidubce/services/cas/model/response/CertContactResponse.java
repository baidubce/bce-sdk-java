/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.response;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Company and contact information of a certificate.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertContactResponse extends AbstractBceResponse {
    private String company;
    @JsonProperty("department")
    private String division;
    private String address;
    private String postalCode;
    @JsonProperty("companyPhone")
    private String phone;
    @JsonProperty("orderName")
    private String adminName;
    @JsonProperty("orderGivenName")
    private String adminFirstName;
    @JsonProperty("orderFamilyName")
    private String adminLastName;
    @JsonProperty("orderPosition")
    private String adminTitle;
    @JsonProperty("orderEmail")
    private String adminEmail;
    @JsonProperty("orderPhone")
    private String adminPhone;
    private String techName;
    @JsonProperty("techGivenName")
    private String techFirstName;
    @JsonProperty("techFamilyName")
    private String techLastName;
    @JsonProperty("techPosition")
    private String techTitle;
    private String techEmail;
    private String techPhone;
    private Region region;

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
