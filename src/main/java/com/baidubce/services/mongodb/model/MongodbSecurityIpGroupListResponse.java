package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Response of listing MongoDB security IP groups, matching the server-side
 * {@code SecurityIpGroupListResponse}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbSecurityIpGroupListResponse extends GenericMongodbResponse {

    private List<SecurityIpGroup> groups;

    public List<SecurityIpGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<SecurityIpGroup> groups) {
        this.groups = groups;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SecurityIpGroup {
        private String uuid;
        private String name;
        private String status;
        private List<String> ips;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getIps() {
            return ips;
        }

        public void setIps(List<String> ips) {
            this.ips = ips;
        }
    }
}
