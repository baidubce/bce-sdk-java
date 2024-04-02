package com.baidubce.services.userservice;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.userservice.model.AddAuthRequest;
import com.baidubce.services.userservice.model.BindInstanceRequest;
import com.baidubce.services.userservice.model.CreateUserServiceRequest;
import com.baidubce.services.userservice.model.EditAuthRequest;
import com.baidubce.services.userservice.model.RemoveAuthRequest;
import com.baidubce.services.userservice.model.UpdateUserServiceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenchangquan
 * @date 2023/11/24
 */
public class UserServiceClientTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceClientTest.class);
    private static final String ak = "3251d60a16f94c839f7aa4b87ed4913b";
    private static final String sk = "21d8c3cdfe1242148e29465fca734e1f";
    private UserserviceClient userserviceClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("blb.bj.qasandbox.baidu-int.com");
        userserviceClient = new UserserviceClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createUserServiceTest() {
        CreateUserServiceRequest createUserServiceRequest = new CreateUserServiceRequest();
        createUserServiceRequest.setName("name");
        createUserServiceRequest.setDescription("desc");
        createUserServiceRequest.setServiceName("testService");
        createUserServiceRequest.setInstanceId("lb-b69cd42f");
        List<CreateUserServiceRequest.Auth> authList = new ArrayList<CreateUserServiceRequest.Auth>();
        CreateUserServiceRequest.Auth auth = new CreateUserServiceRequest.Auth();
        auth.setAuth("allow");
        auth.setUid("*");
        authList.add(auth);
        createUserServiceRequest.setAuthList(authList);
        toJsonPrettyString("create userService result",
                userserviceClient.createUserService(createUserServiceRequest, ""));
    }

    @Test
    public void updateUserServiceTest() {
        UpdateUserServiceRequest updateUserServiceRequest = new UpdateUserServiceRequest();
        updateUserServiceRequest.setName("updateName1");
        updateUserServiceRequest.setDescription("updateDesc");
        userserviceClient.updateUserService("testService.uservice-9fbf1146.beijing.baidubce.com",
                updateUserServiceRequest, null);
    }

    @Test
    public void unbindInstanceTest() {
        userserviceClient.unbindInstance("testService.uservice-9fbf1146.beijing.baidubce.com",
                null);
    }

    @Test
    public void bindInstanceTest() {
        BindInstanceRequest bindInstanceRequest = new BindInstanceRequest();
        bindInstanceRequest.setInstanceId("lb-b69cd42f");
        userserviceClient.bindInstance("testService.uservice-9fbf1146.beijing.baidubce.com",
                bindInstanceRequest, null);
    }

    @Test
    public void addAuthTest() {
        AddAuthRequest addAuthRequest = new AddAuthRequest();
        List<AddAuthRequest.Auth>  authList = new ArrayList<AddAuthRequest.Auth>();
        AddAuthRequest.Auth auth = new AddAuthRequest.Auth();
        auth.setAuth("allow");
        auth.setUid("7cc5aff841ff4b648028d80b84e1917e");
        authList.add(auth);
        addAuthRequest.setAuthList(authList);
        userserviceClient.addAuth("testService.uservice-9fbf1146.beijing.baidubce.com",
                addAuthRequest, null);
    }

    @Test
    public void editAuthTest() {
        EditAuthRequest editAuthRequest = new EditAuthRequest();
        List<EditAuthRequest.Auth>  authList = new ArrayList<EditAuthRequest.Auth>();
        EditAuthRequest.Auth auth = new EditAuthRequest.Auth();
        auth.setAuth("deny");
        auth.setUid("7cc5aff841ff4b648028d80b84e1917e");
        authList.add(auth);
        editAuthRequest.setAuthList(authList);
        userserviceClient.editAuth("testService.uservice-9fbf1146.beijing.baidubce.com",
                editAuthRequest, null);
    }

    @Test
    public void removeAuthTest() {
        RemoveAuthRequest removeAuthRequest = new RemoveAuthRequest();
        List<String> uidList = new ArrayList<String>();
        uidList.add("7cc5aff841ff4b648028d80b84e1917e");
        removeAuthRequest.setUidList(uidList);
        userserviceClient.removeAuth("testService.uservice-9fbf1146.beijing.baidubce.com",
                removeAuthRequest, null);
    }

    @Test
    public void listUserServiceTest() {
        toJsonPrettyString("list userService result", userserviceClient.listUserService(null, null));
    }

    @Test
    public void getUserServiceTest() {
        toJsonPrettyString("get userService result",
                userserviceClient.getUserService("testService.uservice-9fbf1146.beijing.baidubce.com"));;
    }

    @Test
    public void deleteUserServiceTest() {
        userserviceClient.deleteUserService("testService.uservice-1d59d339.beijing.baidubce.com", "");
    }


}
