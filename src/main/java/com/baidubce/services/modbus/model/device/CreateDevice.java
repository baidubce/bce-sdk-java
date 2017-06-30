package com.baidubce.services.modbus.model.device;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateDevice extends GenericAccountRequest {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(Integer slaveId) {
        this.slaveId = slaveId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getGatewayUuid() {
        return gatewayUuid;
    }

    public void setGatewayUuid(String gatewayUuid) {
        this.gatewayUuid = gatewayUuid;
    }

    public Integer getBaud() {
        return baud;
    }

    public void setBaud(Integer baud) {
        this.baud = baud;
    }

    public Integer getDatabits() {
        return databits;
    }

    public void setDatabits(Integer databits) {
        this.databits = databits;
    }

    public Integer getStopbits() {
        return stopbits;
    }

    public void setStopbits(Integer stopbits) {
        this.stopbits = stopbits;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    private String code; // 设备名字 必填

    private Integer slaveId; // 1~247 必填

    private String description;

    private String address; // 必填 host:port, or com port, like "COM1

    private String mode; // 必填 TCP OR RTU

    private String gatewayUuid; // 必填

    // baud databits stopbits parity only for RTU
    private Integer baud; // mode为RTU时必填

    private Integer databits; // mode为RTU时必填

    private Integer stopbits; // mode为RTU时必填

    private String parity; // mode为RTU时必填 "NONE", "EVEN", "ODD"

}
