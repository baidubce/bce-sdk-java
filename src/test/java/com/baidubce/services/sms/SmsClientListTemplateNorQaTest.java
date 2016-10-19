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
import com.baidubce.services.sms.model.GetTemplateDetailResponse;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.UpdateTemplateRequest;

@RunWith(Parameterized.class)
public class SmsClientListTemplateNorQaTest {
    private SmsClient client;
    private SmsInternalClient internalClient;
    private List<String> templateIdList = new ArrayList<String>();

    @Parameter(0)
    public int tag;
    @Parameter(1)
    public String description;
    @Parameter(2)
    public String inName;
    @Parameter(3)
    public String inContent;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        {1, "namecontent", "x", "y"},
                        {
                        2,
                        "namecontent",
                        "x123456789x123456789x123456789x1",
                        "x123456789x123456789x123456789x123456789x123456789x123456789x123456789"
                        },
                        {3, "namecontent", "x", "y${yk1}y${yk2}"},
                        {4, "namecontent", "x*", "y*"},
                        {5, "VALID", "x", "y"}, {6, "INVALID", "x", "y"},
                        {7, "time", "x", "y"}, {8, "deletedId", "x", "y"}});
    }

    @Before
    public void setUp() throws InterruptedException {
        this.client = new SmsClient(
                new SmsClientConfiguration()
                        .withCredentials(
                                new DefaultBceCredentials(SmsConf.SMS_AK,
                                        SmsConf.SMS_SK)).withEndpoint(SmsConf.SMS_ENDPOINT));
        this.internalClient =
                new SmsInternalClient(new SmsClientConfiguration().withCredentials(
                        new DefaultBceCredentials(SmsConf.SMS_AK, SmsConf.SMS_SK)).withEndpoint(SmsConf.SMS_ENDPOINT));
        List<GetTemplateDetailResponse> templateList = this.client
                .listTemplate(new SmsRequest()).getTemplateList();
        Thread.sleep(1000);
        for (GetTemplateDetailResponse template : templateList) {
            if (template.getName().equals("x") || template.getName().equals("x2")) {
                this.client.deleteTemplate(new DeleteTemplateRequest()
                        .withTemplateId(template.getTemplateId()));
            }
        }
    }

    @After
    public void tearDown() {
        for (String templateId : templateIdList) {
            UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                    .withTemplateId(templateId);
            uRequest.setStatus("VALID");
            this.internalClient.updateTemplate(uRequest);

            DeleteTemplateRequest request = new DeleteTemplateRequest();
            request.setTemplateId(templateId);
            this.client.deleteTemplate(request);
        }
        this.client.shutdown();
        this.internalClient.shutdown();
    }

    @Test
    public void test() {
        int beforeSize = 0;
        int afterSize = 0;
        int targetOffset = 0;
        beforeSize = this.client.listTemplate(new SmsRequest())
                .getTemplateList().size();

        CreateTemplateResponse cResponse = this.client
                .createTemplate(new CreateTemplateRequest().withName("x")
                        .withContent("y"));
        String templateId = cResponse.getData().getTemplateId();
        templateIdList.add(templateId);

        switch (tag) {
            case 5:
            case 6: {
                UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                        .withTemplateId(templateId);
                uRequest.setStatus(description);
                break;
            }
            case 7: {
                CreateTemplateResponse cResponse2 = this.client
                        .createTemplate(new CreateTemplateRequest().withName("x2")
                                .withContent("y2"));
                String templateId2 = cResponse2.getData().getTemplateId();
                templateIdList.add(templateId2);
                UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                        .withTemplateId(templateId);
                uRequest.setStatus("PROCESSING");
                targetOffset++;
                break;
            }
            case 8: {
                CreateTemplateResponse cResponse2 = this.client
                        .createTemplate(new CreateTemplateRequest().withName("x2")
                                .withContent("y2"));
                String templateId2 = cResponse2.getData().getTemplateId();
                this.client.deleteTemplate(new DeleteTemplateRequest()
                        .withTemplateId(templateId2));
                break;
            }
            default:
        }

        List<GetTemplateDetailResponse> templateList = this.client
                .listTemplate(new SmsRequest()).getTemplateList();
        afterSize = afterSize + templateList.size();
        Assert.assertEquals(beforeSize, afterSize - 1 - targetOffset);
        Assert.assertEquals("x", templateList.get(afterSize - 1 - targetOffset)
                .getName());
    }

}
