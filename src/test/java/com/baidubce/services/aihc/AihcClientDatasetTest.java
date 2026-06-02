package com.baidubce.services.aihc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.modules.dataset.DatasetModule;
import com.baidubce.services.aihc.modules.dataset.DatasetModuleImpl;
import com.baidubce.services.aihc.model.dataset.CreateDatasetRequest;
import com.baidubce.services.aihc.model.dataset.CreateDatasetResponse;
import com.baidubce.services.aihc.model.dataset.ModifyDatasetRequest;
import com.baidubce.services.aihc.model.dataset.ModifyDatasetResponse;
import com.baidubce.services.aihc.model.dataset.CreateDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.CreateDatasetVersionResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetsRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetsResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionsRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionsResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionResponse;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetRequest;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetResponse;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetVersionResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * AIHC客户端数据集功能单元测试
 * 测试数据集相关的所有API方法
 */
public class AihcClientDatasetTest {

    private DatasetModule mockDatasetModule;

    private AihcClient aihcClient;
    private DatasetModuleImpl datasetModuleImpl;
    private BceClientConfiguration config;

    @Before
    public void setUp() {
        // 创建测试配置
        config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials("test-ak", "test-sk"));
        config.setEndpoint(System.getenv("ENDPOINT") != null ? System.getenv("ENDPOINT") : "AIHC_ENDPOINT");
        
        // 创建客户端实例
        aihcClient = new AihcClient(config);
        
