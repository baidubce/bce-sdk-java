package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsZoneResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;
public class TestGetZoneList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsZoneResponse zoneResponse = rdsClient.getZoneList();
        print("getZoneList", zoneResponse);
    }
}
