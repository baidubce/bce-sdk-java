package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsBilling;
import com.baidubce.services.rds.model.RdsCreateInstanceRequest;
import com.baidubce.services.rds.model.RdsCreateInstanceResponse;
import com.baidubce.services.rds.model.RdsEngine;
import com.baidubce.services.rds.model.RdsPaymentTiming;
import com.baidubce.services.rds.model.RdsRenewTimeUnit;
import com.baidubce.services.rds.model.RdsReservation;
import com.baidubce.services.rds.model.RdsSubnet;
import com.baidubce.services.rds.model.RdsTag;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestCreateInstance {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsCreateInstanceRequest request = new RdsCreateInstanceRequest();
        RdsBilling billing = new RdsBilling();
        RdsPaymentTiming rdsPaymentTiming = RdsPaymentTiming.create("Prepaid");
        RdsReservation reservation = new RdsReservation();
        reservation.setReservationLength(1);
        billing.setPaymentTiming(rdsPaymentTiming);
        billing.setReservation(reservation);
        request.setBilling(billing);

        RdsRenewTimeUnit renewTimeUnit = RdsRenewTimeUnit.create("year");
        request.setAutoRenewTimeUnit(renewTimeUnit);
        request.setAutoRenewTime(1);

        request.setPurchaseCount(1);
        request.setInstanceName("rds-010");

        request.setEngine(RdsEngine.MySQL);
        request.setEngineVersion("5.6");
        request.setCharacterSetName("utf8mb4");
        request.setCpuCount(1);
        request.setMemoryCapacity(2);
        request.setVolumeCapacity(5);
        request.setDiskIoType("normal_io");

        List<String> zoneNames = new ArrayList<>();
        zoneNames.add("cn-bj-d");
        request.setZoneNames(zoneNames);

        request.setVpcId("vpc-70pxg3pmv8rv");
        request.setIsDirectPay(true);
        RdsSubnet subnet = new RdsSubnet();
        subnet.setSubnetId("sbn-dqafncqsy3y4");
        subnet.setZoneName("cn-bj-d");

        List<RdsSubnet> subnets = new ArrayList<>();
        subnets.add(subnet);

        request.setSubnets(subnets);

        RdsTag tag = new RdsTag();
        tag.setTagKey("goods_type");
        tag.setTagValue("music");

        List<RdsTag> tags = new ArrayList<>();
        tags.add(tag);
        request.setTags(tags);

        print("request is ", request);
        RdsCreateInstanceResponse instanceResponse = rdsClient.createInstance(request);
        print("createInstance", instanceResponse);
    }
}
