package com.baidubce.services.bec;

import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bec.model.Backends;
import com.baidubce.services.bec.model.ListRequest;
import com.baidubce.services.bec.model.blb.CreateBecBlbBindingRequest;
import com.baidubce.services.bec.model.blb.CreateBecBlbBindingResponse;
import com.baidubce.services.bec.model.blb.CreateBecBlbMonitorPortRequest;
import com.baidubce.services.bec.model.blb.CreateBecBlbMonitorPortResponse;
import com.baidubce.services.bec.model.blb.CreateBecBlbRequest;
import com.baidubce.services.bec.model.blb.CreateBecBlbResponse;
import com.baidubce.services.bec.model.blb.DeleteBecBlbRequest;
import com.baidubce.services.bec.model.blb.DeleteBecBlbResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendBindingStsListRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendBindingStsListResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendPodListRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendPodListResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbBindingPodListWithStsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbBindingPodListWithStsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbInstanceRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbInstanceResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortDetailsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortDetailsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortListRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortListResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbResourceMetricsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbResourceMetricsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbsResponse;
import com.baidubce.services.bec.model.blb.HealthCheck;
import com.baidubce.services.bec.model.blb.Port;
import com.baidubce.services.bec.model.blb.UpdateBecBlbBindPodWeightRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbBindPodWeightResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbMonitorPortRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbMonitorPortResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbResponse;
import com.baidubce.services.bec.model.overview.GetBecContainerMetricsRequest;
import com.baidubce.services.bec.model.overview.GetBecContainerMetricsResponse;
import com.baidubce.services.bec.model.overview.GetBecContainerSummaryRequest;
import com.baidubce.services.bec.model.overview.GetBecContainerSummaryResponse;
import com.baidubce.services.bec.model.overview.GetBecResourceSummaryRequest;
import com.baidubce.services.bec.model.overview.GetBecResourceSummaryResponse;
import com.baidubce.services.bec.model.overview.GetBecVMSummaryRequest;
import com.baidubce.services.bec.model.overview.GetBecVMSummaryResponse;
import com.baidubce.services.bec.model.overview.GetBecVmMetricsRequest;
import com.baidubce.services.bec.model.overview.GetBecVmMetricsResponse;
import com.baidubce.services.bec.model.overview.GetBecVmNodeLevelMetricsRequest;
import com.baidubce.services.bec.model.overview.GetBecVmNodeLevelMetricsResponse;
import com.baidubce.services.bec.model.purchase.DeploymentInstance;
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesResponse;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesResponse;
import com.baidubce.services.bec.model.vm.DnsConfig;
import com.baidubce.services.bec.model.vm.KeyConfig;
import com.baidubce.services.bec.model.vm.NetworkConfig.Networks;
import com.baidubce.services.bec.model.vm.SystemVolumeConfig;
import com.baidubce.services.bec.model.vm.VolumeConfig;
import com.baidubce.services.bec.model.vm.instance.DeleteBecVmInstanceRequest;
import com.baidubce.services.bec.model.vm.instance.DeleteBecVmInstanceResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecNodeVmInstanceListRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecNodeVmInstanceListResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVirtualMachineRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVirtualMachineResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVmConfigRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVmConfigResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceListRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceListResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceMetricsRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceMetricsResponse;
import com.baidubce.services.bec.model.vm.instance.OperateBecVmDeploymentRequest;
import com.baidubce.services.bec.model.vm.instance.OperateBecVmDeploymentResponse;
import com.baidubce.services.bec.model.vm.instance.ReinstallBecVmInstanceRequest;
import com.baidubce.services.bec.model.vm.instance.ReinstallBecVmInstanceResponse;
import com.baidubce.services.bec.model.vm.instance.UpdateBecVmDeploymentRequest;
import com.baidubce.services.bec.model.vm.instance.UpdateBecVmDeploymentResponse;
import com.baidubce.services.bec.model.vm.network.VmNetworkConfig;
import com.baidubce.services.bec.model.vm.service.BecVmServiceActionRequest;
import com.baidubce.services.bec.model.vm.service.BecVmServiceActionResponse;
import com.baidubce.services.bec.model.vm.service.CreateBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.CreateBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.service.DelBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.DelBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceMetricsRequest;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceMetricsResponse;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.service.GetBecVmServicesRequest;
import com.baidubce.services.bec.model.vm.service.GetBecVmServicesResponse;
import com.baidubce.services.bec.model.vm.service.UpdateBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.UpdateBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateRequest;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateRequest.GpuRequest;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Objects;
import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * test class for testing bec service
 */
