/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.peerconn;

import com.baidubce.services.bcc.model.TagModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.peerconn.model.CreatePeerConnRequest;
import com.baidubce.services.peerconn.model.CreatePeerConnResponse;
import com.baidubce.services.peerconn.model.GetPeerConnRequest;
import com.baidubce.services.peerconn.model.GetPeerConnResponse;
import com.baidubce.services.peerconn.model.ListPeerConnRequest;
import com.baidubce.services.peerconn.model.ListPeerConnResponse;
import com.baidubce.services.peerconn.model.ModifyBandwidthRequest;
import com.baidubce.services.peerconn.model.ModifyPeerConnRequest;
import com.baidubce.services.peerconn.model.PeerConnIdRequest;
import com.baidubce.services.peerconn.model.PurchaseReservedPeerConnRequest;
import com.baidubce.services.peerconn.model.SyncDnsRequest;
import com.baidubce.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Peer conn client test
 *
 */
public class PeerConnClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(PeerConnClientTest.class);
    private static final String AK = "5a6f0606bf5e48f29dd132aa76aa5182";
    private static final String SK = "328057dfa861459d9622353570842988";

    private static final String PEER_AK = "1678d6c6a38c48eb9f30ce87518e2bab";
    private static final String PEER_SK = "1161439741594f62839855481d1c5b85";

    private PeerConnClient client;

    private static final String VPC_ID = "vpc-mivwn2w9xicb";
    private static final String PEER_VPC_ID = "vpc-4ma6p54th2hb";

    private String id = "peerconn-7q4v05qqspf5";

    private String localIfId = "qpif-i3esscy2g0y5";

    private String peerAccountId = "e8deba4d81d3435bb4591ce97233f296";

    private static final String HOST = "bcc.bce-api.baidu.com";

    @Before
    public void setUp() {
        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint(HOST);
        client = new PeerConnClient(config);
    }

    @Test
    public void testCreate() {
        CreatePeerConnRequest request = new CreatePeerConnRequest();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        billing.setReservation(reservation);
        request.setBilling(billing);
        request.setDescription("desc");
        request.setBandwidthInMbps(10);
        request.setLocalIfName("localIfName");
        request.setLocalVpcId(VPC_ID);
        List<TagModel> tags = new ArrayList<TagModel>();
        tags.add(new TagModel().withTagKey("testKey").withTagValue("testValue"));
        request.setTags(tags);
//        request.setPeerIfName("peerIfName");
//        request.setPeerAccountId(peerAccountId);
        request.setPeerRegion("bj");
        request.setPeerVpcId(PEER_VPC_ID);
        CreatePeerConnResponse createPeerConnResponse = client.createPeerConn(request);
        id = createPeerConnResponse.getPeerConnId();
        LOG.info("pc id is " + id);
    }

    @Test
    public void testList() {
        ListPeerConnRequest request  = new ListPeerConnRequest();
        request.setVpcId(VPC_ID);
        ListPeerConnResponse listPeerConnResponse = client.listPeerConn(request);
        LOG.info(JsonUtils.toJsonString(listPeerConnResponse));
    }

    @Test
    public void testGet() {
        GetPeerConnRequest request = new GetPeerConnRequest();
        request.setPeerConnId(id);
        GetPeerConnResponse response = client.getPeerConn(request);
        LOG.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void testModify() {
        ModifyPeerConnRequest request = new ModifyPeerConnRequest();
        request.setLocalIfName("modifyName");
        request.setPeerConnId(id);
        request.setLocalIfId(localIfId);
        client.modifyPeerConn(request);

        GetPeerConnRequest request1 = new GetPeerConnRequest();
        request1.setPeerConnId(id);
        GetPeerConnResponse response = client.getPeerConn(request1);
        Assert.assertEquals("modifyName", response.getLocalIfName());
    }

    @Test
    public void testAccept() {
        PeerConnIdRequest request = new PeerConnIdRequest();
        request.setPeerConnId(id);
        PeerConnClientConfiguration config1 = new PeerConnClientConfiguration();
        config1.setCredentials(new DefaultBceCredentials(PEER_AK, PEER_SK));
        config1.setEndpoint(HOST);
        PeerConnClient client1 = new PeerConnClient(config1);
        client1.accept(request);
    }

    @Test
    public void testReject() {
        PeerConnIdRequest request = new PeerConnIdRequest();
        request.setPeerConnId(id);
        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(PEER_AK, PEER_SK));
        config.setEndpoint(HOST);
        PeerConnClient client1 = new PeerConnClient(config);
        client1.reject(request);
    }

    @Test
    public void testRelease() {
        PeerConnIdRequest request = new PeerConnIdRequest();
        request.setPeerConnId(id);
        client.release(request);
    }

    @Test
    public void testModifyBandwith() {
        ModifyBandwidthRequest request = new ModifyBandwidthRequest();
        request.setNewBandwidthInMbps(10);
        request.setPeerConnId(id);
        client.modifyBandwith(request);
    }

    @Test
    public void testPurchaseReserved() {
        PurchaseReservedPeerConnRequest request = new PurchaseReservedPeerConnRequest();
        request.setPeerConnId(id);
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        Billing billing = new Billing();
        billing.setReservation(reservation);
        request.setBilling(billing);
        client.purchaseReserved(request);
    }

    @Test
    public void testOpenSyncDns() {
        SyncDnsRequest request = new SyncDnsRequest();
        request.setPeerConnId(id);
        request.setRole("initiator");
        client.openSyncDns(request);
    }

    @Test
    public void testCloseSyncDns() {
        SyncDnsRequest request = new SyncDnsRequest();
        request.setPeerConnId(id);
        request.setRole("initiator");
        client.closeSyncDns(request);
    }

}
