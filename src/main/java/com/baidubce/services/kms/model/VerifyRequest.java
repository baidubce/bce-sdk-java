package com.baidubce.services.kms.model;

import com.baidubce.auth.BceCredentials;

/**
 * 验签请求参数
 */
public class VerifyRequest extends GenericKmsRequest {
    private String keyId;
    private String keyVersion;
    private String algorithm;
    private String signature;
    private String message;
    private String messageType;

    public VerifyRequest() {
    }

    public VerifyRequest(String keyId, String keyVersion, String algorithm, String signature, String message) {
        this.keyId = keyId;
        this.keyVersion = keyVersion;
        this.algorithm = algorithm;
        this.signature = signature;
        this.message = message;
    }

    public VerifyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public VerifyRequest withKeyId(String keyId) {
        this.keyId = keyId;
        return this;
    }

    public String getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
    }

    public VerifyRequest withKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
        return this;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public VerifyRequest withAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public VerifyRequest withSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VerifyRequest withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public VerifyRequest withMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }
}