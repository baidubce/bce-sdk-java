package com.baidubce.services.blb;

import java.util.Arrays;

import com.baidubce.services.blb.model.BlbDetailRequest;
import com.baidubce.services.blb.model.BlbInstance;
import com.baidubce.services.blb.model.EsgOperateRequest;
import com.baidubce.services.blb.model.ListAllListenerRequest;
import com.baidubce.services.blb.model.ListBlbEsgResponse;
import com.baidubce.services.blb.model.ListBlbSgRequest;
import com.baidubce.services.blb.model.ListBlbSgResponse;
import com.baidubce.services.blb.model.ListListenerResponse;
import com.baidubce.services.blb.model.SgOperateRequest;
import com.baidubce.services.blb.model.UpdateLoadBalancerAclRequest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.model.BlbListenerRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * blbClient test
 *
 * @author duxiangyu
 * @since 2018/9/12
 */
public class BlbClientTest {

    private static final Logger logger = LoggerFactory.getLogger(BlbClientTest.class);
    private static final String ak = "3251d60a16f94c839f7aa4b87ed4913b";
    private static final String sk = "21d8c3cdfe1242148e29465fca734e1f";
    private BlbClient blbClient;

    @Before
    public void setUp() {
        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("blb.bj.qasandbox.baidu-int.com");
        blbClient = new BlbClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createBlbTest() {
        toJsonPrettyString("create blb Results",
                blbClient.createBlb("blb-test", "blb-desc", "vpc-rtdt6f5bkfp4", "sbn-fk8m7a9hfisj"));
    }

    @Test
    public void listBlbsTest() {
        toJsonPrettyString("list blbs result", blbClient.listBlbs("", "", "", "i-93Df3HzY"));
    }

    @Test
    public void lblbDetailTest() {
        BlbDetailRequest request = new BlbDetailRequest();
        request.setBlbId("lb-0c6c8910");
        BlbInstance blbInstance = blbClient.blbDetail(request);
        System.out.println(blbInstance);
    }

    @Test
    public void modifyBlbAttributesTest() {
        blbClient.modifyBlbAttributes("lb-27228610", "blb-modify-name", "blb-modify-desc");
    }

    @Test
    public void deleteBlbTest() {
        blbClient.deleteBlb("lb-8583ad27");
    }

    @Test
    public void updateLoadBalancerAclTest() {
        UpdateLoadBalancerAclRequest request = new UpdateLoadBalancerAclRequest();
        request.setBlbId("lb-0c6c8910");
        blbClient.updateLoadBalancerAcl(request);
    }

    @Test
    public void createTCPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setBlbId("lb-27228610");
        request.setListenerPort(81);
        request.setBackendPort(81);
        request.setScheduler("Hash");
        request.setType("TCP");
        blbClient.createListener(request);
    }

    @Test
    public void createUDPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("UDP");
        request.setBlbId("lb-27228610");
        request.setListenerPort(82);
        request.setBackendPort(82);
        request.setScheduler("Hash");
        request.setHealthCheckString("\\00");
        blbClient.createListener(request);
    }

    @Test
    public void createHTTPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTP");
        request.setBlbId("lb-27228610");
        request.setListenerPort(90);
        request.setBackendPort(90);
        request.setScheduler("RoundRobin");
        blbClient.createListener(request);
    }

    @Test
    public void createHTTPSListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTPS");
        request.setBlbId("lb-27228610");
        request.setListenerPort(22);
        request.setBackendPort(22);
        request.setScheduler("RoundRobin");
        request.setCertIds(Arrays.asList("xxx"));
        blbClient.createListener(request);
    }

    @Test
    public void listTCPListenersTest() {
        toJsonPrettyString("list TCP listener results:", blbClient.listTcpListener("lb-27228610"));
    }

    @Test
    public void listUDPListenersTest() {
        toJsonPrettyString("list UDP listener results:", blbClient.listUdpListener("lb-27228610"));
    }

    @Test
    public void listHTTPListenersTest() {
        toJsonPrettyString("list HTTP listener results:", blbClient.listHttpListener("lb-27228610"));
    }

    @Test
    public void listHTTPSListenersTest() {
        toJsonPrettyString("list HTTPS listener results:", blbClient.listHttpsListener("lb-1ef11a87"));
    }

    @Test
    public void listSSLListenersTest() {
        toJsonPrettyString("list SSL listener results:", blbClient.listSslListener("lb-1ef11a87"));
    }

    @Test
    public void listAllListenersTest() {
        toJsonPrettyString("list all listener results:", blbClient.listAllListener("lb-1ef11a87"));
    }

    @Test
    public void modifyTCPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("TCP");
        request.setBlbId("lb-27228610");
        request.setListenerPort(80);
        request.setScheduler("RoundRobin");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyUDPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("UDP");
        request.setBlbId("lb-27228610");
        request.setListenerPort(8080);
        request.setBackendPort(8080);
        request.setScheduler("LeastConnection");
        request.setHealthCheckString("haedlth check");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyHTTPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTP");
        request.setBlbId("lb-27228610");
        request.setListenerPort(90);
        request.setScheduler("LeastConnection");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyHTTPSListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTPS");
        request.setBlbId("lb-27228610");
        request.setListenerPort(80);
        request.setScheduler("RoundRobin");
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void deleteListenerTest() {
        blbClient.deleteListener("lb-27228610", Arrays.asList(81, 82));
    }

    @Test
    public void bindSgTest() {
        SgOperateRequest request = new SgOperateRequest();
        request.setBlbId("lb-0c6c8910");
        request.setSecurityGroupIds(Arrays.asList("g-t8smau5jet7b"));
        blbClient.bindSg(request);
    }


    @Test
    public void unBindSgTest() {
        SgOperateRequest request = new SgOperateRequest();
        request.setBlbId("lb-0c6c8910");
        request.setSecurityGroupIds(Arrays.asList("g-t8smau5jet7b"));
        blbClient.unBindSg(request);
    }

    @Test
    public void listBlbSgTest() {
        ListBlbSgRequest request = new ListBlbSgRequest();
        request.setBlbId("lb-0c6c8910");
        ListBlbSgResponse response = blbClient.listBlbSg(request);
        System.out.println(response);
    }

    @Test
    public void bindEsgTest() {
        EsgOperateRequest request = new EsgOperateRequest();
        request.setBlbId("lb-0c6c8910");
        request.setEnterpriseSecurityGroupIds(Arrays.asList("esg-fiuy134625d9"));
        blbClient.bindEsg(request);
    }

    @Test
    public void unBindEsgTest() {
        EsgOperateRequest request = new EsgOperateRequest();
        request.setBlbId("lb-0c6c8910");
        request.setEnterpriseSecurityGroupIds(Arrays.asList("esg-fiuy134625d9"));
        blbClient.unBindEsg(request);
    }

    @Test
    public void listBlbEsgTest() {
        ListBlbSgRequest request = new ListBlbSgRequest();
        request.setBlbId("lb-0c6c8910");
        ListBlbEsgResponse response = blbClient.listBlbEsg(request);
        System.out.println(response);
    }

    @Test
    public void createIpv6BlbTest() {
        toJsonPrettyString("create ipv6 blb Results", blbClient.createIpv6Blb("ipv6blb-test",
                "ipv6blb-desc", "vpc-gg92ixpgmpw7", "sbn-ej7r34w74p69"));
    }

    @Test
    public void listIpv6BlbsTest() {
        toJsonPrettyString("list ipv6 blbs result",
                blbClient.listIpv6Blbs("", "", "", ""));
    }


}
