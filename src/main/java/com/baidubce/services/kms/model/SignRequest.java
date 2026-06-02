package com.baidubce.services.kms.model;

import com.baidubce.auth.BceCredentials;

/**
 * 签名请求参数
 */
public class SignRequest extends GenericKmsRequest{
    private String keyId;
    private String keyVersion;
    private String algorithm;
    private String message;
    private String messageType;

    public SignRequest() {
    }

    public SignRequest(String keyId, String keyVersion, String algorithm, String message, String messageType) {
        this.keyId = keyId;
        this.keyVersion = keyVersion;
        this.algorithm = algorithm;
        this.message = message;
        this.messageType = messageType;
    }

    public SignRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public SignRequest withKeyId(String keyId) {
        this.keyId = keyId;
        return this;
    }

    public String getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
    }

    public SignRequest withKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
        return this;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public SignRequest withAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignRequest withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public SignRequest withMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

}