@RunWith(Enclosed.class)
public class BecClientTest {

    @Ignore
    public static class BecBase {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final Logger logger = LoggerFactory.getLogger(BecClientTest.class);
        protected final String ak = "your ak";
        protected final String sk = "your sk";
        protected static String endpoint = "http://bec.baidubce.com";

        protected BecClientConfiguration config;

        public void setUp() {
            this.config = new BecClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials(ak, sk));
            this.config.setProtocol(Protocol.HTTP);
            this.config.setEndpoint(endpoint);
        }

        public void tearDown() {
            // do something
            logger.info("Base test tearDown");
        }

        public void toJsonPrettyString(String method, Object object) {
            try {
                logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        protected void expectBceServiceException(final int statusCode, final String errorCode) {
            this.thrown.expect(new CustomMatcher<Throwable>(
                    "BceServiceException [ statusCode=" + statusCode + ", errorCode=" + errorCode + "]") {
                @Override
                public boolean matches(Object item) {
                    return (item instanceof BceServiceException)
                            && ((BceServiceException) item).getStatusCode() == statusCode
                            && Objects.equal(((BceServiceException) item).getErrorCode(), errorCode);
                }
            });
        }

    }

    /**
     * Test case about vm service begin
     */
    public static class VmServiceTest extends BecBase {
        protected BecClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BecClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }


        @Test
        public void createBecVmServiceTest() {
            SystemVolumeConfig systemVolume = new SystemVolumeConfig();
            systemVolume.setVolumeType("SATA");
            systemVolume.setSizeInGB(45);
            systemVolume.setName("test-system-disk");

            DeploymentInstance deploymentInstance = new DeploymentInstance();
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            deploymentInstance.setRegion("EAST_CHINA");
            deploymentInstance.setCity("HANGZHOU");
            deploymentInstance.setServiceProvider("CHINA_MOBILE");
            deploymentInstance.setReplicas(1);
            deployInstances.add(deploymentInstance);

            CreateBecVmServiceRequest request = CreateBecVmServiceRequest.builder()
                    .serviceName("test-bec-sdk")
                    .needPublicIp(true)
                    .bandwidth(100)
                    .disableIntranet(false)
                    .disableCloudInit(false)
                    .cpu(1)
                    .memory(2)
                    .imageId("m-f0ZRR9qB")
                    .imageType("bec")
                    .paymentMethod("postpay")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .systemVolume(systemVolume)
                    .deployInstances(deployInstances)
                    .build();
            CreateBecVmServiceResponse response = client.createBecVmService(request);
            toJsonPrettyString("create bec vm service", response);
        }

        @Test
        public void createBecVmServiceTest1() {
            SystemVolumeConfig systemVolume = new SystemVolumeConfig();
            systemVolume.setVolumeType("NVME");
            systemVolume.setSizeInGB(45);
            systemVolume.setName("test-system-disk");

            DeploymentInstance deploymentInstance = new DeploymentInstance();
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            deploymentInstance.setRegionId("cn-hangzhou-cm");
            deploymentInstance.setReplicas(1);
            deploymentInstance.setNetworkType("vpc");
            deploymentInstance.setVpcId("vpc-53tdyo1l");
            deploymentInstance.setSubnetId("sbn-gjdefdk7v9tz");
            deployInstances.add(deploymentInstance);

            CreateBecVmServiceRequest request = CreateBecVmServiceRequest.builder()
                    .serviceName("test-bec-sdk")
                    .needPublicIp(true)
                    .bandwidth(100)
                    .disableIntranet(false)
                    .disableCloudInit(false)
                    .spec("bec.g2.c1m4")
                    .imageId("m-f0ZRR9qB")
                    .imageType("bec")
                    .paymentMethod("postpay")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .systemVolume(systemVolume)
                    .deployInstances(deployInstances)
                    .hostname("hostnametest")
                    .deploysetIdList(Collections.singletonList("dset-zze4apk5"))
                    .needIpv6PublicIp(true)
                    .build();
            CreateBecVmServiceResponse response = client.createBecVmService(request);
            toJsonPrettyString("create bec vm service", response);
        }

        @Test
        public void getBecVmServicesTest() {
            GetBecVmServicesRequest request = new GetBecVmServicesRequest();
            GetBecVmServicesResponse response = client.getBecVmServices(request);
            toJsonPrettyString("get bec vm service list", response);
        }

        @Test
        public void updateBecVmServiceTest() {
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            DeploymentInstance deploymentInstance = new DeploymentInstance();
            deploymentInstance.setRegion("EAST_CHINA");
            deploymentInstance.setReplicas(2);
            deploymentInstance.setServiceProvider("CHINA_TELECOM");
            deploymentInstance.setCity("SHANGHAI");
            deployInstances.add(deploymentInstance);

            UpdateBecVmServiceRequest request = UpdateBecVmServiceRequest.builder()
                    .serviceId("s-rexnpd6k")
                    .serviceName("test-bec")
                    .type("replicas")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .deployInstances(deployInstances)
                    .build();
            UpdateBecVmServiceResponse response = client.updateBecVmService(request);
            toJsonPrettyString("update bec vm service", response);
        }

        @Test
        public void updateBecVmServiceTest1() {
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            DeploymentInstance deploymentInstance = new DeploymentInstance();
            deploymentInstance.setRegionId("cn-hangzhou-cm");
            deploymentInstance.setReplicas(3);
            deployInstances.add(deploymentInstance);

            UpdateBecVmServiceRequest request = UpdateBecVmServiceRequest.builder()
                    .serviceId("s-mduwnsq2")
                    .serviceName("test-bec-sdk")
                    .type("replicas")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .deployInstances(deployInstances)
                    .spec("bec")
                    .hostname("hostnametest11111")
                    .build();
            UpdateBecVmServiceResponse response = client.updateBecVmService(request);
            toJsonPrettyString("update bec vm service", response);
        }

        @Test
        public void getBecVmServiceTest() {
            GetBecVmServiceRequest request = new GetBecVmServiceRequest();
            request.setServiceId("s-rexnpd6k");
            GetBecVmServiceResponse response = client.getBecVmService(request);
            toJsonPrettyString("get bec vm service", response);
        }

        @Test
        public void becVmServiceActionTest() {
            BecVmServiceActionRequest request = new BecVmServiceActionRequest();
            request.setAction("start");
            request.setServiceId("s-rexnpd6k");
            BecVmServiceActionResponse response = client.becVmServiceAction(request);
            toJsonPrettyString("start/stop/release a bec vm service", response);
        }


        @Test
        public void delBecVmServiceTest() {
            DelBecVmServiceRequest request = new DelBecVmServiceRequest();
            request.setServiceId("s-rexnpd6k");
            DelBecVmServiceResponse response = client.delBecVmService(request);
            toJsonPrettyString("delete the bec vm service", response);
        }

        @Test
        public void getBecVmServiceMetricsTest() {
            GetBecVmServiceMetricsRequest request = new GetBecVmServiceMetricsRequest();
            request.setType("CPU");
            request.setServiceId("s-gxblzx8w");
            request.setOffsetInSeconds(3600);
            request.setStepInMin(5);

            GetBecVmServiceMetricsResponse response = client.getBecVmServiceMetrics(request);
            toJsonPrettyString("get bec service metrics", response);
        }

        @Test
        public void createBecVmServiceInstances() {
            SystemVolumeConfig systemVolume = new SystemVolumeConfig();
            systemVolume.setVolumeType("SATA");
            systemVolume.setSizeInGB(45);
            systemVolume.setName("test-system-disk");

            DeploymentInstance deploymentInstance = new DeploymentInstance();
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            deploymentInstance.setRegion("EAST_CHINA");
            deploymentInstance.setCity("HANGZHOU");
            deploymentInstance.setServiceProvider("CHINA_MOBILE");
            deploymentInstance.setReplicas(1);
            deploymentInstance.setNetworkType("vpc");
            deployInstances.add(deploymentInstance);

            KeyConfig keyConfig = new KeyConfig();
            keyConfig.setType("bccKeyPair");
            keyConfig.setBccKeyPairIdList(Arrays.asList("k-r4aaM6es"));

            List<Networks> networksList = new ArrayList<Networks>();
            Networks networks = new Networks();
            networks.setNetName("aaa");
            networks.setNetType("INTERNAL_IP");

            Networks networks1 = new Networks();
            networks1.setNetName("bbb");
            networks1.setNetType("PUBLIC_IP");

            networksList.add(networks);
            networksList.add(networks1);

            CreateBecVmServiceRequest request = CreateBecVmServiceRequest.builder()
                .serviceId("s-rouoaahf")
                .serviceName("test")
                .needPublicIp(true)
                .bandwidth(100)
                .disableIntranet(false)
                .disableCloudInit(false)
                .spec("bec.g2.c1m4")
                .imageId("m-f0ZRaaqB")
                .imageType("bec")
                .paymentMethod("postpay")
                .systemVolume(systemVolume)
                .deployInstances(deployInstances)
                .needIpv6PublicIp(true)
                .vmName("test")
                .keyConfig(keyConfig)
                .networksList(networksList)
                .securityGroupIds(Arrays.asList("sg-mkaivxlw"))
                .build();

            CreateBecVmServiceResponse response = client.createBecVmServiceInstances(request);
            toJsonPrettyString("get bec service metrics", response);
        }
    }

