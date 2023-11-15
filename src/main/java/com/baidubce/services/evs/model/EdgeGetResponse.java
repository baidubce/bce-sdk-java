package com.baidubce.services.evs.model;

public class EdgeGetResponse extends EvsBaseResponse {

    private static final long serialVersionUID = 7277118689017561738L;

    private Long edgeId;

    private String region;

    private String city;

    private String serviceProvider;

    private String edgeName;

    private String sipId;

    private String sipIp;

    private Integer sipPort;

    private String sipProtocol;

    private String sipRealm;

    private String ftpIp;

    private Integer ftpPort;

    public Long getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Long edgeId) {
        this.edgeId = edgeId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getEdgeName() {
        return edgeName;
    }

    public void setEdgeName(String edgeName) {
        this.edgeName = edgeName;
    }

    public String getSipId() {
        return sipId;
    }

    public void setSipId(String sipId) {
        this.sipId = sipId;
    }

    public String getSipIp() {
        return sipIp;
    }

    public void setSipIp(String sipIp) {
        this.sipIp = sipIp;
    }

    public Integer getSipPort() {
        return sipPort;
    }

    public void setSipPort(Integer sipPort) {
        this.sipPort = sipPort;
    }

    public String getSipProtocol() {
        return sipProtocol;
    }

    public void setSipProtocol(String sipProtocol) {
        this.sipProtocol = sipProtocol;
    }

    public String getSipRealm() {
        return sipRealm;
    }

    public void setSipRealm(String sipRealm) {
        this.sipRealm = sipRealm;
    }

    public String getFtpIp() {
        return ftpIp;
    }

    public void setFtpIp(String ftpIp) {
        this.ftpIp = ftpIp;
    }

    public Integer getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(Integer ftpPort) {
        this.ftpPort = ftpPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EdgeGetResponse that = (EdgeGetResponse) o;

        if (edgeId != null ? !edgeId.equals(that.edgeId) : that.edgeId != null) {
            return false;
        }
        if (region != null ? !region.equals(that.region) : that.region != null) {
            return false;
        }
        if (city != null ? !city.equals(that.city) : that.city != null) {
            return false;
        }
        if (serviceProvider != null ? !serviceProvider.equals(that.serviceProvider) : that.serviceProvider != null) {
            return false;
        }
        if (edgeName != null ? !edgeName.equals(that.edgeName) : that.edgeName != null) {
            return false;
        }
        if (sipId != null ? !sipId.equals(that.sipId) : that.sipId != null) {
            return false;
        }
        if (sipIp != null ? !sipIp.equals(that.sipIp) : that.sipIp != null) {
            return false;
        }
        if (sipPort != null ? !sipPort.equals(that.sipPort) : that.sipPort != null) {
            return false;
        }
        if (sipProtocol != null ? !sipProtocol.equals(that.sipProtocol) : that.sipProtocol != null) {
            return false;
        }
        if (sipRealm != null ? !sipRealm.equals(that.sipRealm) : that.sipRealm != null) {
            return false;
        }
        if (ftpIp != null ? !ftpIp.equals(that.ftpIp) : that.ftpIp != null) {
            return false;
        }
        return ftpPort != null ? ftpPort.equals(that.ftpPort) : that.ftpPort == null;
    }

    @Override
    public int hashCode() {
        int result = edgeId != null ? edgeId.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (serviceProvider != null ? serviceProvider.hashCode() : 0);
        result = 31 * result + (edgeName != null ? edgeName.hashCode() : 0);
        result = 31 * result + (sipId != null ? sipId.hashCode() : 0);
        result = 31 * result + (sipIp != null ? sipIp.hashCode() : 0);
        result = 31 * result + (sipPort != null ? sipPort.hashCode() : 0);
        result = 31 * result + (sipProtocol != null ? sipProtocol.hashCode() : 0);
        result = 31 * result + (sipRealm != null ? sipRealm.hashCode() : 0);
        result = 31 * result + (ftpIp != null ? ftpIp.hashCode() : 0);
        result = 31 * result + (ftpPort != null ? ftpPort.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EdgeGetResponse{" +
                "edgeId=" + edgeId +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", serviceProvider='" + serviceProvider + '\'' +
                ", edgeName='" + edgeName + '\'' +
                ", sipId='" + sipId + '\'' +
                ", sipIp='" + sipIp + '\'' +
                ", sipPort=" + sipPort +
                ", sipProtocol='" + sipProtocol + '\'' +
                ", sipRealm='" + sipRealm + '\'' +
                ", ftpIp='" + ftpIp + '\'' +
                ", ftpPort=" + ftpPort +
                "} " + super.toString();
    }
}
