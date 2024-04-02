package com.baidubce.services.cfc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cfc.model.CreateFunctionRequest;
import com.baidubce.services.cfc.model.CreateFunctionResponse;
import com.baidubce.services.cfc.model.Code;
import com.baidubce.services.cfc.model.Environment;
import com.baidubce.services.cfc.model.UpdateFunctionCodeRequest;
import com.baidubce.services.cfc.model.UpdateFunctionCodeResponse;
import com.baidubce.services.cfc.model.GetFunctionConfigurationResponse;
import com.baidubce.services.cfc.model.UpdateFunctionConfigurationRequest;
import com.baidubce.services.cfc.model.UpdateFunctionConfigurationResponse;
import com.baidubce.services.cfc.model.InvokeResponse;
import com.baidubce.services.cfc.model.ListFunctionsResponse;
import com.baidubce.services.cfc.model.GetFunctionResponse;
import com.baidubce.services.cfc.model.ListAliasesResponse;
import com.baidubce.services.cfc.model.GetAliasResponse;
import com.baidubce.services.cfc.model.CreateAliasResponse;
import com.baidubce.services.cfc.model.UpdateAliasResponse;
import com.baidubce.services.cfc.model.ListVersionsByFunctionResponse;
import com.baidubce.services.cfc.model.PublishVersionResponse;
import com.baidubce.services.cfc.model.ListTriggersResponse;
import com.baidubce.services.cfc.model.CreateTriggerResponse;
import com.baidubce.services.cfc.model.UpdateTriggerResponse;
import com.baidubce.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;


public class CfcClientTest {
    private static final String AK = "Access Key"; // 用户的Access Key
    private static final String SK = "Secret Key"; // 用户的Secret Key
    private static final String ENDPOINT = "https://cfc.bj.baidubce.com";