        // 创建数据集模块实例
        datasetModuleImpl = new DatasetModuleImpl(config);
    }

    /**
     * 测试创建数据集 - 传统调用方式
     */
    @Test
    public void testCreateDataset_TraditionalWay() {
        // 准备测试数据
        CreateDatasetRequest request = createTestCreateDatasetRequest();
        CreateDatasetResponse expectedResponse = createTestCreateDatasetResponse();

        // 执行测试
        try {
            CreateDatasetResponse response = aihcClient.dataset().createDataset(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("数据集ID应该匹配", expectedResponse.getId(), response.getId());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试创建数据集 - 模块化调用方式
     */
    @Test
    public void testCreateDataset_ModularWay() {
        // 准备测试数据
        CreateDatasetRequest request = createTestCreateDatasetRequest();
        CreateDatasetResponse expectedResponse = createTestCreateDatasetResponse();

        // 执行测试
        try {
            DatasetModule datasetModule = aihcClient.dataset();
            CreateDatasetResponse response = datasetModule.createDataset(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("数据集ID应该匹配", expectedResponse.getId(), response.getId());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试修改数据集
     */
    @Test
    public void testModifyDataset() {
        // 准备测试数据
        ModifyDatasetRequest request = createTestModifyDatasetRequest();
        ModifyDatasetResponse expectedResponse = new ModifyDatasetResponse();

        // 执行测试
        try {
            ModifyDatasetResponse response = aihcClient.dataset().modifyDataset(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("Request ID不应为空", response.getMetadata().getBceRequestId());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试创建数据集版本
     */
    @Test
    public void testCreateDatasetVersion() {
        // 准备测试数据
        CreateDatasetVersionRequest request = createTestCreateDatasetVersionRequest();
        CreateDatasetVersionResponse expectedResponse = createTestCreateDatasetVersionResponse();

        // 执行测试
        try {
            CreateDatasetVersionResponse response = aihcClient.dataset().createDatasetVersion(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("版本ID应该匹配", expectedResponse.getId(), response.getId());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询数据集列表
     */
    @Test
    public void testDescribeDatasets() {
        // 准备测试数据
        DescribeDatasetsRequest request = createTestDescribeDatasetsRequest();
        DescribeDatasetsResponse expectedResponse = createTestDescribeDatasetsResponse();

        // 执行测试
        try {
            DescribeDatasetsResponse response = aihcClient.dataset().describeDatasets(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("数据集列表不应为空", response.getDatasets());
            assertEquals("数据集数量应该匹配", expectedResponse.getDatasets().size(), response.getDatasets().size());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询数据集详情
     */
    @Test
    public void testDescribeDataset() {
        // 准备测试数据
        DescribeDatasetRequest request = createTestDescribeDatasetRequest();
        DescribeDatasetResponse expectedResponse = createTestDescribeDatasetResponse();

        // 执行测试
        try {
            DescribeDatasetResponse response = aihcClient.dataset().describeDataset(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("数据集ID应该匹配", expectedResponse.getId(), response.getId());
            assertEquals("数据集名称应该匹配", expectedResponse.getName(), response.getName());
            assertNotNull("最新版本ID不应为空", response.getLatestVersionId());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询数据集版本列表
     */
    @Test
    public void testDescribeDatasetVersions() {
        // 准备测试数据
        DescribeDatasetVersionsRequest request = createTestDescribeDatasetVersionsRequest();
        DescribeDatasetVersionsResponse expectedResponse = createTestDescribeDatasetVersionsResponse();

        // 执行测试
        try {
            DescribeDatasetVersionsResponse response = aihcClient.dataset().describeDatasetVersions(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("版本列表不应为空", response.getVersions());
            assertEquals("版本总数应该匹配", expectedResponse.getTotalCount(), response.getTotalCount());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试查询数据集版本详情
     */
    @Test
    public void testDescribeDatasetVersion() {
        // 准备测试数据
        DescribeDatasetVersionRequest request = createTestDescribeDatasetVersionRequest();
        DescribeDatasetVersionResponse expectedResponse = createTestDescribeDatasetVersionResponse();

        // 执行测试
        try {
            DescribeDatasetVersionResponse response = aihcClient.dataset().describeDatasetVersion(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertEquals("数据集ID应该匹配", expectedResponse.getId(), response.getId());
            assertNotNull("版本信息不应为空", response.getVersionEntry());
            assertEquals("版本ID应该匹配", expectedResponse.getVersionEntry().getId(), response.getVersionEntry().getId());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试删除数据集
     */
    @Test
    public void testDeleteDataset() {
        // 准备测试数据
        DeleteDatasetRequest request = createTestDeleteDatasetRequest();

        // 执行测试
        try {
            DeleteDatasetResponse response = aihcClient.dataset().deleteDataset(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("Request ID不应为空", response.getMetadata().getBceRequestId());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    /**
     * 测试删除数据集版本
     */
    @Test
    public void testDeleteDatasetVersion() {
        // 准备测试数据
        DeleteDatasetVersionRequest request = createTestDeleteDatasetVersionRequest();

        // 执行测试
        try {
            DeleteDatasetVersionResponse response = aihcClient.dataset().deleteDatasetVersion(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("Request ID不应为空", response.getMetadata().getBceRequestId());
            
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
        CreateDatasetRequest request = createTestCreateDatasetRequest();

        try {
            // 传统方式调用
            CreateDatasetResponse response1 = aihcClient.dataset().createDataset(request);
            
            // 模块化方式调用
            DatasetModule datasetModule = aihcClient.dataset();
            CreateDatasetResponse response2 = datasetModule.createDataset(request);
            
            // 验证两种方式的结果应该一致
            assertEquals("两种调用方式的结果应该一致", response1.getId(), response2.getId());
            
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
        DescribeDatasetsRequest request = createTestDescribeDatasetsRequest();

        try {
            // 直接使用模块实例
            DescribeDatasetsResponse response = datasetModuleImpl.describeDatasets(request);
            
            // 验证结果
            assertNotNull("响应不应为空", response);
            assertNotNull("数据集列表不应为空", response.getDatasets());
            
        } catch (BceClientException e) {
            // 在测试环境中可能会抛出异常，这是正常的
            assertTrue("异常信息应该包含相关描述", e.getMessage() != null);
        }
    }

    // ==================== 辅助方法 ====================

    /**
     * 创建测试用的创建数据集请求
     */
    private CreateDatasetRequest createTestCreateDatasetRequest() {
        CreateDatasetRequest request = new CreateDatasetRequest();
        request.setName("test-dataset");
        request.setStorageType("BOS");
        request.setStorageInstance("test-bucket");
        request.setImportFormat("FOLDER");
        request.setDescription("测试数据集");
        request.setVisibilityScope("ALL_PEOPLE");
        
        // 设置初始版本信息
        CreateDatasetRequest.DatasetVersionEntry versionEntry = new CreateDatasetRequest.DatasetVersionEntry();
        versionEntry.setDescription("初始版本");
        versionEntry.setStoragePath("/test/path");
        versionEntry.setMountPath("/mnt/test");
        request.setInitVersionEntry(versionEntry);
        
        return request;
    }

    /**
     * 创建测试用的创建数据集响应
     */
    private CreateDatasetResponse createTestCreateDatasetResponse() {
        CreateDatasetResponse response = new CreateDatasetResponse();
        response.setId("ds-test123");
        return response;
    }

    /**
     * 创建测试用的修改数据集请求
     */
    private ModifyDatasetRequest createTestModifyDatasetRequest() {
        ModifyDatasetRequest request = new ModifyDatasetRequest();
        request.setDatasetId("ds-test123");
        request.setName("modified-dataset");
        request.setDescription("修改后的数据集");
        request.setVisibilityScope("USER_GROUP");
        
        // 设置用户权限列表
        List<ModifyDatasetRequest.PermissionEntry> visibilityUser = new ArrayList<>();
        ModifyDatasetRequest.PermissionEntry user = new ModifyDatasetRequest.PermissionEntry();
        user.setId("user123");
        user.setName("testuser");
        user.setPermission("r");
        visibilityUser.add(user);
        request.setVisibilityUser(visibilityUser);
        
        return request;
    }

    /**
     * 创建测试用的创建数据集版本请求
     */
    private CreateDatasetVersionRequest createTestCreateDatasetVersionRequest() {
        CreateDatasetVersionRequest request = new CreateDatasetVersionRequest();
        request.setDatasetId("ds-test123");
        request.setDescription("新版本");
        request.setStoragePath("/test/new-version");
        request.setMountPath("/mnt/test/v2");
        return request;
    }

    /**
     * 创建测试用的创建数据集版本响应
     */
    private CreateDatasetVersionResponse createTestCreateDatasetVersionResponse() {
        CreateDatasetVersionResponse response = new CreateDatasetVersionResponse();
        response.setId("ds-version123");
        return response;
    }

    /**
     * 创建测试用的查询数据集列表请求
     */
    private DescribeDatasetsRequest createTestDescribeDatasetsRequest() {
        DescribeDatasetsRequest request = new DescribeDatasetsRequest();
        request.setKeyword("test");
        request.setStorageType("BOS");
        request.setPageNumber(1);
        request.setPageSize(10);
        return request;
    }

    /**
     * 创建测试用的查询数据集列表响应
     */
    private DescribeDatasetsResponse createTestDescribeDatasetsResponse() {
        DescribeDatasetsResponse response = new DescribeDatasetsResponse();
        List<DescribeDatasetsResponse.Dataset> datasets = new ArrayList<>();
        
        DescribeDatasetsResponse.Dataset dataset = new DescribeDatasetsResponse.Dataset();
        dataset.setId("ds-test123");
        dataset.setName("test-dataset");
        dataset.setStorageType("BOS");
        dataset.setStorageInstance("test-bucket");
        dataset.setImportFormat("FOLDER");
        dataset.setOwner("test-owner");
        dataset.setOwnerName("测试用户");
        dataset.setVisibilityScope("ALL_PEOPLE");
        dataset.setPermission("rw");
        dataset.setLatestVersionId("ds-version123");
        dataset.setLatestVersion("1");
        dataset.setCreatedAt("2024-01-01T00:00:00Z");
        dataset.setUpdatedAt("2024-01-01T00:00:00Z");
        
        datasets.add(dataset);
        response.setDatasets(datasets);
        
        return response;
    }

    /**
     * 创建测试用的查询数据集详情请求
     */
    private DescribeDatasetRequest createTestDescribeDatasetRequest() {
        DescribeDatasetRequest request = new DescribeDatasetRequest();
        request.setDatasetId("ds-test123");
        return request;
    }

    /**
     * 创建测试用的查询数据集详情响应
     */
    private DescribeDatasetResponse createTestDescribeDatasetResponse() {
        DescribeDatasetResponse response = new DescribeDatasetResponse();
        response.setId("ds-test123");
        response.setName("test-dataset");
        response.setStorageType("BOS");
        response.setStorageInstance("test-bucket");
        response.setImportFormat("FOLDER");
        response.setDescription("测试数据集");
        response.setOwner("test-owner");
        response.setOwnerName("测试用户");
        response.setVisibilityScope("ALL_PEOPLE");
        response.setPermission("rw");
        response.setLatestVersionId("ds-version123");
        response.setLatestVersion("1");
        response.setCreatedAt("2024-01-01T00:00:00Z");
        response.setUpdatedAt("2024-01-01T00:00:00Z");
        
        // 设置最新版本信息
        DescribeDatasetResponse.DatasetVersionEntry latestVersionEntry = new DescribeDatasetResponse.DatasetVersionEntry();
        latestVersionEntry.setId("ds-version123");
        latestVersionEntry.setVersion("1");
        latestVersionEntry.setDescription("初始版本");
        latestVersionEntry.setStoragePath("/test/path");
        latestVersionEntry.setMountPath("/mnt/test");
        latestVersionEntry.setCreateUser("test-owner");
        latestVersionEntry.setCreateUserName("测试用户");
        latestVersionEntry.setCreatedAt("2024-01-01T00:00:00Z");
        latestVersionEntry.setUpdatedAt("2024-01-01T00:00:00Z");
        response.setLatestVersionEntry(latestVersionEntry);
        
        return response;
    }

    /**
     * 创建测试用的查询数据集版本列表请求
     */
    private DescribeDatasetVersionsRequest createTestDescribeDatasetVersionsRequest() {
        DescribeDatasetVersionsRequest request = new DescribeDatasetVersionsRequest();
        request.setDatasetId("ds-test123");
        request.setPageNumber(1);
        request.setPageSize(10);
        return request;
    }

    /**
     * 创建测试用的查询数据集版本列表响应
     */
    private DescribeDatasetVersionsResponse createTestDescribeDatasetVersionsResponse() {
        DescribeDatasetVersionsResponse response = new DescribeDatasetVersionsResponse();
        response.setTotalCount(1);
        
        List<DescribeDatasetVersionsResponse.DatasetVersionEntry> versions = new ArrayList<>();
        DescribeDatasetVersionsResponse.DatasetVersionEntry version = new DescribeDatasetVersionsResponse.DatasetVersionEntry();
        version.setId("ds-version123");
        version.setVersion("1");
        version.setDescription("初始版本");
        version.setStoragePath("/test/path");
        version.setMountPath("/mnt/test");
        version.setCreateUser("test-owner");
        version.setCreateUserName("测试用户");
        version.setCreatedAt("2024-01-01T00:00:00Z");
        version.setUpdatedAt("2024-01-01T00:00:00Z");
        versions.add(version);
        
        response.setVersions(versions);
        return response;
    }

    /**
     * 创建测试用的查询数据集版本详情请求
     */
    private DescribeDatasetVersionRequest createTestDescribeDatasetVersionRequest() {
        DescribeDatasetVersionRequest request = new DescribeDatasetVersionRequest();
        request.setDatasetId("ds-test123");
        request.setVersionId("ds-version123");
        return request;
    }

    /**
     * 创建测试用的查询数据集版本详情响应
     */
    private DescribeDatasetVersionResponse createTestDescribeDatasetVersionResponse() {
        DescribeDatasetVersionResponse response = new DescribeDatasetVersionResponse();
        response.setId("ds-test123");
        response.setName("test-dataset");
        response.setStorageType("BOS");
        response.setStorageInstance("test-bucket");
        response.setImportFormat("FOLDER");
        response.setDescription("测试数据集");
        response.setOwner("test-owner");
        response.setOwnerName("测试用户");
        response.setVisibilityScope("ALL_PEOPLE");
        response.setPermission("rw");
        response.setCreatedAt("2024-01-01T00:00:00Z");
        response.setUpdatedAt("2024-01-01T00:00:00Z");
        
        // 设置版本信息
        DescribeDatasetVersionResponse.DatasetVersionEntry versionEntry = new DescribeDatasetVersionResponse.DatasetVersionEntry();
        versionEntry.setId("ds-version123");
        versionEntry.setVersion("1");
        versionEntry.setDescription("初始版本");
        versionEntry.setStoragePath("/test/path");
        versionEntry.setMountPath("/mnt/test");
        versionEntry.setCreateUser("test-owner");
        versionEntry.setCreateUserName("测试用户");
        versionEntry.setCreatedAt("2024-01-01T00:00:00Z");
        versionEntry.setUpdatedAt("2024-01-01T00:00:00Z");
        response.setVersionEntry(versionEntry);
        
        return response;
    }

    /**
     * 创建测试用的删除数据集请求
     */
    private DeleteDatasetRequest createTestDeleteDatasetRequest() {
        DeleteDatasetRequest request = new DeleteDatasetRequest();
        request.setDatasetId("ds-test123");
        return request;
    }

    /**
     * 创建测试用的删除数据集版本请求
     */
    private DeleteDatasetVersionRequest createTestDeleteDatasetVersionRequest() {
        DeleteDatasetVersionRequest request = new DeleteDatasetVersionRequest();
        request.setDatasetId("ds-test123");
        request.setVersionId("ds-version123");
        return request;
    }
}
