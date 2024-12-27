package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsAccountPrivileges;
import com.baidubce.services.rds.model.RdsCharacterSet;
import com.baidubce.services.rds.model.RdsCreateDatabaseRequest;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;

public class TestCreateDatabase {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsCreateDatabaseRequest request = new RdsCreateDatabaseRequest();
        request.setInstanceId("rds-Qk2LpXBj");
        request.setCharacterSetName(RdsCharacterSet.UTF8);
        request.setDbName("testMysqlDatabase");
        request.setRemark("testRemark");
        RdsAccountPrivileges privileges = new RdsAccountPrivileges();
        privileges.setAccountName("rdsroot1");
        privileges.setAuthType("ReadWrite");
        List<RdsAccountPrivileges> privilegesList = new ArrayList<>();
        privilegesList.add(privileges);
        request.setAccountPrivileges(privilegesList);
        request.setCtype("zh_CN.utf-8");
        request.setCollate("zh_CN.utf-8");
        request.setOwner("rdsroot1");
        AbstractBceResponse response = rdsClient.createDatabase(request);
    }
}
