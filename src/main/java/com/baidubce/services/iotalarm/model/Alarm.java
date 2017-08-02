package com.baidubce.services.iotalarm.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alarm extends AbstractBceResponse {
    public static final String NORMAL = "NORMAL";
    public static final String ALARMING = "ALARMING";
    public static final String WARN = "WARN";
    public static final String DISABLED = "DISABLED";
    public static final String ENABLED = "ENABLED";

    private String uuid;
    private String name;
    private String desc;
    private String kind;
    private String severity;
    private String accountUuid;
    private String endpointName;
    private String topic;
    private String select;
    private String condition;
    private String alarmTrigger;
    private String smsReceiver;
    private String smsVars;
    private String smsMsgType;
    private String mqttMsgType;
    private String destTopic;
    private String alarmState;
    private long startTime;
    private long refreshTime;
    private long recoverTime;
    private String lastMsg;
    private String disabled;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAlarmTrigger() {
        return alarmTrigger;
    }

    public void setAlarmTrigger(String alarmTrigger) {
        this.alarmTrigger = alarmTrigger;
    }

    public String getSmsReceiver() {
        return smsReceiver;
    }

    public void setSmsReceiver(String smsReceiver) {
        this.smsReceiver = smsReceiver;
    }

    public String getSmsVars() {
        return smsVars;
    }

    public void setSmsVars(String smsVars) {
        this.smsVars = smsVars;
    }

    public String getSmsMsgType() {
        return smsMsgType;
    }

    public void setSmsMsgType(String smsMsgType) {
        this.smsMsgType = smsMsgType;
    }

    public String getMqttMsgType() {
        return mqttMsgType;
    }

    public void setMqttMsgType(String mqttMsgType) {
        this.mqttMsgType = mqttMsgType;
    }

    public String getDestTopic() {
        return destTopic;
    }

    public void setDestTopic(String destTopic) {
        this.destTopic = destTopic;
    }

    public String getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(String alarmState) {
        this.alarmState = alarmState;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public long getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(long recoverTime) {
        this.recoverTime = recoverTime;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

}
