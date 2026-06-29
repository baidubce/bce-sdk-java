package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request for resetting a MongoDB instance account password.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbResetPasswordRequest extends GenericMongodbRequest {
    private String dbInstanceId;

    private String accountName;

    private String accountPassword;

    private String plaintextAccountPassword;

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getPlaintextAccountPassword() {
        return plaintextAccountPassword;
    }

    public void setPlaintextAccountPassword(String plaintextAccountPassword) {
        this.plaintextAccountPassword = plaintextAccountPassword;
    }
}
