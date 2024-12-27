package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsBilling;
import com.baidubce.services.rds.model.RdsCreateInstanceResponse;
import com.baidubce.services.rds.model.RdsCreateProxyInstance;
import com.baidubce.services.rds.model.RdsPaymentTiming;
import com.baidubce.services.rds.model.RdsSubnet;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestCreateProxyInstance {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsCreateProxyInstance proxyInstance = new RdsCreateProxyInstance();

        RdsBilling billing = new RdsBilling();
        RdsPaymentTiming rdsPaymentTiming = RdsPaymentTiming.create("Postpaid");
        billing.setPaymentTiming(rdsPaymentTiming);

        proxyInstance.setSourceInstanceId("rds-tKEPFelh");
        proxyInstance.setInstanceName("mysql80");

        proxyInstance.setNodeAmount(2);

        List<String> zoneNames = new ArrayList<>();
        zoneNames.add("cn-bj-d");
        proxyInstance.setZoneNames(zoneNames);

        proxyInstance.setVpcId("vpc-70pxg3pmv8rv");

        RdsSubnet subnet = new RdsSubnet();
        subnet.setSubnetId("sbn-dqafncqsy3y4");
        subnet.setZoneName("cn-bj-d");

        proxyInstance.setIsDirectPay(false);

        subnet.setZoneName("cn-bj-d");
        List<RdsSubnet> subnets = new ArrayList<>();
        subnets.add(subnet);
        proxyInstance.setSubnets(subnets);

        RdsCreateInstanceResponse response = rdsClient.createInstanceProxy(proxyInstance);
        print("createProxyInstance", response);
    }
}
