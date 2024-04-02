package com.baidubce.services.bbc;

import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bbc.model.instance.BindBbcTagsRequest;
import com.baidubce.services.bbc.model.instance.CreateBbcImageRequest;
import com.baidubce.services.bbc.model.instance.CreateBbcImageResponse;
import com.baidubce.services.bbc.model.instance.CreateBbcInstanceRequest;
import com.baidubce.services.bbc.model.instance.CreateBbcInstanceResponse;
import com.baidubce.services.bbc.model.instance.GetBbcFlavorRaidResponse;
import com.baidubce.services.bbc.model.instance.GetBbcFlavorRequest;
import com.baidubce.services.bbc.model.instance.GetBbcFlavorResponse;
import com.baidubce.services.bbc.model.instance.GetBbcInstanceResponse;
import com.baidubce.services.bbc.model.instance.GetInstanceVpcRequest;
import com.baidubce.services.bbc.model.instance.GetInstanceVpcResponse;
import com.baidubce.services.bbc.model.instance.ListBbcFlavorResponse;
import com.baidubce.services.bbc.model.instance.ListBbcFlavorsRequest;
import com.baidubce.services.bbc.model.instance.ListBbcInstancesRequest;
import com.baidubce.services.bbc.model.instance.ListBbcInstancesResponse;
import com.baidubce.services.bbc.model.instance.ListOperationLogRequest;
import com.baidubce.services.bbc.model.instance.ListOperationLogResponse;
import com.baidubce.services.bbc.model.instance.RebuildBbcInstanceRequest;
import com.baidubce.services.bbc.model.instance.RenameBbcInstanceRequest;
import com.baidubce.services.bcc.BccClientTest;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.image.GetImageRequest;
import com.baidubce.services.bcc.model.image.GetImageResponse;
import com.baidubce.services.bcc.model.image.ListImagesRequest;
import com.baidubce.services.bcc.model.image.ListImagesResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstancePasswordRequest;
import com.baidubce.services.bcc.model.instance.RebootInstanceRequest;
import com.baidubce.services.bcc.model.instance.ReleaseInstanceRequest;
import com.baidubce.services.bcc.model.instance.StartInstanceRequest;
import com.baidubce.services.bcc.model.instance.StopInstanceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


/**
 * Created by fulinhua on 2019-02-18
 */
@RunWith(Enclosed.class)

public class BbcClientTest {

    @Ignore
    public static class BbcBase {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final Logger logger = LoggerFactory.getLogger(BccClientTest.class);
        protected final String ak = "your ak";
        protected final String sk = "your sk";
        protected static String endpoint = "nmg02-bce-test10.nmg02.baidu.com:8680";

        //   protected static String endpoint = "bbc.bce-api.baidu.com";
        protected BbcClientConfiguration config;
        String instanceId = "i-wH0x5OQ6";
        String flavorId = "BBC-I2-01";
        String imageId = "m-WVCLNuLY";
        String adminPass = "abc12345@";
        String deletedInstanceId = "i-eDWlSedM";
        TagModel tagModel = new TagModel().withTagKey("abc").withTagValue("def");
        List<TagModel> changeTags = new ArrayList<TagModel>();

