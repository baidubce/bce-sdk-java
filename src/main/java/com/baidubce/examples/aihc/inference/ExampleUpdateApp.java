package com.baidubce.examples.aihc.inference;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcInferenceClient;
import com.baidubce.services.aihc.model.inference.CreateAppRequest;
import com.baidubce.services.aihc.model.inference.UpdateAppRequest;
import com.baidubce.services.aihc.model.inference.UpdateAppResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleUpdateApp {
    public static void main(String[] args) {
        String ak = "Your AK";
        String sk = "Your SK";
        String endpoint = "aihc.baidubce.com";
        String region = "bj";

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        AihcInferenceClient client = new AihcInferenceClient(config);

        UpdateAppRequest request = new UpdateAppRequest();
        request.setAppId("test");
        request.setShortDesc("test");

        request.setAppName("test");
        request.setChipType("test");
        request.setInsCount(1);

        request.setResPool(new CreateAppRequest.ResPoolConf("test", "test"));

        List<CreateAppRequest.VolumnConf> volumns = new ArrayList<>();
        volumns.add(new CreateAppRequest.VolumnConf("", "",
                new CreateAppRequest.PFSConfig(""),
                new CreateAppRequest.HostPathConfig("")));
        request.setStorage(new CreateAppRequest.StorageConf(16, volumns));

        request.setAccess(new CreateAppRequest.AccessConf(false, ""));

        request.setLog(new CreateAppRequest.LogConf(true));

        request.setDeploy(new CreateAppRequest.DeployConf(new CreateAppRequest.CanaryStrategyConf(25, 25)));

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
        List<CreateAppRequest.VolumnMountConf> volumeMounts = new ArrayList<>();
        volumeMounts.add(new CreateAppRequest.VolumnMountConf("", "", true));

        List<String> command = new ArrayList<>();
        command.add("/bin/sh");

        containers.add(new CreateAppRequest.ContainerConf("inference", 1, 16, 1, runCmd, null, null, env,
                new CreateAppRequest.ImageConf(2, "registry.baidubce.com/test/test:test", "", ""),
                volumeMounts,
                new CreateAppRequest.ProbeConf(1,1,1,1,1, new CreateAppRequest.ProbeHandlerConf(
                        new CreateAppRequest.ExecAction(command), new CreateAppRequest.HTTPGetAction("", 2000), new CreateAppRequest.TCPSocketAction(2000)
                )),
                null, null));

        request.setContainers(containers);

        try {
            UpdateAppResponse response = client.updateApp(request, region);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
