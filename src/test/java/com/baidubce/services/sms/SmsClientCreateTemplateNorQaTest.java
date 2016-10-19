package com.baidubce.services.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

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
public class SmsClientCreateTemplateNorQaTest {
    private SmsClient client;
    private List<String> templateIdList = new ArrayList<String>();

    @Parameter(0)
    public String inName;
    @Parameter(1)
    public String inContent;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        {"x", "y"},
                        {"x1", "y1"},
                        {
                        "x123456789x123456789x123456789x1",
                        "x123456789x123456789x123456789x123456789x123456789x123456789x123456789"
                        },
                        {"模板名", "模板内容"}, {"x", "y${yk1}y${yk2}"},
                        {"x*", "y*"}});
    }

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
        for (String templateId : templateIdList) {
            DeleteTemplateRequest request = new DeleteTemplateRequest();
            request.setTemplateId(templateId);
            this.client.deleteTemplate(request);
        }
        this.client.shutdown();
    }

    @Test
    public void test() {
        CreateTemplateResponse response = this.client
                .createTemplate(new CreateTemplateRequest().withName(inName)
                        .withContent(inContent));
        Assert.assertNotNull(response.getData().getTemplateId());
        templateIdList.add(response.getData().getTemplateId());
        GetTemplateDetailResponse gResponse = this.client
                .getTemplateDetail(new GetTemplateDetailRequest()
                        .withTemplateId(response.getData().getTemplateId()));
        Assert.assertEquals(inName, gResponse.getName());
        Assert.assertEquals(inContent, gResponse.getContent());
    }

}
