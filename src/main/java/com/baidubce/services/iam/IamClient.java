/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.iam;

import java.util.Map;

import com.baidubce.BceClientConfiguration;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.iam.api.IamApi;
import com.baidubce.services.iam.model.CreateAccessKeyResponse;
import com.baidubce.services.iam.model.CreateGroupRequest;
import com.baidubce.services.iam.model.CreateGroupResponse;
import com.baidubce.services.iam.model.CreatePolicyRequest;
import com.baidubce.services.iam.model.CreatePolicyResponse;
import com.baidubce.services.iam.model.CreateRoleRequest;
import com.baidubce.services.iam.model.CreateRoleResponse;
import com.baidubce.services.iam.model.CreateUserRequest;
import com.baidubce.services.iam.model.CreateUserResponse;
import com.baidubce.services.iam.model.CreateVsAccountRequest;
import com.baidubce.services.iam.model.CreateVsAccountResponse;
import com.baidubce.services.iam.model.DisableAccessKeyResponse;
import com.baidubce.services.iam.model.GetGroupResponse;
import com.baidubce.services.iam.model.GetLoginProfileResponse;
import com.baidubce.services.iam.model.GetPolicyResponse;
import com.baidubce.services.iam.model.GetRoleResponse;
import com.baidubce.services.iam.model.GetUserResponse;
import com.baidubce.services.iam.model.GetVsAccountResponse;
import com.baidubce.services.iam.model.ListAccessKeyResponse;
import com.baidubce.services.iam.model.ListGroupResponse;
import com.baidubce.services.iam.model.ListGroupsForUserResponse;
import com.baidubce.services.iam.model.ListPoliciesForGroupResponse;
import com.baidubce.services.iam.model.ListPoliciesForRoleResponse;
import com.baidubce.services.iam.model.ListPoliciesForUserResponse;
import com.baidubce.services.iam.model.ListPolicyResponse;
import com.baidubce.services.iam.model.ListRoleResponse;
import com.baidubce.services.iam.model.ListUserResponse;
import com.baidubce.services.iam.model.ListUsersInGroupResponse;
import com.baidubce.services.iam.model.EnableAccessKeyResponse;
import com.baidubce.services.iam.model.ListVsAccountsRequest;
import com.baidubce.services.iam.model.ListVsAccountsResponse;
import com.baidubce.services.iam.model.UpdateGroupRequest;
import com.baidubce.services.iam.model.UpdateGroupResponse;
import com.baidubce.services.iam.model.UpdateLoginProfileRequest;
import com.baidubce.services.iam.model.UpdateLoginProfileResponse;
import com.baidubce.services.iam.model.UpdateRoleRequest;
import com.baidubce.services.iam.model.UpdateRoleResponse;
import com.baidubce.services.iam.model.UpdateUserRequest;
import com.baidubce.services.iam.model.UpdateUserResponse;
import com.google.common.collect.ImmutableMap;

/**
 * Iam
 */
