/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.CreateBecAppBlbIpGroupBackendPolicyRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.CreateBecAppBlbIpGroupBackendPolicyResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.CreateBecAppBlbIpGroupMembersRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.CreateBecAppBlbIpGroupMembersResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.CreateBecAppBlbIpGroupRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.CreateBecAppBlbIpGroupResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.DeleteBecAppBlbIpGroupBackendPoliciesRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.DeleteBecAppBlbIpGroupBackendPoliciesResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.DeleteBecAppBlbIpGroupMembersRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.DeleteBecAppBlbIpGroupMembersResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.DeleteBecAppBlbIpGroupRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.DeleteBecAppBlbIpGroupResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.GetBecAppBlbIpGroupBackendPoliciesRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.GetBecAppBlbIpGroupBackendPoliciesResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.GetBecAppBlbIpGroupMembersRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.GetBecAppBlbIpGroupMembersResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.GetBecAppBlbIpGroupsRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.GetBecAppBlbIpGroupsResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupBackendPolicyRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupBackendPolicyResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupMembersRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupMembersResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupResponse;
import com.baidubce.services.bec.model.appblbv2.instance.CreateBecAppBlbRequest;
import com.baidubce.services.bec.model.appblbv2.instance.CreateBecAppBlbResponse;
import com.baidubce.services.bec.model.appblbv2.instance.DeleteBecAppBlbRequest;
import com.baidubce.services.bec.model.appblbv2.instance.DeleteBecAppBlbResponse;
import com.baidubce.services.bec.model.appblbv2.instance.GetBecAppBlbRequest;
import com.baidubce.services.bec.model.appblbv2.instance.GetBecAppBlbResponse;
import com.baidubce.services.bec.model.appblbv2.instance.GetBecAppBlbsRequest;
import com.baidubce.services.bec.model.appblbv2.instance.GetBecAppBlbsResponse;
import com.baidubce.services.bec.model.appblbv2.instance.UpdateBecAppBlbRequest;
import com.baidubce.services.bec.model.appblbv2.instance.UpdateBecAppBlbResponse;
import com.baidubce.services.bec.model.appblbv2.listener.BatchDeleteBecAppBlbListenersRequest;
import com.baidubce.services.bec.model.appblbv2.listener.BatchDeleteBecAppBlbListenersResponse;
import com.baidubce.services.bec.model.appblbv2.listener.CreateBecAppBlbListenerPoliciesRequest;
import com.baidubce.services.bec.model.appblbv2.listener.CreateBecAppBlbListenerPoliciesResponse;
import com.baidubce.services.bec.model.appblbv2.listener.CreateBecAppBlbTCPListenerRequest;
import com.baidubce.services.bec.model.appblbv2.listener.CreateBecAppBlbTCPListenerResponse;
import com.baidubce.services.bec.model.appblbv2.listener.CreateBecAppBlbUDPListenerRequest;
import com.baidubce.services.bec.model.appblbv2.listener.CreateBecAppBlbUDPListenerResponse;
import com.baidubce.services.bec.model.appblbv2.listener.DeleteBecAppBlbListenerPoliciesRequest;
import com.baidubce.services.bec.model.appblbv2.listener.DeleteBecAppBlbListenerPoliciesResponse;
import com.baidubce.services.bec.model.appblbv2.listener.GetBecAppBlbListenerPoliciesRequest;
import com.baidubce.services.bec.model.appblbv2.listener.GetBecAppBlbListenerPoliciesResponse;
import com.baidubce.services.bec.model.appblbv2.listener.GetBecAppBlbTCPListenersRequest;
import com.baidubce.services.bec.model.appblbv2.listener.GetBecAppBlbTCPListenersResponse;
import com.baidubce.services.bec.model.appblbv2.listener.GetBecAppBlbUDPListenersRequest;
import com.baidubce.services.bec.model.appblbv2.listener.GetBecAppBlbUDPListenersResponse;
import com.baidubce.services.bec.model.appblbv2.listener.UpdateBecAppBlbTCPListenerRequest;
import com.baidubce.services.bec.model.appblbv2.listener.UpdateBecAppBlbTCPListenerResponse;
import com.baidubce.services.bec.model.appblbv2.listener.UpdateBecAppBlbUDPListenerRequest;
import com.baidubce.services.bec.model.appblbv2.listener.UpdateBecAppBlbUDPListenerResponse;
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
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortDetailsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortDetailsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortListRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortListResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbInstanceRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbResourceMetricsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbResourceMetricsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbInstanceResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbsResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbBindPodWeightRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbBindPodWeightResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbMonitorPortRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbMonitorPortResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbResponse;
import com.baidubce.services.bec.model.handler.BecHttpResponseHandler;
import com.baidubce.services.bec.model.network.acl.BatchCreateBecAclRulesRequest;
import com.baidubce.services.bec.model.network.acl.BatchCreateBecAclRulesResponse;
import com.baidubce.services.bec.model.network.acl.BatchDeleteBecAclRulesRequest;
import com.baidubce.services.bec.model.network.acl.BatchDeleteBecAclRulesResponse;
import com.baidubce.services.bec.model.network.acl.GetBecAclRequest;
import com.baidubce.services.bec.model.network.acl.GetBecAclResponse;
import com.baidubce.services.bec.model.network.acl.GetBecAclsRequest;
import com.baidubce.services.bec.model.network.acl.GetBecAclsResponse;
import com.baidubce.services.bec.model.network.acl.UpdateBecAclRequest;
import com.baidubce.services.bec.model.network.acl.UpdateBecAclResponse;
import com.baidubce.services.bec.model.network.acl.UpdateBecAclRuleRequest;
import com.baidubce.services.bec.model.network.acl.UpdateBecAclRuleResponse;
import com.baidubce.services.bec.model.network.nat.dnatrule.BatchCreateBecDnatRulesRequest;
import com.baidubce.services.bec.model.network.nat.dnatrule.BatchCreateBecDnatRulesResponse;
import com.baidubce.services.bec.model.network.nat.dnatrule.BatchDeleteBecDnatRulesRequest;
import com.baidubce.services.bec.model.network.nat.dnatrule.BatchDeleteBecDnatRulesResponse;
import com.baidubce.services.bec.model.network.nat.dnatrule.CreateBecDnatRuleRequest;
import com.baidubce.services.bec.model.network.nat.dnatrule.CreateBecDnatRuleResponse;
import com.baidubce.services.bec.model.network.nat.dnatrule.GetBecDnatRulesRequest;
import com.baidubce.services.bec.model.network.nat.dnatrule.GetBecDnatRulesResponse;
import com.baidubce.services.bec.model.network.nat.dnatrule.UpdateBecDnatRuleRequest;
import com.baidubce.services.bec.model.network.nat.dnatrule.UpdateBecDnatRuleResponse;
import com.baidubce.services.bec.model.network.nat.instance.BatchCreateBecNatsRequest;
import com.baidubce.services.bec.model.network.nat.instance.BatchCreateBecNatsResponse;
import com.baidubce.services.bec.model.network.nat.instance.BatchDeleteBecNatsRequest;
import com.baidubce.services.bec.model.network.nat.instance.BatchDeleteBecNatsResponse;
import com.baidubce.services.bec.model.network.nat.instance.GetBecNatMetricsRequest;
import com.baidubce.services.bec.model.network.nat.instance.GetBecNatMetricsResponse;
import com.baidubce.services.bec.model.network.nat.instance.GetBecNatRequest;
import com.baidubce.services.bec.model.network.nat.instance.GetBecNatResponse;
import com.baidubce.services.bec.model.network.nat.instance.GetBecNatsRequest;
import com.baidubce.services.bec.model.network.nat.instance.GetBecNatsResponse;
import com.baidubce.services.bec.model.network.nat.instance.UpdateBecNatBandwidthRequest;
import com.baidubce.services.bec.model.network.nat.instance.UpdateBecNatBandwidthResponse;
import com.baidubce.services.bec.model.network.nat.instance.UpdateBecNatRequest;
import com.baidubce.services.bec.model.network.nat.instance.UpdateBecNatResponse;
import com.baidubce.services.bec.model.network.nat.snatrule.BatchCreateBecSnatRulesRequest;
import com.baidubce.services.bec.model.network.nat.snatrule.BatchCreateBecSnatRulesResponse;
import com.baidubce.services.bec.model.network.nat.snatrule.BatchDeleteBecSnatRulesRequest;
import com.baidubce.services.bec.model.network.nat.snatrule.BatchDeleteBecSnatRulesResponse;
import com.baidubce.services.bec.model.network.nat.snatrule.CreateBecSnatRuleRequest;
import com.baidubce.services.bec.model.network.nat.snatrule.CreateBecSnatRuleResponse;
import com.baidubce.services.bec.model.network.nat.snatrule.GetBecSnatRulesRequest;
import com.baidubce.services.bec.model.network.nat.snatrule.GetBecSnatRulesResponse;
import com.baidubce.services.bec.model.network.nat.snatrule.UpdateBecSnatRuleRequest;
import com.baidubce.services.bec.model.network.nat.snatrule.UpdateBecSnatRuleResponse;
import com.baidubce.services.bec.model.network.route.CreateBecRouteRuleRequest;
import com.baidubce.services.bec.model.network.route.CreateBecRouteRuleResponse;
import com.baidubce.services.bec.model.network.route.DeleteBecRouteRuleRequest;
import com.baidubce.services.bec.model.network.route.DeleteBecRouteRuleResponse;
import com.baidubce.services.bec.model.network.route.GetBecRouteRulesRequest;
import com.baidubce.services.bec.model.network.route.GetBecRouteRulesResponse;
import com.baidubce.services.bec.model.network.route.GetBecRouteTableRequest;
import com.baidubce.services.bec.model.network.route.GetBecRouteTableResponse;
import com.baidubce.services.bec.model.network.route.GetBecRouteTablesRequest;
import com.baidubce.services.bec.model.network.route.GetBecRouteTablesResponse;
import com.baidubce.services.bec.model.network.route.UpdateBecRouteTableRequest;
import com.baidubce.services.bec.model.network.route.UpdateBecRouteTableResponse;
import com.baidubce.services.bec.model.network.securitygroup.BatchCreateBecSgRulesRequest;
import com.baidubce.services.bec.model.network.securitygroup.BatchCreateBecSgRulesResponse;
import com.baidubce.services.bec.model.network.securitygroup.BatchDeleteBecSgRulesRequest;
import com.baidubce.services.bec.model.network.securitygroup.BatchDeleteBecSgRulesResponse;
import com.baidubce.services.bec.model.network.securitygroup.CreateBecSecurityGroupRequest;
import com.baidubce.services.bec.model.network.securitygroup.CreateBecSecurityGroupResponse;
import com.baidubce.services.bec.model.network.securitygroup.DeleteBecSecurityGroupRequest;
import com.baidubce.services.bec.model.network.securitygroup.DeleteBecSecurityGroupResponse;
import com.baidubce.services.bec.model.network.securitygroup.GetBecSecurityGroupRequest;
import com.baidubce.services.bec.model.network.securitygroup.GetBecSecurityGroupResponse;
import com.baidubce.services.bec.model.network.securitygroup.GetBecSecurityGroupsRequest;
import com.baidubce.services.bec.model.network.securitygroup.GetBecSecurityGroupsResponse;
import com.baidubce.services.bec.model.network.securitygroup.UpdateBecSecurityGroupRequest;
import com.baidubce.services.bec.model.network.securitygroup.UpdateBecSecurityGroupResponse;
import com.baidubce.services.bec.model.network.securitygroup.UpdateBecSecurityGroupRuleRequest;
import com.baidubce.services.bec.model.network.securitygroup.UpdateBecSecurityGroupRuleResponse;
import com.baidubce.services.bec.model.network.subnet.CreateBecSubnetRequest;
import com.baidubce.services.bec.model.network.subnet.CreateBecSubnetResponse;
import com.baidubce.services.bec.model.network.subnet.DeleteBecSubnetRequest;
import com.baidubce.services.bec.model.network.subnet.DeleteBecSubnetResponse;
import com.baidubce.services.bec.model.network.subnet.GetBecSubnetRequest;
import com.baidubce.services.bec.model.network.subnet.GetBecSubnetResponse;
import com.baidubce.services.bec.model.network.subnet.GetBecSubnetsRequest;
import com.baidubce.services.bec.model.network.subnet.GetBecSubnetsResponse;
import com.baidubce.services.bec.model.network.subnet.UpdateBecSubnetRequest;
import com.baidubce.services.bec.model.network.subnet.UpdateBecSubnetResponse;
import com.baidubce.services.bec.model.network.vpc.CreateBecVpcRequest;
import com.baidubce.services.bec.model.network.vpc.CreateBecVpcResponse;
import com.baidubce.services.bec.model.network.vpc.DeleteBecVpcRequest;
import com.baidubce.services.bec.model.network.vpc.DeleteBecVpcResponse;
import com.baidubce.services.bec.model.network.vpc.GetBecVpcRequest;
import com.baidubce.services.bec.model.network.vpc.GetBecVpcResponse;
import com.baidubce.services.bec.model.network.vpc.GetBecVpcsRequest;
import com.baidubce.services.bec.model.network.vpc.GetBecVpcsResponse;
import com.baidubce.services.bec.model.network.vpc.UpdateBecVpcRequest;
import com.baidubce.services.bec.model.network.vpc.UpdateBecVpcResponse;
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
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesResponse;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesResponse;
import com.baidubce.services.bec.model.vm.deployset.CreateBecDeploySetRequest;
import com.baidubce.services.bec.model.vm.deployset.CreateBecDeploySetResponse;
import com.baidubce.services.bec.model.vm.deployset.DeleteBecDeploySetInstancesRequest;
import com.baidubce.services.bec.model.vm.deployset.DeleteBecDeploySetInstancesResponse;
import com.baidubce.services.bec.model.vm.deployset.DeleteBecDeploySetRequest;
import com.baidubce.services.bec.model.vm.deployset.DeleteBecDeploySetResponse;
import com.baidubce.services.bec.model.vm.deployset.GetBecDeploySetRequest;
import com.baidubce.services.bec.model.vm.deployset.GetBecDeploySetResponse;
import com.baidubce.services.bec.model.vm.deployset.GetBecDeploySetsRequest;
import com.baidubce.services.bec.model.vm.deployset.GetBecDeploySetsResponse;
import com.baidubce.services.bec.model.vm.deployset.UpdateBecDeploySetInstanceRequest;
import com.baidubce.services.bec.model.vm.deployset.UpdateBecDeploySetInstanceResponse;
import com.baidubce.services.bec.model.vm.deployset.UpdateBecDeploySetRequest;
import com.baidubce.services.bec.model.vm.deployset.UpdateBecDeploySetResponse;
import com.baidubce.services.bec.model.vm.image.BatchDeleteBecVmImageRequest;
import com.baidubce.services.bec.model.vm.image.BatchDeleteBecVmImageResponse;
import com.baidubce.services.bec.model.vm.image.CreateBecVmImageRequest;
import com.baidubce.services.bec.model.vm.image.CreateBecVmImageResponse;
import com.baidubce.services.bec.model.vm.image.GetBecVmImagesRequest;
import com.baidubce.services.bec.model.vm.image.GetBecVmImagesResponse;
import com.baidubce.services.bec.model.vm.image.UpdateBecVmImageRequest;
import com.baidubce.services.bec.model.vm.image.UpdateBecVmImageResponse;
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
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkMultyParamsNotBothEmpty;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Cloud Edge Compute Service(bec).
 */
