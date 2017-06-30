package com.baidubce.services.modbus.model.device;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateDeviceRequest extends GenericAccountRequest {
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    private String state;

    private Integer slaveId;

    private String description;

    private String address; // host:port, or com port, like "COM1

    private String mode; // TCP OR RTU

    private Integer baud;

    private Integer databits;

    private Integer stopbits;

    private String parity; // "NONE", "EVEN", "ODD"
}
