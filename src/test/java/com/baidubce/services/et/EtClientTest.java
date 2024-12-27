package com.baidubce.services.et;

import java.util.UUID;

import com.baidubce.services.et.model.AssociateEtChannelRequest;
import com.baidubce.services.et.model.DisassociateEtChannelRequest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.model.ApplyForEtRequest;
import com.baidubce.services.et.model.ApplyForEtResponse;
import com.baidubce.services.et.model.CreateEtChannelRequest;
import com.baidubce.services.et.model.CreateEtChannelResponse;
import com.baidubce.services.et.model.CreateEtChannelRouteRuleRequest;
import com.baidubce.services.et.model.CreateEtChannelRouteResponse;
import com.baidubce.services.et.model.EnableEtChannelIpv6Request;
import com.baidubce.services.et.model.Et;
import com.baidubce.services.et.model.EtChannelIdRequest;
import com.baidubce.services.et.model.EtChannelRouteRuleIdRequest;
import com.baidubce.services.et.model.ListEtChannelRouteRulesRequest;
import com.baidubce.services.et.model.ListEtChannelRouteRulesResponse;
import com.baidubce.services.et.model.ListEtChannelsResponse;
import com.baidubce.services.et.model.ListEtResponse;
import com.baidubce.services.et.model.ListEtRequest;
import com.baidubce.services.et.model.ResubmitEtChannelRequest;
import com.baidubce.services.et.model.UpdateEtChannelRequest;
import com.baidubce.services.et.model.UpdateEtChannelRouteRuleRequest;
import com.baidubce.services.et.model.UpdateEtRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;

/**
 * ET client UT.
 */
