package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.ModifyDatasetRequest;
import com.baidubce.services.aihc.model.dataset.ModifyDatasetResponse;
import com.baidubce.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class ExampleModifyDataset {
    /**
     * {@summary}
     * 主函数，用于调用AIHC ModifyDataset API接口。
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

        ModifyDatasetRequest request = new ModifyDatasetRequest();
        
        // 设置必选参数
        request.setDatasetId(System.getenv("DATASET_ID"));  // 数据集ID
        
        // 设置可选参数（根据需要修改）
        request.setName("my-dataset-1");  // 数据集名称
        request.setDescription("my dataset test modify");  // 数据集描述
        request.setVisibilityScope("ALL_PEOPLE");  // 可见范围：ALL_PEOPLE、ONLY_OWNER、USER_GROUP
        
        // 设置用户权限列表（可选）
        List<ModifyDatasetRequest.PermissionEntry> visibilityUser = new ArrayList<>();
        ModifyDatasetRequest.PermissionEntry user1 = new ModifyDatasetRequest.PermissionEntry();
        user1.setId("xxxxx");
        user1.setName("lisi");
        user1.setPermission("r");  // 只读权限
        visibilityUser.add(user1);
        
        ModifyDatasetRequest.PermissionEntry user2 = new ModifyDatasetRequest.PermissionEntry();
        user2.setId("xxxxx");
        user2.setName("wangwu");
        user2.setPermission("rw");  // 读写权限
        visibilityUser.add(user2);
        
        // request.setVisibilityUser(visibilityUser);
        
        // 设置用户组权限列表（可选）
        List<ModifyDatasetRequest.PermissionEntry> visibilityGroup = new ArrayList<>();
        ModifyDatasetRequest.PermissionEntry group1 = new ModifyDatasetRequest.PermissionEntry();
        group1.setId("group1");
        group1.setName("开发组");
        group1.setPermission("r");
        visibilityGroup.add(group1);
        
        // request.setVisibilityGroup(visibilityGroup);

        try {
            System.out.println("=== 开始修改数据集 ===");
            System.out.println("数据集ID: " + request.getDatasetId());
            System.out.println("新名称: " + request.getName());
            System.out.println("新描述: " + request.getDescription());
            System.out.println("新可见范围: " + request.getVisibilityScope());
            
            // 方式1：传统调用方式（向后兼容）
            System.out.println("\n--- 方式1：传统调用方式 ---");
            ModifyDatasetResponse response = client.dataset().modifyDataset(request);
            System.out.println("数据集修改成功！");
            System.out.println("Request ID: " + response.getMetadata().getBceRequestId());
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            ModifyDatasetResponse response2 = datasetModule.modifyDataset(request);
            System.out.println("模块化方式修改数据集成功！");
            System.out.println("Request ID: " + response2.getMetadata().getBceRequestId());
            
            // 打印完整的JSON响应
            System.out.println("\n=== API返回的JSON数据 ===");
            String jsonResponse = JsonUtils.toJsonString(response);
            System.out.println(jsonResponse);
            
            System.out.println("\n=== 修改的数据集信息 ===");
            System.out.println("数据集ID: " + request.getDatasetId());
            System.out.println("数据集名称: " + request.getName());
            System.out.println("数据集描述: " + request.getDescription());
            System.out.println("可见范围: " + request.getVisibilityScope());
            
            if (request.getVisibilityUser() != null && !request.getVisibilityUser().isEmpty()) {
                System.out.println("用户权限列表:");
                for (ModifyDatasetRequest.PermissionEntry user : request.getVisibilityUser()) {
                    System.out.println("  - 用户ID: " + user.getId() + ", 用户名: " + user.getName() + ", 权限: " + user.getPermission());
                }
            }
            
            if (request.getVisibilityGroup() != null && !request.getVisibilityGroup().isEmpty()) {
                System.out.println("用户组权限列表:");
                for (ModifyDatasetRequest.PermissionEntry group : request.getVisibilityGroup()) {
                    System.out.println("  - 用户组ID: " + group.getId() + ", 用户组名: " + group.getName() + ", 权限: " + group.getPermission());
                }
            }
            
        } catch (BceClientException e) {
            System.out.println("API调用失败: " + e.getMessage());
        }
    }
} 