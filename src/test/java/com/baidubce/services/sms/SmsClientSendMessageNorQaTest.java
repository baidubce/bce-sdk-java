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
import com.baidubce.services.sms.model.ListTemplateResponse;
import com.baidubce.services.sms.model.SendMessageRequest;
import com.baidubce.services.sms.model.SendMessageResponse;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.UpdateTemplateRequest;

@RunWith(Parameterized.class)
public class SmsClientSendMessageNorQaTest {
    private SmsClient client;
    private SmsInternalClient internalClient;
    private List<String> templateIdList = new ArrayList<String>();

    @Parameter(0)
    public int tag;
    @Parameter(1)
    public int inSendCount;
    @Parameter(2)
    public List<String> inReceiver;
    @Parameter(3)
    public String inContentVar;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        // 带1个参数的模板
                        {1, 1, Arrays.asList("18811086572"), "{\"var1\":\"张三\"}"},
                        // 带2个参数的模板
                        {2, 1, Arrays.asList("18811086572"), "{\"var1\":\"张三\",\"var2\":\"吃饭\"}"},
                        // 1个receiver
                        {0, 1, Arrays.asList("18811086572"), "{}"},
                        // 2个receiver
                        {0, 2, Arrays.asList("18811086572", "13716953216"), "{}"},
                        // contentVar中含空白符
                        {0, 1, Arrays.asList("18811086572"), "{ }"},
                        // 模板不含变量，contentVar提供多余变量值
                        {0, 1, Arrays.asList("18811086572"), "{\"var1\":\"张三\"}"}
                        // contentVar最大长度     该case是长短信，实际按4条计费，暂时注释掉
                        // {
                        //        1,
                        //        1,
                        //        Arrays.asList("18811086572"),
                        //        "{\"var1\":\"" + UUID.randomUUID().toString()
                        //          + UUID.randomUUID().toString()
                        //          + UUID.randomUUID().toString()
                        //          + UUID.randomUUID().toString()
                        //          + UUID.randomUUID().toString()
                        //          + UUID.randomUUID().toString().substring(0,23) +
                        // "\"}" },

                });

    }

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
        for (String templateId : templateIdList) {
            DeleteTemplateRequest request = new DeleteTemplateRequest();
            request.setTemplateId(templateId);
            this.client.deleteTemplate(request);
        }
        this.client.shutdown();
        this.internalClient.shutdown();
    }

    @Test
    public void test() {
        String templateId = null;
        CreateTemplateResponse cResponse = null;

        switch (tag) {
            case 1: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("${var1}，你妈喊你回家"));
                break;
            }
            case 2: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("${var1}，你妈喊你回家${var2}"));
                break;
            }
            default: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("你妈喊你回家"));
            }
        }
        templateId = cResponse.getData().getTemplateId();
        templateIdList.add(templateId);
        UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                .withTemplateId(templateId);
        uRequest.setStatus("VALID");
        this.internalClient.updateTemplate(uRequest);
        SendMessageRequest sRequest = new SendMessageRequest();
        sRequest.setTemplateId(templateId);
        sRequest.setReceiver(inReceiver);
        sRequest.setContentVar(inContentVar);
        SendMessageResponse sResponse = this.client.sendMessage(sRequest);
        Assert.assertNotNull(sResponse.getMessageId());
        Assert.assertEquals(inSendCount, sResponse.getSendStat().getSendCount());
        Assert.assertEquals(inSendCount, sResponse.getSendStat()
                .getSuccessCount());
    }

}
