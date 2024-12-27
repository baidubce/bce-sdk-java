package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsRecoverForTimeNodeRequest;
import com.baidubce.services.rds.model.RecoveryToSourceInstanceModel;
import com.baidubce.services.rds.model.RecoveryToSourceInstanceTableModel;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;


public class TestRecoverForTimeNode {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsRecoverForTimeNodeRequest request = new RdsRecoverForTimeNodeRequest();
        request.setInstanceId("rds-1Cm4tVAO");
        request.setSourceInstanceId("rds-1Cm4tVAO");
        request.setTargetInstanceId("rds-1Cm4tVAO");
        request.setDatetime("2024-07-10T18:36:46Z");

        List<RecoveryToSourceInstanceModel> datas = new ArrayList<>();
        RecoveryToSourceInstanceModel data = new RecoveryToSourceInstanceModel();

        List<RecoveryToSourceInstanceTableModel> tables = new ArrayList<>();
        RecoveryToSourceInstanceTableModel table = new RecoveryToSourceInstanceTableModel();

        table.setTableName("deadlock");
        table.setNewTablename("newdeadlock");
        tables.add(table);

        data.setDbName("cxtest");
        data.setNewDbname("newcxtest");
        data.setRestoreMode("table");
        data.setTables(tables);


        datas.add(data);
        request.setData(datas);
        AbstractBceResponse response = rdsClient.recoverForTimeNode(request);
        print("recoverForTimeNode", response);
    }
}