    /**
     * Test case about vm service end
     */


    /**
     * Test case about vm instance end
     */
    public static class VmInstance extends BecBase {
        protected BecClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BecClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void getBecVmInstanceListTest() {
            GetBecVmInstanceListRequest request = new GetBecVmInstanceListRequest();
            GetBecVmInstanceListResponse response = client.getBecVmInstanceList(request);
            toJsonPrettyString("get bec vm instance list", response);
        }

        @Test
        public void getBecNodeVmInstanceListTest() {
            GetBecNodeVmInstanceListRequest request = new GetBecNodeVmInstanceListRequest();
            ListRequest listRequest = new ListRequest();
            request.setRegion("EAST_CHINA");
            request.setServiceProvider("CHINA_TELECOM");
            request.setCity("SHANGHAI");
            request.setListRequest(listRequest);
            GetBecNodeVmInstanceListResponse response = client.getBecNodeVmInstanceList(request);
            toJsonPrettyString("get node bec vm instance list", response);

        }

        @Test
        public void getBecVirtualMachineTest() {
            GetBecVirtualMachineRequest request = new GetBecVirtualMachineRequest();
            request.setVmID("vm-mduwnsq2-cn-hangzhou-cm-sa5tg");
            GetBecVirtualMachineResponse response = client.getBecVirtualMachine(request);
            toJsonPrettyString("get bec vm instance details", response);
        }

