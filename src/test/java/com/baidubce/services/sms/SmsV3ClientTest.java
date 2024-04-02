package com.baidubce.services.sms;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.model.SendMessageItem;
import com.baidubce.services.sms.model.SendMessageV3Request;
import com.baidubce.services.sms.model.SendMessageV3Response;
import com.baidubce.services.sms.model.v3.CreateBlackRequest;
import com.baidubce.services.sms.model.v3.CreateSignatureRequest;
import com.baidubce.services.sms.model.v3.CreateSignatureResponse;
import com.baidubce.services.sms.model.v3.CreateTemplateRequest;
import com.baidubce.services.sms.model.v3.CreateTemplateResponse;
import com.baidubce.services.sms.model.v3.DeleteSignatureRequest;
import com.baidubce.services.sms.model.v3.DeleteTemplateRequest;
import com.baidubce.services.sms.model.v3.GetSignatureRequest;
import com.baidubce.services.sms.model.v3.GetSignatureResponse;
import com.baidubce.services.sms.model.v3.GetTemplateRequest;
import com.baidubce.services.sms.model.v3.GetTemplateResponse;
import com.baidubce.services.sms.model.v3.ListBlackRequest;
import com.baidubce.services.sms.model.v3.ListBlackResponse;
import com.baidubce.services.sms.model.v3.ListSignatureRequest;
import com.baidubce.services.sms.model.v3.ListSignatureResponse;
import com.baidubce.services.sms.model.v3.ListStatisticsRequest;
import com.baidubce.services.sms.model.v3.ListStatisticsResponse;
import com.baidubce.services.sms.model.v3.ListTemplateRequest;
import com.baidubce.services.sms.model.v3.ListTemplateResponse;
import com.baidubce.services.sms.model.v3.ModifySignatureRequest;
import com.baidubce.services.sms.model.v3.ModifyTemplateRequest;
import com.baidubce.services.sms.model.v3.QueryQuotaRateResponse;
import com.baidubce.services.sms.model.v3.UpdateQuotaRateRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.doThrow;

public class SmsV3ClientTest {

    private SmsClient smsClient;

    protected static final String ENDPOINT = "http://localhost:8220";
    protected static final String ACCESS_KEY = "";
    protected static final String SECRET_KEY = "";

    @Before
    public void setUp() {
        smsClient = Mockito.mock(SmsClient.class);
    }

    @Test
    public void testSendMessage() {
        SendMessageV3Response responseMock = new SendMessageV3Response();
        List<SendMessageItem> sendMessageItemList = new ArrayList<SendMessageItem>();
        SendMessageItem item = new SendMessageItem();
        item.setCode("10000");
        item.setMessage("成功");
        item.setMessageId("5be70aa3-ee75-42bb-849e-802ff312fa11_13800138000");
        item.setMobile("13800138000");
        sendMessageItemList.add(item);
        responseMock.setData(sendMessageItemList);
        responseMock.setCode("10000");
        responseMock.setMessage("成功");
        responseMock.setRequestId("5be70aa3-ee75-42bb-849e-802ff312fa11");
        SendMessageV3Request request = new SendMessageV3Request();
        request.setSignatureId("testSignatureId");
        request.setTemplate("testTemplate");
        request.setMobile("13800138000");
        request.setContentVar(new HashMap<String, String>());
        request.setClientToken("testClientToken");
        request.setUserExtId("123456");
        request.setCustom("testCustom");
        request.setCallbackUrlId("testCallbackUrlId");
        Mockito.when(smsClient.sendMessage(request)).thenReturn(responseMock);
        SendMessageV3Response response = smsClient.sendMessage(request);
        Assert.assertEquals(responseMock.getMessage(), response.getMessage());
        Assert.assertEquals(responseMock.getData(), response.getData());
        Assert.assertEquals(responseMock.getCode(), response.getCode());
        Assert.assertEquals(responseMock.getRequestId(), response.getRequestId());
    }

