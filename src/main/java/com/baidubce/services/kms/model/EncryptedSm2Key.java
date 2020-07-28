package com.baidubce.services.kms.model;

public class EncryptedSm2Key {

    private String publicKeyDer;

    private String encryptedPrivateKey;

    public EncryptedSm2Key() {
    }

    public EncryptedSm2Key(String publicKeyDer, String encryptedPrivateKey) {
        this.publicKeyDer = publicKeyDer;
        this.encryptedPrivateKey = encryptedPrivateKey;
    }

    public String getPublicKeyDer() {
        return publicKeyDer;
    }

    public void setPublicKeyDer(String publicKeyDer) {
        this.publicKeyDer = publicKeyDer;
    }

    public String getEncryptedPrivateKey() {
        return encryptedPrivateKey;
    }

    public void setEncryptedPrivateKey(String encryptedPrivateKey) {
        this.encryptedPrivateKey = encryptedPrivateKey;
    }
}
