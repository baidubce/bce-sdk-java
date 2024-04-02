package com.baidubce.services.sms;

import java.util.ArrayList;
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
import com.baidubce.services.sms.model.GetTemplateDetailResponse;
import com.baidubce.services.sms.model.ListTemplateResponse;
import com.baidubce.services.sms.model.SmsRequest;

@RunWith(Parameterized.class)
public class SmsClientCreateTemplateAbnorQaTest {
    private SmsClient client;
    private List<String> templateIdList = new ArrayList<String>();

    @Parameter(0)
    public int tag;
    @Parameter(1)
    public String inName;
    @Parameter(2)
    public String inContent;
    @Parameter(3)
    public String expMessage;
    @Parameter(4)
    public String expException;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        {
                        1,
                        null,
                        "y",
                        "string name of request object should not be null or empty",
                        "java.lang.IllegalArgumentException"
                        },
                        {
                        2,
                        "",
                        "y",
                        "string name of request object should not be null or empty",
                        "java.lang.IllegalArgumentException"
                        },
                        {
                        3,
                        " ",
                        "y",
                        "string name of request object should not be null or empty",
                        "java.lang.IllegalArgumentException"
                        },
                        {
                        4,
                        "x123456789x123456789x123456789x12",
                        "y",
                        "the length of smsTplName=x123456789x123456789x123456789x12 is longer than 32",
                        "com.baidubce.BceServiceException"},
                        {
                        5,
                        "x",
                        null,
                        "string content of request object should not be null or empty",
                        "java.lang.IllegalArgumentException"},
                        {
                        6,
                        "x",
                        "",
                        "string content of request object should not be null or empty",
                        "java.lang.IllegalArgumentException"},
                        {
                        7,
                        "x",
                        " ",
                        "string content of request object should not be null or empty",
                        "java.lang.IllegalArgumentException"},
                        {
                        8,
                        "x",
                        "x123456789x123456789x123456789x123456789x123456789x123456789x123456789x",
                        "the length of "
                                + "smsTplContent=x123456789x123456789x12345678"
                                + "9x123456789x123456789x123456789x123456789x is longer than 70",
                        "com.baidubce.BceServiceException"},
                        {
                        9,
                        "duplicateName",
                        "y1",
                        "template name exists",
                        "com.baidubce.BceServiceException"
                        },
                        {
                        10,
                        "duplicateNameCaseInsensitive",
                        "y1",
                        "template name exists",
                        "com.baidubce.BceServiceException"
                        }});
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws InterruptedException {
        this.client = new SmsClient(
                new SmsClientConfiguration().withCredentials(
                        new DefaultBceCredentials(SmsConf.SMS_AK, SmsConf.SMS_SK))
                        .withEndpoint(SmsConf.SMS_ENDPOINT));
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
            System.out.println(templateId);
            this.client.deleteTemplate(request);
        }
        this.client.shutdown();
    }

    @Test
    public void test() throws BceServiceException, ClassNotFoundException {
        thrown.expect((Class<? extends Throwable>) Class.forName(expException));
        thrown.expectMessage(expMessage);

        String name = null;

        switch (tag) {
            case 9: {
                CreateTemplateResponse response = this.client
                        .createTemplate(new CreateTemplateRequest().withName("x")
                                .withContent("y"));
                Assert.assertNotNull(response.getData().getTemplateId());
                templateIdList.add(response.getData().getTemplateId());
                name = "x";
                break;

            }
            case 10: {
                CreateTemplateResponse response = this.client
                        .createTemplate(new CreateTemplateRequest().withName("X")
                                .withContent("y"));
                Assert.assertNotNull(response.getData().getTemplateId());
                templateIdList.add(response.getData().getTemplateId());
                name = "x";
                break;
            }
            default:
                name = inName;
        }

        CreateTemplateResponse response = this.client
                .createTemplate(new CreateTemplateRequest().withName(name)
                        .withContent(inContent));
    }

}