public class IamClient extends BaseBceClient {
    // ------Endpoints------
    // map of all endpoints
    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.DEFAULT, "https://iam.bj.baidubce.com")
            .build();
    // ------Endpoints------

    // ------ServiceInfo------
    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Iam";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public IamClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public IamClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public IamClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> IAM_APIS = IamApi.getApis();

    /**
     * AddUserToGroup
     *
     * @param userName
     * @param groupName
     */
    public void addUserToGroup(String userName, String groupName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("addUserToGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName)
                .withPathParameter("groupName", groupName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * AttachPolicyToGroup
     *
     * @param groupName
     * @param policyName
     * @param policyType
     */
    public void attachPolicyToGroup(String groupName, String policyName, String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("attachPolicyToGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("groupName", groupName)
                .withPathParameter("policyName", policyName).get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * AttachPolicyToRole
     *
     * @param roleName
     * @param policyName
     * @param policyType
     */
    public void attachPolicyToRole(String roleName, String policyName, String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("attachPolicyToRole"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("roleName", roleName)
                .withPathParameter("policyName", policyName).get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * AttachPolicyToUser
     *
     * @param userName
     * @param policyName
     * @param policyType
     */
    public void attachPolicyToUser(String userName, String policyName, String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("attachPolicyToUser"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName)
                .withPathParameter("policyName", policyName).get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * CreateAccessKey
     *
     * @param userName
     * @return CreateAccessKeyResponse
     */
    public CreateAccessKeyResponse createAccessKey(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("createAccessKey"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, CreateAccessKeyResponse.class);
    }

    /**
     * CreateGroup
     *
     * @param body
     * @return CreateGroupResponse
     */
    public CreateGroupResponse createGroup(CreateGroupRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("createGroup"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateGroupResponse.class);
    }

    /**
     * CreatePolicy
     *
     * @param body
     * @return CreatePolicyResponse
     */
    public CreatePolicyResponse createPolicy(CreatePolicyRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("createPolicy"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreatePolicyResponse.class);
    }

    /**
     * CreateRole
     *
     * @param body
     * @return CreateRoleResponse
     */
    public CreateRoleResponse createRole(CreateRoleRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("createRole"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateRoleResponse.class);
    }

    /**
     * CreateUser
     *
     * @param body
     * @return CreateUserResponse
     */
    public CreateUserResponse createUser(CreateUserRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("createUser"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateUserResponse.class);
    }

    /**
     * DeleteAccessKey
     *
     * @param userName
     * @param accessKeyId
     */
    public void deleteAccessKey(String userName, String accessKeyId) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("deleteAccessKey"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName)
                .withPathParameter("accessKeyId", accessKeyId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DeleteGroup
     *
     * @param groupName
     */
    public void deleteGroup(String groupName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("deleteGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("groupName", groupName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DeleteLoginProfile
     *
     * @param userName
     */
    public void deleteLoginProfile(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("deleteLoginProfile"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DeletePolicy
     *
     * @param policyName
     */
    public void deletePolicy(String policyName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("deletePolicy"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("policyName", policyName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DeleteRole
     *
     * @param roleName
     */
    public void deleteRole(String roleName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("deleteRole"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("roleName", roleName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DeleteUser
     *
     * @param userName
     */
    public void deleteUser(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("deleteUser"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DetachPolicyFromGroup
     *
     * @param groupName
     * @param policyName
     * @param policyType
     */
    public void detachPolicyFromGroup(String groupName, String policyName, String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("detachPolicyFromGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("groupName", groupName)
                .withPathParameter("policyName", policyName).get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DetachPolicyFromRole
     *
     * @param roleName
     * @param policyName
     * @param policyType
     */
    public void detachPolicyFromRole(String roleName, String policyName, String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("detachPolicyFromRole"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("roleName", roleName)
                .withPathParameter("policyName", policyName).get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DetachPolicyFromUser
     *
     * @param userName
     * @param policyName
     * @param policyType
     */
    public void detachPolicyFromUser(String userName, String policyName, String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("detachPolicyFromUser"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName)
                .withPathParameter("policyName", policyName).get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * DisableAccessKey
     *
     * @param userName
     * @param accessKeyId
     */
    public DisableAccessKeyResponse disableAccessKey(String userName, String accessKeyId) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("disableAccessKey"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName)
                .withPathParameter("accessKeyId", accessKeyId).get();
        apiInfo.getQueries().put("disable", null);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, DisableAccessKeyResponse.class);
    }

    /**
     * EnableAccessKey
     *
     * @param userName
     * @param accessKeyId
     */
    public EnableAccessKeyResponse enableAccessKey(String userName, String accessKeyId) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("updateAccessKeyEnable"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName)
                .withPathParameter("accessKeyId", accessKeyId).get();
        apiInfo.getQueries().put("enable", null);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, EnableAccessKeyResponse.class);
    }

    /**
     * GetGroup
     *
     * @param groupName
     * @return GetGroupResponse
     */
    public GetGroupResponse getGroup(String groupName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("getGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("groupName", groupName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetGroupResponse.class);
    }

    /**
     * GetLoginProfile
     *
     * @param userName
     * @return GetLoginProfileResponse
     */
    public GetLoginProfileResponse getLoginProfile(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("getLoginProfile"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetLoginProfileResponse.class);
    }

    /**
     * GetPolicy
     *
     * @param policyName
     * @param policyType
     * @return GetPolicyResponse
     */
    public GetPolicyResponse getPolicy(String policyName, String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("getPolicy"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("policyName", policyName).get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetPolicyResponse.class);
    }

    /**
     * GetRole
     *
     * @param roleName
     * @return GetRoleResponse
     */
    public GetRoleResponse getRole(String roleName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("getRole"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("roleName", roleName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetRoleResponse.class);
    }

    /**
     * GetUser
     *
     * @param userName
     * @return GetUserResponse
     */
    public GetUserResponse getUser(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("getUser"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetUserResponse.class);
    }

    /**
     * ListAccessKey
     *
     * @param userName
     * @return ListAccessKeyResponse
     */
    public ListAccessKeyResponse listAccessKey(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listAccessKey"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListAccessKeyResponse.class);
    }

    /**
     * ListGroup
     *
     * @return ListGroupResponse
     */
    public ListGroupResponse listGroup() {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listGroup"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListGroupResponse.class);
    }

    /**
     * ListGroupsForUser
     *
     * @param userName
     * @return ListGroupsForUserResponse
     */
    public ListGroupsForUserResponse listGroupsForUser(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listGroupsForUser"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListGroupsForUserResponse.class);
    }

    /**
     * ListPoliciesForGroup
     *
     * @param groupName
     * @return ListPoliciesForGroupResponse
     */
    public ListPoliciesForGroupResponse listPoliciesForGroup(String groupName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listPoliciesForGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("groupName", groupName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListPoliciesForGroupResponse.class);
    }

    /**
     * ListPoliciesForRole
     *
     * @param roleName
     * @return ListPoliciesForRoleResponse
     */
    public ListPoliciesForRoleResponse listPoliciesForRole(String roleName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listPoliciesForRole"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("roleName", roleName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListPoliciesForRoleResponse.class);
    }

    /**
     * ListPoliciesForUser
     *
     * @param userName
     * @return ListPoliciesForUserResponse
     */
    public ListPoliciesForUserResponse listPoliciesForUser(String userName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listPoliciesForUser"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListPoliciesForUserResponse.class);
    }

    /**
     * ListPolicy
     *
     * @return ListPolicyResponse
     */
    public ListPolicyResponse listPolicy(String policyType) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listPolicy"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("policyType", policyType);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListPolicyResponse.class);
    }

    /**
     * ListRole
     *
     * @return ListRoleResponse
     */
    public ListRoleResponse listRole() {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listRole"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListRoleResponse.class);
    }

    /**
     * ListUser
     *
     * @return ListUserResponse
     */
    public ListUserResponse listUser() {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listUser"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListUserResponse.class);
    }

    /**
     * ListUsersInGroup
     *
     * @param groupName
     * @return ListUsersInGroupResponse
     */
    public ListUsersInGroupResponse listUsersInGroup(String groupName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listUsersInGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("groupName", groupName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListUsersInGroupResponse.class);
    }

    /**
     * RemoveUserFromGroup
     *
     * @param userName
     * @param groupName
     */
    public void removeUserFromGroup(String userName, String groupName) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("removeUserFromGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName)
                .withPathParameter("groupName", groupName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }


    /**
     * updateGroup
     *
     * @param groupName
     * @param body
     * @return UpdateLoginProfileResponse
     */
    public UpdateGroupResponse updateGroup(String groupName, UpdateGroupRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("updateGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("groupName", groupName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, UpdateGroupResponse.class);
    }

    /**
     * UpdateLoginProfile
     *
     * @param userName
     * @param body
     * @return UpdateLoginProfileResponse
     */
    public UpdateLoginProfileResponse updateLoginProfile(String userName, UpdateLoginProfileRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("updateLoginProfile"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, UpdateLoginProfileResponse.class);
    }

    /**
     * UpdateRole
     *
     * @param roleName
     * @param body
     * @return UpdateRoleResponse
     */
    public UpdateRoleResponse updateRole(String roleName, UpdateRoleRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("updateRole"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("roleName", roleName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, UpdateRoleResponse.class);
    }

    /**
     * UpdateUser
     *
     * @param userName
     * @param body
     * @return UpdateUserResponse
     */
    public UpdateUserResponse updateUser(String userName, UpdateUserRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("updateUser"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("userName", userName).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, UpdateUserResponse.class);
    }

    /**
     * CreateVirtualStoreAccount
     *
     * @param body
     * @return
     */
    public CreateVsAccountResponse createVsAccount(CreateVsAccountRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("createVsAccount"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateVsAccountResponse.class);
    }

    /**
     * GetVirtualStoreAccount
     *
     * @param userId
     * @return
     */
    public GetVsAccountResponse getVsAccount(String userId) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("getVsAccount"));
        String apiPath = apiInfo.getPath().withPathParameter("userId", userId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetVsAccountResponse.class);
    }

    /**
     * list Virtual store account
     *
     * @param body
     * @return
     */
    public ListVsAccountsResponse listVsAccounts(ListVsAccountsRequest body) {
        ApiInfo apiInfo = new ApiInfo(IAM_APIS.get("listVsAccount"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, ListVsAccountsResponse.class);
    }

}
