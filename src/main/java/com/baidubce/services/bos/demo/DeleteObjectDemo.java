package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.DeleteMultipleObjectsRequest;
import com.baidubce.services.bos.model.DeleteMultipleObjectsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除单个文件、多个文件的demo
 */
public class DeleteObjectDemo {
    // 删除单个文件
    public static void delSingleObject() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 删除Object
        client.deleteObject("bucketName", "objectKey");           //指定要删除的Object所在Bucket名称和该Object名称

        // 关闭客户端
        client.shutdown();
    }

    // 删除多个文件
    public static void delObjects() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 1、以Json格式的字符串
        String jsonObjectKeys = "{\"objects\": ["+"{\"key\": \"token1.h\"},"+"{\"key\": \"token2.h\"}"+"]}";
        DeleteMultipleObjectsRequest request = new DeleteMultipleObjectsRequest();
        request.setBucketName("bucketName");
        request.setJsonDeleteObjects(jsonObjectKeys);
        client.deleteMultipleObjects(request);

        // 2、用户只需指定指定参数即可
        List<String> objectKeys = new ArrayList<String>();
        objectKeys.add("token1.h");
        objectKeys.add("token2.h");
        DeleteMultipleObjectsRequest request2 = new DeleteMultipleObjectsRequest();
        request2.setBucketName("bucketName");
        request2.setObjectKeys(objectKeys);
        // 返回的消息体中只包含删除过程中出错的Object结果；如果所有Object都删除都成功的话，则没有消息体。
        DeleteMultipleObjectsResponse response = client.deleteMultipleObjects(request2);

        // 关闭客户端
        client.shutdown();
    }
}
