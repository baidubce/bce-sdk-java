package com.baidubce.examples.aihc.model;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.model.DescribeModelsRequest;
import com.baidubce.services.aihc.model.model.DescribeModelsResponse;
import com.baidubce.util.JsonUtils;

public class ExampleDescribeModels {
    /**
     * {@summary}
     * 主函数，调用AihcClient进行AIHC DescribeModels API的调用。
     * 首先获取AK、SK和Endpoint，如果没有则使用默认值。
     * 然后构造BceClientConfiguration对象并设置凭证、端点等参数。
     * 最后创建AihcClient对象，发送请求，处理响应，输出结果。
     *
     * @param args 不需要传入任何参数
     * @throws BceClientException 如果API调用失败会抛出此异常
     */
    public static void main(String[] args) {
        String ak = System.getenv("BCE_ACCESS_KEY_ID") != null ? System.getenv("BCE_ACCESS_KEY_ID") : "Your AK";
        String sk = System.getenv("BCE_SECRET_ACCESS_KEY") != null ? System.getenv("BCE_SECRET_ACCESS_KEY") : "Your SK";
        String endpoint = "aihc.bj.baidubce.com";

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        AihcClient client = new AihcClient(config);

        DescribeModelsRequest request = new DescribeModelsRequest();
        // 设置查询参数
        request.setKeyword("test");
        request.setModelType("TENSORFLOW");
        request.setFramework("TF");
        request.setPageNumber(1);
        request.setPageSize(10);
        
        try {
            // 方式1：传统调用方式（向后兼容）
            System.out.println("--- 方式1：传统调用方式 ---");
            DescribeModelsResponse response = client.model().describeModels(request);
            System.out.println("模型总数: " + (response.getTotalCount() != null ? response.getTotalCount() : 0));
            System.out.println("当前页模型数量: " + (response.getModels() != null ? response.getModels().size() : 0));
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.model.ModelModule modelModule = client.model();
            DescribeModelsResponse response2 = modelModule.describeModels(request);
            System.out.println("模块化方式查询模型总数: " + (response2.getTotalCount() != null ? response2.getTotalCount() : 0));
            System.out.println("模块化方式当前页模型数量: " + (response2.getModels() != null ? response2.getModels().size() : 0));
            
            // 打印模型详细信息
            if (response.getModels() != null && !response.getModels().isEmpty()) {
                System.out.println("\n=== 模型详细信息 ===");
                for (DescribeModelsResponse.Model model : response.getModels()) {
                    System.out.println("模型ID: " + model.getId());
                    System.out.println("模型名称: " + model.getName());
                    System.out.println("模型类型: " + model.getModelType());
                    System.out.println("框架: " + model.getFramework());
                    System.out.println("状态: " + model.getStatus());
                    System.out.println("所有者: " + model.getOwnerName());
                    System.out.println("创建时间: " + model.getCreatedAt());
                    System.out.println("---");
                }
            }
            
            // 打印完整的JSON响应
            System.out.println("\n=== API返回的JSON数据 ===");
            String jsonResponse = JsonUtils.toJsonString(response);
            System.out.println(jsonResponse);
        } catch (BceClientException e) {
            System.out.println("API调用失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 