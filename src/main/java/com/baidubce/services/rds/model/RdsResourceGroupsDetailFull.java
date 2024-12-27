package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsResourceGroupsDetailFull extends RdsResourceInfo{
    private List<RdsResourceInfo> groups;
    private String name;
    private String type;
    private String region;
    private String id;
    private String uuid;
    private String summary;
    private String url;
    private String accountId;
    private String userId;
    private List<RdsTag> tag;
    private String resUuid;

    public List<RdsResourceInfo> getGroups() {
        return groups;
    }

    public void setGroups(List<RdsResourceInfo> groups) {
        this.groups = groups;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<RdsTag> getTag() {
        return tag;
    }

    public void setTag(List<RdsTag> tag) {
        this.tag = tag;
    }

    public String getResUuid() {
        return resUuid;
    }

    public void setResUuid(String resUuid) {
        this.resUuid = resUuid;
    }
}
