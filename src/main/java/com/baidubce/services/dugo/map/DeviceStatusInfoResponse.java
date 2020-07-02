/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Get response for query device status info
 */
public class DeviceStatusInfoResponse extends AbstractBceResponse {
    private String deviceId;
    private Info info;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public static class Info {
        private Device device;
        private OTA ota;
        private Version version;
        private SIM sim;

        public Device getDevice() {
            return device;
        }

        public void setDevice(Device device) {
            this.device = device;
        }

        public OTA getOta() {
            return ota;
        }

        public void setOta(OTA ota) {
            this.ota = ota;
        }

        public Version getVersion() {
            return version;
        }

        public void setVersion(Version version) {
            this.version = version;
        }

        public SIM getSim() {
            return sim;
        }

        public void setSim(SIM sim) {
            this.sim = sim;
        }

        public static class Device {
            private String pk;
            private String dn;
            private String sign;

            public String getPk() {
                return pk;
            }

            public void setPk(String pk) {
                this.pk = pk;
            }

            public String getDn() {
                return dn;
            }

            public void setDn(String dn) {
                this.dn = dn;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }

        public static class OTA {
            private String otaId;
            private String otaSecret;

            public String getOtaId() {
                return otaId;
            }

            public void setOtaId(String otaId) {
                this.otaId = otaId;
            }

            public String getOtaSecret() {
                return otaSecret;
            }

            public void setOtaSecret(String otaSecret) {
                this.otaSecret = otaSecret;
            }
        }

        public static class Version {
            private String main;
            private String mcu;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getMcu() {
                return mcu;
            }

            public void setMcu(String mcu) {
                this.mcu = mcu;
            }
        }

        public static class SIM {
            private List<String> type;
            private String iccid;

            public List<String> getType() {
                return type;
            }

            public void setType(List<String> type) {
                this.type = type;
            }

            public String getIccid() {
                return iccid;
            }

            public void setIccid(String iccid) {
                this.iccid = iccid;
            }
        }
    }
}
