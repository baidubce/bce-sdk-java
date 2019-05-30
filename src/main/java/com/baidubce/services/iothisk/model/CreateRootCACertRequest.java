package com.baidubce.services.iothisk.model;

/**
 * Represent the request for create root cert.
 */
public class CreateRootCACertRequest extends IotPkiManageRequest {

    /**
     * Validity period of root cert in days.
     */
    private int duration;

    /**
     * Root cert info.
     */
    private CertRequestInfo certRequestInfo;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CreateRootCACertRequest withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public CertRequestInfo getCertRequestInfo() {
        return certRequestInfo;
    }

    public void setCertRequestInfo(CertRequestInfo certRequestInfo) {
        this.certRequestInfo = certRequestInfo;
    }

    public CreateRootCACertRequest withCertRequestInfo(CertRequestInfo certRequestInfo) {
        this.certRequestInfo = certRequestInfo;
        return this;
    }

    /**
     * Represent the info for create root cert.
     */
    public static class CertRequestInfo {

        /**
         * Country or region of root cert applicant.
         */
        private String country;

        /**
         * Common name of root cert applicant.
         */
        private String commonName;

        /**
         * Organization of root cert applicant.
         */
        private String organization;

        /**
         * Organization unit of root cert applicant.
         */
        private String unit;

        /**
         * Email address of root cert applicant.
         */
        private String emailAddress;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public CertRequestInfo withCountry(String country) {
            this.country = country;
            return this;
        }

        public String getCommonName() {
            return commonName;
        }

        public void setCommonName(String commonName) {
            this.commonName = commonName;
        }

        public CertRequestInfo withCommonName(String commonName) {
            this.commonName = commonName;
            return this;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public CertRequestInfo withOrganization(String organization) {
            this.organization = organization;
            return this;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public CertRequestInfo withUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public CertRequestInfo withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

    }

}
