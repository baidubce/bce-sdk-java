package com.baidubce.services.evs.model;

public class DomainGetResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -1340325038447674555L;

    private String domain;

    /**
     * Support：UPSTREAM、DOWNSTREAM
     */
    private String type;

    private String cname;

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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomainGetResponse that = (DomainGetResponse) o;

        if (domain != null ? !domain.equals(that.domain) : that.domain != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        return cname != null ? cname.equals(that.cname) : that.cname == null;
    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DomainGetResponse{" +
                "domain='" + domain + '\'' +
                ", type='" + type + '\'' +
                ", cname='" + cname + '\'' +
                "} " + super.toString();
    }
}
