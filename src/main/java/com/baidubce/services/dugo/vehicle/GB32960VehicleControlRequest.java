/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.services.dugo.AbstractDuGoRequest;

/**
 * Request for control vehicles
 */
public class GB32960VehicleControlRequest extends AbstractDuGoRequest {
    public String vin;
    public String iccid;
    private Integer commandId;
    private Object commandParam;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public Integer getCommandId() {
        return commandId;
    }

    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }

    public Object getCommandParam() {
        return commandParam;
    }

    public void setCommandParam(Object commandParam) {
        this.commandParam = commandParam;
    }

    public static class RemoteUpgradeCommand {
        private String dialPointName;
        private String dialUsername;
        private String dialPassword;
        private String serverAddress;
        private long serverPort;
        private String terminalManufacturer;
        private String hardwareVersion;
        private String firmwareVersion;
        private String upgradeUrl;
        private long connectionTimeout;

        public String getDialPointName() {
            return dialPointName;
        }

        public void setDialPointName(String dialPointName) {
            this.dialPointName = dialPointName;
        }

        public String getDialUsername() {
            return dialUsername;
        }

        public void setDialUsername(String dialUsername) {
            this.dialUsername = dialUsername;
        }

        public String getDialPassword() {
            return dialPassword;
        }

        public void setDialPassword(String dialPassword) {
            this.dialPassword = dialPassword;
        }

        public String getServerAddress() {
            return serverAddress;
        }

        public void setServerAddress(String serverAddress) {
            this.serverAddress = serverAddress;
        }

        public long getServerPort() {
            return serverPort;
        }

        public void setServerPort(long serverPort) {
            this.serverPort = serverPort;
        }

        public String getTerminalManufacturer() {
            return terminalManufacturer;
        }

        public void setTerminalManufacturer(String terminalManufacturer) {
            this.terminalManufacturer = terminalManufacturer;
        }

        public String getHardwareVersion() {
            return hardwareVersion;
        }

        public void setHardwareVersion(String hardwareVersion) {
            this.hardwareVersion = hardwareVersion;
        }

        public String getFirmwareVersion() {
            return firmwareVersion;
        }

        public void setFirmwareVersion(String firmwareVersion) {
            this.firmwareVersion = firmwareVersion;
        }

        public String getUpgradeUrl() {
            return upgradeUrl;
        }

        public void setUpgradeUrl(String upgradeUrl) {
            this.upgradeUrl = upgradeUrl;
        }

        public long getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(long connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }
    }

    public static class AlarmCommand {
        private int alarmLevel;
        private Object alarmInfo;

        public int getAlarmLevel() {
            return alarmLevel;
        }

        public void setAlarmLevel(int alarmLevel) {
            this.alarmLevel = alarmLevel;
        }

        public Object getAlarmInfo() {
            return alarmInfo;
        }

        public void setAlarmInfo(Object alarmInfo) {
            this.alarmInfo = alarmInfo;
        }
    }
}
