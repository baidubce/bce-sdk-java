package com.baidubce.services.blb;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.services.blb.model.AdditionalAttributes;
import com.baidubce.services.blb.model.BackendServer;
import com.baidubce.services.blb.model.BlbDetailRequest;
import com.baidubce.services.blb.model.BlbInstance;
import com.baidubce.services.blb.model.DeleteListenerRequest;
import com.baidubce.services.blb.model.EsgOperateRequest;
import com.baidubce.services.blb.model.ListBlbEsgResponse;
import com.baidubce.services.blb.model.ListBlbSgRequest;
import com.baidubce.services.blb.model.ListBlbSgResponse;
import com.baidubce.services.blb.model.ModifyBlbAttributesRequest;
import com.baidubce.services.blb.model.PortType;
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
    private static final String ak = "";
    private static final String sk = "";
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
    public void modifyBlbAttributesWithAllowDeleteTest() {
        ModifyBlbAttributesRequest request = new ModifyBlbAttributesRequest();
        request.setBlbId("lb-27228610");
        request.setName("blb-with-allow-delete");
        request.setAllowDelete(true);
        blbClient.modifyBlbAttributes(request);
        toJsonPrettyString("modifyBlbAttributes with allowDelete=true", "Success");
    }

    @Test
    public void modifyBlbAttributesWithAllocateIpv6Test() {
        ModifyBlbAttributesRequest request = new ModifyBlbAttributesRequest();
        request.setBlbId("lb-27228610");
        request.setName("blb-with-ipv6");
        request.setAllocateIpv6(true);
        blbClient.modifyBlbAttributes(request);
        toJsonPrettyString("modifyBlbAttributes with allocateIpv6=true", "Success");
    }

    @Test
    public void modifyBlbAttributesWithBuilderTest() {
        ModifyBlbAttributesRequest request = new ModifyBlbAttributesRequest()
                .withBlbId("lb-27228610")
                .withName("blb-full-update")
                .withDesc("Updated with builder pattern")
                .withAllowDelete(false)
                .withAllocateIpv6(true);
        blbClient.modifyBlbAttributes(request);
        toJsonPrettyString("modifyBlbAttributes with all parameters", "Success");
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
        request.setTcpSessionTimeout(900); // tcp session timeout
        request.setHealthCheckType("TCP"); // health check type
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
        request.setHealthCheckType("UDP"); // health check type
        request.setHealthCheckPort(82); // health check port
        request.setUdpSessionTimeout(90); // udp session timeout
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
        request.setxForwardFor(true);
        request.setxForwardedProto(true); // forward protocol to backend
        request.setHealthCheckHost("localhost"); // health check host
        // additional attributes
        AdditionalAttributes additionalAttributes = new AdditionalAttributes();
        additionalAttributes.setGzipJson("on");
        request.setAdditionalAttributes(additionalAttributes);
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
        request.setCertIds(Arrays.asList("cert-xxx"));
        request.setxForwardFor(true);
        request.setxForwardedProto(true); // forward protocol to backend
        request.setHealthCheckHost("localhost"); // health check host
        request.setEncryptionType("tls_cipher_policy_default"); // encryption type
        request.setDualAuth(false); // dual auth
        // additional attributes
        AdditionalAttributes additionalAttributes = new AdditionalAttributes();
        additionalAttributes.setGzipJson("on");
        request.setAdditionalAttributes(additionalAttributes);
        blbClient.createListener(request);
    }

    @Test
    public void createSSLListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("SSL");
        request.setBlbId("lb-27228610");
        request.setListenerPort(23);
        request.setBackendPort(23);
        request.setScheduler("RoundRobin");
        request.setCertIds(Arrays.asList("cert-xxx"));
        request.setHealthCheckType("TCP"); // health check type
        request.setServerTimeout(900); // server timeout
        request.setEncryptionType("tls_cipher_policy_default"); // encryption type
        request.setDualAuth(false); // dual auth
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
        request.setTcpSessionTimeout(900); // tcp session timeout
        request.setHealthCheckType("TCP"); // health check type
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
        request.setHealthCheckString("health check");
        request.setHealthCheckType("UDP"); // health check type
        request.setHealthCheckPort(8080); // health check port
        request.setUdpSessionTimeout(90); // udp session timeout
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyHTTPListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTP");
        request.setBlbId("lb-27228610");
        request.setListenerPort(90);
        request.setScheduler("LeastConnection");
        request.setxForwardFor(true);
        request.setxForwardedProto(true); // forward protocol to backend
        request.setHealthCheckHost("localhost"); // health check host
        // additional attributes
        AdditionalAttributes additionalAttributes = new AdditionalAttributes();
        additionalAttributes.setGzipJson("off");
        request.setAdditionalAttributes(additionalAttributes);
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifyHTTPSListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("HTTPS");
        request.setBlbId("lb-27228610");
        request.setListenerPort(80);
        request.setScheduler("RoundRobin");
        request.setxForwardFor(true);
        request.setxForwardedProto(true); // forward protocol to backend
        request.setHealthCheckHost("localhost"); // health check host
        request.setEncryptionType("tls_cipher_policy_1_2"); // encryption type
        // additional attributes
        AdditionalAttributes additionalAttributes = new AdditionalAttributes();
        additionalAttributes.setGzipJson("on");
        request.setAdditionalAttributes(additionalAttributes);
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void modifySSLListenerTest() {
        BlbListenerRequest request = new BlbListenerRequest();
        request.setType("SSL");
        request.setBlbId("lb-27228610");
        request.setListenerPort(23);
        request.setScheduler("RoundRobin");
        request.setHealthCheckType("TCP"); // health check type
        request.setServerTimeout(900); // server timeout
        request.setEncryptionType("tls_cipher_policy_1_2"); // encryption type
        blbClient.modifyListenerAttributes(request);
    }

    @Test
    public void deleteListenerTest() {
        blbClient.deleteListener("lb-27228610", Arrays.asList(81, 82));
    }

    @Test
    public void deleteListenerWithPortTypeTest() {
        // delete listener by port and type
        DeleteListenerRequest request = new DeleteListenerRequest();
        request.setBlbId("lb-27228610");
        request.setPortTypeList(Arrays.asList(
                new PortType(80, "TCP"),
                new PortType(80, "UDP")
        ));
        blbClient.deleteListener(request);
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

    @Test
    public void addBackendServersTest() {
        String blbId = "lb-081b7605";
        BackendServer backendServer = new BackendServer();
        backendServer.setInstanceId("i-VfM3kz2");
        backendServer.setWeight(50);
        try {
            blbClient.addBackendServers(blbId, Arrays.asList(backendServer));
        }catch (BceClientException e) {

        }

    }

    @Test
    public void listBackendServerStatusTest() {
        try {
            toJsonPrettyString("list backend server status result:",
                    blbClient.listBackendServerStatus("lb-0c6c8910", 90));
        }catch (BceClientException e) {

        }
    }

    @Test
    public void listBackendServersTest() {
        try {
            toJsonPrettyString("list backend servers result:",
                    blbClient.listBackendServers("lb-0c6c8910"));
        }catch (BceClientException e) {

        }
    }

    @Test
    public void modifyBackendServerAttributesTest() {
        String blbId = "lb-0c6c8910";
        BackendServer backendServer = new BackendServer();
        backendServer.setInstanceId("i-VfM3kz2D");
        backendServer.setWeight(60);
        try {
            blbClient.modifyBackendServerAttributes(blbId, Arrays.asList(backendServer));
        }catch (BceClientException e) {

        }

    }

    @Test
    public void deleteBackendServersTest() {
        try {
            blbClient.deleteBackendServers("lb-0c6c8910", Arrays.asList("i-VfM3kz2D"));
        }catch (BceClientException e) {

        }
    }


}
