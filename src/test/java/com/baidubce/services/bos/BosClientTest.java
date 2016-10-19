/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bos;

import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.TestUtils;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.RestartableFileInputStream;
import com.baidubce.model.User;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.BucketSummary;
import com.baidubce.services.bos.model.CannedAccessControlList;
import com.baidubce.services.bos.model.CompleteMultipartUploadRequest;
import com.baidubce.services.bos.model.CompleteMultipartUploadResponse;
import com.baidubce.services.bos.model.CopyObjectRequest;
import com.baidubce.services.bos.model.CopyObjectResponse;
import com.baidubce.services.bos.model.CreateBucketRequest;
import com.baidubce.services.bos.model.DeleteBucketRequest;
import com.baidubce.services.bos.model.DoesBucketExistRequest;
import com.baidubce.services.bos.model.GeneratePresignedUrlRequest;
import com.baidubce.services.bos.model.GetBucketAclRequest;
import com.baidubce.services.bos.model.GetBucketAclResponse;
import com.baidubce.services.bos.model.GetObjectRequest;
import com.baidubce.services.bos.model.Grant;
import com.baidubce.services.bos.model.Grantee;
import com.baidubce.services.bos.model.InitiateMultipartUploadRequest;
import com.baidubce.services.bos.model.InitiateMultipartUploadResponse;
import com.baidubce.services.bos.model.ListBucketsRequest;
import com.baidubce.services.bos.model.ListBucketsResponse;
import com.baidubce.services.bos.model.ListMultipartUploadsRequest;
import com.baidubce.services.bos.model.ListMultipartUploadsResponse;
import com.baidubce.services.bos.model.ListObjectsRequest;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.ListPartsRequest;
import com.baidubce.services.bos.model.ListPartsResponse;
import com.baidubce.services.bos.model.MultipartUploadSummary;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PartETag;
import com.baidubce.services.bos.model.PartSummary;
import com.baidubce.services.bos.model.Permission;
import com.baidubce.services.bos.model.PutObjectRequest;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.services.bos.model.ResponseHeaderOverrides;
import com.baidubce.services.bos.model.SetBucketAclRequest;
import com.baidubce.services.bos.model.UploadPartRequest;
import com.baidubce.services.bos.model.UploadPartResponse;
import com.baidubce.util.HashUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matchers;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Enclosed.class)
public class BosClientTest {

    @Ignore
    public static class Base {
        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final String BUCKET_PREFIX = "ut" + Long.toHexString(new Random().nextLong()) + "-";
        //        protected static final String ENDPOINT = "http://10.26.208.32:8918";
        protected static String ENDPOINT = "http://10.105.97.15";

        protected BosClient client;
        protected User owner;
        protected Grantee grantee;
        protected Grantee anonymous;
        protected String bucketName;
        protected BosClientConfiguration config;
        protected Date createTime;

        @Before
        public void setUp() {
            this.bucketName = BUCKET_PREFIX + Long.toHexString(new Random().nextLong());
            this.config = new BosClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials("b5db8b624200403a933b0f5a527368ae",
                    "3a02670d81c94d209d728bfc4f445641"));
            this.config.setEndpoint(ENDPOINT);
            this.client = new BosClient(this.config);
            this.owner = new User("ef820bcc574e4dd09c47c0427f40b24b", "PASSPORT:1800208914");
            this.grantee = new Grantee("ef820bcc574e4dd09c47c0427f40b24b");
            this.anonymous = new Grantee("*");
            this.client.createBucket(this.bucketName);
            this.createTime = new Date();
        }

