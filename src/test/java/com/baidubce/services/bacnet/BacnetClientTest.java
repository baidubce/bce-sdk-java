package com.baidubce.services.bacnet;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bacnet.internal.BacnetObjectInternal;
import com.baidubce.services.bacnet.internal.BacnetObjectState;
import com.baidubce.services.bacnet.internal.CreateBacnetDeviceRequest;
import com.baidubce.services.bacnet.internal.CreateBacnetObjectRequest;
import com.baidubce.services.bacnet.internal.ListBacnetObjectInternalResponse;
import com.baidubce.services.bacnet.internal.UpdateBacnetDeviceRequest;
import com.baidubce.services.bacnet.internal.UpdateBacnetObjectBatchRequest;
import com.baidubce.services.bacnet.model.BacnetDevice;
import com.baidubce.services.bacnet.model.BacnetGateway;
import com.baidubce.services.bacnet.model.BacnetGatewayCredential;
import com.baidubce.services.bacnet.model.BacnetObject;
import com.baidubce.services.bacnet.model.BacnetObjectId;
import com.baidubce.services.bacnet.model.BacnetObjectPresentValue;
import com.baidubce.services.bacnet.model.ListBacnetObjectResponse;
import com.baidubce.services.bacnet.model.ListObjectPresentValueRequest;
import com.baidubce.services.bacnet.model.ListObjectPresentValueResponse;
import com.baidubce.services.bacnet.model.SubBacnetObjectRequest;
import com.baidubce.services.bacnet.model.UpdateBacnetObjectPresentValueRequest;
import com.baidubce.services.bacnet.model.UpdateDestTopicsRequest;
import com.baidubce.services.bacnet.model.CreateBacnetGatewayRequest;
import com.baidubce.services.bacnet.model.ListBacnetDeviceResponse;
import com.baidubce.services.bacnet.model.ListBacnetGatewayResponse;
import com.baidubce.services.bacnet.model.ListRequest;
import com.baidubce.services.bacnet.model.UpdateBacnetGatewayRequest;

import com.baidubce.services.bacnet.internal.UpdateLastActiveTimeRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by yuanyoujun on 2017/10/18.
 */
public class BacnetClientTest {
    private static final String AK = "fc375540f44e4728884a4b86dc50340b";
    private static final String SK = "9db8de13f5d141b5a3d38f6dd937fba2";
    private static final String ENDPOINT = "http://nmg01-hpc-w1134.nmg01.baidu.com:8008";

    // test data
    static final String DEFAULT_DESCRIPTION = "test bacnet gateway description";
    static final String DEFAULT_TOPICS = "topic1,sensor/gateway";
    static final int DEFAULT_INSTANCE = 86021;
    static final int DEFAULT_INSTANCE_DEVICE = 1999;
    static final int DEFAULT_POLL_INTERVAL = 30;
    static final int DEFAULT_POLL_INTERVAL_COV = 300;
    static final int DEFAULT_SUB_DURATION = 600;
    static final int DEFAULT_WHOIS_INTERVAL = 1000;
    static final int DEFAULT_OBJECT_DISCOVER_INTERVAL = 3601;
    static final String SUBSCRIBE_TYPE_NONE = "NoSubscribe";
    static final String SUBSCRIBE_TYPE_OBJECT = "SubscribeObject";
    static final double DEFAULT_COV_INCREMENT = 0.5;

    // device
    static final int DEFAULT_REVISION = 14;
    static final int DEFAULT_VERSION = 1;
    static final int DEFAULT_VENDOR_ID = 17;
    static final String DEFAULT_VENDOR_NAME = "TEST BACNET SDK";
    static final String DEFAULT_TYPES_SUPPORTED = "DEFAULT_TYPES_SUPPORTED";
    static final String DEFAULT_SERVICES_SUPPORTED = "DEFAULT_SERVICES_SUPPORTED";

    // object
    static final int DEFAULT_OBJECT_TYPE = 0;
    static final String DEFAULT_OBJECT_TYPE_NAME = "ANALOG_INPUT";
    static final int DEFAULT_OBJECT_INSTANCE = 1;

