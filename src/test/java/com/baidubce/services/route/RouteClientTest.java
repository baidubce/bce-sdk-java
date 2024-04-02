package com.baidubce.services.route;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.route.model.ListRouteRuleReq;
import com.baidubce.services.route.model.ListRouteRuleResponse;
import com.baidubce.services.route.model.UpdateRouteRuleRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by zhangjing60 on 17/8/10.
 */
public class RouteClientTest {
    private static final Logger logger = LoggerFactory.getLogger(RouteClientTest.class);
    //    private static final String ak = "37a24f544b094d7185313d60a899b23e";
    //    private static final String sk = "8c7780a605634bec9337fdd3ba8e934e";
    private static final String ak = "Your Ak";
    private static final String sk = "Your Sk";
    protected RouteClient routeClient;

    @Before
    public void setUp() {
        RouteClientConfiguration config = new RouteClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        //        config.setEndpoint("bcc.bj.baidubce.com"); // 线上
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        routeClient = new RouteClient(config);
        // logger.debug("config",routeClient);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRouteTest() {

        toJsonPrettyString("getRouteResults", routeClient.getRoute("", "vpc-b9ycwxxisrb7"));
    }

    @Test
    public void deleteRouteTest() {
        routeClient.deleteRouteRule("rr-vvhz3aiv0gz2");
    }

    @Test
    public void createRouteRuleTest() {
    }

    @Test
    public void updateRouteRuleTest() {
        UpdateRouteRuleRequest updateRouteRuleRequest = new UpdateRouteRuleRequest();
        updateRouteRuleRequest.setRouteRuleId("rr-jcr3w2xryq2g");
        updateRouteRuleRequest.setDescription("aaa");
        routeClient.updateRouteRule(updateRouteRuleRequest);
    }

    @Test
    public void listRouteRule() {
        ListRouteRuleReq listRouteRuleReq = new ListRouteRuleReq();
        listRouteRuleReq.setVpcId("vpc-b9ycwxxisrb7");

        ListRouteRuleResponse listRouteRuleResponse = routeClient.listRouteRule(listRouteRuleReq);
        toJsonPrettyString("list route rule", listRouteRuleResponse);
    }

    @Test
    public void testSwitchRouteRule() {
        routeClient.switchRouteHa("rr-jcr3w2xryq2g");
    }
}
