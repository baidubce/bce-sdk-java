package com.baidubce.services.vpn;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.vpn.model.*;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huozhibin on 20/11/04.
 */
public class VpnClientTest {
    private static final Logger logger = LoggerFactory.getLogger(VpnClientTest.class);
    private static final String ak = "";
    private static final String sk = "";

    protected VpnClient vpnClient;

    @Before
    public void setUp() {
        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        vpnClient = new VpnClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listVpnsTest() {
        ListVpnRequest listVpnRequest = new ListVpnRequest();
        listVpnRequest.setVpcId("vpc-rif0euejenz7");
        toJsonPrettyString("list vpn", vpnClient.listVpns(listVpnRequest));
    }

    @Test
    public void createVpnTest() {
        CreateVpnRequest createVpnRequest = new CreateVpnRequest();
        createVpnRequest.setVpcId("vpc-m817ndhrgvd3");
        createVpnRequest.setVpnName("hzb_test_vpn_4");
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        createVpnRequest.setBilling(billing);
        toJsonPrettyString("create vpn", vpnClient.createVpn(createVpnRequest));
    }

    @Test
    public void updateVpnTest() {
        UpdateVpnRequest updateVpnRequest = new UpdateVpnRequest();
        updateVpnRequest.setVpnId("vpn-s28gd54ub5ce");
        updateVpnRequest.setDescription("描述");
        updateVpnRequest.setVpnName("hzb_test_vpn");
        toJsonPrettyString("update Vpn", vpnClient.updateVpn(updateVpnRequest));
    }

    @Test
    public void deleteVpnTest() {
        DeleteVpnRequest deleteVpnRequest = new DeleteVpnRequest();
        deleteVpnRequest.setVpnId("vpn-sgsj56yhheiz");
        toJsonPrettyString("delete vpn", vpnClient.deleteVpn(deleteVpnRequest));
    }

    @Test
    public void getVpnTest() {
        toJsonPrettyString("get vpn", vpnClient.getVpn("vpn-s28gd54ub5ce"));
    }

    @Test
    public void bindEipTest() {
        BindEipRequest bindEipRequest = new BindEipRequest();
        bindEipRequest.setVpnId("vpn-bzehyume6vzh");
        bindEipRequest.setEip("180.76.121.26");
        toJsonPrettyString("bind eip", vpnClient.bindEip(bindEipRequest));
    }

    @Test
    public void unBindEipTest() {
        UnBindEipRequest unBindEipRequest = new UnBindEipRequest();
        unBindEipRequest.setVpnId("vpn-bzehyume6vzh");
        toJsonPrettyString("unBind eip", vpnClient.unBindEip(unBindEipRequest));
    }

    @Test
    public void renewVpn() {
        RenewVpnRequest renewVpnRequest = new RenewVpnRequest();
        renewVpnRequest.setVpnId("vpn-snbciyy2jpuv");
        Billing billing = new Billing();
        billing.setPaymentTiming("Prepaid");
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        billing.setReservation(reservation);
        renewVpnRequest.setBilling(billing);
        toJsonPrettyString("renew vpn", vpnClient.renewVpn(renewVpnRequest));
    }

    @Test
    public void listVpcConn() {
        ListVpnConnRequest listVpnConnRequest = new ListVpnConnRequest();
        listVpnConnRequest.setVpnId("vpn-s28gd54ub5ce");
        toJsonPrettyString("list vpnConn", vpnClient.listVpnConns(listVpnConnRequest));
    }

    @Test
    public void createVpnConn() {
        CreateVpnConnRequest createVpnConnRequest = new CreateVpnConnRequest();
        createVpnConnRequest.setVpnId("vpn-s28gd54ub5ce");
        createVpnConnRequest.setSecretKey("hzb123456!");
        List<String> localSubnets = new ArrayList<String>();
        localSubnets.add("192.168.0.0/20");
        createVpnConnRequest.setLocalSubnets(localSubnets);
        createVpnConnRequest.setRemoteIp("180.76.121.26");
        List<String> remoteSubnets = new ArrayList<String>();
        remoteSubnets.add("192.168.100.0/24");
        createVpnConnRequest.setRemoteSubnets(remoteSubnets);
        createVpnConnRequest.setVpnConnName("hzb_test_conn_1");
        IkeConfig ikeConfig = new IkeConfig();
        createVpnConnRequest.setIkeConfig(ikeConfig);
        IpsecConfig ipsecConfig = new IpsecConfig();
        createVpnConnRequest.setIpsecConfig(ipsecConfig);
        toJsonPrettyString("create vpnConn", vpnClient.createVpnConn(createVpnConnRequest));
    }

    @Test
    public void updateVpnConn() {
        UpdateVpnConnRequest updateVpnConnRequest = new UpdateVpnConnRequest();
        updateVpnConnRequest.setVpnConnId("vpnconn-5bgbjdhjbv99");
        updateVpnConnRequest.setVpnId("vpn-s28gd54ub5ce");
        updateVpnConnRequest.setSecretKey("hzb123456!");
        List<String> localSubnets = new ArrayList<String>();
        localSubnets.add("192.168.0.0/20");
        updateVpnConnRequest.setLocalSubnets(localSubnets);
        updateVpnConnRequest.setRemoteIp("180.76.121.26");
        List<String> remoteSubnets = new ArrayList<String>();
        remoteSubnets.add("192.168.100.0/24");
        updateVpnConnRequest.setRemoteSubnets(remoteSubnets);
        updateVpnConnRequest.setVpnConnName("hzb_test_conn_h");
        IkeConfig ikeConfig = new IkeConfig();
        updateVpnConnRequest.setIkeConfig(ikeConfig);
        IpsecConfig ipsecConfig = new IpsecConfig();
        updateVpnConnRequest.setIpsecConfig(ipsecConfig);
        toJsonPrettyString("update vpnConn", vpnClient.updateVpnConn(updateVpnConnRequest));
    }

    @Test
    public void deleteVpnConn() {
        DeleteVpnConnRequest deleteVpnConnRequest = new DeleteVpnConnRequest();
        deleteVpnConnRequest.setVpnConnId("vpnconn-6rjp04dvs1hg");
        toJsonPrettyString("delete vpnConn", vpnClient.deleteVpnConn(deleteVpnConnRequest));
    }

    // 创建SSL-VPN服务端
    @Test
    public void createSslVpnServer() {
        CreateSslVpnServerRequest request = new CreateSslVpnServerRequest();
        request.setVpnId("vpn-id");
        request.setSslVpnServerName("testServer");
        request.setLocalSubnets(Arrays.asList("172.16.0.0/24"));
        request.setRemoteSubnet("192.168.100.0/24");
        request.setClientDns("100.88.0.83");
        CreateSslVpnServerResponse sslVpnServer = vpnClient.createSslVpnServer(request);
        toJsonPrettyString("create sslVpnUser", sslVpnServer);
    }

    @Test
    public void updateSslVpnServer() {
        UpdateSslVpnServerRequest request = new UpdateSslVpnServerRequest();
        request.setVpnId("vpn-id");
        request.setSslVpnServerId("sslvpn-id");
        request.setSslVpnServerName("testServer-update");
        request.setLocalSubnets(Arrays.asList("172.16.0.0/24"));
        request.setRemoteSubnet("192.168.100.0/24");
        vpnClient.updateSslVpnServer(request);
    }

    @Test
    public void deleteSslVpnServer() {
        DeleteSslVpnServerRequest request = new DeleteSslVpnServerRequest();
        request.setVpnId("vpn-b98ed8hpm3ij");
        request.setSslVpnServerId("sslvpn-id");
        vpnClient.deleteSslVpnServer(request);
    }

    @Test
    public void getSslVpnServer() {
        GetSslVpnServerResponse sslVpnServer = vpnClient.getSslVpnServer("vpn-id");
        toJsonPrettyString("getSslVpnServer:", sslVpnServer);
    }

    @Test
    public void batchCreateSslVpnUser() {
        BatchCreateSslVpnUserRequest request = new BatchCreateSslVpnUserRequest();
        SslVpnUser sslVpnUser1 = new SslVpnUser();
        sslVpnUser1.setUserName("user1");
        sslVpnUser1.setPassword("qwe123Test.1");
        sslVpnUser1.setDescription("desc user1");
        SslVpnUser sslVpnUser2 = new SslVpnUser();
        sslVpnUser2.setUserName("user2");
        sslVpnUser2.setPassword("qwe123Test.2");
        sslVpnUser2.setDescription("desc user2");
        request.setSslVpnUsers(Arrays.asList(sslVpnUser1, sslVpnUser2));
        request.setVpnId("vpn-id");
        BatchCreateSslVpnUserResponse batchCreateSslVpnUserResponse = vpnClient.batchCreateSslVpnUser(request);
        toJsonPrettyString("batchCreateSslVpnUser:", batchCreateSslVpnUserResponse);
    }

    @Test
    public void updateSslVpnUser() {
        UpdateSslVpnUserRequest request = new UpdateSslVpnUserRequest();
        request.setUserId("vpn-ssl-user-id");
        request.setVpnId("vpn-id");
        request.setDescription("description-update");
        vpnClient.updateSslVpnUser(request);
    }

    @Test
    public void deleteSslVpnUser() {
        DeleteSslVpnUserRequest request = new DeleteSslVpnUserRequest();
        request.setUserId("vpn-ssl-user-id");
        request.setVpnId("vpn-id");
        vpnClient.deleteSslVpnUser(request);
    }

    @Test
    public void getSslVpnUser() {
        ListSslVpnUserRequest request = new ListSslVpnUserRequest();
        request.setVpnId("vpn-id");
        ListSslVpnUserResponse sslVpnUser = vpnClient.getSslVpnUser(request);
        toJsonPrettyString("getSslVpnUser:", sslVpnUser);
    }

}
