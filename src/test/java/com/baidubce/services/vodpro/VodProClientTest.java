package com.baidubce.services.vodpro;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vodpro.model.response.GetVcaResultResponse;
import com.baidubce.services.vodpro.model.response.GetVcrResultResponse;
import com.baidubce.services.vodpro.model.response.MediaResponse;
import com.google.common.base.Objects;
import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

/**
 * Created on 17/9/4
 *
 * @author liumin08
 */
@RunWith(Enclosed.class)
public class VodProClientTest {

    @Ignore
    public static class Base {
        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final String AK = "";
        protected static final String SK = "";
        protected static final String ENDPOINT = "http://10.107.43.49:8181";

        protected VodproClient vodproClient;

        @Before
        public void setUp() {
            BceClientConfiguration config = new BceClientConfiguration()
                    .withCredentials(new DefaultBceCredentials(AK, SK))
                    .withEndpoint(ENDPOINT).withSocketTimeoutInMillis(120 * 1000);
            vodproClient = new VodproClient(config);
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

    public static class MediaTest extends Base {
        @Test
        public void createMediaTest() {
            MediaResponse mediaCreateResponse = this.vodproClient.createMedia("test",
                    "test", "test_jpg/test.jpg", "client_test", "notification_name", "description");
            this.expectBceServiceException(404, "NoSuchProject");
        }

        @Test
        public void queryMediaTest() {
            MediaResponse response =
                    this.vodproClient.getMedia("liumin", "liumin", "test_jpg/test.jpg");
            assertThat(response, hasProperty("triggerName"));
            assertThat(response, hasProperty("projectId"));
            assertThat(response, hasProperty("spaceId"));
        }

        @Test
        public void queryMediaVcaTest() {
            GetVcaResultResponse vcaResult =
                    this.vodproClient.getVcaResult("liumin", "liumin", "test_jpg/test.jpg", "vca");
            assertThat(vcaResult, hasProperty("source"));
            assertThat(vcaResult, hasProperty("results"));
        }

        @Test
        public void queryMediaVcrTest() {
            GetVcrResultResponse vcrResult =
                    this.vodproClient.getVcrResult("liumin", "liumin", "test_jpg/test.jpg", "vcr");
            assertThat(vcrResult, hasProperty("source"));
            assertThat(vcrResult, hasProperty("results"));
        }
    }
}
