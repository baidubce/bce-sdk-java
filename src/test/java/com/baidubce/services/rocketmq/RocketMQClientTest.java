package com.baidubce.services.rocketmq;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.baidubce.http.BceHttpClient;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.rocketmq.model.Message;
import com.baidubce.services.rocketmq.model.request.*;
import com.baidubce.services.rocketmq.model.response.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

public class RocketMQClientTest {

    @InjectMocks
    private RocketMQClient rocketMQClient;

    private BceHttpClient mockHttpClient;

    @Mock
    private RocketMQClientConfiguration configuration;

    private final String clusterId = "test-cluster-123";
    private final String topicName = "test-topic";
    private final String consumerGroupName = "test-consumer-group";
    private final String username = "test-user";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockHttpClient = mock(BceHttpClient.class);
        rocketMQClient = new RocketMQClient(configuration);
        rocketMQClient.setClient(mockHttpClient);
    }

    @Test
    public void testListClusters() {
        ListClustersRequest request = new ListClustersRequest();
        request.setMarker("test-marker");
        request.setMaxKeys(100);
        request.setClusterName("test-cluster");
        request.setState("ACTIVE");
        request.setArch("test-arch");
        request.setRocketmqVersion("4.8.0");
        request.setPayment("POSTPAID");
        request.setTagKey("test-key");
        request.setTagValue("test-value");

        ListClustersResponse mockResponse = new ListClustersResponse();
        when(mockHttpClient.execute(any(), eq(ListClustersResponse.class), any())).thenReturn(mockResponse);

        ListClustersResponse response = rocketMQClient.listClusters(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListClustersResponse.class), any());
    }

    @Test
    public void testCreateCluster() {
        CreateClusterRequest request = new CreateClusterRequest();
        request.setName("test-cluster");
        CreateClusterResponse mockResponse = new CreateClusterResponse();
        when(mockHttpClient.execute(any(), eq(CreateClusterResponse.class), any())).thenReturn(mockResponse);

        CreateClusterResponse response = rocketMQClient.createCluster(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(CreateClusterResponse.class), any());
    }

    @Test
    public void testGetCluster() {
        GetClusterRequest request = new GetClusterRequest();
        request.setClusterId(clusterId);
        GetClusterResponse mockResponse = new GetClusterResponse();
        when(mockHttpClient.execute(any(), eq(GetClusterResponse.class), any())).thenReturn(mockResponse);

        GetClusterResponse response = rocketMQClient.getCluster(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(GetClusterResponse.class), any());
    }
    @Test
    public void testStartCluster() {
        StartClusterRequest request = new StartClusterRequest();
        request.setClusterId(clusterId);
        StartClusterResponse mockResponse = new StartClusterResponse();
        when(mockHttpClient.execute(any(), eq(StartClusterResponse.class), any())).thenReturn(mockResponse);

        StartClusterResponse response = rocketMQClient.startCluster(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(StartClusterResponse.class), any());
    }
    @Test
    public void testExpandBroker() {
        ExpandBrokerRequest request = new ExpandBrokerRequest();
        request.setClusterId(clusterId);
        ExpandBrokerResponse mockResponse = new ExpandBrokerResponse();
        when(mockHttpClient.execute(any(), eq(ExpandBrokerResponse.class), any())).thenReturn(mockResponse);

        ExpandBrokerResponse response = rocketMQClient.expandBroker(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ExpandBrokerResponse.class), any());
    }

    @Test
    public void testModifyBrokerNodeType() {
        ModifyBrokerNodeTypeRequest request = new ModifyBrokerNodeTypeRequest();
        request.setClusterId(clusterId);
        ModifyBrokerNodeTypeResponse mockResponse = new ModifyBrokerNodeTypeResponse();
        when(mockHttpClient.execute(any(), eq(ModifyBrokerNodeTypeResponse.class), any())).thenReturn(mockResponse);

        ModifyBrokerNodeTypeResponse response = rocketMQClient.modifyBrokerNodeType(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ModifyBrokerNodeTypeResponse.class), any());
    }

    @Test
    public void testRestartBrokerNode() {
        RestartBrokerNodeRequest request = new RestartBrokerNodeRequest();
        request.setClusterId(clusterId);
        RestartBrokerNodeResponse mockResponse = new RestartBrokerNodeResponse();
        when(mockHttpClient.execute(any(), eq(RestartBrokerNodeResponse.class), any())).thenReturn(mockResponse);

        RestartBrokerNodeResponse response = rocketMQClient.restartBrokerNode(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(RestartBrokerNodeResponse.class), any());
    }

    @Test
    public void testStopClusterSuccess() {
        StopClusterRequest request = new StopClusterRequest();
        request.setClusterId(clusterId);
        StopClusterResponse mockResponse = new StopClusterResponse();

        when(mockHttpClient.execute(any(InternalRequest.class), eq(StopClusterResponse.class), any())).thenReturn(mockResponse);

        StopClusterResponse response = rocketMQClient.stopCluster(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(InternalRequest.class), eq(StopClusterResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStopClusterWithNullRequest() {
        rocketMQClient.stopCluster(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStopClusterWithEmptyClusterId() {
        StopClusterRequest request = new StopClusterRequest();
        rocketMQClient.stopCluster(request);
    }

    @Test
    public void testDeleteClusterSuccess() {
        DeleteClusterRequest request = new DeleteClusterRequest();
        request.setClusterId(clusterId);

        DeleteClusterResponse mockResponse = new DeleteClusterResponse();
        when(mockHttpClient.execute(any(), eq(DeleteClusterResponse.class), any())).thenReturn(mockResponse);

        DeleteClusterResponse response = rocketMQClient.deleteCluster(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(DeleteClusterResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteClusterWithNullRequest() {
        rocketMQClient.deleteCluster(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteClusterWithEmptyClusterId() {
        DeleteClusterRequest request = new DeleteClusterRequest();
        request.setClusterId("");
        rocketMQClient.deleteCluster(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteClusterWithNullClusterId() {
        DeleteClusterRequest request = new DeleteClusterRequest();
        request.setClusterId(null);
        rocketMQClient.deleteCluster(request);
    }

    @Test
    public void testGetAccessEndpoints() {
        GetClusterAccessEndpointsRequest request = new GetClusterAccessEndpointsRequest();
        request.setClusterId(clusterId);

        GetClusterAccessEndpointsResponse mockResponse = new GetClusterAccessEndpointsResponse();
        when(mockHttpClient.execute(any(), eq(GetClusterAccessEndpointsResponse.class), any()))
                .thenReturn(mockResponse);

        GetClusterAccessEndpointsResponse response = rocketMQClient.getAccessEndpoints(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(GetClusterAccessEndpointsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAccessEndpointsWithNullRequest() {
        rocketMQClient.getAccessEndpoints(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAccessEndpointsWithEmptyClusterId() {
        GetClusterAccessEndpointsRequest request = new GetClusterAccessEndpointsRequest();
        rocketMQClient.getAccessEndpoints(request);
    }

    @Test
    public void testGetConsumerGroup() {
        GetConsumerGroupRequest request = new GetConsumerGroupRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);

        GetConsumerGroupResponse mockResponse = new GetConsumerGroupResponse();
        when(mockHttpClient.execute(any(), eq(GetConsumerGroupResponse.class), any())).thenReturn(mockResponse);

        GetConsumerGroupResponse response = rocketMQClient.getConsumerGroup(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(GetConsumerGroupResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetConsumerGroupWithNullRequest() {
        rocketMQClient.getConsumerGroup(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetConsumerGroupWithNullClusterId() {
        GetConsumerGroupRequest request = new GetConsumerGroupRequest();
        request.setConsumerGroupName(consumerGroupName);

        rocketMQClient.getConsumerGroup(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetConsumerGroupWithNullConsumerGroupName() {
        GetConsumerGroupRequest request = new GetConsumerGroupRequest();
        request.setClusterId(clusterId);

        rocketMQClient.getConsumerGroup(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetConsumerGroupWithEmptyClusterId() {
        GetConsumerGroupRequest request = new GetConsumerGroupRequest();
        request.setClusterId("");
        request.setConsumerGroupName(consumerGroupName);

        rocketMQClient.getConsumerGroup(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetConsumerGroupWithEmptyConsumerGroupName() {
        GetConsumerGroupRequest request = new GetConsumerGroupRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName("");

        rocketMQClient.getConsumerGroup(request);
    }

    @Test
    public void testCreateTopicSuccess() {
        CreateTopicRequest request = new CreateTopicRequest();
        request.setTopicName(topicName);
        request.setClusterId(clusterId);

        CreateTopicResponse mockResponse = new CreateTopicResponse();
        when(mockHttpClient.execute(any(), eq(CreateTopicResponse.class), any())).thenReturn(mockResponse);

        CreateTopicResponse response = rocketMQClient.createTopic(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(CreateTopicResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTopicWithNullRequest() {
        rocketMQClient.createTopic(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTopicWithEmptyClusterId() {
        CreateTopicRequest request = new CreateTopicRequest();
        request.setTopicName(topicName);
        request.setClusterId("");

        rocketMQClient.createTopic(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTopicWithNullClusterId() {
        CreateTopicRequest request = new CreateTopicRequest();
        request.setTopicName(topicName);
        request.setClusterId(null);

        rocketMQClient.createTopic(request);
    }

    @Test
    public void testUpdateConsumerGroupSuccess() {
        UpdateConsumerGroupRequest request = new UpdateConsumerGroupRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        request.setConsumeEnable(true);
        request.setConsumeBroadcastEnable(false);
        request.setRetryMaxTimes(3);

        UpdateConsumerGroupResponse mockResponse = new UpdateConsumerGroupResponse();
        when(mockHttpClient.execute(any(), eq(UpdateConsumerGroupResponse.class), any())).thenReturn(mockResponse);

        UpdateConsumerGroupResponse response = rocketMQClient.updateConsumerGroup(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(UpdateConsumerGroupResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateConsumerGroupWithNullRequest() {
        rocketMQClient.updateConsumerGroup(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateConsumerGroupWithEmptyClusterId() {
        UpdateConsumerGroupRequest request = new UpdateConsumerGroupRequest();
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.updateConsumerGroup(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateConsumerGroupWithEmptyConsumerGroupName() {
        UpdateConsumerGroupRequest request = new UpdateConsumerGroupRequest();
        request.setClusterId(clusterId);
        rocketMQClient.updateConsumerGroup(request);
    }

    @Test
    public void testDeleteConsumerGroupSuccess() {
        DeleteConsumerGroupRequest request = new DeleteConsumerGroupRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);

        DeleteConsumerGroupResponse mockResponse = new DeleteConsumerGroupResponse();
        when(mockHttpClient.execute(any(), eq(DeleteConsumerGroupResponse.class), any())).thenReturn(mockResponse);

        DeleteConsumerGroupResponse response = rocketMQClient.deleteConsumerGroup(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(DeleteConsumerGroupResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteConsumerGroupWithNullRequest() {
        rocketMQClient.deleteConsumerGroup(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteConsumerGroupWithNullClusterId() {
        DeleteConsumerGroupRequest request = new DeleteConsumerGroupRequest();
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.deleteConsumerGroup(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteConsumerGroupWithNullConsumerGroupName() {
        DeleteConsumerGroupRequest request = new DeleteConsumerGroupRequest();
        request.setClusterId(clusterId);
        rocketMQClient.deleteConsumerGroup(request);
    }

    @Test
    public void testResetConsumeOffset() {
        ResetConsumeOffsetRequest request = new ResetConsumeOffsetRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        request.setTopicName(topicName);

        ResetConsumeOffsetResponse mockResponse = new ResetConsumeOffsetResponse();
        when(mockHttpClient.execute(any(), eq(ResetConsumeOffsetResponse.class), any())).thenReturn(mockResponse);

        ResetConsumeOffsetResponse response = rocketMQClient.resetConsumeOffset(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ResetConsumeOffsetResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResetConsumeOffsetWithNullRequest() {
        rocketMQClient.resetConsumeOffset(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResetConsumeOffsetWithNullClusterId() {
        ResetConsumeOffsetRequest request = new ResetConsumeOffsetRequest();
        request.setConsumerGroupName(consumerGroupName);
        request.setTopicName(topicName);
        rocketMQClient.resetConsumeOffset(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResetConsumeOffsetWithNullConsumerGroupName() {
        ResetConsumeOffsetRequest request = new ResetConsumeOffsetRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        rocketMQClient.resetConsumeOffset(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResetConsumeOffsetWithNullTopicName() {
        ResetConsumeOffsetRequest request = new ResetConsumeOffsetRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.resetConsumeOffset(request);
    }

    @Test
    public void testListConsumerClients() {
        ListConsumerClientsRequest request = new ListConsumerClientsRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListConsumerClientsResponse mockResponse = new ListConsumerClientsResponse();
        when(mockHttpClient.execute(any(), eq(ListConsumerClientsResponse.class), any())).thenReturn(mockResponse);

        ListConsumerClientsResponse response = rocketMQClient.listConsumerClients(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListConsumerClientsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerClientsWithNullRequest() {
        rocketMQClient.listConsumerClients(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerClientsWithEmptyClusterId() {
        ListConsumerClientsRequest request = new ListConsumerClientsRequest();
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.listConsumerClients(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerClientsWithEmptyConsumerGroupName() {
        ListConsumerClientsRequest request = new ListConsumerClientsRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listConsumerClients(request);
    }

    @Test
    public void testListConsumerGroupSubscriptions() {
        ListConsumerGroupSubscriptionsRequest request = new ListConsumerGroupSubscriptionsRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListConsumerGroupSubscriptionsResponse mockResponse = new ListConsumerGroupSubscriptionsResponse();
        when(mockHttpClient.execute(any(), eq(ListConsumerGroupSubscriptionsResponse.class), any()))
                .thenReturn(mockResponse);

        ListConsumerGroupSubscriptionsResponse response =
                rocketMQClient.listConsumerGroupSubscriptions(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListConsumerGroupSubscriptionsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerGroupSubscriptionsWithNullRequest() {
        rocketMQClient.listConsumerGroupSubscriptions(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerGroupSubscriptionsWithEmptyClusterId() {
        ListConsumerGroupSubscriptionsRequest request = new ListConsumerGroupSubscriptionsRequest();
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.listConsumerGroupSubscriptions(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerGroupSubscriptionsWithEmptyConsumerGroupName() {
        ListConsumerGroupSubscriptionsRequest request = new ListConsumerGroupSubscriptionsRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listConsumerGroupSubscriptions(request);
    }

    @Test
    public void testListConsumerGroupUsers() {
        ListConsumerGroupUsersRequest request = new ListConsumerGroupUsersRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);

        ListConsumerGroupUsersResponse mockResponse = new ListConsumerGroupUsersResponse();
        when(mockHttpClient.execute(any(), eq(ListConsumerGroupUsersResponse.class), any())).thenReturn(mockResponse);

        ListConsumerGroupUsersResponse response = rocketMQClient.listConsumerGroupUsers(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListConsumerGroupUsersResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerGroupUsersWithNullRequest() {
        rocketMQClient.listConsumerGroupUsers(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerGroupUsersWithEmptyClusterId() {
        ListConsumerGroupUsersRequest request = new ListConsumerGroupUsersRequest();
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.listConsumerGroupUsers(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumerGroupUsersWithEmptyConsumerGroupName() {
        ListConsumerGroupUsersRequest request = new ListConsumerGroupUsersRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listConsumerGroupUsers(request);
    }

    @Test
    public void testListConsumeStates() {
        ListConsumeStatesRequest request = new ListConsumeStatesRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        request.setTopicName(topicName);
        request.setBrokerNames(Arrays.asList("broker1", "broker2"));

        ListConsumeStatesResponse mockResponse = new ListConsumeStatesResponse();
        when(mockHttpClient.execute(any(), eq(ListConsumeStatesResponse.class), any())).thenReturn(mockResponse);

        ListConsumeStatesResponse response = rocketMQClient.listConsumeStates(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListConsumeStatesResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumeStatesWithNullRequest() {
        rocketMQClient.listConsumeStates(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumeStatesWithEmptyClusterId() {
        ListConsumeStatesRequest request = new ListConsumeStatesRequest();
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.listConsumeStates(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListConsumeStatesWithEmptyConsumerGroupName() {
        ListConsumeStatesRequest request = new ListConsumeStatesRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listConsumeStates(request);
    }

    @Test
    public void testListConsumeStatesWithoutBrokerNames() {
        ListConsumeStatesRequest request = new ListConsumeStatesRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        request.setTopicName(topicName);

        ListConsumeStatesResponse mockResponse = new ListConsumeStatesResponse();
        when(mockHttpClient.execute(any(), eq(ListConsumeStatesResponse.class), any())).thenReturn(mockResponse);

        ListConsumeStatesResponse response = rocketMQClient.listConsumeStates(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListConsumeStatesResponse.class), any());
    }

    @Test
    public void testCreateAclUserSuccess() {
        CreateAclUserConfigRequest request = new CreateAclUserConfigRequest();
        request.setClusterId(clusterId);
        request.setUsername(username);

        CreateAclUserResponse mockResponse = new CreateAclUserResponse();
        when(mockHttpClient.execute(any(), eq(CreateAclUserResponse.class), any())).thenReturn(mockResponse);

        CreateAclUserResponse response = rocketMQClient.createAclUser(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(CreateAclUserResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAclUserWithNullRequest() {
        rocketMQClient.createAclUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAclUserWithNullClusterId() {
        CreateAclUserConfigRequest request = new CreateAclUserConfigRequest();
        request.setUsername(username);
        rocketMQClient.createAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAclUserWithNullUsername() {
        CreateAclUserConfigRequest request = new CreateAclUserConfigRequest();
        request.setClusterId(clusterId);
        rocketMQClient.createAclUser(request);
    }

    @Test
    public void testGetAclUser() {
        GetAclUserRequest request = new GetAclUserRequest();
        request.setClusterId(clusterId);
        request.setUsername(username);

        GetAclUserConfigResponse mockResponse = new GetAclUserConfigResponse();
        when(mockHttpClient.execute(any(), eq(GetAclUserConfigResponse.class), any())).thenReturn(mockResponse);

        GetAclUserConfigResponse response = rocketMQClient.getAclUser(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(GetAclUserConfigResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAclUserWithNullRequest() {
        rocketMQClient.getAclUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAclUserWithEmptyClusterId() {
        GetAclUserRequest request = new GetAclUserRequest();
        request.setUsername(username);
        request.setClusterId("");

        rocketMQClient.getAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAclUserWithEmptyUsername() {
        GetAclUserRequest request = new GetAclUserRequest();
        request.setClusterId(clusterId);
        request.setUsername("");

        rocketMQClient.getAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAclUserWithNullClusterId() {
        GetAclUserRequest request = new GetAclUserRequest();
        request.setUsername(username);
        request.setClusterId(null);

        rocketMQClient.getAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAclUserWithNullUsername() {
        GetAclUserRequest request = new GetAclUserRequest();
        request.setClusterId(clusterId);
        request.setUsername(null);

        rocketMQClient.getAclUser(request);
    }

    @Test
    public void testUpdateAclUser() {
        UpdateAclUserConfigRequest request = new UpdateAclUserConfigRequest();
        request.setClusterId(clusterId);
        request.setUsername(username);
        request.setPassword("");
        request.setAdmin(true);

        UpdateAclUserConfigResponse mockResponse = new UpdateAclUserConfigResponse();
        when(mockHttpClient.execute(any(), eq(UpdateAclUserConfigResponse.class), any())).thenReturn(mockResponse);

        UpdateAclUserConfigResponse response = rocketMQClient.updateAclUser(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(UpdateAclUserConfigResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAclUserWithNullRequest() {
        rocketMQClient.updateAclUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAclUserWithNullClusterId() {
        UpdateAclUserConfigRequest request = new UpdateAclUserConfigRequest();
        request.setUsername(username);
        rocketMQClient.updateAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAclUserWithNullUsername() {
        UpdateAclUserConfigRequest request = new UpdateAclUserConfigRequest();
        request.setClusterId(clusterId);
        rocketMQClient.updateAclUser(request);
    }

    @Test
    public void testDeleteAclUser_Success() {
        DeleteAclUserConfigRequest request = new DeleteAclUserConfigRequest();
        request.setClusterId(clusterId);
        request.setUsername(username);

        DeleteAclUserConfigResponse mockResponse = new DeleteAclUserConfigResponse();
        when(mockHttpClient.execute(any(), eq(DeleteAclUserConfigResponse.class), any())).thenReturn(mockResponse);

        DeleteAclUserConfigResponse response = rocketMQClient.deleteAclUser(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(DeleteAclUserConfigResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAclUser_NullRequest() {
        rocketMQClient.deleteAclUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAclUser_NullClusterId() {
        DeleteAclUserConfigRequest request = new DeleteAclUserConfigRequest();
        request.setUsername(username);

        rocketMQClient.deleteAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAclUser_NullUsername() {
        DeleteAclUserConfigRequest request = new DeleteAclUserConfigRequest();
        request.setClusterId(clusterId);

        rocketMQClient.deleteAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAclUser_EmptyClusterId() {
        DeleteAclUserConfigRequest request = new DeleteAclUserConfigRequest();
        request.setClusterId("");
        request.setUsername(username);

        rocketMQClient.deleteAclUser(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAclUser_EmptyUsername() {
        DeleteAclUserConfigRequest request = new DeleteAclUserConfigRequest();
        request.setClusterId(clusterId);
        request.setUsername("");

        rocketMQClient.deleteAclUser(request);
    }

    @Test
    public void testSendMessageSuccess() {
        SendMessageRequest request = new SendMessageRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setBody("test-body".getBytes());
        request.setKey("test-key");
        request.setTag("test-tag");

        SendMessageResponse mockResponse = new SendMessageResponse();
        when(mockHttpClient.execute(any(), eq(SendMessageResponse.class), any())).thenReturn(mockResponse);

        SendMessageResponse response = rocketMQClient.sendMessage(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(SendMessageResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendMessageWithNullRequest() {
        rocketMQClient.sendMessage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendMessageWithNullClusterId() {
        SendMessageRequest request = new SendMessageRequest();
        request.setTopicName(topicName);
        request.setBody("test-body".getBytes());
        rocketMQClient.sendMessage(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendMessageWithNullTopicName() {
        SendMessageRequest request = new SendMessageRequest();
        request.setClusterId(clusterId);
        request.setBody("test-body".getBytes());
        rocketMQClient.sendMessage(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendMessageWithNullBody() {
        SendMessageRequest request = new SendMessageRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        rocketMQClient.sendMessage(request);
    }

    @Test
    public void testQueryMessage() {
        QueryMessageRequest request = new QueryMessageRequest();
        request.setClusterId(clusterId);
        request.setMessageId("test-message-id");
        request.setTopicName(topicName);

        QueryMessageResponse.QueryMessageInnerResponse innerResponse = new QueryMessageResponse.QueryMessageInnerResponse();
        innerResponse.setCode("200");
        innerResponse.setMessage("success");
        innerResponse.setRequestId("test-request-id");

        Message.MessageInner messageInner = new Message.MessageInner();
        messageInner.setMsgId("test-message-id");
        messageInner.setTopicName(topicName);
        messageInner.setTag("test-tag");
        messageInner.setKey("test-key");
        messageInner.setStoreTime(123456789L);
        messageInner.setBase64Body(Base64.getEncoder().encodeToString("test-body".getBytes()));
        innerResponse.setMessageDetail(messageInner);

        when(mockHttpClient.execute(any(InternalRequest.class), eq(QueryMessageResponse.QueryMessageInnerResponse.class), any()))
                .thenReturn(innerResponse);

        QueryMessageResponse response = rocketMQClient.queryMessage(request);

        assertNotNull(response);
        assertEquals("200", response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals("test-request-id", response.getRequestId());
        assertNotNull(response.getMessageDetail());
        assertEquals("test-message-id", response.getMessageDetail().getMsgId());
        assertEquals(topicName, response.getMessageDetail().getTopicName());
        assertEquals("test-tag", response.getMessageDetail().getTag());
        assertEquals("test-key", response.getMessageDetail().getKey());
        assertEquals(123456789L, (long)response.getMessageDetail().getStoreTime());
        assertArrayEquals("test-body".getBytes(), response.getMessageDetail().getBody());

        verify(mockHttpClient).execute(any(InternalRequest.class), eq(QueryMessageResponse.QueryMessageInnerResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageWithNullRequest() {
        rocketMQClient.queryMessage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageWithNullClusterId() {
        QueryMessageRequest request = new QueryMessageRequest();
        request.setMessageId("test-message-id");
        rocketMQClient.queryMessage(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageWithNullMessageId() {
        QueryMessageRequest request = new QueryMessageRequest();
        request.setClusterId(clusterId);
        rocketMQClient.queryMessage(request);
    }

    @Test
    public void testListActionOperations() {
        ListActionOperationsRequest request = new ListActionOperationsRequest();
        request.setClusterId(clusterId);
        request.setActionId("test-action-id");
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListActionOperationsResponse mockResponse = new ListActionOperationsResponse();
        when(mockHttpClient.execute(any(), eq(ListActionOperationsResponse.class), any())).thenReturn(mockResponse);

        ListActionOperationsResponse response = rocketMQClient.listActionOperations(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListActionOperationsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListActionOperationsWithNullRequest() {
        rocketMQClient.listActionOperations(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListActionOperationsWithNullClusterId() {
        ListActionOperationsRequest request = new ListActionOperationsRequest();
        request.setActionId("test-action-id");
        rocketMQClient.listActionOperations(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListActionOperationsWithNullActionId() {
        ListActionOperationsRequest request = new ListActionOperationsRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listActionOperations(request);
    }

    @Test
    public void testListTopicUsers() {
        ListTopicUsersRequest request = new ListTopicUsersRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListTopicUsersResponse mockResponse = new ListTopicUsersResponse();
        when(mockHttpClient.execute(any(), eq(ListTopicUsersResponse.class), any())).thenReturn(mockResponse);

        ListTopicUsersResponse response = rocketMQClient.listTopicUsers(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListTopicUsersResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicUsersWithNullRequest() {
        rocketMQClient.listTopicUsers(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicUsersWithEmptyClusterId() {
        ListTopicUsersRequest request = new ListTopicUsersRequest();
        request.setTopicName(topicName);
        rocketMQClient.listTopicUsers(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicUsersWithEmptyTopicName() {
        ListTopicUsersRequest request = new ListTopicUsersRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listTopicUsers(request);
    }

    @Test
    public void testCreateConsumerGroup() {
        CreateConsumerGroupRequest request = new CreateConsumerGroupRequest();
        request.setClusterId(clusterId);
        request.setConsumerGroupName(consumerGroupName);
        request.setConsumeEnable(true);
        request.setConsumeBroadcastEnable(false);
        request.setRetryMaxTimes(3);

        CreateConsumerGroupResponse mockResponse = new CreateConsumerGroupResponse();
        when(mockHttpClient.execute(any(), eq(CreateConsumerGroupResponse.class), any())).thenReturn(mockResponse);

        CreateConsumerGroupResponse response = rocketMQClient.createConsumerGroup(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(CreateConsumerGroupResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConsumerGroupWithNullRequest() {
        rocketMQClient.createConsumerGroup(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConsumerGroupWithEmptyClusterId() {
        CreateConsumerGroupRequest request = new CreateConsumerGroupRequest();
        request.setConsumerGroupName(consumerGroupName);
        rocketMQClient.createConsumerGroup(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConsumerGroupWithEmptyConsumerGroupName() {
        CreateConsumerGroupRequest request = new CreateConsumerGroupRequest();
        request.setClusterId(clusterId);
        rocketMQClient.createConsumerGroup(request);
    }

    @Test
    public void testQueryMessageList() {
        QueryMessageListRequest request = new QueryMessageListRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setBeginTime(1000L);
        request.setEndTime(2000L);
        request.setMessageId("msg-id-123");
        request.setMessageKey("msg-key-456");

        QueryMessageListResponse.QueryMessageListInnerResponse innerResponse =
                new QueryMessageListResponse.QueryMessageListInnerResponse();
        innerResponse.setMessage("success");
        innerResponse.setCode("200");
        innerResponse.setRequestId("req-123");
        innerResponse.setMarker("marker-1");
        innerResponse.setIsTruncated(false);
        innerResponse.setMaxKeys(10);
        innerResponse.setNextMarker("marker-2");

        Message.MessageInner messageInner = new Message.MessageInner();
        messageInner.setMsgId("msg-id-123");
        messageInner.setTopicName(topicName);
        messageInner.setTag("tag-1");
        messageInner.setKey("key-1");
        messageInner.setStoreTime(1500L);
        messageInner.setBase64Body(Base64.getEncoder().encodeToString("test-body".getBytes()));
        innerResponse.setMessages(Collections.singletonList(messageInner));

        when(mockHttpClient.execute(any(), eq(QueryMessageListResponse.QueryMessageListInnerResponse.class), any()))
                .thenReturn(innerResponse);

        QueryMessageListResponse response = rocketMQClient.queryMessageList(request);

        assertNotNull(response);
        assertEquals("success", response.getMessage());
        assertEquals("200", response.getCode());
        assertEquals("req-123", response.getRequestId());
        assertEquals("marker-1", response.getMarker());
        assertFalse(response.getIsTruncated());
        assertEquals(Integer.valueOf(10), response.getMaxKeys());
        assertEquals("marker-2", response.getNextMarker());
        assertEquals(1, response.getMessages().size());

        Message message = response.getMessages().get(0);
        assertEquals("msg-id-123", message.getMsgId());
        assertEquals(topicName, message.getTopicName());
        assertEquals("tag-1", message.getTag());
        assertEquals("key-1", message.getKey());
        assertEquals(Long.valueOf(1500L), message.getStoreTime());
        assertArrayEquals("test-body".getBytes(), message.getBody());

        verify(mockHttpClient).execute(any(), eq(QueryMessageListResponse.QueryMessageListInnerResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageListWithNullRequest() {
        rocketMQClient.queryMessageList(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageListWithEmptyClusterId() {
        QueryMessageListRequest request = new QueryMessageListRequest();
        request.setClusterId("");
        request.setTopicName(topicName);
        request.setBeginTime(1000L);
        request.setEndTime(2000L);
        rocketMQClient.queryMessageList(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageListWithEmptyTopicName() {
        QueryMessageListRequest request = new QueryMessageListRequest();
        request.setClusterId(clusterId);
        request.setTopicName("");
        request.setBeginTime(1000L);
        request.setEndTime(2000L);
        rocketMQClient.queryMessageList(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageListWithInvalidBeginTime() {
        QueryMessageListRequest request = new QueryMessageListRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setBeginTime(0L);
        request.setEndTime(2000L);
        rocketMQClient.queryMessageList(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryMessageListWithInvalidEndTime() {
        QueryMessageListRequest request = new QueryMessageListRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setBeginTime(1000L);
        request.setEndTime(0L);
        rocketMQClient.queryMessageList(request);
    }

    @Test
    public void testListTopicSubscription() {
        ListTopicSubscriptionsRequest request = new ListTopicSubscriptionsRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListTopicSubscriptionsResponse mockResponse = new ListTopicSubscriptionsResponse();
        when(mockHttpClient.execute(any(), eq(ListTopicSubscriptionsResponse.class), any()))
                .thenReturn(mockResponse);

        ListTopicSubscriptionsResponse response = rocketMQClient.listTopicSubscription(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListTopicSubscriptionsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicSubscriptionWithNullRequest() {
        rocketMQClient.listTopicSubscription(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicSubscriptionWithNullClusterId() {
        ListTopicSubscriptionsRequest request = new ListTopicSubscriptionsRequest();
        request.setTopicName(topicName);
        rocketMQClient.listTopicSubscription(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicSubscriptionWithNullTopicName() {
        ListTopicSubscriptionsRequest request = new ListTopicSubscriptionsRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listTopicSubscription(request);
    }

    @Test
    public void testListTopicQueue() {
        ListTopicQueuesRequest request = new ListTopicQueuesRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListTopicQueuesResponse mockResponse = new ListTopicQueuesResponse();
        when(mockHttpClient.execute(any(), eq(ListTopicQueuesResponse.class), any())).thenReturn(mockResponse);

        ListTopicQueuesResponse response = rocketMQClient.listTopicQueue(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListTopicQueuesResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicQueueWithNullRequest() {
        rocketMQClient.listTopicQueue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicQueueWithNullClusterId() {
        ListTopicQueuesRequest request = new ListTopicQueuesRequest();
        request.setTopicName(topicName);
        rocketMQClient.listTopicQueue(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListTopicQueueWithNullTopicName() {
        ListTopicQueuesRequest request = new ListTopicQueuesRequest();
        request.setClusterId(clusterId);
        rocketMQClient.listTopicQueue(request);
    }

    @Test
    public void testDeleteTopic_Success() {
        DeleteTopicRequest request = new DeleteTopicRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);

        DeleteTopicResponse mockResponse = new DeleteTopicResponse();
        when(mockHttpClient.execute(any(), eq(DeleteTopicResponse.class), any())).thenReturn(mockResponse);

        DeleteTopicResponse response = rocketMQClient.deleteTopic(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(DeleteTopicResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTopic_NullRequest() {
        rocketMQClient.deleteTopic(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTopic_NullClusterId() {
        DeleteTopicRequest request = new DeleteTopicRequest();
        request.setTopicName(topicName);
        rocketMQClient.deleteTopic(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTopic_NullTopicName() {
        DeleteTopicRequest request = new DeleteTopicRequest();
        request.setClusterId(clusterId);
        rocketMQClient.deleteTopic(request);
    }

    @Test
    public void testUpdateTopicSuccess() {
        UpdateTopicRequest request = new UpdateTopicRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);
        request.setPermission(1);

        UpdateTopicResponse mockResponse = new UpdateTopicResponse();
        when(mockHttpClient.execute(any(), eq(UpdateTopicResponse.class), any())).thenReturn(mockResponse);

        UpdateTopicResponse response = rocketMQClient.updateTopic(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(UpdateTopicResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTopicWithNullRequest() {
        rocketMQClient.updateTopic(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTopicWithNullClusterId() {
        UpdateTopicRequest request = new UpdateTopicRequest();
        request.setTopicName(topicName);
        rocketMQClient.updateTopic(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTopicWithNullTopicName() {
        UpdateTopicRequest request = new UpdateTopicRequest();
        request.setClusterId(clusterId);
        rocketMQClient.updateTopic(request);
    }

    @Test
    public void testGetTopicSuccess() {
        GetTopicRequest request = new GetTopicRequest();
        request.setClusterId(clusterId);
        request.setTopicName(topicName);

        GetTopicResponse mockResponse = new GetTopicResponse();
        when(mockHttpClient.execute(any(InternalRequest.class), eq(GetTopicResponse.class), any())).thenReturn(mockResponse);

        GetTopicResponse response = rocketMQClient.getTopic(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(InternalRequest.class), eq(GetTopicResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTopicWithNullRequest() {
        rocketMQClient.getTopic(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTopicWithEmptyClusterId() {
        GetTopicRequest request = new GetTopicRequest();
        request.setTopicName(topicName);
        rocketMQClient.getTopic(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTopicWithEmptyTopicName() {
        GetTopicRequest request = new GetTopicRequest();
        request.setClusterId(clusterId);
        rocketMQClient.getTopic(request);
    }

    @Test
    public void testIncreaseStorageSize() {
        IncreaseStorageSizeRequest request = new IncreaseStorageSizeRequest();
        request.setClusterId(clusterId);
        request.setTargetBrokerStorageSizeInGB(100);
        request.setIsAutoPay(true);

        IncreaseStorageSizeResponse mockResponse = new IncreaseStorageSizeResponse();
        when(mockHttpClient.execute(any(), eq(IncreaseStorageSizeResponse.class), any())).thenReturn(mockResponse);

        IncreaseStorageSizeResponse response = rocketMQClient.increaseStorageSize(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(IncreaseStorageSizeResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncreaseStorageSizeWithNullRequest() {
        rocketMQClient.increaseStorageSize(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncreaseStorageSizeWithEmptyClusterId() {
        IncreaseStorageSizeRequest request = new IncreaseStorageSizeRequest();
        rocketMQClient.increaseStorageSize(request);
    }

    @Test
    public void testListClusterAclUsers() {
        ListClusterActionsRequest request = new ListClusterActionsRequest();
        request.setClusterId(clusterId);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListClusterActionsResponse mockResponse = new ListClusterActionsResponse();
        when(mockHttpClient.execute(any(), eq(ListClusterActionsResponse.class), any())).thenReturn(mockResponse);

        ListClusterActionsResponse response = rocketMQClient.listClusterAclUsers(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListClusterActionsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterAclUsersWithNullRequest() {
        rocketMQClient.listClusterAclUsers((ListClusterAclUserConfigsRequest) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterAclUsersWithEmptyClusterId() {
        ListClusterActionsRequest request = new ListClusterActionsRequest();
        request.setClusterId("");
        rocketMQClient.listClusterAclUsers(request);
    }

    @Test
    public void testListClusterAclUsers_Success() {
        ListClusterAclUserConfigsRequest request = new ListClusterAclUserConfigsRequest();
        request.setClusterId(clusterId);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListClusterAclUserConfigsResponse mockResponse = new ListClusterAclUserConfigsResponse();
        when(mockHttpClient.execute(any(), eq(ListClusterAclUserConfigsResponse.class), any())).thenReturn(mockResponse);

        ListClusterAclUserConfigsResponse response = rocketMQClient.listClusterAclUsers(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListClusterAclUserConfigsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterAclUsers_NullRequest() {
        rocketMQClient.listClusterAclUsers((ListClusterAclUserConfigsRequest) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterAclUsers_EmptyClusterId() {
        ListClusterAclUserConfigsRequest request = new ListClusterAclUserConfigsRequest();
        request.setClusterId("");
        rocketMQClient.listClusterAclUsers(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterAclUsers_NullClusterId() {
        ListClusterAclUserConfigsRequest request = new ListClusterAclUserConfigsRequest();
        request.setClusterId(null);
        rocketMQClient.listClusterAclUsers(request);
    }

    @Test
    public void testListClusterConsumerGroups() {
        ListClusterConsumerGroupsRequest request = new ListClusterConsumerGroupsRequest();
        request.setClusterId(clusterId);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListClusterConsumerGroupsResponse mockResponse = new ListClusterConsumerGroupsResponse();
        when(mockHttpClient.execute(any(), eq(ListClusterConsumerGroupsResponse.class), any()))
                .thenReturn(mockResponse);

        ListClusterConsumerGroupsResponse response = rocketMQClient.listClusterConsumerGroups(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListClusterConsumerGroupsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterConsumerGroupsWithNullRequest() {
        rocketMQClient.listClusterConsumerGroups(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterConsumerGroupsWithEmptyClusterId() {
        ListClusterConsumerGroupsRequest request = new ListClusterConsumerGroupsRequest();
        rocketMQClient.listClusterConsumerGroups(request);
    }

    @Test
    public void testListClusterTopics_Success() {
        ListClusterTopicsRequest request = new ListClusterTopicsRequest();
        request.setClusterId(clusterId);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListClusterTopicsResponse mockResponse = new ListClusterTopicsResponse();
        when(mockHttpClient.execute(any(), eq(ListClusterTopicsResponse.class), any())).thenReturn(mockResponse);

        ListClusterTopicsResponse response = rocketMQClient.listClusterTopics(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListClusterTopicsResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterTopics_NullRequest() {
        rocketMQClient.listClusterTopics(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterTopics_NullClusterId() {
        ListClusterTopicsRequest request = new ListClusterTopicsRequest();
        rocketMQClient.listClusterTopics(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterTopics_EmptyClusterId() {
        ListClusterTopicsRequest request = new ListClusterTopicsRequest();
        request.setClusterId("");
        rocketMQClient.listClusterTopics(request);
    }

    @Test
    public void testListClusterBrokerNodes() {
        ListClusterBrokerNodesRequest request = new ListClusterBrokerNodesRequest();
        request.setClusterId(clusterId);
        request.setMarker("test-marker");
        request.setMaxKeys(100);

        ListClusterBrokerNodesResponse mockResponse = new ListClusterBrokerNodesResponse();
        when(mockHttpClient.execute(any(), eq(ListClusterBrokerNodesResponse.class), any())).thenReturn(mockResponse);

        ListClusterBrokerNodesResponse response = rocketMQClient.listClusterBrokerNodes(request);

        assertNotNull(response);
        verify(mockHttpClient).execute(any(), eq(ListClusterBrokerNodesResponse.class), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterBrokerNodesWithNullRequest() {
        rocketMQClient.listClusterBrokerNodes(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClusterBrokerNodesWithEmptyClusterId() {
        ListClusterBrokerNodesRequest request = new ListClusterBrokerNodesRequest();
        rocketMQClient.listClusterBrokerNodes(request);
    }
}
