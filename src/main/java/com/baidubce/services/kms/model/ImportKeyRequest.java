package com.baidubce.services.kms.model;

import com.baidubce.auth.BceCredentials;

public class ImportKeyRequest extends GenericKmsRequest {

    private String keyId;

    private String importToken;

    private String encryptedKey;

    private String keySpec;

    private String keyUsage;

    public ImportKeyRequest() {
    }

    public ImportKeyRequest(String keyId, String importToken, String encryptedKey, String keySpec, String keyUsage) {
        this.keyId = keyId;
        this.importToken = importToken;
        this.encryptedKey = encryptedKey;
        this.keySpec = keySpec;
        this.keyUsage = keyUsage;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getImportToken() {
        return importToken;
    }

    public void setImportToken(String importToken) {
        this.importToken = importToken;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public String getKeySpec() {
        return keySpec;
    }

    public void setKeySpec(String keySpec) {
        this.keySpec = keySpec;
    }

    public String getKeyUsage() {
        return keyUsage;
    }

    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }

    @Override
    public ImportKeyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
