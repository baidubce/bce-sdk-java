package com.baidubce.services.kafka;

import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.kafka.model.acl.CreateAclRequest;
import com.baidubce.services.kafka.model.acl.DeleteAclRequest;
import com.baidubce.services.kafka.model.acl.ListAclRequest;
import com.baidubce.services.kafka.model.cluster.Authentication;
import com.baidubce.services.kafka.model.cluster.Billing;
import com.baidubce.services.kafka.model.cluster.ConfigMeta;
import com.baidubce.services.kafka.model.cluster.CreateClusterRequest;
import com.baidubce.services.kafka.model.cluster.DecreaseBrokerCountRequest;
import com.baidubce.services.kafka.model.cluster.DeleteClusterRequest;
import com.baidubce.services.kafka.model.cluster.ExpandBrokerDiskCapacityRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterAccessEndpointsRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterConfigurationsRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterDetailRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterNodesRequest;
import com.baidubce.services.kafka.model.cluster.IncreaseBrokerCountRequest;
import com.baidubce.services.kafka.model.cluster.ListClustersRequest;
import com.baidubce.services.kafka.model.cluster.Mode;
import com.baidubce.services.kafka.model.cluster.Provisioned;
import com.baidubce.services.kafka.model.cluster.ResizeClusterEipBandwidthRequest;
import com.baidubce.services.kafka.model.cluster.StartClusterRequest;
import com.baidubce.services.kafka.model.cluster.StopClusterRequest;
import com.baidubce.services.kafka.model.cluster.StorageAutoDelete;
import com.baidubce.services.kafka.model.cluster.StorageMeta;
import com.baidubce.services.kafka.model.cluster.StoragePolicy;
import com.baidubce.services.kafka.model.cluster.StoragePolicyType;
import com.baidubce.services.kafka.model.cluster.StorageType;
import com.baidubce.services.kafka.model.cluster.SwitchClusterEipRequest;
import com.baidubce.services.kafka.model.cluster.Tag;
import com.baidubce.services.kafka.model.cluster.Type;
import com.baidubce.services.kafka.model.cluster.UpdateAccessConfigRequest;
import com.baidubce.services.kafka.model.cluster.UpdateBrokerNodeTypeRequest;
import com.baidubce.services.kafka.model.cluster.UpdateKafkaConfigRequest;
import com.baidubce.services.kafka.model.cluster.UpdateSecurityGroupRequest;
import com.baidubce.services.kafka.model.cluster.UpdateStoragePolicyRequest;
import com.baidubce.services.kafka.model.config.CreateClusterConfigRequest;
import com.baidubce.services.kafka.model.config.CreateClusterConfigRevisionRequest;
import com.baidubce.services.kafka.model.config.DeleteClusterConfigRequest;
import com.baidubce.services.kafka.model.config.GetClusterConfigRequest;
import com.baidubce.services.kafka.model.config.GetClusterConfigRevisionRequest;
import com.baidubce.services.kafka.model.config.GetClusterConfigRevisionResponse;
import com.baidubce.services.kafka.model.config.ListClusterConfigRevisionsRequest;
import com.baidubce.services.kafka.model.config.ListClusterConfigsRequest;
import com.baidubce.services.kafka.model.consumer.DeleteConsumerGroupRequest;
import com.baidubce.services.kafka.model.consumer.ListConsumerGroupRequest;
import com.baidubce.services.kafka.model.consumer.ListSubscribedTopicsRequest;
import com.baidubce.services.kafka.model.consumer.ResetConsumerGroupRequest;
import com.baidubce.services.kafka.model.job.CancelJobRequest;
import com.baidubce.services.kafka.model.job.GetJobDetailRequest;
import com.baidubce.services.kafka.model.job.GetOperationDetailRequest;
import com.baidubce.services.kafka.model.job.ListJobsRequest;
import com.baidubce.services.kafka.model.job.ResumeJobRequest;
import com.baidubce.services.kafka.model.job.StartJobRequest;
import com.baidubce.services.kafka.model.job.SuspendJobRequest;
import com.baidubce.services.kafka.model.topic.CreateTopicRequest;
import com.baidubce.services.kafka.model.topic.DeleteTopicRequest;
import com.baidubce.services.kafka.model.topic.GetSubscribedGroupDetailRequest;
import com.baidubce.services.kafka.model.topic.GetTopicDetailRequest;
import com.baidubce.services.kafka.model.topic.GetTopicPartitionDetailRequest;
import com.baidubce.services.kafka.model.topic.ListSubscribedGroupsRequest;
import com.baidubce.services.kafka.model.topic.ListTopicPartitionsRequest;
import com.baidubce.services.kafka.model.topic.ListTopicRequest;
import com.baidubce.services.kafka.model.topic.UpdateTopicRequest;
import com.baidubce.services.kafka.model.user.CreateUserRequest;
import com.baidubce.services.kafka.model.user.DeleteUserRequest;
import com.baidubce.services.kafka.model.user.ListUsersRequest;
import com.baidubce.services.kafka.model.user.ResetUserPasswordRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Objects;
import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * test class for testing kafka service
 */
