package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of rds account list
 */
public class RdsAccountListResponse extends AbstractBceResponse {

    private List<RdsAccount> accounts = new ArrayList<RdsAccount>();

    public List<RdsAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<RdsAccount> accounts) {
        this.accounts = accounts;
    }
}