public class BecClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String VERSION_2 = "v2";
    private static final String OVERVIEW = "overview";
    private static final String SUMMARY = "summary";
    private static final String CONTAINER = "container";
    private static final String BLB_PREFIX = "blb";
    private static final String LB_PREFIX = "lb";
    private static final String VM_PREFIX = "vm";
    private static final String NODE_PREFIX = "node";
    private static final String DEPLOY_SET_PREFIX = "deployset";
    private static final String VPC_PREFIX = "vpc";
    private static final String SUBNET_PREFIX = "subnet";
    private static final String SECURITY_GROUP_PREFIX = "securityGroup";
    private static final String ROUTE_PREFIX = "route";
    private static final String APP_BLB_PREFIX = "appblb";
    private static final String NAT_PREFIX = "nat";
    private static final String SERVICE = "service";
    private static final String INSTANCE = "instance";
    private static final String TEMPLATE = "template";
    private static final String IMAGE = "image";
    private static final String METRICS = "metrics";
    private static final String ACL = "acl";
    private static final String RULE = "rule";
    private static final String BATCH = "batch";
    private static final String DELETE = "delete";
    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String DETAIL = "detail";
    private static final String REGIONS = "regions";
    private static final String SERVICE_PROVIDER = "sps";
    private static final String CITIES = "cities";
    private static final String SYSTEM = "system";
    private static final String CONFIG = "config";
    private static final String REINSTALL = "reinstall";
    private static final String MONITOR = "monitor";
    private static final String RESOURCE = "resource";
    private static final String PACKAGE = "package";
    private static final String DISK = "disk";
    private static final String PASS_THROUGH = "passthrough";
    private static final String BINDED = "binded";
    private static final String BINDING = "binding";
    private static final String BINDINGPOD = "bindingpod";
    private static final String PORT = "port";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String CREATE = "create";
    private static final String LIST = "list";
    private static final String DEL_RELATION = "delRelation";
    private static final String UPDATE_RELATION = "updateRelation";
    private static final String TCP_LISTENER = "TCPlistener";
    private static final String UDP_LISTENER = "UDPlistener";
    private static final String POLICYS = "policys";
    private static final String LISTENER = "listener";
    private static final String IP_GROUP = "ipgroup";
    private static final String UPDATE_BANDWIDTH = "updateBandwidth";
    private static final String BACKEND_POLICY = "backendpolicy";
    private static final String MEMBER = "member";
    private static final String SNAT_RULE = "snatRule";
    private static final String DNAT_RULE = "dnatRule";
    private static final String BATCH_CREATE = "batchCreate";
    private static final String BATCH_DELETE = "batchdelete";

    /**
     * List selection keywords.
     */
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE = "offsetInSeconds should not be null.";
    private static final String BLB_ID_MESSAGE_KEY = "blbId";
    private static final String BLB_TYPE_MESSAGE_KEY = "type";
    private static final String IP_TYPE_MESSAGE_KEY = "ipType";
    private static final String VM_SERVICE_ID_MESSAGE_KEY = "serviceId";
    private static final String VM_ID_MESSAGE_KEY = "vmId";
    private static final String TEMPLATE_ID_MESSAGE_KEY = "templateId";
    private static final String VM_INSTANCE_REGION_MESSAGE_KEY = "region";
    private static final String VM_INSTANCE_SPS_MESSAGE_KEY = "serviceProvider";
    private static final String VM_INSTANCE_CITY_MESSAGE_KEY = "city";
    private static final String VM_ACTION_MESSAGE_KEY = "action";
    private static final String IMAGE_ID_MESSAGE_KEY = "imageId";
    private static final String IMAGE_ID_LIST_MESSAGE_KEY = "imageIdList";
    private static final String METRICS_TYPE_MESSAGE_KEY = "metricsType";
    private static final String START_MESSAGE_KEY = "start";
    private static final String END_MESSAGE_KEY = "end";
    private static final String DEPLOY_SET_ID_MESSAGE_KEY = "deploySetId";
    private static final String VPC_ID_MESSAGE_KEY = "vpcId";
    private static final String SUBNET_ID_MESSAGE_KEY = "subnetId";
    private static final String INSTANCE_ID_MESSAGE_KEY = "instanceId";
    private static final String SECURITY_GROUP_ID_MESSAGE_KEY = "securityGroupId";
    private static final String SECURITY_GROUP_RULE_ID_MESSAGE_KEY = "securityGroupRuleId";
    private static final String ROUTE_TABLE_ID_MESSAGE_KEY = "tableId";
    private static final String ACL_RULE_ID_MESSAGE_KEY = "aclRuleId";
    private static final String ROUTE_RULE_ID_MESSAGE_KEY = "ruleId";
    private static final String ACL_ID_MESSAGE_KEY = "aclId";
    private static final String NAT_ID_MESSAGE_KEY = "natId";
    private static final String RULE_ID_MESSAGE_KEY = "ruleId";
    private static final String IP_GROUP_ID_MESSAGE_KEY = "ipGroupId";
    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all bec service calls.
     */
    private static final HttpResponseHandler[] bec_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BecHttpResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on bec.
     */
    public BecClient() {
        this(new BecClientConfiguration());
    }

    /**
     * Constructs a new bec client using the client configuration to access bec.
     *
     * @param clientConfiguration The bec client configuration options controlling how this client
     *                            connects to bec (e.g. proxy settings, retry counts, etc).
     */
    public BecClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, bec_handlers);
    }

    /**
     * Creates and initializes a new request object for the specified network resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        return commonRequestProcess(bceRequest, httpMethod, path);
    }

    /**
     * Creates and initializes a new v2 request object for the specified network resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new v2 request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequestV2(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                            String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION_2);
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        return commonRequestProcess(bceRequest, httpMethod, path);
    }

    /**
     * Common Request Process.
     *
     * @param bceRequest
     * @param httpMethod
     * @param path
     * @return final InternalRequest.
     */
    private InternalRequest commonRequestProcess(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                                 List<String> path) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * the method to fill the internalRequest's content field with bceRequest
     * Caution: only support HttpMethodName.POST or HttpMethodName.PUT.
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson = null;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
    }

    /**
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     * <p/>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }


    /**
     * Create a new BEC virtual machine api.
     *
     * @param request: The request containing all options for creating a bec virtual machine service.
     * @return: The response with id of service newly created.
     */
    public CreateBecVmServiceResponse createBecVmService(CreateBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VM_PREFIX, SERVICE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVmServiceResponse.class);
    }

    /**
     * create BEC virtual machine instances for service api
     *
     * @param request: The request contains all options to create a bec virtual machine for the service.
     * @return: The response with id of service.
     */
    public CreateBecVmServiceResponse createBecVmServiceInstances(CreateBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VM_PREFIX, SERVICE,
                request.getServiceId(), INSTANCE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVmServiceResponse.class);
    }

    /**
     * Get BEC virtual machine service list
     *
     * @param request: The request contains all options for getting a list of BEC virtual machine services.
     * @return: paged api list, for brief info.
     */
    public GetBecVmServicesResponse getBecVmServices(GetBecVmServicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET
                , VM_PREFIX, SERVICE);
        if (StringUtils.isNotEmpty(request.getKeywordType())) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }
        if (StringUtils.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        return invokeHttpClient(internalRequest, GetBecVmServicesResponse.class);
    }

    /**
     * Update BEC virtual machine service.
     *
     * @param request: The request containing all options for updating the virtual machine service.
     * @return: The response contains information about whether the service was successfully updated.
     */
    public UpdateBecVmServiceResponse updateBecVmService(UpdateBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                VM_PREFIX, SERVICE, request.getServiceId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecVmServiceResponse.class);
    }

    /**
     * Get BEC virtual machine service details.
     *
     * @param request: The request containing all options for getting the api details.
     * @return: BEC virtual machine service details.
     */
    public GetBecVmServiceResponse getBecVmService(GetBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET
                , VM_PREFIX, SERVICE, request.getServiceId());
        return invokeHttpClient(internalRequest, GetBecVmServiceResponse.class);
    }

    /**
     * start/stop/release the BEC virtual machine service.
     *
     * @param request: The request contains all options for operating the virtual machine service.
     * @return: The response contains the result of operating the virtual machine service.
     */
    public BecVmServiceActionResponse becVmServiceAction(BecVmServiceActionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAction()
                , checkEmptyExceptionMessageFormat(VM_ACTION_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                VM_PREFIX, SERVICE, request.getServiceId(), request.getAction());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        return invokeHttpClient(internalRequest, BecVmServiceActionResponse.class);
    }

    /**
     * Delete BEC virtual machine service.
     *
     * @param request: The request contains the api ID that should be deleted.
     * @return: The response contains the result of whether the service was successfully deleted.
     */
    public DelBecVmServiceResponse deleteBecVmService(DelBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE,
                VM_PREFIX, SERVICE, request.getServiceId());
        return invokeHttpClient(internalRequest, DelBecVmServiceResponse.class);
    }

    /**
     * Get BEC service metrics.
     *
     * @param request: The request containing all options for getting BEC service metrics.
     * @return: The response contains BEC service metrics.
     */
    public GetBecVmServiceMetricsResponse getBecVmServiceMetrics(GetBecVmServiceMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getType()
                , checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        checkNotNull(request.getOffsetInSeconds(), OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                MONITOR, SERVICE, VM_PREFIX, request.getServiceId());
        if (StringUtils.isNotEmpty(request.getServiceId())) {
            internalRequest.addParameter("serviceId", request.getServiceId());
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecVmServiceMetricsResponse.class);

    }

    /**
     * Get the list of BEC virtual machine instances.
     *
     * @param request: optional query parameter, the keyword for deployment name.
     * @return: paged deployment list.
     */
    public GetBecVmInstanceListResponse getBecVmInstanceList(GetBecVmInstanceListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                VM_PREFIX, INSTANCE);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getOrder())) {
                internalRequest.addParameter("order", request.getListRequest().getOrder());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getOrderBy())) {
                internalRequest.addParameter("orderBy", request.getListRequest().getOrderBy());
            }
        }
        return invokeHttpClient(internalRequest, GetBecVmInstanceListResponse.class);
    }

    /**
     * Get the BEC virtual machine list of the node.
     *
     * @param request: optional query parameter, the keyword for deployment name.
     * @return: deployment list.
     */
    public GetBecNodeVmInstanceListResponse getBecNodeVmInstanceList(GetBecNodeVmInstanceListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getRegion(), checkEmptyExceptionMessageFormat(VM_INSTANCE_REGION_MESSAGE_KEY));
        checkStringNotEmpty(request.getServiceProvider(),
                checkEmptyExceptionMessageFormat(VM_INSTANCE_SPS_MESSAGE_KEY));
        checkStringNotEmpty(request.getCity(), checkEmptyExceptionMessageFormat(VM_INSTANCE_CITY_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, INSTANCE, REGIONS,
                request.getRegion(), SERVICE_PROVIDER, request.getServiceProvider(), CITIES, request.getCity());
        return invokeHttpClient(internalRequest, GetBecNodeVmInstanceListResponse.class);
    }

    /**
     * Get the details of the BEC virtual machine.
     *
     * @param request: The request contains all the options for getting the details of the BEC virtual machine.
     * @return: deployment details.
     */
    public GetBecVirtualMachineResponse getBecVmInstance(GetBecVirtualMachineRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, INSTANCE,
                request.getVmID());
        return invokeHttpClient(internalRequest, GetBecVirtualMachineResponse.class);
    }

    /**
     * Delete BEC virtual machine.
     *
     * @param request: The request contains all options for deleting the BEC virtual machine.
     * @return: The response contains the result of whether the Instance was successfully deleted.
     */
    public DeleteBecVmInstanceResponse deleteBecVmInstance(DeleteBecVmInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, VM_PREFIX, INSTANCE,
                request.getVmID());
        return invokeHttpClient(internalRequest, DeleteBecVmInstanceResponse.class);
    }

    /**
     * Update BEC virtual machine resources.
     *
     * @param request: The request contains all the options for updating BEC virtual machine resources.
     * @return: The response contains information about whether the instance was successfully updated.
     */
    public UpdateBecVmDeploymentResponse updateBecVmDeployment(UpdateBecVmDeploymentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VM_PREFIX, INSTANCE,
                request.getVmID());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecVmDeploymentResponse.class);
    }

    /**
     * Reinstall the BEC virtual machine system.
     *
     * @param request: The request contains all the options for reinstalling the BEC virtual machine system.
     * @return: The response contains information on whether the system reinstallation was successful.
     */
    public ReinstallBecVmInstanceResponse reinstallBecVmInstance(ReinstallBecVmInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VM_PREFIX, INSTANCE,
                request.getVmID(), SYSTEM, REINSTALL);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ReinstallBecVmInstanceResponse.class);
    }

    /**
     * start/stop/restart the BEC virtual machine instance.
     *
     * @param request: The request contains all options for operating the BEC virtual machine instance.
     * @return: The response contains the result of operating the virtual machine instance.
     */
    public OperateBecVmDeploymentResponse operateBecVmDeployment(OperateBecVmDeploymentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAction(), checkEmptyExceptionMessageFormat(VM_ACTION_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VM_PREFIX, INSTANCE,
                request.getVmID(), request.getAction());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, OperateBecVmDeploymentResponse.class);
    }

    /**
     * Get the BEC virtual machine instance metrics.
     *
     * @param request: The request containing all options for getting bec virtual machine instance metrics.
     * @return: The response contains BEC virtual machine instance metrics.
     */
    public GetBecVmInstanceMetricsResponse getBecVmInstanceMetrics(GetBecVmInstanceMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmId(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                MONITOR, VM_PREFIX, request.getVmId());
        if (StringUtils.isNotEmpty(request.getVmId())) {
            internalRequest.addParameter("vmId", request.getVmId());
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecVmInstanceMetricsResponse.class);
    }

    /**
     * Get the BEC node level virtual machine instance metrics.
     *
     * @param request: The request containing all options for getting bec node level virtual machine instance metrics.
     * @return: The response contains BEC node level virtual machine instance metrics.
     */
    public GetBecVmNodeLevelMetricsResponse getVmNodeMetrics(GetBecVmNodeLevelMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getRegion(), checkEmptyExceptionMessageFormat(VM_INSTANCE_REGION_MESSAGE_KEY));
        checkStringNotEmpty(request.getCity(), checkEmptyExceptionMessageFormat(VM_INSTANCE_CITY_MESSAGE_KEY));
        checkStringNotEmpty(request.getServiceProvider(),
                checkEmptyExceptionMessageFormat(VM_INSTANCE_SPS_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                MONITOR, OVERVIEW, VM_PREFIX, NODE_PREFIX, request.getType());

        if (request.getStart() > 0 && request.getEnd() > 0) {
            internalRequest.addParameter("start", String.valueOf(request.getStart()));
            internalRequest.addParameter("end", String.valueOf(request.getEnd()));
        }

        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }

        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("city", request.getCity());
        }

        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("region", request.getRegion());
        }

        if (StringUtils.isNotEmpty(request.getNetwork())) {
            internalRequest.addParameter("network", request.getNetwork());
        }

        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }

        return invokeHttpClient(internalRequest, GetBecVmNodeLevelMetricsResponse.class);

    }

    /**
     * Get the BEC virtual machine instance config.
     *
     * @param request: The request containing all options for getting BEC virtual machine instance config.
     * @return: The response contains BEC virtual machine instance config.
     */
    public GetBecVmConfigResponse getBecVmConfig(GetBecVmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, INSTANCE,
                request.getVmID(), CONFIG);
        return invokeHttpClient(internalRequest, GetBecVmConfigResponse.class);
    }

    /**
     * Create a virtual machine template.
     *
     * @param request: The request containing all options for creating the BEC vm template.
     * @return: The response with id of template newly created.
     */
    public CreateBecVmTemplateResponse createBecVmTemplate(CreateBecVmTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VM_PREFIX, TEMPLATE, CREATE);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVmTemplateResponse.class);
    }

    /**
     * Get the list of BEC virtual machine templates.
     *
     * @param request: The request containing all options for getting the BEC vm template list.
     * @return: paged template list.
     */
    public GetBecVmTemplateListResponse listBecVmTemplate(GetBecVmTemplateListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, TEMPLATE, LIST);

        if (StringUtils.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }

        if (StringUtils.isNotEmpty(request.getKeywordType())) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }

        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }

        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }

        if (request.getPageNo() != 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }

        if (request.getPageSize() != 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, GetBecVmTemplateListResponse.class);
    }

    /**
     * Get BEC virtual machine template details.
     *
     * @param request: The request containing all options for getting the teplate details.
     * @return: BEC virtual machine template details.
     */
    public GetBecVmTemplateResponse getBecVmTemplate(GetBecVmTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getTemplateId(), checkEmptyExceptionMessageFormat(TEMPLATE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, TEMPLATE,
                request.getTemplateId());
        return invokeHttpClient(internalRequest, GetBecVmTemplateResponse.class);
    }

    /**
     * Create a BEC blb with the specified options.
     *
     * @param request: The request containing all options for creating the BEC blb.
     * @return: The response contains whether the BEC blb was successfully created.
     */
    public CreateBecBlbResponse createBecBlb(CreateBecBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BLB_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecBlbResponse.class);
    }

    /**
     * Get the BEC blb list.
     *
     * @param request: The request containing all options for getting the BEC blb list.
     * @return: The response contains the created BEC blb list.
     */
    public GetBecBlbsResponse getBecBlbs(GetBecBlbsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX);
        if (StringUtils.isNotEmpty(request.getLbType())) {
            internalRequest.addParameter("lbType", request.getLbType());
        }
        if (request.getLbType() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getLbType() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, GetBecBlbsResponse.class);
    }

    /**
     * Get the specific BEC blb instance.
     *
     * @param request: The request containing all options for getting the BEC blb instance info.
     * @return: The response contains the created BEC blb instance.
     */
    public GetBecBlbInstanceResponse getBecBlb(GetBecBlbInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId());
        return invokeHttpClient(internalRequest, GetBecBlbInstanceResponse.class);
    }

    /**
     * Delete the specific BEC blb instance.
     *
     * @param request: The request containing all options for deleting the BEC blb instance.
     * @return: The response contains information about whether the BEC blb instance was successfully deleted.
     */
    public DeleteBecBlbResponse deleteBecBlb(DeleteBecBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE
                , BLB_PREFIX, request.getBlbId());
        return invokeHttpClient(internalRequest, DeleteBecBlbResponse.class);
    }

    /**
     * Update the specific BEC blb instance.
     *
     * @param request: The request containing all options for updating the BEC blb instance.
     * @return: The response contains information about whether the BLB instance was successfully updated.
     */
    public UpdateBecBlbResponse updateBecBlb(UpdateBecBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, BLB_PREFIX, request.getBlbId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecBlbResponse.class);
    }

    /**
     * Create the BEC blb monitor port for assign blb.
     *
     * @param request: The request containing all options for creating the BEC blb monitor port.
     * @return: The response contains information on whether the BEC blb monitor port was successfully created.
     */
    public CreateBecBlbMonitorPortResponse createBecBlbMonitorPort(CreateBecBlbMonitorPortRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BLB_PREFIX,
                request.getBlbId(), MONITOR);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecBlbMonitorPortResponse.class);
    }

    /**
     * Get the BEC blb port monitor list for assign blb.
     *
     * @param request: The request containing all options for getting the BEC blb port monitor list.
     * @return paged blb listeners port list, for brief info
     */
    public GetBecBlbMonitorPortListResponse getBlbMonitorPortList(GetBecBlbMonitorPortListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), MONITOR);
        return invokeHttpClient(internalRequest, GetBecBlbMonitorPortListResponse.class);

    }

    /**
     * Get the BEC blb monitor port details for assign blb and assign port.
     *
     * @param request: The request containing all options for getting the BEC blb monitor port details.
     * @return: The response contains the BEC blb monitor port details.
     */
    public GetBecBlbMonitorPortDetailsResponse getBecBlbMonitorPortDetails(GetBecBlbMonitorPortDetailsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), MONITOR, PORT);
        if (request.getProtocol() != null) {
            internalRequest.addParameter("protocol", String.valueOf(request.getProtocol()));
        }
        if (request.getPort() != 0) {
            internalRequest.addParameter("port", String.valueOf(request.getPort()));
        }
        return invokeHttpClient(internalRequest, GetBecBlbMonitorPortDetailsResponse.class);
    }

    /**
     * Update the BEC blb monitor port for assign blb.
     *
     * @param request: The request containing all options for updating the Blb monitor port.
     * @return: The response contains information about whether the BEC blb monitor port was successfully updated.
     */
    public UpdateBecBlbMonitorPortResponse updateBecBlbMonitorPort(UpdateBecBlbMonitorPortRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, BLB_PREFIX,
                request.getBlbId(), MONITOR);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecBlbMonitorPortResponse.class);
    }

    /**
     * Get the bind BEC blb backend Pod/Vm list for assign blb.
     *
     * @param request: The request containing all options for getting the bind BEC blb backend Pod/Vm list.
     * @return: paged blb backend pod list, for brief info.
     */
    public GetBecBlbBackendPodListResponse getBecBlbBackendPodList(GetBecBlbBackendPodListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), BINDED);
        return invokeHttpClient(internalRequest, GetBecBlbBackendPodListResponse.class);
    }

    /**
     * Get the binding BEC blb backend StatefulSet/VmReplicas list for assign blb.
     *
     * @param request: The request containing all options for getting the binding BEC blb backend
     *                 StatefulSet/VmReplicas list.
     * @return: paged blb backend StatefulSet/VmReplicas list, for brief info.
     */
    public GetBecBlbBackendBindingStsListResponse getBecBlbBackendBindingStsList(GetBecBlbBackendBindingStsListRequest
                                                                                         request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), BINDING);
        return invokeHttpClient(internalRequest, GetBecBlbBackendBindingStsListResponse.class);
    }

    /**
     * Get the binding BEC blb backend Pod/Vm list for assign blb.
     *
     * @param request: The request containing all options for getting the binding BEC blb backend Pod/Vm list.
     * @return: paged blb backend Pod/Vm list, for brief info.
     */
    public GetBecBlbBindingPodListWithStsResponse getBecBlbBindingPodListWithSts(GetBecBlbBindingPodListWithStsRequest
                                                                                         request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX
                , request.getBlbId(), BINDINGPOD);
        if (StringUtils.isNotEmpty(request.getStsName())) {
            internalRequest.addParameter("stsName", request.getStsName());
        }
        return invokeHttpClient(internalRequest, GetBecBlbBindingPodListWithStsResponse.class);
    }

    /**
     * Bind the backend StatefulSet/VmReplicas to the specified BEC blb.
     *
     * @param request: The request containing all options for binding the backend StatefulSet/VmReplicas.
     * @return: The response contains information about whether the binding was successful.
     */
    public CreateBecBlbBindingResponse createBecBlbBinding(CreateBecBlbBindingRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BLB_PREFIX
                , request.getBlbId(), BINDING);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecBlbBindingResponse.class);
    }

    /**
     * Modify the weight of the Pod/Vm bound to the specified BEC BLB backend.
     *
     * @param request: The request containing all options for modifying the weight of the Pod/Vm.
     * @return: The response contains information about whether the weight modification was successful.
     */
    public UpdateBecBlbBindPodWeightResponse updateBecBlbBindPodWeight(UpdateBecBlbBindPodWeightRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, BLB_PREFIX
                , request.getBlbId(), BINDED);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecBlbBindPodWeightResponse.class);
    }

    /**
     * Get the BEC blb monitor metrics.
     *
     * @param request: The request containing all options for getting the BEC blb monitor metrics.
     * @return: The response contains the BEC blb monitor metrics.
     */
    public GetBecBlbResourceMetricsResponse getBecBlbResourceMetrics(GetBecBlbResourceMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkNotNull(request.getStart(), START_MESSAGE_KEY);
        checkNotNull(request.getEnd(), END_MESSAGE_KEY);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, MONITOR, LB_PREFIX,
                request.getBlbId());
        if (StringUtils.isNotEmpty(request.getMetricsType())) {
            internalRequest.addParameter("metricsType", request.getMetricsType());
        }
        if (StringUtils.isNotEmpty(request.getIpType())) {
            internalRequest.addParameter("ipType", request.getIpType());
        }
        if (request.getStart() > 0 && request.getEnd() > 0) {
            internalRequest.addParameter("start", String.valueOf(request.getStart()));
            internalRequest.addParameter("end", String.valueOf(request.getEnd()));
        }
        if (StringUtils.isNotEmpty(request.getPort())) {
            internalRequest.addParameter("port", request.getPort());
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        return invokeHttpClient(internalRequest, GetBecBlbResourceMetricsResponse.class);
    }

    /**
     * Get the BEC user level overview data.
     *
     * @param request: The request containing all options for getting the BEC user level overview data.
     * @return user level data, include service/deployment/pod status
     */
    public GetBecResourceSummaryResponse getBecResourceSummary(GetBecResourceSummaryRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, OVERVIEW, SUMMARY);
        if (StringUtils.isNotEmpty(request.getRegion())) {
            internalRequest.addParameter("region", request.getRegion());
        }
        return invokeHttpClient(internalRequest, GetBecResourceSummaryResponse.class);
    }

    /**
     * Get overview information of container services.
     *
     * @param request: The request containing all options for getting overview information of container services.
     * @return user level data,container service overview information.
     */
    public GetBecContainerSummaryResponse getBecContainerSummary(GetBecContainerSummaryRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, OVERVIEW, SUMMARY, CONTAINER);
        return invokeHttpClient(internalRequest, GetBecContainerSummaryResponse.class);
    }

    /**
     * Get overview information of vm services.
     *
     * @param request: The request containing all options for getting overview information of vm services.
     * @return user level data,vm service overview information.
     */
    public GetBecVMSummaryResponse getBecVmSummary(GetBecVMSummaryRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, OVERVIEW, SUMMARY, VM_PREFIX);
        return invokeHttpClient(internalRequest, GetBecVMSummaryResponse.class);
    }

    /**
     * Get BEC user level container metrics.
     *
     * @param request: The request containing all options for getting BEC user level container metrics.
     * @return metrics data
     */
    public GetBecContainerMetricsResponse getBecContainerMetrics(GetBecContainerMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getOffsetInSeconds(), OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, MONITOR, OVERVIEW,
                CONTAINER, request.getType());
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() != null && request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecContainerMetricsResponse.class);
    }

    /**
     * Get BEC user level vm metrics.
     *
     * @param request: The request containing all options for getting BEC user level vm metrics.
     * @return metrics data
     */
    public GetBecVmMetricsResponse getBecVmMetrics(GetBecVmMetricsRequest request) {
        checkNotNull(request.getOffsetInSeconds(), OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, MONITOR,
                OVERVIEW, VM_PREFIX, request.getType());
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() != null && request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecVmMetricsResponse.class);
    }

    /**
     * List bec service packages.
     *
     * @param request: The request containing all options for listing bec service packages.
     * @return bec service packages data
     */
    public ListBecServicePackagesResponse listBecServicePackages(ListBecServicePackagesRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, RESOURCE, PACKAGE
                , request.getType());
        return invokeHttpClient(internalRequest, ListBecServicePackagesResponse.class);
    }

    /**
     * List bec passThrough disk packages.
     *
     * @param request: The request containing all options for listing bec passThrough disk packages.
     * @return bec passThrough disk packages data
     */
    public ListBecPassThroughDiskPackagesResponse listBecPassThroughDiskPackages
    (ListBecPassThroughDiskPackagesRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, RESOURCE, DISK
                , PASS_THROUGH);
        return invokeHttpClient(internalRequest, ListBecPassThroughDiskPackagesResponse.class);
    }

    /**
     * Create a new BEC vm image.
     *
     * @param request: The request containing all options for creating a bec vm image request.
     * @return: The vm image detail.
     */
    public CreateBecVmImageResponse createBecVmImage(CreateBecVmImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VM_PREFIX, IMAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVmImageResponse.class);
    }

    /**
     * Get BEC vm images.
     *
     * @param request: The request contains all options for getting a list of BEC vm image.
     * @return: paged api list, contains vm image details.
     */
    public GetBecVmImagesResponse getBecVmImages(GetBecVmImagesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, IMAGE);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getOrder())) {
                internalRequest.addParameter("order", request.getListRequest().getOrder());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getOrderBy())) {
                internalRequest.addParameter("orderBy", request.getListRequest().getOrderBy());
            }
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("type", request.getType());
        }
        if (StringUtils.isNotEmpty(request.getImageId())) {
            internalRequest.addParameter("imageId", request.getImageId());
        }
        if (StringUtils.isNotEmpty(request.getOsName())) {
            internalRequest.addParameter("osName", request.getOsName());
        }
        if (StringUtils.isNotEmpty(request.getRegionId())) {
            internalRequest.addParameter("regionId", request.getRegionId());
        }
        return invokeHttpClient(internalRequest, GetBecVmImagesResponse.class);
    }

    /**
     * Update vm image.
     *
     * @param request: The request contains all the options for updating BEC vm image.
     * @return: The response contains information about whether the vm image was successfully updated.
     */
    public UpdateBecVmImageResponse updateBecVmImage(UpdateBecVmImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VM_PREFIX, IMAGE,
                request.getImageId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecVmImageResponse.class);
    }

    /**
     * Batch delete BEC vm image list.
     *
     * @param request: The request contains the vm image ids that should be deleted.
     * @return: The response contains information about whether the vm image list was successfully deleted.
     */
    public BatchDeleteBecVmImageResponse batchDeleteBecVmImage(BatchDeleteBecVmImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkMultyParamsNotBothEmpty(request.getImageIdList(), IMAGE_ID_LIST_MESSAGE_KEY);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, VM_PREFIX, IMAGE);
        String imageIdList = String.join(",", request.getImageIdList());
        internalRequest.addParameter("imageIdList", imageIdList);
        return invokeHttpClient(internalRequest, BatchDeleteBecVmImageResponse.class);
    }

    /**
     * Create a new BEC deployment set.
     *
     * @param request: The request containing all options for creating a bec deployment set request.
     * @return: The deployment set id.
     */
    public CreateBecDeploySetResponse createBecDeploySet(CreateBecDeploySetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DEPLOY_SET_PREFIX, CREATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecDeploySetResponse.class);
    }

    /**
     * Get BEC deployment set list
     *
     * @param request: The request contains all options for getting a list of BEC deployment set.
     * @return: paged api list, for brief info.
     */
    public GetBecDeploySetsResponse getBecDeploySets(GetBecDeploySetsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DEPLOY_SET_PREFIX, LIST);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        return invokeHttpClient(internalRequest, GetBecDeploySetsResponse.class);
    }

    /**
     * Get BEC deployment set detail.
     *
     * @param request: The request containing all options for getting the api detail.
     * @return: BEC deployment set detail.
     */
    public GetBecDeploySetResponse getBecDeploySet(GetBecDeploySetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDeploySetId(), checkEmptyExceptionMessageFormat(DEPLOY_SET_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DEPLOY_SET_PREFIX,
                request.getDeploySetId());
        return invokeHttpClient(internalRequest, GetBecDeploySetResponse.class);
    }

    /**
     * Update BEC deployment set.
     *
     * @param request: The request containing all options for updating the deployment set.
     * @return: The response is empty.
     */
    public UpdateBecDeploySetResponse updateBecDeploySet(UpdateBecDeploySetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDeploySetId(), checkEmptyExceptionMessageFormat(DEPLOY_SET_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DEPLOY_SET_PREFIX,
                request.getDeploySetId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        internalRequest.addParameter("modifyAttribute", "");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecDeploySetResponse.class);
    }

    /**
     * Delete BEC deployment set.
     *
     * @param request: The request contains the deployment set id that should be deleted.
     * @return: The response is empty.
     */
    public DeleteBecDeploySetResponse deleteBecDeploySet(DeleteBecDeploySetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDeploySetId(), checkEmptyExceptionMessageFormat(DEPLOY_SET_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, DEPLOY_SET_PREFIX,
                request.getDeploySetId());
        return invokeHttpClient(internalRequest, DeleteBecDeploySetResponse.class);
    }

    /**
     * Delete BEC deployment set's instances.
     *
     * @param request: The request containing all options for deleting the deployment set's instances.
     * @return: The response is empty.
     */
    public DeleteBecDeploySetInstancesResponse deleteBecDeploySetInstances(DeleteBecDeploySetInstancesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDeploysetId(), checkEmptyExceptionMessageFormat(DEPLOY_SET_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DEPLOY_SET_PREFIX, DEL_RELATION);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DeleteBecDeploySetInstancesResponse.class);
    }

    /**
     * Update BEC instance's deployment set.
     *
     * @param request: The request containing all options for updating a bec instance's deployment sets.
     * @return: The response is empty.
     */
    public UpdateBecDeploySetInstanceResponse updateBecDeploySetInstance(UpdateBecDeploySetInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCE_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DEPLOY_SET_PREFIX, UPDATE_RELATION);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecDeploySetInstanceResponse.class);
    }

    /**
     * Create a new BEC vpc.
     *
     * @param request: The request containing all options for creating a bec vpc request.
     * @return: The vpc id.
     */
    public CreateBecVpcResponse createBecVpc(CreateBecVpcRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VPC_PREFIX);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVpcResponse.class);
    }

    /**
     * Get a BEC vpc detail.
     *
     * @param request: The request containing all options for getting the api detail.
     * @return: The BEC vpc detail.
     */
    public GetBecVpcResponse getBecVpc(GetBecVpcRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVpcId(), checkEmptyExceptionMessageFormat(VPC_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VPC_PREFIX,
                request.getVpcId());
        return invokeHttpClient(internalRequest, GetBecVpcResponse.class);
    }

    /**
     * Get BEC vpcs.
     *
     * @param request: The request contains all options for getting a list of BEC vpc.
     * @return: paged api list, contains vpc details.
     */
    public GetBecVpcsResponse getBecVpcs(GetBecVpcsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VPC_PREFIX);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        if (StringUtils.isNotEmpty(request.getRegionId())) {
            internalRequest.addParameter("regionId", request.getRegionId());
        }
        return invokeHttpClient(internalRequest, GetBecVpcsResponse.class);
    }

    /**
     * Update BEC vpc.
     *
     * @param request: The request contains all the options for updating BEC vpc.
     * @return: The response contains information about whether the vpc was successfully updated.
     */
    public UpdateBecVpcResponse updateBecVpc(UpdateBecVpcRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVpcId(), checkEmptyExceptionMessageFormat(VPC_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VPC_PREFIX,
                request.getVpcId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecVpcResponse.class);
    }

    /**
     * Delete BEC vpc.
     *
     * @param request: The request contains the vpc id that should be deleted.
     * @return: The response contains information about whether the vpc was successfully deleted.
     */
    public DeleteBecVpcResponse deleteBecVpc(DeleteBecVpcRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVpcId(), checkEmptyExceptionMessageFormat(VPC_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, VPC_PREFIX,
                request.getVpcId());
        return invokeHttpClient(internalRequest, DeleteBecVpcResponse.class);
    }

    /**
     * Create a new BEC subnet.
     *
     * @param request: The request containing all options for creating a bec subnet request.
     * @return: The subnet detail.
     */
    public CreateBecSubnetResponse createBecSubnet(CreateBecSubnetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VPC_PREFIX, SUBNET_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecSubnetResponse.class);
    }

    /**
     * Get BEC subnet detail.
     *
     * @param request: The request containing all options for getting the api detail.
     * @return: BEC subnet detail.
     */
    public GetBecSubnetResponse getBecSubnet(GetBecSubnetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSubnetId(), checkEmptyExceptionMessageFormat(SUBNET_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VPC_PREFIX, SUBNET_PREFIX,
                request.getSubnetId());
        return invokeHttpClient(internalRequest, GetBecSubnetResponse.class);
    }

    /**
     * Get BEC subnets.
     *
     * @param request: The request contains all options for getting a list of BEC subnet.
     * @return: paged api list, contains subnet details.
     */
    public GetBecSubnetsResponse getBecSubnets(GetBecSubnetsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VPC_PREFIX, SUBNET_PREFIX);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        return invokeHttpClient(internalRequest, GetBecSubnetsResponse.class);
    }

    /**
     * Update subnet.
     *
     * @param request: The request contains all the options for updating BEC subnet.
     * @return: The response contains information about whether the subnet was successfully updated.
     */
    public UpdateBecSubnetResponse updateBecSubnet(UpdateBecSubnetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSubnetId(), checkEmptyExceptionMessageFormat(SUBNET_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VPC_PREFIX, SUBNET_PREFIX,
                request.getSubnetId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecSubnetResponse.class);
    }

    /**
     * Delete BEC subnet.
     *
     * @param request: The request contains the subnet id that should be deleted.
     * @return: The response contains information about whether the subnet was successfully deleted.
     */
    public DeleteBecSubnetResponse deleteBecSubnet(DeleteBecSubnetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSubnetId(), checkEmptyExceptionMessageFormat(SUBNET_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, VPC_PREFIX, SUBNET_PREFIX,
                request.getSubnetId());
        return invokeHttpClient(internalRequest, DeleteBecSubnetResponse.class);
    }

    /**
     * Create a new BEC security group.
     *
     * @param request: The request containing all options for creating a bec security group request.
     * @return: The security group detail.
     */
    public CreateBecSecurityGroupResponse createBecSecurityGroup(CreateBecSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, SECURITY_GROUP_PREFIX);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecSecurityGroupResponse.class);
    }

    /**
     * Batch create a new BEC security group rules.
     *
     * @param request: The request containing all options for creating a bec security group rules request.
     * @return: The security group rules detail.
     */
    public BatchCreateBecSgRulesResponse batchCreateBecSecurityGroupRules(BatchCreateBecSgRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId()
                , checkEmptyExceptionMessageFormat(SECURITY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, SECURITY_GROUP_PREFIX,
                request.getSecurityGroupId(), RULE, BATCH, ADD);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchCreateBecSgRulesResponse.class);
    }

    /**
     * Get BEC security group detail.
     * Doesn't exist in Baidu cloud website api introduction.
     *
     * @param request: The request containing all options for getting the api detail.
     * @return: BEC security group detail.
     */
    public GetBecSecurityGroupResponse getBecSecurityGroup(GetBecSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId()
                , checkEmptyExceptionMessageFormat(SECURITY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, SECURITY_GROUP_PREFIX,
                request.getSecurityGroupId());
        return invokeHttpClient(internalRequest, GetBecSecurityGroupResponse.class);
    }

    /**
     * Get BEC security groups.
     *
     * @param request: The request contains all options for getting a list of BEC security group.
     * @return: paged api list, contains security group detail.
     */
    public GetBecSecurityGroupsResponse getBecSecurityGroups(GetBecSecurityGroupsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, SECURITY_GROUP_PREFIX);

        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        return invokeHttpClient(internalRequest, GetBecSecurityGroupsResponse.class);
    }

    /**
     * Update security group.
     *
     * @param request: The request contains all the options for updating BEC security group.
     * @return: The response contains information about whether the security group was successfully updated.
     */
    public UpdateBecSecurityGroupResponse updateBecSecurityGroup(UpdateBecSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, SECURITY_GROUP_PREFIX,
                request.getSecurityGroupId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecSecurityGroupResponse.class);
    }

    /**
     * Update security group rule.
     *
     * @param request: The request contains all the options for updating BEC security group.
     * @return: The response contains information about whether the security group was successfully updated.
     */
    public UpdateBecSecurityGroupRuleResponse updateBecSecurityGroupRule(UpdateBecSecurityGroupRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITY_GROUP_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getSecurityGroupRuleId(),
                checkEmptyExceptionMessageFormat(SECURITY_GROUP_RULE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, SECURITY_GROUP_PREFIX,
                request.getSecurityGroupId(), RULE, request.getSecurityGroupRuleId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecSecurityGroupRuleResponse.class);
    }

    /**
     * Delete BEC security group.
     *
     * @param request: The request contains the security group id that should be deleted.
     * @return: The response contains information about whether the security group was successfully deleted.
     */
    public DeleteBecSecurityGroupResponse deleteBecSecurityGroup(DeleteBecSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, SECURITY_GROUP_PREFIX,
                request.getSecurityGroupId());
        return invokeHttpClient(internalRequest, DeleteBecSecurityGroupResponse.class);
    }

    /**
     * Delete BEC batch security group rules.
     *
     * @param request: The request contains the batch security group id that should be deleted.
     * @return: The response contains information about whether the batch security group was successfully deleted.
     */
    public BatchDeleteBecSgRulesResponse batchDeleteBecSecurityGroupRules(BatchDeleteBecSgRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, SECURITY_GROUP_PREFIX,
                request.getSecurityGroupId(), RULE, BATCH, DELETE);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchDeleteBecSgRulesResponse.class);
    }

    /**
     * Create a new BEC route rule.
     *
     * @param request: The request containing all options for creating a bec route rule request.
     * @return: The route rule detail.
     */
    public CreateBecRouteRuleResponse createBecRouteRule(CreateBecRouteRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, ROUTE_PREFIX, RULE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecRouteRuleResponse.class);
    }

    /**
     * Get BEC route table detail.
     *
     * @param request: The request containing all options for getting the api detail.
     * @return: BEC route detail.
     */
    public GetBecRouteTableResponse getBecRouteTable(GetBecRouteTableRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getTableId(), checkEmptyExceptionMessageFormat(ROUTE_TABLE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ROUTE_PREFIX, DETAIL,
                request.getTableId());
        return invokeHttpClient(internalRequest, GetBecRouteTableResponse.class);
    }

    /**
     * Get BEC route tables.
     *
     * @param request: The request contains all options for getting a list of BEC security group.
     * @return: paged api list, contains route table details.
     */
    public GetBecRouteTablesResponse getBecRouteTables(GetBecRouteTablesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ROUTE_PREFIX, LIST);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        if (StringUtils.isNotEmpty(request.getRegionId())) {
            internalRequest.addParameter("regionId", request.getRegionId());
        }
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        return invokeHttpClient(internalRequest, GetBecRouteTablesResponse.class);
    }

    /**
     * Get BEC route rules.
     *
     * @param request: The request contains all options for getting a list of the BEC route rule.
     * @return: paged api list, contains the route rule details.
     */
    public GetBecRouteRulesResponse getBecRouteRules(GetBecRouteRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getTableId(), checkEmptyExceptionMessageFormat(ROUTE_TABLE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ROUTE_PREFIX, RULE, LIST,
                request.getTableId());
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        if (StringUtils.isNotEmpty(request.getTableId())) {
            internalRequest.addParameter("tableId", request.getTableId());
        }
        return invokeHttpClient(internalRequest, GetBecRouteRulesResponse.class);
    }

    /**
     * Update route table.
     *
     * @param request: The request contains all the options for updating BEC route table.
     * @return: The response contains information about whether the route table was successfully updated.
     */
    public UpdateBecRouteTableResponse updateBecRouteTable(UpdateBecRouteTableRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getTableId(), checkEmptyExceptionMessageFormat(ROUTE_TABLE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, ROUTE_PREFIX, UPDATE,
                request.getTableId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecRouteTableResponse.class);
    }

    /**
     * Delete BEC route rule.
     *
     * @param request: The request contains the route rule id that should be deleted.
     * @return: The response contains information about whether the route rule was successfully deleted.
     */
    public DeleteBecRouteRuleResponse deleteBecRouteRule(DeleteBecRouteRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getRuleId(), checkEmptyExceptionMessageFormat(ROUTE_RULE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, ROUTE_PREFIX, RULE, DELETE,
                request.getRuleId());
        return invokeHttpClient(internalRequest, DeleteBecRouteRuleResponse.class);
    }

    /**
     * Batch create BEC acl rules.
     *
     * @param request: The request containing all options for creating bec acl rules request.
     * @return: The acl rules.
     */
    public BatchCreateBecAclRulesResponse batchCreateBecAclRules(BatchCreateBecAclRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VPC_PREFIX, ACL, RULE, BATCH, CREATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchCreateBecAclRulesResponse.class);
    }

    /**
     * Get BEC acl detail.
     *
     * @param request: The request containing all options for getting the api detail.
     * @return: BEC acl detail.
     */
    public GetBecAclResponse getBecAcl(GetBecAclRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVpcId(), checkEmptyExceptionMessageFormat(VPC_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VPC_PREFIX, ACL, DETAIL);
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        return invokeHttpClient(internalRequest, GetBecAclResponse.class);
    }

    /**
     * Get BEC acls.
     *
     * @param request: The request contains all options for getting a list of BEC acl.
     * @return: paged api list, contains acl detail.
     */
    public GetBecAclsResponse getBecAcls(GetBecAclsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VPC_PREFIX, ACL, LIST);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        if (StringUtils.isNotEmpty(request.getRegionId())) {
            internalRequest.addParameter("regionId", request.getRegionId());
        }
        return invokeHttpClient(internalRequest, GetBecAclsResponse.class);
    }

    /**
     * Update acl.
     *
     * @param request: The request contains all the options for updating BEC acl.
     * @return: The response contains information about whether the acl was successfully updated.
     */
    public UpdateBecAclResponse updateBecAcl(UpdateBecAclRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAclId(), checkEmptyExceptionMessageFormat(ACL_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VPC_PREFIX, ACL,
                request.getAclId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAclResponse.class);
    }

    /**
     * Update acl rule.
     *
     * @param request: The request contains all the options for updating BEC acl rule.
     * @return: The response contains information about whether the acl rule was successfully updated.
     */
    public UpdateBecAclRuleResponse updateBecAclRule(UpdateBecAclRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAclRuleId(), checkEmptyExceptionMessageFormat(ACL_RULE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VPC_PREFIX, ACL, RULE,
                request.getAclRuleId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAclRuleResponse.class);
    }

    /**
     * Batch delete BEC acl rules.
     *
     * @param request: The request contains the acl rule ids that should be deleted.
     * @return: The response contains information about whether the acl rules was successfully deleted.
     */
    public BatchDeleteBecAclRulesResponse batchDeleteBecAclRules(BatchDeleteBecAclRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VPC_PREFIX, ACL, RULE, BATCH, DELETE);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchDeleteBecAclRulesResponse.class);
    }

    /**
     * App blb instance api begin.
     */
    /**
     * Create a new BEC app blb instance.
     *
     * @param request: The request containing all options for creating a bec app blb instance request.
     * @return: The app blb instance detail.
     */
    public CreateBecAppBlbResponse createBecAppBlbV2(CreateBecAppBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.POST, APP_BLB_PREFIX);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecAppBlbResponse.class);
    }

    /**
     * Get BEC app blb instance detail.
     *
     * @param request: The request containing all options for getting the api instance detail.
     * @return: BEC app blb instance detail.
     */
    public GetBecAppBlbResponse getBecAppBlbV2(GetBecAppBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX,
                request.getBlbId());
        return invokeHttpClient(internalRequest, GetBecAppBlbResponse.class);
    }

    /**
     * Get BEC app blb instances.
     *
     * @param request: The request contains all options for getting a list of BEC app blb instance.
     * @return: paged api list, contains app blb instance detail.
     */
    public GetBecAppBlbsResponse getBecAppBlbsV2(GetBecAppBlbsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getMarker())) {
                internalRequest.addParameter(MARKER, request.getListRequest().getMarker());
            }
            if (ObjectUtils.allNotNull((request.getListRequest().getMaxKeys()))) {
                internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getListRequest().getMaxKeys()));
            }
        }
        return invokeHttpClient(internalRequest, GetBecAppBlbsResponse.class);
    }

    /**
     * Update app blb instance.
     *
     * @param request: The request contains all the options for updating BEC app blb instance.
     * @return: The response contains information about whether the app blb instance was successfully updated.
     */
    public UpdateBecAppBlbResponse updateBecAppBlbV2(UpdateBecAppBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAppBlbResponse.class);
    }

    /**
     * Delete BEC app blb instance.
     *
     * @param request: The request contains the app blb instance id that should be deleted.
     * @return: The response is empty.
     */
    public DeleteBecAppBlbResponse deleteBecAppBlbV2(DeleteBecAppBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.DELETE, APP_BLB_PREFIX,
                request.getBlbId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        return invokeHttpClient(internalRequest, DeleteBecAppBlbResponse.class);
    }
    /**
     * App blb instance api end.
     */

    /**
     * App blb listener api begin.
     */
    /**
     * Create a new BEC app blb TCP listener.
     *
     * @param request: The request containing all options for creating a bec app blb TCP listener request.
     * @return: The app blb TCP listener detail.
     */
    public CreateBecAppBlbTCPListenerResponse createBecAppBlbTCPListenerV2(CreateBecAppBlbTCPListenerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.POST, APP_BLB_PREFIX,
                request.getBlbId(), TCP_LISTENER);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecAppBlbTCPListenerResponse.class);
    }

    /**
     * Create a new BEC app blb UDP listener.
     *
     * @param request: The request containing all options for creating a bec app blb UDP listener request.
     * @return: The app blb UDP listener detail.
     */
    public CreateBecAppBlbUDPListenerResponse createBecAppBlbUDPListenerV2(CreateBecAppBlbUDPListenerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.POST, APP_BLB_PREFIX,
                request.getBlbId(), UDP_LISTENER);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecAppBlbUDPListenerResponse.class);
    }

    /**
     * Create  BEC app blb listener policies.
     *
     * @param request: The request containing all options for creating a bec app blb listener policies request.
     * @return: The app blb listener policies.
     */
    public CreateBecAppBlbListenerPoliciesResponse createBecAppBlbListenerPoliciesV2(CreateBecAppBlbListenerPoliciesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.POST, APP_BLB_PREFIX,
                request.getBlbId(), POLICYS);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecAppBlbListenerPoliciesResponse.class);
    }

    /**
     * Get BEC app blb TCP listener detail.
     *
     * @param request: The request containing all options for getting the api listener detail.
     * @return: BEC app blb TCP listener detail.
     */
    public GetBecAppBlbTCPListenersResponse getBecAppBlbTCPListenersV2(GetBecAppBlbTCPListenersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX,
                request.getBlbId(), TCP_LISTENER);
        if (ObjectUtils.allNotNull((request.getListenerPort()))) {
            internalRequest.addParameter("listenerPort", String.valueOf(request.getListenerPort()));
        }
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getMarker())) {
                internalRequest.addParameter(MARKER, request.getListRequest().getMarker());
            }
            if (ObjectUtils.allNotNull((request.getListRequest().getMaxKeys()))) {
                internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getListRequest().getMaxKeys()));
            }
        }
        return invokeHttpClient(internalRequest, GetBecAppBlbTCPListenersResponse.class);
    }


    /**
     * Get BEC app blb UDP listener detail.
     *
     * @param request: The request containing all options for getting the api listener detail.
     * @return: BEC app blb UDP listener detail.
     */
    public GetBecAppBlbUDPListenersResponse getBecAppBlbUDPListenersV2(GetBecAppBlbUDPListenersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX,
                request.getBlbId(), UDP_LISTENER);
        if (ObjectUtils.allNotNull((request.getListenerPort()))) {
            internalRequest.addParameter("listenerPort", String.valueOf(request.getListenerPort()));
        }
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getMarker())) {
                internalRequest.addParameter(MARKER, request.getListRequest().getMarker());
            }
            if (ObjectUtils.allNotNull((request.getListRequest().getMaxKeys()))) {
                internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getListRequest().getMaxKeys()));
            }
        }
        return invokeHttpClient(internalRequest, GetBecAppBlbUDPListenersResponse.class);
    }

    /**
     * Get BEC app blb listener policies.
     *
     * @param request: The request contains all options for getting a list of BEC app blb listener policies.
     * @return: paged api list, contains app blb listener policy detail.
     */
    public GetBecAppBlbListenerPoliciesResponse getBecAppBlbListenerPoliciesV2(GetBecAppBlbListenerPoliciesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX,
                request.getBlbId(), POLICYS);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getMarker())) {
                internalRequest.addParameter(MARKER, request.getListRequest().getMarker());
            }
            if (ObjectUtils.allNotNull((request.getListRequest().getMaxKeys()))) {
                internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getListRequest().getMaxKeys()));
            }
        }
        if (ObjectUtils.allNotNull((request.getPort()))) {
            internalRequest.addParameter("port", String.valueOf(request.getPort()));
        }
        if (ObjectUtils.allNotNull((request.getType()))) {
            internalRequest.addParameter("type", String.valueOf(request.getType()));
        }
        return invokeHttpClient(internalRequest, GetBecAppBlbListenerPoliciesResponse.class);
    }

    /**
     * Update app blb TCP listener.
     *
     * @param request: The request contains all the options for updating BEC app blb TCP listener.
     * @return: The response contains information about whether the app blb TCP listener was successfully updated.
     */
    public UpdateBecAppBlbTCPListenerResponse updateBecAppBlbTCPListenerV2(UpdateBecAppBlbTCPListenerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), TCP_LISTENER);
        if (ObjectUtils.allNotNull((request.getListenerPort()))) {
            internalRequest.addParameter("listenerPort", String.valueOf(request.getListenerPort()));
        }
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAppBlbTCPListenerResponse.class);
    }


    /**
     * Update app blb UDP listener.
     *
     * @param request: The request contains all the options for updating BEC app blb UDP listener.
     * @return: The response contains information about whether the app blb UDP listener was successfully updated.
     */
    public UpdateBecAppBlbUDPListenerResponse updateBecAppBlbUDPListenerV2(UpdateBecAppBlbUDPListenerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), UDP_LISTENER);
        if (ObjectUtils.allNotNull((request.getListenerPort()))) {
            internalRequest.addParameter("listenerPort", String.valueOf(request.getListenerPort()));
        }
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAppBlbUDPListenerResponse.class);
    }


    /**
     * Delete BEC app blb listener policies.
     *
     * @param request: The request contains the app blb listener policy ids that should be deleted.
     * @return: The response is empty.
     */
    public DeleteBecAppBlbListenerPoliciesResponse deleteBecAppBlbListenerPoliciesV2(DeleteBecAppBlbListenerPoliciesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), POLICYS);
        internalRequest.addParameter(BATCH_DELETE, "");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DeleteBecAppBlbListenerPoliciesResponse.class);
    }

    /**
     * Delete BEC app blb listeners.
     *
     * @param request: The request contains the app blb listener ids that should be deleted.
     * @return: The response is empty.
     */
    public BatchDeleteBecAppBlbListenersResponse batchDeleteBecAppBlbListenersV2(BatchDeleteBecAppBlbListenersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), LISTENER);
        internalRequest.addParameter(BATCH_DELETE, "");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchDeleteBecAppBlbListenersResponse.class);
    }
    /**
     * App blb listener api end.
     */

    /**
     * App blb backend api begin.
     */
    /**
     * Create a new BEC app blb ip group.
     *
     * @param request: The request containing all options for creating a bec app blb ip group request.
     * @return: The app blb ip group detail.
     */
    public CreateBecAppBlbIpGroupResponse createBecAppBlbIpGroupV2(CreateBecAppBlbIpGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.POST, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecAppBlbIpGroupResponse.class);
    }

    /**
     * Create a new BEC app blb ip group policy.
     *
     * @param request: The request containing all options for creating a bec app blb ip group policy request.
     * @return: The app blb ip group policy detail.
     */
    public CreateBecAppBlbIpGroupBackendPolicyResponse createBecAppBlbIpGroupBackendPoliciesV2(CreateBecAppBlbIpGroupBackendPolicyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.POST, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, BACKEND_POLICY);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecAppBlbIpGroupBackendPolicyResponse.class);
    }

    /**
     * Create a new BEC app blb ip group member.
     *
     * @param request: The request containing all options for creating a bec app blb ip group member request.
     * @return: The app blb ip group member detail.
     */
    public CreateBecAppBlbIpGroupMembersResponse createBecAppBlbIpGroupMembersV2(CreateBecAppBlbIpGroupMembersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.POST, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, MEMBER);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecAppBlbIpGroupMembersResponse.class);
    }

    /**
     * Get BEC app blb ip group detail.
     *
     * @param request: The request containing all options for getting the api ip group detail.
     * @return: BEC app blb ip group detail.
     */
    public GetBecAppBlbIpGroupsResponse getBecAppBlbIpGroupsV2(GetBecAppBlbIpGroupsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getMarker())) {
                internalRequest.addParameter(MARKER, request.getListRequest().getMarker());
            }
            if (ObjectUtils.allNotNull((request.getListRequest().getMaxKeys()))) {
                internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getListRequest().getMaxKeys()));
            }
        }
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (ObjectUtils.allNotNull(request.getExactlyMatch())) {
            internalRequest.addParameter("exactlyMatch", String.valueOf(request.getExactlyMatch()));
        }
        return invokeHttpClient(internalRequest, GetBecAppBlbIpGroupsResponse.class);
    }


    /**
     * Get BEC app blb ip group policies.
     *
     * @param request: The request containing all options for getting the api ip group policies detail.
     * @return: BEC app blb ip group polices.
     */
    public GetBecAppBlbIpGroupBackendPoliciesResponse getBecAppBlbIpGroupPoliciesV2(GetBecAppBlbIpGroupBackendPoliciesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, BACKEND_POLICY);
        internalRequest.addParameter("ipGroupId", request.getIpGroupId());
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getMarker())) {
                internalRequest.addParameter(MARKER, request.getListRequest().getMarker());
            }
            if (ObjectUtils.allNotNull((request.getListRequest().getMaxKeys()))) {
                internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getListRequest().getMaxKeys()));
            }
        }
        return invokeHttpClient(internalRequest, GetBecAppBlbIpGroupBackendPoliciesResponse.class);
    }


    /**
     * Get BEC app blb ip group members.
     *
     * @param request: The request contains all options for getting a list of BEC app blb ip group members.
     * @return: paged api list, contains app blb ip group member details.
     */
    public GetBecAppBlbIpGroupMembersResponse getBecAppBlbIpGroupMembersV2(GetBecAppBlbIpGroupMembersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.GET, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, MEMBER);
        internalRequest.addParameter("ipGroupId", request.getIpGroupId());
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getMarker())) {
                internalRequest.addParameter(MARKER, request.getListRequest().getMarker());
            }
            if (ObjectUtils.allNotNull((request.getListRequest().getMaxKeys()))) {
                internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getListRequest().getMaxKeys()));
            }
        }
        return invokeHttpClient(internalRequest, GetBecAppBlbIpGroupMembersResponse.class);
    }

    /**
     * Update app blb ip group.
     *
     * @param request: The request contains all the options for updating BEC app blb ip group.
     * @return: The response contains information about whether the app blb ip group was successfully updated.
     */
    public UpdateBecAppBlbIpGroupResponse updateBecAppBlbIpGroupV2(UpdateBecAppBlbIpGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAppBlbIpGroupResponse.class);
    }

    /**
     * Update app blb ip group policy.
     *
     * @param request: The request contains all the options for updating BEC app blb ip group policy.
     * @return: The response contains information about whether the app blb ip group policy were successfully updated.
     */
    public UpdateBecAppBlbIpGroupBackendPolicyResponse updateBecAppBlbIpGroupPolicyV2(UpdateBecAppBlbIpGroupBackendPolicyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, BACKEND_POLICY);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAppBlbIpGroupBackendPolicyResponse.class);
    }


    /**
     * Update app blb ip group members.
     *
     * @param request: The request contains all the options for updating BEC app blb ip group members.
     * @return: The response contains information about whether the app blb ip group members were successfully updated.
     */
    public UpdateBecAppBlbIpGroupMembersResponse updateBecAppBlbIpGroupMembersV2(UpdateBecAppBlbIpGroupMembersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, MEMBER);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecAppBlbIpGroupMembersResponse.class);
    }

    /**
     * Delete BEC app blb ip group.
     *
     * @param request: The request contains the app blb ip group id that should be deleted.
     * @return: The response is empty.
     */
    public DeleteBecAppBlbIpGroupResponse deleteBecAppBlbIpGroupV2(DeleteBecAppBlbIpGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP);
        internalRequest.addParameter(DELETE, "");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DeleteBecAppBlbIpGroupResponse.class);
    }

    /**
     * Delete BEC app blb ip group policies.
     *
     * @param request: The request contains the app blb ip group policies id that should be deleted.
     * @return: The response is empty.
     */
    public DeleteBecAppBlbIpGroupBackendPoliciesResponse deleteBecAppBlbIpGroupBackendPoliciesV2(
            DeleteBecAppBlbIpGroupBackendPoliciesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, BACKEND_POLICY);
        internalRequest.addParameter(DELETE, "");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DeleteBecAppBlbIpGroupBackendPoliciesResponse.class);
    }

    /**
     * Delete BEC app blb ip group members.
     *
     * @param request: The request contains the app blb ip group member ids that should be deleted.
     * @return: The response is empty.
     */
    public DeleteBecAppBlbIpGroupMembersResponse deleteBecAppBlbIpGroupMembersV2(DeleteBecAppBlbIpGroupMembersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequestV2(request, HttpMethodName.PUT, APP_BLB_PREFIX,
                request.getBlbId(), IP_GROUP, MEMBER);
        internalRequest.addParameter(DELETE, "");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DeleteBecAppBlbIpGroupMembersResponse.class);
    }
    /**
     * App blb backend api end.
     */

    /**
     * Bec nat api begin.
     */
    /**
     * Create a new BEC nat.
     *
     * @param request: The request containing all options for creating a bec nat request.
     * @return: The nat detail.
     */
    public BatchCreateBecNatsResponse batchCreateBecNat(BatchCreateBecNatsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX, BATCH, CREATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchCreateBecNatsResponse.class);
    }

    /**
     * Get BEC nat detail.
     *
     * @param request: The request containing all options for getting the nat detail.
     * @return: BEC nat detail.
     */
    public GetBecNatResponse getBecNat(GetBecNatRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, NAT_PREFIX,
                request.getNatId());
        return invokeHttpClient(internalRequest, GetBecNatResponse.class);
    }

    /**
     * Get BEC nats.
     *
     * @param request: The request containing all options for getting the nats.
     * @return: BEC nats.
     */
    public GetBecNatsResponse getBecNats(GetBecNatsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, NAT_PREFIX, LIST);
        if (ObjectUtils.allNotNull(request.getListRequest())) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
        }
        if (StringUtils.isNotEmpty(request.getRegionId())) {
            internalRequest.addParameter("regionId", request.getRegionId());
        }
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        return invokeHttpClient(internalRequest, GetBecNatsResponse.class);
    }

    /**
     * Update nat.
     *
     * @param request: The request contains all the options for updating BEC nat.
     * @return: The response contains information about whether the nat was successfully updated.
     */
    public UpdateBecNatResponse updateBecNat(UpdateBecNatRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, NAT_PREFIX,
                request.getNatId(), UPDATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecNatResponse.class);
    }

    /**
     * Update nat bandwidth.
     *
     * @param request: The request contains all the options for updating BEC nat bandwidth.
     * @return: The response contains information about whether the nat bandwidth  was successfully updated.
     */
    public UpdateBecNatBandwidthResponse updateBecNatBandwidth(UpdateBecNatBandwidthRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, NAT_PREFIX,
                request.getNatId(), UPDATE_BANDWIDTH);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecNatBandwidthResponse.class);
    }

    /**
     * Delete BEC nats.
     *
     * @param request: The request contains the nat ids that should be deleted.
     * @return: The response is empty.
     */
    public BatchDeleteBecNatsResponse batchDeleteBecNats(BatchDeleteBecNatsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX, BATCH, DELETE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchDeleteBecNatsResponse.class);
    }

    /**
     * Get the BEC nat instance metrics.
     *
     * @param request: The request containing all options for getting bec nat instance metrics.
     * @return: The response contains BEC nat instance metrics.
     */
    public GetBecNatMetricsResponse getBecNatMetrics(GetBecNatMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getMetricsType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        checkNotNull(request.getStart(), START_MESSAGE_KEY);
        checkNotNull(request.getEnd(), END_MESSAGE_KEY);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, MONITOR, NAT_PREFIX,
                request.getNatId());
        if (request.getStart() > 0 && request.getEnd() > 0) {
            internalRequest.addParameter("start", String.valueOf(request.getStart()));
            internalRequest.addParameter("end", String.valueOf(request.getEnd()));
        }
        if (StringUtils.isNotEmpty(request.getMetricsType())) {
            internalRequest.addParameter("metricsType", request.getMetricsType());
        }
        return invokeHttpClient(internalRequest, GetBecNatMetricsResponse.class);
    }
    /**
     * Bec nat api end.
     */

    /**
     * Bec S-NAT rule begin.
     */
    /**
     * Create a new BEC rule.
     *
     * @param request: The request containing all options for creating a bec S-NAT rule request.
     * @return: The S-NAT rule detail.
     */
    public CreateBecSnatRuleResponse createBecSnatRule(CreateBecSnatRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX,
                request.getNatId(), SNAT_RULE, CREATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecSnatRuleResponse.class);
    }

    /**
     * Batch create BEC S-NAT rules.
     *
     * @param request: The request containing all options for creating bec S-NAT rules request.
     * @return: The S-NAT rules.
     */
    public BatchCreateBecSnatRulesResponse batchCreateBecSnatRules(BatchCreateBecSnatRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX,
                request.getNatId(), SNAT_RULE, BATCH_CREATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchCreateBecSnatRulesResponse.class);
    }

    /**
     * Get BEC S-NAT rules.
     *
     * @param request: The request containing all options for getting the S-NAT rules.
     * @return: BEC S-NAT rules.
     */
    public GetBecSnatRulesResponse getBecSnatRules(GetBecSnatRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, NAT_PREFIX,
                request.getNatId(), SNAT_RULE);
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, GetBecSnatRulesResponse.class);
    }

    /**
     * Update S-NAT rule.
     *
     * @param request: The request contains all the options for updating BEC S-NAT rule.
     * @return: The response contains information about whether the S-NAT rule was successfully updated.
     */
    public UpdateBecSnatRuleResponse updateBecSnatRule(UpdateBecSnatRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, NAT_PREFIX,
                request.getNatId(), SNAT_RULE, request.getRuleId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecSnatRuleResponse.class);
    }

    /**
     * Delete BEC S-NAT rules.
     *
     * @param request: The request contains the S-NAT rule ids that should be deleted.
     * @return: The response is empty.
     */
    public BatchDeleteBecSnatRulesResponse batchDeleteBecSnatRules(BatchDeleteBecSnatRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX,
                request.getNatId(), SNAT_RULE, BATCH, DELETE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchDeleteBecSnatRulesResponse.class);
    }
    /**
     * Bec S-NAT rule end.
     */

    /**
     * Bec D-NAT rule begin.
     */
    /**
     * Create a new BEC D-NAT rule.
     *
     * @param request: The request containing all options for creating a bec D-NAT rule request.
     * @return: The D-NAT rule detail.
     */
    public CreateBecDnatRuleResponse createBecDnatRule(CreateBecDnatRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX,
                request.getNatId(), DNAT_RULE, CREATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecDnatRuleResponse.class);
    }

    /**
     * Batch create BEC D-NAT rules.
     *
     * @param request: The request containing all options for creating batch bec D-NAT rules request.
     * @return: The D-NAT rules。
     */
    public BatchCreateBecDnatRulesResponse batchCreateBecDnatRules(BatchCreateBecDnatRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX,
                request.getNatId(), DNAT_RULE, BATCH_CREATE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchCreateBecDnatRulesResponse.class);
    }

    /**
     * Get BEC D-NAT rules.
     *
     * @param request: The request containing all options for getting the D-NAT rules.
     * @return: BEC D-NAT rules.
     */
    public GetBecDnatRulesResponse getBecDnatRules(GetBecDnatRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, NAT_PREFIX,
                request.getNatId(), DNAT_RULE);
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, GetBecDnatRulesResponse.class);
    }

    /**
     * Update D-NAT rule.
     *
     * @param request: The request contains all the options for updating BEC D-NAT rule.
     * @return: The response contains information about whether the D-NAT rule was successfully updated.
     */
    public UpdateBecDnatRuleResponse updateBecDnatRule(UpdateBecDnatRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getRuleId(),
                checkEmptyExceptionMessageFormat(RULE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, NAT_PREFIX,
                request.getNatId(), DNAT_RULE, request.getRuleId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecDnatRuleResponse.class);
    }

    /**
     * Batch Delete BEC D-NAT rules.
     *
     * @param request: The request contains the D-NAT rule ids that should be deleted.
     * @return: The response is empty.
     */
    public BatchDeleteBecDnatRulesResponse batchDeleteBecDnatRules(BatchDeleteBecDnatRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getNatId(), checkEmptyExceptionMessageFormat(NAT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, NAT_PREFIX,
                request.getNatId(), DNAT_RULE, BATCH, DELETE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchDeleteBecDnatRulesResponse.class);
    }
    /**
     * Bec D-NAT rule end.
     */
}