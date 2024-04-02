package com.baidubce.services.dns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dns.model.AddLineGroupRequest;
import com.baidubce.services.dns.model.CreatePaidZoneRequest;
import com.baidubce.services.dns.model.CreateRecordRequest;
import com.baidubce.services.dns.model.CreateZoneRequest;
import com.baidubce.services.dns.model.RenewZoneRequest;
import com.baidubce.services.dns.model.UpdateLineGroupRequest;
import com.baidubce.services.dns.model.UpdateRecordRequest;
import com.baidubce.services.dns.model.UpgradeZoneRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * dnsClient test
 *
 * @author ccq
 * @since 2022/8/29
 */
public class DnsClientTest {

    private static final Logger logger = LoggerFactory.getLogger(DnsClientTest.class);
    private static final String ak = "3251d60a16f94c839f7aa4b87ed4913b";
    private static final String sk = "21d8c3cdfe1242148e29465fca734e1f";
    private DnsClient dnsClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("gwgp-7ytktyvth6d.b.gateway-test.baidu-int.com");
        dnsClient = new DnsClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createZoneTest() {
        CreateZoneRequest createZoneRequest = new CreateZoneRequest();
        createZoneRequest.setName("javaSdk.com");
        dnsClient.createZone(createZoneRequest, "");
    }

    @Test
    public void listZoneTest() {
        toJsonPrettyString("list dns result", dnsClient.listZone("", "", 1));
    }

    @Test
    public void deleteZoneTest() {
        dnsClient.deleteZone("javasdk.com", null);
    }

    @Test
    public void createPaidZoneTest() {
        CreatePaidZoneRequest createZoneRequest = new CreatePaidZoneRequest();
        CreatePaidZoneRequest.Billing billing = new CreatePaidZoneRequest.Billing();
        billing.setPaymentTiming("Prepaid");
        CreatePaidZoneRequest.Billing.Reservation reservation = new CreatePaidZoneRequest.Billing.Reservation();
        reservation.setReservationLength(1);
        billing.setReservation(reservation);
        createZoneRequest.setNames(Arrays.asList("javaSdkPaid.com"));
        createZoneRequest.setBilling(billing);
        createZoneRequest.setProductVersion("discount");
        dnsClient.createPaidZone(createZoneRequest, "");
    }

    @Test
    public void upgradeZoneTest() {
        UpgradeZoneRequest upgradeZoneRequest = new UpgradeZoneRequest();
        UpgradeZoneRequest.Billing billing = new UpgradeZoneRequest.Billing();
        billing.setPaymentTiming("Prepaid");
        UpgradeZoneRequest.Billing.Reservation reservation = new UpgradeZoneRequest.Billing.Reservation();
        reservation.setReservationLength(1);
        billing.setReservation(reservation);
        upgradeZoneRequest.setNames(Arrays.asList("javaSdk.com"));
        upgradeZoneRequest.setBilling(billing);
        dnsClient.upgradeZone(upgradeZoneRequest, "");
    }

    @Test
    public void renewZoneTest() {
        RenewZoneRequest renewZoneRequest = new RenewZoneRequest();
        RenewZoneRequest.Billing billing = new RenewZoneRequest.Billing();
        RenewZoneRequest.Billing.Reservation reservation = new RenewZoneRequest.Billing.Reservation();
        reservation.setReservationLength(1);
        billing.setReservation(reservation);
        renewZoneRequest.setBilling(billing);
        dnsClient.renewZone("javaSdk.com", renewZoneRequest, null);
    }

    @Test
    public void createRecordTest() {
        CreateRecordRequest createRecordRequest = new CreateRecordRequest();
        createRecordRequest.setRr("www");
        createRecordRequest.setType("A");
        createRecordRequest.setValue("1.1.1.1");
        dnsClient.createRecord("ccq.com", createRecordRequest, "");
    }

    @Test
    public void listRecordTest() {
        toJsonPrettyString("list record result", dnsClient.listRecord("ccq.com", "", "", "", 1));
    }

    @Test
    public void updateRecordTest() {
        UpdateRecordRequest updateRecordRequest = new UpdateRecordRequest();
        updateRecordRequest.setRr("www");
        updateRecordRequest.setType("A");
        updateRecordRequest.setValue("1.1.1.2");
        dnsClient.updateRecord("ccq.com", "48554", updateRecordRequest, "");
    }

    @Test
    public void updateRecordEnableTest() {
        dnsClient.updateRecordEnable("ccq.com", "48561", null);
    }

    @Test
    public void updateRecordDisableTest() {
        dnsClient.updateRecordDisable("ccq.com", "48561", null);
    }

    @Test
    public void deleteRecordTest() {
        dnsClient.deleteRecord("ccq.com", "48561", null);
    }

    @Test
    public void addLineGroupTest() {
        AddLineGroupRequest addLineGroupRequest = new AddLineGroupRequest();
        addLineGroupRequest.setName("ccqLine");
        addLineGroupRequest.setLines(Arrays.asList("yunnan.ct", "henan.ct"));
        dnsClient.addLineGroup(addLineGroupRequest, "");
    }

    @Test
    public void updateLineGroupTest() {
        UpdateLineGroupRequest updateLineGroupRequest = new UpdateLineGroupRequest();
        updateLineGroupRequest.setName("ccqLine1");
        updateLineGroupRequest.setLines(Arrays.asList("yunnan.ct"));
        dnsClient.updateLineGroup("6166", updateLineGroupRequest, "");
    }

    @Test
    public void listLineGroupTest() {
        toJsonPrettyString("list line group result", dnsClient.listLineGroup( "", 3));
    }

    @Test
    public void deleteLineGroupTest() {
        dnsClient.deleteLineGroup("6166", null);
    }







}
