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
package com.baidubce.services.iam.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

/**
 * Iam api
 */
public class IamApi {
    /**
     * Api list with api name
     */
    private static Map<String, ApiInfo> APIS = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {
        // AddUserToGroup
        APIS.put("addUserToGroup", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/group/[groupName]/user/[userName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // AttachPolicyToGroup
        APIS.put("attachPolicyToGroup", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/group/[groupName]/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("policyType", null);
                    }
                }));
        // AttachPolicyToRole
        APIS.put("attachPolicyToRole", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/role/[roleName]/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("policyType", null);
                    }
                }));
        // AttachPolicyToUser
        APIS.put("attachPolicyToUser", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/user/[userName]/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("policyType", null);
                    }
                }));
        // CreateAccessKey
        APIS.put("createAccessKey", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/user/[userName]/accesskey"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // CreateGroup
        APIS.put("createGroup", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/group"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // CreatePolicy
        APIS.put("createPolicy", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/policy"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // CreateRole
        APIS.put("createRole", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/role"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // CreateUser
        APIS.put("createUser", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/user"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // DeleteAccessKey
        APIS.put("deleteAccessKey", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/user/[userName]/accesskey/[accessKeyId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // DeleteGroup
        APIS.put("deleteGroup", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/group/[groupName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // DeleteLoginProfile
        APIS.put("deleteLoginProfile", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/user/[userName]/loginProfile"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // DeletePolicy
        APIS.put("deletePolicy", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // DeleteRole
        APIS.put("deleteRole", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/role/[roleName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // DeleteUser
        APIS.put("deleteUser", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/user/[userName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // DetachPolicyFromGroup
        APIS.put("detachPolicyFromGroup", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/group/[groupName]/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("policyType", null);
                    }
                }));
        // DetachPolicyFromRole
        APIS.put("detachPolicyFromRole", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/role/[roleName]/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("policyType", null);
                    }
                }));
        // DetachPolicyFromUser
        APIS.put("detachPolicyFromUser", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/user/[userName]/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("policyType", null);
                    }
                }));
        // DisableAccessKey
        APIS.put("disableAccessKey", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/user/[userName]/accesskey/[accessKeyId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("disable", null);
                    }
                }));
        // UpdateAccessKeyEnable
        APIS.put("updateAccessKeyEnable", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/user/[userName]/accesskey/[accessKeyId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("enable", null);
                    }
                }));
        // GetGroup
        APIS.put("getGroup", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/group/[groupName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // GetLoginProfile
        APIS.put("getLoginProfile", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/user/[userName]/loginProfile"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // GetPolicy
        APIS.put("getPolicy", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/policy/[policyName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>() {
                    {
                        put("policyType", null);
                    }
                }));
        // GetRole
        APIS.put("getRole", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/role/[roleName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // GetUser
        APIS.put("getUser", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/user/[userName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListAccessKey
        APIS.put("listAccessKey", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/user/[userName]/accesskey"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListGroup
        APIS.put("listGroup", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/group"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListGroupsForUser
        APIS.put("listGroupsForUser", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/user/[userName]/group"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListPoliciesForGroup
        APIS.put("listPoliciesForGroup", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/group/[groupName]/policy"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListPoliciesForRole
        APIS.put("listPoliciesForRole", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/role/[roleName]/policy"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListPoliciesForUser
        APIS.put("listPoliciesForUser", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/user/[userName]/policy"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListPolicy
        APIS.put("listPolicy", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/policy"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListRole
        APIS.put("listRole", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/role"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListUser
        APIS.put("listUser", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/user"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListUsersInGroup
        APIS.put("listUsersInGroup", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/group/[groupName]/user"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // RemoveUserFromGroup
        APIS.put("removeUserFromGroup", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/group/[groupName]/user/[userName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // UpdateGroup
        APIS.put("updateGroup", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/group/[groupName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // UpdateLoginProfile
        APIS.put("updateLoginProfile", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/user/[userName]/loginProfile"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // UpdateRole
        APIS.put("updateRole", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/role/[roleName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // UpdateUser
        APIS.put("updateUser", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/user/[userName]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // CreateVsAccount
        APIS.put("createVsAccount", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/vs/account"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // GetVsAccount
        APIS.put("getVsAccount", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/vs/account/[userId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // ListVsAccounts
        APIS.put("listVsAccount", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/vs/account/list"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        return APIS;
    }
}
