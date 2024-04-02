package com.baidubce.services.lbdc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lbdc.model.CreateLbdcRequest;
import com.baidubce.services.lbdc.model.RenewLbdcRequest;
import com.baidubce.services.lbdc.model.UpdateLbdcRequest;
import com.baidubce.services.lbdc.model.UpgradeLbdcRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * LbdcClientTest test
 *
 * @author ccq
 * @since 2023/11/24
 */
public class LbdcClientTest {

    private static final Logger logger = LoggerFactory.getLogger(LbdcClientTest.class);
    private static final String ak = "3251d60a16f94c839f7aa4b87ed4913b";
    private static final String sk = "21d8c3cdfe1242148e29465fca734e1f";
    private LbdcClient lbdcClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("blb.bj.qasandbox.baidu-int.com");
        lbdcClient = new LbdcClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createLbdcTest() {
        CreateLbdcRequest createLbdcRequest = new CreateLbdcRequest();
        createLbdcRequest.setName("lbdcName");
        createLbdcRequest.setType("4Layer");
        createLbdcRequest.setCcuCount(2);
        CreateLbdcRequest.BillingForCreate billingForCreate = new CreateLbdcRequest.BillingForCreate();
        billingForCreate.setPaymentTiming("Prepaid");
        CreateLbdcRequest.Reservation reservation = new CreateLbdcRequest.Reservation();
        reservation.setReservationLength(3);
        billingForCreate.setReservation(reservation);
        createLbdcRequest.setRenewReservation(reservation);
        createLbdcRequest.setBilling(billingForCreate);
        toJsonPrettyString("create lbdc result", lbdcClient.createLbdc(createLbdcRequest, ""));
    }

    @Test
    public void upgradeLbdcTest() {
        UpgradeLbdcRequest upgradeLbdcRequest = new UpgradeLbdcRequest();
        upgradeLbdcRequest.setCcuCount(8);
        lbdcClient.upgradeLbdc("bgw_group-1f1b6e17", upgradeLbdcRequest, "");
    }

    @Test
    public void renewLbdcTest() {
        RenewLbdcRequest renewLbdcRequest = new RenewLbdcRequest();
        RenewLbdcRequest.BillingForRenew billingForRenew = new RenewLbdcRequest.BillingForRenew();
        RenewLbdcRequest.BillingForRenew.Reservation reservation = new RenewLbdcRequest.BillingForRenew.Reservation();
        reservation.setReservationLength(5);
        billingForRenew.setReservation(reservation);
        renewLbdcRequest.setBilling(billingForRenew);
        lbdcClient.renewLbdc("bgw_group-1f1b6e17", renewLbdcRequest, "");
    }

    @Test
    public void listLbdcTest() {
        toJsonPrettyString("list lbdc result", lbdcClient.listLbdc(null, null));
    }

    @Test
    public void getLbdcTest() {
        toJsonPrettyString("get lbdc result", lbdcClient.getLbdc("bgw_group-1f1b6e17"));
    }

    @Test
    public void updateLbdcTest() {
        UpdateLbdcRequest updateLbdcRequest = new UpdateLbdcRequest();
        updateLbdcRequest.setDesc("updateDesc");
        lbdcClient.updateLbdc("bgw_group-1f1b6e17", updateLbdcRequest, null);
    }

    @Test
    public void getBoundBlBListOfLbdcTest() {
        toJsonPrettyString("get bound blb list of lbdc result",
                lbdcClient.getBoundBlBListOfLbdc("bgw_group-f632f4bb"));
    }








}
