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
import com.baidubce.services.iam.model.GetPolicyResponse;
import com.baidubce.services.iam.model.GetRoleResponse;
import com.baidubce.services.iam.model.GetUserResponse;
import com.baidubce.services.iam.model.GetVsAccountResponse;
import com.baidubce.services.iam.model.ListAccessKeyResponse;
import com.baidubce.services.iam.model.ListGroupResponse;
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
import com.baidubce.services.iam.model.UpdateRoleRequest;
import com.baidubce.services.iam.model.UpdateRoleResponse;
import com.baidubce.services.iam.model.UpdateUserRequest;
import com.baidubce.services.iam.model.UpdateUserResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.common.BceRegion;

import java.util.HashSet;
import java.util.Set;

/**
 * unit case
 */
public class IamTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IamTest.class);
    private static final String AK = "your ak";
    private static final String SK = "your sk";
    private IamClient client = new IamClient(AK, SK, BceRegion.DEFAULT);
    private String subuserName = "test_user";;
    private String groupName = "test_group";
    private String policyName = "test_policy";
    private String defaultPolicyType = "Custom";
    private String desc = "desc";
    private String roleName = "test_role";
    @Before
    public void setUp() {
        // do something
    }

    @After
    public void tearDown() {
        // do something
    }

    @Test
    public void addUserToGroupAndRemoveUserFromGroupTest() {
        // test api addUserToGroup
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(groupName);
        CreateGroupResponse createGroupResponse = client.createGroup(createGroupRequest);

        client.addUserToGroup(subuserName, groupName);
        ListUsersInGroupResponse response = client.listUsersInGroup(groupName);
        GetUserResponse targetUserResponse = client.getUser(subuserName);
        Set<String> ids = new HashSet();
        for (ListUsersInGroupResponse.User user : response.getUsers()) {
            ids.add(user.getId());
        }
        Assert.assertTrue(ids.contains(targetUserResponse.getId()));
        client.removeUserFromGroup(subuserName, groupName);
        client.deleteUser(subuserName);
        client.deleteGroup(groupName);
    }

    @Test
    public void attachAndDetachPolicyToGroupTest() {
        // test api attachPolicyToGroup
        String newPolicyName = "test_policy";
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(newPolicyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        Assert.assertEquals(createPolicyResponse.getName(), newPolicyName);
        Assert.assertEquals(createPolicyResponse.getDescription(), desc);
        client.attachPolicyToGroup(groupName, newPolicyName, defaultPolicyType);
        client.detachPolicyFromGroup(groupName, newPolicyName, defaultPolicyType);
        client.deletePolicy(newPolicyName);
    }

    @Test
    public void attachAndDetachPolicyToRole() {
        // test api attachPolicyToRole
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(policyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        String testRoleDescription = "test_role_description";
        String testAssumeRolePolicyDocument = "{\"version\":\"v1\",\"accessControlList\":[{\"service\":\"bce:iam\"," +
                "\"permission\":[\"AssumeRole\"],\"region\":\"*\",\"grantee\":[{\"id\":\"grantee-id\"}],\"effect\":\"Allow\"}]}";
        CreateRoleRequest createRoleRequest = new CreateRoleRequest();
        createRoleRequest.setName(roleName);
        createRoleRequest.setDescription(testRoleDescription);
        createRoleRequest.setAssumeRolePolicyDocument(testAssumeRolePolicyDocument);
        CreateRoleResponse createRoleResponse = client.createRole(createRoleRequest);
        client.attachPolicyToRole(roleName, policyName, defaultPolicyType);
        client.detachPolicyFromRole(roleName, policyName, defaultPolicyType);
        client.deleteRole(roleName);
        client.deletePolicy(policyName);
    }

    @Test
    public void attachAndDetachPolicyToUserTest() {
        // test api attachPolicyToUser
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(policyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        client.attachPolicyToUser(subuserName, policyName, defaultPolicyType);
        client.detachPolicyFromUser(subuserName, policyName, defaultPolicyType);
        client.deleteUser(subuserName);
        client.deletePolicy(policyName);
    }

    @Test
    public void createAndDeleteAccessKeyTest() throws InterruptedException {
        // test api createAccessKey
        CreateAccessKeyResponse response = client.createAccessKey(subuserName);
        Assert.assertEquals(response.isEnabled(), true);
        Thread.sleep(2000);
        client.deleteAccessKey(subuserName, response.getId());
        Assert.assertFalse(response.getSecret().isEmpty());
    }

    @Test
    public void createAndDeleteGroupTest() {
        // test api createGroup
        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(groupName);
        CreateGroupResponse response = client.createGroup(createGroupRequest);
        Assert.assertEquals(response.getName(), groupName);
        client.deleteGroup(groupName);
    }

    @Test
    public void createAndDeletePolicyTest() {
        // test api createPolicy
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\"," +
                "\"resource\":[\"*\"],\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(policyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse response = client.createPolicy(createPolicyRequest);
        Assert.assertEquals(response.getName(), policyName);
        client.deletePolicy(policyName);
    }

    @Test
    public void createAndDeleteRoleTest() {
        // test api createRole
        String testRoleDescription = "test_role_description";
        String testAssumeRolePolicyDocument = "{\"version\":\"v1\",\"accessControlList\":[{\"service\":\"bce:iam\"," +
                "\"permission\":[\"AssumeRole\"],\"region\":\"*\",\"grantee\":[{\"id\":\"grantee-id\"}],\"effect\":" +
                "\"Allow\"}]}";
        CreateRoleRequest createRoleRequest = new CreateRoleRequest();
        createRoleRequest.setName(roleName);
        createRoleRequest.setDescription(testRoleDescription);
        createRoleRequest.setAssumeRolePolicyDocument(testAssumeRolePolicyDocument);
        CreateRoleResponse createRoleResponse = client.createRole(createRoleRequest);
        Assert.assertEquals(createRoleResponse.getName(), roleName);
        Assert.assertEquals(createRoleResponse.getDescription(), testRoleDescription);
        client.deleteRole(roleName);
    }

    @Test
    public void createAndDeleteUserTest() {
        // test api createUser
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Assert.assertEquals(response.getName(), subuserName);
        Assert.assertEquals(response.getDescription(), desc);
        client.deleteUser(subuserName);
    }

    @Test
    public void disableAccessKeyTest() throws InterruptedException {
        // test api disableAccessKey
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Thread.sleep(2000);
        CreateAccessKeyResponse createAccessKeyResponse = client.createAccessKey(subuserName);
        DisableAccessKeyResponse response1 =
                client.disableAccessKey(subuserName, createAccessKeyResponse.getId());
        Assert.assertFalse(response1.isEnabled());
        client.deleteAccessKey(subuserName, createAccessKeyResponse.getId());
        client.deleteUser(subuserName);
    }

    @Test
    public void enableAccessKeyTest() throws InterruptedException {
        // test api disableAccessKey
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Thread.sleep(2000);
        CreateAccessKeyResponse createAccessKeyResponse = client.createAccessKey(subuserName);
        EnableAccessKeyResponse response1 =
                client.enableAccessKey(subuserName, createAccessKeyResponse.getId());
        Assert.assertTrue(response1.isEnabled());
        client.deleteAccessKey(subuserName, createAccessKeyResponse.getId());
        client.deleteUser(subuserName);
    }

    @Test
    public void getGroupTest() {
        // test api getGroup
        client.deleteGroup(groupName);
        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(groupName);
        client.createGroup(createGroupRequest);
        GetGroupResponse getGroupResponse = client.getGroup(groupName);
        Assert.assertEquals(getGroupResponse.getName(), groupName);
        client.deleteGroup(groupName);
    }

    @Test
    public void getLoginProfileTest() {
        // test api getLoginProfile
        client.getLoginProfile(subuserName);
    }

    @Test
    public void getPolicyTest() {
        // test api getPolicy
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(policyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        GetPolicyResponse response = client.getPolicy(policyName, "Custom");
        Assert.assertEquals(response.getName(), policyName);
        Assert.assertEquals(response.getType(), defaultPolicyType);
        client.deletePolicy(policyName);
    }

    @Test
    public void getRoleTest() {
        // test api getRole
        String testRoleDescription = "test_role_description";
        String testAssumeRolePolicyDocument = "{\"version\":\"v1\",\"accessControlList\":[{\"service\":\"bce:iam\"," +
                "\"permission\":[\"AssumeRole\"],\"region\":\"*\",\"grantee\":[{\"id\":\"grantee-id\"}],\"effect\":" +
                "\"Allow\"}]}";
        CreateRoleRequest createRoleRequest = new CreateRoleRequest();
        createRoleRequest.setName(roleName);
        createRoleRequest.setDescription(testRoleDescription);
        createRoleRequest.setAssumeRolePolicyDocument(testAssumeRolePolicyDocument);
        client.createRole(createRoleRequest);
        GetRoleResponse getRoleResponse = client.getRole(roleName);
        Assert.assertEquals(getRoleResponse.getName(), roleName);
        Assert.assertEquals(getRoleResponse.getDescription(), testRoleDescription);
        client.deleteRole(roleName);
    }

    @Test
    public void getAndDeleteUserTest() throws InterruptedException {
        // test api getUser
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName(subuserName);
        createUserRequest.setDescription(desc);
        client.createUser(createUserRequest);
        GetUserResponse getUserResponse = client.getUser(subuserName);
        Assert.assertEquals(getUserResponse.getName(), subuserName);
        Assert.assertEquals(getUserResponse.getDescription(), desc);
        Thread.sleep(2000);
        client.deleteUser(subuserName);
    }

    @Test
    public void listAccessKeyTest() throws InterruptedException {
        // test api listAccessKey
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Thread.sleep(2000);
        CreateAccessKeyResponse createAccessKeyResponse = client.createAccessKey(subuserName);
        Assert.assertEquals(response.isEnabled(), true);
        Thread.sleep(2000);
        ListAccessKeyResponse listAccessKeyResponse = client.listAccessKey(subuserName);
        Assert.assertFalse(listAccessKeyResponse.getAccessKeys().isEmpty());
        client.deleteAccessKey(subuserName, createAccessKeyResponse.getId());
        client.deleteUser(subuserName);
    }

    @Test
    public void listGroupTest() {
        // test api listGroup
        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(groupName);
        CreateGroupResponse response = client.createGroup(createGroupRequest);
        ListGroupResponse listGroupResponse = client.listGroup();
        Assert.assertNotNull(listGroupResponse.getGroups());
        client.deleteGroup(groupName);
    }

    @Test
    public void listGroupsForUserTest() {
        // test api listGroupsForUser
        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(groupName);
        CreateGroupResponse response = client.createGroup(createGroupRequest);
        Assert.assertEquals(response.getName(), groupName);

        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);

        client.addUserToGroup(subuserName, groupName);
        ListUsersInGroupResponse listUsersInGroupResponse = client.listUsersInGroup(groupName);
        System.out.println(listUsersInGroupResponse);
        for (ListUsersInGroupResponse.User user : listUsersInGroupResponse.getUsers()) {
            if (user.getName().equals(subuserName)) {
                break;
            }
        }
        client.removeUserFromGroup(subuserName, groupName);
        client.deleteUser(subuserName);
        client.deleteGroup(groupName);
    }

    @Test
    public void listPoliciesForGroupTest() {
        // test api listPoliciesForGroup
        String newPolicyName = "test_policy";
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(newPolicyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        Assert.assertEquals(createPolicyResponse.getName(), newPolicyName);
        Assert.assertEquals(createPolicyResponse.getDescription(), desc);
        client.attachPolicyToGroup(groupName, newPolicyName, defaultPolicyType);
        ListPoliciesForGroupResponse listPoliciesForGroupResponse = client.listPoliciesForGroup(groupName);
        Assert.assertFalse(listPoliciesForGroupResponse.getPolicies().isEmpty());
        client.detachPolicyFromGroup(groupName, newPolicyName, defaultPolicyType);
        client.deletePolicy(newPolicyName);
    }

    @Test
    public void listPoliciesForRoleTest() {
        // test api listPoliciesForRole
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(policyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        String testRoleDescription = "test_role_description";
        String testAssumeRolePolicyDocument = "{\"version\":\"v1\",\"accessControlList\":[{\"service\":\"bce:iam\"," +
                "\"permission\":[\"AssumeRole\"],\"region\":\"*\",\"grantee\":[{\"id\":\"grantee-id\"}],\"effect\":" +
                "\"Allow\"}]}";
        CreateRoleRequest createRoleRequest = new CreateRoleRequest();
        createRoleRequest.setName(roleName);
        createRoleRequest.setDescription(testRoleDescription);
        createRoleRequest.setAssumeRolePolicyDocument(testAssumeRolePolicyDocument);
        CreateRoleResponse createRoleResponse = client.createRole(createRoleRequest);
        client.attachPolicyToRole(roleName, policyName, defaultPolicyType);
        ListPoliciesForRoleResponse listPoliciesForRoleResponse = client.listPoliciesForRole(roleName);
        Assert.assertFalse(listPoliciesForRoleResponse.getPolicies().isEmpty());
        client.detachPolicyFromRole(roleName, policyName, defaultPolicyType);
        client.deletePolicy(policyName);
        client.deleteRole(roleName);
    }

    @Test
    public void listPoliciesForUserTest() {
        // test api listPoliciesForUser
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(policyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        String userDesc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(userDesc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Assert.assertEquals(response.getName(), subuserName);
        Assert.assertEquals(response.getDescription(), userDesc);
        client.attachPolicyToUser(subuserName, policyName, defaultPolicyType);
        ListPoliciesForUserResponse listPoliciesForUserResponse = client.listPoliciesForUser(subuserName);
        Assert.assertFalse(listPoliciesForUserResponse.getPolicies().isEmpty());
        client.detachPolicyFromUser(subuserName, policyName, defaultPolicyType);
        client.deletePolicy(policyName);
        client.deleteUser(subuserName);
    }

    @Test
    public void listPolicyTest() {
        // test api listPolicy
        String desc = "description";
        String document = "{\"accessControlList\": [{\"region\":\"bj\",\"service\":\"bcc\",\"resource\":[\"*\"]," +
                "\"permission\":[\"*\"],\"effect\":\"Allow\"}]}";
        CreatePolicyRequest createPolicyRequest = new CreatePolicyRequest();
        createPolicyRequest.setName(policyName);
        createPolicyRequest.setDescription(desc);
        createPolicyRequest.setDocument(document);
        CreatePolicyResponse createPolicyResponse = client.createPolicy(createPolicyRequest);
        ListPolicyResponse response = client.listPolicy(defaultPolicyType);
        Assert.assertFalse(response.getPolicies().isEmpty());
        client.deletePolicy(policyName);
    }

    @Test
    public void listRoleTest() {
        // test api listRole
        String testRoleDescription = "test_role_description";
        String testAssumeRolePolicyDocument = "{\"version\":\"v1\",\"accessControlList\":[{\"service\":\"bce:iam\"," +
                "\"permission\":[\"AssumeRole\"],\"region\":\"*\",\"grantee\":[{\"id\":\"grantee-id\"}],\"effect\":" +
                "\"Allow\"}]}";
        CreateRoleRequest createRoleRequest = new CreateRoleRequest();
        createRoleRequest.setName(roleName);
        createRoleRequest.setDescription(testRoleDescription);
        createRoleRequest.setAssumeRolePolicyDocument(testAssumeRolePolicyDocument);
        CreateRoleResponse createRoleResponse = client.createRole(createRoleRequest);
        ListRoleResponse roleResponse = client.listRole();
        Assert.assertNotNull(roleResponse.getRoles());
        client.deleteRole(roleName);
    }

    @Test
    public void listUserTest() {
        // test api listUser
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Assert.assertEquals(response.getName(), subuserName);
        Assert.assertEquals(response.getDescription(), desc);
        ListUserResponse listUserResponse = client.listUser();
        Assert.assertFalse(listUserResponse.getUsers().isEmpty());
        client.deleteUser(subuserName);
    }

    @Test
    public void listUsersInGroupTest() {
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Assert.assertEquals(response.getName(), subuserName);
        Assert.assertEquals(response.getDescription(), desc);
        ListUserResponse listUserResponse = client.listUser();
        Assert.assertFalse(listUserResponse.getUsers().isEmpty());
        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(groupName);
        CreateGroupResponse groupResponse = client.createGroup(createGroupRequest);
        Assert.assertEquals(groupResponse.getName(), groupName);
        client.addUserToGroup(subuserName, groupName);
        ListUsersInGroupResponse listUsersInGroupResponse = client.listUsersInGroup(groupName);
        Assert.assertFalse(listUsersInGroupResponse.getUsers().isEmpty());
        client.removeUserFromGroup(subuserName, groupName);
        client.deleteGroup(groupName);
        client.deleteUser(subuserName);
    }

    @Test
    public void updateGroupTest() {
        // test api updateGroup
        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(groupName);
        CreateGroupResponse response = client.createGroup(createGroupRequest);
        Assert.assertEquals(response.getName(), groupName);
        String newGroupDesc = "new desc";
        UpdateGroupRequest updateGroupRequest = new UpdateGroupRequest();
        updateGroupRequest.setName(groupName);
        updateGroupRequest.setDescription(newGroupDesc);
        UpdateGroupResponse updateGroupResponse = client.updateGroup(groupName, updateGroupRequest);
        Assert.assertEquals(updateGroupResponse.getDescription(), newGroupDesc);
        client.deleteGroup(groupName);
    }

    // 需要在控制台开启
    @Test
    public void updateLoginProfileTest() {
        // test api updateLoginProfile
    }

    @Test
    public void updateRoleTest() {
        // test api updateRole
        String testRoleDescription = "test_role_description";
        String testAssumeRolePolicyDocument = "{\"version\":\"v1\",\"accessControlList\":[{\"service\":\"bce:iam\"," +
                "\"permission\":[\"AssumeRole\"],\"region\":\"*\",\"grantee\":[{\"id\":\"grantee-id\"}],\"effect\":" +
                "\"Allow\"}]}";
        CreateRoleRequest createRoleRequest = new CreateRoleRequest();
        createRoleRequest.setName(roleName);
        createRoleRequest.setDescription(testRoleDescription);
        createRoleRequest.setAssumeRolePolicyDocument(testAssumeRolePolicyDocument);
        CreateRoleResponse createRoleResponse = client.createRole(createRoleRequest);
        Assert.assertEquals(createRoleResponse.getName(), roleName);
        Assert.assertEquals(createRoleResponse.getDescription(), testRoleDescription);
        UpdateRoleRequest updateRoleRequest = new UpdateRoleRequest();
        String newTestRoleDescription = "new_test_role_description";
        updateRoleRequest.setDescription(newTestRoleDescription);
        updateRoleRequest.setName(roleName);
        UpdateRoleResponse updateRoleResponse = client.updateRole(roleName, updateRoleRequest);
        Assert.assertEquals(updateRoleResponse.getDescription(), newTestRoleDescription);
        client.deleteRole(roleName);
    }

    @Test
    public void updateUserTest() {
        // test api updateUser
        String desc = "test sdk user";
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setDescription(desc);
        createUserRequest.setName(subuserName);
        CreateUserResponse response = client.createUser(createUserRequest);
        Assert.assertEquals(response.getName(), subuserName);
        Assert.assertEquals(response.getDescription(), desc);
        String newUserDesc = "new test sdk user";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setDescription(newUserDesc);
        updateUserRequest.setName(subuserName);
        UpdateUserResponse updateUserResponse = client.updateUser(subuserName, updateUserRequest);
        Assert.assertEquals(updateUserResponse.getDescription(), newUserDesc);
        client.deleteUser(subuserName);
    }

    @Test
    public void createVsAccountTest() {
        CreateVsAccountRequest request = new CreateVsAccountRequest();
        String mobilePhone = "mobilePhone";
        request.setAccountType("1");
        request.setCompany("test_company");
        request.setEmail("test@mail.com");
        request.setMobilePhone(mobilePhone);
        CreateVsAccountResponse response = client.createVsAccount(request);
        Assert.assertEquals(response.getMobilePhone(), mobilePhone);
    }

    @Test
    public void getVsAccountTest() {
        CreateVsAccountRequest request = new CreateVsAccountRequest();
        request.setAccountType("1");
        request.setCompany("test_company");
        request.setEmail("test@mail.com");
        request.setMobilePhone("15651656788");
        CreateVsAccountResponse response = client.createVsAccount(request);
        GetVsAccountResponse vsAccount = client.getVsAccount(response.getUserId());
        Assert.assertNotNull(vsAccount);
    }

    @Test
    public void listVsAccountsTest() {
        Integer pageNo = 1;
        Integer pageSize = 10;
        ListVsAccountsRequest request = new ListVsAccountsRequest();
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        ListVsAccountsResponse response = client.listVsAccounts(request);
        Assert.assertNotEquals(response.getTotalSize(), Long.valueOf(0));
    }

}
