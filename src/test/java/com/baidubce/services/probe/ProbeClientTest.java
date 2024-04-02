package com.baidubce.services.probe;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.probe.model.CreateProbeRequest;
import com.baidubce.services.probe.model.CreateProbeResponse;
import com.baidubce.services.probe.model.GetProbeResponse;
import com.baidubce.services.probe.model.ListProbeRequest;
import com.baidubce.services.probe.model.ListProbeResponse;
import com.baidubce.services.probe.model.UpdateProbeRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class ProbeClientTest {

    private static final String AK = "";
    private static final String SK = "";

    protected ProbeClient probeClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("");
        probeClient = new ProbeClient(config);
    }

    @Test
    public void createProbeTest() {
        CreateProbeRequest createProbeRequest = new CreateProbeRequest();
        createProbeRequest.setClientToken(UUID.randomUUID().toString());
        createProbeRequest.setName("test");
        createProbeRequest.setDescription("desc");
        createProbeRequest.setVpcId("vpcId");
        createProbeRequest.setSubnetId("sbnId");
        createProbeRequest.setProtocol("UDP");
        createProbeRequest.setFrequency(10);
        createProbeRequest.setSourceIpNum(2);
        createProbeRequest.setDestIp("2.2.2.2");
        createProbeRequest.setDestPort(80);
        createProbeRequest.setPayload("udp_probe");
        CreateProbeResponse probe = probeClient.createProbe(createProbeRequest);
        System.out.println(probe);


    }

    @Test
    public void listProbeTest() {
        ListProbeRequest listProbeRequest = new ListProbeRequest();
        ListProbeResponse listProbeResponse = probeClient.listProbes(listProbeRequest);
        System.out.println(listProbeResponse);
    }

    @Test
    public void getProbeTest() {
        String probeId = "probeId";
        GetProbeResponse probe = probeClient.getProbe(probeId);
        System.out.println(probe);
    }

    @Test
    public void updateProbeTest() {
        UpdateProbeRequest updateProbeRequest = new UpdateProbeRequest();
        updateProbeRequest.setProbeId("probeId");
        updateProbeRequest.setName("name-update");
        probeClient.updateProbe(updateProbeRequest);
        GetProbeResponse probe = probeClient.getProbe(updateProbeRequest.getProbeId());
        System.out.println(probe);
    }

    @Test
    public void deleteProbeTest() {
        String probeId = "probeId";
        probeClient.deleteProbe(probeId);
    }

}
