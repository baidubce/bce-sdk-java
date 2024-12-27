package com.baidubce.services.bci;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bci.model.common.Environment;
import com.baidubce.services.bci.model.common.ImageRegistryCredential;
import com.baidubce.services.bci.model.common.Port;
import com.baidubce.services.bci.model.common.Tag;
import com.baidubce.services.bci.model.container.Container;
import com.baidubce.services.bci.model.instance.CreateInstanceRequest;
import com.baidubce.services.bci.model.instance.CreateInstanceResponse;
import com.baidubce.services.bci.model.instance.GetInstanceResponse;
import com.baidubce.services.bci.model.instance.GetInstanceResponse;
import com.baidubce.services.bci.model.instance.ListInstancesRequest;
import com.baidubce.services.bci.model.volume.Volume;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The BciClientTest of bci
 */
public class BciClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BciClientTest.class);
    private static final String BCI_AK = "2e1be1eb99e946c3a543ec5a4eaa7d39-gztest-ak";
    private static final String BCI_SK = "2e1be1eb99e946c3a543ec5a4eaa7d39-gztest-sk";

    private static final String BCI_ENDPOINT = "http://10.164.104.144:8784";

    private static final String ZONE_NAME = "zoneC";

    private static final String SUBNET_ID = "sbn-r107hfd7a45n";

    private static final String SECURITY_GROUP_ID = "g-59gf44p4jmwe";

    protected BciClient bciClient;

    private String instanceId = "p-eaedblr7";

    @Before
    public void setUp() {
        BciClientConfiguration config = new BciClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(BCI_AK, BCI_SK));
        config.setEndpoint(BCI_ENDPOINT);
        bciClient = new BciClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
//            LOGGER.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
            System.out.println(String.format("[%s]==>%s", method, JsonUtils.toJsonPrettyString(object)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createInstanceTest() {
        CreateInstanceRequest createInstanceRequest = new CreateInstanceRequest();
        createInstanceRequest.setName("test-instance-01");

        // sandbox
        createInstanceRequest.setZoneName(ZONE_NAME);
        createInstanceRequest.setSecurityGroupIds(new ArrayList<String>()).getSecurityGroupIds().add(SECURITY_GROUP_ID);
        createInstanceRequest.setSubnetIds(new ArrayList<String>()).getSubnetIds().add(SUBNET_ID);

        createInstanceRequest.setRestartPolicy("Always");
        createInstanceRequest.setAutoCreateEip(false);
        createInstanceRequest.setTags(new ArrayList<Tag>()).getTags().add(new Tag("tagkey", "tagvalue"));
        createInstanceRequest.setImageRegistryCredentials(new ArrayList<ImageRegistryCredential>()).getImageRegistryCredentials().add(new ImageRegistryCredential(
                "docker.io/wywcoder", "wywcoder", "Qaz123456"
        ));
        createInstanceRequest.setVolume(new Volume());

        Container container = new Container();
        container.setName("container01");
        container.setImage("registry.baidubce.com/bci-zjm-public/ubuntu:18.04");
        container.setMemory((float) 0.25);
        container.setCpu((float) 0.25);
        container.setWorkingDir("");
        container.setImagePullPolicy("Always");
        Collections.addAll(container.setCommands(new ArrayList<String>()).getCommands(), "/bin/sh", "-c", "sleep 36000 && exit 0");
        container.setPorts(new ArrayList<Port>()).getPorts().add(new Port(80, "TCP", "myport"));
        container.setEnvironmentVars(new ArrayList<Environment>()).getEnvironmentVars().add(new Environment("envkey", "envvalue"));
        createInstanceRequest.setContainers(new ArrayList<Container>()).getContainers().add(container);
        CreateInstanceResponse createInstanceResponse = bciClient.createInstance(createInstanceRequest);
        instanceId = createInstanceResponse.getInstanceId();
        toJsonPrettyString("create bci instance Results", createInstanceResponse);
    }

    @Test
    public void deleteInstanceTest() {
        toJsonPrettyString("delete bci instance Results", bciClient.deleteInstance(instanceId, true));
    }

    @Test
    public void getInstanceTest() {
        toJsonPrettyString("get bci instance Results", bciClient.getInstance(instanceId));
    }

    @Test
    public void listInstancesTest() {
        toJsonPrettyString("list bci instances Results", bciClient.listInstances());
    }

    @Test
    public void listInstancesWithMarkerTest() {
        ListInstancesRequest request = new ListInstancesRequest();
        request.setMarker("p-jtawir02");
        toJsonPrettyString("list bci instances Results", bciClient.listInstances(request));
    }
}