        @Test
        public void deleteBecVmInstanceTest() {
            DeleteBecVmInstanceRequest request = new DeleteBecVmInstanceRequest();
            request.setVmID("vm-zdmygfxe-cn-hangzhou-cm-hq6l0");
            DeleteBecVmInstanceResponse response = client.deleteBecVmInstance(request);
            toJsonPrettyString("delete a instance", response);
        }

        @Test
        public void updateBecVmDeploymentTest() {
            UpdateBecVmDeploymentRequest request = new UpdateBecVmDeploymentRequest();

            request.setType("resource");
            request.setVmID("vm-9dtvctrq-cn-hangzhou-cm-6iz1h");

            VmNetworkConfig networkConfig = new VmNetworkConfig();
            List<Networks> networksList = new ArrayList<Networks>();
            Networks networks = new Networks();
            networks.setNetName("test");
            networks.setNetType("INTERNAL_IP");
            networksList.add(networks);
            networkConfig.setNetworksList(networksList);
            networkConfig.setNodeType("SINGLE");
            request.setNetworkConfig(networkConfig);

            request.setHostname("test");
            request.setNeedIpv6PublicIp(true);

            UpdateBecVmDeploymentResponse response = client.updateBecVmDeployment(request);
            toJsonPrettyString("update a bec vm", response);
        }

