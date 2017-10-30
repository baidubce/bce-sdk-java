package com.baidubce.services.bacnet.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by yuanyoujun on 2017/10/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BacnetGateway extends AbstractBceResponse {
    private long id;
    private String name;
    private String description;
    private boolean useSsl;
    private int instanceNumber;
    private String ipOrInterface;
    private int pollInterval;
    private int pollIntervalCov;
    private int whoIsInterval;
    private int subscribeDuration;
    private Date lastActiveTime;
    private String destTopics;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUseSsl() {
        return useSsl;
    }

    public void setUseSsl(boolean useSsl) {
        this.useSsl = useSsl;
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(int instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public String getIpOrInterface() {
        return ipOrInterface;
    }

    public void setIpOrInterface(String ipOrInterface) {
        this.ipOrInterface = ipOrInterface;
    }

    public int getPollInterval() {
        return pollInterval;
    }

    public void setPollInterval(int pollInterval) {
        this.pollInterval = pollInterval;
    }

    public int getPollIntervalCov() {
        return pollIntervalCov;
    }

    public void setPollIntervalCov(int pollIntervalCov) {
        this.pollIntervalCov = pollIntervalCov;
    }

    public int getWhoIsInterval() {
        return whoIsInterval;
    }

    public void setWhoIsInterval(int whoIsInterval) {
        this.whoIsInterval = whoIsInterval;
    }

    public int getSubscribeDuration() {
        return subscribeDuration;
    }

    public void setSubscribeDuration(int subscribeDuration) {
        this.subscribeDuration = subscribeDuration;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public String getDestTopics() {
        return destTopics;
    }

    public void setDestTopics(String destTopics) {
        this.destTopics = destTopics;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
