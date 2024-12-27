package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsBilling;
import com.baidubce.services.rds.model.RdsCreateInstanceResponse;
import com.baidubce.services.rds.model.RdsCreateReadableInstance;
import com.baidubce.services.rds.model.RdsPaymentTiming;
import com.baidubce.services.rds.model.RdsReservation;
import com.baidubce.services.rds.model.RdsSubnet;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestCreateReadableInstance {
    public static void main(String[] args) {

        RdsClient rdsClient = createRdsClient();

        RdsCreateReadableInstance createReadableInstance = new RdsCreateReadableInstance();

        RdsBilling billing = new RdsBilling();
        RdsPaymentTiming rdsPaymentTiming = RdsPaymentTiming.create("Postpaid");
        RdsReservation reservation = new RdsReservation();
        reservation.setReservationLength(1);
        billing.setPaymentTiming(rdsPaymentTiming);
        billing.setReservation(reservation);
        createReadableInstance.setBilling(billing);

        createReadableInstance.setInstanceName("rds-oKFG");
        createReadableInstance.setCpuCount(1);
        createReadableInstance.setMemoryCapacity(2);
        createReadableInstance.setVolumeCapacity(50);
        createReadableInstance.setDiskIoType("normal_io");
        createReadableInstance.setIsDirectPay(false);
        createReadableInstance.setPurchaseCount(1);
        createReadableInstance.setInstanceName("rds-019");
        String sourceInstanceId = "rds-tKEPFelh";
        createReadableInstance.setSourceInstanceId(sourceInstanceId);
        String zoneName = "cn-bj-d";
        List<String> zoneNames = new ArrayList<>();
        zoneNames.add(zoneName);
        createReadableInstance.setZoneNames(zoneNames);
        createReadableInstance.setVpcId("vpc-70pxg3pmv8rv");

        RdsSubnet subnet = new RdsSubnet();
        subnet.setSubnetId("sbn-dqafncqsy3y4");
        subnet.setZoneName("cn-bj-d");
        List<RdsSubnet> subnets = new ArrayList<>();
        subnets.add(subnet);
        createReadableInstance.setSubnets(subnets);
        createReadableInstance.setEntryPort(3306);

        RdsCreateInstanceResponse response = rdsClient.createInstanceReadableReplica(createReadableInstance);
        print("createReadableInstance", response);
    }
}
