package com.baidubce.services.ipv6Gateway;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.model.CreateEgressOnlyRuleRequest;
import com.baidubce.services.ipv6Gateway.model.CreateRateLimitRuleRequest;
import com.baidubce.services.ipv6Gateway.model.UpdateRateLimitRuleRequest;
import com.baidubce.services.subnet.SubnetClientTest;
import com.baidubce.services.vpc.VpcClientConfiguration;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Ipv6GatewayClientTest {
    private static final Logger logger = LoggerFactory.getLogger(SubnetClientTest.class);
    private static final String ak = "Your Ak";
    private static final String sk = "Your Sk";

    protected Ipv6GatewayClient ipv6GatewayClient;

    @Before
    public void setUp() {
        VpcClientConfiguration config = new VpcClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bce-api.baidu.com");
        ipv6GatewayClient = new Ipv6GatewayClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createIpv6GatewayTest() {
        ipv6GatewayClient.createIpv6Gateway("vpc-q35pxi0bx6q4",
                "testIpv6", 20);
    }

    @Test
    public void listIpv6GatewayTest() {
        toJsonPrettyString("list gateway", ipv6GatewayClient.getIpv6Gateway("vpc-q35pxi0bx6q4"));
    }

    @Test
    public void deleteIpv6Gateway() {
        ipv6GatewayClient.deleteIpv6Gateway("gw-ba92ac44");
    }

    @Test
    public void resizeIpv6GatewayTest() {
        ipv6GatewayClient.resizeIpv6Gateway("gw-ba92ac44", 50);
    }

    @Test
    public void listIpv6GatewayEgressOnlyRule() {
        toJsonPrettyString("list egressOnlyRule", ipv6GatewayClient.listEgressOnlyRule("gw-ba92ac44"));
    }

    @Test
    public void createIpv6GatewayEgressOnlyRule() {
        CreateEgressOnlyRuleRequest request = new CreateEgressOnlyRuleRequest();
        request.setGatewayId("gw-ba92ac44");
        request.setCidr("2400:da00:e003:400::2/128");
        toJsonPrettyString("crate egress", ipv6GatewayClient.createEgressOnlyRule(request));
    }

    @Test
    public void deleteIpv6GatewayEgressOnlyRule() {
        ipv6GatewayClient.deleteIpv6GatewayEgressOnlyRule("gw-ba92ac44", "ipv6_seg-34aa2171");
    }

    @Test
    public void listIpv6GatewayRateLimitRule() {
        toJsonPrettyString("list ..", ipv6GatewayClient.listRateLimitRule("gw-ba92ac44"));
    }

    @Test
    public void createIpv6RateLimitRuleTest() {
        CreateRateLimitRuleRequest request = new CreateRateLimitRuleRequest();
        request.setEgressBandwidthInMbps(10);
        request.setGatewayId("gw-ba92ac44");
        request.setIngressBandwidthInMbps(20);
        request.setIpv6Address("2400:da00:e003:400::1");
        toJsonPrettyString("add rateLimitRule", ipv6GatewayClient.createRateLimitRule(request));
    }

    @Test
    public void updateIpv6RateLimitRuleTest() {
        UpdateRateLimitRuleRequest updateRateLimitRule = new UpdateRateLimitRuleRequest();
        updateRateLimitRule.setGatewayId("gw-ba92ac44");
        updateRateLimitRule.setEgressBandwidthInMbps(8);
        //   updateRateLimitRule.setIngressBandwidthInMbps(6);
        updateRateLimitRule.setRateLimitRuleId("ipv6_qos-1a5de4b5");
        ipv6GatewayClient.updateRateLimitRule(updateRateLimitRule);
    }

    @Test
    public void deleteIpv6RateLimitRule() {
        ipv6GatewayClient.deleteIpv6GatewayRateLimitRule("gw-ba92ac44", "ipv6_qos-1a5de4b5");
    }
}
