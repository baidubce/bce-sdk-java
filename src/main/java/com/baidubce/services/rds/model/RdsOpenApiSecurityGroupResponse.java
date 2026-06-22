package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Response of listing security groups bound to a rds instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RdsOpenApiSecurityGroupResponse extends AbstractBceResponse {

    private String eniId;

    private String eniUuid;

    private String eniName;

    private Boolean primary;

    private List<RdsOpenApiSecurityGroup> groups;

    private List<RdsSecurityGroupRule> activeRules;

    public String getEniId() {
        return eniId;
    }

    public void setEniId(String eniId) {
        this.eniId = eniId;
    }

    public String getEniUuid() {
        return eniUuid;
    }

    public void setEniUuid(String eniUuid) {
        this.eniUuid = eniUuid;
    }

    public String getEniName() {
        return eniName;
    }

    public void setEniName(String eniName) {
        this.eniName = eniName;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public List<RdsOpenApiSecurityGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<RdsOpenApiSecurityGroup> groups) {
        this.groups = groups;
    }

    public List<RdsSecurityGroupRule> getActiveRules() {
        return activeRules;
    }

    public void setActiveRules(List<RdsSecurityGroupRule> activeRules) {
        this.activeRules = activeRules;
    }
}
