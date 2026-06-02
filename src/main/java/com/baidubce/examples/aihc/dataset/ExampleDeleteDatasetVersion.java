package com.baidubce.examples.aihc.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetVersionResponse;
import com.baidubce.util.JsonUtils;

public class ExampleDeleteDatasetVersion {
    /**
     * 删除数据集版本示例
     * 
     * 本示例演示如何使用AIHC SDK删除指定的数据集版本
     * 
     * 注意事项：
     * 1. 删除操作是不可逆的，请谨慎使用
     * 2. 请确保要删除的版本不再被使用
     * 3. 如果删除的是最新版本，数据集的最新版本信息会相应更新
     */
    public static void main(String[] args) {
        // 获取环境变量
        String ak = System.getenv("BCE_ACCESS_KEY_ID");
        String sk = System.getenv("BCE_SECRET_ACCESS_KEY");
        
        // 检查环境变量
        if (ak == null || sk == null || "Your AK".equals(ak) || "Your SK".equals(sk)) {
            System.out.println("❌ 环境变量未设置！");
            System.out.println("请设置以下环境变量：");
            System.out.println("export BCE_ACCESS_KEY_ID=\"your-access-key\"");
            System.out.println("export BCE_SECRET_ACCESS_KEY=\"your-secret-key\"");
            System.out.println();
            System.out.println("⚠️  注意：删除操作是不可逆的，请确保使用正确的访问密钥！");
            return;
        }
        
        String endpoint = System.getenv("ENDPOINT") != null ? System.getenv("ENDPOINT") : "AIHC_ENDPOINT";

        // 配置客户端
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        AihcClient client = new AihcClient(config);

        // 创建请求对象
        DeleteDatasetVersionRequest request = new DeleteDatasetVersionRequest();
        
        // 设置必选参数
        String datasetId = System.getenv("DATASET_ID");  // 数据集ID - 请替换为实际的数据集ID
        String versionId = System.getenv("DATASET_VERSION_ID");  // 数据集版本ID - 请替换为实际的版本ID
        
        request.setDatasetId(datasetId);
        request.setVersionId(versionId);

        try {
            System.out.println("=== 开始删除数据集版本 ===");
            System.out.println("数据集ID: " + request.getDatasetId());
            System.out.println("版本ID: " + request.getVersionId());
            System.out.println();
            
            // 确认删除操作
            System.out.println("⚠️  警告：此操作将永久删除指定的数据集版本！");
            System.out.println("请确认您要删除的版本信息是否正确。");
            System.out.println();
            
            // 方式1：传统调用方式（向后兼容）
            System.out.println("--- 方式1：传统调用方式 ---");
            DeleteDatasetVersionResponse response = client.dataset().deleteDatasetVersion(request);
            System.out.println("✅ 数据集版本删除成功！");
            System.out.println("Request ID: " + response.getMetadata().getBceRequestId());
            
            // 打印完整的JSON响应
            System.out.println("\n=== API返回的JSON数据 ===");
            String jsonResponse = JsonUtils.toJsonString(response);
            System.out.println(jsonResponse);
            
            System.out.println("\n=== 操作完成 ===");
            System.out.println("✅ 数据集版本删除操作已成功完成");
            System.out.println("📋 删除的版本信息：");
            System.out.println("   - 数据集ID: " + datasetId);
            System.out.println("   - 版本ID: " + versionId);
            System.out.println("   - 请求ID: " + response.getMetadata().getBceRequestId());
            
            // 方式2：模块化调用方式（推荐）
            System.out.println("\n--- 方式2：模块化调用方式 ---");
            com.baidubce.services.aihc.modules.dataset.DatasetModule datasetModule = client.dataset();
            DeleteDatasetVersionResponse response2 = datasetModule.deleteDatasetVersion(request);
            System.out.println("✅ 模块化方式删除数据集版本成功！");
            System.out.println("Request ID: " + response2.getMetadata().getBceRequestId());
            
        } catch (BceClientException e) {
            System.err.println("❌ API调用失败: " + e.getMessage());
            System.err.println();
            System.err.println("可能的原因：");
            System.err.println("1. 访问密钥无效或权限不足");
            System.err.println("2. 数据集ID或版本ID不存在");
            System.err.println("3. 网络连接问题");
            System.err.println("4. 服务端点不可用");
            System.err.println();
            System.err.println("建议的解决方案：");
            System.err.println("1. 检查环境变量设置是否正确");
            System.err.println("2. 验证数据集ID和版本ID是否存在");
            System.err.println("3. 确认网络连接正常");
            System.err.println("4. 检查账户权限是否足够");
        } catch (Exception e) {
            System.err.println("❌ 程序执行异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 