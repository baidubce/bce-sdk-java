package com.baidubce.services.rds.model;

public class RdsUpdateEncryptPolicyRequest {

    private EncryptPolicyRequest encryptStrategy;

    public EncryptPolicyRequest getEncryptStrategy() {
        return encryptStrategy;
    }

    public void setEncryptStrategy(EncryptPolicyRequest encryptStrategy) {
        this.encryptStrategy = encryptStrategy;
    }

    public static final class EncryptPolicyRequest {

        private boolean encryptEnable;
        private String keyManagementType;
        private String keyManagementServiceName;
        private String secretKeyID;

        public boolean getEncryptEnable() {
            return encryptEnable;
        }

        public void setEncryptEnable(boolean encryptEnable) {
            this.encryptEnable = encryptEnable;
        }

        public String getKeyManagementType() {
            return keyManagementType;
        }

        public void setKeyManagementType(String keyManagementType) {
            this.keyManagementType = keyManagementType;
        }

        public String getKeyManagementServiceName() {
            return keyManagementServiceName;
        }

        public void setKeyManagementServiceName(String keyManagementServiceName) {
            this.keyManagementServiceName = keyManagementServiceName;
        }

        public String getSecretKeyID() {
            return secretKeyID;
        }

        public void setSecretKeyID(String secretKeyID) {
            this.secretKeyID = secretKeyID;
        }
    }
}
