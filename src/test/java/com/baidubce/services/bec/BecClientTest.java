package com.baidubce.services.bec;

import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bec.model.Backends;
import com.baidubce.services.bec.model.ListRequest;
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
import com.baidubce.services.bec.model.appblbv2.backendbind.IpGroupMemberForm;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupBackendPolicyRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupBackendPolicyResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupMembersRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupMembersResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupRequest;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateBecAppBlbIpGroupResponse;
import com.baidubce.services.bec.model.appblbv2.backendbind.UpdateIpGroupMemberForm;
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
import com.baidubce.services.bec.model.appblbv2.listener.CreateAppPolicy;
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
import com.baidubce.services.bec.model.blb.BlbBindingForm;
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
import com.baidubce.services.bec.model.enums.DiskTypeEnum;
import com.baidubce.services.bec.model.enums.DnsTypeEnum;
import com.baidubce.services.bec.model.enums.LbModeEnum;
import com.baidubce.services.bec.model.enums.NetTypeEnum;
import com.baidubce.services.bec.model.enums.NodeTypeEnum;
import com.baidubce.services.bec.model.enums.ProtocolEnum;
import com.baidubce.services.bec.model.enums.RouteTypeEnum;
import com.baidubce.services.bec.model.enums.ServiceProviderEnum;
import com.baidubce.services.bec.model.network.acl.AclRule;
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
import com.baidubce.services.bec.model.network.nat.NatDeploymentInstance;
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
import com.baidubce.services.bec.model.network.securitygroup.SecurityGroupRule;
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
import com.baidubce.services.bec.model.purchase.DeploymentInstance;
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesResponse;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesResponse;
import com.baidubce.services.bec.model.vm.DnsConfig;
import com.baidubce.services.bec.model.vm.NetworkConfig;
import com.baidubce.services.bec.model.vm.NetworkConfig.Networks;
import com.baidubce.services.bec.model.vm.SystemVolumeConfig;
import com.baidubce.services.bec.model.vm.VolumeConfig;
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
import com.baidubce.services.bec.model.vm.image.Image;
import com.baidubce.services.bec.model.vm.image.UpdateBecVmImageRequest;
import com.baidubce.services.bec.model.vm.image.UpdateBecVmImageResponse;
import com.baidubce.services.bec.model.vm.image.VmImage;
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
import com.baidubce.services.bec.model.vm.service.Reservation;
import com.baidubce.services.bec.model.vm.service.UpdateBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.UpdateBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateRequest;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateRequest.GpuRequest;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateResponse;
import com.baidubce.services.bec.model.vo.v2.ListenerModel;
import com.baidubce.services.tag.model.Tag;
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

