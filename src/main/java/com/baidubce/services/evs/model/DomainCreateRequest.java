package com.baidubce.services.evs.model;

public class DomainCreateRequest extends EvsBaseRequest {

    private static final long serialVersionUID = 8757426093543848248L;

    private String domain;

    /**
     * Support: UPSTREAM、DOWNSTREAM
     * GB28181 space without push river basin name
     */
    private String type;

    public DomainCreateRequest() {
    }

    public DomainCreateRequest(String domain, String type) {
        this.domain = domain;
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomainCreateRequest that = (DomainCreateRequest) o;

        if (domain != null ? !domain.equals(that.domain) : that.domain != null) {
            return false;
        }
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DomainCreateRequest{" +
                "domain='" + domain + '\'' +
                ", type=" + type +
                '}';
    }
}