        @Test
        public void reinstallBecVmInstanceTest() {
            ReinstallBecVmInstanceRequest request = new ReinstallBecVmInstanceRequest();
            request.setVmID("vm-4ndo38ct-1-t-shanghai-nzak1");
            request.setAdminPass("SYDuVCJW%rjrS9W5+q9@");
            request.setImageId("m-fOH8SMW7");
            ReinstallBecVmInstanceResponse response = client.reinstallBecVmInstance(request);
            toJsonPrettyString("reinstall a bec vm", response);
        }

        @Test
        public void operateBecVmDeploymentTest() {
            OperateBecVmDeploymentRequest request = new OperateBecVmDeploymentRequest();
            request.setVmID("vm-kikhfpbw-1-t-shanghai-hvd3t");
            request.setAction("start");
            OperateBecVmDeploymentResponse response = client.operateBecVmDeployment(request);
            toJsonPrettyString("operate a bec vm instance", response);
        }

        @Test
        public void getBecVmInstanceMetricsTest() {
            GetBecVmInstanceMetricsRequest request = new GetBecVmInstanceMetricsRequest();
            request.setVmId("vm-gxblzx8w-cn-hangzhou-cm-gwwus");
            request.setType("cpu");
            request.setOffsetInSeconds(3600);
            request.setStepInMin(5);
            GetBecVmInstanceMetricsResponse response = client.getBecVmInstanceMetrics(request);
            toJsonPrettyString("get vm instance metrics", response);
        }

        @Test
        public void getBecVmConfigTest() {
            GetBecVmConfigRequest request = new GetBecVmConfigRequest();
            request.setVmID("vm-kikhfpbw-1-t-shanghai-hvd3t");
            GetBecVmConfigResponse response = client.getBecVmConfig(request);
            toJsonPrettyString("get bec vm config", response);
        }

    }

    /**
     * Test case about vm instance end
     */


    /**
     * Test case about blb begin
     */
    public static class BlbTest extends BecBase {
        protected BecClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BecClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void createBecBlbTest() {
            CreateBecBlbRequest request = CreateBecBlbRequest.builder()
                    .lbType("vm")
                    .paymentMethod("postpay")
                    .regionSelection("regional")
                    .region("EAST_CHINA")
                    .city("HANGZHOU")
                    .serviceProvider("CHINA_MOBILE")
                    .blbName("bec-test")
                    .needPublicIp(true)
                    .bandwidthInMbpsLimit(100)
                    .build();
            CreateBecBlbResponse response = client.createBecBlb(request);
            toJsonPrettyString("create bec blb Results", response);
        }

        @Test
        public void getBecBlbsTest() {
            GetBecBlbsRequest request = new GetBecBlbsRequest();
            request.setLbType("vm");
            request.setPageNo(1);
            request.setPageSize(10);
            GetBecBlbsResponse response = client.getBecBlbs(request);
            toJsonPrettyString("get blb list", response);
        }

        @Test
        public void getBecBlbTest() {
            GetBecBlbInstanceRequest request = new GetBecBlbInstanceRequest();
            request.setBlbId("lb-bootjoi9");
            GetBecBlbInstanceResponse response = client.getBecBlb(request);
            toJsonPrettyString("get bec blb instance", response);
        }

        @Test
        public void deleteBecBlbTest() {
            DeleteBecBlbRequest request = new DeleteBecBlbRequest();
            request.setBlbId("lb-bootjoi9");
            DeleteBecBlbResponse response = client.deleteBecBlb(request);
            toJsonPrettyString("delete the specific BLB instance", response);
        }

        @Test
        public void updateBecBlbTest() {
            UpdateBecBlbRequest request = new UpdateBecBlbRequest();
            request.setBlbId("lb-bootjoi9");
            request.setBlbName("bec-test-test-test");
            UpdateBecBlbResponse response = client.updateBecBlb(request);
            toJsonPrettyString("update the specific BLB instance", response);
        }

