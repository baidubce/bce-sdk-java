package com.baidubce.services.dcc;

import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.volume.EphemeralDisk;
import com.baidubce.services.dcc.model.CreateDccInstanceRequest;
import com.baidubce.services.dcc.model.CreateDccRequest;
import com.baidubce.services.dcc.model.CreateDccResponse;
import com.baidubce.services.dcc.model.DccBindTagsRequest;
import com.baidubce.services.dcc.model.DccDetailRequest;
import com.baidubce.services.dcc.model.DccModel;
import com.baidubce.services.dcc.model.DccRenameRequest;
import com.baidubce.services.dcc.model.DccUnbindTagsRequest;
import com.baidubce.services.dcc.model.ListDccRequest;
import com.baidubce.services.dcc.model.ListDccResponse;
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

/**
 * test class for testing bcc service
 */
@RunWith(Enclosed.class)
public class DccClientTest {

    @Ignore
    public static class DccBase {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final Logger logger = LoggerFactory.getLogger(DccClientTest.class);
        protected final String ak = "your ak";
        protected final String sk = "your sk";
        protected static String endpoint = "dcc.api-sandbox.baidu.com";

        protected DccClientConfiguration config;

        public void setUp() {
            this.config = new DccClientConfiguration();
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

    public static class DccTest extends DccBase {

        protected DccClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new DccClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void createDcc() {
            CreateDccRequest request = new CreateDccRequest();
            request.setFlavor("C01");
            request.setCpuCount(35);
            request.setPurchaseCount(1);
            request.setZoneName("cn-bj-b");
            request.setType("calculation");
            CreateDccResponse response  = client.createDcc(request);
            System.out.println(response);
        }

        @Test
        public void dccBindTags() {
            DccBindTagsRequest request = new DccBindTagsRequest();
            request.setDccId("d-7wn97cAG");
            List<TagModel> tags = new ArrayList<TagModel>();
            TagModel tag1 = new TagModel().withTagKey("aaa").withTagValue("123");
            TagModel tag2 = new TagModel().withTagKey("bbb").withTagValue("789");
            tags.add(tag1);
            tags.add(tag2);
            request.setChangeTags(tags);
            client.dccBindTags(request);
        }

        @Test
        public void createDccInstance() {
            CreateDccInstanceRequest request = new CreateDccInstanceRequest();
            request.setCpuCount(1);
            request.setMemoryCapacityInGB(1);
            request.setEphemeralDisks(Lists.newArrayList(new EphemeralDisk().withSizeInGB(40).withStorageType("ssd")));
            request.setName("example");
            request.setImageId("m-0N8yoUGf");
            request.setNetworkCapacityInMbps(0);
            request.setDedicatedHostId("d-1xU2a1k1");
            request.setPurchaseCount(1);
            client.createDccInstance(request);
        }

        @Test
        public void createDccInstanceWithEncryption() {
            CreateDccInstanceRequest request = new CreateDccInstanceRequest();
            request.setCpuCount(1);
            request.setMemoryCapacityInGB(1);
            request.setEphemeralDisks(Lists.newArrayList(new EphemeralDisk().withSizeInGB(40).withStorageType("ssd")));
            request.setName("example1");
            request.setImageId("m-8rU0UtxY");
            request.setNetworkCapacityInMbps(0);
            request.setDedicatedHostId("d-dgAcUk0U");
            request.setPurchaseCount(1);
            request.setAdminPass("Aa123456");
            client.createDccInstanceWithEncryption(request);
        }

        public void dccUnbindTags() {
            DccUnbindTagsRequest request = new DccUnbindTagsRequest();
            request.setDccId("d-7wn97cAG");
            List<TagModel> tags = new ArrayList<TagModel>();
            TagModel tag1 = new TagModel().withTagKey("aaa").withTagValue("123");
            tags.add(tag1);
            request.setChangeTags(tags);
            client.dccUnbindTags(request);
        }

        @Test
        public void dccInstanceRename() {
            DccRenameRequest request = new DccRenameRequest();
            request.withInstanceId("d-7wn97cAG").withName("zhengyuhost");
            client.dccRename(request);
        }

        @Test
        public void dccDetail() {
            DccModel model = client.dccDetail(new DccDetailRequest().withDccId("d-JaTMyVOU"));
            System.out.println(model);
        }

        @Test
        public void dccList() {
            ListDccResponse response = client.dccList(new ListDccRequest());
            System.out.println(response);
        }
    }
}
