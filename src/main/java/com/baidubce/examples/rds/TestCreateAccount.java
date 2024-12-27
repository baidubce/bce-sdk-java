package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.DatabasePrivilege;
import com.baidubce.services.rds.model.RdsAccountType;
import com.baidubce.services.rds.model.RdsCreateAccountRequest;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;
public class TestCreateAccount {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsCreateAccountRequest createAccountRequest = new RdsCreateAccountRequest();
        createAccountRequest.setInstanceId("rds-nr2GCznE");
        createAccountRequest.setAccountName("test_acc");
        createAccountRequest.setPassword("rds_passwordKJH");
        createAccountRequest.setAccountType(RdsAccountType.Common);
        DatabasePrivilege privilege = new DatabasePrivilege();
        privilege.setDbName("test");
        privilege.setAuthType("ReadOnly");
        List<DatabasePrivilege> privileges = new ArrayList<>();
        privileges.add(privilege);
        createAccountRequest.setDatabasePrivileges(privileges);
        createAccountRequest.setDesc("rds_sdk_created_createAccount_test");
        createAccountRequest.setType("OnlyMaster");
        try {
            AbstractBceResponse accountResponse = rdsClient.createAccount(createAccountRequest);
            print("createAccount", accountResponse);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
