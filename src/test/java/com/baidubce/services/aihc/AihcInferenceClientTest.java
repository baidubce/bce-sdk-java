package com.baidubce.services.aihc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.model.inference.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AihcInferenceClient unit test
 */
public class AihcInferenceClientTest {
    private static final Logger logger = LoggerFactory.getLogger(AihcInferenceClientTest.class);
    private static final String AK = "";
    private static final String SK = "";
    private static final String region = "";

    protected AihcInferenceClient aihcInferenceClient;
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("");
        config.setProtocol(Protocol.HTTPS);
        aihcInferenceClient = new AihcInferenceClient(config);
    }

    @Test
    public void createAppTest() {
        CreateAppRequest request = new CreateAppRequest();
        request.setAppName("test");
        request.setChipType("test");
        request.setInsCount(1);
        request.setResPool(new CreateAppRequest.ResPoolConf("test", "test"));
        request.setStorage(new CreateAppRequest.StorageConf(1, null));
        request.setAccess(new CreateAppRequest.AccessConf(false, ""));
        request.setLog(new CreateAppRequest.LogConf(false));
        request.setDeploy(new CreateAppRequest.DeployConf(null));

        Map<String, String> podLabels = new HashMap<String, String>();
        podLabels.put("test", "");
        request.setMisc(new CreateAppRequest.Misc(podLabels, null));

        List<String> runCmd = new ArrayList<String>();
        runCmd.add("/bin/sh");
        runCmd.add("-c");
        runCmd.add("test");

        Map<String, String> env = new HashMap<>();
        env.put("test", "test");

        List<CreateAppRequest.ContainerConf> containers = new ArrayList<>();
        containers.add(new CreateAppRequest.ContainerConf("inference", 1, 16, 1, runCmd, null, null, env,
                new CreateAppRequest.ImageConf(2, "test", "", ""),
                null, null, null, null));
        request.setContainers(containers);

        CreateAppResponse response = aihcInferenceClient.createApp(request, region);
    }

    @Test
    public void listAppTest() {
        ListAppRequest request = new ListAppRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        request.setKeyword("test");
        request.setOrder("desc");
        request.setOrderBy("ctime");

        ListAppResponse response = aihcInferenceClient.listApp(request, region);
    }

    @Test
    public void listStatsTest() {
        ListStatsRequest request = new ListStatsRequest();

        List<String> appIds = new ArrayList<String>();
        appIds.add("test");
        request.setAppIds(appIds);

        ListStatsResponse response = aihcInferenceClient.listStats(request, region);
    }

    @Test
    public void appDetailsTest() {
        AppDetailsRequest request = new AppDetailsRequest();
        request.setAppId("test");

        AppDetailsResponse response = aihcInferenceClient.appDetails(request, region);
    }

    @Test
    public void updateAppTest() {
        UpdateAppRequest request = new UpdateAppRequest();
        request.setAppId("test");
        request.setShortDesc("test");

        request.setAppName("test");
        request.setChipType("test");
        request.setInsCount(1);
        request.setResPool(new CreateAppRequest.ResPoolConf("test", "test"));
        request.setStorage(new CreateAppRequest.StorageConf(1, null));
        request.setAccess(new CreateAppRequest.AccessConf(false, ""));
        request.setLog(new CreateAppRequest.LogConf(false));
        request.setDeploy(new CreateAppRequest.DeployConf(null));

        Map<String, String> podLabels = new HashMap<String, String>();
        podLabels.put("test", "");
        request.setMisc(new CreateAppRequest.Misc(podLabels, null));

        List<String> runCmd = new ArrayList<String>();
        runCmd.add("/bin/sh");
        runCmd.add("-c");
        runCmd.add("test");

        Map<String, String> env = new HashMap<>();
        env.put("test", "test");

        List<CreateAppRequest.ContainerConf> containers = new ArrayList<>();
        containers.add(new CreateAppRequest.ContainerConf("inference", 1, 16, 1, runCmd, null, null, env,
                new CreateAppRequest.ImageConf(2, "test", "", ""),
                null, null, null, null));
        request.setContainers(containers);

        UpdateAppResponse response = aihcInferenceClient.updateApp(request, region);
    }

    @Test
    public void scaleAppTest() {
        ScaleAppRequest request = new ScaleAppRequest();

        request.setAppId("test");
        request.setInsCount(0);

        ScaleAppResponse response = aihcInferenceClient.scaleApp(request, region);
    }

    @Test
    public void pubAccessTest() {
        PubAccessRequest request = new PubAccessRequest();
        request.setAppId("test");
        request.setPublicAccess(true);
        request.setEip("test");

        PubAccessResponse response = aihcInferenceClient.pubAccess(request, region);
    }

    @Test
    public void listChangeTest() {
        ListChangeRequest request = new ListChangeRequest();
        request.setAppId("test");
        request.setOrder("desc");
        request.setOrderBy("ctime");
        request.setPageNo(1);
        request.setPageSize(10);
        request.setChangeType(1);

        ListChangeResponse response = aihcInferenceClient.listChange(request, region);
    }

    @Test
    public void appChangeDetailTest() {
        AppChangeDetailRequest request = new AppChangeDetailRequest();
        request.setChangeId("test");

        AppChangeDetailResponse response = aihcInferenceClient.appChangeDetail(request, region);
    }

    @Test
    public void deleteAppTest() {
        DeleteAppRequest request = new DeleteAppRequest();
        request.setAppId("test");

        DeleteAppResponse response = aihcInferenceClient.deleteApp(request, region);
    }

    @Test
    public void listPodTest() {
        ListPodRequest request = new ListPodRequest();
        request.setAppId("test");

        ListPodResponse response = aihcInferenceClient.listPod(request, region);
    }

    @Test
    public void blockPodTest() {
        BlockPodRequest request = new BlockPodRequest();
        request.setAppId("test");
        request.setInsID("test");
        request.setBlock(true);

        BlockPodResponse response = aihcInferenceClient.blockPod(request, region);
    }

    @Test
    public void deletePodTest() {
        DeletePodRequest request = new DeletePodRequest();
        request.setAppId("test");
        request.setInsID("test");

        DeletePodResponse response = aihcInferenceClient.deletePod(request, region);
    }

    @Test
    public void listResPoolBriefTest() {
        ListResPoolBriefRequest request = new ListResPoolBriefRequest();

        request.setPageNo(1);
        request.setPageSize(10);

        ListResPoolBriefResponse response = aihcInferenceClient.listResPoolBrief(request, region);
    }

    @Test
    public void resPoolDetailTest() {
        ResPoolDetailRequest request = new ResPoolDetailRequest();

        request.setResPoolId("test");

        ResPoolDetailResponse response = aihcInferenceClient.resPoolDetail(request, region);
    }
}

