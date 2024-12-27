package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUpdateWriteListResquest;

import java.util.Arrays;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateWriteList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsUpdateWriteListResquest resquest = new RdsUpdateWriteListResquest();
        resquest.setInstanceId("rds-5WIldjI3");
        List<String> ips = Arrays.asList("%");
        resquest.setSecurityIps(ips);
        AbstractBceResponse response =rdsClient.updateWriteList(resquest);
        print("updateWriteList", response);
    }
}