        @Test
        public void createBecBlbMonitorPortTest() {
            CreateBecBlbMonitorPortRequest request = new CreateBecBlbMonitorPortRequest();

            Port frontendPort = new Port();
            frontendPort.setPort(8083);
            frontendPort.setProtocol("TCP");

            HealthCheck healthCheck = new HealthCheck();
            healthCheck.setHealthCheckString("udp");
            healthCheck.setHealthyThreshold(3);
            healthCheck.setIntervalInSeconds(1);
            healthCheck.setTimeoutInSeconds(1);
            healthCheck.setUnhealthyThreshold(3);

            request.setBlbId("lb-kux6ezhx");
            request.setBackendPort(80);
            request.setFrontendPort(frontendPort);
            request.setHealthCheck(healthCheck);
            request.setLbMode("wrr");
            CreateBecBlbMonitorPortResponse response = client.createBecBlbMonitorPort(request);
            toJsonPrettyString("create Blb monitor port for assign blb", response);
        }

        @Test
        public void getBlbMonitorPortListTest() {
            GetBecBlbMonitorPortListRequest request = new GetBecBlbMonitorPortListRequest();
            request.setBlbId("lb-kux6ezhx");
            GetBecBlbMonitorPortListResponse response = client.getBlbMonitorPortList(request);
            toJsonPrettyString("get the Blb port monitor list for assign blb", response);

        }

        @Test
        public void getBecBlbMonitorPortDetailsTest() {
            GetBecBlbMonitorPortDetailsRequest request = new GetBecBlbMonitorPortDetailsRequest();
            request.setBlbId("lb-kux6ezhx");
            request.setPort(8083);
            request.setProtocol("TCP");
            GetBecBlbMonitorPortDetailsResponse response = client.getBecBlbMonitorPortDetails(request);
            toJsonPrettyString("get Blb monitor port details for assign blb and assign port", response);
        }

        @Test
        public void updateBecBlbMonitorPortTest() {
            UpdateBecBlbMonitorPortRequest request = new UpdateBecBlbMonitorPortRequest();

            Port frontendPort = new Port();
            frontendPort.setProtocol("TCP");
            frontendPort.setPort(8083);

            String lbMode = "wrr";

            HealthCheck healthCheck = new HealthCheck();
            healthCheck.setUnhealthyThreshold(3);
            healthCheck.setTimeoutInSeconds(1);
            healthCheck.setIntervalInSeconds(2);
            healthCheck.setHealthyThreshold(4);
            healthCheck.setHealthCheckString("wohao");

            request.setBlbId("lb-kux6ezhx");
            request.setHealthCheck(healthCheck);
            request.setBackendPort(8083);
            request.setFrontendPort(frontendPort);
            request.setLbMode(lbMode);

            UpdateBecBlbMonitorPortResponse response = client.updateBecBlbMonitorPort(request);
            toJsonPrettyString("update Blb monitor port for assign blb", response);
        }

        @Test
        public void getBecBlbBackendPodListTest() {
            GetBecBlbBackendPodListRequest request = new GetBecBlbBackendPodListRequest();
            request.setBlbId("lb-b9ppeguh");

            GetBecBlbBackendPodListResponse response = client.getBecBlbBackendPodList(request);
            toJsonPrettyString("get the bind Blb backend Pod/Vm list for assign blb", response);

        }

        @Test
        public void getBecBlbBackendBindingStsListTest() {
            GetBecBlbBackendBindingStsListRequest request = new GetBecBlbBackendBindingStsListRequest();
            request.setBlbId("lb-b9ppeguh");

            GetBecBlbBackendBindingStsListResponse response = client.getBecBlbBackendBindingStsList(request);
            toJsonPrettyString("get the binding Blb backend StatefulSet/VmReplicas list for assign blb", response);
        }

        @Test
        public void getBecBlbBindingPodListWithStsTest() {
            GetBecBlbBindingPodListWithStsRequest request = new GetBecBlbBindingPodListWithStsRequest();
            request.setBlbId("lb-b9ppeguh");
            request.setStsName("vmrs-4ndo38ct-1-t-shanghai");

            GetBecBlbBindingPodListWithStsResponse response = client.getBecBlbBindingPodListWithSts(request);
            toJsonPrettyString("Get the binding BEC blb backend Pod/Vm list for assign blb", response);
        }

