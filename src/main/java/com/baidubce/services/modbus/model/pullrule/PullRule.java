package com.baidubce.services.modbus.model.pullrule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRule {
    private String uuid;

    private String parserObjectUuid;

    private String deviceUuid;

    private int functionCode;

    private int startAddress;

    private int length;

    private int pullInterval;

    private String createTime;

    private String updateTime;

    private DeviceUnitDto device;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParserObjectUuid() {
        return parserObjectUuid;
    }

    public void setParserObjectUuid(String parserObjectUuid) {
        this.parserObjectUuid = parserObjectUuid;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public int getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(int functionCode) {
        this.functionCode = functionCode;
    }

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPullInterval() {
        return pullInterval;
    }

    public void setPullInterval(int pullInterval) {
        this.pullInterval = pullInterval;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public DeviceUnitDto getDevice() {
        return device;
    }

    public void setDevice(DeviceUnitDto device) {
        this.device = device;
    }
}
