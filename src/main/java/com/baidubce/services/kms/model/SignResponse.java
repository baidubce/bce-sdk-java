package com.baidubce.services.kms.model;

/**
 * Provides response for sign operation.
 */
public class SignResponse extends KmsResponse {

    private String keyId;
    private String keyVersion;
    private String signature;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}