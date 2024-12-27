package com.baidubce.services.vpc;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.subnet.SubnetClientTest;
import com.baidubce.services.tag.model.Tag;
import com.baidubce.services.vpc.model.CreateVpcRequest;
import com.baidubce.services.vpc.model.GetVpcPrivateIpAddressInfoRequest;
import com.baidubce.services.vpc.model.GetVpcResourceIpRequest;
import com.baidubce.services.vpc.model.ModifyVpcAttributesRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;

/**
 * Created by zhangjing60 on 17/8/10.
 */
public class VpcClientTest {
    private static final Logger logger = LoggerFactory.getLogger(SubnetClientTest.class);
    private static final String ak = "";
    private static final String sk = "";

    protected VpcClient vpcClient;

    @Before
    public void setUp() {
        VpcClientConfiguration config = new VpcClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        vpcClient = new VpcClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createVpcTest() {
        toJsonPrettyString("create vpc test", vpcClient.createVpc("vpc_test_2", "192.168.0.0/16"));
    }

    @Test
    public void listVpcsTest() {
        toJsonPrettyString("list vpc", vpcClient.listVpcs());
    }

    @Test
    public void getVpcTest() {
        toJsonPrettyString("get specific vpc", vpcClient.getVpc("vpc-12in0aj6nywq"));
    }

    @Test
    public void deleteVpcTest() {
        vpcClient.deleteVpc("vpc-12in0aj6nywq");
    }

    @Test
    public void modifyInstanceAttributesTest() {
        vpcClient.modifyInstanceAttributes("test_update_2", "vpc-e8ff5i875svs");
    }

    @Test
    public void createVpcTest1() {
        CreateVpcRequest createVpcRequest = new CreateVpcRequest();
        createVpcRequest.setEnableIpv6(true);
        createVpcRequest.setName("testVpcSdk");
        createVpcRequest.setCidr("192.168.0.0/16");

        Tag tag = new Tag();
        tag.setTagKey("tagKey");
        tag.setTagValue("tagValue");
        createVpcRequest.setTags(Lists.newArrayList(tag));

        toJsonPrettyString("create vpc test", vpcClient.createVpc(createVpcRequest));
    }

    @Test
    public void testUpdateVpc() {
        ModifyVpcAttributesRequest modifyVpcAttributesRequest = new ModifyVpcAttributesRequest();
        modifyVpcAttributesRequest.setEnableIpv6(false);
        modifyVpcAttributesRequest.setVpcId("vpc-8d97jq9a9zpf");
        modifyVpcAttributesRequest.setName("new_name");
        vpcClient.modifyInstanceAttributes(modifyVpcAttributesRequest);
    }

    @Test
    public void testGetVpcPrivateIpAddressInfo() {
        GetVpcPrivateIpAddressInfoRequest request = new GetVpcPrivateIpAddressInfoRequest();
        request.setVpcId("vpc-cxvqgxipk36r");
        request.setPrivateIpAddresses(Arrays.asList("192.168.0.4", "192.168.0.57"));
        request.setPrivateIpRange("192.168.0.57-192.168.0.60");
        toJsonPrettyString("get vpc private ip address info test", vpcClient.getVpcPrivateIpAddressInfo(request));
    }

    @Test
    public void testOpenVpcRelay() {
        vpcClient.openVpcRelay("vpc-12in0aj6nywq");
    }

    @Test
    public void testShutDownVpcRelay() {
        vpcClient.shutDownVpcRelay("vpc-12in0aj6nywq");
    }

    @Test
    public void testGetVpcResourceIpInfo() {
        GetVpcResourceIpRequest request = new GetVpcResourceIpRequest();
        request.setVpcId("vpc-12in0aj6nywq");
        request.setResourceType("enic");
        request.setSubnetId("sbn-kr0sm4rk5888");
        request.setPageNo(1);
        request.setPageSize(100);
        toJsonPrettyString("get vpc resource ip address info test", vpcClient.getVpcResourceIpInfo(request));
    }
}
