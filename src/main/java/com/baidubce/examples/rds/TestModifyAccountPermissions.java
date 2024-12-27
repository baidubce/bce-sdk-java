package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.DatabasePrivilege;
import com.baidubce.services.rds.model.RdsModifyAccountPermissionRequest;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestModifyAccountPermissions {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsModifyAccountPermissionRequest request = new RdsModifyAccountPermissionRequest();

        request.setInstanceId("rds-tXjFULZA");
        request.setAccountName("nosuper");

        DatabasePrivilege privilege = new DatabasePrivilege();
        privilege.setAuthType("ReadOnly");
        privilege.setDbName("nosuperdb");

        List<DatabasePrivilege> privileges = new ArrayList<>();
        privileges.add(privilege);
        request.setDatabasePrivileges(privileges);
        AbstractBceResponse response =rdsClient.modifyAccountPermissions(request);
    }
}