    @Test
    public void testSignature() {
        // 1. create
        CreateSignatureResponse createSignRespMock = new CreateSignatureResponse();
        createSignRespMock.setSignatureId("test-signatureId");
        createSignRespMock.setStatus("SUBMITTED");
        CreateSignatureRequest createSignReq = new CreateSignatureRequest();
        createSignReq.setContent("Baidu");
        createSignReq.setContentType("Enterprise");
        createSignReq.setCountryType("DOMESTIC");
        createSignReq.setDescription("my sign");
        Mockito.when(smsClient.createSignature(createSignReq)).thenReturn(createSignRespMock);
        CreateSignatureResponse createSignatureResponse = smsClient.createSignature(createSignReq);
        Assert.assertEquals(createSignRespMock.getSignatureId(), createSignatureResponse.getSignatureId());
        Assert.assertEquals(createSignRespMock.getStatus(), createSignatureResponse.getStatus());
        // 2. update
        ModifySignatureRequest modifySignatureRequest = new ModifySignatureRequest();
        modifySignatureRequest.setStatus("SUBMITTED");
        modifySignatureRequest.setContent("BaiduCloud");
        modifySignatureRequest.setContentType("MobileApp");
        modifySignatureRequest.setCountryType("GLOBAL");
        modifySignatureRequest.setDescription("my sign update");
        modifySignatureRequest.setSignatureId("test-signatureId");
        doThrow(new RuntimeException("ModifyError")).when(smsClient).modifySignature(modifySignatureRequest);
        try {
            smsClient.modifySignature(modifySignatureRequest);
        } catch (Exception e) {
            Assert.assertEquals("ModifyError", e.getMessage());
        }
        // 3. get
        GetSignatureResponse getSignRespMock = new GetSignatureResponse();
        getSignRespMock.setContent("Baidu");
        getSignRespMock.setContentType("Enterprise");
        getSignRespMock.setCountryType("INTERNATIONAL");
        getSignRespMock.setCreateDate(new Date());
        getSignRespMock.setUpdateDate(new Date());
        getSignRespMock.setReview("review");
        getSignRespMock.setSignatureId("test-signatureId");
        getSignRespMock.setStatus("SUBMITTED");
        getSignRespMock.setUserId("zhangsan");
        GetSignatureRequest getSignReq = new GetSignatureRequest();
        getSignReq.setSignatureId("test-signatureId");
        smsClient.getSignature(getSignReq);
        Mockito.when(smsClient.getSignature(getSignReq)).thenReturn(getSignRespMock);
        GetSignatureResponse getSignatureResponse = smsClient.getSignature(getSignReq);
        Assert.assertEquals(getSignRespMock.getContent(), getSignatureResponse.getContent());
        Assert.assertEquals(getSignRespMock.getContentType(), getSignatureResponse.getContentType());
        Assert.assertEquals(getSignRespMock.getCreateDate(), getSignatureResponse.getCreateDate());
        Assert.assertEquals(getSignRespMock.getReview(), getSignatureResponse.getReview());
        Assert.assertEquals(getSignRespMock.getSignatureId(), getSignatureResponse.getSignatureId());
        Assert.assertEquals(getSignRespMock.getCountryType(), getSignatureResponse.getCountryType());
        Assert.assertEquals(getSignRespMock.getStatus(), getSignatureResponse.getStatus());
        Assert.assertEquals(getSignRespMock.getUpdateDate(), getSignatureResponse.getUpdateDate());
        Assert.assertEquals(getSignRespMock.getUserId(), getSignatureResponse.getUserId());
        // 4. list
        ListSignatureResponse lr = new ListSignatureResponse();
        List<GetSignatureResponse> list = new ArrayList<GetSignatureResponse>();
        list.add(getSignRespMock);
        lr.setSignatureApplies(list);
        lr.setTotalCount(1);
        ListSignatureRequest lq = new ListSignatureRequest();
        lq.setStatus("READY");
        lq.setSignatureId("test-signatureId");
        lq.setCountryType("INTERNATIONAL");
        lq.setContent("Baidu");
        lq.setPageNo(1);
        lq.setPageSize(1);
        Mockito.when(smsClient.listSignature(lq)).thenReturn(lr);
        Assert.assertEquals(1, lr.getTotalCount());
        // 5. delete
        DeleteSignatureRequest deleteSignatureRequest = new DeleteSignatureRequest()
                .withSignatureId("test-signatureId");
        smsClient.deleteSignature(deleteSignatureRequest);
        doThrow(new RuntimeException("DeleteError")).when(smsClient).deleteSignature(deleteSignatureRequest);
        try {
            smsClient.deleteSignature(deleteSignatureRequest);
        } catch (Exception e) {
            Assert.assertEquals("DeleteError", e.getMessage());
        }
    }

