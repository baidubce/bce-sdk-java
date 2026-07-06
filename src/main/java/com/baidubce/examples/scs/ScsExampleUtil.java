/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsBilling;
import com.baidubce.services.scs.model.ScsMarkerRequest;
import com.baidubce.services.scs.model.ScsOrderRequest;
import com.baidubce.services.scs.model.ScsReservation;
import com.baidubce.services.scs.model.ScsSubnet;
import com.baidubce.services.scs.model.ScsTag;
import com.baidubce.services.scs.model.account.ScsAclUserRequest;
import com.baidubce.services.scs.model.backup.ScsBackupPolicy;
import com.baidubce.services.scs.model.instance.ScsPriceRequest;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupRequest;
import com.baidubce.services.scs.model.whitelist.ScsWhiteListGroupRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
class ScsExampleUtil {

    static final String AK = "";
    static final String SK = "";
    static final String ENDPOINT = "redis.bj.baidubce.com";
    static final String INSTANCE_ID = "";
    static final String INSTANCE_ID_2 = "";
    static final String INSTANCE_ID_3 = "";
    static final String VPC_ID = "";
    static final String SUBNET_ID = "";
    static final String ZONE_NAME = "cn-bj-d";
    static final String SECURITY_GROUP_ID = "";
    static final String GROUP_ID = "";
    static final String TEMPLATE_ID = "";
    static final String SYNC_GROUP_ID = "";
    static final String BACKUP_RECORD_ID = "";
    static final String LOG_ID = "";
    static final String CLIENT_TOKEN = "";
    static final String CLIENT_AUTH = "";

    static ScsClient createClient() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint(ENDPOINT);
        return new ScsClient(config);
    }

    static ScsBilling examplePostpaidBilling() {
        ScsBilling billing = new ScsBilling();
        billing.setPaymentTiming("Postpaid");
        return billing;
    }

    static ScsBilling examplePrepaidBilling() {
        ScsReservation reservation = new ScsReservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        ScsBilling billing = new ScsBilling();
        billing.setPaymentTiming("Prepaid");
        billing.setReservation(reservation);
        return billing;
    }

    static ScsSubnet exampleSubnet() {
        ScsSubnet subnet = new ScsSubnet();
        subnet.setSubnetId(SUBNET_ID);
        subnet.setZoneName(ZONE_NAME);
        return subnet;
    }

    static ScsTag exampleTag() {
        ScsTag tag = new ScsTag();
        tag.setTagKey("java-sdk-example");
        tag.setTagValue("test-2");
        return tag;
    }

    static ScsPriceRequest examplePriceRequest() {
        ScsPriceRequest request = new ScsPriceRequest();
        request.setChargeType("prepay");
        request.setNodeType("cache.n1.micro");
        request.setClusterType("cluster");
        request.setEngine(2);
        request.setShardNum(1);
        request.setReplicationNum(1);
        request.setInstanceNum(1);
        return request;
    }

    static ScsWhiteListGroupRequest exampleWhiteListGroupRequest() {
        ScsWhiteListGroupRequest request = new ScsWhiteListGroupRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setGroupName("example-group");
        request.setClusterIpList(Collections.singletonList("127.0.0.1"));
        return request;
    }

    static ScsBackupPolicy exampleBackupPolicy() {
        ScsBackupPolicy policy = new ScsBackupPolicy();
        policy.setBackupTime("03:00:00");
        policy.setBackupDays("Mon,Tue,Wed,Thu,Fri,Sta,Sun");
        policy.setExpireDay(7);
        return policy;
    }

    static ScsAclUserRequest exampleAclUserRequest() {
        ScsAclUserRequest request = new ScsAclUserRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setUserName("example_user");
        request.setUserType(2);
        return request;
    }

    static ScsOrderRequest exampleOrderRequest() {
        ScsOrderRequest request = new ScsOrderRequest();
        request.setInstanceIds(Collections.singletonList(INSTANCE_ID));
        request.setDuration(1);
        return request;
    }

    static ScsSecurityGroupRequest exampleSecurityGroupRequest() {
        ScsSecurityGroupRequest request = new ScsSecurityGroupRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setSecurityGroupIds(Collections.singletonList(SECURITY_GROUP_ID));
        return request;
    }

    static ScsMarkerRequest exampleMarkerRequest() {
        ScsMarkerRequest request = new ScsMarkerRequest();
        request.setMarker("-1");
        request.setMaxKeys(10);
        return request;
    }

    static Map<String, Object> exampleClusterMap(String instanceId) {
        Map<String, Object> cluster = new HashMap<String, Object>();
        cluster.put("clusterShowId", instanceId);
        cluster.put("region", "bj");
        return cluster;
    }

    static Map<String, Object> exampleParameterMap() {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("name", "maxmemory-policy");
        parameter.put("value", "allkeys-lru");
        return parameter;
    }
}
