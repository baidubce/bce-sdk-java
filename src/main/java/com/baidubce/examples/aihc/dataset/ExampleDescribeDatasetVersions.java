package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionsRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionsResponse;
import com.baidubce.util.JsonUtils;

public class ExampleDescribeDatasetVersions {
    /**
     * {@summary}
     * 主函数，用于调用AIHC DescribeDatasetVersions API接口。
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

        DescribeDatasetVersionsRequest request = new DescribeDatasetVersionsRequest();
        // 设置必选参数
        request.setDatasetId(System.getenv("DATASET_ID"));  // 数据集ID
        // 设置可选参数
        request.setPageNumber(1);  // 第1页
        request.setPageSize(10);   // 每页10条

        try {
            // 方式1：传统调用方式（向后兼容）
            System.out.println("--- 方式1：传统调用方式 ---");
            DescribeDatasetVersionsResponse response = client.dataset().describeDatasetVersions(request);
            System.out.println("数据集版本总数: " + response.getTotalCount());
            System.out.println("当前页版本数量: " + (response.getVersions() != null ? response.getVersions().size() : 0));
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            DescribeDatasetVersionsResponse response2 = datasetModule.describeDatasetVersions(request);
            System.out.println("模块化方式查询数据集版本总数: " + response2.getTotalCount());
            System.out.println("模块化方式当前页版本数量: " + (response2.getVersions() != null ? response2.getVersions().size() : 0));
            
            // 打印版本列表
            if (response.getVersions() != null && !response.getVersions().isEmpty()) {
                System.out.println("\n=== 数据集版本列表 ===");
                for (int i = 0; i < response.getVersions().size(); i++) {
                    DescribeDatasetVersionsResponse.DatasetVersionEntry version = response.getVersions().get(i);
                    System.out.println("\n--- 版本 " + (i + 1) + " ---");
                    System.out.println("版本ID: " + version.getId());
                    System.out.println("版本号: " + version.getVersion());
                    System.out.println("版本描述: " + version.getDescription());
                    System.out.println("存储路径: " + version.getStoragePath());
                    System.out.println("挂载路径: " + version.getMountPath());
                    System.out.println("创建用户: " + version.getCreateUser());
                    System.out.println("创建用户名: " + version.getCreateUserName());
                    System.out.println("创建时间: " + version.getCreatedAt());
                    System.out.println("更新时间: " + version.getUpdatedAt());
                }
            } else {
                System.out.println("\n=== 数据集版本列表 ===");
                System.out.println("暂无版本数据");
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