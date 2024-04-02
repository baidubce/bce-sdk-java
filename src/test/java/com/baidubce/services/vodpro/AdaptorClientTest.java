package com.baidubce.services.vodpro;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vodpro.model.adaptor.RequestType;
import com.baidubce.services.vodpro.model.adaptor.response.QueryVcaResponse;
import com.baidubce.services.vodpro.model.adaptor.response.QueryVcrResponse;
import com.baidubce.services.vodpro.model.adaptor.response.TaskStartResponse;
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
 * Created on 17/10/10
 *
 * @author liumin08
 */
@RunWith(Enclosed.class)
public class AdaptorClientTest {

    @Ignore
    public static class Base {
        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final String AK = "your ak";
        protected static final String SK = "your sk";
        protected static final String ENDPOINT = "http://10.107.43.49:8494";

        protected AdaptorClient adaptorClient;

        @Before
        public void setUp() {
            BceClientConfiguration config = new BceClientConfiguration()
                    .withCredentials(new DefaultBceCredentials(AK, SK))
                    .withEndpoint(ENDPOINT).withSocketTimeoutInMillis(120 * 1000);
            adaptorClient = new AdaptorClient(config);
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

    public static class TaskTest extends AdaptorClientTest.Base {
        @Test
        public void startTaskTest() {
            TaskStartResponse response = adaptorClient.startTask(RequestType.vca,
                    "http://ftsummerer.bos.qasandbox.bcetest.baidu.com/test2.mp4",
                    "description_vcr", null);
        }

        @Test
        public void queryVcaTest() {
            QueryVcaResponse vcaResult = adaptorClient.queryAdaptorVca(
                    "http://ftsummerer.bos.qasandbox.bcetest.baidu.com/test2.mp4");
            assertThat(vcaResult, hasProperty("url"));
            assertThat(vcaResult, hasProperty("description"));
            assertThat(vcaResult, hasProperty("results"));
        }

        @Test
        public void queryVcrTest() {
            QueryVcrResponse vcrResult = adaptorClient.queryAdaptorVcr(
                    "http://jin-test.bj.bcebos.com/media/3min_4.mp4");
            assertThat(vcrResult, hasProperty("code"));
            assertThat(vcrResult, hasProperty("msg"));
            assertThat(vcrResult, hasProperty("result"));
        }
    }
}
