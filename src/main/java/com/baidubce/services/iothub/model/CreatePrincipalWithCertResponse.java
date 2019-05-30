/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iothub.model;

/**
 * Represent the create Principal with certificate response.
 */
public class CreatePrincipalWithCertResponse extends QueryResponse {


    private String privateKey;

    private String publicKey;

    private String clientCertId;

    private String clientCertDownloadUrl;

    private String principalName;

    private String certificate;

    private String clientCertCA;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getClientCertId() {
        return clientCertId;
    }

    public void setClientCertId(String clientCertId) {
        this.clientCertId = clientCertId;
    }

    public String getClientCertDownloadUrl() {
        return clientCertDownloadUrl;
    }

    public void setClientCertDownloadUrl(String clientCertDownloadUrl) {
        this.clientCertDownloadUrl = clientCertDownloadUrl;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getClientCertCA() {
        return clientCertCA;
    }

    public void setClientCertCA(String clientCertCA) {
        this.clientCertCA = clientCertCA;
    }
}
