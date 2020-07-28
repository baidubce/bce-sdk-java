package com.baidubce.services.bos.demo;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.GetObjectRequest;
import com.baidubce.services.bos.model.ObjectMetadata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 下载object的demo
 */
public class GetObjectDemo {
    // 读Object到一个流
    public static void getObjectToStream() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 获取Object，返回结果为BosObject对象
        BosObject object = client.getObject("bucketName", "objectKey");

        // 获取ObjectMeta
        ObjectMetadata meta = object.getObjectMetadata();

        // 获取Object的输入流
        InputStream objectContent = object.getObjectContent();

        // 处理这个流...

        // 关闭流
        try {
            if (objectContent != null) {
                objectContent.close();
            }
        } catch (IOException e) {
            throw new BceServiceException("", e);
        }

        // 关闭客户端
        client.shutdown();
    }

    // 读Object到一个文件
    public static void getObjectToFile() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 新建GetObjectRequest
        GetObjectRequest getObjectRequest = new GetObjectRequest("bucketName", "objectKey");

        // 下载Object到文件
        ObjectMetadata objectMetadata =
                client.getObject(getObjectRequest, new File("/path/to/file","filename"));

        // 关闭客户端
        client.shutdown();
    }
}
