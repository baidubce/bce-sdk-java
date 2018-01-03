package com.baidubce.services.bacnet.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2017/10/18.
 */
public class UpdateBacnetGatewayRequest extends GenericAccountRequest {
    private String description;
    private boolean useSsl;
    private int instanceNumber;
    private String ipOrInterface;
    private int pollInterval;
    private int pollIntervalCov;
    private int whoIsInterval;
    private int subscribeDuration;
    private int objectDiscoverInterval;
    private String subscribeType;
    private double covIncrement;
    private String destTopics;

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

    public String getDestTopics() {
        return destTopics;
    }

    public void setDestTopics(String destTopics) {
        this.destTopics = destTopics;
    }

    public int getObjectDiscoverInterval() {
        return objectDiscoverInterval;
    }

    public void setObjectDiscoverInterval(int objectDiscoverInterval) {
        this.objectDiscoverInterval = objectDiscoverInterval;
    }

    public String getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(String subscribeType) {
        this.subscribeType = subscribeType;
    }

    public double getCovIncrement() {
        return covIncrement;
    }

    public void setCovIncrement(double covIncrement) {
        this.covIncrement = covIncrement;
    }

}
