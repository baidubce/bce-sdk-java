/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.blb.model;

import java.util.List;

/**
 * SSL listener info modal.
 */
public class SslListener extends ListenerBase {

    /**
     * the certificate ids of listener.
     */
    private List<String> certIds;
    /**
     * the encryptionType of listener.
     */
    private String encryptionType;
    /**
     * the encryptionProtocols of listener.
     */
    private List<String> encryptionProtocols;
    /**
     * open dualAuth or not.
     */
    private boolean dualAuth;
    /**
     * the clientCert ids of listener.
     */
    private List<String> clientCertIds;

    public List<String> getCertIds() {
        return certIds;
    }

    public void setCertIds(List<String> certIds) {
        this.certIds = certIds;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public List<String> getEncryptionProtocols() {
        return encryptionProtocols;
    }

    public void setEncryptionProtocols(List<String> encryptionProtocols) {
        this.encryptionProtocols = encryptionProtocols;
    }

    public boolean isDualAuth() {
        return dualAuth;
    }

    public void setDualAuth(boolean dualAuth) {
        this.dualAuth = dualAuth;
    }

    public List<String> getClientCertIds() {
        return clientCertIds;
    }

    public void setClientCertIds(List<String> clientCertIds) {
        this.clientCertIds = clientCertIds;
    }

    @Override
    public String toString() {
        return "SslListener{" +
                "certIds=" + certIds +
                ", encryptionType='" + encryptionType + '\'' +
                ", encryptionProtocols=" + encryptionProtocols +
                ", dualAuth=" + dualAuth +
                ", clientCertIds=" + clientCertIds +
                '}';
    }
}
