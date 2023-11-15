package com.baidubce.services.media.model;

import com.baidubce.model.AbstractBceResponse;

public class GetTranscodingEncryptionKeyResponse extends AbstractBceResponse{

    private String encryptionKey = null;

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetTranscodingEncryptionKeyResponse {\n");
        sb.append("    encryptionKey: ").append(encryptionKey).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
