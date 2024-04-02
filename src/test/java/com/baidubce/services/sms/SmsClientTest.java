package com.baidubce.services.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.model.CreateTemplateRequest;
import com.baidubce.services.sms.model.CreateTemplateResponse;
import com.baidubce.services.sms.model.DeleteTemplateRequest;
import com.baidubce.services.sms.model.GetTemplateDetailRequest;
import com.baidubce.services.sms.model.GetTemplateDetailResponse;
import com.baidubce.services.sms.model.ListTemplateResponse;
import com.baidubce.services.sms.model.QueryMessageDetailRequest;
import com.baidubce.services.sms.model.QueryMessageDetailResponse;
import com.baidubce.services.sms.model.QueryQuotaResponse;
import com.baidubce.services.sms.model.SendMessageRequest;
import com.baidubce.services.sms.model.SendMessageResponse;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.StatReceiverRequest;
import com.baidubce.services.sms.model.StatReceiverResponse;
import com.baidubce.services.sms.model.TemplateStatus;
import com.baidubce.services.sms.model.UpdateQuotaRequest;
import com.baidubce.services.sms.model.UpdateTemplateRequest;
import com.google.common.base.Objects;

@RunWith(Enclosed.class)
public class SmsClientTest {

    @Ignore
    public static class Base {
        protected static final String ENDPOINT = "http://nmg02-bce-test8.nmg02.baidu.com:8887";
        protected static final String ACCESSKEY_ID = "11f9634810b743ad9255266b064ddba1";
        protected static final String SERCERT_KEY = "d5b8dd75acca40f8bb6cd75269305a1c";

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected SmsClient client;
        protected SmsInternalClient internalClient;

        @Before
        public void setUp() {
            this.client = new SmsClient(new SmsClientConfiguration().withCredentials(
                    new DefaultBceCredentials(ACCESSKEY_ID, SERCERT_KEY)).withEndpoint(ENDPOINT));
            this.internalClient =
                    new SmsInternalClient(new SmsClientConfiguration().withCredentials(
                            new DefaultBceCredentials(ACCESSKEY_ID, SERCERT_KEY)).withEndpoint(ENDPOINT));
        }

        @After
        public void tearDown() {
            this.client.shutdown();
            this.internalClient.shutdown();
        }

        protected void expectBceServiceException(final int statusCode, final String errorCode) {
            this.thrown.expect(new CustomMatcher<Throwable>(
                    "BceServiceException [ statusCode=" + statusCode + ", errorCode="
                            + errorCode + "]") {
                @Override
                public boolean matches(Object item) {
                    return (item instanceof BceServiceException)
                            && ((BceServiceException) item).getStatusCode() == statusCode
                            && Objects.equal(((BceServiceException) item).getErrorCode(), errorCode);
                }
            });
        }
    }

    public static class MessageTest extends Base {
        private static ThreadLocal<String> localMessageId = new ThreadLocal<String>();

        @Test
        public void testSendMessage() {
            // create template
            CreateTemplateResponse createResponse = this.client
                    .createTemplate(new CreateTemplateRequest().withName(
                            UUID.randomUUID().toString().substring(0, 32)).withContent("test${KEY}"));
            // update
            UpdateTemplateRequest updateRequest = new UpdateTemplateRequest()
                    .withTemplateId(createResponse.getData().getTemplateId());
            updateRequest.setName(UUID.randomUUID().toString().substring(0, 32));
            updateRequest.setContent("test${KEY}");
            updateRequest.setStatus(TemplateStatus.VALID.getValue());
            this.internalClient.updateTemplate(updateRequest);
            // send message
            System.out.println("--------->" + createResponse.getData().getTemplateId());
            SendMessageRequest request = new SendMessageRequest();
            request.setTemplateId(createResponse.getData().getTemplateId());
            request.setContentVar("{\"KEY\" : \"val1\"}");
            List<String> receivers = new ArrayList<String>();
            receivers.add("13589878678");
            request.setReceiver(receivers);
            SendMessageResponse sendResponse = this.client.sendMessage(request);
            localMessageId.set(sendResponse.getMessageId());
            Assert.assertTrue(sendResponse.getMessageId() != null
                    && sendResponse.getMessageId().length() > 0);
        }

