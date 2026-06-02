package com.baidubce.services.kms.model;

/**
 * 验签响应结果
 */
public class VerifyResponse extends KmsResponse {
    private String keyId;
    private String keyVersion;
    private boolean signatureValid;

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

    public boolean isSignatureValid() {
        return signatureValid;
    }

    public void setSignatureValid(boolean signatureValid) {
        this.signatureValid = signatureValid;
    }
}