    @Test
    public void testTemplate() {
        // 1. create
        CreateTemplateResponse creatTempRespMock = new CreateTemplateResponse();
        creatTempRespMock.setStatus("SUBMITTED");
        creatTempRespMock.setTemplateId("test-templateId");
        CreateTemplateRequest createTemplateRequest = new CreateTemplateRequest();
        createTemplateRequest.setContent("${content}");
        createTemplateRequest.setCountryType("GLOBAL");
        createTemplateRequest.setDescription("my template");
        createTemplateRequest.setName("My TemplateName");
        createTemplateRequest.setSmsType("CommonNotice");
        Mockito.when(smsClient.createTemplate(createTemplateRequest)).thenReturn(creatTempRespMock);
        CreateTemplateResponse createTemplateResponse = smsClient.createTemplate(createTemplateRequest);
        Assert.assertEquals(creatTempRespMock.getStatus(), createTemplateResponse.getStatus());
        Assert.assertEquals(creatTempRespMock.getTemplateId(), createTemplateResponse.getTemplateId());
        // 2. update
        ModifyTemplateRequest modifyTemplateRequest = new ModifyTemplateRequest()
                .withTemplateId("test-templateId")
                .withName("My TemplateName")
                .withContent("${content}")
                .withCountryType("DOMESTIC")
                .withSmsType("CommonNotice")
                .withDescription("my description");
        doThrow(new RuntimeException("ModifyError")).when(smsClient)
                .modifyTemplate(modifyTemplateRequest);
        try {
            smsClient.modifyTemplate(modifyTemplateRequest);
        } catch (Exception e) {
            Assert.assertEquals("ModifyError", e.getMessage());
        }
        // 3. get
        GetTemplateRequest getTemplateRequest = new GetTemplateRequest()
                .withTemplateId("test-templateId");
        GetTemplateResponse getTempMock = new GetTemplateResponse();
        getTempMock.setContent("${content}");
        getTempMock.setCountryType("GLOBAL");
        getTempMock.setCreateDate(new Date());
        getTempMock.setDescription("decription");
        getTempMock.setName("My template");
        getTempMock.setReview("review");
        getTempMock.setSmsType("CommonNotice");
        getTempMock.setStatus("SUBMITTED");
        getTempMock.setTemplateId("test-templateId");
        getTempMock.setUpdateDate(new Date());
        getTempMock.setUserId("zhangsan");
        Mockito.when(smsClient.getTemplate(getTemplateRequest)).thenReturn(getTempMock);
        GetTemplateResponse getTemplateResponse = smsClient.getTemplate(getTemplateRequest);
        Assert.assertEquals(getTempMock.getContent(), getTemplateResponse.getContent());
        Assert.assertEquals(getTempMock.getCountryType(), getTemplateResponse.getCountryType());
        Assert.assertEquals(getTempMock.getCreateDate(), getTemplateResponse.getCreateDate());
        Assert.assertEquals(getTempMock.getDescription(), getTemplateResponse.getDescription());
        Assert.assertEquals(getTempMock.getName(), getTemplateResponse.getName());
        Assert.assertEquals(getTempMock.getReview(), getTemplateResponse.getReview());
        Assert.assertEquals(getTempMock.getSmsType(), getTemplateResponse.getSmsType());
        Assert.assertEquals(getTempMock.getStatus(), getTemplateResponse.getStatus());
        Assert.assertEquals(getTempMock.getTemplateId(), getTemplateResponse.getTemplateId());
        Assert.assertEquals(getTempMock.getUpdateDate(), getTemplateResponse.getUpdateDate());
        Assert.assertEquals(getTempMock.getUserId(), getTemplateResponse.getUserId());
        // 4. list
        ListTemplateResponse lr = new ListTemplateResponse();
        List<GetTemplateResponse> list = new ArrayList<GetTemplateResponse>();
        list.add(getTempMock);
        lr.setTemplates(list);
        lr.setTotalCount(1);
        ListTemplateRequest lq = new ListTemplateRequest();
        lq.setStatus("READY");
        lq.setTemplateId("test-templateId");
        lq.setCountryType("INTERNATIONAL");
        lq.setContent("${content");
        lq.setSmsType("CommonNotice");
        lq.setStatus("SUBMITTED");
        lq.setPageNo(1);
        lq.setPageSize(1);
        Mockito.when(smsClient.listTemplate(lq)).thenReturn(lr);
        Assert.assertEquals(1, lr.getTotalCount());
        // 5. delete
        DeleteTemplateRequest deleteTemplateRequest = new DeleteTemplateRequest()
                .withTemplateId("test-templateId");
        doThrow(new RuntimeException("DeleteError")).when(smsClient).deleteTemplate(deleteTemplateRequest);
        try {
            smsClient.deleteTemplate(deleteTemplateRequest);
        } catch (Exception e) {
            Assert.assertEquals("DeleteError", e.getMessage());
        }
    }

