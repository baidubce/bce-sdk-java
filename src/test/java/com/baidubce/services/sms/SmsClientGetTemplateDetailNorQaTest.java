package com.baidubce.services.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
import com.baidubce.services.sms.model.UpdateTemplateRequest;

@RunWith(Parameterized.class)
public class SmsClientGetTemplateDetailNorQaTest {
    private SmsClient client;
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
                .asList(new Object[][]{
                        {1, "namecontent", "x", "y"},
                        {2, "namecontent", "x123456789x123456789x123456789x1", "y"},
                        {3, "namecontent", "x1", "x123456789x123456789x123456789x123456789x123456789"},
                        {4, "namecontent", "x2", "y${yk1}y${yk2}"},
                        {5, "namecontent", "x3", "y*"},
                        {6, "VALID", "x4", "y"},
                        {7, "INVALID", "x5", "y"},
                        {8, "time", "x6", "y"}
                });
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
        Thread.sleep(1000);
        for (GetTemplateDetailResponse template : templateList) {
            if (template.getName().equals("x")) {
                this.client.deleteTemplate(new DeleteTemplateRequest()
                        .withTemplateId(template.getTemplateId()));
            }
        }
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
    public void test() throws InterruptedException {
        String status = "PROCESSING";
        Date createTime = new Date();
        Date updateTime = createTime;

        CreateTemplateResponse cResponse = this.client
                .createTemplate(new CreateTemplateRequest().withName(inName)
                        .withContent(inContent));
        Assert.assertNotNull(cResponse.getData().getTemplateId());
        String templateId = cResponse.getData().getTemplateId();
        templateIdList.add(templateId);

        switch (tag) {
            case 6:
            case 7: {
                UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                        .withTemplateId(templateId);
                uRequest.setStatus(description);
                break;
            }
            case 8: {
                Thread.sleep(3000);
                UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                        .withTemplateId(templateId);
                uRequest.setStatus("PROCESSING");
                updateTime = new Date();
                break;
            }
            default:
        }

        GetTemplateDetailResponse gResponse = this.client
                .getTemplateDetail(new GetTemplateDetailRequest()
                        .withTemplateId(templateId));

        Assert.assertEquals(inName, gResponse.getName());
        Assert.assertEquals(inContent, gResponse.getContent());
        Assert.assertEquals(status, gResponse.getStatus());
        Assert.assertTrue((gResponse.getCreateTime().getTime() - createTime
                .getTime()) / 1000 < 3);
        Assert.assertTrue((gResponse.getUpdateTime().getTime() - updateTime
                .getTime()) / 1000 < 3);
    }

}
