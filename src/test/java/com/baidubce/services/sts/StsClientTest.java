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
package com.baidubce.services.sts;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceServiceException;
import com.baidubce.TestUtils;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.auth.DefaultBceSessionCredentials;
import com.baidubce.services.sts.model.GetSessionTokenRequest;
import com.baidubce.services.sts.model.GetSessionTokenResponse;
import com.google.common.base.Objects;
import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

@RunWith(Enclosed.class)
public class StsClientTest {

    @Ignore
    public static class Base {
        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static String ENDPOINT = "http://10.107.37.40:8586";

        protected StsClient client;
        protected BceClientConfiguration config;

        @Before
        public void setUp() {
            this.config = new BceClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials("d154df3e2ac44e53b566db53ec644a7e",
                    "2e8445d62bc84011991b4306b91f19f8"));
            this.config.setEndpoint(ENDPOINT);
            this.client = new StsClient(this.config);
        }

        protected void expectBceServiceException(final int statusCode, final String errorCode) {
            this.expectBceServiceException(statusCode, errorCode, null);
        }

        protected void expectBceServiceException(
                final int statusCode, final String errorCode, final String errorMessage) {
            this.thrown.expect(new CustomMatcher<Throwable>(
                    "BceServiceException [ statusCode=" + statusCode + ", errorCode=" + errorCode + "]") {
                @Override
                public boolean matches(Object item) {
                    if (!(item instanceof BceServiceException)) {
                        return false;
                    }
                    BceServiceException bceServiceException = (BceServiceException) item;
                    return bceServiceException.getStatusCode() == statusCode
                            && Objects.equal(bceServiceException.getErrorCode(), errorCode)
                            && (errorMessage == null || errorMessage.equals(bceServiceException.getErrorMessage()));
                }
            });
        }
    }

    public static class GetSessionTokenTest extends Base {
        @Test
        public void testOrdinary() {
            GetSessionTokenResponse response = this.client.getSessionToken();
            assertThat(response, hasProperty("accessKeyId"));
            assertThat(response, hasProperty("secretAccessKey"));
            assertThat(response, hasProperty("sessionToken"));
            assertThat(response, hasProperty("expiration", TestUtils.timeGapInSecondsGreaterThan(new Date(), 1500)));
        }

        @Test
        public void testDurationSeconds() {
            GetSessionTokenResponse response =
                    this.client.getSessionToken(new GetSessionTokenRequest().withDurationSeconds(10));
            assertThat(response, hasProperty("accessKeyId"));
            assertThat(response, hasProperty("secretAccessKey"));
            assertThat(response, hasProperty("sessionToken"));
            assertThat(response, hasProperty("expiration", TestUtils.timeGapInSecondsLessThan(new Date(), 30)));
        }

        @Test
        public void testAcl() {
            GetSessionTokenResponse response =
                    this.client.getSessionToken(new GetSessionTokenRequest().withAcl(""
                            + "{"
                            + "    \"id\": \"test\","
                            + "    \"accessControlList\": ["
                            + "        {"
                            + "            \"eid\": \"e0\","
                            + "            \"service\": \"bce:bos\","
                            + "            \"region\": \"bj\","
                            + "            \"effect\": \"Allow\","
                            + "            \"resource\": [\"test-bucket/*\"],"
                            + "            \"permission\": [\"READ\"]"
                            + "        }"
                            + "    ]"
                            + "}"));
            assertThat(response, hasProperty("accessKeyId"));
            assertThat(response, hasProperty("secretAccessKey"));
            assertThat(response, hasProperty("sessionToken"));
            assertThat(response, hasProperty("expiration"));
        }

        @Test
        public void testEmptyAcl() {
            this.expectBceServiceException(400, "InvalidRequest");
            this.client.getSessionToken(new GetSessionTokenRequest().withAcl("{}"));
        }

        @Test
        public void testInvalidAcl() {
            this.expectBceServiceException(400, "InvalidRequestBody");
            this.client.getSessionToken(new GetSessionTokenRequest().withAcl("{"));
        }

        @Test
        public void testTemporaryCredential() {
            GetSessionTokenResponse response = this.client.getSessionToken();
            BceCredentials tempCredentials = new DefaultBceSessionCredentials(
                    response.getAccessKeyId(),
                    response.getSecretAccessKey(),
                    response.getSessionToken());
            this.expectBceServiceException(400, "InvalidRequest", "not support temporal credential");
            this.client.getSessionToken(new GetSessionTokenRequest().withRequestCredentials(tempCredentials));
        }
    }
}
