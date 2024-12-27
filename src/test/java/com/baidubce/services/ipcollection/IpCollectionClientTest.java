package com.baidubce.services.ipcollection;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipcollection.model.ipgroup.BindIpSetRequest;
import com.baidubce.services.ipcollection.model.ipgroup.CreateIpAddressGroupRequest;
import com.baidubce.services.ipcollection.model.ipgroup.DeleteIpGroupRequest;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupDetailRequest;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupDetailResponse;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupListRequest;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupListResponse;
import com.baidubce.services.ipcollection.model.ipgroup.UnBindIpSetRequest;
import com.baidubce.services.ipcollection.model.ipgroup.UpdateIpGroupRequest;
import com.baidubce.services.ipcollection.model.ipset.AddIpAddressToIpSetRequest;
import com.baidubce.services.ipcollection.model.ipset.CreateIpAddressSetRequest;
import com.baidubce.services.ipcollection.model.ipset.DeleteIpSetRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetDetailRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetDetailResponse;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetListRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetListResponse;
import com.baidubce.services.ipcollection.model.ipset.RemoveIpAddressFromIpSetRequest;
import com.baidubce.services.ipcollection.model.TemplateIpAddressInfo;
import com.baidubce.services.ipcollection.model.ipset.UpdateIpSetRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IpCollectionClientTest {

    private static final String AK = "Your ak";
    private static final String SK = "Your sk";
    private static final String HOST = "bcc.bj.qasandbox.baidu-int.com";
    private IpCollectionClient ipCollectionClient;

    @Before
    public void setUp() {
        IpCollectionClientConfiguration config = new IpCollectionClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint(HOST);
        ipCollectionClient = new IpCollectionClient(config);
    }

    @Test
    public void testCreateIpAddressSet()  {
        // success case
        CreateIpAddressSetRequest createIpAddressGroupRequest = new CreateIpAddressSetRequest();
        createIpAddressGroupRequest.setName("test20241205");
        createIpAddressGroupRequest.setIpVersion("IPv4");
        List<TemplateIpAddressInfo> ipAddressInfo = new ArrayList<>();
        TemplateIpAddressInfo templateIpAddressInfo = new TemplateIpAddressInfo();
        templateIpAddressInfo.setIpAddress("192.168.33.0");
        templateIpAddressInfo.setDescription("test ip address");
        ipAddressInfo.add(templateIpAddressInfo);

        createIpAddressGroupRequest.setIpAddressInfo(ipAddressInfo);
        createIpAddressGroupRequest.setDescription("test description");
        ipCollectionClient.createIpAddressSet(createIpAddressGroupRequest);
    }

    @Test
    public void testAddIpAddressToIpSet() {
        AddIpAddressToIpSetRequest request = new AddIpAddressToIpSetRequest();
        request.setIpSetId("ips-zmbp03b6wz8a");

        List<TemplateIpAddressInfo> ipAddressInfo = new ArrayList<>();
        TemplateIpAddressInfo templateIpAddressInfo = new TemplateIpAddressInfo();
        templateIpAddressInfo.setIpAddress("192.168.39.0");
        templateIpAddressInfo.setDescription("test ip address2");
        ipAddressInfo.add(templateIpAddressInfo);
        request.setIpAddressInfo(ipAddressInfo);
        ipCollectionClient.addIpAddressToIpSet(request);
    }

    @Test
    public void testRemoveIpAddressFromIpSet() {
        RemoveIpAddressFromIpSetRequest request = new RemoveIpAddressFromIpSetRequest();
        request.setIpSetId("ips-zmbp03b6wz8a");
        List<String> ipAddressInfo = new ArrayList<>();
        ipAddressInfo.add("192.168.34.0");
        ipAddressInfo.add("192.168.35.0");
        request.setIpAddressInfo(ipAddressInfo);

        ipCollectionClient.removeIpAddressFromIpSet(request);
    }

    @Test
    public void testUpdateIpSet() {
        UpdateIpSetRequest updateIpSetRequest = new UpdateIpSetRequest();
        updateIpSetRequest.setClientToken(UUID.randomUUID().toString());
        updateIpSetRequest.setDescription("test description update1212121");
        updateIpSetRequest.setIpSetId("ips-zmbp03b6wz8a");
        updateIpSetRequest.setName("test20241205-updat21111");
        ipCollectionClient.updateIpSet(updateIpSetRequest);
    }

    @Test
    public void testDeleteIpSet() {
        DeleteIpSetRequest deleteIpSetRequest = new DeleteIpSetRequest();
        deleteIpSetRequest.setClientToken(UUID.randomUUID().toString());
        deleteIpSetRequest.setIpSetId("ips-xwnu2mdjcxfy");
        ipCollectionClient.deleteIpSet(deleteIpSetRequest);
    }

    @Test
    public void testQueryIpSetList(){
        QueryIpSetListRequest request = new QueryIpSetListRequest();
        request.setIpVersion("IPv6");
        request.setMaxKeys(10);
        request.setMarker("ips-xwnu2mdjcxfy");
        QueryIpSetListResponse response = ipCollectionClient.queryIpSetList(request);
        Assert.assertNotNull(response);
    }

    @Test
    public void testQueryIpSetDetail(){
        QueryIpSetDetailRequest request = new QueryIpSetDetailRequest();
        request.setIpSetId("ips-kabcn396gjdz");
        QueryIpSetDetailResponse queryIpSetDetailResponse = ipCollectionClient.queryIpSetDetail(request);
        Assert.assertNotNull(queryIpSetDetailResponse);
    }

    @Test
    public void testCreateIpAddressGroup(){
        CreateIpAddressGroupRequest request = new CreateIpAddressGroupRequest();
        request.setName("ipGroupByCode_1223");
        request.setIpVersion("IPv4");
        request.setDescription("test descriptio32n");
        List<String> ipSetList = new ArrayList<>();
        ipSetList.add("ips-vqkhe16ex5vb");
        request.setIpSetIds(ipSetList);
        ipCollectionClient.createIpAddressGroup(request);
    }

    @Test
    public void testBindIpSet(){
        BindIpSetRequest request = new BindIpSetRequest();
        request.setIpGroupId("ipg-biw5wer67zhx");
        List<String> ipSetIds = new ArrayList<>();
        ipSetIds.add("ips-kabcn396gjdz");
        ipSetIds.add("ips-vqkhe16ex5vb");
        request.setIpSetIds(ipSetIds);

        ipCollectionClient.bindIpSet(request);
    }

    @Test
    public void testUnBindIpSet(){
        UnBindIpSetRequest request = new UnBindIpSetRequest();
        request.setIpGroupId("ipg-biw5wer67zhx");
        List<String> ipSetIds = new ArrayList<>();
        ipSetIds.add("ips-kabcn396gjdz");
        ipSetIds.add("ips-vqkhe16ex5vb");
        request.setIpSetIds(ipSetIds);

        ipCollectionClient.unBindIpSet(request);
    }

    @Test
    public void testUpdateIpGroup(){
        UpdateIpGroupRequest request = new UpdateIpGroupRequest();
        request.setIpGroupId("ipg-biw5wer67zhx");
        request.setName("ipGroupByCode_Updat11e");
        request.setDescription("test description up11date");
        ipCollectionClient.updateIpGroup(request);
    }

    @Test
    public void testDeleteIpGroup(){
        DeleteIpGroupRequest request = new DeleteIpGroupRequest();
        request.setIpGroupId("ipg-biw5wer67zhx");
        ipCollectionClient.deleteIpGroup(request);
    }

    @Test
    public void testQueryIpGroupList(){
        QueryIpGroupListRequest request = new QueryIpGroupListRequest();
        request.setIpVersion("IPv4");
        request.setMaxKeys(10);
        request.setMarker("ipg-de165m88ipiz");
        QueryIpGroupListResponse response = ipCollectionClient.queryIpGroupList(request);
        Assert.assertNotNull(response);
    }

    @Test
    public void testQueryIpGroupDetail(){
        QueryIpGroupDetailRequest request = new QueryIpGroupDetailRequest();
        request.setIpGroupId("ipg-p40bmsaw3aad");

        QueryIpGroupDetailResponse response = ipCollectionClient.queryIpGroupDetail(request);
        Assert.assertNotNull(response);
    }

}