@RunWith(Enclosed.class)
public class KafkaClientTest {

    @Ignore
    public static class KafkaBase {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final Logger logger = LoggerFactory.getLogger(KafkaClientTest.class);
        protected static String endpoint = "kafka.domain.name.baidu.com";
        protected final String ak = "ak";
        protected final String sk = "sk";

        protected KafkaClientConfiguration config;

        /**
         * Constants start
         */
        String clusteraName = "demo";
        String clusterId = "clusterId";
        List<String> couponIds = Arrays.asList("couponId1", "couponId2");
        String topicName = "testTopic";
        String groupName = "testGroup";
        Map<String, String> otherConfig = new LinkedHashMap<String, String>();

        String userName = "testUser";
        String passwd = "<PASSWORD>";

        String jobId = "jobId";
        String operationId = "operationId";

        /**
         * Constants end
         */

        public void setUp() {
            this.config = new KafkaClientConfiguration();
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
     * Test case about cluster begin
     */
    public static class ClusterTest extends KafkaBase {
        protected KafkaClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new KafkaClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void createClusterTest() {
            CreateClusterRequest request = new CreateClusterRequest();
            request.setName(clusteraName);
            request.setMode(Mode.HP);
            request.setType(Type.PROVISIONED);
            request.setTags(Arrays.asList(Tag.builder()
                    .tagKey("KAFKA-Cluster")
                    .tagValue("")
                    .build()
            ));
            // 构造预置模式对象
            Provisioned provisioned = new Provisioned();
            provisioned.setKafkaVersion("2.7.2");
            provisioned.setNodeType("kafka.g4.c2m8");
            provisioned.setNumberOfBrokerNodes(3);
            provisioned.setNumberOfBrokerNodesPerZone(3);
            provisioned.setStorageMeta(StorageMeta.builder()
                    .storageType(StorageType.ENHANCED_SSD_PL1)
                    .storageSize(100)
                    .numberOfDisk(1)
                    .build());
            provisioned.setDeploySetEnabled(true);
            provisioned.setBilling(Billing.builder()
                            .payment("Prepaid")
                            .timeLength(1)
                            .timeUnit("month")
                            .autoRenewEnabled(true)
                            .autoRenewTimeLength(1)
                            .autoRenewTimeUnit("month")
                            .couponIds(couponIds)
                            .isAutoPay(true)
                            .build());
            provisioned.setVpcId("vpc-tf3xqatke54b");
            provisioned.setSubnetIds(Arrays.asList("subnet-12kruawmu30u"));
            provisioned.setSecurityGroupIds(Arrays.asList("g-12kruawmu30u"));
            provisioned.setPublicIpEnabled(true);
            provisioned.setPublicIpBandwidth(1);
            provisioned.setIntranetIpEnabled(false);
            provisioned.setAclEnabled(true);
            provisioned.setAuthentications(Arrays.asList(
                    Authentication.builder().mode("NONE").build(),
                    Authentication.builder().mode("SASL_SCRAM").build()));
            // 集群配置参数
            Map<String, String> configContext = new LinkedHashMap<String, String>();
            configContext.put("auto.create.topics.enable", "true");
            configContext.put("log.retention.hours", "168");
            configContext.put("message.max.bytes", "8110080");
            provisioned.setConfigMeta(ConfigMeta.builder()
                            .context(configContext)
                    .build());
            request.setProvisioned(provisioned);
            this.client.createCluster(request);
        }

        @Test
        public void deleteClusterTest() {
            DeleteClusterRequest request = new DeleteClusterRequest();
            request.setClusterId(clusterId);
            this.client.deleteCluster(request);
        }

        @Test
        public void listClustersTest() {
            ListClustersRequest request = new ListClustersRequest();
            request.setMaxKeys(100);

            request.setClusterName("Kafka-");
            request.setState("ACTIVE");
            request.setMode("HP");
            request.setKafkaVersion("2.7.2");
            request.setPayment("Prepaid");
            request.setTagKey("KAFKA-Cluster");
            request.setTagValue("xxx");

            this.client.listClusters(request);
        }

        @Test
        public void getClusterDetailTest() {
            GetClusterDetailRequest request = new GetClusterDetailRequest();
            request.setClusterId(clusterId);
            this.client.getClusterDetail(request);
        }

        @Test
        public void getClusterAccessEndpointsTest() {
            GetClusterAccessEndpointsRequest request = new GetClusterAccessEndpointsRequest();
            request.setClusterId(clusterId);
            this.client.getClusterAccessEndpoints(request);
        }

        @Test
        public void getClusterNodesTest() {
            GetClusterNodesRequest request = new GetClusterNodesRequest();
            request.setClusterId(clusterId);
            request.setState("ALIVE");
            this.client.getClusterNodes(request);
        }

        @Test
        public void getClusterConfigurationsTest() {
            GetClusterConfigurationsRequest request = new GetClusterConfigurationsRequest();
            request.setClusterId(clusterId);
            this.client.getClusterConfigurations(request);
        }

        @Test
        public void increaseBrokerCountTest() {
            IncreaseBrokerCountRequest request = new IncreaseBrokerCountRequest();
            request.setClusterId(clusterId);
            // 期望变更后集群节点数为9
            request.setNumberOfBrokerNodes(9);
            request.setCouponIds(couponIds);
            request.setIsAutoPay(true);
            this.client.increaseBrokerCount(request);
        }

        @Test
        public void decreaseBrokerCountTest() {
            DecreaseBrokerCountRequest request = new DecreaseBrokerCountRequest();
            request.setClusterId(clusterId);
            // 期望变更后集群节点数为3
            request.setNumberOfBrokerNodes(3);
            this.client.decreaseBrokerCount(request);
        }

        @Test
        public void updateBrokerNodeTypeTest() {
            UpdateBrokerNodeTypeRequest request = new UpdateBrokerNodeTypeRequest();
            request.setClusterId(clusterId);
            request.setNodeType("kafka.g4.c2m8");
            request.setCouponIds(couponIds);
            request.setIsAutoPay(true);
            this.client.updateBrokerNodeType(request);
        }

        @Test
        public void expandBrokerDiskCapacityTest() {
            ExpandBrokerDiskCapacityRequest request = new ExpandBrokerDiskCapacityRequest();
            request.setClusterId(clusterId);
            // 期望变更后磁盘容量为1000G
            request.setStorageSize(1000L);
            request.setCouponIds(couponIds);
            request.setIsAutoPay(true);
            this.client.expandBrokerDiskCapacity(request);
        }

        @Test
        public void updateAccessConfigTest() {
            UpdateAccessConfigRequest request = new UpdateAccessConfigRequest();
            request.setClusterId(clusterId);
            request.setAclEnabled(false);
            request.setAuthentications(Arrays.asList(
                    Authentication.builder().mode("SASL_SCRAM").build()));
            this.client.updateAccessConfig(request);
        }

        @Test
        public void startClusterTest() {
            StartClusterRequest request = new StartClusterRequest();
            request.setClusterId(clusterId);
            this.client.startCluster(request);
        }

        @Test
        public void stopClusterTest() {
            StopClusterRequest request = new StopClusterRequest();
            request.setClusterId(clusterId);
            this.client.stopCluster(request);
        }

        @Test
        public void resizeClusterEipBandwidthTest() {
            ResizeClusterEipBandwidthRequest request = new ResizeClusterEipBandwidthRequest();
            request.setClusterId(clusterId);
            // 期望变更后带宽为100Mbps
            request.setPublicIpBandwidth(100);
            request.setCouponIds(couponIds);
            request.setIsAutoPay(true);
            this.client.resizeClusterEipBandwidth(request);
        }

        @Test
        public void switchClusterEipTest() {
            SwitchClusterEipRequest request = new SwitchClusterEipRequest();
            request.setClusterId(clusterId);
            // 期望变更后打开公网，且带宽为100Mbps
            request.setPublicIpEnabled(true);
            request.setPublicIpBandwidth(100);
            request.setCouponIds(couponIds);
            request.setIsAutoPay(true);
            this.client.switchClusterEip(request);
        }

        @Test
        public void updateStoragePolicyTest() {
            UpdateStoragePolicyRequest request = new UpdateStoragePolicyRequest();
            request.setClusterId(clusterId);
            request.setStoragePolicyEnabled(true);
            StoragePolicy storagePolicy = new StoragePolicy();
            storagePolicy.setType(StoragePolicyType.AUTO_DELETE);
            StorageAutoDelete autoDelete = new StorageAutoDelete();
            autoDelete.setDiskUsedThresholdPercent(95);
            autoDelete.setLogMinRetentionMs(3600000L);
            autoDelete.setLogMinRetentionBytes(1073741824L);
            storagePolicy.setAutoDelete(autoDelete);
            request.setStoragePolicy(storagePolicy);
            this.client.updateStoragePolicy(request);
        }

        @Test
        public void updateKafkaConfigTest() {
            UpdateKafkaConfigRequest request = new UpdateKafkaConfigRequest();
            request.setClusterId(clusterId);
            request.setConfigId("configId");
            request.setRevisionId(2);
            this.client.updateKafkaConfig(request);
        }

        @Test
        public void updateSecurityGroupTest() {
            UpdateSecurityGroupRequest request = new UpdateSecurityGroupRequest();
            request.setClusterId(clusterId);
            request.setSecurityGroupIds(Arrays.asList("g-1234567890"));
            this.client.updateSecurityGroup(request);
        }
    }
    /**
     * Test case about cluster end
     */

    /**
     * Test case about config begin
     */
    public static class ConfigTest extends KafkaBase {
        protected KafkaClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new KafkaClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void createClusterConfigTest() {
            CreateClusterConfigRequest request = new CreateClusterConfigRequest();
            request.setName("test");
            request.setDescription("test add config");
            Map<String, String> context = new LinkedHashMap<String, String>();
            context.put("auto.create.topics.enable", "true");
            request.setContext(context);
            this.client.createClusterConfig(request);
        }

        @Test
        public void listClusterConfigsTest() {
            ListClusterConfigsRequest request = new ListClusterConfigsRequest();
            request.setMaxKeys(100);

            request.setConfigName("test");
            request.setState("UNUSED");

            this.client.listClusterConfigs(request);
        }

        @Test
        public void deleteClusterConfigTest() {
            DeleteClusterConfigRequest request = new DeleteClusterConfigRequest();
            request.setConfigId("configId");
            this.client.deleteClusterConfig(request);
        }

        @Test
        public void getClusterConfigTest() {
            GetClusterConfigRequest request = new GetClusterConfigRequest();
            request.setConfigId("configId");
            this.client.getClusterConfig(request);
        }

        @Test
        public void createClusterConfigRevisionTest() {
            CreateClusterConfigRevisionRequest request = new CreateClusterConfigRevisionRequest();
            request.setConfigId("configId");
            request.setRevisionId(2);
            Map<String, String> context = new LinkedHashMap<String, String>();
            context.put("auto.create.topics.enable", "false");
            request.setContext(context);
            this.client.createClusterConfigRevision(request);
        }

        @Test
        public void listClusterConfigRevisionsTest() {
            ListClusterConfigRevisionsRequest request = new ListClusterConfigRevisionsRequest();
            request.setConfigId("configId");
            request.setState("UNUSED");
            this.client.listClusterConfigRevisions(request);
        }

        @Test
        public void getClusterConfigRevisionTest() {
            GetClusterConfigRevisionRequest request = new GetClusterConfigRevisionRequest();
            request.setConfigId("configId");
            request.setRevisionId(2);
            GetClusterConfigRevisionResponse response = this.client.getClusterConfigRevision(request);
            System.out.println(JsonUtils.toJsonString(response));
        }
    }
    /**
     * Test case about config end
     */

    /**
     * Test case about topic begin
     */
    public static class TopicTest extends KafkaBase {
        protected KafkaClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new KafkaClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void updateTopicTest() {
            UpdateTopicRequest request = new UpdateTopicRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            request.setPartitionNum(3);
            request.setOtherConfigs(otherConfig);
            this.client.updateTopic(request);
        }

        @Test
        public void getSubscribedGroupDetailTest() {
            GetSubscribedGroupDetailRequest request = new GetSubscribedGroupDetailRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            request.setGroupName(groupName);
            this.client.getSubscribedGroupDetail(request);
        }

        @Test
        public void listTopicPartitionsTest() {
            ListTopicPartitionsRequest request = new ListTopicPartitionsRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            request.setPageNo(2);
            request.setPageSize(10);
            this.client.listTopicPartitions(request);
        }

        @Test
        public void getTopicPartitionDetailTest() {
            GetTopicPartitionDetailRequest request = new GetTopicPartitionDetailRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            request.setPartitionId(0);
            this.client.getTopicPartitionDetail(request);
        }

        @Test
        public void listTopicTest() {
            ListTopicRequest request = new ListTopicRequest();
            request.setClusterId(clusterId);
            request.setTopicName("test-");
            this.client.listTopic(request);
        }

        @Test
        public void getTopicDetailTest() {
            GetTopicDetailRequest request = new GetTopicDetailRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            this.client.getTopicDetail(request);
        }

        @Test
        public void createTopicTest() {
            CreateTopicRequest request = new CreateTopicRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            request.setPartitionNum(10);
            request.setReplicationFactor(3);
            request.setOtherConfigs(otherConfig);
            this.client.createTopic(request);
        }

        @Test
        public void listSubscribedGroupsTest() {
            ListSubscribedGroupsRequest request = new ListSubscribedGroupsRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            this.client.listSubscribedGroups(request);
        }

        @Test
        public void deleteTopicTest() {
            DeleteTopicRequest request = new DeleteTopicRequest();
            request.setClusterId(clusterId);
            request.setTopicName(topicName);
            this.client.deleteTopic(request);
        }
    }
    /**
     * Test case about topic end
     */

    /**
     * Test case about consumer begin
     */
    public static class ConsumerTest extends KafkaBase {
        protected KafkaClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new KafkaClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void ListSubscribedTopicsTest() {
            ListSubscribedTopicsRequest request = new ListSubscribedTopicsRequest();
            request.setClusterId(clusterId);
            request.setGroupName(groupName);
            this.client.listSubscribedTopics(request);
        }

        @Test
        public void listConsumerGroupTest() {
            ListConsumerGroupRequest request = new ListConsumerGroupRequest();
            request.setClusterId(clusterId);
            this.client.listConsumerGroup(request);
        }

        @Test
        public void deleteConsumerGroupTest() {
            DeleteConsumerGroupRequest request = new DeleteConsumerGroupRequest();
            request.setClusterId(clusterId);
            request.setGroupName(groupName);
            this.client.deleteConsumerGroup(request);
        }

        @Test
        public void resetConsumerGroupTest() {
            ResetConsumerGroupRequest request = new ResetConsumerGroupRequest();
            request.setClusterId(clusterId);
            request.setGroupName(groupName);
            request.setTopicName(topicName);
            request.setPartitions(Arrays.asList(0, 1));
            request.setResetStrategy("tooffset");
            request.setResetValue("1024");
            this.client.resetConsumerGroup(request);
        }
    }
    /**
     * Test case about consumer end
     */

    /**
     * Test case about user begin
     */
    public static class UserTest extends KafkaBase {
        protected KafkaClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new KafkaClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void createUserTest() {
            CreateUserRequest request = new CreateUserRequest();
            request.setClusterId(clusterId);
            request.setUsername(userName);
            request.setPassword(passwd);
            this.client.createUser(request);
        }

        @Test
        public void deleteUserTest() {
            DeleteUserRequest request = new DeleteUserRequest();
            request.setClusterId(clusterId);
            request.setUsername(userName);
            this.client.deleteUser(request);
        }

        @Test
        public void resetUserPasswordTest() {
            ResetUserPasswordRequest request = new ResetUserPasswordRequest();
            request.setClusterId(clusterId);
            request.setUsername(userName);
            request.setPassword(passwd);
            this.client.resetUserPassword(request);
        }

        @Test
        public void listUsersTest() {
            ListUsersRequest request = new ListUsersRequest();
            request.setClusterId(clusterId);
            this.client.listUsers(request);
        }
    }
    /**
     * Test case about user end
     */

    /**
     * Test case about acl begin
     */
    public static class AclTest extends KafkaBase {
        protected KafkaClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new KafkaClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void createAclTest() {
            CreateAclRequest request = new CreateAclRequest();
            request.setClusterId(clusterId);
            request.setUsername(userName);
            request.setPatternType("PREFIXED");
            request.setResourceType("TOPIC");
            request.setResourceName("test-");
            request.setOperations(Arrays.asList("PRODUCE"));
            this.client.createAcl(request);
        }

        @Test
        public void deleteAclTest() {
            DeleteAclRequest request = new DeleteAclRequest();
            request.setClusterId(clusterId);
            request.setUsername(userName);
            request.setPatternType("PREFIXED");
            request.setResourceType("TOPIC");
            request.setResourceName("test-");
            request.setOperation("PRODUCE");
            this.client.deleteAcl(request);
        }

        @Test
        public void listAclsTest() {
            ListAclRequest request = new ListAclRequest();
            request.setClusterId(clusterId);
            request.setUsername(userName);
            request.setPatternType("PREFIXED");
            request.setResourceType("TOPIC");
            request.setResourceName("test-");
            this.client.listAcls(request);
        }
    }
    /**
     * Test case about acl end
     */

    /**
     * Test case about job begin
     */
    public static class JobTest extends KafkaBase {
        protected KafkaClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new KafkaClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void listJobsTest() {
            ListJobsRequest request = new ListJobsRequest();
            request.setClusterId(clusterId);
            request.setName("INCREASE_BROKER_COUNT");
            request.setMaxKeys(100);
            this.client.listJobs(request);
        }

        @Test
        public void getJobTest() {
            GetJobDetailRequest request = new GetJobDetailRequest();
            request.setClusterId(clusterId);
            request.setJobId(jobId);
            this.client.getJob(request);
        }

        @Test
        public void getOperationTest() {
            GetOperationDetailRequest request = new GetOperationDetailRequest();
            request.setClusterId(clusterId);
            request.setJobId(jobId);
            request.setOperationId(operationId);
            this.client.getOperation(request);
        }

        @Test
        public void startJobTest() {
            StartJobRequest request = new StartJobRequest();
            request.setClusterId(clusterId);
            request.setJobId(jobId);
            this.client.startJob(request);
        }

        @Test
        public void cancelJobTest() {
            CancelJobRequest request = new CancelJobRequest();
            request.setClusterId(clusterId);
            request.setJobId(jobId);
            this.client.cancelJob(request);
        }

        @Test
        public void suspendJobTest() {
            SuspendJobRequest request = new SuspendJobRequest();
            request.setClusterId(clusterId);
            request.setJobId(jobId);
            this.client.suspendJob(request);
        }

        @Test
        public void resumeJobTest() {
            ResumeJobRequest request = new ResumeJobRequest();
            request.setClusterId(clusterId);
            request.setJobId(jobId);
            this.client.resumeJob(request);
        }
    }
    /**
     * Test case about job end
     */
}