    public BacnetClient client;
    public BacnetInternalClient internalClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        client = new BacnetClient(config);
        internalClient = new BacnetInternalClient(config);
    }

    @Test
    public void createGateway() throws Exception {
        String name = "createGateway";
        long gwid = createGatewayImpl(name);

        BacnetGateway gateway = client.getGateway(gwid);
        Assert.assertEquals(name, gateway.getName());
        Assert.assertEquals(DEFAULT_DESCRIPTION, gateway.getDescription());
        Assert.assertEquals(DEFAULT_TOPICS, gateway.getDestTopics());
        Assert.assertEquals(DEFAULT_INSTANCE, gateway.getInstanceNumber());
        Assert.assertEquals(DEFAULT_POLL_INTERVAL, gateway.getPollInterval());
        Assert.assertEquals(DEFAULT_POLL_INTERVAL_COV, gateway.getPollIntervalCov());
        Assert.assertEquals(DEFAULT_SUB_DURATION, gateway.getSubscribeDuration());
        Assert.assertEquals(DEFAULT_WHOIS_INTERVAL, gateway.getWhoIsInterval());
        Assert.assertEquals(DEFAULT_OBJECT_DISCOVER_INTERVAL, gateway.getObjectDiscoverInterval());
        Assert.assertEquals(SUBSCRIBE_TYPE_NONE, gateway.getSubscribeType());
        Assert.assertEquals(DEFAULT_COV_INCREMENT, gateway.getCovIncrement(), 0.01);

        // delete
        client.deleteGateway(gwid);
    }

    @Test
    public void updateGateway() throws Exception {
        String name = "updateGateway";
        long gwid = createGatewayImpl(name);

        UpdateBacnetGatewayRequest request = new UpdateBacnetGatewayRequest();
        String description = DEFAULT_DESCRIPTION + " updated";
        request.setDescription(description);
        boolean useSsl = true;
        request.setUseSsl(useSsl);
        int instanceNumber = DEFAULT_INSTANCE - 1;
        request.setInstanceNumber(instanceNumber);
        String ipOrInterface = "eth0";
        request.setIpOrInterface(ipOrInterface);
        int pollInterval = DEFAULT_POLL_INTERVAL - 1;
        request.setPollInterval(pollInterval);
        int pollIntervalCov = DEFAULT_POLL_INTERVAL_COV - 1;
        request.setPollIntervalCov(pollIntervalCov);
        int whoIsInterval = DEFAULT_POLL_INTERVAL_COV - 1;
        request.setWhoIsInterval(whoIsInterval);
        int subscribeDuration = DEFAULT_SUB_DURATION - 1;
        request.setSubscribeDuration(subscribeDuration);
        String destTopic = DEFAULT_TOPICS + ",topic3";
        request.setDestTopics(destTopic);
        String subscribeType = SUBSCRIBE_TYPE_OBJECT;
        request.setSubscribeType(subscribeType);
        double covIncrement = DEFAULT_COV_INCREMENT + .5;
        request.setCovIncrement(covIncrement);
        int objectDiscoveryInterval = DEFAULT_OBJECT_DISCOVER_INTERVAL + 1;
        request.setObjectDiscoverInterval(objectDiscoveryInterval);

        client.updateGateway(request, gwid);

        BacnetGateway gateway = client.getGateway(gwid);

        Assert.assertEquals(description, gateway.getDescription());
        Assert.assertEquals(useSsl, gateway.isUseSsl());
        Assert.assertEquals(instanceNumber, gateway.getInstanceNumber());
        Assert.assertEquals(ipOrInterface, gateway.getIpOrInterface());
        Assert.assertEquals(pollInterval, gateway.getPollInterval());
        Assert.assertEquals(pollIntervalCov, gateway.getPollIntervalCov());
        Assert.assertEquals(whoIsInterval, gateway.getWhoIsInterval());
        Assert.assertEquals(subscribeDuration, gateway.getSubscribeDuration());
        Assert.assertEquals(subscribeType, gateway.getSubscribeType());
        Assert.assertEquals(covIncrement, gateway.getCovIncrement(), 0.01);
        Assert.assertEquals(objectDiscoveryInterval, gateway.getObjectDiscoverInterval());

        Set<String> expTopics = new HashSet<String>(Arrays.asList(destTopic.split(",")));
        Set<String> actualTopics = new HashSet<String>(Arrays.asList(gateway.getDestTopics().split(",")));
        Assert.assertEquals(expTopics, actualTopics);

        // delete
        client.deleteGateway(gwid);
    }

    @Test
    public void updateGatewayLastActiveTime() throws Exception {
        String name = "updateGatewayLastActiveTime";
        long gwid = createGatewayImpl(name);

        UpdateLastActiveTimeRequest request = new UpdateLastActiveTimeRequest();
        Date lastActiveTime = new Date(System.currentTimeMillis() / 1000 * 1000);   // round to second
        request.setLastActiveTime(lastActiveTime);
        internalClient.updateGatewayLastActiveTime(request, gwid);
        BacnetGateway gateway = client.getGateway(gwid);
        Assert.assertEquals(lastActiveTime.getTime(), gateway.getLastActiveTime().getTime());

        // delete
        client.deleteGateway(gwid);
    }

    private long createGatewayImpl(String name) {
        deleteGatewayByName(name);

        CreateBacnetGatewayRequest request = new CreateBacnetGatewayRequest();
        request.setName(name);
        request.setDescription(DEFAULT_DESCRIPTION);
        request.setDestTopics(DEFAULT_TOPICS);
        request.setInstanceNumber(DEFAULT_INSTANCE);
        request.setPollInterval(DEFAULT_POLL_INTERVAL);
        request.setPollIntervalCov(DEFAULT_POLL_INTERVAL_COV);
        request.setSubscribeDuration(DEFAULT_SUB_DURATION );
        request.setWhoIsInterval(DEFAULT_WHOIS_INTERVAL);
        request.setObjectDiscoverInterval(DEFAULT_OBJECT_DISCOVER_INTERVAL);
        request.setSubscribeType(SUBSCRIBE_TYPE_NONE);
        request.setCovIncrement(DEFAULT_COV_INCREMENT);

        return client.createGateway(request).getId();
    }

    @Test
    public void getCredential() throws Exception {
        long gwid = createGatewayImpl("getCredential");
        BacnetGatewayCredential credential = client.getGatewayCredential(gwid);
        String cmdTopic = String.format("/_sys_/bacnet/%d/sub", gwid);
        Assert.assertEquals(cmdTopic, credential.getCmdTopic());
        String dataTopic = String.format("/_sys_/bacnet/%d/pub", gwid);
        Assert.assertEquals(dataTopic, credential.getDataTopic());

        client.deleteGateway(gwid);
    }

    void deleteGatewayByName(String name) {
        ListRequest request = new ListRequest();
        request.setPageNo(0);
        request.setPageSize(50);
        ListBacnetGatewayResponse page = null;
        do {
            request.setPageNo(request.getPageNo() + 1);
            page = client.listGateway(request);
            for (BacnetGateway gw : page.getResult()) {
                if (gw.getName().equals(name)) {
                    client.deleteGateway(gw.getId());
                    return;
                }
            }
        } while (page.getTotalCount() > request.getPageNo() * request.getPageSize());
    }

    // device
    @Test
    public void createDevice() throws Exception {
        String gatewayName = "createDevice";
        long gwid = createGatewayImpl(gatewayName);

        String deviceName = "createDevice";
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);

        BacnetDevice device = client.getDevice(gwid, DEFAULT_INSTANCE_DEVICE);
        Assert.assertEquals(deviceName, device.getName());
        Assert.assertEquals(DEFAULT_INSTANCE_DEVICE, device.getInstanceNumber());
        Assert.assertEquals(DEFAULT_DESCRIPTION, device.getDescription());
        Assert.assertEquals(DEFAULT_REVISION, device.getRevision());
        Assert.assertEquals(DEFAULT_VERSION, device.getVersion());
        Assert.assertEquals(DEFAULT_VENDOR_ID, device.getVendorId());
        Assert.assertEquals(DEFAULT_VENDOR_NAME, device.getVendorName());
        Assert.assertEquals(DEFAULT_TYPES_SUPPORTED, device.getTypesSupported());
        Assert.assertEquals(DEFAULT_SERVICES_SUPPORTED, device.getServicesSupported());

        client.deleteDevice(gwid, DEFAULT_INSTANCE_DEVICE);
        client.deleteGateway(gwid);

    }

    @Test
    public void listDeivce() throws Exception {
        String gatewayName = "listDeivce";
        long gwid = createGatewayImpl(gatewayName);

        String deviceName1 = "listDeivce1";
        String deviceName2 = "listDeivce2";

        createBacnetDevice(gwid, 1, deviceName1);
        createBacnetDevice(gwid, 2, deviceName2);

        ListRequest listRequest = new ListRequest();
        listRequest.setPageSize(1);
        listRequest.setPageNo(0);
        int cnt = 0;
        ListBacnetDeviceResponse response = null;
        do {
            listRequest.setPageNo(listRequest.getPageNo() + 1);
            response = client.listDevice(listRequest, gwid);
            Assert.assertEquals(2, response.getTotalCount());
            Assert.assertTrue(response.getResult().get(0).getInstanceNumber() <= 2);
        } while (response.getTotalCount() > listRequest.getPageNo() * listRequest.getPageSize());

        client.deleteGateway(gwid);
    }

    @Test
    public void updateDevice() throws Exception {
        String gatewayName = "updateDevice";
        long gwid = createGatewayImpl(gatewayName);

        String deviceName = "updateDevice";
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);

        UpdateBacnetDeviceRequest request = new UpdateBacnetDeviceRequest();
        String name = deviceName + " updated";
        request.setName(name);
        String description = DEFAULT_DESCRIPTION + " updated";
        request.setDescription(description);
        int vendorId = DEFAULT_VENDOR_ID + 1;
        request.setVendorId(vendorId);
        String venderName = DEFAULT_VENDOR_NAME + " updated";
        request.setVendorName(venderName);
        int revision = DEFAULT_REVISION + 1;
        request.setRevision(revision);
        int version = DEFAULT_VERSION + 1;
        request.setVersion(version);
        String typesSupported = DEFAULT_TYPES_SUPPORTED + " updated";
        request.setTypesSupported(typesSupported);
        String serviceSupported = DEFAULT_SERVICES_SUPPORTED + " updated";
        request.setServicesSupported(serviceSupported);

        Date lastAcitveTime = new Date(System.currentTimeMillis() / 1000 * 1000);   // round to second
        request.setLastActiveTime(lastAcitveTime);

        internalClient.updateDevice(request, gwid, DEFAULT_INSTANCE_DEVICE);

        BacnetDevice device = client.getDevice(gwid, DEFAULT_INSTANCE_DEVICE);
        Assert.assertEquals(name, device.getName());
        Assert.assertEquals(description, device.getDescription());
        Assert.assertEquals(vendorId, device.getVendorId());
        Assert.assertEquals(venderName, device.getVendorName());
        Assert.assertEquals(revision, device.getRevision());
        Assert.assertEquals(version, device.getVersion());
        Assert.assertEquals(typesSupported, device.getTypesSupported());
        Assert.assertEquals(serviceSupported, device.getServicesSupported());

        Assert.assertEquals(lastAcitveTime.getTime(), device.getLastActiveTime().getTime());

        client.deleteGateway(gwid);
    }

    @Test
    public void updateDeviceDestTopic() throws Exception {
        String gatewayName = "updateDeviceDestTopic";
        long gwid = createGatewayImpl(gatewayName);

        String deviceName = "updateDeviceDestTopic";
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);

        UpdateDestTopicsRequest request = new UpdateDestTopicsRequest();
        String destTopic = DEFAULT_TOPICS + ",t4";
        request.setDestTopics(destTopic);
        client.updateDeviceDestTopic(request, gwid, DEFAULT_INSTANCE_DEVICE);

        BacnetDevice device = client.getDevice(gwid, DEFAULT_INSTANCE_DEVICE);
        Set<String> expTopics = new HashSet<String>(Arrays.asList(destTopic.split(",")));
        Set<String> actualTopics = new HashSet<String>(Arrays.asList(device.getDestTopics().split(",")));
        Assert.assertEquals(expTopics, actualTopics);

        client.deleteGateway(gwid);
    }

    void createBacnetDevice(long gwid, int instance, String name) {
        CreateBacnetDeviceRequest request = new CreateBacnetDeviceRequest();
        request.setInstanceNumber(instance);
        request.setName(name);
        request.setDescription(DEFAULT_DESCRIPTION);
        request.setRevision(DEFAULT_REVISION);
        request.setVersion(DEFAULT_VERSION);
        request.setVendorId(DEFAULT_VENDOR_ID);
        request.setVendorName(DEFAULT_VENDOR_NAME);
        request.setTypesSupported(DEFAULT_TYPES_SUPPORTED);
        request.setServicesSupported(DEFAULT_SERVICES_SUPPORTED);

        internalClient.createDevice(request, gwid);
    }

    // objects
    @Test
    public void createObject() {
        String name = "createObject";
        long gwid = createGatewayImpl(name);

        String deviceName = name;
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);

        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE);

        BacnetObject obj = client.getObject(gwid, DEFAULT_INSTANCE_DEVICE,
                DEFAULT_OBJECT_TYPE_NAME, DEFAULT_OBJECT_INSTANCE);
        Assert.assertEquals(DEFAULT_OBJECT_TYPE_NAME, obj.getObjectType());
        Assert.assertEquals(DEFAULT_OBJECT_INSTANCE, obj.getObjectInstance());

        client.deleteObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE_NAME, DEFAULT_OBJECT_INSTANCE);

        client.deleteDevice(gwid, DEFAULT_INSTANCE_DEVICE);
        client.deleteGateway(gwid);
    }

    @Test
    public void listObject() {
        String name = "listObject";
        long gwid = createGatewayImpl(name);

        String deviceName = name;
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);

        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE);
        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE + 1);

        ListRequest request = new ListRequest();
        request.setPageNo(0);
        request.setPageSize(1);

        ListBacnetObjectResponse response = null;

        do {
            request.setPageNo(request.getPageNo() + 1);
            response = client.listObject(request, gwid, DEFAULT_INSTANCE_DEVICE);
            Assert.assertEquals(1, response.getResult().size());
        } while (response.getTotalCount() > response.getPageNo() * response.getPageSize());
        Assert.assertEquals(2, response.getPageNo());

        client.deleteGateway(gwid);
    }

    @Test
    public void updateObjectDestTopics() {
        String name = "updateObjectDestTopics";
        long gwid = createGatewayImpl(name);

        String deviceName = name;
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);

        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE);
        UpdateDestTopicsRequest request = new UpdateDestTopicsRequest();
        request.setDestTopics(DEFAULT_TOPICS);
        client.updateObjectDestTopic(request, gwid, DEFAULT_INSTANCE_DEVICE,
                DEFAULT_OBJECT_TYPE_NAME, DEFAULT_OBJECT_INSTANCE);

        BacnetObject obj = client.getObject(gwid, DEFAULT_INSTANCE_DEVICE,
                DEFAULT_OBJECT_TYPE_NAME, DEFAULT_OBJECT_INSTANCE);
        Set<String> oldTopics = new HashSet<String>(Arrays.asList(DEFAULT_TOPICS.split(",")));
        Set<String> newTopics = new HashSet<String>(Arrays.asList(obj.getDestTopics().split(",")));
        Assert.assertEquals(oldTopics, newTopics);

        request.setDestTopics("");
        client.updateObjectDestTopic(request, gwid, DEFAULT_INSTANCE_DEVICE,
                DEFAULT_OBJECT_TYPE_NAME, DEFAULT_OBJECT_INSTANCE);

        obj = client.getObject(gwid, DEFAULT_INSTANCE_DEVICE,
                DEFAULT_OBJECT_TYPE_NAME, DEFAULT_OBJECT_INSTANCE);
        Assert.assertEquals("", obj.getDestTopics());

        client.deleteGateway(gwid);
    }

    @Test
    public void updateObject() {
        String name = "updateObject";
        long gwid = createGatewayImpl(name);

        String deviceName = name;
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);
        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE);

        UpdateBacnetObjectBatchRequest request = new UpdateBacnetObjectBatchRequest();
        request.setPoints(new ArrayList<BacnetObjectState>());
        BacnetObjectState state1 = new BacnetObjectState();
        state1.setObjectType(DEFAULT_OBJECT_TYPE);
        state1.setObjectInstance(DEFAULT_OBJECT_INSTANCE);
        state1.setName("AI1");
        state1.setPresentValue(12.345);
        long ts = System.currentTimeMillis() / 1000 * 1000;
        state1.setUpdateTime(new Date(ts));
        request.getPoints().add(state1);

        BacnetObjectState state2 = new BacnetObjectState();
        state2.setObjectType(DEFAULT_OBJECT_TYPE);
        state2.setObjectInstance(DEFAULT_OBJECT_INSTANCE + 1);
        state2.setName("AO2");
        state2.setPresentValue(10.4);
        state2.setCreateNew(true);
        state2.setUpdateTime(new Date(ts + 1000));
        request.getPoints().add(state2);

        internalClient.updateObjectBatch(request, gwid, DEFAULT_INSTANCE_DEVICE);
        ListRequest listRequest = new ListRequest();
        listRequest.setPageNo(1);
        listRequest.setPageSize(100);
        ListBacnetObjectInternalResponse response = internalClient.listBacnetObject(listRequest, gwid,
                DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE);

        for (BacnetObjectState state : request.getPoints()) {
            boolean foundAndMatch = false;
            for (BacnetObjectInternal boi : response.getResult()) {
                if (state.getObjectInstance() == boi.getObjectInstance()
                        && state.getObjectType() == boi.getObjectType()) {
                    Assert.assertEquals(state.getName(), boi.getName());
                    Assert.assertEquals("presentvalue", state.getPresentValue(), boi.getPresentValue(), 0.1);
                    foundAndMatch = true;
                    break;
                }
            }
            Assert.assertEquals(true, foundAndMatch);
        }

        // present value
        ListObjectPresentValueRequest listPV = new ListObjectPresentValueRequest();
        listPV.setPoints(new ArrayList<BacnetObjectId>());
        BacnetObjectId boid1 = new BacnetObjectId();
        boid1.setInstanceNumber(DEFAULT_INSTANCE_DEVICE);
        boid1.setObjectType(DEFAULT_OBJECT_TYPE_NAME);
        boid1.setObjectInstance(DEFAULT_OBJECT_INSTANCE);
        listPV.getPoints().add(boid1);

        BacnetObjectId boid2 = new BacnetObjectId();
        boid2.setInstanceNumber(DEFAULT_INSTANCE_DEVICE);
        boid2.setObjectType(DEFAULT_OBJECT_TYPE_NAME);
        boid2.setObjectInstance(DEFAULT_OBJECT_INSTANCE + 1);
        listPV.getPoints().add(boid2);

        ListObjectPresentValueResponse listPVResponse = client.listObjectPresentValue(listPV, gwid);
        for (BacnetObjectState state : request.getPoints()) {
            boolean foundAndMatch = false;
            for (BacnetObject bo : listPVResponse.getPoints()) {
                if (state.getObjectInstance() == bo.getObjectInstance()
                        && DEFAULT_OBJECT_TYPE_NAME.equals(bo.getObjectType())) {
                    Assert.assertEquals(state.getName(), bo.getName());
                    Assert.assertEquals("presentvalue", state.getPresentValue(), bo.getPresentValue(), 0.1);
                    foundAndMatch = true;
                    break;
                }
            }
            Assert.assertEquals(true, foundAndMatch);
        }

        client.deleteGateway(gwid);
    }

    @Test
    public void requestUpdateOfBacnetDeviceObjects() {
        String name = "requestUpdateOfBacnetDeviceObjects";
        long gwid = createGatewayImpl(name);

        String deviceName = name;
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);

        UpdateBacnetObjectPresentValueRequest uborvr = new UpdateBacnetObjectPresentValueRequest();
        List<BacnetObjectPresentValue> points = new ArrayList<BacnetObjectPresentValue>();
        BacnetObjectPresentValue p1 = new BacnetObjectPresentValue();
        p1.setInstanceNumber(DEFAULT_INSTANCE_DEVICE);
        p1.setObjectType(DEFAULT_OBJECT_TYPE_NAME);
        p1.setObjectInstance(DEFAULT_OBJECT_INSTANCE);
        String aiVal = "3.14";
        p1.setPresentValue(aiVal);
        points.add(p1);

        BacnetObjectPresentValue p2 = new BacnetObjectPresentValue();
        p2.setInstanceNumber(DEFAULT_INSTANCE_DEVICE);
        p2.setObjectType("BINARY_INPUT");
        p2.setObjectInstance(DEFAULT_OBJECT_INSTANCE);
        String biVal = "1";
        p2.setPresentValue(biVal);
        points.add(p2);

        uborvr.setPoints(points);
        client.updateObjectPresentValue(uborvr, gwid);
    }

    @Test
    public void subAndUnsub() {
        String name = "subAndUnsub";
        long gwid = createGatewayImpl(name);

        String deviceName = name;
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);
        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE);
        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE + 1);

        String destTopic = "subandunsub";
        SubBacnetObjectRequest subReq = new SubBacnetObjectRequest();
        subReq.setDestTopic(destTopic);

        subReq.setPoints(new ArrayList<BacnetObjectId>());
        BacnetObjectId boid1 = new BacnetObjectId();
        boid1.setInstanceNumber(DEFAULT_INSTANCE_DEVICE);
        boid1.setObjectType(DEFAULT_OBJECT_TYPE_NAME);
        boid1.setObjectInstance(DEFAULT_OBJECT_INSTANCE);
        subReq.getPoints().add(boid1);

        BacnetObjectId boid2 = new BacnetObjectId();
        boid2.setInstanceNumber(DEFAULT_INSTANCE_DEVICE);
        boid2.setObjectType(DEFAULT_OBJECT_TYPE_NAME);
        boid2.setObjectInstance(DEFAULT_OBJECT_INSTANCE + 1);
        subReq.getPoints().add(boid2);

        client.subObjectChanges(subReq, gwid, DEFAULT_INSTANCE_DEVICE);

        ListRequest listRequest = new ListRequest();
        listRequest.setPageNo(1);
        listRequest.setPageSize(100);
        ListBacnetObjectInternalResponse response = internalClient.listBacnetObject(listRequest, gwid,
                DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE);
        for (BacnetObjectInternal boi : response.getResult()) {
            Assert.assertEquals(destTopic, boi.getDestTopics());
        }

        String destTopic2 = "subandunsub2";
        subReq.setDestTopic(destTopic2);
        client.subObjectChanges(subReq, gwid, DEFAULT_INSTANCE_DEVICE);

        response = internalClient.listBacnetObject(listRequest, gwid,
                DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE);
        Set<String> topics = new HashSet<String>();
        topics.add(destTopic);
        topics.add(destTopic2);

        for (BacnetObjectInternal boi : response.getResult()) {
            Assert.assertEquals(topics, new HashSet<String>(Arrays.asList(boi.getDestTopics().split(","))));
        }

        client.unsubObjectChanges(subReq, gwid, DEFAULT_INSTANCE_DEVICE);

        response = internalClient.listBacnetObject(listRequest, gwid,
                DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE);
        topics.remove(destTopic2);
        for (BacnetObjectInternal boi : response.getResult()) {
            Assert.assertEquals(topics, new HashSet<String>(Arrays.asList(boi.getDestTopics().split(","))));
        }

        client.deleteGateway(gwid);
    }

    @Test
    public void refreshObject() {
        String name = "refreshObject";
        long gwid = createGatewayImpl(name);

        String deviceName = name;
        createBacnetDevice(gwid, DEFAULT_INSTANCE_DEVICE, deviceName);
        createBacnetObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE, DEFAULT_OBJECT_INSTANCE);

        client.refreshObject(gwid, DEFAULT_INSTANCE_DEVICE, DEFAULT_OBJECT_TYPE_NAME, DEFAULT_OBJECT_INSTANCE);
        client.deleteGateway(gwid);
    }

    void createBacnetObject(long gateway, int instance, int type, int objectInstance) {
        CreateBacnetObjectRequest request = new CreateBacnetObjectRequest();
        request.setObjectType(type);
        request.setObjectInstance(objectInstance);

        internalClient.createObject(request, gateway, instance);
    }
}