        @After
        public void tearDown() {
            this.client = new BosClient(this.config);
            for (BucketSummary bucket : this.client.listBuckets().getBuckets()) {
                String bucketName = bucket.getName();
                if (bucketName.startsWith("ut")) {
                    while (true) {
                        List<BosObjectSummary> objectList = this.client.listObjects(bucketName).getContents();
                        if (objectList.size() < 1) {
                            break;
                        }
                        for (BosObjectSummary object : objectList) {
                            String key = object.getKey();
                            this.client.deleteObject(bucketName, key);
                        }
                    }
                    this.client.deleteBucket(bucket.getName());
                }
            }
            this.client.shutdown();
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

    public static class CommonTest extends Base {
        @Test
        public void test_RequestWithInvalidCredential() {
            this.expectBceServiceException(403, "SignatureDoesNotMatch");
            this.client.listBuckets(new ListBucketsRequest()
                    .withRequestCredentials(new DefaultBceCredentials("test", "test")));
        }
    }

    public static class GetBosAccountOwnerTest extends Base {
        @Test
        public void testOrdinary() {
            assertThat(this.client.getBosAccountOwner(), is(this.owner));
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.getBosAccountOwner(null);
        }
    }

    public static class ListBucketsTest extends Base {

        @Test
        public void testOrdinary() {
            for (int i = 0; i < 10; ++i) {
                this.client.createBucket(this.bucketName + "-" + i);
            }
            ListBucketsResponse listBucketsResponse = this.client.listBuckets();
            List<BucketSummary> buckets = listBucketsResponse.getBuckets();
            assertThat(listBucketsResponse, hasProperty("owner", is(this.owner)));
            for (int i = 0; i < 10; ++i) {
                assertThat(buckets, Matchers.<BucketSummary>hasItem(allOf(
                        hasProperty("creationDate", TestUtils.timeGapInSecondsLessThan(this.createTime, 30)),
                        hasProperty("name", is(this.bucketName + "-" + i)))));
            }
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.listBuckets(null);
        }
    }

    public static class CreateBucketTest extends Base {

        @Test
        public void testOrdinary() {
            String bucketName = this.bucketName + "other";
            this.client.createBucket(bucketName);
            GetBucketAclResponse response = this.client.getBucketAcl(bucketName);

            List<Grant> expectedGrant = new ArrayList<Grant>();
            List<Grantee> expectedGrantee = new ArrayList<Grantee>();
            expectedGrantee.add(this.grantee);
            List<Permission> expectedPermission = new ArrayList<Permission>();
            expectedPermission.add(Permission.FULL_CONTROL);
            expectedGrant.add(new Grant(expectedGrantee, expectedPermission));
            for (Grant grant : expectedGrant) {
                assertThat(response.getAccessControlList(), hasItem(new SamePropertyValuesAs(grant)));
            }
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.createBucket((CreateBucketRequest) null);
        }

        @Test
        public void testDuplicateName() {
            this.expectBceServiceException(409, "BucketAlreadyExists");
            this.client.createBucket(this.bucketName);
        }

        @Test
        public void testTooManyBuckets() {
            this.expectBceServiceException(400, "TooManyBuckets");
            for (int i = 0; i < 100; ++i) {
                this.client.createBucket(this.bucketName + i);
            }
        }

        @Test(expected = NullPointerException.class)
        public void testBucketWithEmptyName() {
            this.client.createBucket(new CreateBucketRequest(null));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameWithLessThreeLetters() {
            String bucketName = "aa";
            this.client.createBucket(bucketName);
        }

        @Test
        public void testBucketNameWithThreeLetters() {
            boolean excepted = false;
            String bucketName = "z4g";
            String errorCode = null;
            try {
                this.client.createBucket(bucketName);
            } catch (BceServiceException e) {
                excepted = true;
                errorCode = e.getErrorCode();
            }
            assertThat(excepted, is(true));
            assertThat(errorCode, is("BucketAlreadyExists"));
        }

        @Test
        public void testBucketNameWith63Letters() {
            boolean excepted = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 63; i++) {
                sb.append("e");
            }
            String bucketName = sb.toString();
            try {
                this.client.createBucket(bucketName);
            } catch (Exception e) {
                excepted = true;
            }
            Assert.assertFalse(excepted);

            try {
                this.client.deleteBucket(bucketName);
            } catch (BceServiceException e) {
                excepted = true;
            }
            Assert.assertFalse(excepted);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameWith64Letters() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 64; i++) {
                sb.append("b");
            }
            String bucketName = sb.toString();
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameWithUppercase() {
            String bucketName = "aaaAAaaa";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameStartUppercase() {
            String bucketName = "Aaa";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameEndUppercase() {
            String bucketName = "aaaA";
            this.client.createBucket(bucketName);
        }

        @Test
        public void testBucketNameStartNumber() {
            boolean excepted = false;
            String bucketName = "9aaa" + System.currentTimeMillis() + "a";
            try {
                this.client.createBucket(bucketName);
            } catch (Exception e) {
                excepted = true;
            }
            Assert.assertFalse(excepted);

            try {
                this.client.deleteBucket(bucketName);
            } catch (BceServiceException e) {
                e.printStackTrace();
                excepted = true;
            }
            Assert.assertFalse(excepted);
        }

        @Test
        public void testBucketNameEndNumber() {
            boolean excepted = false;
            String bucketName = "a" + System.currentTimeMillis() + "aaa9";
            try {
                this.client.createBucket(bucketName);
            } catch (Exception e) {
                excepted = true;
            }
            Assert.assertFalse(excepted);

            try {
                this.client.deleteBucket(bucketName);
            } catch (BceServiceException e) {
                e.printStackTrace();
                excepted = true;
            }
            Assert.assertFalse(excepted);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameStartHyphen() {
            String bucketName = "-aaa";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameEndHyphen() {
            String bucketName = "aaa-";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainOtherLetter() {
            String bucketName = "abcd!a";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainOtherLetter2() {
            String bucketName = "abcd{}a";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainOtherLetter3() {
            String bucketName = "ab=a";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainOtherLetter4() {
            String bucketName = "ab[]a";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainWhiteSpace() {
            String bucketName = "aaa aa";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainWhiteSpaceT() {
            String bucketName = "aaa\taaa";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainWhiteSpaceR() {
            String bucketName = "aaa\raaa";
            this.client.createBucket(bucketName);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testBucketNameContainWhiteSpaceN() {
            String bucketName = "aaa\naaa";
            this.client.createBucket(bucketName);
        }

        @Test
        public void testBucketNameContainNumber() {
            boolean excepted = false;
            String bucketName = "aaa-222-999aa1";
            try {
                this.client.createBucket(bucketName);
            } catch (Exception e) {
                excepted = true;
            }
            Assert.assertFalse(excepted);

            try {
                this.client.deleteBucket(bucketName);
            } catch (BceServiceException e) {
                e.printStackTrace();
                excepted = true;
            }
            Assert.assertFalse(excepted);
        }

    }

    public static class DoesBucketExistTest extends Base {

        @Test
        public void testExist() {
            assertThat(this.client.doesBucketExist(this.bucketName), is(true));
        }

        @Test
        public void testExistForbidden() {
            BosClient bosClient = new BosClient(new BosClientConfiguration()
                    .withCredentials(new DefaultBceCredentials
                            ("1899c3f4e2bc4ce2a10dbfe25691a8fd",
                                    "f7f030bdce1f45348e1de94270db1369"))
                    .withEndpoint(ENDPOINT));
            assertThat(bosClient.doesBucketExist(this.bucketName), is(true));
        }

        @Test
        public void testNotExist() {
            assertThat(this.client.doesBucketExist(this.bucketName + "notexist"), is(false));
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.doesBucketExist((DoesBucketExistRequest) null);
        }
    }

    public static class GetBucketTest extends Base {

        @Test
        public void testDefaultAcl() {
            GetBucketAclResponse response = this.client.getBucketAcl(this.bucketName);
            assertThat(response.getOwner(), is(this.grantee));

            List<Grant> grants = new ArrayList<Grant>();
            List<Grantee> grantee = new ArrayList<Grantee>();
            grantee.add(this.grantee);
            List<Permission> permission = new ArrayList<Permission>();
            permission.add(Permission.FULL_CONTROL);
            grants.add(new Grant(grantee, permission));
            assertThat(response.getAccessControlList(), hasSize(grants.size()));
            for (Grant grant : grants) {
                assertThat(response.getAccessControlList(), hasItem(new SamePropertyValuesAs(grant)));
            }
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.getBucketAcl((GetBucketAclRequest) null);
        }
    }

    public static class SetBucketAclTest extends Base {

        @Test
        public void testPublicReadWrite() {
            String objectKey = "objectPublicReadWrite";
            String data = "dataPublicReadWrite";

            this.client.setBucketAcl(this.bucketName, CannedAccessControlList.PublicReadWrite);
            GetBucketAclResponse response = this.client.getBucketAcl(this.bucketName);
            assertThat(response.getOwner(), is(this.grantee));

            List<Grant> grants = new ArrayList<Grant>();
            List<Grantee> granteeOwner = new ArrayList<Grantee>();
            granteeOwner.add(this.grantee);
            List<Permission> permissionOwner = new ArrayList<Permission>();
            permissionOwner.add(Permission.FULL_CONTROL);
            grants.add(new Grant(granteeOwner, permissionOwner));
            List<Grantee> granteeAnonymous = new ArrayList<Grantee>();
            granteeAnonymous.add(this.anonymous);
            List<Permission> permissionAnonymous = new ArrayList<Permission>();
            permissionAnonymous.add(Permission.READ);
            permissionAnonymous.add(Permission.WRITE);
            grants.add(new Grant(granteeAnonymous, permissionAnonymous));

            assertThat(response.getAccessControlList(), hasSize(grants.size()));
            for (Grant grant : grants) {
                assertThat(response.getAccessControlList(), hasItem(new SamePropertyValuesAs(grant)));
            }

            this.client.putObject(this.bucketName, objectKey, data);
            BosClient bosAnonymous = new BosClient(new BosClientConfiguration().withEndpoint(ENDPOINT));
            assertThat(new String(bosAnonymous.getObjectContent(this.bucketName, objectKey)), is(data));

            bosAnonymous.putObject(this.bucketName, "anonymous", "dataAnonymous");
            assertThat(new String(bosAnonymous.getObjectContent(this.bucketName, "anonymous")), is("dataAnonymous"));

            bosAnonymous.deleteObject(this.bucketName, objectKey);
            this.expectBceServiceException(404, "NoSuchKey");
            bosAnonymous.getObject(this.bucketName, objectKey);

            this.client.setBucketAcl(this.bucketName, CannedAccessControlList.Private);
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.setBucketAcl((SetBucketAclRequest) null);
        }

        @Test
        public void testPublicRead() throws Exception {
            String objectKey = "objectPublicRead";
            String data = "dataPublicRead";

            this.client.putObject(this.bucketName, objectKey, data);
            this.client.setBucketAcl(this.bucketName, CannedAccessControlList.PublicRead);

            BosClient bosAnonymous = new BosClient(new BosClientConfiguration().withEndpoint(ENDPOINT));
            assertThat(new String(bosAnonymous.getObjectContent(this.bucketName, objectKey)), is(data));

            this.expectBceServiceException(403, "AccessDenied");
            bosAnonymous.putObject(this.bucketName, "anonymous", "dataAnonymous");

            this.expectBceServiceException(403, "AccessDenied");
            bosAnonymous.deleteObject(this.bucketName, objectKey);

            this.client.setBucketAcl(this.bucketName, CannedAccessControlList.Private);
        }

        @Test
        public void testSetBucketAclFromBody() throws Exception {
            List<Grant> grants = new ArrayList<Grant>();
            List<Grantee> grantee = new ArrayList<Grantee>();
            List<Permission> permission = new ArrayList<Permission>();
            grantee.add(new Grantee("bf33dc8ef8a942c08f47d94ade6aee7d"));
            grantee.add(new Grantee("00000000000000000000000000000000"));
            grantee.add(new Grantee("11111111111111111111111111111111"));
            permission.add(Permission.READ);
            permission.add(Permission.WRITE);
            grants.add(new Grant().withGrantee(grantee).withPermission(permission));

            grantee = new ArrayList<Grantee>();
            permission = new ArrayList<Permission>();
            grantee.add(new Grantee("bf33dc8ef8a942c08f47d94ade6aee7d"));
            grantee.add(this.grantee);
            permission.add(Permission.FULL_CONTROL);
            grants.add(new Grant().withGrantee(grantee).withPermission(permission));
            SetBucketAclRequest request = new SetBucketAclRequest(this.bucketName, grants);
            this.client.setBucketAcl(request);
            GetBucketAclResponse response = this.client.getBucketAcl(bucketName);

            GetBucketAclResponse expectedResponse = new GetBucketAclResponse();
            expectedResponse.setOwner(this.grantee);
            expectedResponse.setAccessControlList(grants);
            assertThat(response.getAccessControlList(), hasSize(expectedResponse.getAccessControlList().size()));
            for (Grant grant : expectedResponse.getAccessControlList()) {
                assertThat(response.getAccessControlList(), hasItem(new SamePropertyValuesAs(grant)));
            }
        }
    }

    public static class DeleteBucketTest extends Base {
        @Test
        public void testOrdinary() {
            this.client.deleteBucket(this.bucketName);
            assertThat(this.client.listBuckets().getBuckets(), not(hasProperty("name", is(this.bucketName))));
        }

        @Test
        public void testNotExist() {
            this.expectBceServiceException(404, "NoSuchBucket");
            this.client.deleteBucket(this.bucketName + "notexist");
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.deleteBucket((DeleteBucketRequest) null);
        }

        @Test
        public void testBucketNotEmpty() {
            this.expectBceServiceException(409, "BucketNotEmpty");
            this.client.putObject(this.bucketName, "test", "value");
            this.client.deleteBucket(this.bucketName);
        }
    }

    @Ignore
    public static class ListObjectsBase extends Base {
        protected SortedMap<String, String> expectedContents;
        protected List<String> expectedCommonPrefixes;
        protected String expectedDelimiter;
        protected String expectedMarker;
        protected String expectedNextMarker;
        protected boolean expectedTruncated;
        protected int expectedMaxKeys;
        protected String expectedPrefix;

        @Override
        @Before
        public void setUp() {
            super.setUp();
            this.expectedContents = Maps.newTreeMap();
            this.expectedContents.put("dir0/dir1/key0", Strings.repeat("v", 1));
            this.expectedContents.put("dir0/key0", Strings.repeat("v", 2));
            this.expectedContents.put("dir0/key1", Strings.repeat("v", 3));
            this.expectedContents.put("dir1/dir2/key1", Strings.repeat("v", 4));
            this.expectedContents.put("dir1/dir2/key2", Strings.repeat("v", 5));
            this.expectedContents.put("dir1/key0", Strings.repeat("v", 6));
            this.expectedContents.put("dir3/key0", Strings.repeat("v", 7));
            this.expectedContents.put("dir3/key1", Strings.repeat("v", 8));
            this.expectedContents.put("dir4/key0", Strings.repeat("v", 9));
            this.expectedContents.put("key", Strings.repeat("v", 10));

            for (Map.Entry<String, String> entry : this.expectedContents.entrySet()) {
                this.client.putObject(this.bucketName, entry.getKey(), entry.getValue());
            }
            this.expectedCommonPrefixes = null;
            this.expectedDelimiter = null;
            this.expectedMarker = "";
            this.expectedNextMarker = null;
            this.expectedTruncated = false;
            this.expectedPrefix = "";
            this.expectedMaxKeys = 1000;
        }

        protected void checkResponse(ListObjectsResponse response) {
            assertThat(response, hasProperty("bucketName", is(this.bucketName)));
            assertThat(response, hasProperty("commonPrefixes", is(this.expectedCommonPrefixes)));
            assertThat(response, hasProperty("delimiter", is(this.expectedDelimiter)));
            assertThat(response, hasProperty("marker", is(this.expectedMarker)));
            assertThat(response, hasProperty("nextMarker", is(this.expectedNextMarker)));
            assertThat(response, hasProperty("truncated", is(this.expectedTruncated)));
            assertThat(response, hasProperty("maxKeys", is(this.expectedMaxKeys)));
            assertThat(response, hasProperty("prefix", is(this.expectedPrefix)));
            List<BosObjectSummary> objects = response.getContents();
            assertThat(objects, hasSize(this.expectedContents.size()));
            Iterator<BosObjectSummary> it = objects.iterator();
            for (Map.Entry<String, String> entry : this.expectedContents.entrySet()) {
                BosObjectSummary object = it.next();
                assertThat(object, hasProperty("bucketName", is(this.bucketName)));
                assertThat(object, hasProperty("key", is(entry.getKey())));
                assertThat(object, hasProperty("owner", is(this.owner)));
                assertThat(object, hasProperty("size", is((long) entry.getValue().length())));
                assertThat(object,
                        hasProperty("lastModified", TestUtils.timeGapInSecondsLessThan(this.createTime, 30)));
                try {
                    assertThat(object, hasProperty("ETag", is(Hex.encodeHexString(HashUtils
                            .computeMd5Hash(new ByteArrayInputStream(entry.getValue().getBytes()))))));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ListObjectsTest extends ListObjectsBase {

        @Test
        public void testOrdinary() {
            this.checkResponse(this.client.listObjects(this.bucketName));
        }

        @Test
        public void testPrefix() {
            this.expectedContents.remove("key");
            this.expectedPrefix = "d";
            this.checkResponse(this.client.listObjects(this.bucketName, "d"));
        }

        @Test
        public void testMarker() {
            this.expectedContents = this.expectedContents.subMap("dir1", "z");
            this.expectedMarker = "dir1";
            this.checkResponse(this.client.listObjects(new ListObjectsRequest(this.bucketName).withMarker("dir1")));
        }

        @Test
        public void testDelimiter() {
            this.expectedContents.clear();
            this.expectedContents.put("dir0/key0", Strings.repeat("v", 2));
            this.expectedCommonPrefixes = Lists.newArrayList();
            this.expectedCommonPrefixes.add("dir0/dir1/");
            this.expectedNextMarker = "dir0/key0";
            this.expectedDelimiter = "/";
            this.expectedTruncated = true;
            this.expectedMaxKeys = 2;
            this.expectedPrefix = "dir0/";
            this.checkResponse(this.client
                    .listObjects(new ListObjectsRequest(this.bucketName, "dir0/").withDelimiter("/").withMaxKeys(2)));
        }

        @Test
        public void testMaxKeys() {
            this.expectedContents = this.expectedContents.subMap("dir0", "dir1/dir2/key1");
            this.expectedTruncated = true;
            this.expectedMaxKeys = 3;
            this.expectedNextMarker = "dir0/key1";
            this.checkResponse(this.client.listObjects(new ListObjectsRequest(this.bucketName).withMaxKeys(3)));
        }

        @Test
        public void testNegativeMaxKeys() {
            this.checkResponse(this.client.listObjects(new ListObjectsRequest(this.bucketName).withMaxKeys(-1)));
        }

        @Test
        public void testCombination() {
            this.expectedContents = this.expectedContents.subMap("dir1", "dir3/key0");
            this.expectedTruncated = true;
            this.expectedMarker = "dir1";
            this.expectedMaxKeys = 3;
            this.expectedPrefix = "d";
            this.expectedNextMarker = "dir1/key0";
            this.checkResponse(this.client.listObjects(new ListObjectsRequest(this.bucketName).withPrefix("d")
                    .withMarker("dir1").withMaxKeys(3)));
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.listObjects((ListObjectsRequest) null);
        }
    }

    public static class ListNextBatchOfObjectsTest extends ListObjectsBase {
        protected ListObjectsResponse response;

        @Override
        @Before
        public void setUp() {
            super.setUp();
            this.response = new ListObjectsResponse();
            this.response.setBucketName(this.bucketName);
            this.response.setTruncated(true);
            this.response.setMaxKeys(1000);
        }

        @Test
        public void testOrdinary() {
            this.checkResponse(this.client.listNextBatchOfObjects(this.response));
        }

        @Test
        public void testPrefix() {
            this.expectedContents.remove("key");
            this.expectedPrefix = "d";
            this.response.setPrefix("d");
            this.checkResponse(this.client.listNextBatchOfObjects(this.response));
        }

        @Test
        public void testMarker() {
            this.expectedContents = this.expectedContents.subMap("dir1", "z");
            this.expectedMarker = "dir1";
            this.response.setNextMarker("dir1");
            this.checkResponse(this.client.listNextBatchOfObjects(this.response));
        }

        @Test
        public void testDelimiter() {
            this.expectedContents.clear();
            this.expectedCommonPrefixes = Lists.newArrayList();
            this.expectedCommonPrefixes.add("dir1/");
            this.expectedCommonPrefixes.add("dir3/");
            this.expectedCommonPrefixes.add("dir4/");
            this.expectedDelimiter = "/";
            this.expectedMaxKeys = 3;
            this.expectedNextMarker = "dir4/key0";
            this.expectedTruncated = true;
            this.expectedMarker = "dir0/key0";

            this.response.setDelimiter("/");
            this.response.setMaxKeys(3);
            this.response.setNextMarker("dir0/key0");
            this.checkResponse(this.client.listNextBatchOfObjects(this.response));
        }

        @Test
        public void testMaxKeys() {
            this.expectedContents = this.expectedContents.subMap("dir0", "dir1/dir2/key1");
            this.expectedTruncated = true;
            this.expectedMaxKeys = 3;
            this.expectedNextMarker = "dir0/key1";
            this.response.setMaxKeys(3);
            this.checkResponse(this.client.listNextBatchOfObjects(this.response));
        }

        @Test
        public void testCombination() {
            this.expectedContents = this.expectedContents.subMap("dir1", "dir3/key0");
            this.expectedTruncated = true;
            this.expectedMarker = "dir1";
            this.expectedNextMarker = "dir1/key0";
            this.expectedMaxKeys = 3;
            this.expectedPrefix = "d";
            this.response.setPrefix("d");
            this.response.setNextMarker("dir1");
            this.response.setMaxKeys(3);
            this.checkResponse(this.client.listNextBatchOfObjects(this.response));
        }

        @Test(expected = NullPointerException.class)
        public void testNullResponse() {
            this.client.listNextBatchOfObjects((ListObjectsResponse) null);
        }

        @Test
        public void testNotTruncated() {
            this.response.setPrefix("d");
            this.response.setNextMarker("dir1");
            this.response.setMaxKeys(3);
            this.response.setTruncated(false);
            this.expectedContents.clear();
            this.expectedMarker = "dir1";
            this.expectedPrefix = "d";
            this.expectedMaxKeys = 3;
            this.checkResponse(this.client.listNextBatchOfObjects(this.response));
        }
    }

    public static class GetObjectTest extends Base {
        protected String expectedKey;
        protected String expectedValue;
        protected ObjectMetadata expectedMetadata;

        @Override
        @Before
        public void setUp() {
            super.setUp();
            this.expectedKey = "key";
            this.expectedValue = "value";
            this.expectedMetadata = new ObjectMetadata();
            this.expectedMetadata.addUserMetadata("uk0", "v0");
            this.expectedMetadata.addUserMetadata("uk1", "v1");
            this.client.putObject(this.bucketName, this.expectedKey, this.expectedValue, this.expectedMetadata);
        }

        protected void checkObject(BosObject object) throws IOException {
            BosObjectInputStream input = object.getObjectContent();
            try {
                assertThat(object, hasProperty("bucketName", is(this.bucketName)));
                assertThat(object, hasProperty("key", is(this.expectedKey)));
                assertThat(IOUtils.toString(input), is(this.expectedValue));
                ObjectMetadata metadata = object.getObjectMetadata();
                assertThat(metadata.getContentLength(), is((long) this.expectedValue.length()));
                assertThat(metadata.getUserMetadata(), is(this.expectedMetadata.getUserMetadata()));
            } catch (IOException e) {
                input.close();
            }
        }

        @Test
        public void testOrdinary() throws IOException {
            this.checkObject(this.client.getObject(this.bucketName, "key"));
            this.client.getObject(this.bucketName, "key").close();
        }

        @Test
        public void testNotExist() {
            this.expectBceServiceException(404, "NoSuchKey");
            this.client.getObject(this.bucketName, "nosuchkey");
        }

        @Test
        public void testRange() throws IOException {
            String objectKey = "testRange";
            this.client.putObject(this.bucketName, objectKey, "abcdefghigklmnopqrstuvwxyz");
            this.expectedValue = "abcdef";
            GetObjectRequest request =
                    new GetObjectRequest().withBucketName(this.bucketName).withKey(objectKey).withRange(0, 5);
            InputStream input = this.client.getObject(request).getObjectContent();
            assertThat(IOUtils.toString(input), is(this.expectedValue));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testNegativeRange() throws IOException {
            GetObjectRequest request = new GetObjectRequest().withRange(-1, 5);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testErrorRange() throws IOException {
            GetObjectRequest request = new GetObjectRequest().withRange(6, 5);
        }

        @Test
        public void testRangeToFile() throws IOException, NoSuchAlgorithmException {
            String objectKey = "testRangeToFile";
            this.client.putObject(this.bucketName, objectKey, "abcdefghigklmnopqrstuvwxyz");
            this.expectedValue = "abcdef";
            File file = new File("test");
            try {
                this.client.getObject(new GetObjectRequest(this.bucketName, objectKey).withRange(0, 5), file);
                assertThat(HashUtils.computeMd5Hash(new ByteArrayInputStream("abcdef".getBytes())),
                        is(HashUtils.computeMd5Hash(new FileInputStream(file))));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectToFile() throws IOException, NoSuchAlgorithmException {
            String objectKey = "testObjectToFile";
            this.client.putObject(this.bucketName, objectKey, "dataFile");
            File file = new File("test");
            try {
                this.client.getObject(this.bucketName, objectKey, file);
                assertThat(HashUtils.computeMd5Hash(new ByteArrayInputStream("dataFile".getBytes())),
                        is(HashUtils.computeMd5Hash(new FileInputStream(file))));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectContent() {
            String objectKey = "testObjectContent";
            this.client.putObject(this.bucketName, objectKey, "dataContent");
            assertThat("dataContent".getBytes(), is(this.client.getObjectContent(this.bucketName, objectKey)));
        }
    }

    public static class GetObjectMetadataTest extends Base {
        @Test
        public void testOrdinary() {
            ObjectMetadata metadata = new ObjectMetadata();
            for (int i = 0; i < 10; ++i) {
                metadata.addUserMetadata("key-" + i, "value-" + i);
            }
            this.client.putObject(this.bucketName, "test", "data", metadata);
            metadata = this.client.getObjectMetadata(this.bucketName, "test");
            assertThat(metadata.getContentLength(), is(4L));
            Map<String, String> userMeta = metadata.getUserMetadata();
            for (int i = 0; i < 10; ++i) {
                assertThat(userMeta.get("key-" + i), is("value-" + i));
            }
        }

        @Test
        public void testNoSuchKey() {
            this.client.putObject(this.bucketName, "test", "data");
            this.expectBceServiceException(404, null);
            this.client.getObjectMetadata(this.bucketName, "test1");
        }
    }

    public static class PutObjectTest extends Base {
        @Test
        public void testOrdinary() throws IOException, NoSuchAlgorithmException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                PutObjectRequest request = new PutObjectRequest(this.bucketName, "test", file);
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("text/plain");
                request.setObjectMetadata(objectMetadata);
                String eTag = this.client.putObject(request).getETag();
                assertThat(eTag, is(Hex.encodeHexString(HashUtils.computeMd5Hash(new FileInputStream(file)))));
                assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testNoSuchBucket() throws IOException {
            String object = "object" + System.currentTimeMillis();
            String base = "1234567890";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1024 * 40; i++) {
                sb.append(base);
            }
            this.expectBceServiceException(404, "NoSuchBucket");
            this.client.putObject("nosuchbucket111", object, sb.toString());
        }

        @Test
        public void testInputStream() throws IOException, NoSuchAlgorithmException {
            PutObjectRequest request =
                    new PutObjectRequest(this.bucketName, "test", new ByteArrayInputStream("data".getBytes()));
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("text/plain");
            request.setObjectMetadata(objectMetadata);
            String eTag = this.client.putObject(request).getETag();
            assertThat(eTag, is(Hex.encodeHexString(HashUtils
                    .computeMd5Hash(new ByteArrayInputStream("data".getBytes())))));
            assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
        }

        @Test
        public void testInputStreamWithContentLength() throws IOException, NoSuchAlgorithmException {
            PutObjectRequest request =
                    new PutObjectRequest(this.bucketName, "test", new ByteArrayInputStream("data".getBytes()));
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("text/plain");
            objectMetadata.setContentLength(1);
            request.setObjectMetadata(objectMetadata);
            String eTag = this.client.putObject(this.bucketName, "test", "data", objectMetadata).getETag();
            assertThat(eTag, is(Hex.encodeHexString(HashUtils
                    .computeMd5Hash(new ByteArrayInputStream("d".getBytes())))));
            assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("d"));
        }

        @Test
        public void testFileInputStream() throws IOException, NoSuchAlgorithmException {
            File file = createFile("test", 5 * 1024 * 1024);
            try {
                String eTag = this.client.putObject(this.bucketName, "test", new FileInputStream(file)).getETag();
                assertThat(eTag, is(Hex.encodeHexString(HashUtils.computeMd5Hash(new FileInputStream(file)))));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testRestartableInputStream() throws IOException, NoSuchAlgorithmException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                PutObjectRequest request =
                        new PutObjectRequest(this.bucketName, "test", new RestartableFileInputStream(file));
                String eTag = this.client.putObject(request).getETag();
                assertThat(eTag, is(Hex.encodeHexString(HashUtils.computeMd5Hash(new FileInputStream(file)))));
                assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testWrapRestartableInputStream() throws IOException, NoSuchAlgorithmException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                PutObjectRequest request = new PutObjectRequest(this.bucketName, "test", new FileInputStream(file));
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("text/plain");
                objectMetadata.setContentLength(4);
                request.setObjectMetadata(objectMetadata);
                String eTag = this.client.putObject(request).getETag();
                assertThat(eTag, is(Hex.encodeHexString(HashUtils.computeMd5Hash(new FileInputStream(file)))));
                assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test(expected = IllegalArgumentException.class)
        public void testEmptyObject() {
            this.client.putObject(this.bucketName, "", "data");
        }

        @Test
        public void testObjectSpace() {
            this.client.putObject(this.bucketName, "  ", "data");
            assertThat(new String(this.client.getObjectContent(this.bucketName, "  ")), is("data"));
        }

        @Test
        public void testObjectKeyWithSpecialChar() throws IOException, NoSuchAlgorithmException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                String eTag =
                        this.client.putObject(this.bucketName, "///~`+_|!@#$%^&*()-=[]{};':'：,.<>/?// 、", file)
                                .getETag();
                assertThat(eTag, is(Hex.encodeHexString(HashUtils.computeMd5Hash(new FileInputStream(file)))));
                assertThat(new String(this.client
                        .getObjectContent(this.bucketName, "///~`+_|!@#$%^&*()-=[]{};':'：,.<>/?// 、")), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectKeyWithChinese() throws IOException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                this.client.putObject(this.bucketName, "百度云", file);
                assertThat(new String(this.client.getObjectContent(this.bucketName, "百度云")), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectKeyWith1024Letter() throws Exception {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < 1024) {
                sb.append("a");
                i++;
            }
            String objectKey = sb.toString();

            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                this.client.putObject(this.bucketName, objectKey, file);
                assertThat(new String(this.client.getObjectContent(this.bucketName, objectKey)), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test(expected = IllegalArgumentException.class)
        public void testObjectKeyWith1025Letter() throws Exception {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < 1025) {
                sb.append("a");
                i++;
            }
            String objectKey = sb.toString();

            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                this.client.putObject(this.bucketName, objectKey, file);
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectKeyWithUserMeta() throws IOException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                ObjectMetadata objectMetadata = new ObjectMetadata();
                Map<String, String> userMetadata = Maps.newHashMap();
                // userMetadata.put("test/a=b/file", "百度  ");
                userMetadata.put("test1", "百度  ");
                objectMetadata.setUserMetadata(userMetadata);
                this.client.putObject(this.bucketName, "test", file, objectMetadata);
                assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
                assertThat(this.client.getObjectMetadata(this.bucketName, "test").getUserMetaDataOf("test1"),
                        is("百度  "));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectKeyWithUserMetaNonKey() throws IOException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                ObjectMetadata objectMetadata = new ObjectMetadata();
                Map<String, String> userMetadata = Maps.newHashMap();
                userMetadata.put(null, null);
                objectMetadata.setUserMetadata(userMetadata);
                this.client.putObject(this.bucketName, "test", file, objectMetadata);
                assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectKeyWithUserMetaNonValue() throws IOException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                ObjectMetadata objectMetadata = new ObjectMetadata();
                Map<String, String> userMetadata = Maps.newHashMap();
                userMetadata.put("key", null);
                objectMetadata.setUserMetadata(userMetadata);
                this.client.putObject(this.bucketName, "test", file, objectMetadata);
                assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
                assertThat(this.client.getObjectMetadata(this.bucketName, "test").getUserMetaDataOf("key"),
                        is((String) null));
//                assertThat(this.client.getObjectMetadata(this.bucketName, "test").getUserMetaDataOf("key"),
//                        is((String) ""));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectKeyWithUserMeta2048Byte() throws IOException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                ObjectMetadata objectMetadata = new ObjectMetadata();
                Map<String, String> userMetadata = Maps.newHashMap();
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i < 2034) {
                    sb.append("a");
                    i++;
                }
                userMetadata.put("key", sb.toString());
                objectMetadata.setUserMetadata(userMetadata);
                this.client.putObject(this.bucketName, "test", file, objectMetadata);
                assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testObjectKeyWithUserMeta2049Byte() throws IOException {
            File file = File.createTempFile("test", null);
            try {
                FileUtils.writeStringToFile(file, "data");
                ObjectMetadata objectMetadata = new ObjectMetadata();
                Map<String, String> userMetadata = Maps.newHashMap();
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i < 2035) {
                    sb.append("a");
                    i++;
                }
                userMetadata.put("key", sb.toString());
                objectMetadata.setUserMetadata(userMetadata);
                try {
                    this.client.putObject(this.bucketName, "test", file, objectMetadata);
                } catch (BceServiceException bse) {
                    assertThat(bse.getStatusCode(), is(400));
                    assertThat(bse.getErrorCode(), is("MetadataTooLarge"));
                }
            } finally {
                file.delete();
            }
        }

        @Test(expected = BceClientException.class)
        public void testObjectKeyWithUserMetaTooLong() throws IOException {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            Map<String, String> userMetadata = Maps.newHashMap();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < 1024 * 1024) {
                sb.append("a");
                i++;
            }
            userMetadata.put("key", sb.toString());
            objectMetadata.setUserMetadata(userMetadata);
            this.client.putObject(this.bucketName, "test", "data", objectMetadata);
        }

        @Test
        @Ignore
        public void testLength5G() throws IOException, NoSuchAlgorithmException {
            File file = createFile("test5G", 50 * 1024 * 1024);
            try {
                PutObjectRequest request = new PutObjectRequest(this.bucketName, "test", file);
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("text/plain");
                request.setObjectMetadata(objectMetadata);
                String eTag = this.client.putObject(request).getETag();
                assertThat(eTag, is(Hex.encodeHexString(HashUtils.computeMd5Hash(new FileInputStream(file)))));
            } finally {
                file.delete();
            }
        }

        @Test
        @Ignore
        public void testLengthMoreThan5G() throws IOException {
            File file = createFile("test", 5 * 1024 * 1024 * 1024L + 1);
            try {
                this.expectBceServiceException(400, "EntityTooLarge");
                this.client.putObject(this.bucketName, "test", file);
            } finally {
                file.delete();
            }
        }

        @Test
        public void testPutObjectStanardIA() throws IOException {

            File file = createFile("ia_object", 1024 * 1024);
            try {
                PutObjectRequest request =
                        new PutObjectRequest(this.bucketName, "ia_test", file);
                request.withStorageClass(BosClient.STORAGE_CLASS_STANDARD_IA);
                this.client.putObject(request);
                ObjectMetadata meta = this.client.getObjectMetadata(this.bucketName, "ia_test");
                assertThat(meta.getStorageClass(), is(BosClient.STORAGE_CLASS_STANDARD_IA));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testPutObjectStanard() throws IOException {

            File file = createFile("standard_object", 1024 * 1024);
            try {
                PutObjectRequest request =
                        new PutObjectRequest(this.bucketName, "standard_test", file);
                request.withStorageClass("");
                this.client.putObject(request);
                ObjectMetadata meta = this.client.getObjectMetadata(this.bucketName, "standard_test");
                assertThat(meta.getStorageClass(), is(BosClient.STORAGE_CLASS_STANDARD));
            } finally {
                file.delete();
            }
        }

        @Test
        public void testPutObjectStanardWithNoArg() throws IOException {

            File file = createFile("standard_object", 1024 * 1024);
            try {
                PutObjectRequest request =
                        new PutObjectRequest(this.bucketName, "standard_test_noarg", file);
                this.client.putObject(request);
                ObjectMetadata meta = this.client.getObjectMetadata(this.bucketName, "standard_test_noarg");
                assertThat(meta.getStorageClass(), is(BosClient.STORAGE_CLASS_STANDARD));
            } finally {
                file.delete();
            }
        }

    }

    public static class CopyObjectTest extends Base {
        @Test
        public void testSameBucket() throws IOException {
            // this.client.putObject(this.bucketName, "test", "data");
            // this.client.copyObject(this.bucketName, "test", this.bucketName, "test1");

            this.client.putObject(this.bucketName, "你好", "data");
            this.client.copyObject(this.bucketName, "你好", this.bucketName, "test1");

            assertThat(new String(this.client.getObjectContent(this.bucketName, "test1")), is("data"));
        }

        @Test
        public void testETagMatch() throws IOException, NoSuchAlgorithmException {
            String eTag = this.client.putObject(this.bucketName, "test", "data").getETag();
            CopyObjectRequest copyObjectRequest =
                    new CopyObjectRequest(this.bucketName, "test", this.bucketName, "test1");
            CopyObjectResponse response = this.client.copyObject(copyObjectRequest.withETag(eTag));
            assertThat(response.getETag(), is(Hex.encodeHexString(HashUtils.computeMd5Hash
                    (new ByteArrayInputStream("data".getBytes())))));
            assertThat(new String(this.client.getObjectContent(this.bucketName, "test")), is("data"));
        }

        @Test
        public void testETagNotMatch() {
            this.expectBceServiceException(412, "PreconditionFailed");
            this.client.putObject(this.bucketName, "test", "data").getETag();
            CopyObjectRequest copyObjectRequest =
                    new CopyObjectRequest(this.bucketName, "test", this.bucketName, "test1");
            this.client.copyObject(copyObjectRequest.withETag("111111111183bf192b57a4afc76fa632"));
        }

        @Test
        public void testKeyWithSpecialChar() {
            String sourceKey = "  test/A=B/file  ";
            String eTag = this.client.putObject(this.bucketName, sourceKey, "data").getETag();
            CopyObjectRequest copyObjectRequest =
                    new CopyObjectRequest(this.bucketName, sourceKey, this.bucketName, "  test/C=d/file1  ");
            this.client.copyObject(copyObjectRequest.withETag(eTag));
            assertThat(new String(this.client.getObjectContent(this.bucketName, "  test/C=d/file1  ")), is("data"));
        }

        @Test
        public void testSyncCopySuccess() {
            this.client.putObject(this.bucketName, "test", "data").getETag();
            CopyObjectRequest req = new CopyObjectRequest(bucketName, "test", bucketName, "test1");
            req.withStorageClass(BosClient.STORAGE_CLASS_STANDARD_IA);
            this.client.copyObject(req);
            ObjectMetadata meta = this.client.getObjectMetadata(this.bucketName, "test1");
            assertThat(meta.getStorageClass(), is(BosClient.STORAGE_CLASS_STANDARD_IA));
        }

        @Test
        public void testMetaReplace() throws Exception {
            String sourceBucketName = this.bucketName;
            String sourceKey = "testCopyObjectMetaCopySrc";
            String destinationBucketName = this.bucketName;
            String destinationKey = "testCopyObjectMetaCopyDest";
            boolean excepted = false;
            String userMetadataKeySrc = "meta-key-src";
            String userMetadataKeyDest = "meta-key-dest";
            String userMetadataValueSrc = "meta-value-src";
            String userMetadataValueDest = "meta-value-dest";
            String resultUserMetadataValueSrc = null;
            String resultUserMetadataValueDest = null;

            try {
                // Source Object With Metadata
                ObjectMetadata objectMetadata = new ObjectMetadata();
                Map<String, String> userMetadata = new HashMap<String, String>();
                userMetadata.put(userMetadataKeySrc, userMetadataValueSrc);
                objectMetadata.setUserMetadata(userMetadata);
                PutObjectResponse putObjectResult =
                        this.client.putObject(this.bucketName, sourceKey, "value", objectMetadata);

                //  Destination Object With NewMetadata
                CopyObjectRequest copyObjectRequest =
                        new CopyObjectRequest(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
                ObjectMetadata objectNewMetadata = new ObjectMetadata();
                Map<String, String> userNewMetadata = new HashMap<String, String>();
                userNewMetadata.put(userMetadataKeyDest, userMetadataValueDest);
                objectNewMetadata.setUserMetadata(userNewMetadata);
                copyObjectRequest.withNewObjectMetadata(objectNewMetadata);
                this.client.copyObject(copyObjectRequest);


                resultUserMetadataValueSrc =
                        this.client.getObjectMetadata(bucketName, sourceKey).getUserMetadata()
                                .get(userMetadataKeySrc);
//                userMetadataKeyDest = userMetadataKeyDest.toLowerCase();
                resultUserMetadataValueDest =
                        this.client.getObjectMetadata(bucketName, destinationKey).getUserMetadata()
                                .get(userMetadataKeyDest);
            } catch (BceServiceException bse) {
                excepted = true;
            } catch (BceClientException bce) {
                excepted = true;
            }
            Assert.assertFalse(excepted);
            Assert.assertEquals(userMetadataValueSrc, resultUserMetadataValueSrc);
            Assert.assertEquals(userMetadataValueDest, resultUserMetadataValueDest);
        }

        @Test
        public void testSourceKeyNotExist() {
            this.expectBceServiceException(404, "NoSuchKey");
            this.client.copyObject(this.bucketName, "noSuchKey", this.bucketName, "dest");
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.copyObject((CopyObjectRequest) null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testEmptyKey() {
            this.client.copyObject(this.bucketName, "", this.bucketName, "test1");
        }
    }

    public static class DeleteObjectTest extends Base {
        @Test
        public void testOrdinary() {
            this.client.putObject(this.bucketName, "test", "data");
            this.client.deleteObject(this.bucketName, "test");
            assertThat(this.client.listObjects(this.bucketName).getContents(), empty());
        }

        @Test
        public void testNoSuchKey() {
            this.expectBceServiceException(404, "NoSuchKey");
            this.client.deleteObject(this.bucketName, "noSuchKey");
        }

        @Test(expected = IllegalArgumentException.class)
        public void testEmptyKey() {
            this.client.deleteObject(this.bucketName, "");
        }
    }

    public static class InitiateMultipartUploadTest extends Base {
        @Test
        public void testOrdinary() {
            InitiateMultipartUploadResponse response = this.client.initiateMultipartUpload(this.bucketName, "test");
            assertThat(response.getBucketName(), is(this.bucketName));
            assertThat(response.getKey(), is("test"));
            String uploadId = response.getUploadId();
            List<MultipartUploadSummary> uploads =
                    this.client.listMultipartUploads(this.bucketName).getMultipartUploads();
            assertThat(uploads, hasSize(1));
            assertThat(uploads.get(0).getUploadId(), is(uploadId));
        }
    }

    public static class UploadPartTest extends Base {
        @Test
        public void testOrdinary() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            UploadPartResponse response = this.client.uploadPart(new UploadPartRequest().withBucketName(this.bucketName)
                    .withKey("test").withUploadId(uploadId).withPartNumber(1).withPartSize(4)
                    .withInputStream(new ByteArrayInputStream("data".getBytes())));
            assertThat(response.getPartNumber(), is(1));
            assertThat(response.getETag(), notNullValue());
            List<PartSummary> parts = this.client.listParts(this.bucketName, "test", uploadId).getParts();
            assertThat(parts, hasSize(1));
            PartSummary part = parts.get(0);
            assertThat(part, notNullValue());
            assertThat(part.getETag(), is(response.getETag()));
            assertThat(part.getSize(), is(4L));
        }

        @Test
        public void testNoSuchBucket() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            // this.expectBceServiceException(404, "NoSuchUpload");
            this.expectBceServiceException(404, "NoSuchBucket");
            String base = "1234567890";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1024 * 40; i++) {
                sb.append(base);
            }
            String bucketName = "nosuchbucket" + DateTime.now().getMinuteOfDay();
            this.client.uploadPart(new UploadPartRequest().withBucketName(bucketName)
                    .withKey("test").withUploadId(uploadId).withPartNumber(1)
                    .withPartSize(sb.length())
                    .withInputStream(new ByteArrayInputStream(sb.toString().getBytes())));
        }

        @Test
        public void testNoSuchUpload() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            this.client.abortMultipartUpload(this.bucketName, "test", uploadId);
            this.expectBceServiceException(404, "NoSuchUpload");
            String base = "1234567890";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1024 * 40; i++) {
                sb.append(base);
            }
            this.client.uploadPart(new UploadPartRequest().withBucketName(this.bucketName).withKey("test")
                    .withUploadId(uploadId).withPartNumber(1)
                    .withPartSize(sb.length())
                    .withInputStream(new ByteArrayInputStream(sb.toString().getBytes())));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testPartNumberZero() throws IOException {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            this.client.uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, 0, 4,
                    new ByteArrayInputStream("data".getBytes())));
        }

        @Test
        public void testPartNumber10000() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            UploadPartResponse response = this.client
                    .uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, 10000, 4,
                            new ByteArrayInputStream("data".getBytes())));
            assertThat(response.getPartNumber(), is(10000));
        }

        @Test
        public void testPartNumber10001() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            this.expectBceServiceException(400, "InvalidArgument");
            this.client.uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, 10001, 4,
                    new ByteArrayInputStream("data".getBytes())));
        }

        @Test
        public void testPartSizeLessThan5M() throws IOException, NoSuchAlgorithmException {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            UploadPartResponse uploadPartResponse = this.client
                    .uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, 1, 4,
                            new ByteArrayInputStream("data".getBytes())));
            List<PartETag> partETags = Lists.newArrayList();
            partETags.add(uploadPartResponse.getPartETag());
            assertThat(uploadPartResponse.getPartNumber(), is(1));
            assertThat(uploadPartResponse.getETag(), is(Hex.encodeHexString(HashUtils
                    .computeMd5Hash(new ByteArrayInputStream("data".getBytes())))));
            this.client.completeMultipartUpload(this.bucketName, "test", uploadId, partETags);
            assertThat(this.client.getObjectContent(this.bucketName, "test"), is("data".getBytes()));
        }

        @Test
        public void testEntityTooSmall() throws IOException {
            String objectKey = "partSize";
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, objectKey).getUploadId();
            List<PartETag> partETags = new ArrayList<PartETag>();
            for (int i = 0; i < 2; i++) {
                UploadPartRequest uploadPartRequest =
                        new UploadPartRequest(bucketName, objectKey, uploadId, i + 1, 4,
                                new ByteArrayInputStream("data".getBytes()));
                UploadPartResponse uploadPartResult = client.uploadPart(uploadPartRequest);
                partETags.add(uploadPartResult.getPartETag());
            }

            this.expectBceServiceException(400, "EntityTooSmall");
            this.client.completeMultipartUpload(this.bucketName, objectKey, uploadId, partETags);
        }

        @Test
        @Ignore
        public void testPartSizeMoreThan5G() throws IOException {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            File file = createFile("test", 5 * 1024 * 1024 * 1024L + 1);
            boolean excepted = false;
            try {
                UploadPartResponse response = this.client
                        .uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, 1, file.length(),
                                new FileInputStream(file)));
            } catch (BceClientException bce) {
                excepted = true;
            } finally {
                file.delete();
            }
            assertThat(excepted, is(true));
        }
    }

    public static class ListPartsTest extends Base {

        @Test
        public void testOrdinary() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            List<String> eTags = Lists.newArrayList();
            for (int i = 0; i < 10; ++i) {
                eTags.add(this.client.uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, i + 1, i,
                        new ByteArrayInputStream(Strings.repeat("v", i).getBytes()))).getETag());
            }
            ListPartsResponse response = this.client.listParts(this.bucketName, "test", uploadId);
            assertThat(response.getBucketName(), is(this.bucketName));
            assertThat(response.isTruncated(), is(false));
            assertThat(response.getKey(), is("test"));
            assertThat(response.getMaxParts(), is(1000));
            assertThat(response.getNextPartNumberMarker(), is(10));
            assertThat(response.getOwner(), is(this.owner));
            assertThat(response.getPartNumberMarker(), is(0));
            assertThat(response.getUploadId(), is(uploadId));
            List<PartSummary> parts = response.getParts();
            assertThat(parts, hasSize(10));
            for (int i = 0; i < 10; ++i) {
                PartSummary part = parts.get(i);
                assertThat(part.getETag(), is(eTags.get(i)));
                assertThat(part.getPartNumber(), is(i + 1));
                assertThat(part.getSize(), is((long) i));
                assertThat(part.getLastModified(), is(TestUtils.timeGapInSecondsLessThan(this.createTime, 30)));
            }
        }

        @Test
        public void testMaxParts() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            List<String> eTags = Lists.newArrayList();
            for (int i = 0; i < 10; ++i) {
                eTags.add(this.client.uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, i + 1, i,
                        new ByteArrayInputStream(Strings.repeat("v", i).getBytes()))).getETag());
            }
            ListPartsResponse response =
                    this.client.listParts(new ListPartsRequest(this.bucketName, "test", uploadId).withMaxParts(3));
            assertThat(response.getBucketName(), is(this.bucketName));
            assertThat(response.isTruncated(), is(true));
            assertThat(response.getKey(), is("test"));
            assertThat(response.getMaxParts(), is(3));
            assertThat(response.getNextPartNumberMarker(), is(3));
            assertThat(response.getOwner(), is(this.owner));
            assertThat(response.getPartNumberMarker(), is(0));
            assertThat(response.getUploadId(), is(uploadId));
            List<PartSummary> parts = response.getParts();
            assertThat(parts, hasSize(3));
            for (int i = 0; i < 3; ++i) {
                PartSummary part = parts.get(i);
                assertThat(part.getETag(), is(eTags.get(i)));
                assertThat(part.getPartNumber(), is(i + 1));
                assertThat(part.getSize(), is((long) i));
                assertThat(part.getLastModified(), is(TestUtils.timeGapInSecondsLessThan(this.createTime, 30)));
            }
        }

        @Test
        public void testPartNumberMarker() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "test").getUploadId();
            List<String> eTags = Lists.newArrayList();
            for (int i = 0; i < 10; ++i) {
                eTags.add(this.client.uploadPart(new UploadPartRequest(this.bucketName, "test", uploadId, i + 1, i,
                        new ByteArrayInputStream(Strings.repeat("v", i).getBytes()))).getETag());
            }
            ListPartsResponse response = this.client.listParts(
                    new ListPartsRequest(this.bucketName, "test", uploadId).withPartNumberMarker(3));
            assertThat(response.getBucketName(), is(this.bucketName));
            assertThat(response.isTruncated(), is(false));
            assertThat(response.getKey(), is("test"));
            assertThat(response.getMaxParts(), is(1000));
            assertThat(response.getNextPartNumberMarker(), is(10));
            assertThat(response.getOwner(), is(this.owner));
            assertThat(response.getPartNumberMarker(), is(3));
            assertThat(response.getUploadId(), is(uploadId));
            List<PartSummary> parts = response.getParts();
            assertThat(parts, hasSize(7));
            for (int i = 0; i < 7; ++i) {
                PartSummary part = parts.get(i);
                assertThat(part.getETag(), is(eTags.get(i + 3)));
                assertThat(part.getPartNumber(), is(i + 1 + 3));
                assertThat(part.getSize(), is((long) (i + 3)));
                assertThat(part.getLastModified(), is(TestUtils.timeGapInSecondsLessThan(this.createTime, 30)));
            }
        }
    }

    public static class CompleteMultipartUploadTest extends Base {
        @Test
        public void testOrdinary() {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("text/plain");
            InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest
                    (this.bucketName, "test").withMetadata(objectMetadata);
            String uploadId = this.client.initiateMultipartUpload(initRequest).getUploadId();
            List<PartETag> partETags = Lists.newArrayList();
            for (int i = 0; i < 1; ++i) {
                int size = i + 5 * 1024 * 1024;
                String eTag = this.client.uploadPart(
                        new UploadPartRequest(this.bucketName, "test", uploadId, i + 1, size,
                                new ByteArrayInputStream(Strings.repeat("v", size).getBytes()))).getETag();
                partETags.add(new PartETag(i + 1, eTag));
            }
            objectMetadata = new ObjectMetadata();
            Map<String, String> userMetadata = new HashMap<String, String>();
            userMetadata.put("meta-key", "meta-value");
            objectMetadata.setUserMetadata(userMetadata);
            objectMetadata.setContentType("text/json");
            CompleteMultipartUploadRequest request =
                    new CompleteMultipartUploadRequest().withBucketName(this.bucketName).withKey("test")
                            .withUploadId(uploadId).withPartETags(partETags).withObjectMetadata(objectMetadata);
            CompleteMultipartUploadResponse response = this.client.completeMultipartUpload(request);
            assertThat(response.getBucketName(), is(this.bucketName));
            assertThat(response.getKey(), is("test"));
            assertThat(response.getETag(), notNullValue());
            assertThat(response.getLocation(), notNullValue());
            ObjectMetadata metadata = this.client.getObjectMetadata(bucketName, "test");
            assertThat(metadata.getContentType(), is("text/plain"));
//            String resultUserMeta = metadata.getUserMetadata().get("metaKey".toLowerCase());
            String resultUserMeta = metadata.getUserMetadata().get("meta-key");

            assertThat(resultUserMeta, is("meta-value"));
        }

        @Test
        public void TestMultiUploadWithStorageClass() throws IOException {
            String storageClass = "STANDARD";
            String objectName = "test";
            List<byte[]> parts = new ArrayList<byte[]>();
            byte[] part1 = new byte[5 * 1024 * 1024];
            for (int i = 0; i < part1.length; ++i) {
                part1[i] = (byte) (i & 0xff);
            }
            byte[] part2 = new byte[5 * 1024 * 1024];
            for (int i = 0; i < part2.length; ++i) {
                part2[i] = (byte) (i & 0xff);
            }
            byte[] part3 = new byte[1023];
            for (int i = 0; i < part3.length; ++i) {
                part3[i] = (byte) (i & 0xff);
            }
            parts.add(part1);
            parts.add(part2);
            parts.add(part3);
            InitiateMultipartUploadRequest iniReq = null;
            iniReq = new InitiateMultipartUploadRequest(bucketName, objectName).withStorageClass(storageClass);

            InitiateMultipartUploadResponse iniRes = client.initiateMultipartUpload(iniReq);
            String uploadId = iniRes.getUploadId();
            for (int i = 0; i < parts.size(); ++i) {
                UploadPartRequest req = new UploadPartRequest();
                req.setBucketName(bucketName);
                req.setPartNumber(i + 1);
                req.setKey(objectName);
                req.setUploadId(uploadId);
                InputStream input = new ByteArrayInputStream(parts.get(i));
                req.setInputStream(input);
                req.setPartSize(input.available());
                client.uploadPart(req);
            }
            ListPartsResponse res = client.listParts(bucketName, objectName, uploadId);
            assertThat(storageClass, is(res.getStorageClass()));
            List<PartETag> partETags = new ArrayList<PartETag>();
            for (PartSummary each : res.getParts()) {
                PartETag tmp = new PartETag();
                tmp.setETag(each.getETag());
                tmp.setPartNumber(each.getPartNumber());
                partETags.add(tmp);
            }
            client.completeMultipartUpload(bucketName, objectName, uploadId, partETags);
            ObjectMetadata meta = client.getObjectMetadata(bucketName, objectName);
            assertThat(storageClass, is(meta.getStorageClass()));
        }

        @Test
        public void TestMultiUploadWithStorageClassIA() throws IOException {
            String storageClass = "STANDARD_IA";
            String objectName = "test";
            List<byte[]> parts = new ArrayList<byte[]>();
            byte[] part1 = new byte[5 * 1024 * 1024];
            for (int i = 0; i < part1.length; ++i) {
                part1[i] = (byte) (i & 0xff);
            }
            byte[] part2 = new byte[5 * 1024 * 1024];
            for (int i = 0; i < part2.length; ++i) {
                part2[i] = (byte) (i & 0xff);
            }
            byte[] part3 = new byte[1023];
            for (int i = 0; i < part3.length; ++i) {
                part3[i] = (byte) (i & 0xff);
            }
            parts.add(part1);
            parts.add(part2);
            parts.add(part3);
            InitiateMultipartUploadRequest iniReq = null;
            iniReq = new InitiateMultipartUploadRequest(bucketName, objectName).withStorageClass(storageClass);

            InitiateMultipartUploadResponse iniRes = client.initiateMultipartUpload(iniReq);
            String uploadId = iniRes.getUploadId();
            for (int i = 0; i < parts.size(); ++i) {
                UploadPartRequest req = new UploadPartRequest();
                req.setBucketName(bucketName);
                req.setPartNumber(i + 1);
                req.setKey(objectName);
                req.setUploadId(uploadId);
                InputStream input = new ByteArrayInputStream(parts.get(i));
                req.setInputStream(input);
                req.setPartSize(input.available());
                client.uploadPart(req);
            }
            ListPartsResponse res = client.listParts(bucketName, objectName, uploadId);
            assertThat(storageClass, is(res.getStorageClass()));
            List<PartETag> partETags = new ArrayList<PartETag>();
            for (PartSummary each : res.getParts()) {
                PartETag tmp = new PartETag();
                tmp.setETag(each.getETag());
                tmp.setPartNumber(each.getPartNumber());
                partETags.add(tmp);
            }
            client.completeMultipartUpload(bucketName, objectName, uploadId, partETags);
            ObjectMetadata meta = client.getObjectMetadata(bucketName, objectName);
            assertThat(storageClass, is(meta.getStorageClass()));
        }
    }

    public static class AbortMultipartUploadTest extends Base {
        @Test
        public void testOrdinary() {
            String uploadId = this.client.initiateMultipartUpload(this.bucketName, "abortMultipartTest").getUploadId();
            for (int i = 0; i < 10; ++i) {
                this.client.uploadPart(new UploadPartRequest(this.bucketName, "abortMultipartTest", uploadId, i + 1, i,
                        new ByteArrayInputStream(Strings.repeat("v", i).getBytes()))).getETag();
            }
            List<MultipartUploadSummary> uploads =
                    this.client.listMultipartUploads(this.bucketName).getMultipartUploads();
            this.client.abortMultipartUpload(this.bucketName, "abortMultipartTest", uploadId);
            uploads = this.client.listMultipartUploads(this.bucketName).getMultipartUploads();
            assertThat(uploads, hasSize(0));
        }
    }

    @Ignore
    public static class ListMultipartUploadsBase extends Base {
        protected SortedMap<String, String> expectedUploadsId;
        protected List<String> expectedCommonPrefixes;
        protected String expectedDelimiter;
        protected String expectedKeyMarker;
        protected String expectedNextKeyMarker;
        protected boolean expectedTruncated;
        protected int expectedMaxUploads;
        protected String expectedPrefix;

        @Override
        @Before
        public void setUp() {
            super.setUp();
            this.expectedUploadsId = Maps.newTreeMap();
            this.expectedUploadsId.put("dir0/dir1/key0", "");
            this.expectedUploadsId.put("dir0/key0", "");
            this.expectedUploadsId.put("dir0/key1", "");
            this.expectedUploadsId.put("dir1/dir2/key1", "");
            this.expectedUploadsId.put("dir1/dir2/key2", "");
            this.expectedUploadsId.put("dir1/key0", "");
            this.expectedUploadsId.put("dir3/key0", "");
            this.expectedUploadsId.put("dir3/key1", "");
            this.expectedUploadsId.put("key", "");
            for (Map.Entry<String, String> entry : this.expectedUploadsId.entrySet()) {
                this.expectedUploadsId.put(entry.getKey(),
                        this.client.initiateMultipartUpload(this.bucketName, entry.getKey()).getUploadId());
            }
            this.expectedCommonPrefixes = null;
            this.expectedDelimiter = null;
            this.expectedKeyMarker = "";
            this.expectedNextKeyMarker = null;
            this.expectedTruncated = false;
            this.expectedPrefix = "";
            this.expectedMaxUploads = 1000;
        }

        protected void checkResponse(ListMultipartUploadsResponse response) {
            assertThat(response, hasProperty("bucketName", is(this.bucketName)));
            assertThat(response, hasProperty("commonPrefixes", is(this.expectedCommonPrefixes)));
            assertThat(response, hasProperty("keyMarker", is(this.expectedKeyMarker)));
//            assertThat(response, hasProperty("nextKeyMarker", is(this.expectedNextKeyMarker)));
            assertThat(response, hasProperty("delimiter", is(this.expectedDelimiter)));
            assertThat(response, hasProperty("truncated", is(this.expectedTruncated)));
            assertThat(response, hasProperty("maxUploads", is(this.expectedMaxUploads)));
            assertThat(response, hasProperty("prefix", is(this.expectedPrefix)));
            List<MultipartUploadSummary> multipartUploadSummaries = response.getMultipartUploads();
            assertThat(multipartUploadSummaries, hasSize(this.expectedUploadsId.size()));
            Iterator<MultipartUploadSummary> it = multipartUploadSummaries.iterator();
            for (Map.Entry<String, String> entry : this.expectedUploadsId.entrySet()) {
                MultipartUploadSummary multipartUploadSummary = it.next();
                assertThat(multipartUploadSummary, hasProperty("key", is(entry.getKey())));
                assertThat(multipartUploadSummary, hasProperty("owner", is(this.owner)));
                assertThat(multipartUploadSummary, hasProperty("uploadId", is(entry.getValue())));
                assertThat(multipartUploadSummary,
                        hasProperty("initiated", TestUtils.timeGapInSecondsLessThan(this.createTime, 30)));
            }
        }
    }

    public static class ListMultipartUploadsTest extends ListMultipartUploadsBase {
        @Test
        public void testOrdinary() {
            this.checkResponse(this.client.listMultipartUploads(this.bucketName));
        }

        @Test
        public void testPrefix() {
            this.expectedUploadsId.remove("key");
            this.expectedPrefix = "d";
            this.checkResponse(this.client.listMultipartUploads(new ListMultipartUploadsRequest(this.bucketName)
                    .withPrefix("d")));
        }

        @Test
        public void testKeyMarker() {
            this.expectedUploadsId = this.expectedUploadsId.subMap("dir1", "z");
            this.expectedKeyMarker = "dir1";
            this.checkResponse(this.client.listMultipartUploads(new ListMultipartUploadsRequest(this.bucketName)
                    .withKeyMarker("dir1")));
        }

        @Test
        public void testDelimiter() {
            String tempUploadId = this.expectedUploadsId.get("dir0/key0");
            this.expectedUploadsId.clear();
            this.expectedUploadsId.put("dir0/key0", tempUploadId);

            this.expectedCommonPrefixes = Lists.newArrayList();
            this.expectedCommonPrefixes.add("dir0/dir1/");
            this.expectedDelimiter = "/";
            this.expectedMaxUploads = 2;
            this.expectedTruncated = true;
            this.expectedNextKeyMarker = "dir0/key0";
            this.expectedPrefix = "dir0/";
            this.checkResponse(
                    this.client.listMultipartUploads(
                            new ListMultipartUploadsRequest(
                                    this.bucketName, "dir0/").withDelimiter("/").withMaxUploads(2)));

        }

        @Test
        public void testMaxUploads() {
            this.expectedUploadsId = this.expectedUploadsId.subMap("dir0", "dir1/dir2/key1");
            this.expectedTruncated = true;
            this.expectedMaxUploads = 3;
            this.checkResponse(this.client.listMultipartUploads(new ListMultipartUploadsRequest(this.bucketName)
                    .withMaxUploads(3)));
        }

        @Test
        public void testNegativeMaxUploads() {
            this.checkResponse(this.client.listMultipartUploads(new ListMultipartUploadsRequest(this.bucketName)
                    .withMaxUploads(-1)));
        }

        @Test
        public void testCombination() {
            this.expectedUploadsId = this.expectedUploadsId.subMap("dir1", "dir3/key0");
            this.expectedTruncated = true;
            this.expectedKeyMarker = "dir1";
            this.expectedMaxUploads = 3;
            this.expectedPrefix = "d";
            this.checkResponse(this.client.listMultipartUploads(new ListMultipartUploadsRequest(this.bucketName)
                    .withPrefix("d").withKeyMarker("dir1").withMaxUploads(3)));
        }

        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.listMultipartUploads((ListMultipartUploadsRequest) null);
        }
    }

    @Ignore
    public static class GeneratePresignedUrlBase extends Base {
        protected SortedMap<String, String> expectedContents;
        protected List<String> expectedCommonPrefixes;
        protected String expectedDelimiter;
        protected String expectedMarker;
        protected String expectedNextMarker;
        protected boolean expectedTruncated;
        protected int expectedMaxKeys;
        protected String expectedPrefix;

        @Override
        @Before
        public void setUp() {
            super.setUp();
            this.expectedContents = Maps.newTreeMap();
            this.expectedContents.put("dir0/dir1/key0", Strings.repeat("v", 1));
            this.expectedContents.put("dir0/key0", Strings.repeat("v", 2));
            this.expectedContents.put("dir0/key1", Strings.repeat("v", 3));
            this.expectedContents.put("dir1/dir2/key1", Strings.repeat("v", 4));
            this.expectedContents.put("dir1/dir2/key2", Strings.repeat("v", 5));
            this.expectedContents.put("dir1/key0", Strings.repeat("v", 6));
            this.expectedContents.put("dir3/key0", Strings.repeat("v", 7));
            this.expectedContents.put("dir3/key1", Strings.repeat("v", 8));
            this.expectedContents.put("dir4/key0", Strings.repeat("v", 9));
            this.expectedContents.put("key", Strings.repeat("v", 10));

            for (Map.Entry<String, String> entry : this.expectedContents.entrySet()) {
                this.client.putObject(this.bucketName, entry.getKey(), entry.getValue());
            }
            this.expectedCommonPrefixes = null;
            this.expectedDelimiter = null;
            this.expectedMarker = "";
            this.expectedNextMarker = null;
            this.expectedTruncated = false;
            this.expectedPrefix = "";
            this.expectedMaxKeys = 1000;
        }

        protected void checkResponse(ListObjectsResponse response) {
            assertThat(response, hasProperty("bucketName", is(this.bucketName)));
            assertThat(response, hasProperty("commonPrefixes", is(this.expectedCommonPrefixes)));
            assertThat(response, hasProperty("delimiter", is(this.expectedDelimiter)));
            assertThat(response, hasProperty("marker", is(this.expectedMarker)));
//            assertThat(response, hasProperty("nextMarker", is(this.expectedNextMarker)));
            assertThat(response, hasProperty("truncated", is(this.expectedTruncated)));
            assertThat(response, hasProperty("maxKeys", is(this.expectedMaxKeys)));
            assertThat(response, hasProperty("prefix", is(this.expectedPrefix)));
            List<BosObjectSummary> objects = response.getContents();
            assertThat(objects, hasSize(this.expectedContents.size()));
            Iterator<BosObjectSummary> it = objects.iterator();
            for (Map.Entry<String, String> entry : this.expectedContents.entrySet()) {
                BosObjectSummary object = it.next();
                assertThat(object, hasProperty("key", is(entry.getKey())));
                assertThat(object, hasProperty("owner", is(this.owner)));
                assertThat(object, hasProperty("size", is((long) entry.getValue().length())));
                assertThat(object,
                        hasProperty("lastModified", TestUtils.timeGapInSecondsLessThan(this.createTime, 30)));
                try {
                    assertThat(object, hasProperty("ETag", is(Hex.encodeHexString(HashUtils
                            .computeMd5Hash(new ByteArrayInputStream(entry.getValue().getBytes()))))));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class GeneratePresignedUrlTest extends GeneratePresignedUrlBase {
        @Test(expected = NullPointerException.class)
        public void testNullRequest() {
            this.client.generatePresignedUrl((GeneratePresignedUrlRequest) null);
        }

        @Test
        public void testOrdinary() {
            String objectKey = "test";
            String value = "value1" + "\n" + "value2";
            this.client.putObject(this.bucketName, objectKey, value);
            GeneratePresignedUrlRequest request =
                    new GeneratePresignedUrlRequest(this.bucketName, objectKey, HttpMethodName.GET);
            request.setResponseHeaders(new ResponseHeaderOverrides());
            request.setExpiration(1800);
            URL url = this.client.generatePresignedUrl(request);
            BufferedReader bufferedReader = null;
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                if ((inputLine = bufferedReader.readLine()) != null) {
                    result.append(inputLine);
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        result.append("\n" + inputLine);
                    }
                }
                assertThat(result.toString(), is(value));
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Test
        public void testPutBucketAcl() {
            List<Grant> grants = new ArrayList<Grant>();
            List<Grantee> grantee = new ArrayList<Grantee>();
            grantee.add(this.grantee);
            List<Permission> permission = new ArrayList<Permission>();
            permission.add(Permission.FULL_CONTROL);
            grants.add(new Grant(grantee, permission));
            for (Grant grant : grants) {
                assertThat(this.client.getBucketAcl(this.bucketName).getAccessControlList(),
                        hasItem(new SamePropertyValuesAs(grant)));
            }
            GeneratePresignedUrlRequest request =
                    new GeneratePresignedUrlRequest(this.bucketName, null, HttpMethodName.PUT);
            request.addRequestParameter("acl", null);
            request.addRequestHeaders("x-bce-acl", "public-read-write");
            request.setExpiration(1800);
            URL url = this.client.generatePresignedUrl(request);
            BufferedReader bufferedReader = null;
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("x-bce-acl", "public-read-write");
                connection.connect();
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            List<Grant> expectedGrants = new ArrayList<Grant>();
            List<Grantee> granteeOwner = new ArrayList<Grantee>();
            granteeOwner.add(this.grantee);
            List<Permission> permissionOwner = new ArrayList<Permission>();
            permissionOwner.add(Permission.FULL_CONTROL);
            expectedGrants.add(new Grant(granteeOwner, permissionOwner));
            List<Grantee> granteeAnonymous = new ArrayList<Grantee>();
            granteeAnonymous.add(this.anonymous);
            List<Permission> permissionAnonymous = new ArrayList<Permission>();
            permissionAnonymous.add(Permission.READ);
            permissionAnonymous.add(Permission.WRITE);
            expectedGrants.add(new Grant(granteeAnonymous, permissionAnonymous));
            GetBucketAclResponse response = this.client.getBucketAcl(this.bucketName);
            assertThat(response.getAccessControlList(), hasSize(expectedGrants.size()));
            for (Grant grant : grants) {
                assertThat(response.getAccessControlList(), hasItem(new SamePropertyValuesAs(grant)));
            }

        }

        @Test
        public void testListMultipart() {
            String objectKey = "test";
            this.client.initiateMultipartUpload(this.bucketName, objectKey);
            GeneratePresignedUrlRequest request =
                    new GeneratePresignedUrlRequest(bucketName, null, HttpMethodName.PUT);
            request.addRequestParameter("uploads", null);
            request.setExpiration(1800);
            URL url = this.client.generatePresignedUrl(request);
            BufferedReader bufferedReader = null;
            try {
                URLConnection urlConnection = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                if ((inputLine = bufferedReader.readLine()) != null) {
                    ListMultipartUploadsResponse response =
                            JsonUtils.fromJsonString(inputLine, ListMultipartUploadsResponse.class);
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Test
        public void testParameter() {
            this.expectedContents = this.expectedContents.subMap("dir1", "dir3/key0");
            this.expectedTruncated = true;
            this.expectedMarker = "dir1";
            this.expectedMaxKeys = 3;
            this.expectedPrefix = "d";

            GeneratePresignedUrlRequest request =
                    new GeneratePresignedUrlRequest(this.bucketName, null, HttpMethodName.GET);
            request.addRequestParameter("maxKeys", "3");
            request.addRequestParameter("prefix", "d");
            request.addRequestParameter("marker", "dir1");
            URL url = this.client.generatePresignedUrl(request);
            BufferedReader bufferedReader = null;
            try {
                URLConnection urlConnection = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                if ((inputLine = bufferedReader.readLine()) != null) {
                    ListObjectsResponse response = JsonUtils.fromJsonString(inputLine, ListObjectsResponse.class);
                    this.checkResponse(response);
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Test
        public void testResponseOverrides() {
            String bucketName = this.bucketName + "-other";
            String objectKey = "test";
            String value = "value" + System.currentTimeMillis();
            this.client.createBucket(bucketName);
            this.client.putObject(bucketName, objectKey, value);
            GeneratePresignedUrlRequest request =
                    new GeneratePresignedUrlRequest(bucketName, objectKey, HttpMethodName.GET);
            request.setResponseHeaders(
                    new ResponseHeaderOverrides().withContentDisposition("attachment;filename=name.txt")
                            .withCacheControl("no-cache").withContentEncoding("utf-8")
                            .withContentLanguage("zh-CN").withContentType("text/plain").withExpires("1"));
            URL url = this.client.generatePresignedUrl(request);
            BufferedReader bufferedReader = null;
            try {
                URLConnection urlConnection = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                if ((inputLine = bufferedReader.readLine()) != null) {
                    assertThat(inputLine, is(value));
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.client.deleteObject(bucketName, objectKey);
            this.client.deleteBucket(bucketName);
        }

        @Test
        public void testPublicRead() throws IOException, NoSuchAlgorithmException {
            String objectKey = "test";
            String value = "value" + System.currentTimeMillis();
            this.client.putObject(this.bucketName, objectKey, value);
            this.client.setBucketAcl(this.bucketName, CannedAccessControlList.PublicRead);
            BosClient bosAnonymous = new BosClient(new BosClientConfiguration().withEndpoint(ENDPOINT));
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(this.bucketName, objectKey);
            request.setExpiration(300);
            request.setContentType("text/plain");
            request.setContentMd5(Hex.encodeHexString(HashUtils
                    .computeMd5Hash(new ByteArrayInputStream(value.getBytes()))));
            URL url = bosAnonymous.generatePresignedUrl(request);
            BufferedReader bufferedReader = null;
            try {
                URLConnection urlConnection = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                if ((inputLine = bufferedReader.readLine()) != null) {
                    assertThat(inputLine, is(value));
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static File createFile(String fileName, long length) throws IOException {
        File file = new File(fileName);
        RandomAccessFile r = null;
        try {
            r = new RandomAccessFile(file, "rw");
            r.setLength(length);
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

}