        @Test
        public void createBecBlbBinding() {
            CreateBecBlbBindingRequest request = new CreateBecBlbBindingRequest();

            request.setBlbId("lb-b9ppeguh");
            request.setDeploymentId("vmrs-4ndo38ct-1-t-shanghai");
            request.setDefaultWeight(80);

            List<Backends> podWeight = new LinkedList<Backends>();
            Backends backends = new Backends();
            backends.setName("vm-4ndo38ct-1-t-shanghai-nzak1");
            backends.setWeight(20);
            podWeight.add(backends);
            request.setPodWeight(podWeight);

            CreateBecBlbBindingResponse response = client.createBecBlbBinding(request);
            toJsonPrettyString("Bind the backend StatefulSet/VmReplicas to the specified BEC blb", response);
        }

        @Test
        public void updateBecBlbBindPodWeight() {
            UpdateBecBlbBindPodWeightRequest request = new UpdateBecBlbBindPodWeightRequest();

            List<Backends> podWeightList = new ArrayList<Backends>();
            Backends backends = new Backends();
            backends.setWeight(100);
            backends.setName("vm-4ndo38ct-1-t-shanghai-nzak1");
            podWeightList.add(backends);

            request.setBlbId("lb-b9ppeguh");
            request.setPodWeightList(podWeightList);

            UpdateBecBlbBindPodWeightResponse response = client.updateBecBlbBindPodWeight(request);
            toJsonPrettyString("Modify the weight of the Pod/Vm bound to the specified BEC BLB backend", response);
        }

        @Test
        public void getBecBlbResourceMetricsTest() {
            GetBecBlbResourceMetricsRequest request = new GetBecBlbResourceMetricsRequest();
            request.setBlbId("applb-cn-hangzhou-cm-v3feadgv");
            request.setType("bandwidth_receive");
            request.setIpType("extranet");
            request.setOffsetInSeconds(3600);
            request.setStepInMin(5);

            GetBecBlbResourceMetricsResponse response = client.getBecBlbResourceMetrics(request);
            toJsonPrettyString("Get the BEC blb monitor metrics", response);
        }
    }

    /**
     * Test case about blb end
     */


    /**
     * Test case about overview begin
     */
    public static class OverviewTest extends BecBase {
        protected BecClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BecClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void getBecResourceSummaryTest() {
            GetBecResourceSummaryRequest request = new GetBecResourceSummaryRequest();
            GetBecResourceSummaryResponse response = client.getBecResourceSummary(request);
            toJsonPrettyString("Get the BEC user level overview data", response);
        }

        @Test
        public void getBecContainerSummaryTest() {
            GetBecContainerSummaryRequest request = new GetBecContainerSummaryRequest();
            GetBecContainerSummaryResponse response = client.getBecContainerSummary(request);
            toJsonPrettyString("Get overview information of container services", response);
        }

        @Test
        public void getBecVmSummaryTest() {
            GetBecVMSummaryRequest request = new GetBecVMSummaryRequest();
            GetBecVMSummaryResponse response = client.getBecVmSummary(request);
            toJsonPrettyString("Get overview information of vm services", response);
        }

        @Test
        public void getBecContainerMetricsTest() {
            GetBecContainerMetricsRequest request = new GetBecContainerMetricsRequest();
            request.setType("cpu");
            request.setOffsetInSeconds(3600);
            request.setStepInMin(5);
            GetBecContainerMetricsResponse response = client.getBecContainerMetrics(request);
            toJsonPrettyString("Get BEC user level container metrics", response);
        }

        @Test
        public void getBecVmMetricsTest() {
            GetBecVmMetricsRequest request = new GetBecVmMetricsRequest();
            request.setType("bandwidth_receive");
            request.setOffsetInSeconds(3600);
            request.setStepInMin(5);
            GetBecVmMetricsResponse response = client.getBecVmMetrics(request);
            toJsonPrettyString("Get BEC user level vm metrics", response);
        }

