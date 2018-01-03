package com.baidubce.services.bacnet.model;

import com.baidubce.model.GenericAccountRequest;


/**
 * Created by yuanyoujun on 2017/10/18.
 */
public class CreateBacnetGatewayRequest extends GenericAccountRequest {
    private String name;
    private String description;
    private boolean useSsl;
    private int instanceNumber;
    private String ipOrInterface;
    private int pollInterval;
    private int pollIntervalCov;
    private int whoIsInterval;
    private int objectDiscoverInterval;
    private int subscribeDuration;
    private String subscribeType;
    private double covIncrement;
    private String destTopics;

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


    public double getCovIncrement() {
        return covIncrement;
    }

    public void setCovIncrement(double covIncrement) {
        this.covIncrement = covIncrement;
    }

    public String getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(String subscribeType) {
        this.subscribeType = subscribeType;
    }
}