    @Test
    public void testQuotaRate() {
        UpdateQuotaRateRequest updateQuotaRateRequest = new UpdateQuotaRateRequest();
        updateQuotaRateRequest.setQuotaPerDay(1000);
        updateQuotaRateRequest.setQuotaPerMonth(10000);
        updateQuotaRateRequest.setRateLimitPerDay(100);
        updateQuotaRateRequest.setRateLimitPerHour(50);
        updateQuotaRateRequest.setRateLimitPerMinute(10);
        doThrow(new RuntimeException("UpdateError"))
                .when(smsClient)
                .updateQuotaRate(updateQuotaRateRequest);
        try {
            smsClient.updateQuotaRate(updateQuotaRateRequest);
        } catch (Exception e) {
            Assert.assertEquals("UpdateError", e.getMessage());
        }
        QueryQuotaRateResponse queryQuotaRateRespMock = new QueryQuotaRateResponse();
        queryQuotaRateRespMock.setQuotaPerDay(1000);
        queryQuotaRateRespMock.setQuotaPerMonth(10000);
        queryQuotaRateRespMock.setRateLimitPerDay(100);
        queryQuotaRateRespMock.setRateLimitPerHour(50);
        queryQuotaRateRespMock.setRateLimitPerMinute(10);
        Mockito.when(smsClient.queryQuotaRate()).thenReturn(queryQuotaRateRespMock);
        QueryQuotaRateResponse response = smsClient.queryQuotaRate();
        Assert.assertEquals(queryQuotaRateRespMock.getQuotaPerDay(), response.getQuotaPerDay());
        Assert.assertEquals(queryQuotaRateRespMock.getQuotaPerMonth(), response.getQuotaPerMonth());
        Assert.assertEquals(queryQuotaRateRespMock.getRateLimitPerDay(), response.getRateLimitPerDay());
        Assert.assertEquals(queryQuotaRateRespMock.getRateLimitPerHour(), response.getRateLimitPerHour());
        Assert.assertEquals(queryQuotaRateRespMock.getRateLimitPerMinute(), response.getRateLimitPerMinute());
    }

