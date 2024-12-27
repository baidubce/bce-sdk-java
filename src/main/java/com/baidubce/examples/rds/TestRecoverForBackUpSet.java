package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsRecoverForBackUpSetRequest;
import com.baidubce.services.rds.model.RecoveryToSourceInstanceModel;
import com.baidubce.services.rds.model.RecoveryToSourceInstanceTableModel;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestRecoverForBackUpSet {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsRecoverForBackUpSetRequest request = new RdsRecoverForBackUpSetRequest();
        request.setInstanceId("rds-JC7L0yFI");
        request.setSourceInstanceId("rds-1Cm4tVAO");
        request.setTargetInstanceId("rds-1Cm4tVAO");
        request.setSnapshotId("1720637060912738501");
        List<RecoveryToSourceInstanceModel> datas = new ArrayList<>();
        RecoveryToSourceInstanceModel data = new RecoveryToSourceInstanceModel();

        List<RecoveryToSourceInstanceTableModel> tables = new ArrayList<>();
        RecoveryToSourceInstanceTableModel table = new RecoveryToSourceInstanceTableModel();

        table.setTableName("newcxtest");
        table.setNewTablename("cxtest");

        data.setDbName("newdeadlock");
        data.setNewDbname("deadlock");
        data.setRestoreMode("table");
        data.setTables(tables);

        datas.add(data);
        request.setData(datas);

        AbstractBceResponse response = rdsClient.recoverForBackUpSet(request);
        print("recoverForBackUpSet", response);
    }
}