import java.util.ArrayList;
import java.util.Arrays;
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

        /**
         * 创建vm实例组
         */
        @Test
        public void createBecVmServiceTest() {
            SystemVolumeConfig systemVolume = new SystemVolumeConfig();
            systemVolume.setVolumeType(DiskTypeEnum.NVME);
            systemVolume.setSizeInGB(47);
            systemVolume.setName("test-system-disk-zy");

            VolumeConfig volumeConfig = new VolumeConfig();
            volumeConfig.setVolumeType(DiskTypeEnum.CDS_SSD);
            volumeConfig.setName("data0-zy");
            volumeConfig.setSizeInGB(23);
            List<VolumeConfig> volumeConfigList = new ArrayList<VolumeConfig>() {{
                add(volumeConfig);
            }};

            DeploymentInstance deploymentInstance = new DeploymentInstance();
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            deploymentInstance.setRegionId("cn-huhehaote-ix");
            deploymentInstance.setNetworkType("vpc");
            deploymentInstance.setSubnetId("sbn-6s0hyohf3gdl");
            deploymentInstance.setVpcId("vpc-jlhljrppmtqn");
            deploymentInstance.setReplicas(1);
            deployInstances.add(deploymentInstance);

            DnsConfig dnsConfig = new DnsConfig();
            dnsConfig.setDnsType(DnsTypeEnum.CUSTOMIZE);
            dnsConfig.setDnsAddress("8.8.8.8");

            Networks internal = new Networks();
            internal.setNetName("eth0");
            internal.setNetType(NetTypeEnum.INTERNAL_IP);
            Networks publicNet = new Networks();
            publicNet.setNetName("eth1");
            publicNet.setNetType(NetTypeEnum.PUBLIC_IP);
            List<Networks> singleNetworksList = new ArrayList<Networks>() {{
                add(internal);
                add(publicNet);
            }};

            Networks cm = new Networks();
            cm.setNetName("eth1");
            cm.setNetType(NetTypeEnum.TRIPLE_CM);
            Networks un = new Networks();
            un.setNetName("eth2");
            un.setNetType(NetTypeEnum.TRIPLE_UN);
            Networks ct = new Networks();
            ct.setNetName("eth3");
            ct.setNetType(NetTypeEnum.TRIPLE_CT);

            List<Networks> tripleNetworksList = new ArrayList<Networks>() {{
                add(internal);
                add(cm);
                add(un);
                add(ct);
            }};

            NetworkConfig singleConfig = new NetworkConfig();
            singleConfig.setNodeType(NodeTypeEnum.SINGLE);
            singleConfig.setNetworksList(singleNetworksList);

            NetworkConfig tripleConfig = new NetworkConfig();
            tripleConfig.setNodeType(NodeTypeEnum.TRIPLE);
            tripleConfig.setNetworksList(tripleNetworksList);

            List<NetworkConfig> networkConfigList = new ArrayList<NetworkConfig>() {{
                add(singleConfig);
                add(tripleConfig);
            }};

            Reservation reservation = new Reservation();
            reservation.setTimeUnit("month");

            List<String> deploysetIdList = new ArrayList<String>() {{
                add("dset-1j7ewwjb");
            }};

            Tag tag = new Tag();
            tag.setTagKey("bec-zy-key");
            tag.setTagValue("bec-zy-key-val");
            List<Tag> tags = new ArrayList<Tag>() {{
                add(tag);
            }};

            CreateBecVmServiceRequest request = CreateBecVmServiceRequest
                    .builder()
                    .serviceName("test-bec-service-gr1-gpu-sdk-zy-noregionId-1111-1")
                    .vmName("test-bec-service-gr1-gpu-sdk-zy-noregionId-1111-1")
                    .needPublicIp(true)
                    .needIpv6PublicIp(false)
                    .disableIntranet(false)
                    .disableCloudInit(false)
                    .cpu(10)
                    .memory(50)
                    .imageId("m-PUwcDkol")
                    .imageType("bec")
                    .paymentMethod("postpay")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .bandwidth(80)
                    .cudaVersion("12.0.1")
                    .cudnnVersion("9.0.0")
                    .driverVersion("550.90.07")
                    .systemVolume(systemVolume)
                    .dataVolumeList(volumeConfigList)
                    .deployInstances(deployInstances)
                    .dnsConfig(dnsConfig)
                    .networkConfigList(networkConfigList)
                    .reservation(reservation)
                    .spec("bec.gan2.c10m50.1gac90")
                    .deploysetIdList(deploysetIdList)
                    .tags(tags)
                    .userData("dXNlcl9pbmplY3RlZF9kYXRhOiBJeUV2WW1sdUwzTm9DbVZqYUc4Z0lsZGxiR052YldVZ2RHOGdRb" +
                            "UZwWkhVZ1FVa2dRMnh2ZFdRdUlpQjhJSFJsWlNBdmNtOXZkQzkxYzJWeVJHRjBZVVpwYkdVMA==")
                    .build();
            CreateBecVmServiceResponse response = client.createBecVmService(request);
            toJsonPrettyString("create bec vm service", response);
        }

        /**
         * 创建vm实例组
         * 重点测试 regionId方式, network, spec, userData等参数
         */
        @Test
        public void createBecVmServiceTest1() {
            SystemVolumeConfig systemVolume = new SystemVolumeConfig();
            systemVolume.setVolumeType(DiskTypeEnum.NVME);
            systemVolume.setSizeInGB(47);
            systemVolume.setName("test-system-disk-zy");

            VolumeConfig volumeConfig = new VolumeConfig();
            volumeConfig.setVolumeType(DiskTypeEnum.CDS_SSD);
            volumeConfig.setName("data0-zy");
            volumeConfig.setSizeInGB(23);
            List<VolumeConfig> volumeConfigList = new ArrayList<VolumeConfig>() {{
                add(volumeConfig);
            }};

            DeploymentInstance deploymentInstance = new DeploymentInstance();
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            deploymentInstance.setRegionId("cn-huhehaote-ix");
            deploymentInstance.setNetworkType("vpc");
            deploymentInstance.setSubnetId("sbn-6s0hyohf3gdl");
            deploymentInstance.setVpcId("vpc-jlhljrppmtqn");
            deploymentInstance.setReplicas(1);
            deployInstances.add(deploymentInstance);

            DnsConfig dnsConfig = new DnsConfig();
            dnsConfig.setDnsType(DnsTypeEnum.CUSTOMIZE);
            dnsConfig.setDnsAddress("8.8.8.8");

            Networks internal = new Networks();
            internal.setNetName("eth0");
            internal.setNetType(NetTypeEnum.INTERNAL_IP);
            Networks publicNet = new Networks();
            publicNet.setNetName("eth1");
            publicNet.setNetType(NetTypeEnum.PUBLIC_IP);
            List<Networks> singleNetworksList = new ArrayList<Networks>() {{
                add(internal);
                add(publicNet);
            }};

            Networks cm = new Networks();
            cm.setNetName("eth1");
            cm.setNetType(NetTypeEnum.TRIPLE_CM);
            Networks un = new Networks();
            un.setNetName("eth2");
            un.setNetType(NetTypeEnum.TRIPLE_UN);
            Networks ct = new Networks();
            ct.setNetName("eth3");
            ct.setNetType(NetTypeEnum.TRIPLE_CT);

            List<Networks> tripleNetworksList = new ArrayList<Networks>() {{
                add(internal);
                add(cm);
                add(un);
                add(ct);
            }};

            NetworkConfig singleConfig = new NetworkConfig();
            singleConfig.setNodeType(NodeTypeEnum.SINGLE);
            singleConfig.setNetworksList(singleNetworksList);

            NetworkConfig tripleConfig = new NetworkConfig();
            tripleConfig.setNodeType(NodeTypeEnum.TRIPLE);
            tripleConfig.setNetworksList(tripleNetworksList);

            List<NetworkConfig> networkConfigList = new ArrayList<NetworkConfig>() {{
                add(singleConfig);
                add(tripleConfig);
            }};

            Reservation reservation = new Reservation();
            reservation.setTimeUnit("month");

            List<String> deploysetIdList = new ArrayList<String>() {{
                add("dset-1j7ewwjb");
            }};

            Tag tag = new Tag();
            tag.setTagKey("bec-zy-key");
            tag.setTagValue("bec-zy-key-val");
            List<Tag> tags = new ArrayList<Tag>() {{
                add(tag);
            }};

            CreateBecVmServiceRequest request = CreateBecVmServiceRequest
                    .builder()
                    .serviceName("test-bec-service-gr1-gpu-sdk-zy-regionId-1111-2")
                    .vmName("test-bec-service-gr1-gpu-sdk-zy-regionId-1111-2")
                    .needPublicIp(true)
                    .needIpv6PublicIp(false)
                    .disableIntranet(false)
                    .disableCloudInit(false)
                    .cpu(10)
                    .memory(50)
                    .imageId("m-PUwcDkol")
                    .imageType("bec")
                    .paymentMethod("postpay")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .bandwidth(80)
                    .cudaVersion("12.0.1")
                    .cudnnVersion("9.0.0")
                    .driverVersion("550.90.07")
                    .systemVolume(systemVolume)
                    .dataVolumeList(volumeConfigList)
                    .deployInstances(deployInstances)
                    .dnsConfig(dnsConfig)
                    .networkConfigList(networkConfigList)
                    .reservation(reservation)
                    .spec("bec.gan2.c10m50.1gac90")
                    .deploysetIdList(deploysetIdList)
                    .tags(tags)
                    .userData("dXNlcl9pbmplY3RlZF9kYXRhOiBJeUV2WW1sdUwzTm9DbVZqYUc4Z0lsZGxiR052YldVZ2RHOGdRb" +
                            "UZwWkhVZ1FVa2dRMnh2ZFdRdUlpQjhJSFJsWlNBdmNtOXZkQzkxYzJWeVJHRjBZVVpwYkdVMA==")
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
            deploymentInstance.setReplicas(2);
            deploymentInstance.setRegionId("cn-huhehaote-ix");
            deployInstances.add(deploymentInstance);

            UpdateBecVmServiceRequest request = UpdateBecVmServiceRequest
                    .builder()
                    .serviceId("s-uczacbyy")
                    .serviceName("test-bec-2")
                    .type("replicas")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .deployInstances(deployInstances)
                    .build();
            UpdateBecVmServiceResponse response = client.updateBecVmService(request);
            toJsonPrettyString("update bec vm service", response);
        }

        /**
         * 更新bec实例组名称信息
         */
        @Test
        public void updateBecVmServiceTestName() {
            UpdateBecVmServiceRequest request = UpdateBecVmServiceRequest
                    .builder()
                    .serviceId("s-uczacbyy")
                    .serviceName("test-bec-service-gr1-gpu-sdk-zy-regionId-1111-3")
                    .type("serviceName")
                    .build();
            UpdateBecVmServiceResponse response = client.updateBecVmService(request);
            toJsonPrettyString("update bec vm service", response);
        }

        @Test
        public void updateBecVmServiceTest1() {
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            DeploymentInstance deploymentInstance = new DeploymentInstance();
            deploymentInstance.setRegionId("cn-huhehaote-ix");
            deploymentInstance.setReplicas(3);
            deployInstances.add(deploymentInstance);

            UpdateBecVmServiceRequest request = UpdateBecVmServiceRequest
                    .builder()
                    .serviceId("s-uczacbyy")
                    .serviceName("test-bec-sdk")
                    .type("replicas")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
                    .deployInstances(deployInstances)
                    .spec("bec.gan2.c10m50.1gac90")
                    .hostname("hostnametest11133")
                    .build();
            UpdateBecVmServiceResponse response = client.updateBecVmService(request);
            toJsonPrettyString("update bec vm service", response);
        }

        /**
         * 获取vm实例组列表
         */
        @Test
        public void getBecVmServiceTest() {
            GetBecVmServiceRequest request = new GetBecVmServiceRequest();
            request.setServiceId("s-uczacbyy");
            GetBecVmServiceResponse response = client.getBecVmService(request);
            toJsonPrettyString("get bec vm service", response);
        }

        @Test
        public void becVmServiceActionTest() {
            BecVmServiceActionRequest request = new BecVmServiceActionRequest();
            request.setAction("start");
            request.setServiceId("s-uczacbyy");
            BecVmServiceActionResponse response = client.becVmServiceAction(request);
            toJsonPrettyString("start/stop/release a bec vm service", response);
        }

        @Test
        public void deleteBecVmServiceTest() {
            DelBecVmServiceRequest request = new DelBecVmServiceRequest();
            request.setServiceId("s-lkuupa8t");
            DelBecVmServiceResponse response = client.deleteBecVmService(request);
            toJsonPrettyString("delete the bec vm service", response);
        }

        /**
         * Current param request, return a error response.
         */
        @Test
        public void getBecVmServiceMetricsTest() {
            GetBecVmServiceMetricsRequest request = new GetBecVmServiceMetricsRequest();
            request.setType("CPU");
            request.setServiceId("s-uczacbyy");
            request.setOffsetInSeconds(3600);
            request.setStepInMin(5);

            GetBecVmServiceMetricsResponse response = client.getBecVmServiceMetrics(request);
            toJsonPrettyString("get bec service metrics", response);
        }

        /**
         * 扩容实例
         * regionId, 状态ok
         */
        @Test
        public void createBecVmServiceInstances() {
            SystemVolumeConfig systemVolume = new SystemVolumeConfig();
            systemVolume.setVolumeType(DiskTypeEnum.NVME);
            systemVolume.setSizeInGB(44);
            systemVolume.setName("test-system-disk-zy");

            DeploymentInstance deploymentInstance = new DeploymentInstance();
            List<DeploymentInstance> deployInstances = new ArrayList<DeploymentInstance>();
            deploymentInstance.setRegionId("cn-huhehaote-ix");
            deploymentInstance.setReplicas(1);
            deploymentInstance.setNetworkType("vpc");
            deployInstances.add(deploymentInstance);

            CreateBecVmServiceRequest request = CreateBecVmServiceRequest
                    .builder()
                    .serviceId("s-uczacbyy")
                    .serviceName("test-gr2-zy-regionId")
                    .needPublicIp(false)
                    .bandwidth(100)
                    .disableIntranet(false)
                    .disableCloudInit(false)
                    .spec("bec.gan2.c10m50.1gac90")
                    .imageId("m-PUwcDkol")
                    .imageType("bec")
                    .paymentMethod("postpay")
                    .systemVolume(systemVolume)
                    .deployInstances(deployInstances)
                    .needIpv6PublicIp(true)
                    .vmName("test-grsdk-zy-ins3-regionId-cn-changzhou-ix-expand-11")
                    .adminPass("SYDuVCJW%rjrS9W5+q9@")
//                    .keyConfig(keyConfig)
                    .securityGroupIds(Arrays.asList("sg-219mosrn"))
                    .userData("dXNlcl9pbmplY3RlZF9kYXRhOiBJeUV2WW1sdUwzTm9DbVZqYUc4Z0lsZGxiR052YldVZ2RHOGdRbUZwWkh"
                            + "VZ1FVa2dRMnh2ZFdRdUlpQjhJSFJsWlNBdmNtOXZkQzkxYzJWeVJHRjBZVVpwYkdVMA==")
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
    public static class VmInstanceTest extends BecBase {
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
            request.setRegion("NORTH_CHINA");
            request.setServiceProvider("TRIPLE_LINE");
            request.setCity("HUHEHAOTE");
            request.setListRequest(listRequest);
            GetBecNodeVmInstanceListResponse response = client.getBecNodeVmInstanceList(request);
            toJsonPrettyString("get node bec vm instance list", response);
        }

        /**
         * Get bec virtual machine detail.
         */
        @Test
        public void getBecVirtualMachineTest() {
            GetBecVirtualMachineRequest request = new GetBecVirtualMachineRequest();
            request.setVmID("vm-uczacbyy-cn-huhehaote-ix-6vfhq");
            GetBecVirtualMachineResponse response = client.getBecVmInstance(request);
            toJsonPrettyString("get bec vm instance details", response);
        }

        @Test
        public void updateBecVmDeploymentTest() {
            UpdateBecVmDeploymentRequest request = new UpdateBecVmDeploymentRequest();

            request.setType("resource");
            request.setVmID("vm-uczacbyy-cn-huhehaote-ix-4rkpn");

            VmNetworkConfig networkConfig = new VmNetworkConfig();
            List<Networks> networksList = new ArrayList<Networks>();
            Networks networks = new Networks();
            networks.setNetName("test-zyc3");
            networks.setNetType(NetTypeEnum.INTERNAL_IP);
            networksList.add(networks);
            networkConfig.setNetworksList(networksList);
            networkConfig.setNodeType(NodeTypeEnum.SINGLE);
            request.setNetworkConfig(networkConfig);
            request.setHostname("test-zyc-ye3");
            request.setNeedIpv6PublicIp(true);

            UpdateBecVmDeploymentResponse response = client.updateBecVmDeployment(request);
            toJsonPrettyString("update a bec vm", response);
        }

        /**
         * vm实例重装
         */
        @Test
        public void reinstallBecVmInstanceTest() {
            Image image = new Image();
            image.setPvcId("lvm-2imsv1kq-cn-changzhou-ix-apj88-rootfs");
            image.setImageId("m-BFQenf9L");
            image.setImageType("bec");
            List<Image> images = new ArrayList<Image>();
            images.add(image);

            ReinstallBecVmInstanceRequest request = new ReinstallBecVmInstanceRequest();
            request.setVmID("vm-w0hpiwum-cn-huhehaote-ix-jkaxf");
            request.setAdminPass("SYDuVCJW%rjrS9W5+q9@");
            request.setImages(images);
            request.setResetDataDisk(false);
            request.setUserData("dXNlcl9pbmplY3RlZF9kYXRhOiBJeUV2WW1sdUwzTm9DbVZqYUc4Z0lsZGxiR052Yld"
                    + "VZ2RHOGdRbUZwWkhVZ1FVa2dRMnh2ZFdRdUlpQjhJSFJsWlNBdmNtOXZkQzkxYzJWeVJHRjBZVVpwYkdVMA==");
            ReinstallBecVmInstanceResponse response = client.reinstallBecVmInstance(request);
            toJsonPrettyString("reinstall a bec vm", response);
        }

        @Test
        public void operateBecVmDeploymentTest() {
            OperateBecVmDeploymentRequest request = new OperateBecVmDeploymentRequest();
            request.setVmID("vm-w0hpiwum-cn-huhehaote-ix-jkaxf");
            request.setAction("stop");
            OperateBecVmDeploymentResponse response = client.operateBecVmDeployment(request);
            toJsonPrettyString("operate a bec vm instance", response);
        }

        @Test
        public void deleteBecVmInstanceTest() {
            DeleteBecVmInstanceRequest request = new DeleteBecVmInstanceRequest();
            request.setVmID("vm-w0hpiwum-cn-huhehaote-ix-jkaxf");
            DeleteBecVmInstanceResponse response = client.deleteBecVmInstance(request);
            toJsonPrettyString("delete a instance", response);
        }

        /**
         * return a error response.
         */
        @Test
        public void getBecVmInstanceMetricsTest() {
            GetBecVmInstanceMetricsRequest request = new GetBecVmInstanceMetricsRequest();
            request.setVmId("vm-levxsjlk-cn-nanning-cm-uzoss");
            request.setType("cpu");
            request.setOffsetInSeconds(3600);
            request.setStepInMin(5);
            GetBecVmInstanceMetricsResponse response = client.getBecVmInstanceMetrics(request);
            toJsonPrettyString("get vm instance metrics", response);
        }

        @Test
        public void getBecVmConfigTest() {
            GetBecVmConfigRequest request = new GetBecVmConfigRequest();
            request.setVmID("vm-levxsjlk-cn-nanning-cm-uzoss");
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
//                    .regionId("cn-nanning-cm")
                    .region("SOUTH_CHINA")
                    .city("NANNING")
                    .serviceProvider(ServiceProviderEnum.CHINA_MOBILE)
                    .blbName("bec-blb-classic-sdk-zy-11192")
                    .networkType("vpc")
                    // default vpd subnet id.
                    .vpcId("vpc-qfliqepb")
                    .subnetId("sbn-kq97da7b")
                    .needPublicIp(true)
                    .needIpv6PublicIp(true)
                    .bandwidthInMbpsLimit(23)
                    .subServiceProviders(Arrays.asList("cm"))
                    .build();
            CreateBecBlbResponse response = client.createBecBlb(request);
            toJsonPrettyString("create bec blb Results", response);
        }

        @Test
        public void getBecBlbTest() {
            GetBecBlbInstanceRequest request = new GetBecBlbInstanceRequest();
            request.setBlbId("applb-cn-changzhou-ix-odvelu7v");
            GetBecBlbInstanceResponse response = client.getBecBlb(request);
            toJsonPrettyString("get bec blb instance", response);
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
        public void updateBecBlbTest() {
            UpdateBecBlbRequest request = new UpdateBecBlbRequest();
            request.setBlbId("applb-cn-nanning-cm-o8n4rdr7");
            request.setBlbName("bec-test-test-test-sdk-candel-11193");
            UpdateBecBlbResponse response = client.updateBecBlb(request);
            toJsonPrettyString("update the specific BLB instance", response);
        }

        @Test
        public void getBecBlbResourceMetricsTest() {
            GetBecBlbResourceMetricsRequest request = new GetBecBlbResourceMetricsRequest();
            request.setBlbId("applb-cn-jinan2-cm-yun7rkch");
            request.setMetricsType("BANDWIDTH_TRANSMIT");
            request.setIpType("extranet");
            request.setStart(1732464000L);
            request.setEnd(1732532173L);
            GetBecBlbResourceMetricsResponse response = client.getBecBlbResourceMetrics(request);
            toJsonPrettyString("Get the BEC blb monitor metrics", response);
        }

        /**
         * 删除blb.
         */
        @Test
        public void deleteBecBlbTest() {
            DeleteBecBlbRequest request = new DeleteBecBlbRequest();
            request.setBlbId("applb-cn-nanning-cm-2gyngpfo");
            DeleteBecBlbResponse response = client.deleteBecBlb(request);
            toJsonPrettyString("delete the specific BLB instance", response);
        }

        @Test
        public void createBecBlbMonitorPortTest() {
            CreateBecBlbMonitorPortRequest request = new CreateBecBlbMonitorPortRequest();

            Port frontendPort = new Port();
            frontendPort.setPort(8483);
            frontendPort.setProtocol(ProtocolEnum.TCP);

            HealthCheck healthCheck = new HealthCheck();
//            healthCheck.setHealthCheckString("udp");
            healthCheck.setIntervalInSeconds(1);
            healthCheck.setTimeoutInSeconds(1);
            healthCheck.setHealthyThreshold(3);
            healthCheck.setUnhealthyThreshold(3);
            healthCheck.setHealthCheckType("tcp");

            request.setBlbId("applb-cn-nanning-cm-v8gyynnh");
            request.setBackendPort(8473);
            request.setFrontendPort(frontendPort);
            request.setHealthCheck(healthCheck);
            request.setKeepaliveTimeout(180);
            request.setLbMode(LbModeEnum.wrr);
            request.setEnableCipTTM(true);
            CreateBecBlbMonitorPortResponse response = client.createBecBlbMonitorPort(request);
            toJsonPrettyString("create Blb monitor port for assign blb", response);
        }

        @Test
        public void getBecBlbMonitorPortTest() {
            GetBecBlbMonitorPortDetailsRequest request = new GetBecBlbMonitorPortDetailsRequest();
            request.setBlbId("applb-cn-nanning-cm-vaawtoaj");
            request.setPort(8483);
            request.setProtocol(ProtocolEnum.TCP);
            GetBecBlbMonitorPortDetailsResponse response = client.getBecBlbMonitorPortDetails(request);
            toJsonPrettyString("get Blb monitor port details for assign blb and assign port", response);
        }

        @Test
        public void getBlbMonitorPortListTest() {
            GetBecBlbMonitorPortListRequest request = new GetBecBlbMonitorPortListRequest();
            request.setBlbId("applb-cn-nanning-cm-vaawtoaj");
            GetBecBlbMonitorPortListResponse response = client.getBlbMonitorPortList(request);
            toJsonPrettyString("get the Blb port monitor list for assign blb", response);
        }

        @Test
        public void updateBecBlbMonitorPortTest() {
            UpdateBecBlbMonitorPortRequest request = new UpdateBecBlbMonitorPortRequest();

            Port frontendPort = new Port();
            frontendPort.setProtocol(ProtocolEnum.TCP);
            frontendPort.setPort(8483);

            HealthCheck healthCheck = new HealthCheck();
            healthCheck.setUnhealthyThreshold(3);
            healthCheck.setTimeoutInSeconds(3);
            healthCheck.setIntervalInSeconds(3);
            healthCheck.setHealthyThreshold(3);
//            healthCheck.setHealthCheckString("wohao");
            healthCheck.setHealthCheckType("tcp");

            request.setBlbId("applb-cn-nanning-cm-vaawtoaj");
            request.setHealthCheck(healthCheck);
            request.setBackendPort(8773);
            request.setFrontendPort(frontendPort);
            request.setKeepaliveTimeout(1773);
            request.setLbMode(LbModeEnum.srch);

            UpdateBecBlbMonitorPortResponse response = client.updateBecBlbMonitorPort(request);
            toJsonPrettyString("update Blb monitor port for assign blb", response);
        }

        @Test
        public void createBecBlbBinding() {

            Backends backends = new Backends();
            backends.setName("vm-k4pzokvb-cn-nanning-cm-hjq69");
            backends.setWeight(79);
            backends.setIp("172.18.16.219");
            List<Backends> podWeight = new ArrayList<>();
            podWeight.add(backends);

            BlbBindingForm bindingForm = new BlbBindingForm();
            bindingForm.setDeploymentId("vmrs-k4pzokvb-cn-nanning-cm");
            bindingForm.setPodWeight(podWeight);
            List<BlbBindingForm> bindingForms = Arrays.asList(bindingForm);

            CreateBecBlbBindingRequest request = new CreateBecBlbBindingRequest();
            request.setBlbId("applb-cn-nanning-cm-v8gyynnh");
            request.setBindingForms(bindingForms);
            CreateBecBlbBindingResponse response = client.createBecBlbBinding(request);
            toJsonPrettyString("Bind the backend StatefulSet/VmReplicas to the specified BEC blb", response);
        }

        @Test
        public void getBecBlbBackendPodListTest() {
            GetBecBlbBackendPodListRequest request = new GetBecBlbBackendPodListRequest();
            request.setBlbId("applb-cn-nanning-cm-v8gyynnh");

            GetBecBlbBackendPodListResponse response = client.getBecBlbBackendPodList(request);
            toJsonPrettyString("get the bind Blb backend Pod/Vm list for assign blb", response);
        }

        @Test
        public void getBecBlbBackendBindingStsListTest() {
            GetBecBlbBackendBindingStsListRequest request = new GetBecBlbBackendBindingStsListRequest();
            request.setBlbId("applb-cn-nanning-cm-v8gyynnh");

            GetBecBlbBackendBindingStsListResponse response = client.getBecBlbBackendBindingStsList(request);
            toJsonPrettyString("get the binding Blb backend StatefulSet/VmReplicas list for assign blb", response);
        }

        @Test
        public void getBecBlbBindingPodListWithStsTest() {
            GetBecBlbBindingPodListWithStsRequest request = new GetBecBlbBindingPodListWithStsRequest();
            request.setBlbId("applb-cn-nanning-cm-v8gyynnh");
            request.setStsName("sts-fja8qbw7-3-l-haikou-9lstr");

            GetBecBlbBindingPodListWithStsResponse response = client.getBecBlbBindingPodListWithSts(request);
            toJsonPrettyString("Get the binding BEC blb backend Pod/Vm list for assign blb", response);
        }

        @Test
        public void updateBecBlbBindPodWeight() {
            UpdateBecBlbBindPodWeightRequest request = new UpdateBecBlbBindPodWeightRequest();

            List<Backends> podWeightList = new ArrayList<Backends>();
            Backends backends = new Backends();
            backends.setWeight(1);
            backends.setName("vm-k4pzokvb-cn-nanning-cm-hjq69");
            podWeightList.add(backends);

            request.setBlbId("applb-cn-nanning-cm-v8gyynnh");
            request.setPodWeightList(podWeightList);

            UpdateBecBlbBindPodWeightResponse response = client.updateBecBlbBindPodWeight(request);
            toJsonPrettyString("Modify the weight of the Pod/Vm bound to the specified BEC BLB backend", response);
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
            systemVolume.setVolumeType(DiskTypeEnum.NVME);

            List<VolumeConfig> dataVolumeList = new ArrayList<VolumeConfig>();
            VolumeConfig volumeConfig = new VolumeConfig();
            volumeConfig.setVolumeType(DiskTypeEnum.NVME);
            volumeConfig.setName("data0");
            volumeConfig.setSizeInGB(20);
            dataVolumeList.add(volumeConfig);

            DnsConfig dnsConfig = new DnsConfig();
            dnsConfig.setDnsType(DnsTypeEnum.DEFAULT);

            GpuRequest gpu = new GpuRequest();
            gpu.setType("nvidia.com/TU104GL_Tesla_T4");
            gpu.setNum(1);

            List<Networks> networksList = new ArrayList<Networks>();
            Networks networks = new Networks();
            networks.setNetType(NetTypeEnum.INTERNAL_IP);
            networks.setNetName("test0");

            Networks networks1 = new Networks();
            networks1.setNetType(NetTypeEnum.PUBLIC_IP);
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

    /**
     * Test case about vm image begin.
     */
    public static class VmImageTest extends BecBase {

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

        /**
         * Create a vm image.
         */
        @Test
        public void createBecVmImage() {
            CreateBecVmImageRequest request = new CreateBecVmImageRequest();
            request.setVmId("vm-gvjnit31-cn-changzhou-ix-kae90");
            VmImage image = new VmImage();
            image.setImageName("image-java-sdk-zy-1118-candelete3");
            image.setImageProcessType("local");
            image.setPvcId("lvm-gvjnit31-cn-changzhou-ix-kae90-rootfs");
            List<VmImage> images = Arrays.asList(image);
            request.setImages(images);
            CreateBecVmImageResponse response = client.createBecVmImage(request);
            toJsonPrettyString("create a bec vpc vm image", response);
        }

        /**
         * Get the vm images.
         */
        @Test
        public void getBecVmImages() {
            GetBecVmImagesRequest request = new GetBecVmImagesRequest();
            GetBecVmImagesResponse response = client.getBecVmImages(request);
            toJsonPrettyString("get bec vm images", response);
        }

        /**
         * Update the vm image.
         */
        @Test
        public void updateBecVmImage() {
            UpdateBecVmImageRequest request = new UpdateBecVmImageRequest();
            request.setImageId("im-xrqvlvue");
            request.setName("im-java-sdk-zy-1115-update-333344");
            UpdateBecVmImageResponse response = client.updateBecVmImage(request);
            toJsonPrettyString("update bec vm image", response);
        }

        /**
         * Batch delete vm images.
         */
        @Test
        public void batchDeleteBecVmImage() {
            BatchDeleteBecVmImageRequest request = new BatchDeleteBecVmImageRequest();
            List<String> imageIdList = Arrays.asList("im-vykjgpdg");
            request.setImageIdList(imageIdList);
            BatchDeleteBecVmImageResponse response = client.batchDeleteBecVmImage(request);
            toJsonPrettyString("delete bec vm image", response);
        }
    }
    /**
     * Test case about vm image end.
     */

    /**
     * Test case about deployment set begin.
     */
    public static class VmDeploySetTest extends BecBase {

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

        /**
         * Create a deployment set.
         */
        @Test
        public void createBecDeploySet() {
            CreateBecDeploySetRequest request = new CreateBecDeploySetRequest();
            request.setName("deployset-java-sdk-zy");
            request.setDesc("test sdk interface");
            CreateBecDeploySetResponse response = client.createBecDeploySet(request);
            toJsonPrettyString("create bec deployment set", response);
        }

        /**
         * Get the deployment sets.
         */
        @Test
        public void getBecDeploySets() {
            GetBecDeploySetsRequest request = new GetBecDeploySetsRequest();
            GetBecDeploySetsResponse response = client.getBecDeploySets(request);
            toJsonPrettyString("get bec deployment sets", response);
        }

        /**
         * Get the deployment set detail.
         */
        @Test
        public void getBecDeploySet() {
            GetBecDeploySetRequest request = new GetBecDeploySetRequest();
            request.setDeploySetId("dset-1j7ewwjb");
            GetBecDeploySetResponse response = client.getBecDeploySet(request);
            toJsonPrettyString("get bec deployment set detail", response);
        }

        /**
         * Update the deployment set.
         */
        @Test
        public void updateBecDeploySet() {
            UpdateBecDeploySetRequest request = new UpdateBecDeploySetRequest();
            request.setDeploySetId("dset-yved6pdy");
            request.setDesc("sdk-update5");
            UpdateBecDeploySetResponse response = client.updateBecDeploySet(request);
            toJsonPrettyString("update bec deployment set", response);
        }

        /**
         * Delete the deployment set.
         */
        @Test
        public void deleteBecDeploySet() {
            DeleteBecDeploySetRequest request = new DeleteBecDeploySetRequest();
            request.setDeploySetId("dset-yved6pdy");
            DeleteBecDeploySetResponse response = client.deleteBecDeploySet(request);
            toJsonPrettyString("delete bec deployment set", response);
        }

        /**
         * Delete the deployment set's instances.
         */
        @Test
        public void deleteBecDeploySetInstances() {
            DeleteBecDeploySetInstancesRequest request = new DeleteBecDeploySetInstancesRequest();
            request.setDeploysetId("dset-1j7ewwjb");
            List<String> list = new ArrayList<String>();
            list.add("vm-9rsvnwf9-cn-changzhou-ix-cviue");
            request.setInstanceIdList(list);
            DeleteBecDeploySetInstancesResponse response = client.deleteBecDeploySetInstances(request);
            toJsonPrettyString("delete bec deployment set's instances", response);
        }

        /**
         * Update the instance's deployment sets.
         */
        @Test
        public void updateBecDeploySetInstance() {
            UpdateBecDeploySetInstanceRequest request = new UpdateBecDeploySetInstanceRequest();
            request.setInstanceId("vm-9rsvnwf9-cn-changzhou-ix-k4hnk");
            List<String> list = new ArrayList<String>();
            list.add("dset-1j7ewwjb");
            request.setDeploysetIdList(list);
            UpdateBecDeploySetInstanceResponse response = client.updateBecDeploySetInstance(request);
            toJsonPrettyString("update bec deployment set's instances", response);
        }
    }
    /**
     * Test case about deployment set end.
     */

    /**
     * Test case about vpc begin.
     */
    public static class NetworkVpcTest extends BecBase {

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

        /**
         * Create a vpc.
         */
        @Test
        public void createBecVpc() {
            Tag tag = new Tag();
            tag.setTagKey("bec-zy-key");
            tag.setTagValue("bec-zy-key-val");
            List<Tag> tags = new ArrayList<Tag>() {{
                add(tag);
            }};

            CreateBecVpcRequest request = new CreateBecVpcRequest();
            request.setName("vpc-java-sdk-zy-1115-notdel-5");
            request.setCidr("10.11.0.0/16");
            request.setRegionId("cn-changzhou-ix");
            request.setDescription("test sdk interface-jdk");
            request.setTags(tags);
            CreateBecVpcResponse response = client.createBecVpc(request);
            toJsonPrettyString("create bec vpc", response);
        }

        /**
         * Get the vpc detail.
         */
        @Test
        public void getBecVpc() {
            GetBecVpcRequest request = new GetBecVpcRequest();
            request.setVpcId("vpc-sviuw5nywgto");
            GetBecVpcResponse response = client.getBecVpc(request);
            toJsonPrettyString("get a bec vpc detail", response);
        }

        /**
         * Get the vpcs.
         */
        @Test
        public void getBecVpcs() {
            GetBecVpcsRequest request = new GetBecVpcsRequest();
            GetBecVpcsResponse response = client.getBecVpcs(request);
            toJsonPrettyString("get bec vpcs", response);
        }

        /**
         * Update the vpc.
         */
        @Test
        public void updateBecVpc() {
            UpdateBecVpcRequest request = new UpdateBecVpcRequest();
            request.setVpcId("vpc-sviuw5nywgto");
            request.setName("vpc-java-sdk-zy-1113-update33");
            UpdateBecVpcResponse response = client.updateBecVpc(request);
            toJsonPrettyString("update bec vpc", response);
        }

        /**
         * Delete the vpc.
         */
        @Test
        public void deleteBecVpc() {
            DeleteBecVpcRequest request = new DeleteBecVpcRequest();
            request.setVpcId("vpc-eullpyn6r64r");
            DeleteBecVpcResponse response = client.deleteBecVpc(request);
            toJsonPrettyString("delete bec vpc", response);
        }
    }
    /**
     * Test case about vpc end.
     */

    /**
     * Test case about subnet begin.
     */
    public static class NetworkVpcSubsetTest extends BecBase {

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

        /**
         * Create a subset.
         */
        @Test
        public void createBecSubnet() {
            Tag tag = new Tag();
            tag.setTagKey("bec-zy-key");
            tag.setTagValue("bec-zy-key-val");
            List<Tag> tags = new ArrayList<Tag>() {{
                add(tag);
            }};

            CreateBecSubnetRequest request = new CreateBecSubnetRequest();
            request.setName("subset-java-sdk-zy-1115-candelete");
            request.setVpcId("vpc-sviuw5nywgto");
            request.setCidr("192.168.8.0/24");
            request.setDescription("test sdk interface-15");
            request.setTags(tags);
            CreateBecSubnetResponse response = client.createBecSubnet(request);
            toJsonPrettyString("create a bec vpc subset", response);
        }

        /**
         * Get the subset detail.
         */
        @Test
        public void getBecSubnet() {
            GetBecSubnetRequest request = new GetBecSubnetRequest();
            request.setSubnetId("sbn-rysx6uofdjhz");
            GetBecSubnetResponse response = client.getBecSubnet(request);
            toJsonPrettyString("get bec subset detail", response);
        }

        /**
         * Get the subsets.
         */
        @Test
        public void getBecSubnets() {
            GetBecSubnetsRequest request = new GetBecSubnetsRequest();
            GetBecSubnetsResponse response = client.getBecSubnets(request);
            toJsonPrettyString("get bec subsets", response);
        }

        /**
         * Update the subset.
         */
        @Test
        public void updateBecSubnet() {
            UpdateBecSubnetRequest request = new UpdateBecSubnetRequest();
            request.setSubnetId("sbn-rysx6uofdjhz");
            request.setName("subn-java-sdk-zy-1115-update-3333");
            request.setDescription("hengheng");
            UpdateBecSubnetResponse response = client.updateBecSubnet(request);
            toJsonPrettyString("update bec subset", response);
        }

        /**
         * Delete the subset.
         */
        @Test
        public void deleteBecSubnet() {
            DeleteBecSubnetRequest request = new DeleteBecSubnetRequest();
            request.setSubnetId("sbn-rysx6uofdjhz");
            DeleteBecSubnetResponse response = client.deleteBecSubnet(request);
            toJsonPrettyString("delete bec subset", response);
        }
    }
    /**
     * Test case about subnet end.
     */

    /**
     * Test case about security group begin.
     */
    public static class NetworkSecurityGroupTest extends BecBase {

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

        /**
         * Create a security-group.
         */
        @Test
        public void createBecSecurityGroup() {
            CreateBecSecurityGroupRequest request = new CreateBecSecurityGroupRequest();
            request.setName("security-group-java-sdk-zy-1115-22");
            request.setDesc("from sdk-22");
            SecurityGroupRule rule = new SecurityGroupRule();
            rule.setDirection("ingress");
            rule.setEtherType("IPv4");
            rule.setSourceIp("10.178.3.0/24");
            rule.setPortRange("3333-9999");
            rule.setProtocol("tcp");
            rule.setRemark("from sdk-22");
            List<SecurityGroupRule> rules = Arrays.asList(rule);
            request.setRules(rules);
            CreateBecSecurityGroupResponse response = client.createBecSecurityGroup(request);
            toJsonPrettyString("create bec security group", response);
        }

        /**
         * Batch create security-group rules.
         * TODO need 优化.
         */
        @Test
        public void batchCreateBecSecurityGroupRules() {
            BatchCreateBecSgRulesRequest request = new BatchCreateBecSgRulesRequest();
            request.setSecurityGroupId("sg-segyu78p");
            SecurityGroupRule rule = new SecurityGroupRule();
            rule.setDirection("ingress");
            rule.setEtherType("IPv4");
            rule.setSourceIp("10.178.4.0/24");
            rule.setPortRange("1111-9999");
            rule.setProtocol("tcp");
            rule.setRemark("from sdk-22");
            rule.setDirection("ingress");
            rule.setRemark("from sdk-rule-22");
            List<SecurityGroupRule> rules = Arrays.asList(rule);
            request.setRules(rules);
            BatchCreateBecSgRulesResponse response = client.batchCreateBecSecurityGroupRules(request);
            toJsonPrettyString("batch create bec security group rules", response);
        }

        /**
         * Get the security group detail.
         */
        @Test
        public void getBecSecurityGroup() {
            GetBecSecurityGroupRequest request = new GetBecSecurityGroupRequest();
            request.setSecurityGroupId("sg-segyu78p");
            GetBecSecurityGroupResponse response = client.getBecSecurityGroup(request);
            toJsonPrettyString("get bec security group detail", response);
        }

        /**
         * Get the security groups.
         */
        @Test
        public void getBecSecurityGroups() {
            GetBecSecurityGroupsRequest request = new GetBecSecurityGroupsRequest();
            GetBecSecurityGroupsResponse response = client.getBecSecurityGroups(request);
            toJsonPrettyString("get bec security groups", response);
        }

        /**
         * Update the security group.
         */
        @Test
        public void updateBecSecurityGroup() {
            UpdateBecSecurityGroupRequest request = new UpdateBecSecurityGroupRequest();
            request.setSecurityGroupId("sg-segyu78p");
            request.setName("security-group-java-sdk-zy-1113-notdelete-update33");
            request.setDesc("security-group-java-sdk-zy-1113-update2233");
            UpdateBecSecurityGroupResponse response = client.updateBecSecurityGroup(request);
            toJsonPrettyString("update bec security group", response);
        }

        /**
         * Update the security group rule.
         */
        @Test
        public void updateBecSecurityGroupRule() {
            UpdateBecSecurityGroupRuleRequest request = new UpdateBecSecurityGroupRuleRequest();
            request.setSecurityGroupId("sg-segyu78p");
            request.setSecurityGroupRuleId("sgr-segyu78p-iatacwd0");
            request.setRemark("security-group-rule-java-sdk-zy-1113-update33");
            request.setDirection("ingress");
//            request.setProtocol("tcp");
//            request.setPortRange("2001-10001");
            toJsonPrettyString("update bec security group rule request", request);
            UpdateBecSecurityGroupRuleResponse response = client.updateBecSecurityGroupRule(request);
            toJsonPrettyString("update bec security group rule", response);
        }

        /**
         * Batch delete the security group rule.
         */
        @Test
        public void batchDeleteBecSecurityGroupRule() {
            BatchDeleteBecSgRulesRequest request = new BatchDeleteBecSgRulesRequest();
            request.setSecurityGroupId("sg-segyu78p");
            List<String> securityGroupRuleIds = Arrays.asList("sgr-segyu78p-xthlzxhl");
            request.setSecurityGroupRuleIds(securityGroupRuleIds);
            BatchDeleteBecSgRulesResponse response = client.batchDeleteBecSecurityGroupRules(request);
            toJsonPrettyString("batch delete bec security group rules", response);
        }

        /**
         * Delete the security group.
         */
        @Test
        public void deleteBecSecurityGroup() {
            DeleteBecSecurityGroupRequest request = new DeleteBecSecurityGroupRequest();
            request.setSecurityGroupId("sg-gmqqayvw");
            DeleteBecSecurityGroupResponse response = client.deleteBecSecurityGroup(request);
            toJsonPrettyString("delete bec security group", response);
        }
    }
    /**
     * Test case about security group end.
     */

    /**
     * Test case about route table begin.
     */
    public static class NetworkRouteTableTest extends BecBase {

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

        /**
         * Create a route rule.
         */
        @Test
        public void createBecRouteRule() {
            CreateBecRouteRuleRequest request = new CreateBecRouteRuleRequest();
            request.setTableId("rtb-hd23kbc24ain");
            request.setNexthop("vm-gvjnit31-cn-changzhou-ix-kae90");
            request.setIpVersion(4);
            request.setDescription("from java sdk-2");
            request.setSourceAddress("192.168.6.0/24");
            request.setRouteType(RouteTypeEnum.CUSTOM);
            request.setDestinationAddress("192.168.7.0/24");
            CreateBecRouteRuleResponse response = client.createBecRouteRule(request);
            toJsonPrettyString("create bec route rule", response);
        }

        /**
         * Get the route table detail.
         */
        @Test
        public void getBecRouteTable() {
            GetBecRouteTableRequest request = new GetBecRouteTableRequest();
            request.setTableId("rtb-hd23kbc24ain");
            GetBecRouteTableResponse response = client.getBecRouteTable(request);
            toJsonPrettyString("get bec route table detail", response);
        }

        /**
         * Get the route tables.
         */
        @Test
        public void getBecRouteTables() {
            GetBecRouteTablesRequest request = new GetBecRouteTablesRequest();
            GetBecRouteTablesResponse response = client.getBecRouteTables(request);
            toJsonPrettyString("get bec route tables", response);
        }

        /**
         * Get the route table rules.
         */
        @Test
        public void getBecRouteRules() {
            GetBecRouteRulesRequest request = new GetBecRouteRulesRequest();
            request.setTableId("rtb-hd23kbc24ain");
            GetBecRouteRulesResponse response = client.getBecRouteRules(request);
            toJsonPrettyString("get bec route rules", response);
        }

        /**
         * Update the route table.
         */
        @Test
        public void updateBecRouteTable() {
            UpdateBecRouteTableRequest request = new UpdateBecRouteTableRequest();
            request.setTableId("rtb-hd23kbc24ain");
            request.setTableName("default-upto-zyc-updatefromsdk-up32");
            UpdateBecRouteTableResponse response = client.updateBecRouteTable(request);
            toJsonPrettyString("update bec route table", response);
        }

        /**
         * Delete the route rule.
         */
        @Test
        public void deleteBecRouteRule() {
            DeleteBecRouteRuleRequest request = new DeleteBecRouteRuleRequest();
            request.setRuleId("rtr-6skdelm0gnum");
            DeleteBecRouteRuleResponse response = client.deleteBecRouteRule(request);
            toJsonPrettyString("delete bec route rule", response);
        }
    }
    /**
     * Test case about route table end.
     */

    /**
     * Test case about acl begin.
     */
    public static class NetworkAclTest extends BecBase {

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

        /**
         * Batch create a acl.
         */
        @Test
        public void batchCreateBecAclRule() {
            BatchCreateBecAclRulesRequest request = new BatchCreateBecAclRulesRequest();
            request.setSubnetId("sbn-afay2zni8abr");
            AclRule aclRule = new AclRule();
            List<AclRule> aclRules = Arrays.asList(aclRule);
            aclRule.setSubnetId("sbn-afay2zni8abr");
            aclRule.setAction("allow");
            aclRule.setDescription("description-sdk");
            aclRule.setDirection("ingress");
            aclRule.setEtherType("IPv4");
            aclRule.setProtocol("tcp");
            aclRule.setPosition(3457);
            aclRule.setSourcePort("100-999");
            aclRule.setDestinationPort("3034");
            aclRule.setSourceIpAddress("12.1.2.6/8");
            aclRule.setDestinationIpAddress("192.168.3.0/24");
            request.setAclRules(aclRules);
            BatchCreateBecAclRulesResponse response = client.batchCreateBecAclRules(request);
            toJsonPrettyString("create bec acl rule", response);
        }

        /**
         * Get the acl detail.
         */
        @Test
        public void getBecAcl() {
            GetBecAclRequest request = new GetBecAclRequest();
            request.setVpcId("vpc-sviuw5nywgto");
            GetBecAclResponse response = client.getBecAcl(request);
            toJsonPrettyString("get bec acl detail", response);
        }

        /**
         * Get the acls.
         */
        @Test
        public void getBecAcls() {
            GetBecAclsRequest request = new GetBecAclsRequest();
            GetBecAclsResponse response = client.getBecAcls(request);
            toJsonPrettyString("get bec acls", response);
        }

        /**
         * Update the acl.
         */
        @Test
        public void updateBecAcl() {
            UpdateBecAclRequest request = new UpdateBecAclRequest();
            request.setAclId("acl-5wfka1h0");
            request.setName("默认ACL-zy-notdelete-updatefromsdk-2");
            UpdateBecAclResponse response = client.updateBecAcl(request);
            toJsonPrettyString("update bec acl table", response);
        }

        /**
         * Update the acl rule.
         */
        @Test
        public void updateBecAclRule() {
            UpdateBecAclRuleRequest request = new UpdateBecAclRuleRequest();
            request.setAclRuleId("ar-gwkj37koklr7");
            AclRule aclRule = new AclRule();
            aclRule.setAction("allow");
            aclRule.setDescription("description-sdk-2");
            aclRule.setDirection("ingress");
            aclRule.setEtherType("IPv4");
            aclRule.setProtocol("tcp");
            aclRule.setPosition(345);
            aclRule.setSourcePort("1-999");
            aclRule.setSubnetId("sbn-afay2zni8abr");
            aclRule.setDestinationPort("303");
            aclRule.setSourceIpAddress("12.2.2.7/8");
            aclRule.setDestinationIpAddress("192.168.3.0/24");
            request.setAclRule(aclRule);
            UpdateBecAclRuleResponse response = client.updateBecAclRule(request);
            toJsonPrettyString("update bec acl rule", response);
        }

        /**
         * Batch delete the acl rule.
         */
        @Test
        public void batchDeleteBecAclRule() {
            BatchDeleteBecAclRulesRequest request = new BatchDeleteBecAclRulesRequest();
            request.setAclRuleIds(Arrays.asList("ar-kyjipfhiqorn"));
            BatchDeleteBecAclRulesResponse response = client.batchDeleteBecAclRules(request);
            toJsonPrettyString("delete bec acl rule", response);
        }
    }
    /**
     * Test case about acl end.
     */

    /**
     * Test case about app blb begin.
     */
    public static class AppBlbInstanceTest extends BecBase {

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

        /**
         * Create app blb.
         */
        @Test
        public void createBecAppBlb() {
            CreateBecAppBlbRequest request = CreateBecAppBlbRequest
                    .builder()
                    .name("appblb-changzhou-zy-sdk-11222-3")
                    .desc("validate app blb v2")
                    .needPublicIp(true)
                    .regionId("cn-changzhou-ix")
                    .subnetId("sbn-xekp4ltw")
                    .subServiceProviders(Arrays.asList("un"))
                    .vpcId("vpc-wwqoaez8")
                    .build();
            CreateBecAppBlbResponse response = client.createBecAppBlbV2(request);
            toJsonPrettyString("create bec app blb", response);
        }

        /**
         * Get the app blb detail.
         */
        @Test
        public void getBecAppBlb() {
            GetBecAppBlbRequest request = new GetBecAppBlbRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            GetBecAppBlbResponse response = client.getBecAppBlbV2(request);
            toJsonPrettyString("get app blb detail", response);
        }

        /**
         * Get the app blbs.
         */
        @Test
        public void getBecAppBlbs() {
            GetBecAppBlbsRequest request = new GetBecAppBlbsRequest();
            GetBecAppBlbsResponse response = client.getBecAppBlbsV2(request);
            toJsonPrettyString("get app blbs", response);
        }

        /**
         * Update the app blb.
         */
        @Test
        public void updateBecAppBlb() {
            UpdateBecAppBlbRequest request = new UpdateBecAppBlbRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setName("hhhhh-zy-candelete-updatefromsdk-up3");
            request.setDesc("desc sdk hhh");
            UpdateBecAppBlbResponse response = client.updateBecAppBlbV2(request);
            toJsonPrettyString("update app blb", response);
        }

        /**
         * Delete the app blb.
         */
        @Test
        public void deleteBecAppBlb() {
            DeleteBecAppBlbRequest request = new DeleteBecAppBlbRequest();
            request.setBlbId("applb-cn-changzhou-ix-vtgdhbwn");
            DeleteBecAppBlbResponse response = client.deleteBecAppBlbV2(request);
            toJsonPrettyString("delete app blb", response);
        }

        /**
         * Create app blb tcp listener.
         */
        @Test
        public void createBecAppBlbTCPListener() {
            CreateBecAppBlbTCPListenerRequest request = CreateBecAppBlbTCPListenerRequest
                    .builder()
                    .blbId("applb-cn-changzhou-ix-sxx9thkm")
                    .listenerPort(3009)
                    .scheduler("RoundRobin")
                    .tcpSessionTimeout(903)
                    .build();
            CreateBecAppBlbTCPListenerResponse response = client.createBecAppBlbTCPListenerV2(request);
            toJsonPrettyString("create bec app blb tcp listener", response);
        }

        /**
         * Get the app blb tcp listeners.
         */
        @Test
        public void getBecAppBlbTCPListeners() {
            GetBecAppBlbTCPListenersRequest request = new GetBecAppBlbTCPListenersRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setListenerPort(19);
            GetBecAppBlbTCPListenersResponse response = client.getBecAppBlbTCPListenersV2(request);
            toJsonPrettyString("get app blb tcp listeners", response);
        }

        /**
         * Update the app blb tcp listener.
         */
        @Test
        public void updateBecAppBlbTCPListener() {
            UpdateBecAppBlbTCPListenerRequest request = new UpdateBecAppBlbTCPListenerRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setListenerPort(19);
            request.setScheduler("LeastConnection");
            request.setTcpSessionTimeout(230);
            UpdateBecAppBlbTCPListenerResponse response = client.updateBecAppBlbTCPListenerV2(request);
            toJsonPrettyString("update app blb tcp listener", response);
        }

        /**
         * Delete the app blb tcp listener.
         */
        @Test
        public void batchDeleteAppBlbTcpListeners() {
            BatchDeleteBecAppBlbListenersRequest request = new BatchDeleteBecAppBlbListenersRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            ListenerModel listenerModel = new ListenerModel();
            listenerModel.setPort("3029");
            listenerModel.setType("TCP");
            List<ListenerModel> portTypeList = Arrays.asList(listenerModel);
            request.setPortTypeList(portTypeList);
            BatchDeleteBecAppBlbListenersResponse response = client.batchDeleteBecAppBlbListenersV2(request);
            toJsonPrettyString("delete app blb listeners", response);
        }

        /**
         * Create app blb udp listener.
         */
        @Test
        public void createBecAppBlbUDPListener() {
            CreateBecAppBlbUDPListenerRequest request = CreateBecAppBlbUDPListenerRequest
                    .builder()
                    .blbId("applb-cn-changzhou-ix-sxx9thkm")
                    .listenerPort(2038)
                    .scheduler("RoundRobin")
                    .udpSessionTimeout(903)
                    .build();
            CreateBecAppBlbUDPListenerResponse response = client.createBecAppBlbUDPListenerV2(request);
            toJsonPrettyString("create bec app blb udp listener", response);
        }

        /**
         * Get the app blb udp listeners.
         */
        @Test
        public void getBecAppBlbUDPListeners() {
            GetBecAppBlbUDPListenersRequest request = new GetBecAppBlbUDPListenersRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setListenerPort(2039);
            GetBecAppBlbUDPListenersResponse response = client.getBecAppBlbUDPListenersV2(request);
            toJsonPrettyString("get app blb udp listeners", response);
        }

        /**
         * Update the app blb udp listener.
         */
        @Test
        public void updateBecAppBlbUDPListener() {
            UpdateBecAppBlbUDPListenerRequest request = new UpdateBecAppBlbUDPListenerRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setListenerPort(2039);
            request.setScheduler("LeastConnection");
            request.setUdpSessionTimeout(239);
            UpdateBecAppBlbUDPListenerResponse response = client.updateBecAppBlbUDPListenerV2(request);
            toJsonPrettyString("update app blb udp listener", response);
        }

        /**
         * Delete the app blb udp listeners.
         */
        @Test
        public void batchDeleteAppBlbUdpListeners() {
            BatchDeleteBecAppBlbListenersRequest request = new BatchDeleteBecAppBlbListenersRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            ListenerModel listenerModel = new ListenerModel();
            listenerModel.setPort("2038");
            listenerModel.setType("UDP");
            List<ListenerModel> portTypeList = Arrays.asList(listenerModel);
            request.setPortTypeList(portTypeList);
            BatchDeleteBecAppBlbListenersResponse response = client.batchDeleteBecAppBlbListenersV2(request);
            toJsonPrettyString("delete app blb udp listeners", response);
        }

        /**
         * Create app blb listener policies.
         */
        @Test
        public void createBecAppBlbListenerPolicies() {
            CreateAppPolicy createAppPolicy = new CreateAppPolicy();
            createAppPolicy.setAppIpGroupId("bec_ip_group-zni2q1rw");
            createAppPolicy.setPriority(310);
            createAppPolicy.setDesc("listener policy");
            CreateBecAppBlbListenerPoliciesRequest request = CreateBecAppBlbListenerPoliciesRequest
                    .builder()
                    .blbId("applb-cn-changzhou-ix-sxx9thkm")
                    .appPolicyVos(Arrays.asList(createAppPolicy))
                    .listenerPort(3009)
                    .type("TCP")
                    .build();
            CreateBecAppBlbListenerPoliciesResponse response = client.createBecAppBlbListenerPoliciesV2(request);
            toJsonPrettyString("create bec app blb listener policies", response);
        }

        /**
         * Get the app blb listener policies.
         */
        @Test
        public void getBecAppBlbListenerPolicies() {
            GetBecAppBlbListenerPoliciesRequest request = new GetBecAppBlbListenerPoliciesRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setPort(3009);
            request.setType("TCP");
            GetBecAppBlbListenerPoliciesResponse response = client.getBecAppBlbListenerPoliciesV2((request));
            toJsonPrettyString("get app blb listener policies", response);
        }

        /**
         * Delete the app blb listener policies.
         */
        @Test
        public void deleteAppBlbListenerPolicies() {
            DeleteBecAppBlbListenerPoliciesRequest request = new DeleteBecAppBlbListenerPoliciesRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setPort(3009);
            request.setType("TCP");
            request.setPolicyIdList(Arrays.asList("bec_policy-hulbcxbn"));
            DeleteBecAppBlbListenerPoliciesResponse response = client.deleteBecAppBlbListenerPoliciesV2(request);
            toJsonPrettyString("delete app blb listener policies", response);
        }

        /**
         * Create app blb ip group.
         */
        @Test
        public void createBecAppBlbIpGroup() {
            IpGroupMemberForm memberForm = new IpGroupMemberForm();
            memberForm.setIp("172.18.224.3");
            memberForm.setPort(8483);
            memberForm.setWeight(23);
            List<IpGroupMemberForm> memberList = Arrays.asList(memberForm);
            CreateBecAppBlbIpGroupRequest request = CreateBecAppBlbIpGroupRequest
                    .builder()
                    .blbId("applb-cn-changzhou-ix-sxx9thkm")
                    .name("ipg-fromsdk-zy-mustdelete-1")
                    .desc("not del")
                    .memberList(memberList)
                    .build();
            CreateBecAppBlbIpGroupResponse response = client.createBecAppBlbIpGroupV2(request);
            toJsonPrettyString("create bec app blb ip group", response);
        }

        /**
         * Get the app blb ip groups.
         */
        @Test
        public void getBecAppBlbIpGroups() {
            GetBecAppBlbIpGroupsRequest request = new GetBecAppBlbIpGroupsRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setName("ipg-fromsdk-zy-notdelete");
            request.setExactlyMatch(true);
            GetBecAppBlbIpGroupsResponse response = client.getBecAppBlbIpGroupsV2(request);
            toJsonPrettyString("get app blb ip groups", response);
        }

        /**
         * Update the app blb ip group.
         */
        @Test
        public void updateBecAppBlbIpGroup() {
            UpdateBecAppBlbIpGroupRequest request = new UpdateBecAppBlbIpGroupRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setIpGroupId("bec_ip_group-zni2q1rw");
            request.setName("ipg-fromsdk-zy-notdelete-1122");
            request.setDesc("ipg-fromsdk-zy-notdelete-1122-desc");
            UpdateBecAppBlbIpGroupResponse response = client.updateBecAppBlbIpGroupV2(request);
            toJsonPrettyString("update app blb ip group", response);
        }

        /**
         * Delete the app blb ip group.
         */
        @Test
        public void deleteAppBlbIpGroup() {
            DeleteBecAppBlbIpGroupRequest request = new DeleteBecAppBlbIpGroupRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setIpGroupId("bec_ip_group-vtvb8gju");
            DeleteBecAppBlbIpGroupResponse response = client.deleteBecAppBlbIpGroupV2(request);
            toJsonPrettyString("delete app blb ip group", response);
        }

        /**
         * Create app blb ip group policies.
         */
        @Test
        public void createBecAppBlbIpGroupBackendPolicies() {
            CreateBecAppBlbIpGroupBackendPolicyRequest request = CreateBecAppBlbIpGroupBackendPolicyRequest
                    .builder()
                    .blbId("applb-cn-changzhou-ix-sxx9thkm")
                    .healthCheck("TCP")
                    .healthCheckDownRetry(4)
                    .healthCheckHost("172.18.224.10")
                    .healthCheckUpRetry(2)
                    .healthCheckPort(1374)
                    .healthCheckIntervalInSecond(4)
                    .healthCheckUrlPath("/")
                    .healthCheckNormalStatus("http_103")
                    .healthCheckTimeoutInSecond(12)
                    .ipGroupId("bec_ip_group-zni2q1rw")
                    .type("HTTP")
                    .build();
            CreateBecAppBlbIpGroupBackendPolicyResponse response = client.createBecAppBlbIpGroupBackendPoliciesV2(request);
            toJsonPrettyString("create bec app blb ip group policies", response);
        }

        /**
         * Get the app blb ip group policies.
         */
        @Test
        public void getBecAppBlbIpGroupBackendPolicies() {
            GetBecAppBlbIpGroupBackendPoliciesRequest request = new GetBecAppBlbIpGroupBackendPoliciesRequest();
            request.setBlbId("applb-cn-preonline-cm-ajugnorw");
            request.setIpGroupId("bec_ip_group-zg3tw0ob");
            GetBecAppBlbIpGroupBackendPoliciesResponse response = client.getBecAppBlbIpGroupPoliciesV2(request);
            toJsonPrettyString("get app blb ip group policies", response);
        }

        /**
         * Update the app blb ip group policy.
         */
        @Test
        public void updateAppBlbIpGroupBackendPolicy() {
            UpdateBecAppBlbIpGroupBackendPolicyRequest request = new UpdateBecAppBlbIpGroupBackendPolicyRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setId("bec_ip_group_policy-wteennth");
            request.setIpGroupId("bec_ip_group-zni2q1rw");
            request.setHealthCheckPort(13);
            UpdateBecAppBlbIpGroupBackendPolicyResponse response = client.updateBecAppBlbIpGroupPolicyV2(request);
            toJsonPrettyString("update app blb ip group policy", response);
        }

        /**
         * Delete the app blb ip group policies.
         */
        @Test
        public void deleteAppBlbIpGroupBackendPolicies() {
            DeleteBecAppBlbIpGroupBackendPoliciesRequest request = DeleteBecAppBlbIpGroupBackendPoliciesRequest
                    .builder()
                    .blbId("applb-cn-preonline-cm-ajugnorw")
                    .ipGroupId("bec_ip_group-zg3tw0ob")
                    .backendPolicyIdList(Arrays.asList("bec_ip_group_policy-m9nctj2z"))
                    .build();
            DeleteBecAppBlbIpGroupBackendPoliciesResponse response = client.deleteBecAppBlbIpGroupBackendPoliciesV2(request);
            toJsonPrettyString("delete app blb ip group policies", response);
        }

        /**
         * Create app blb ip group members.
         */
        @Test
        public void createBecAppBlbIpGroupMembers() {
            IpGroupMemberForm memberForm = new IpGroupMemberForm();
            memberForm.setIp("172.18.224.31");
            memberForm.setWeight(13);
            memberForm.setPort(2090);
            List<IpGroupMemberForm> memberList = new ArrayList<>();
            memberList.add(memberForm);
            CreateBecAppBlbIpGroupMembersRequest request = CreateBecAppBlbIpGroupMembersRequest
                    .builder()
                    .blbId("applb-cn-changzhou-ix-sxx9thkm")
                    .ipGroupId("bec_ip_group-zni2q1rw")
                    .memberList(memberList)
                    .build();
            CreateBecAppBlbIpGroupMembersResponse response = client.createBecAppBlbIpGroupMembersV2(request);
            toJsonPrettyString("create bec app blb ip group members", response);
        }

        /**
         * Get the app blb ip group members.
         */
        @Test
        public void getBecAppBlbIpGroupMembers() {
            GetBecAppBlbIpGroupMembersRequest request = new GetBecAppBlbIpGroupMembersRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setIpGroupId("bec_ip_group-zni2q1rw");
            GetBecAppBlbIpGroupMembersResponse response = client.getBecAppBlbIpGroupMembersV2(request);
            toJsonPrettyString("get app blb ip group members", response);
        }

        /**
         * Update the app blb ip group members.
         */
        @Test
        public void updateAppBlbIpGroupMembers() {
            UpdateIpGroupMemberForm memberForm = new UpdateIpGroupMemberForm();
            memberForm.setMemberId("bec_ip_member-53v69scq");
            memberForm.setPort(2091);
            memberForm.setWeight(84);
            UpdateBecAppBlbIpGroupMembersRequest request = new UpdateBecAppBlbIpGroupMembersRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setIpGroupId("bec_ip_group-zni2q1rw");
            request.setMemberList(Arrays.asList(memberForm));
            UpdateBecAppBlbIpGroupMembersResponse response = client.updateBecAppBlbIpGroupMembersV2(request);
            toJsonPrettyString("update app blb ip group members", response);
        }

        /**
         * Delete the app blb ip group members.
         */
        @Test
        public void deleteAppBlbIpGroupMembers() {
            DeleteBecAppBlbIpGroupMembersRequest request = new DeleteBecAppBlbIpGroupMembersRequest();
            request.setBlbId("applb-cn-changzhou-ix-sxx9thkm");
            request.setIpGroupId("bec_ip_group-zni2q1rw");
            request.setMemberIdList(Arrays.asList("bec_ip_member-53v69scq"));
            DeleteBecAppBlbIpGroupMembersResponse response = client.deleteBecAppBlbIpGroupMembersV2(request);
            toJsonPrettyString("delete app blb ip group members", response);
        }
    }
    /**
     * Test case about app blb end.
     */

    /**
     * Test case about nat begin.
     */
    public static class NatTest extends BecBase {

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

        /**
         * Create nat.
         */
        @Test
        public void batchCreateBecNat() {
            NatDeploymentInstance natDeploymentInstance = new NatDeploymentInstance();
            natDeploymentInstance.setRegionId("cn-changzhou-ix");
            natDeploymentInstance.setSpec("small");
            natDeploymentInstance.setVpcId("vpc-wwqoaez8");
            natDeploymentInstance.setSubServiceProviders(Arrays.asList("cm"));
            BatchCreateBecNatsRequest request = BatchCreateBecNatsRequest
                    .builder()
                    .bandwidth(11)
                    .deployInstances(Arrays.asList(natDeploymentInstance))
                    .desc("validate nat")
                    .name("nat-changzhou-zy-sdk-1122-2")
                    .build();
            BatchCreateBecNatsResponse response = client.batchCreateBecNat(request);
            toJsonPrettyString("create nat", response);
        }

        /**
         * Get the nat detail.
         */
        @Test
        public void getBecNat() {
            GetBecNatRequest request = new GetBecNatRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            GetBecNatResponse response = client.getBecNat(request);
            toJsonPrettyString("get nat detail", response);
        }

        /**
         * Get the nats.
         */
        @Test
        public void getBecNats() {
            GetBecNatsRequest request = new GetBecNatsRequest();
            GetBecNatsResponse response = client.getBecNats(request);
            toJsonPrettyString("get nats", response);
        }

        /**
         * Update the nat.
         */
        @Test
        public void updateBecNat() {
            UpdateBecNatRequest request = new UpdateBecNatRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            request.setName("nat-zy-notdelete-updatefromsdk-up333");
            request.setDesc("desc 33sdk");
            UpdateBecNatResponse response = client.updateBecNat(request);
            toJsonPrettyString("update nat", response);
        }

        /**
         * Update the nat bandwidth.
         */
        @Test
        public void updateBecNatBandwidth() {
            UpdateBecNatBandwidthRequest request = new UpdateBecNatBandwidthRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            request.setBandwidth(17);
            UpdateBecNatBandwidthResponse response = client.updateBecNatBandwidth(request);
            toJsonPrettyString("update nat bandwidth", response);
        }

        /**
         * Batch delete the nats.
         */
        @Test
        public void batchDeleteBecNats() {
            BatchDeleteBecNatsRequest request = new BatchDeleteBecNatsRequest();
            request.setNatIdList(Arrays.asList("nat-cn-jiangmen1-cm-actgdewe"));
            BatchDeleteBecNatsResponse response = client.batchDeleteBecNats(request);
            toJsonPrettyString("delete nats", response);
        }

        /**
         * Get nat metrics.
         */
        @Test
        public void getNatMetrics() {
            GetBecNatMetricsRequest request = new GetBecNatMetricsRequest();
            request.setNatId("nat-cn-jinan2-cm-8l9vcxn0");
            request.setMetricsType("BANDWIDTH_RECEIVE");
            request.setStart(1732444950L);
            request.setEnd(1732531350L);
            GetBecNatMetricsResponse response = client.getBecNatMetrics(request);
            toJsonPrettyString("get nat metrics", response);
        }

        /**
         * Create dnat rule.
         */
        @Test
        public void createBecDnatRule() {
            CreateBecDnatRuleRequest request = CreateBecDnatRuleRequest
                    .builder()
                    .natId("nat-cn-changzhou-ix-vydt73es")
                    .privateIpAddress("172.18.224.5")
                    .privatePort("8586")
                    .protocol("TCP")
                    .publicIpAddress("36.150.40.121")
                    .publicPort("8486")
                    .ruleName("dnatrule-changzhou-zy-sdk-1122-3")
                    .build();
            CreateBecDnatRuleResponse response = client.createBecDnatRule(request);
            toJsonPrettyString("create nat rule", response);
        }

        /**
         * Batch create dnat rules.
         */
        @Test
        public void batchCreateBecDnatRules() {
            CreateBecDnatRuleRequest createBecDnatRuleRequest = new CreateBecDnatRuleRequest();
            createBecDnatRuleRequest.setPrivateIpAddress("172.18.224.6");
            createBecDnatRuleRequest.setPrivatePort("77");
            createBecDnatRuleRequest.setProtocol("TCP");
            createBecDnatRuleRequest.setPublicIpAddress("36.150.40.121");
            createBecDnatRuleRequest.setPublicPort("87");
            createBecDnatRuleRequest.setRuleName("batch-creat-urle-sdk");
            BatchCreateBecDnatRulesRequest request = BatchCreateBecDnatRulesRequest
                    .builder()
                    .natId("nat-cn-changzhou-ix-vydt73es")
                    .rules(Arrays.asList(createBecDnatRuleRequest))
                    .build();
            BatchCreateBecDnatRulesResponse response = client.batchCreateBecDnatRules(request);
            toJsonPrettyString("batch create dnat rules", response);
        }

        /**
         * Get the dnat rules.
         */
        @Test
        public void getBecDnatRules() {
            GetBecDnatRulesRequest request = new GetBecDnatRulesRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            GetBecDnatRulesResponse response = client.getBecDnatRules(request);
            toJsonPrettyString("get dnat rules", response);
        }

        /**
         * Update the dnat rule.
         */
        @Test
        public void updateBecDnatRule() {
            UpdateBecDnatRuleRequest request = new UpdateBecDnatRuleRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            request.setRuleId("rule-dvbqtjdvsnay");
            request.setRuleName("hhh");
            request.setPrivateIpAddress("172.18.224.7");
            request.setPrivatePort("13");
            request.setPublicIpAddress("36.150.40.121");
            request.setPublicPort("14");
            request.setProtocol("TCP");
            UpdateBecDnatRuleResponse response = client.updateBecDnatRule(request);
            toJsonPrettyString("update dnat rule", response);
        }

        /**
         * Batch delete the dnat rules.
         */
        @Test
        public void batchDeleteBecDnatRules() {
            BatchDeleteBecDnatRulesRequest request = new BatchDeleteBecDnatRulesRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            request.setRuleIdList(Arrays.asList("rule-dvbqtjdvsnay"));
            BatchDeleteBecDnatRulesResponse response = client.batchDeleteBecDnatRules(request);
            toJsonPrettyString("delete nats", response);
        }

        /**
         * Create snat rule.
         */
        @Test
        public void createBecSnatRule() {
            CreateBecSnatRuleRequest request = CreateBecSnatRuleRequest
                    .builder()
                    .natId("nat-cn-changzhou-ix-vydt73es")
                    .sourceCIDR("172.18.224.0/24")
                    .ruleName("snatrule-changzhou-zy-sdk-1122-3")
                    .build();
            CreateBecSnatRuleResponse response = client.createBecSnatRule(request);
            toJsonPrettyString("create nat rule", response);
        }

        /**
         * Batch create snat rules.
         */
        @Test
        public void batchCreateBecSnatRules() {
            CreateBecSnatRuleRequest createBecSnatRuleRequest = new CreateBecSnatRuleRequest();
            createBecSnatRuleRequest.setSourceCIDR("172.18.224.0/25");
            createBecSnatRuleRequest.setRuleName("batch-creat-snatrule-sdk");
            BatchCreateBecSnatRulesRequest request = BatchCreateBecSnatRulesRequest
                    .builder()
                    .natId("nat-cn-changzhou-ix-vydt73es")
                    .snatRules(Arrays.asList(createBecSnatRuleRequest))
                    .build();
            BatchCreateBecSnatRulesResponse response = client.batchCreateBecSnatRules(request);
            toJsonPrettyString("batch create snat rules", response);
        }

        /**
         * Get the snat rules.
         */
        @Test
        public void getBecSnatRules() {
            GetBecSnatRulesRequest request = new GetBecSnatRulesRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            GetBecSnatRulesResponse response = client.getBecSnatRules(request);
            toJsonPrettyString("get dnat rules", response);
        }

        /**
         * Update the snat rule.
         */
        @Test
        public void updateBecsnatRule() {
            UpdateBecSnatRuleRequest request = new UpdateBecSnatRuleRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            request.setRuleId("rule-wngcbpd7xldb");
            request.setRuleName("hhh-sss");
            request.setSourceCIDR("172.18.224.128/25");
            UpdateBecSnatRuleResponse response = client.updateBecSnatRule(request);
            toJsonPrettyString("update dnat rule", response);
        }

        /**
         * Batch delete the snat rules.
         */
        @Test
        public void batchDeleteBecSnatRules() {
            BatchDeleteBecSnatRulesRequest request = new BatchDeleteBecSnatRulesRequest();
            request.setNatId("nat-cn-changzhou-ix-vydt73es");
            request.setRuleIdList(Arrays.asList("rule-wngcbpd7xldb"));
            BatchDeleteBecSnatRulesResponse response = client.batchDeleteBecSnatRules(request);
            toJsonPrettyString("delete nats", response);
        }
    }
    /**
     * Test case about nat end.
     */
}
