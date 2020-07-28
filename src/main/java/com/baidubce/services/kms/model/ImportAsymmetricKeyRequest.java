package com.baidubce.services.kms.model;

import com.baidubce.auth.BceCredentials;

public class ImportAsymmetricKeyRequest extends GenericKmsRequest {

    private String keyId;

    private String importToken;

    private String asymmetricKeySpec;

    private String asymmetricKeyUsage;

    private String encryptedKeyEncryptionKey;

    private EncryptedRsaKey encryptedRsaKey;

    private EncryptedSm2Key encryptedSm2Key;

    public ImportAsymmetricKeyRequest() {
    }

    public ImportAsymmetricKeyRequest(String keyId, String importToken,
                                      String asymmetricKeySpec, String asymmetricKeyUsage,
                                      String encryptedKeyEncryptionKey, EncryptedRsaKey encryptedRsaKey) {
        this.keyId = keyId;
        this.importToken = importToken;
        this.asymmetricKeySpec = asymmetricKeySpec;
        this.asymmetricKeyUsage = asymmetricKeyUsage;
        this.encryptedKeyEncryptionKey = encryptedKeyEncryptionKey;
        this.encryptedRsaKey = encryptedRsaKey;
    }

    public ImportAsymmetricKeyRequest(String keyId, String importToken,
                                      String asymmetricKeySpec, String asymmetricKeyUsage,
                                      String encryptedKeyEncryptionKey, EncryptedSm2Key encryptedSm2Key) {
        this.keyId = keyId;
        this.importToken = importToken;
        this.asymmetricKeySpec = asymmetricKeySpec;
        this.asymmetricKeyUsage = asymmetricKeyUsage;
        this.encryptedKeyEncryptionKey = encryptedKeyEncryptionKey;
        this.encryptedSm2Key = encryptedSm2Key;
    }

    @Override
    public ImportAsymmetricKeyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
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

    public String getAsymmetricKeySpec() {
        return asymmetricKeySpec;
    }

    public void setAsymmetricKeySpec(String asymmetricKeySpec) {
        this.asymmetricKeySpec = asymmetricKeySpec;
    }

    public String getAsymmetricKeyUsage() {
        return asymmetricKeyUsage;
    }

    public void setAsymmetricKeyUsage(String asymmetricKeyUsage) {
        this.asymmetricKeyUsage = asymmetricKeyUsage;
    }

    public String getEncryptedKeyEncryptionKey() {
        return encryptedKeyEncryptionKey;
    }

    public void setEncryptedKeyEncryptionKey(String encryptedKeyEncryptionKey) {
        this.encryptedKeyEncryptionKey = encryptedKeyEncryptionKey;
    }

    public EncryptedRsaKey getEncryptedRsaKey() {
        return encryptedRsaKey;
    }

    public void setEncryptedRsaKey(EncryptedRsaKey encryptedRsaKey) {
        this.encryptedRsaKey = encryptedRsaKey;
    }

    public EncryptedSm2Key getEncryptedSm2Key() {
        return encryptedSm2Key;
    }

    public void setEncryptedSm2Key(EncryptedSm2Key encryptedSm2Key) {
        this.encryptedSm2Key = encryptedSm2Key;
    }
}
