package com.baidubce.services.iotalarm.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
public class CreateAlarmRequest extends GenericAccountRequest {
    private String name;
    private String desc;
    private String kind;
    private String severity;
    private String endpointName;
    private String topic;
    private String select;
    private String condition;
    private String alarmTrigger;
    private String smsReceiver;
    private String smsVars;
    private String smsMsgType;
    private String destTopic;

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

    public String getDestTopic() {
        return destTopic;
    }

    public void setDestTopic(String destTopic) {
        this.destTopic = destTopic;
    }
}