        public void setUp() {
            this.config = new BbcClientConfiguration();
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
     * Test case about instance begin
     */
    public static class InstanceTest extends BbcBase {
        protected BbcClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BbcClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void bindInstanceToTags() {
            changeTags.add(tagModel);
            client.bindInstanceTag(new BindBbcTagsRequest().withInstanceId(instanceId)
                    .withChangeTags(changeTags));
        }

        @Test
        public void unbindInstanceToTags() {
            changeTags.add(tagModel);
            client.unBindInstanceTag(new BindBbcTagsRequest().withInstanceId(instanceId)
                    .withChangeTags(changeTags));
        }

        @Test
        public void changePassInstance() {
            client.modifyBbcPassword(
                    new ModifyInstancePasswordRequest().withInstanceId(instanceId).withAdminPass("1qaz@WSX"));
        }

        @Test
        public void getInstanceVpcsubnet() {
            GetInstanceVpcResponse response = client.getInstanceVpcsubnet(
                    new GetInstanceVpcRequest().withBbcIds(Lists.newArrayList(instanceId)));
            assertThat(response.getNetworkInfo(), notNullValue());
            toJsonPrettyString("getVpcNet", response);
        }

        // 新补充sdk测试用例分割线--------

        @Test
        public void listInstance() {
            ListBbcInstancesResponse response = client.listBbcInstances(new ListBbcInstancesRequest());
            assertThat(response.getInstances(), notNullValue());
            toJsonPrettyString("listInstance", response);
        }

        @Test
        public void getInstance() {
            GetBbcInstanceResponse response = client.getInstance(new GetInstanceRequest().withInstanceId(instanceId));
            assertThat(response, notNullValue());
            toJsonPrettyString("getInstance", response);
        }

        //        @Test
        public void startInstance() {
            client.startInstance(new StartInstanceRequest().withInstanceId(instanceId));
        }

        //        @Test
        public void stopInstance() {
            client.stopInstance(new StopInstanceRequest().withInstanceId(instanceId));
        }

        //       @Test
        public void rebootInstance() {
            client.rebootInstance(new RebootInstanceRequest().withInstanceId(instanceId));
        }

        //        @Test
        public void renameInstance() {
            client.renameBbcInstance(
                    new RenameBbcInstanceRequest()
                            .withInstanceId(instanceId)
                            .withName("helloword")
            );
        }

        //        @Test
        public void modifyPassword() {
            client.modifyBbcPassword(
                    new ModifyInstancePasswordRequest()
                            .withInstanceId(instanceId)
                            .withAdminPass(adminPass)
            );
        }

        @Test
        public void listFlavors() {
            ListBbcFlavorResponse bbcFlavorResponse = client.listBbcFlavors(new ListBbcFlavorsRequest());
            assertThat(bbcFlavorResponse.getFlavors(), notNullValue());
            toJsonPrettyString("listFlavors", bbcFlavorResponse);
        }

        @Test
        public void getFlavor() {
            GetBbcFlavorResponse bbcFlavorResponse = client.getBbcFlavor(
                    new GetBbcFlavorRequest().withFlavorId(flavorId));
            assertThat(bbcFlavorResponse, notNullValue());
            toJsonPrettyString("getFlavor", bbcFlavorResponse);
        }

        @Test
        public void getFlavorRaid() {
            GetBbcFlavorRaidResponse bbcFlavorResponse = client.getBbcFlavorRaid(
                    new GetBbcFlavorRequest().withFlavorId("BBC-TEST1-01"));
            assertThat(bbcFlavorResponse, notNullValue());
            toJsonPrettyString("getFlavorRaid", bbcFlavorResponse);
        }

        @Test
        public void listBbcImages() {
            ListImagesResponse response = client.listImages(new ListImagesRequest());
            assertThat(response, notNullValue());
            toJsonPrettyString("listBbcImages", response);
        }

        @Test
        public void getBbcImage() {
            GetImageResponse response = client.getImage(new GetImageRequest().withImageId(imageId));
            assertThat(response, notNullValue());
            toJsonPrettyString("getImage", response);
        }

        @Test
        public void listOperationLog() {
            ListOperationLogResponse response = client.listBbcOperationLog(new ListOperationLogRequest());
            assertThat(response, notNullValue());
            toJsonPrettyString("listLog", response);
        }

        @Test
        public void rebuildBbcInstance() {
            client.rebuildBbcInstance(new RebuildBbcInstanceRequest()
                    .withInstanceId(instanceId).withAdminPass(adminPass).withImageId(imageId));
        }

        @Test
        public void createImage() {
            CreateBbcImageResponse response = client.createBbcImage(new CreateBbcImageRequest()
                    .withInstanceId(instanceId).withImageName("abc_def_0"));
            assertThat(response, notNullValue());
            toJsonPrettyString("createImage", response);
        }

        @Test
        public void releaseBbcInstance() {
            client.releaseBbcInstance(new ReleaseInstanceRequest().withInstanceId(deletedInstanceId));
        }

        @Test
        public void createBbcInstance() {
            CreateBbcInstanceRequest request = new CreateBbcInstanceRequest();
            request.setName("test2");
            request.setFlavorId("BBC-TEST1-01");
            request.setImageId("m-25WgHgho");
            request.setRaidId("raid-F1n37Kxq");
            request.setRootDiskSizeInGb(40);
            request.setZoneName("cn-bj-b");
            CreateBbcInstanceResponse response = client.createInstance(request);
            assertThat(response, notNullValue());
            toJsonPrettyString("createInstance", response);
        }
    }

}