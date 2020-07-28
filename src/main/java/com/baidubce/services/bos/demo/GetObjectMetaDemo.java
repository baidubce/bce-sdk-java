package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.ObjectMetadata;

/**
 * 获取ObjectMetadata的demo
 */
public class GetObjectMetaDemo {
    // 只获取ObjectMetadata而不获取Object的实体
    public static void getObjectMeta() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        ObjectMetadata objectMetadata = client.getObjectMetadata("bucketName", "objectKey");
        // 查看object元信息
        System.out.println("contentType: " + objectMetadata.getContentType() + "\n" +
                "contentLength: " + objectMetadata.getContentLength() + "\n" +
                "contentMd5: " + objectMetadata.getContentMd5() + "\n" +
                "etag: " + objectMetadata.getETag() + "\n" +
                "storageClass: " + objectMetadata.getStorageClass() + "\n");

        // 关闭客户端
        client.shutdown();
    }
}
