package com.baidubce.examples.aihc.model;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.model.DescribeModelRequest;
import com.baidubce.services.aihc.model.model.DescribeModelResponse;
import com.baidubce.BceClientException;

/**
 * 获取模型详情的示例
 * 
 * 本示例演示如何使用AIHC SDK获取指定模型的详细信息
 */
public class ExampleDescribeModel {

    public static void main(String[] args) {
        // 配置客户端
        String accessKey = System.getenv("BCE_ACCESS_KEY_ID");
        String secretKey = System.getenv("BCE_SECRET_ACCESS_KEY");
        
        if (accessKey == null || secretKey == null) {
            System.out.println("请设置环境变量 BCE_ACCESS_KEY_ID 和 BCE_SECRET_ACCESS_KEY");
            return;
        }

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(accessKey, secretKey));
        config.setEndpoint("aihc.bj.baidubce.com");

        AihcClient client = new AihcClient(config);

        // 设置要查询的模型ID
        String modelId = "mo-reI5ejY5"; // 请替换为实际的模型ID

        try {
            // 方式1：传统调用方式（向后兼容）
            System.out.println("\n--- 方式1：传统调用方式 ---");
            DescribeModelRequest request = new DescribeModelRequest();
            request.setModelId(modelId);

            DescribeModelResponse response = client.model().describeModel(request);
            
            System.out.println("模型详情获取成功！");
            System.out.println("Request ID: " + response.getMetadata().getBceRequestId());
            System.out.println("模型ID: " + response.getId());
            System.out.println("模型名称: " + response.getName());
            System.out.println("模型格式: " + response.getModelFormat());
            System.out.println("描述: " + response.getDescription());
            System.out.println("所有者: " + response.getOwnerName());
            System.out.println("可见范围: " + response.getVisibilityScope());
            System.out.println("创建时间: " + response.getCreatedAt());
            System.out.println("更新时间: " + response.getUpdatedAt());

            // 显示版本信息
            if (response.getVersionEntry() != null) {
                DescribeModelResponse.ModelVersionEntry versionEntry = response.getVersionEntry();
                System.out.println("\n最新版本信息:");
                System.out.println("  版本号: " + versionEntry.getVersion());
                System.out.println("  版本ID: " + versionEntry.getId());
                System.out.println("  来源: " + versionEntry.getSource());
                System.out.println("  存储桶: " + versionEntry.getStorageBucket());
                System.out.println("  存储路径: " + versionEntry.getStoragePath());
                System.out.println("  描述: " + versionEntry.getDescription());
                System.out.println("  创建用户: " + versionEntry.getCreateUserName());
                System.out.println("  创建时间: " + versionEntry.getCreatedAt());
            }

            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.model.ModelModule modelModule = client.model();
            DescribeModelResponse response2 = modelModule.describeModel(request);
            
            System.out.println("模块化方式获取模型详情成功！");
            System.out.println("Request ID: " + response2.getMetadata().getBceRequestId());
            System.out.println("模型ID: " + response2.getId());
            System.out.println("模型名称: " + response2.getName());

        } catch (BceClientException e) {
            System.err.println("获取模型详情失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 