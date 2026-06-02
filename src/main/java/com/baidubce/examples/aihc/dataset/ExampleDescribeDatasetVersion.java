package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionResponse;
import com.baidubce.util.JsonUtils;

public class ExampleDescribeDatasetVersion {
    /**
     * {@summary}
     * 主函数，用于调用AIHC DescribeDatasetVersion API接口。
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

        DescribeDatasetVersionRequest request = new DescribeDatasetVersionRequest();
        // 设置必选参数
        request.setDatasetId(System.getenv("DATASET_ID"));  // 数据集ID
        request.setVersionId(System.getenv("DATASET_VERSION_ID"));  // 数据集版本ID

        try {
            // 方式1：传统调用方式（向后兼容）
            System.out.println("--- 方式1：传统调用方式 ---");
            DescribeDatasetVersionResponse response = client.dataset().describeDatasetVersion(request);
            System.out.println("数据集ID: " + response.getId());
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            DescribeDatasetVersionResponse response2 = datasetModule.describeDatasetVersion(request);
            System.out.println("模块化方式查询数据集ID: " + response2.getId());
            System.out.println("数据集名称: " + response.getName());
            System.out.println("存储类型: " + response.getStorageType());
            System.out.println("存储实例: " + response.getStorageInstance());
            System.out.println("导入格式: " + response.getImportFormat());
            System.out.println("描述: " + response.getDescription());
            System.out.println("所有者: " + response.getOwner());
            System.out.println("所有者名称: " + response.getOwnerName());
            System.out.println("可见范围: " + response.getVisibilityScope());
            System.out.println("权限: " + response.getPermission());
            System.out.println("创建时间: " + response.getCreatedAt());
            System.out.println("更新时间: " + response.getUpdatedAt());
            
            // 打印版本信息
            if (response.getVersionEntry() != null) {
                DescribeDatasetVersionResponse.DatasetVersionEntry versionEntry = response.getVersionEntry();
                System.out.println("\n=== 版本信息 ===");
                System.out.println("版本ID: " + versionEntry.getId());
                System.out.println("版本号: " + versionEntry.getVersion());
                System.out.println("版本描述: " + versionEntry.getDescription());
                System.out.println("存储路径: " + versionEntry.getStoragePath());
                System.out.println("挂载路径: " + versionEntry.getMountPath());
                System.out.println("创建用户: " + versionEntry.getCreateUser());
                System.out.println("创建用户名: " + versionEntry.getCreateUserName());
                System.out.println("版本创建时间: " + versionEntry.getCreatedAt());
                System.out.println("版本更新时间: " + versionEntry.getUpdatedAt());
            }
            
            // 打印用户权限列表
            if (response.getVisibilityUser() != null && !response.getVisibilityUser().isEmpty()) {
                System.out.println("\n=== 用户权限列表 ===");
                for (DescribeDatasetVersionResponse.PermissionEntry user : response.getVisibilityUser()) {
                    System.out.println("用户ID: " + user.getId() + ", 用户名: " + user.getName() + ", 权限: " + user.getPermission());
                }
            }
            
            // 打印用户组权限列表
            if (response.getVisibilityGroup() != null && !response.getVisibilityGroup().isEmpty()) {
                System.out.println("\n=== 用户组权限列表 ===");
                for (DescribeDatasetVersionResponse.PermissionEntry group : response.getVisibilityGroup()) {
                    System.out.println("用户组ID: " + group.getId() + ", 用户组名: " + group.getName() + ", 权限: " + group.getPermission());
                }
            }
            
            // 打印完整的JSON响应
            System.out.println("\n=== API返回的JSON数据 ===");
            String jsonResponse = JsonUtils.toJsonString(response);
            System.out.println(jsonResponse);
        } catch (BceClientException e) {
            System.out.println("API调用失败: " + e.getMessage());
        }
    }
} 