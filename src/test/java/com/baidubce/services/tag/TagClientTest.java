/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.tag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tag.model.CreateTagsRequest;
import com.baidubce.services.tag.model.DeleteTagsRequest;
import com.baidubce.services.tag.model.ListTagResourcesResponse;
import com.baidubce.services.tag.model.ListTagsRequest;
import com.baidubce.services.tag.model.ListTagsResponse;
import com.baidubce.services.tag.model.Tag;

/**
 * test class for testing tag service
 */
@RunWith(Enclosed.class)
public class TagClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagClientTest.class);

    @Ignore
    public static class TagBase {
        protected static final String ak = "62f0900586964cb2b76bcd221969eb0f";
        protected static final String sk = "8756f18f02f74519b8ecbb47d4e971c1";
        static String endpoint = "http://tag.sandbox.bcetest.baidu-int.com";
        protected TagClientConfiguration config;
        protected TagClient client;

        public void setUp() {
            this.config = new TagClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials(ak, sk));
            this.config.setProtocol(Protocol.HTTP);
            this.config.setEndpoint(endpoint);
            this.client = new TagClient(config);
        }

        public void tearDown() {
            // do something
            LOGGER.info("Base test tearDown");
        }

        void checkTags(List<Tag> tags) {
            for (Tag tag : tags) {
                ListTagsResponse response = client.listTags(
                        new ListTagsRequest().withTagKey(tag.getTagKey()).withTagValue(tag.getTagValue()));
                assertFalse("Check tag:" + tag.toString() + " Failed", response.getTags().isEmpty());
            }
        }

        void checkTagsNotExist(List<Tag> tags) {
            for (Tag tag : tags) {
                ListTagsResponse response = client.listTags(
                        new ListTagsRequest().withTagKey(tag.getTagKey()).withTagValue(tag.getTagValue()));
                assertTrue("Check tag:" + tag.toString() + " Failed", response.getTags().isEmpty());
            }
        }
    }

    public static class CreateTagsTest extends TagBase {

        @Before
        public void setUp() {
            super.setUp();
        }

        @After
        public void tearDown() {
            super.tearDown();
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_2"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_2"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_3").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_3").withTagValue("java_sdk_test_value_2"));
            client.deleteTags(tags);
        }

        @Test
        public void createSingleTag() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_1"));
            client.createTags(tags);
            checkTags(tags);
        }

        @Test
        public void createMultipleTags() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_2"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_2"));
            client.createTags(tags);
            checkTags(tags);
        }

        @Test
        public void createTagsAlreadyExist() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_3").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_3").withTagValue("java_sdk_test_value_2"));
            client.createTags(tags);
            checkTags(tags);
            client.createTags(tags);
            checkTags(tags);
        }

        @Test(expected = IllegalArgumentException.class)
        public void createTagsRequestNullException() {
            client.createTags((CreateTagsRequest) null);
        }

        @Test(expected = BceServiceException.class)
        public void createTagsTagNullException() {
            client.createTags((List<Tag>) null);
        }
    }

    public static class DeleteTagsTest extends TagBase {

        @Before
        public void setUp() {
            super.setUp();
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void deleteSingleTag() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_1"));
            client.createTags(tags);
            checkTags(tags);
            client.deleteTags(tags);
            checkTagsNotExist(tags);
        }

        @Test
        public void deleteMultiplyTags() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_2"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_2"));
            client.createTags(tags);
            checkTags(tags);
            client.deleteTags(tags);
            checkTagsNotExist(tags);
        }

        @Test
        public void deleteTagsNotExist() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_4").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_4").withTagValue("java_sdk_test_value_2"));
            client.deleteTags(tags);
        }

        @Test(expected = IllegalArgumentException.class)
        public void deleteTagsRequestNullException() {
            client.deleteTags((DeleteTagsRequest) null);
        }

        @Test(expected = BceServiceException.class)
        public void deleteTagsTagNullException() {
            client.deleteTags((List<Tag>) null);
        }
    }

    public static class ListTagsTest extends TagBase {

        @Before
        public void setUp() {
            super.setUp();
        }

        @After
        public void tearDown() {
            super.tearDown();
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_2"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_2"));
            client.deleteTags(tags);
        }

        @Test
        public void listAllTags() {
            ListTagsResponse resp = client.listTags();
            int tagsNum = resp.getTags().size();
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_2"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_2").withTagValue("java_sdk_test_value_2"));
            client.createTags(tags);
            resp = client.listTags();
            assertTrue("Tag number wrong after insert", resp.getTags().size() - tagsNum == 4);
        }

        @Test
        public void listTagsWithCondition() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_2"));
            client.createTags(tags);
            ListTagsResponse resp = client.listTags("java_sdk_test_key_1");
            assertEquals(2, resp.getTags().size());
            resp = client.listTags("java_sdk_test_key_1", "");
            assertEquals(1, resp.getTags().size());
            resp = client.listTags("java_sdk_test_key_1", "java_sdk_test_value_2");
            assertEquals(1, resp.getTags().size());
        }
    }

    public static class ListTagResourcesTest extends TagBase {

        @Before
        public void setUp() {
            super.setUp();
        }

        @After
        public void tearDown() {
            super.tearDown();
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_1"));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            client.deleteTags(tags);
        }

        @Test
        public void listTagResources() {
            List<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue(""));
            tags.add(new Tag().withTagKey("java_sdk_test_key_1").withTagValue("java_sdk_test_value_2"));
            client.createTags(tags);
            ListTagResourcesResponse resp = client.listTagResources("java_sdk_test_key1", "java_sdk_test_value_2");
            assertEquals("Tag resources size error", resp.getTagResources().size(), 0);
        }
    }
}
