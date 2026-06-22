package com.baidubce.services.gaiadb.model;

import java.util.List;

public class AccountListResponse extends GenericGaiadbResponse {
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
