package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetResponse;
import com.baidubce.util.JsonUtils;

public class ExampleDescribeDataset {
    /**
     * {@summary}
     * 主函数，用于调用AIHC DescribeDataset API接口。
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

        DescribeDatasetRequest request = new DescribeDatasetRequest();
        // 设置必选参数
        request.setDatasetId(System.getenv("DATASET_ID"));  // 数据集ID

        try {
            // 方式1：传统调用方式（向后兼容）
            System.out.println("--- 方式1：传统调用方式 ---");
            DescribeDatasetResponse response = client.dataset().describeDataset(request);
            System.out.println("数据集ID: " + response.getId());
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            DescribeDatasetResponse response2 = datasetModule.describeDataset(request);
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
            System.out.println("最新版本ID: " + response.getLatestVersionId());
            System.out.println("最新版本号: " + response.getLatestVersion());
            System.out.println("创建时间: " + response.getCreatedAt());
            System.out.println("更新时间: " + response.getUpdatedAt());
            
            // 打印最新版本信息
            if (response.getLatestVersionEntry() != null) {
                DescribeDatasetResponse.DatasetVersionEntry latestVersionEntry = response.getLatestVersionEntry();
                System.out.println("\n=== 最新版本信息 ===");
                System.out.println("版本ID: " + latestVersionEntry.getId());
                System.out.println("版本号: " + latestVersionEntry.getVersion());
                System.out.println("版本描述: " + latestVersionEntry.getDescription());
                System.out.println("存储路径: " + latestVersionEntry.getStoragePath());
                System.out.println("挂载路径: " + latestVersionEntry.getMountPath());
                System.out.println("创建用户: " + latestVersionEntry.getCreateUser());
                System.out.println("创建用户名: " + latestVersionEntry.getCreateUserName());
                System.out.println("版本创建时间: " + latestVersionEntry.getCreatedAt());
                System.out.println("版本更新时间: " + latestVersionEntry.getUpdatedAt());
            }
            
            // 打印用户权限列表
            if (response.getVisibilityUser() != null && !response.getVisibilityUser().isEmpty()) {
                System.out.println("\n=== 用户权限列表 ===");
                for (DescribeDatasetResponse.PermissionEntry user : response.getVisibilityUser()) {
                    System.out.println("用户ID: " + user.getId() + ", 用户名: " + user.getName() + ", 权限: " + user.getPermission());
                }
            }
            
            // 打印用户组权限列表
            if (response.getVisibilityGroup() != null && !response.getVisibilityGroup().isEmpty()) {
                System.out.println("\n=== 用户组权限列表 ===");
                for (DescribeDatasetResponse.PermissionEntry group : response.getVisibilityGroup()) {
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