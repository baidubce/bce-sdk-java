package com.baidubce.services.oos;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcm.BcmClientConfiguration;
import com.baidubce.services.bcm.BcmClientTest;
import com.baidubce.services.oos.model.Operator;
import com.baidubce.services.oos.model.Template;
import com.baidubce.services.oos.model.common.TemplateType;
import com.baidubce.services.oos.model.request.BaseExecutionRequest;
import com.baidubce.services.oos.model.request.BaseTemplateRequest;
import com.baidubce.services.oos.model.request.OperatorListRequest;
import com.baidubce.services.oos.model.request.TemplateListRequest;
import com.baidubce.services.oos.model.response.BaseExecutionResponse;
import com.baidubce.services.oos.model.response.BaseTemplateResponse;
import com.baidubce.services.oos.model.response.CheckTemplateResponse;
import com.baidubce.services.oos.model.response.OperatorListResponse;
import com.baidubce.services.oos.model.response.TemplateListResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OosClientTest {
    private static final Logger logger = LoggerFactory.getLogger(BcmClientTest.class);
    private static final String ak = "ak";
    private static final String sk = "sk";
    private OosClient oosClient;

    @Before
    public void setUp() {
        BcmClientConfiguration config = new BcmClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("oos.bj.baidubce.com");
        oosClient = new OosClient(config);
    }

    private void printResult(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateTemplate() {
        BaseTemplateRequest request = getBaseTemplateRequest("创建模板测试");
        BaseTemplateResponse resp = oosClient.createTemplate(request);
        Assert.assertTrue(resp.getResult().getId().length() > 0);
    }

    @Test
    public void testCheckTemplate() {
        BaseTemplateRequest request = getBaseTemplateRequest("校验模板测试");
        CheckTemplateResponse resp = oosClient.checkTemplate(request);
        System.out.println(resp);
        Assert.assertTrue(resp.isSuccess());
    }

    @Test
    public void testUpdateTemplate() {
        BaseTemplateRequest request = getBaseTemplateRequest("更改模板测试");
        request.setId("tpl-5z2cdY**");
        BaseTemplateResponse resp = oosClient.updateTemplate(request);
        Assert.assertTrue(resp.isSuccess());
    }

    @Test
    public void testDeleteTemplate() {
        BaseTemplateResponse resp = oosClient.deleteTemplate("tpl-nXzDX2**");
        Assert.assertTrue(resp.isSuccess());
    }

    @Test
    public void testGetTemplateDetail() {
        BaseTemplateResponse resp = oosClient.getTemplateDetail("test_template_011234", TemplateType.INDIVIDUAL);
        Assert.assertTrue(resp.isSuccess());
        Assert.assertEquals("test_template_011234", resp.getResult().getName());
    }

    @Test
    public void testGetTemplateList() {
        TemplateListRequest request = new TemplateListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        TemplateListResponse resp = oosClient.getTemplateList(request);
        Assert.assertTrue(resp.isSuccess());
    }

    @Test
    public void testGetOperatorList() {
        OperatorListRequest request = new OperatorListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        OperatorListResponse resp = oosClient.getOperatorList(request);
        Assert.assertTrue(resp.isSuccess());
    }

    @Test
    public void testCreateExecution1() {
        BaseExecutionRequest request = new BaseExecutionRequest();
        Template template = new Template();
        template.setLinear(true);
        template.setRef("tpl-5z2cdY**");
        request.setTemplate(template);
        BaseExecutionResponse resp = oosClient.createExecution(request);
        Assert.assertTrue(resp.isSuccess());
    }

    @Test
    public void testCreateExecution2() {
        BaseExecutionRequest request = new BaseExecutionRequest();
        Template template = new Template();
        template.setLinear(true);
        template.setName("test_template_name");
        Operator operator1 = getOperator("stop", "停止BCC实例", "BCE::BCC::StopInstance");
        Operator operator2 = getOperator("start", "启动BCC实例", "BCE::BCC::StartInstance");
        template.setOperators(Arrays.asList(operator1, operator2));
        request.setTemplate(template);
        BaseExecutionResponse resp = oosClient.createExecution(request);
        Assert.assertTrue(resp.isSuccess());
    }

    @Test
    public void testGetExecutionDetail() {
        BaseExecutionResponse resp = oosClient.getExecutionDetail("d-j8fLppU7****");
        Assert.assertTrue(resp.isSuccess());
        Assert.assertEquals("d-j8fLppU7****", resp.getResult().getId());
    }

    private Operator getOperator(String name, String desc, String operator) {
        Operator result = new Operator();
        result.setName(name);
        result.setDescription(desc);
        result.setOperator(operator);
        Map<String, Object> properties = new HashMap<String, Object>();
        Map<String, Object> instances = new HashMap<String, Object>();
        instances.put("instanceId", "i-jOS24U**");
        instances.put("name", "instance-3nd0wnlr");
        properties.put("instances", instances);
        result.setProperties(properties);
        return result;
    }

    private BaseTemplateRequest getBaseTemplateRequest(String desc) {
        BaseTemplateRequest request = new BaseTemplateRequest();
        request.setDescription(desc);
        request.setName("test_template_011234");
        Operator operator1 = getOperator("stop", "停止BCC实例", "BCE::BCC::StopInstance");
        Operator operator2 = getOperator("start", "启动BCC实例", "BCE::BCC::StartInstance");
        request.setOperators(Arrays.asList(operator1, operator2));
        request.setLinear(true);
        return request;
    }
}
