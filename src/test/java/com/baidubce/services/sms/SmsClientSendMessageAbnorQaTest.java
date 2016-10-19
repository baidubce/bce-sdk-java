package com.baidubce.services.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class SmsClientSendMessageAbnorQaTest {
    private SmsClient client;
    private SmsInternalClient internalClient;
    private List<String> templateIdList = new ArrayList<String>();

    // 0:无参数模板,1:1个参数的模板,2:2个参数的模板,3:不用创建模板
    // 10:审批中的模板,20:审批失败的模板,30:已删除的模板
    @Parameter(0)
    public int tag;
    @Parameter(1)
    public String inTemplateId;
    @Parameter(2)
    public List<String> inReceiver;
    @Parameter(3)
    public String inContentVar;
    @Parameter(4)
    public String expException;
    @Parameter(5)
    public String expMessage;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        // templateId参数非法
                        // templateId为null
                        {3, null, Arrays.asList("18811086572"), "{}",
                        "java.lang.IllegalArgumentException",
                        "string templateId of request should not be null or empty"},
                        // templateId为空串
                        {3, "", Arrays.asList("18811086572"), "{}",
                        "java.lang.IllegalArgumentException",
                        "string templateId of request should not be null or empty"},
                        // templateId仅包含空白符
                        {3, " ", Arrays.asList("18811086572"), "{}",
                        "java.lang.IllegalArgumentException",
                        "string templateId of request should not be null or empty"},
                        // templateId不存在
                        {3, "smsTpl:notExistId944df8a9833153babc5930",
                        Arrays.asList("18811086572"), "{}",
                        "com.baidubce.BceServiceException",
                        "no records in database"},
                        // templateId状态为processing
                        {10, "", Arrays.asList("18811086572"), "{}",
                        "com.baidubce.BceServiceException",
                        "template is not in valid state"},
                        // templateId状态为invalid
                        {20, "", Arrays.asList("18811086572"), "{}",
                        "com.baidubce.BceServiceException",
                        "template is not in valid state"},
                        // templateId已删除
                        {30, "", Arrays.asList("18811086572"), "{}",
                        "com.baidubce.BceServiceException",
                        "no records in database"},

                        // receiver参数非法
                        // receiver为null
                        {0, "", null, "{}",
                        "java.lang.IllegalArgumentException",
                        "list receiver of request should not be null or empty"},
                        // receiver为空串
                        {0, "", Arrays.asList(""), "{}",
                        "java.lang.IllegalArgumentException",
                        "receiver should not be null or empty"},
                        // receiver仅包含空白串
                        {0, "", Arrays.asList(" "), "{}",
                        "java.lang.IllegalArgumentException",
                        "receiver should not be null or empty"},
                        // receiver包含非数字字符
                        {0, "", Arrays.asList("1381193940a"), "{}",
                        "com.baidubce.BceServiceException",
                        "receiver=1381193940a is not a valid phone number"},
                        // receiver位数不足11位
                        {0, "", Arrays.asList("1"), "{}",
                        "com.baidubce.BceServiceException",
                        "receiver=1 is not a valid phone number"},
                        // 部分receiver位数不足11位
                        {0, "", Arrays.asList("18811086572", "1"), "{}",
                        "com.baidubce.BceServiceException",
                        "receiver=1 is not a valid phone number"},

                        // contentVar参数非法
                        // contentVar为null
                        {0, "", Arrays.asList("18811086572"), null,
                        "java.lang.IllegalArgumentException",
                        "contentVar should not be null or empty"},
                        // contentVar为空串
                        {0, "", Arrays.asList("18811086572"), "",
                        "java.lang.IllegalArgumentException",
                        "contentVar should not be null or empty"},
                        // contentVar仅包含空白符
                        {0, "", Arrays.asList("18811086572"), " ",
                        "java.lang.IllegalArgumentException",
                        "contentVar should not be null or empty"},
                        // contentVar不符合json格式
                        {0, "", Arrays.asList("18811086572"), "{zzz}",
                        "com.baidubce.BceServiceException",
                        "contentVar={zzz} is not a valid json string"},
                        // 模板含1个变量，contentVar无变量值
                        {1, "", Arrays.asList("18811086572"), "{}",
                        "com.baidubce.BceServiceException",
                        "var map not match template"},
                        // 模板含2个变量，contentVar仅提供1个变量值
                        {2, "", Arrays.asList("18811086572"),
                        "{\"var1\":\"张三\"}",
                        "com.baidubce.BceServiceException",
                        "var map not match template"},
                        // 模板含1个变量，contentVar提供的变量对未加引号
                        {1, "", Arrays.asList("18811086572"), "{var1:张三}",
                        "com.baidubce.BceServiceException",
                        "contentVar={var1:张三} is not a valid json string"},
                        // 模板含1个变量，contentVar长度超限
                        {
                        1,
                        "",
                        Arrays.asList("18811086572"),
                        "{\"var1\":\""
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                + UUID.randomUUID().toString()
                                .substring(0, 23) + "x\"}",
                        "com.baidubce.BceServiceException",
                        "message's content is too large, not allow to > 210 chars"},
                });
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
            UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                    .withTemplateId(templateId);
            uRequest.setStatus("VALID");
            this.internalClient.updateTemplate(uRequest);
            this.client.deleteTemplate(new DeleteTemplateRequest()
                    .withTemplateId(templateId));
        }
        this.client.shutdown();
        this.internalClient.shutdown();
    }

    @Test
    public void test() throws ClassNotFoundException {
        thrown.expect((Class<? extends Throwable>) Class.forName(expException));
        thrown.expectMessage(expMessage);

        String templateId = null;
        CreateTemplateResponse cResponse = null;

        switch (tag) {
            case 1: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("${var1}，你妈喊你回家"));
                templateId = cResponse.getData().getTemplateId();
                prepareTemplate(templateId);
                break;
            }
            case 2: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("${var1}，你妈喊你回家${var2}"));
                templateId = cResponse.getData().getTemplateId();
                prepareTemplate(templateId);
                break;
            }
            case 0: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("你妈喊你回家"));
                templateId = cResponse.getData().getTemplateId();
                prepareTemplate(templateId);
                break;
            }
            case 10: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("你妈喊你回家"));
                templateId = cResponse.getData().getTemplateId();
                templateIdList.add(templateId);
                break;
            }
            case 20: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("你妈喊你回家"));
                templateId = cResponse.getData().getTemplateId();
                templateIdList.add(templateId);
                UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                        .withTemplateId(templateId);
                uRequest.setStatus("INVALID");
                this.internalClient.updateTemplate(uRequest);
                break;
            }
            case 30: {
                cResponse = this.client.createTemplate(new CreateTemplateRequest()
                        .withName("x").withContent("你妈喊你回家"));
                templateId = cResponse.getData().getTemplateId();
                this.client.deleteTemplate(new DeleteTemplateRequest()
                        .withTemplateId(templateId));
                break;
            }
            default:
                templateId = inTemplateId;
        }

        SendMessageRequest sRequest = new SendMessageRequest();
        sRequest.setTemplateId(templateId);
        sRequest.setReceiver(inReceiver);
        sRequest.setContentVar(inContentVar);
        SendMessageResponse sResponse = this.client.sendMessage(sRequest);
    }

    private void prepareTemplate(String templateId) {
        templateIdList.add(templateId);
        UpdateTemplateRequest uRequest = new UpdateTemplateRequest()
                .withTemplateId(templateId);
        uRequest.setStatus("VALID");
        this.internalClient.updateTemplate(uRequest);
    }

}
