package com.baidubce.services.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.baidubce.services.sms.model.QueryMessageDetailRequest;
import com.baidubce.services.sms.model.QueryMessageDetailResponse;
import com.baidubce.services.sms.model.SendMessageRequest;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.UpdateTemplateRequest;

@RunWith(Parameterized.class)
public class SmsClientQueryMessageDetailNorQaTest {
    private SmsClient client;
    private SmsInternalClient internalClient;
    private List<String> templateIdList = new ArrayList<String>();

    @Parameter(0)
    public List<String> inReceiver;
    @Parameter(1)
    public String inContentVar;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // content含中文字符
                {Arrays.asList("18811086572"), "{\"var1\":\"张三\"}"},
                // 短信整体长达达到最大
                {
                Arrays.asList("18811086572"),
                "{\"var1\":\"" + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString().substring(0, 20)
                                + "\"}"
                },
                // 2个receiver
                {Arrays.asList("18811086572", "13716953216"), "{\"var1\":\"张三两遍\"}"}});
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
        this.internalClient.shutdown();
    }

    @Test
    public void test() {
        // 创建模板
        CreateTemplateResponse cResponse = this.client
                .createTemplate(new CreateTemplateRequest().withName("x")
                        .withContent("${var1}，查询短信详情Nor"));
        String templateId = cResponse.getData().getTemplateId();
        templateIdList.add(templateId);

        // 将模板更新为valid状态
        UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                .withTemplateId(templateId);
        uRequest.setStatus("VALID");
        this.internalClient.updateTemplate(uRequest);

        // 发送短信
        Date expTime = new Date();
        SendMessageRequest sRequest = new SendMessageRequest();
        sRequest.setTemplateId(templateId);
        sRequest.setReceiver(inReceiver);
        sRequest.setContentVar(inContentVar);
        String messageId = this.client.sendMessage(sRequest).getMessageId();
        // 查询短信详情
        QueryMessageDetailResponse qResponse = this.client
                .queryMessageDetail(new QueryMessageDetailRequest()
                        .withMessageId(messageId));

        // 验证信息
        Assert.assertEquals(
                inContentVar.split(",")[0].split(":")[1].split("\\\"")[1]
                        + "，查询短信详情Nor", qResponse.getContent());
        Assert.assertEquals(inReceiver, qResponse.getReceiver());
        Assert.assertTrue((qResponse.getSendTime().getTime() - expTime
                .getTime()) / 1000 < 3);
    }

}
