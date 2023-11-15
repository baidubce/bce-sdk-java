package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.CdnRequest;
import com.baidubce.services.cdn.model.domain.HttpsConfig;

import java.util.List;

public class BatchUploadCertRequest extends CdnRequest {


    private List<String> domains;

    private Cert certificate;

    private HttpsConfig https;

    public BatchUploadCertRequest() {
    }

    public BatchUploadCertRequest withHttps(HttpsConfig https) {
        setHttps(https);
        return this;
    }

    public BatchUploadCertRequest withCertificate(Cert certificate) {
        setCertificate(certificate);
        return this;
    }

    /**
     * @param domains the domains name
     * @return returns this object
     */
    public BatchUploadCertRequest withDomains(List<String> domains) {
        setDomains(domains);
        return this;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public Cert getCertificate() {
        return certificate;
    }

    public void setCertificate(Cert certificate) {
        this.certificate = certificate;
    }

    public HttpsConfig getHttps() {
        return https;
    }

    public void setHttps(HttpsConfig https) {
        this.https = https;
    }
}
