package com.baidubce.services.sms;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

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

@RunWith(Parameterized.class)
public class SmsClientDeleteTemplateAbnorQaTest {
    private SmsClient client;

    @Parameter(0)
    public int tag;
    @Parameter(1)
    public String inTemplateId;
    @Parameter(2)
    public String expMessage;
    @Parameter(3)
    public String expException;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1, null, "object templateId should not be null or empty.", "java.lang.IllegalArgumentException"},
                {2, "", "object templateId should not be null or empty.", "java.lang.IllegalArgumentException"},
                {3, " ", "object templateId should not be null or empty.", "java.lang.IllegalArgumentException"},
                {4, "smsTpl:notExistId944df8a9833153babc5930", "template does not exist",
                 "com.baidubce.BceServiceException"},
                {5, "deletedId", "template does not exist", "com.baidubce.BceServiceException"}});
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws InterruptedException {
        this.client = new SmsClient(
                new SmsClientConfiguration()
                        .withCredentials(
                                new DefaultBceCredentials(SmsConf.SMS_AK,
                                        SmsConf.SMS_SK)).withEndpoint(SmsConf.SMS_ENDPOINT));
        ListTemplateResponse lResponse = this.client
                .listTemplate(new SmsRequest());
        List<GetTemplateDetailResponse> templateList = lResponse
                .getTemplateList();
        for (GetTemplateDetailResponse template : templateList) {
            if (template.getName().equals("x")) {
                this.client.deleteTemplate(new DeleteTemplateRequest()
                        .withTemplateId(template.getTemplateId()));
            }
        }
        Thread.sleep(1000);
    }

    @After
    public void tearDown() {
        this.client.shutdown();
    }

    @Test
    public void test() throws BceServiceException, ClassNotFoundException {
        String templateId = null;
        String tmpTemplateId = null;

        thrown.expect((Class<? extends Throwable>) Class.forName(expException));
        thrown.expectMessage(expMessage);

        switch (tag) {
            case 5: {
                try {
                    // 创建模板
                    CreateTemplateResponse cResponse = this.client
                            .createTemplate(new CreateTemplateRequest().withName(
                                    "x")
                                    .withContent("y"));
                    tmpTemplateId = cResponse.getData().getTemplateId();
                    // 确认模板创建成功
                    GetTemplateDetailResponse gResponse = this.client
                            .getTemplateDetail(new GetTemplateDetailRequest()
                                    .withTemplateId(tmpTemplateId));
                    Assert.assertEquals(tmpTemplateId, gResponse.getTemplateId());
                    // 删除模板
                    DeleteTemplateRequest dRequest = new DeleteTemplateRequest();
                    dRequest.setTemplateId(tmpTemplateId);
                    this.client.deleteTemplate(dRequest);
                    // 确认模板已删除（无法获取已删除模板的详情）
                    this.client.getTemplateDetail(new GetTemplateDetailRequest()
                            .withTemplateId(tmpTemplateId));
                } catch (BceServiceException e) {
                    Assert.assertTrue("Case data prepare fail", e.getMessage()
                            .contains("no records in database"));
                }
                templateId = tmpTemplateId;
                break;
            }
            default:
                templateId = inTemplateId;
        }
        DeleteTemplateRequest dRequest2 = new DeleteTemplateRequest();
        dRequest2.setTemplateId(templateId);
        this.client.deleteTemplate(dRequest2);
    }

}
