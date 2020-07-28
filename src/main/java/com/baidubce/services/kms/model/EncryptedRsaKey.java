package com.baidubce.services.kms.model;

public class EncryptedRsaKey {

    private String publicKeyDer;

    private String encryptedD;

    private String encryptedP;

    private String encryptedQ;

    private String encryptedDp;

    private String encryptedDq;

    private String encryptedQinv;

    public EncryptedRsaKey() {
    }

    public EncryptedRsaKey(String publicKeyDer, String encryptedD,
                           String encryptedP, String encryptedQ, String encryptedDp,
                           String encryptedDq, String encryptedQinv) {
        this.publicKeyDer = publicKeyDer;
        this.encryptedD = encryptedD;
        this.encryptedP = encryptedP;
        this.encryptedQ = encryptedQ;
        this.encryptedDp = encryptedDp;
        this.encryptedDq = encryptedDq;
        this.encryptedQinv = encryptedQinv;
    }

    public String getPublicKeyDer() {
        return publicKeyDer;
    }

    public void setPublicKeyDer(String publicKeyDer) {
        this.publicKeyDer = publicKeyDer;
    }

    public String getEncryptedD() {
        return encryptedD;
    }

    public void setEncryptedD(String encryptedD) {
        this.encryptedD = encryptedD;
    }

    public String getEncryptedP() {
        return encryptedP;
    }

    public void setEncryptedP(String encryptedP) {
        this.encryptedP = encryptedP;
    }

    public String getEncryptedQ() {
        return encryptedQ;
    }

    public void setEncryptedQ(String encryptedQ) {
        this.encryptedQ = encryptedQ;
    }

    public String getEncryptedDp() {
        return encryptedDp;
    }

    public void setEncryptedDp(String encryptedDp) {
        this.encryptedDp = encryptedDp;
    }

    public String getEncryptedDq() {
        return encryptedDq;
    }

    public void setEncryptedDq(String encryptedDq) {
        this.encryptedDq = encryptedDq;
    }

    public String getEncryptedQinv() {
        return encryptedQinv;
    }

    public void setEncryptedQinv(String encryptedQinv) {
        this.encryptedQinv = encryptedQinv;
    }
}