    /**
     * type: MerchantBlack、SignatureBlack
     */
    @Ignore
    @Test
    public void testMobileBlack() {

        SmsClientConfiguration config = new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY,SECRET_KEY));
        config.setEndpoint(ENDPOINT);
        this.smsClient = new SmsClient(config);

        // 创建黑名单
        CreateBlackRequest createBlackRequest = new CreateBlackRequest();
        createBlackRequest.setType("MerchantBlack");
        createBlackRequest.setPhone("17600000000");
        smsClient.createMobileBlack(createBlackRequest);

        createBlackRequest = new CreateBlackRequest();
        createBlackRequest.setType("MerchantBlack");
        createBlackRequest.setPhone("17600000000");
        createBlackRequest.setSmsType("CommonNotice");
        smsClient.createMobileBlack(createBlackRequest);

        try {
            createBlackRequest = new CreateBlackRequest();
            createBlackRequest.setType("SignatureBlack");
            createBlackRequest.setPhone("17600000000");
            createBlackRequest.setSmsType("CommonNotice");
            smsClient.createMobileBlack(createBlackRequest);
        } catch (Exception e) {
            // 签名黑，短信类型和签名必填
            System.out.println(e.getMessage());
        }

        createBlackRequest = new CreateBlackRequest();
        createBlackRequest.setType("SignatureBlack");
        createBlackRequest.setPhone("17600000000");
        createBlackRequest.setSmsType("CommonNotice");
        createBlackRequest.setSignatureIdStr("sms-sign-hHYgtS57924");
        smsClient.createMobileBlack(createBlackRequest);

        // 查询黑名单
        ListBlackRequest request = new ListBlackRequest();
        ListBlackResponse listBlackResponse = smsClient.listMobileBlack(request);
        Assert.assertEquals(listBlackResponse.getTotalCount(), listBlackResponse.getTotalCount());

        request.setPhone("17600000000");
        listBlackResponse = smsClient.listMobileBlack(request);
        Assert.assertEquals(3, listBlackResponse.getTotalCount());

        request.setPhone("17600000000");
        request.setSmsType("CommonNotice");
        listBlackResponse = smsClient.listMobileBlack(request);
        Assert.assertEquals(2, listBlackResponse.getTotalCount());

        request.setPhone("17600000000");
        request.setSmsType("CommonNotice");
        request.setSignatureIdStr("sms-sign-hHYgtS57924");
        String start = "2023-07-18";
        String end = "2023-07-19";
        request.setStartTime(start);
        request.setEndTime(end);
        listBlackResponse = smsClient.listMobileBlack(request);
        Assert.assertEquals(1, listBlackResponse.getTotalCount());

        // 删除黑名单
        smsClient.deleteMobileBlack("17600000000,17600000001");
        request.setPhone("17600000000");
        request.setSmsType("CommonNotice");
        listBlackResponse = smsClient.listMobileBlack(request);
        Assert.assertEquals(0, listBlackResponse.getTotalCount());

        smsClient.deleteMobileBlack("17600000000");
    }

    /**
     * Test, used to test the listStatistics method
     */
    @Test
    public void testStatistics() {
        // 配置client
        SmsClientConfiguration config = new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY,SECRET_KEY));
        config.setEndpoint(ENDPOINT);
        SmsClient emptySmsClient = new SmsClient(config);

        // 配置时间对象
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // 空统计结果，三条记录
        ListStatisticsRequest request = new ListStatisticsRequest();
        request.setSmsType("all");


        request.setStartTime(formatter.format(yesterday));
        request.setEndTime(formatter.format(today));

        ListStatisticsResponse emptyResult = emptySmsClient.listStatistics(request);
        Assert.assertNotNull(emptyResult);
        Assert.assertEquals(3, emptyResult.getStatisticsResults().size());

        // 传入其他可选参数
        request.setSignatureId("mock-signature-id");
        request.setTemplateCode("mock-template-code");
        request.setCountryType("international");
        emptyResult = emptySmsClient.listStatistics(request);
        Assert.assertNotNull(emptyResult);
        Assert.assertEquals(3, emptyResult.getStatisticsResults().size());

        // 缺少必要参数的请求
        request.setSmsType(null);

        try {
            emptySmsClient.listStatistics(request);
            Assert.fail("此处需要抛出一个必要参数缺少的异常");
        } catch(NullPointerException e) {
            Assert.assertTrue(e.getMessage().contains("smsType should not be null, \"all\" can be set as default"));
        }

        // 时间参数不符合格式，抛出异常
        request.setSmsType("all");
        request.setStartTime("2023-09-31");
        try {
            emptySmsClient.listStatistics(request);
            Assert.fail("此处需要抛出一个Internal Server Error异常");
        } catch(BceServiceException e) {
            Assert.assertTrue(e.getMessage().contains("Internal Server Error"));
        }

        // 选择的查询时间早于一年之前
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -366);
        Date oneYearBefore = calendar.getTime();
        request.setStartTime(formatter.format(oneYearBefore));
        try {
            emptySmsClient.listStatistics(request);
            Assert.fail("此处需要抛出一个查询时间范围的异常");
        } catch(BceServiceException e) {
            Assert.assertTrue(e.getMessage().contains("请求时间范围错误，请检查请求查询时间范围"));
        }

        // mock统计数据
        request.setSmsType("all");
        request.setStartTime("2023-09-24");
        request.setEndTime("2023-09-25");

        ListStatisticsResponse mockResponse = new ListStatisticsResponse();
        List<ListStatisticsResponse.StatisticsResult> resultsList =
                new ArrayList<ListStatisticsResponse.StatisticsResult>();
        ListStatisticsResponse.StatisticsResult res1 = new ListStatisticsResponse.StatisticsResult();
        res1.setDatetime("2023-09-24");
        res1.setCountryAlpha2Code("CN");
        res1.setSubmitCount("10");
        res1.setDeliverSuccessCount("8");
        res1.setDeliverSuccessProportion("0.8");
        res1.setResponseSuccessCount("0");
        res1.setResponseSuccessProportion("0");
        resultsList.add(res1);

        ListStatisticsResponse.StatisticsResult res2 = new ListStatisticsResponse.StatisticsResult();
        res2.setDatetime("合计");
        res2.setCountryAlpha2Code("CN");
        res2.setSubmitCount("10");
        res2.setDeliverSuccessCount("8");
        res2.setDeliverSuccessProportion("0.8");
        res2.setResponseSuccessCount("0");
        res2.setResponseSuccessProportion("0");
        resultsList.add(res2);

        mockResponse.setStatisticsResults(resultsList);

        Mockito.when(smsClient.listStatistics(request)).thenReturn(mockResponse);
        ListStatisticsResponse response = smsClient.listStatistics(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatisticsResults().size(), 2);
    }
}