        @Test
        public void testQueryMessageDetail() {
            testSendMessage();

            QueryMessageDetailResponse querResponse = this.client
                    .queryMessageDetail(new QueryMessageDetailRequest()
                            .withMessageId(localMessageId.get()));

            Assert.assertTrue(querResponse.getMessageId().equals(localMessageId.get()));
        }
    }

    public static class TemplateTest extends Base {
        private static ThreadLocal<String> localTemplateId = new ThreadLocal<String>();

        @Test
        public void testCreateTemplate() {
            CreateTemplateResponse response = this.client
                    .createTemplate(new CreateTemplateRequest().withName(
                            UUID.randomUUID().toString().substring(0, 32)).withContent("test${KEY}"));

            System.out.println(localTemplateId.get());
            localTemplateId.set(response.getData().getTemplateId());

            Assert.assertTrue(response.getData() != null
                    && response.getData().getTemplateId().length() > 0);
        }

        @Test
        public void testUpdateTemplate() {
            testCreateTemplate();

            String randomTemplateName = UUID.randomUUID().toString().substring(0, 32);

            // update
            UpdateTemplateRequest updateRequest = new UpdateTemplateRequest()
                    .withTemplateId(localTemplateId.get());
            updateRequest.setName(randomTemplateName);
            updateRequest.setContent("test${KEY}");
            updateRequest.setStatus(TemplateStatus.VALID.getValue());
            this.internalClient.updateTemplate(updateRequest);

            // get
            GetTemplateDetailResponse getResponse = this.client
                    .getTemplateDetail(new GetTemplateDetailRequest()
                            .withTemplateId(localTemplateId.get()));

            Assert.assertTrue(getResponse.getName().equals(randomTemplateName));
        }

        @Ignore
        public void testDeleteTemplate() {
            expectBceServiceException(400, "ParamError");
            // first to create a template
            testCreateTemplate();
            // second to delete it
            this.client.deleteTemplate(new DeleteTemplateRequest()
                    .withTemplateId(localTemplateId.get()));
            // last to get it then throw no records in database exception
            this.client.getTemplateDetail(new GetTemplateDetailRequest()
                    .withTemplateId(localTemplateId.get()));
        }

        @Test
        public void testGetTemplateDetail() {
            // first to create a template
            testCreateTemplate();
            // second to get detail of the template
            GetTemplateDetailResponse response = this.client
                    .getTemplateDetail(new GetTemplateDetailRequest()
                            .withTemplateId(localTemplateId.get()));

            Assert.assertTrue(response.getTemplateId().equals(localTemplateId.get()));
        }

        @Test
        public void testListTemplate() {
            // first to create a template
            testCreateTemplate();
            // second to list
            ListTemplateResponse response = this.client.listTemplate(new SmsRequest());

            Assert.assertTrue(response.getTemplateList().size() > 0);
        }
    }

    public static class QuotaTest extends Base {
        @Test
        public void testUpdateQuota() {
            // first to update
            this.internalClient.updateQuota(new UpdateQuotaRequest(1000, 100));

            // second to query
            QueryQuotaResponse response = this.client.queryQuota(new SmsRequest());
            Assert.assertTrue(response.getMaxReceivePerPhoneNumberDay() == 100);
        }

        @Test
        public void testQueryQuota() {
            // first to update
            testUpdateQuota();

            // second to query
            QueryQuotaResponse response = this.client.queryQuota(new SmsRequest());
            Assert.assertTrue(response.getMaxSendPerDay() == 1000);
        }
    }

    public static class StatTest extends Base {
        @Test
        public void testStatReceiver() {
            StatReceiverResponse response = this.client.statReceiver(new StatReceiverRequest()
                    .withPhoneNumber("13000000000"));
            Assert.assertTrue(response.getMaxReceivePerPhoneNumberDay() == 100);
        }
    }

}
