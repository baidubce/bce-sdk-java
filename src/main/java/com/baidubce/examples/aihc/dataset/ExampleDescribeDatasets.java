package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetsRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetsResponse;
import com.baidubce.util.JsonUtils;

public class ExampleDescribeDatasets {
    /**
     * {@summary}
     * 主函数，用于调用AIHC API接口。
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

        DescribeDatasetsRequest request = new DescribeDatasetsRequest();
        try {
            // 方式1：传统调用方式（向后兼容）
            System.out.println("--- 方式1：传统调用方式 ---");
            DescribeDatasetsResponse response = client.dataset().describeDatasets(request);
            System.out.println("数据集总数: " + (response.getDatasets() != null ? response.getDatasets().size() : 0));
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            DescribeDatasetsResponse response2 = datasetModule.describeDatasets(request);
            System.out.println("模块化方式查询数据集总数: " + (response2.getDatasets() != null ? response2.getDatasets().size() : 0));
            
            // 打印完整的JSON响应
            System.out.println("\n=== API返回的JSON数据 ===");
            String jsonResponse = JsonUtils.toJsonString(response);
            System.out.println(jsonResponse);
        } catch (BceClientException e) {
            System.out.println("API调用失败: " + e.getMessage());
        }
    }
} 