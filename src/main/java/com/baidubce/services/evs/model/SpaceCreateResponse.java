package com.baidubce.services.evs.model;

import java.util.List;

public class SpaceCreateResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -2344397488911277215L;

    private Long spaceId;

    private List<DomainGetResponse> domains;

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public List<DomainGetResponse> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainGetResponse> domains) {
        this.domains = domains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpaceCreateResponse that = (SpaceCreateResponse) o;

        if (spaceId != null ? !spaceId.equals(that.spaceId) : that.spaceId != null) {
            return false;
        }
        return domains != null ? domains.equals(that.domains) : that.domains == null;
    }

    @Override
    public int hashCode() {
        int result = spaceId != null ? spaceId.hashCode() : 0;
        result = 31 * result + (domains != null ? domains.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpaceCreateResponse{" +
                "spaceId=" + spaceId +
                ", domains=" + domains +
                '}';
    }
}