    private CfcClient cfcClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        cfcClient = new CfcClient(config);
    }

    @Test
    public void testCreateFunction() {
        String zipFile = "UEsDBBQACAgIAGumvE4AAAAAAAAAAAAAAAAIAAAAaW5kZXgucHkVykEKgCAQQNG94B2m3FQk0i66"
                + "TeWUgYwxjVG3z/7y8U3l8sVuOcgh3XC+EhJpZcB2FtbkD9onyLLZ8RetPG4QZvIRucEbSfpykeAj7aQVlBglM0E9BIwx"
                + "1R9QSwcIunbW71oAAABhAAAAUEsBAhQAFAAICAgAa6a8Trp21u9aAAAAYQAAAAgAAAAAAAAAAAAAAAAAAAAAAG"
                + "luZGV4LnB5UEsFBgAAAAABAAEANgAAAJAAAAAAAA==";
        Code code = new Code();
        code.setZipFile(zipFile);
        code.setPublish(false);
        code.setDryRun(true);
        String description = "test api";
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("additional", "aaa");
        Environment environment = new Environment();
        environment.setVariables(variables);
        long time = System.currentTimeMillis();
        String functionName = "test-" + String.valueOf(time);
        String handler = "index.handler";
        Integer memorySize = 256;
        String runtime = "python3";
        Integer timeOut = 5;

        CreateFunctionRequest request = new CreateFunctionRequest(functionName, null)
                .withCode(code)
                .withEnvironment(environment)
                .withHandler(handler)
                .withMemorySize(memorySize)
                .withRuntime(runtime)
                .withTimeout(timeOut);
        CreateFunctionResponse response = cfcClient.createFunction(request);
        System.out.println(response);

    }

    @Test
    public void testUpdateFunctionCode() {
        String zipFile = "UEsDBBQACAgIAIumvE4AAAAAAAAAAAAAAAAIAAAAaW5kZXgucHkVjMsOgjAQAO9N+g8rXNTYVEHk"
                + "8TMG6EJJmi2pW5C/t85xMpn8pOMn6GEhjbTBerD1JEUO6qpg9GahuYPIk2r+RgqDE9iejMNwxg2Jb6kixi9fOikg"
                + "EZBjIMgKwv1t0Tn/KMpn9aqb9t4PYxrMNvsBUEsHCGs/OTtwAAAAdwAAAFBLAQIUABQACAgIAIumvE5rPzk7cAAAA"
                + "HcAAAAIAAAAAAAAAAAAAAAAAAAAAABpbmRleC5weVBLBQYAAAAAAQABADYAAACmAAAAAAA=";
        Code code = new Code();
        code.setZipFile(zipFile);
        Boolean publish = true;
        Boolean dryRun = false;
        String functionName = "test-1560487078143"; // 已经存在的函数名称

        UpdateFunctionCodeRequest request = new UpdateFunctionCodeRequest()
                .withFunctionName(functionName)
                .withZipFile(zipFile)
                .withPublish(publish)
                .withDryRun(dryRun);
        UpdateFunctionCodeResponse response = cfcClient.updateFunctionCode(request);
        System.out.println(response);

    }

    @Test
    public void testGetFunctionConfiguration() {
        String functionName = "test-1560848182899"; // 已经存在的函数名称
        GetFunctionConfigurationResponse response = cfcClient.getFunctionConfiguration(functionName, null);
        System.out.println(response);
    }

    @Test
    public void testUpdateFunctionConfiguration() {
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("additional1", "bbbb");
        Environment environment = new Environment();
        environment.setVariables(variables);
        String functionName = "test-1560848182899"; // 已经存在的函数名
        String desc = "123";
        Integer timeout = 3;
        String zipFile = "UEsDBBQACAgIAIumvE4AAAAAAAAAAAAAAAAIAAAAaW5kZXgucHkVjMsOgjAQAO9N+g8rXNTYVEHk"
                + "8TMG6EJJmi2pW5C/t85xMpn8pOMn6GEhjbTBerD1JEUO6qpg9GahuYPIk2r+RgqDE9iejMNwxg2Jb6kixi9fOikg"
                + "EZBjIMgKwv1t0Tn/KMpn9aqb9t4PYxrMNvsBUEsHCGs/OTtwAAAAdwAAAFBLAQIUABQACAgIAIumvE5rPzk7cAAAAHcAAAA"
                + "IAAAAAAAAAAAAAAAAAAAAAABpbmRleC5weVBLBQYAAAAAAQABADYAAACmAAAAAAA=";
        Code code = new Code();
        code.setZipFile(zipFile);
        code.setPublish(false);
        code.setDryRun(true);

        UpdateFunctionConfigurationRequest request = new UpdateFunctionConfigurationRequest(functionName, null)
                .withEnvironment(environment)
                .withTimeout(timeout)
                .withCode(code)
                .withFunctionName(functionName);
        UpdateFunctionConfigurationResponse response = cfcClient.updateFunctionConfiguration(request);
        System.out.println(response);
    }

    @Test
    public void testInvoke() {
        HashMap<String, Object> payload = new HashMap<String, Object>();
        HashMap<String, Object> payload1 = new HashMap<String, Object>();
        payload1.put("key1", "value1");
        payload.put("key2", payload1);
        payload.put("key3", "value3");
        byte[] payloadBytes = JsonUtils.toJsonString(payload).getBytes();
        String functionName = "test-1603961009154"; // 已经存在的函数名称
        InvokeResponse response = cfcClient.invoke(functionName, "RequestResponse", "Tail", null, payloadBytes);
        String res = new String(response.getPayload());
        System.out.println(res);

    }

    @Test
    public void testListFunctions() {
        ListFunctionsResponse response = cfcClient.listFunctions("ALL", null, null);
        System.out.println(response);
    }

    @Test
    public void testGetFunction() {
        String functionName = "test-1560848182899"; // 已经存在的函数名称
        GetFunctionResponse response = cfcClient.getFunction(functionName, "$LATEST");
        System.out.println(response);
    }

    @Test
    public void testDeleteFunction() {
        String functionName = "test-1557893401910"; // 已经存在的函数名称
        cfcClient.deleteFunction(functionName, null);
    }

    @Test
    public void testListAliases() {
        String functionName = "test-1560487078143"; // 已经存在的函数名称
        ListAliasesResponse response = cfcClient.listAliases(functionName, null, null, null);
        System.out.println(response);
    }

    @Test
    public void testGetAlias() {
        String functionName = "test-1560848182899"; // 已经存在的函数名称
        GetAliasResponse response = cfcClient.getAlias("test1", functionName);
        System.out.println(response);
    }

    @Test
    public void testCreateAlias() {
        String functionName = "test-1560848182899"; // 已经存在的函数名称
        CreateAliasResponse response = cfcClient.createAlias(functionName, "$LATEST", "test1", null);
        System.out.println(response);
    }

    @Test
    public void testUpdateAlias() {
        String functionName = "test-1560848182899"; // 已经存在的函数名称
        UpdateAliasResponse response = cfcClient.updateAlias(functionName, "$LATEST", "test2", "更新别名1");
        System.out.println(response);
    }

    @Test
    public void testDeleteAlias() {
        cfcClient.deleteAlias("test-1557387367523", "test1");
    }

    @Test
    public void testListVersionsByFunction() {
        ListVersionsByFunctionResponse response = cfcClient.listVersionsByFunction("test-1560487078143", null, null);
        System.out.println(response);
    }

    @Test
    public void testPublishVersion() {
        PublishVersionResponse response = cfcClient.publishVersion("test-1560848182899", "1123", null);
        System.out.println(response);
    }

    @Test
    public void testListTrigger() {
        String functionBrn = "brn:bce:cfc:bj:7c83a9530352900ef3e38db05f1c10e9:function:test-1560848182899:$LATEST";
        ListTriggersResponse response = cfcClient.listTriggers(functionBrn);
        System.out.println(response);
    }

    @Test
    public void testCreateTrigger() {
        String target = "brn:bce:cfc:bj:7c83a9530352900ef3e38db05f1c10e9:function:test-1560848182899:$LATEST";
        String source = "cfc-http-trigger/v1/CFCAPI";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("AuthType", "anonymous");
        data.put("Method", "GET");
        data.put("ResourcePath", "hello002");

        CreateTriggerResponse response = cfcClient.createTrigger(target, source, data);
        System.out.println(response);

    }

    @Test
    public void testUpdateTrigger() {
        String relationId = "brn:bce:cfc-http-trigger:bj:7c83a9530352900ef3e38db05f1c10e9:ece4c25ee836060"
                + "f84d60fcc4a5477fd/cfc/GET/hello002";
        String source = "cfc-http-trigger/v1/CFCAPI";
        String target = "brn:bce:cfc:bj:7c83a9530352900ef3e38db05f1c10e9:function:test-1560487078143:2";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("AuthType", "anonymous");
        data.put("Method", "GET");
        data.put("ResourcePath", "hello1");

        UpdateTriggerResponse response = cfcClient.updateTrigger(relationId, target, source, data);
        System.out.println(response);
    }

    @Test
    public void testDeleteTrigger() {
        String relationId = "brn:bce:cfc-http-trigger:bj:7c83a9530352900ef3e38db05f1c10e9:ece4c25ee836060f8"
                + "4d60fcc4a5477fd/cfc/GET/hello1";
        String source = "cfc-http-trigger/v1/CFCAPI";
        String target = "brn:bce:cfc:bj:7c83a9530352900ef3e38db05f1c10e9:function:test-1560487078143:2";
        cfcClient.deleteTrigger(target, source, relationId);
    }

}
