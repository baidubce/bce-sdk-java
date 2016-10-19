package com.baidubce.services.sms;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SmsConf;
import com.baidubce.services.sms.model.CreateTemplateRequest;
import com.baidubce.services.sms.model.CreateTemplateResponse;
import com.baidubce.services.sms.model.DeleteTemplateRequest;
import com.baidubce.services.sms.model.GetTemplateDetailRequest;
import com.baidubce.services.sms.model.GetTemplateDetailResponse;
import com.baidubce.services.sms.model.ListTemplateResponse;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.UpdateTemplateRequest;
import com.baidubce.services.sms.model.UpdateTemplateResponse;

public class SmsClientDeleteTemplateNorQaTest {
    private SmsClient client;
    private SmsInternalClient internalClient;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws InterruptedException {
        this.client = new SmsClient(
                new SmsClientConfiguration()
                        .withCredentials(
                                new DefaultBceCredentials(SmsConf.SMS_AK,
                                        SmsConf.SMS_SK)).withEndpoint(SmsConf.SMS_ENDPOINT));
        this.internalClient = new SmsInternalClient(
                new SmsClientConfiguration()
                        .withCredentials(
                                new DefaultBceCredentials(SmsConf.SMS_AK,
                                        SmsConf.SMS_SK)).withEndpoint(SmsConf.SMS_ENDPOINT));
        ListTemplateResponse lResponse = this.client
                .listTemplate(new SmsRequest());
        Thread.sleep(1000);
        List<GetTemplateDetailResponse> templateList = lResponse
                .getTemplateList();
        for (GetTemplateDetailResponse template : templateList) {
            if (template.getName().equals("x")) {
                this.client.deleteTemplate(new DeleteTemplateRequest()
                        .withTemplateId(template.getTemplateId()));
            }
        }
    }

    @After
    public void tearDown() {
        this.client.shutdown();
        this.internalClient.shutdown();
    }

    @Test
    public void testDeleteProcessingTemplate() throws BceServiceException {
        thrown.expect(BceServiceException.class);
        thrown.expectMessage("no records in database");
        String templateId = null;

        try {
            // 创建模板
            CreateTemplateResponse cResponse = this.client
                    .createTemplate(new CreateTemplateRequest().withName("x")
                            .withContent("y"));
            templateId = cResponse.getData().getTemplateId();
            // 确认模板创建成功
            GetTemplateDetailResponse gResponse = this.client
                    .getTemplateDetail(new GetTemplateDetailRequest()
                            .withTemplateId(templateId));
            Assert.assertEquals(templateId, gResponse.getTemplateId());
            // 删除模板
            DeleteTemplateRequest dRequest = new DeleteTemplateRequest();
            dRequest.setTemplateId(templateId);
            this.client.deleteTemplate(dRequest);
        } catch (BceServiceException e) {
            Assert.assertTrue("Case data prepare fail", false);
        }
        // 确认模板已删除（无法获取已删除模板的详情）
        this.client.getTemplateDetail(new GetTemplateDetailRequest()
                .withTemplateId(templateId));
    }

    @Test
    public void testDeleteValidTemplate() throws BceServiceException {
        thrown.expect(BceServiceException.class);
        thrown.expectMessage("no records in database");
        String templateId = null;

        try {
            // 创建模板
            CreateTemplateResponse cResponse = this.client
                    .createTemplate(new CreateTemplateRequest().withName("x")
                            .withContent("y"));
            templateId = cResponse.getData().getTemplateId();
            // 更新模板
            UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                    .withTemplateId(templateId);
            uRequest.setStatus("VALID");
            UpdateTemplateResponse uResponse = this.internalClient
                    .updateTemplate(uRequest);

            // 确认模板状态为VALID
            GetTemplateDetailResponse gResponse = this.client
                    .getTemplateDetail(new GetTemplateDetailRequest()
                            .withTemplateId(templateId));
            Assert.assertEquals("VALID", gResponse.getStatus());
            // 删除模板
            DeleteTemplateRequest dRequest = new DeleteTemplateRequest();
            dRequest.setTemplateId(templateId);
            this.client.deleteTemplate(dRequest);
        } catch (BceServiceException e) {
            Assert.assertTrue("Case data prepare fail", false);
        }
        // 确认模板已删除（无法获取已删除模板的详情）
        this.client.getTemplateDetail(new GetTemplateDetailRequest()
                .withTemplateId(templateId));
    }

    @Test
    public void testDeleteInvalidTemplate() throws BceServiceException {
        thrown.expect(BceServiceException.class);
        thrown.expectMessage("no records in database");
        String templateId = null;

        try {
            // 创建模板
            CreateTemplateResponse cResponse = this.client
                    .createTemplate(new CreateTemplateRequest().withName("x")
                            .withContent("y"));
            templateId = cResponse.getData().getTemplateId();
            // 更新模板
            UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                    .withTemplateId(templateId);
            uRequest.setStatus("INVALID");
            UpdateTemplateResponse uResponse = this.internalClient
                    .updateTemplate(uRequest);

            // 确认模板状态为VALID
            GetTemplateDetailResponse gResponse = this.client
                    .getTemplateDetail(new GetTemplateDetailRequest()
                            .withTemplateId(templateId));
            Assert.assertEquals("INVALID", gResponse.getStatus());
            // 删除模板
            DeleteTemplateRequest dRequest = new DeleteTemplateRequest();
            dRequest.setTemplateId(templateId);
            this.client.deleteTemplate(dRequest);
        } catch (BceServiceException e) {
            Assert.assertTrue("Case data prepare fail", false);
        }
        // 确认模板已删除（无法获取已删除模板的详情）
        this.client.getTemplateDetail(new GetTemplateDetailRequest()
                .withTemplateId(templateId));
    }

}
