package com.baidubce.services.subnet;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.subnet.model.CreateIpReservedReq;
import com.baidubce.services.subnet.model.CreateIpReservedResponse;
import com.baidubce.services.subnet.model.CreateSubnetRequest;
import com.baidubce.services.subnet.model.ListIpReserveRequest;
import com.baidubce.services.subnet.model.ListIpReserveResponse;
import com.baidubce.services.subnet.model.ModifySubnetAttributesRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by zhangjing60 on 17/8/10.
 */
public class SubnetClientTest {
    private static final Logger logger = LoggerFactory.getLogger(SubnetClientTest.class);
    private static final String ak = "Your Ak";
    private static final String sk = "Your Sk";

    protected SubnetClient subnetClient;

    @Before
    public void setUp() {
        SubnetClientConfiguration config = new SubnetClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        subnetClient = new SubnetClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listSubnetsTest() {
        toJsonPrettyString("list subnets Results", subnetClient.listSubnets());
    }

    @Test
    public void createSubnetTest() {
        toJsonPrettyString("create subnet result", subnetClient.createSubnet("test_subnet_2", "vpc-e8ff5i875svs",
                "192.168.0.0/20", "cn-bj-a"));
    }

    @Test
    public void getSubnetTest() {
        toJsonPrettyString("get subnet result", subnetClient.getSubnet("sbn-qn10rj5624ay"));
    }

    @Test
    public void getSubnetDetailTest() {
        toJsonPrettyString("get subnet result", subnetClient.getSubnetDetail("sbn-qn10rj5624ay"));
    }

    @Test
    public void modifySubnetAttributesTest() {
        subnetClient.modifySubnetAttributes("sbn-eg6ixcdpa3j2", "subnet_modify");
    }

    @Test
    public void deleteSubnetTest() {
        subnetClient.deleteSubnet("sbn-kvrxdch0rwaf");
    }

    @Test
    public void createSubnetTest1() {
        CreateSubnetRequest createSubnetRequest = new CreateSubnetRequest();
        createSubnetRequest.setEnableIpv6(true);
        createSubnetRequest.setName("testSubnet");
        createSubnetRequest.setCidr("192.168.0.0/20");
        createSubnetRequest.setZoneName("cn-bj-a");
        createSubnetRequest.setVpcId("vpc-8d97jq9a9zpf");
        subnetClient.createSubnet(createSubnetRequest);

    }

    @Test
    public void testUpdateSubnet() {
        ModifySubnetAttributesRequest modifySubnetAttributesRequest = new ModifySubnetAttributesRequest();
        modifySubnetAttributesRequest.setEnableIpv6(false);
        modifySubnetAttributesRequest.setSubnetId("sbn-mn0nc8fm88g2");
        modifySubnetAttributesRequest.setName("newName");
        subnetClient.modifySubnetAttributes(modifySubnetAttributesRequest);
    }

    @Test
    public void listIpReserve() {
        ListIpReserveResponse response = subnetClient.listIpReserve();
        toJsonPrettyString("list ipReserve", response);

        ListIpReserveRequest listIpReserveRequest = new ListIpReserveRequest();
        listIpReserveRequest.setSubnetId("sbn-6ha6gp1vczuv");
        ListIpReserveResponse listIpReserveResponse = subnetClient.listIpReserve(listIpReserveRequest);
        toJsonPrettyString("list ipReserve", listIpReserveResponse);
    }

    @Test
    public void createIpReserve() {
        CreateIpReservedReq createIpReservedReq = new CreateIpReservedReq();
        createIpReservedReq.setSubnetId("sbn-6ha6gp1vczuv");
        createIpReservedReq.setIpCidr("192.168.0.0/30");
        createIpReservedReq.setDescription("aaa");
        CreateIpReservedResponse createIpReservedResponse = subnetClient.createIpReserved(createIpReservedReq);
        toJsonPrettyString("create ipReserve", createIpReservedResponse);
    }

    @Test
    public void deleteIpReserveTest() {
        String ipReserve = "ipr-ce033uf99j3b";
        subnetClient.deleteIpReserve(ipReserve);
    }

}
