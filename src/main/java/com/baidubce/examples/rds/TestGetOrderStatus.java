package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetOrderStatusRequest;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetOrderStatus {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetOrderStatusRequest request = new RdsGetOrderStatusRequest();
        request.setOrderId("0fa2f32386184e7aa47233446b9614dd");
        AbstractBceResponse response = rdsClient.getOrderStatus(request);
        print("getOrderStatus", response);
    }
}
