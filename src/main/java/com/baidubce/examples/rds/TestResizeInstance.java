package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsInstanceResizeRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestResizeInstance {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsInstanceResizeRequest resizeRequest = new RdsInstanceResizeRequest();
        resizeRequest.setInstanceId("rds-EzZEzrYA");
        resizeRequest.setVolumeCapacity(10);
        resizeRequest.setIsDirectPay(true);
        AbstractBceResponse resizeResponse = rdsClient.resizeInstance(resizeRequest);
        print("resizeInstance", resizeResponse);

    }
}