        @Test
        public void getVmNodeMetrics() {
            GetBecVmNodeLevelMetricsRequest request = new GetBecVmNodeLevelMetricsRequest();
            request.setStart(Long.valueOf(1661184000));
            request.setEnd(Long.valueOf(1661270400));
            request.setCity("HANGZHOU");
            request.setRegion("EAST_CHINA");
            request.setServiceProvider("CHINA_MOBILE");
            request.setType("BANDWIDTH_RECEIVE");
            request.setStepInMin(2);

            GetBecVmNodeLevelMetricsResponse response = client.getVmNodeMetrics(request);
            toJsonPrettyString("Get node level vm metrics", response);
        }
    }
    /**
     * Test case about overview end
     */

    /**
     * Test case about resource begin
     */
    public static class ResourceTest extends BecBase {

        protected BecClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BecClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void listBecServicePackagesTest() {
            ListBecServicePackagesRequest request = new ListBecServicePackagesRequest();
            request.setType("vm");
            ListBecServicePackagesResponse response = client.listBecServicePackages(request);
            toJsonPrettyString("List BEC service packages", response);
        }

        @Test
        public void listBecPassThroughDiskPackages() {
            ListBecPassThroughDiskPackagesRequest request = new ListBecPassThroughDiskPackagesRequest();
            ListBecPassThroughDiskPackagesResponse response = client.listBecPassThroughDiskPackages(request);
            toJsonPrettyString("List bec passThrough disk packages", response);
        }
    }

    /**
     * Test case about resource end
     */


    /**
     * Test case about template start
     */
    public static class TemplateTest extends BecBase {

        protected BecClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BecClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void createBecVmTemplate() {

            SystemVolumeConfig systemVolume = new SystemVolumeConfig();
            systemVolume.setName("sys");
            systemVolume.setSizeInGB(40);
            systemVolume.setVolumeType("NVME");

            List<VolumeConfig> dataVolumeList = new ArrayList<VolumeConfig>();
            VolumeConfig volumeConfig = new VolumeConfig();
            volumeConfig.setVolumeType("NVME");
            volumeConfig.setName("data0");
            volumeConfig.setSizeInGB(20);
            dataVolumeList.add(volumeConfig);

            DnsConfig dnsConfig = new DnsConfig();
            dnsConfig.setDnsType("DEFAULT");

            GpuRequest gpu = new GpuRequest();
            gpu.setType("nvidia.com/TU104GL_Tesla_T4");
            gpu.setNum(1);

            List<Networks> networksList = new ArrayList<Networks>();
            Networks networks = new Networks();
            networks.setNetType("INTERNAL_IP");
            networks.setNetName("test0");

            Networks networks1 = new Networks();
            networks1.setNetType("PUBLIC_IP");
            networks1.setNetName("test1");

            networksList.add(networks);
            networksList.add(networks1);

            CreateBecVmTemplateRequest request = new CreateBecVmTemplateRequest();
            request.setTemplateName("sdk-test");
            request.setBandwidth(100);
            request.setCpu(1);
            request.setMemory(2);
            request.setDataVolumeList(dataVolumeList);
            request.setDisableIntranet(false);
            request.setDnsConfig(dnsConfig);
            request.setGpu(gpu);
            request.setImageId("m-f0aRR9qB");
            request.setImageType("bec");
            request.setNeedIpv6PublicIp(false);
            request.setNeedPublicIp(true);
            request.setNetworksList(networksList);
            request.setPolicy("spec");
            request.setSecurityGroupIds(Arrays.asList("sg-mavivalw"));
            request.setSpec("bec.g4.c1m4");
            request.setSystemVolume(systemVolume);

            CreateBecVmTemplateResponse response = client.createBecVmTemplate(request);
            toJsonPrettyString("Create bec vm template", response);
        }

        @Test
        public void listBecVmTemplate() {
            GetBecVmTemplateListRequest request = new GetBecVmTemplateListRequest();

            request.setKeyword("test");
            request.setKeywordType("templateName");
            request.setOrderBy("config");
            request.setOrder("desc");

            GetBecVmTemplateListResponse response = client.listBecVmTemplate(request);
            toJsonPrettyString("List bec vm template", response);
        }

        @Test
        public void getBecVmTemplate() {
            GetBecVmTemplateRequest request = new GetBecVmTemplateRequest();
            request.setTemplateId("tmpl-xheaanr16");

            GetBecVmTemplateResponse response = client.getBecVmTemplate(request);
            toJsonPrettyString("Get bec vm template", response);
        }
    }
}
