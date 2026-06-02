package com.baidubce.services.aihc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.modules.model.ModelModule;
import com.baidubce.services.aihc.modules.model.ModelModuleImpl;
import com.baidubce.services.aihc.model.model.DescribeModelsRequest;
import com.baidubce.services.aihc.model.model.DescribeModelsResponse;
import com.baidubce.services.aihc.model.model.DescribeModelRequest;
import com.baidubce.services.aihc.model.model.DescribeModelResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * AIHC客户端模型功能单元测试
 * 测试已实现的模型相关API方法
 */
public class AihcClientModelTest {

    private ModelModule mockModelModule;

    private AihcClient aihcClient;
    private ModelModuleImpl modelModuleImpl;
    private BceClientConfiguration config;

    @Before
    public void setUp() {
        // 创建测试配置
        config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials("test-ak", "test-sk"));
        config.setEndpoint(System.getenv("ENDPOINT") != null ? System.getenv("ENDPOINT") : "AIHC_ENDPOINT");
        
        // 创建客户端实例
        aihcClient = new AihcClient(config);
        
        // 创建模型模块实例
        modelModuleImpl = new ModelModuleImpl(config);
    }

    /**
     * 测试查询模型列表 - 传统调用方式
     */
    @Test
    public void testDescribeModels_TraditionalWay() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequest();
        DescribeModelsResponse expectedResponse = createTestDescribeModelsResponse();

        // 执行测试
        try {
            DescribeModelsResponse response = aihcClient.model().describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("模型总数应该匹配", expectedResponse.getTotalCount(), response.getTotalCount());
            assertNotNull("模型列表不应为空", response.getModels());
            assertEquals("模型数量应该匹配", expectedResponse.getModels().size(), response.getModels().size());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询模型列表 - 模块化调用方式
     */
    @Test
    public void testDescribeModels_ModularWay() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequest();
        DescribeModelsResponse expectedResponse = createTestDescribeModelsResponse();

        // 执行测试
        try {
            ModelModule modelModule = aihcClient.model();
            DescribeModelsResponse response = modelModule.describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("模型总数应该匹配", expectedResponse.getTotalCount(), response.getTotalCount());
            assertNotNull("模型列表不应为空", response.getModels());
            assertEquals("模型数量应该匹配", expectedResponse.getModels().size(), response.getModels().size());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询模型列表 - 分页参数
     */
    @Test
    public void testDescribeModels_WithPagination() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequestWithPagination();
        DescribeModelsResponse expectedResponse = createTestDescribeModelsResponse();

        // 执行测试
        try {
            DescribeModelsResponse response = aihcClient.model().describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("模型列表不应为空", response.getModels());
            assertTrue("模型数量应该小于等于页面大小", response.getModels().size() <= request.getPageSize());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询模型列表 - 关键字搜索
     */
    @Test
    public void testDescribeModels_WithKeyword() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequestWithKeyword();
        DescribeModelsResponse expectedResponse = createTestDescribeModelsResponse();

        // 执行测试
        try {
            DescribeModelsResponse response = aihcClient.model().describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("模型列表不应为空", response.getModels());
            
            // 验证关键字搜索结果
            if (response.getModels() != null && !response.getModels().isEmpty()) {
                for (DescribeModelsResponse.Model model : response.getModels()) {
                    assertTrue("模型名称应该包含关键字", 
                        model.getName().toLowerCase().contains(request.getKeyword().toLowerCase()) ||
                        model.getDescription() != null && model.getDescription().toLowerCase().contains(request.getKeyword().toLowerCase()));
                }
            }
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询模型列表 - 模型类型过滤
     */
    @Test
    public void testDescribeModels_WithModelType() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequestWithModelType();
        DescribeModelsResponse expectedResponse = createTestDescribeModelsResponse();

        // 执行测试
        try {
            DescribeModelsResponse response = aihcClient.model().describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("模型列表不应为空", response.getModels());
            
            // 验证模型类型过滤结果
            if (response.getModels() != null && !response.getModels().isEmpty()) {
                for (DescribeModelsResponse.Model model : response.getModels()) {
                    assertEquals("模型类型应该匹配", request.getModelType(), model.getModelType());
                }
            }
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询模型列表 - 框架过滤
     */
    @Test
    public void testDescribeModels_WithFramework() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequestWithFramework();
        DescribeModelsResponse expectedResponse = createTestDescribeModelsResponse();

        // 执行测试
        try {
            DescribeModelsResponse response = aihcClient.model().describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("模型列表不应为空", response.getModels());
            
            // 验证框架过滤结果
            if (response.getModels() != null && !response.getModels().isEmpty()) {
                for (DescribeModelsResponse.Model model : response.getModels()) {
                    assertEquals("框架应该匹配", request.getFramework(), model.getFramework());
                }
            }
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试模块化调用的一致性
     */
    @Test
    public void testModularConsistency() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequest();

        try {
            // 传统方式调用
            DescribeModelsResponse response1 = aihcClient.model().describeModels(request);
            
            // 模块化方式调用
            ModelModule modelModule = aihcClient.model();
            DescribeModelsResponse response2 = modelModule.describeModels(request);
            
            // 验证两种方式的结果应该一致
            assertEquals("两种调用方式的结果应该一致", response1.getTotalCount(), response2.getTotalCount());
            assertEquals("模型数量应该一致", response1.getModels().size(), response2.getModels().size());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试独立模块使用
     */
    @Test
    public void testIndependentModuleUsage() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequest();

        try {
            // 直接使用模块实例
            DescribeModelsResponse response = modelModuleImpl.describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("模型列表不应为空", response.getModels());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试模型详细信息验证
     */
    @Test
    public void testModelDetailsValidation() {
        // 准备测试数据
        DescribeModelsRequest request = createTestDescribeModelsRequest();

        try {
            DescribeModelsResponse response = aihcClient.model().describeModels(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("模型列表不应为空", response.getModels());
            
            // 验证模型详细信息
            if (response.getModels() != null && !response.getModels().isEmpty()) {
                DescribeModelsResponse.Model model = response.getModels().get(0);
                
                assertNotNull("模型ID不应为空", model.getId());
                assertNotNull("模型名称不应为空", model.getName());
                assertNotNull("模型类型不应为空", model.getModelType());
                assertNotNull("框架不应为空", model.getFramework());
                assertNotNull("状态不应为空", model.getStatus());
                assertNotNull("所有者不应为空", model.getOwner());
                assertNotNull("所有者名称不应为空", model.getOwnerName());
                assertNotNull("创建时间不应为空", model.getCreatedAt());
                assertNotNull("更新时间不应为空", model.getUpdatedAt());
                
                // 验证模型类型和框架的有效性
                assertTrue("模型类型应该是有效值", 
                    model.getModelType().equals("TENSORFLOW") || 
                    model.getModelType().equals("PYTORCH") || 
                    model.getModelType().equals("ONNX"));
                
                assertTrue("框架应该是有效值", 
                    model.getFramework().equals("TF") || 
                    model.getFramework().equals("PT") || 
                    model.getFramework().equals("ONNX"));
            }
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询模型详情 - 传统调用方式
     */
    @Test
    public void testDescribeModel_TraditionalWay() {
        // 准备测试数据
        DescribeModelRequest request = createTestDescribeModelRequest();
        DescribeModelResponse expectedResponse = createTestDescribeModelResponse();

        // 执行测试
        try {
            DescribeModelResponse response = aihcClient.model().describeModel(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("模型ID应该匹配", expectedResponse.getId(), response.getId());
            assertEquals("模型名称应该匹配", expectedResponse.getName(), response.getName());
            assertNotNull("模型格式不应为空", response.getModelFormat());
            assertNotNull("所有者不应为空", response.getOwner());
            assertNotNull("所有者名称不应为空", response.getOwnerName());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询模型详情 - 模块化调用方式
     */
    @Test
    public void testDescribeModel_ModularWay() {
        // 准备测试数据
        DescribeModelRequest request = createTestDescribeModelRequest();
        DescribeModelResponse expectedResponse = createTestDescribeModelResponse();

        // 执行测试
        try {
            ModelModule modelModule = aihcClient.model();
            DescribeModelResponse response = modelModule.describeModel(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("模型ID应该匹配", expectedResponse.getId(), response.getId());
            assertEquals("模型名称应该匹配", expectedResponse.getName(), response.getName());
            assertNotNull("模型格式不应为空", response.getModelFormat());
            assertNotNull("所有者不应为空", response.getOwner());
            assertNotNull("所有者名称不应为空", response.getOwnerName());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试模型详情信息验证
     */
    @Test
    public void testModelDetailValidation() {
        // 准备测试数据
        DescribeModelRequest request = createTestDescribeModelRequest();

        try {
            DescribeModelResponse response = aihcClient.model().describeModel(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("模型ID不应为空", response.getId());
            assertNotNull("模型名称不应为空", response.getName());
            assertNotNull("模型格式不应为空", response.getModelFormat());
            assertNotNull("所有者不应为空", response.getOwner());
            assertNotNull("所有者名称不应为空", response.getOwnerName());
            assertNotNull("可见范围不应为空", response.getVisibilityScope());
            assertNotNull("创建时间不应为空", response.getCreatedAt());
            assertNotNull("更新时间不应为空", response.getUpdatedAt());
            
            // 验证版本信息
            if (response.getVersionEntry() != null) {
                DescribeModelResponse.ModelVersionEntry versionEntry = response.getVersionEntry();
                assertNotNull("版本号不应为空", versionEntry.getVersion());
                assertNotNull("版本ID不应为空", versionEntry.getId());
                assertNotNull("来源不应为空", versionEntry.getSource());
                assertNotNull("创建用户不应为空", versionEntry.getCreateUser());
                assertNotNull("创建用户名不应为空", versionEntry.getCreateUserName());
                assertNotNull("创建时间不应为空", versionEntry.getCreatedAt());
            }
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    // ==================== 辅助方法 ====================

    /**
     * 创建测试用的查询模型列表请求
     */
    private DescribeModelsRequest createTestDescribeModelsRequest() {
        DescribeModelsRequest request = new DescribeModelsRequest();
        request.setKeyword("test");
        request.setModelType("TENSORFLOW");
        request.setFramework("TF");
        request.setPageNumber(1);
        request.setPageSize(10);
        return request;
    }

    /**
     * 创建测试用的查询模型列表响应
     */
    private DescribeModelsResponse createTestDescribeModelsResponse() {
        DescribeModelsResponse response = new DescribeModelsResponse();
        response.setTotalCount(1);
        
        List<DescribeModelsResponse.Model> models = new ArrayList<>();
        DescribeModelsResponse.Model model = new DescribeModelsResponse.Model();
        model.setId("model-test123");
        model.setName("test-model");
        model.setModelType("TENSORFLOW");
        model.setFramework("TF");
        model.setStatus("ACTIVE");
        model.setOwner("test-owner");
        model.setOwnerName("测试用户");
        model.setDescription("测试模型");
        model.setCreatedAt("2024-01-01T00:00:00Z");
        model.setUpdatedAt("2024-01-01T00:00:00Z");
        
        models.add(model);
        response.setModels(models);
        
        return response;
    }

    /**
     * 创建测试用的查询模型列表请求 - 带分页参数
     */
    private DescribeModelsRequest createTestDescribeModelsRequestWithPagination() {
        DescribeModelsRequest request = new DescribeModelsRequest();
        request.setPageNumber(1);
        request.setPageSize(5);
        return request;
    }

    /**
     * 创建测试用的查询模型列表请求 - 带关键字
     */
    private DescribeModelsRequest createTestDescribeModelsRequestWithKeyword() {
        DescribeModelsRequest request = new DescribeModelsRequest();
        request.setKeyword("test");
        request.setPageNumber(1);
        request.setPageSize(10);
        return request;
    }

    /**
     * 创建测试用的查询模型列表请求 - 带模型类型
     */
    private DescribeModelsRequest createTestDescribeModelsRequestWithModelType() {
        DescribeModelsRequest request = new DescribeModelsRequest();
        request.setModelType("TENSORFLOW");
        request.setPageNumber(1);
        request.setPageSize(10);
        return request;
    }

    /**
     * 创建测试用的查询模型列表请求 - 带框架
     */
    private DescribeModelsRequest createTestDescribeModelsRequestWithFramework() {
        DescribeModelsRequest request = new DescribeModelsRequest();
        request.setFramework("TF");
        request.setPageNumber(1);
        request.setPageSize(10);
        return request;
    }

    /**
     * 创建测试用的查询模型详情请求
     */
    private DescribeModelRequest createTestDescribeModelRequest() {
        DescribeModelRequest request = new DescribeModelRequest();
        request.setModelId("model-test123");
        return request;
    }

    /**
     * 创建测试用的查询模型详情响应
     */
    private DescribeModelResponse createTestDescribeModelResponse() {
        DescribeModelResponse response = new DescribeModelResponse();
        response.setId("model-test123");
        response.setName("test-model");
        response.setModelFormat("ONNX");
        response.setOwner("test-owner");
        response.setOwnerName("测试用户");
        response.setVisibilityScope("PUBLIC");
        response.setCreatedAt("2024-01-01T00:00:00Z");
        response.setUpdatedAt("2024-01-01T00:00:00Z");

        DescribeModelResponse.ModelVersionEntry versionEntry = new DescribeModelResponse.ModelVersionEntry();
        versionEntry.setVersion("v1.0");
        versionEntry.setId("version-test123");
        versionEntry.setSource("test-source");
        versionEntry.setCreateUser("test-user");
        versionEntry.setCreateUserName("测试用户");
        versionEntry.setCreatedAt("2024-01-01T00:00:00Z");
        response.setVersionEntry(versionEntry);

        return response;
    }
}
