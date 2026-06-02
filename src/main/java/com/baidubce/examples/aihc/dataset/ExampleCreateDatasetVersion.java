package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.CreateDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.CreateDatasetVersionResponse;
import com.baidubce.util.JsonUtils;

public class ExampleCreateDatasetVersion {
    /**
     * {@summary}
     * 主函数，用于调用AIHC CreateDatasetVersion API接口。
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

        CreateDatasetVersionRequest request = new CreateDatasetVersionRequest();
        
        // 设置必选参数
        request.setDatasetId(System.getenv("DATASET_ID"));  // 数据集ID
        request.setStoragePath("/path/to/version2");  // 存储路径
        request.setMountPath("/mnt/datasets/my-dataset-1/v2");  // 默认挂载路径
        
        // 设置可选参数
        request.setDescription("new version");  // 版本描述

        try {
            System.out.println("=== 开始创建数据集版本 ===");
            System.out.println("数据集ID: " + request.getDatasetId());
            System.out.println("版本描述: " + request.getDescription());
            System.out.println("存储路径: " + request.getStoragePath());
            System.out.println("挂载路径: " + request.getMountPath());
            
            // 方式1：传统调用方式（向后兼容）
            System.out.println("\n--- 方式1：传统调用方式 ---");
            CreateDatasetVersionResponse response = client.dataset().createDatasetVersion(request);
            System.out.println("数据集版本创建成功！");
            System.out.println("版本ID: " + response.getId());
            System.out.println("Request ID: " + response.getMetadata().getBceRequestId());
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            CreateDatasetVersionResponse response2 = datasetModule.createDatasetVersion(request);
            System.out.println("模块化方式创建数据集版本成功！");
            System.out.println("版本ID: " + response2.getId());
            System.out.println("Request ID: " + response2.getMetadata().getBceRequestId());
            
            // 打印完整的JSON响应
            System.out.println("\n=== API返回的JSON数据 ===");
            String jsonResponse = JsonUtils.toJsonString(response);
            System.out.println(jsonResponse);
            
            System.out.println("\n=== 创建的数据集版本信息 ===");
            System.out.println("数据集ID: " + request.getDatasetId());
            System.out.println("版本ID: " + response.getId());
            System.out.println("版本描述: " + request.getDescription());
            System.out.println("存储路径: " + request.getStoragePath());
            System.out.println("挂载路径: " + request.getMountPath());
            
            System.out.println("\n=== 注意事项 ===");
            System.out.println("新创建的版本将成为数据集的最新版本。");
            System.out.println("请确保存储路径中的数据已经准备好。");
            System.out.println("挂载路径将在使用数据集时生效。");
            
        } catch (BceClientException e) {
            System.out.println("API调用失败: " + e.getMessage());
        }
    }
} 