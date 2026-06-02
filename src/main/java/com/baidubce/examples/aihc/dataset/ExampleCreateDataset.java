package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.CreateDatasetRequest;
import com.baidubce.services.aihc.model.dataset.CreateDatasetResponse;
import com.baidubce.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class ExampleCreateDataset {
    /**
     * {@summary}
     * 主函数，用于调用AIHC CreateDataset API接口。
     * 需要先设置AK和SK，然后在Baidu Cloud Console中获取Endpoint。
     * 如果环境变量BCE_ACCESS_KEY_ID和BCE_SECRET_ACCESS_KEY存在，则使用这两个值作为AK和SK；否则使用传入的AK和SK。
     * 默认使用HTTPS协议。
     *
     * @param args 无参数
     * @throws BceClientException 如果API调用失败，会抛出此异常
     */
    public static void main(String[] args) {
        String ak = System.getenv("BCE_ACCESS_KEY_ID") != null ? System.getenv("BCE_ACCESS_KEY_ID") : "Your AK";
        String sk = System.getenv("BCE_SECRET_ACCESS_KEY") != null ? System.getenv("BCE_SECRET_ACCESS_KEY") : "Your SK";
        String endpoint = System.getenv("ENDPOINT") != null ? System.getenv("ENDPOINT") : "AIHC_ENDPOINT";

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        AihcClient client = new AihcClient(config);

        CreateDatasetRequest request = new CreateDatasetRequest();
        
        // 设置必选参数
        request.setName("test-dataset-11111");  // 数据集名称
        request.setStorageType("BOS");  // 存储类型：PFS 或 BOS
        request.setStorageInstance("bucket1");  // 存储实例ID
        request.setImportFormat("FOLDER");  // 导入格式：FILE 或 FOLDER
        request.setVisibilityScope("USER_GROUP");  // 可见范围：ALL_PEOPLE、ONLY_OWNER、USER_GROUP
        
        // 设置可选参数
        request.setDescription("test dataset");  // 数据集描述
        request.setOwner("xxx");  // 修改为实际的owner id（可选）
        
        // 设置用户权限列表（可选）
        List<CreateDatasetRequest.PermissionEntry> visibilityUser = new ArrayList<>();
        CreateDatasetRequest.PermissionEntry user1 = new CreateDatasetRequest.PermissionEntry();
        user1.setId("xxxxx"); // 修改为实际的user id
        user1.setName("lisi");
        user1.setPermission("r");  // 只读权限
        visibilityUser.add(user1);
        
        CreateDatasetRequest.PermissionEntry user2 = new CreateDatasetRequest.PermissionEntry();
        user2.setId("xxxxx"); // 修改为实际的user id
        user2.setName("wangwu");
        user2.setPermission("rw");  // 读写权限
        visibilityUser.add(user2);
        
        request.setVisibilityUser(visibilityUser);
        
        // 设置用户组权限列表（可选）
        List<CreateDatasetRequest.PermissionEntry> visibilityGroup = new ArrayList<>();
        CreateDatasetRequest.PermissionEntry group1 = new CreateDatasetRequest.PermissionEntry();
        group1.setId("group1");
        group1.setName("开发组");
        group1.setPermission("r");
        visibilityGroup.add(group1);
        
        request.setVisibilityGroup(visibilityGroup);
        
        // 设置初始版本信息（必选）
        CreateDatasetRequest.DatasetVersionEntry initVersionEntry = new CreateDatasetRequest.DatasetVersionEntry();
        initVersionEntry.setDescription("dataset first version");
        initVersionEntry.setStoragePath("/path/to/dir");
        initVersionEntry.setMountPath("/mnt/datasets/test1");
        request.setInitVersionEntry(initVersionEntry);

        try {
            System.out.println("=== 开始创建数据集 ===");
            System.out.println("数据集名称: " + request.getName());
            System.out.println("存储类型: " + request.getStorageType());
            System.out.println("存储实例: " + request.getStorageInstance());
            System.out.println("导入格式: " + request.getImportFormat());
            System.out.println("可见范围: " + request.getVisibilityScope());
            System.out.println("描述: " + request.getDescription());
            
            // 方式1：传统调用方式（向后兼容）
            System.out.println("\n--- 方式1：传统调用方式 ---");
            CreateDatasetResponse response = client.dataset().createDataset(request);
            System.out.println("数据集创建成功！");
            System.out.println("数据集ID: " + response.getId());
            System.out.println("Request ID: " + response.getMetadata().getBceRequestId());
            
            // 方式2：模块化调用方式（推荐）
            request.setName("test22222");  // 数据集名称
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            CreateDatasetResponse response2 = datasetModule.createDataset(request);
            System.out.println("模块化方式创建数据集成功！");
            System.out.println("数据集ID: " + response2.getId());
            System.out.println("Request ID: " + response2.getMetadata().getBceRequestId());
            
            // 打印完整的JSON响应
            System.out.println("\n=== API返回的JSON数据 ===");
            String jsonResponse = JsonUtils.toJsonString(response);
            System.out.println(jsonResponse);
            
            System.out.println("\n=== 创建的数据集信息 ===");
            System.out.println("数据集ID: " + response.getId());
            System.out.println("数据集名称: " + request.getName());
            System.out.println("存储类型: " + request.getStorageType());
            System.out.println("存储实例: " + request.getStorageInstance());
            System.out.println("导入格式: " + request.getImportFormat());
            System.out.println("可见范围: " + request.getVisibilityScope());
            System.out.println("描述: " + request.getDescription());
            System.out.println("初始版本描述: " + initVersionEntry.getDescription());
            System.out.println("存储路径: " + initVersionEntry.getStoragePath());
            System.out.println("挂载路径: " + initVersionEntry.getMountPath());
            
        } catch (BceClientException e) {
            System.out.println("API调用失败: " + e.getMessage());
        }
    }
} 