public class EtClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtClientTest.class);
    private static final String AK = "Your Ak";
    private static final String SK = "Your Sk";
    private EtClient client;

    @Before
    public void setUp() {
        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("bcc.bj.baidubce.com");
        // config.setProtocol(Protocol.HTTPS);
        client = new EtClient(config);
    }

    private void toJsonPrettyString(String method, Object object) {
        try {
            LOGGER.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void applyForEtTest() {
        ApplyForEtRequest request = new ApplyForEtRequest();
        request.setName("NewJavaSdkTestET");
        request.setDescription("New Java sdk test ET");
        request.setIsp("ISP_CMCC");
        request.setIntfType("1G");
        request.setApType("BAIDU");
        request.setApAddr("BB");
        request.setUserIdc("北京|市辖区|东城区|东单");
        request.setClientToken(UUID.randomUUID().toString());

        ApplyForEtResponse response = client.applyForEt(request);
        toJsonPrettyString("apply for ET", response);
    }

    @Test
    public void updateEtTest() {
        UpdateEtRequest request = new UpdateEtRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-t6ewxjaekkt2");
        request.setName("JavaSdkTestETUpdate");
        request.setDescription("Java sdk test ET update");

        client.updateEt(request);
    }

    @Test
    public void listEtsTest() {
        ListEtRequest request = new ListEtRequest();
        request.setStatus("ack-wait");
        ListEtResponse response = client.listEts(request);
        toJsonPrettyString("list ETs", response);
    }

    @Test
    public void getEtDetailTest() {
        Et et = client.getEtDetail("dcphy-t6ewxjaekkt2");
        toJsonPrettyString("get ET detail", et);
    }

    @Test
    public void createEtChannelTest() {
        CreateEtChannelRequest request = new CreateEtChannelRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-6vtqgw1fk3mj");
        request.setAuthorizedUsers(Lists.newArrayList("1beb4ad4762746db96941a5ad253ac8c"));
        request.setDescription("Java sdk test ET channel");
        request.setBaiduAddress("192.168.0.2/24");
        request.setName("JavaSdkTestEtChannel");
        request.setCustomerAddress("192.168.0.3/24");
        request.setRouteType("static-route");
        request.setVlanId(2);

        CreateEtChannelResponse response = client.createEtChannel(request);
        toJsonPrettyString("create ET channel", response);
    }

    @Test
    public void listEtChannelsTest() {
        ListEtChannelsResponse response = client.listEtChannels("dcphy-axibreesn6af");
        toJsonPrettyString("list ET channels", response);
    }

    @Test
    public void resubmitEtChannelTest() {
        ResubmitEtChannelRequest request = new ResubmitEtChannelRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-axibreesn6af");
        request.setEtChannelId("dedicatedconn-3nv9cus8geaf");
        request.setAuthorizedUsers(Lists.newArrayList("1beb4ad4762746db96941a5ad253ac8c"));
        request.setDescription("Java sdk test ET channel");
        request.setBaiduAddress("192.168.1.1/24");
        request.setName("JavaSdkTestEtChannel");
        request.setCustomerAddress("192.168.1.2/24");
        request.setRouteType("static-route");
        request.setVlanId(2);

        client.resubmitEtChannel(request);
    }

    @Test
    public void updateEtChannelTest() {
        UpdateEtChannelRequest request = new UpdateEtChannelRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-axibreesn6af");
        request.setEtChannelId("dedicatedconn-kd1ff138ypnm");
        request.setDescription("Java sdk test ET channel update");
        request.setName("JavaSdkTestEtChannelUpdate");

        client.updateEtChannel(request);
    }

    @Test
    public void deleteEtChannelTest() {
        EtChannelIdRequest request = new EtChannelIdRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-axibreesn6af");
        request.setEtChannelId("dedicatedconn-tkki3g71vr09");

        client.deleteEtChannel(request);
    }

    @Test
    public void enableEtChannelIpv6Test() {
        EnableEtChannelIpv6Request request = new EnableEtChannelIpv6Request();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-bjrtsp6ar5ug");
        request.setEtChannelId("dedicatedconn-xdzaphpgcqfn");
        request.setBaiduIpv6Address("2400:DA00:E003:0000:016A:0400:0000:100/127");
        request.setCustomerIpv6Address("2400:DA00:E003:0000:016A:0400:0000:101/127");

        client.enableEtChannelIpv6(request);
    }

    @Test
    public void disableEtChannelIpv6Test() {
        EtChannelIdRequest request = new EtChannelIdRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-bjrtsp6ar5ug");
        request.setEtChannelId("dedicatedconn-xdzaphpgcqfn");

        client.disableEtChannelIpv6(request);
    }

    @Test
    public void createEtChannelRouteRuleTest() {
        CreateEtChannelRouteRuleRequest request = new CreateEtChannelRouteRuleRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-axibreesn6af");
        request.setEtChannelId("dedicatedconn-nezh65s7z325");
        request.setDestAddress("192.168.0.9/32");
        request.setNexthopType("etChannel");
        request.setNexthopId("dedicatedconn-nezh65s7z325");
        request.setDescription("Java SDK test");

        CreateEtChannelRouteResponse response = client.createEtChannelRouteRule(request);
        toJsonPrettyString("create ET channel route", response);
    }

    @Test
    public void listEtChannelRouteRulesTest() {
        ListEtChannelRouteRulesRequest request = new ListEtChannelRouteRulesRequest();
        request.setEtId("dcphy-axibreesn6af");
        request.setEtChannelId("dedicatedconn-nezh65s7z325");
        request.setDestAddress("192.168.0.9/32");

        ListEtChannelRouteRulesResponse response = client.listEtChannelRouteRules(request);
        toJsonPrettyString("list ET channel route", response);
    }

    @Test
    public void updateEtChannelRouteRuleTest() {
        UpdateEtChannelRouteRuleRequest request = new UpdateEtChannelRouteRuleRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-axibreesn6af");
        request.setEtChannelId("dedicatedconn-nezh65s7z325");
        request.setRouteRuleId("dcrr-bf5545b8-de8");
        request.setDescription("Java SDK test update");

        client.updateEtChannelRouteRule(request);
    }

    @Test
    public void deleteEtRouteRuleTest() {
        EtChannelRouteRuleIdRequest request = new EtChannelRouteRuleIdRequest();
        request.setClientToken(UUID.randomUUID().toString());
        request.setEtId("dcphy-axibreesn6af");
        request.setEtChannelId("dedicatedconn-nezh65s7z325");
        request.setRouteRuleId("dcrr-bf5545b8-de8");

        client.deleteEtRouteRule(request);
    }

    @Test
    public void associateEtChannel(){
        AssociateEtChannelRequest request = new AssociateEtChannelRequest();
        request.setEtId("dcphy-zzdark9nuk1g");
        request.setEtChannelId("dedicatedconn-hzf4aigzqttd");
        request.setExtraChannelId("dedicatedconn-i05sdfw25kqz");

        client.associateEtChannel(request);
    }

    @Test
    public void disassociateEtChannel(){
        DisassociateEtChannelRequest request = new DisassociateEtChannelRequest();
        request.setEtId("dcphy-zzdark9nuk1g");
        request.setEtChannelId("dedicatedconn-hzf4aigzqttd");
        request.setExtraChannelId("dedicatedconn-i05sdfw25kqz");

        client.disassociateEtChannel(request);
    }
}
