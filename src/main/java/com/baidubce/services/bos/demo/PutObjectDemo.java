package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 简单上传object，包括以文件、数据流、二进制串、字符串方式；上传时设置object元信息的demo
 */
public class PutObjectDemo {
    // 简单上传，支持以指定文件形式、以数据流方式、以二进制串方式、以字符串方式执行Object上传
    public static void putObjectSimple() throws FileNotFoundException {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 获取指定文件
        File file = new File("/path/to/file.zip");
        // 获取数据流
        InputStream inputStream = new FileInputStream("/path/to/test.zip");
        byte[] byte0 = new byte[0];

        // 以文件形式上传Object
        PutObjectResponse putObjectFromFileResponse =
                client.putObject("bucketName", "file-objectKey", file);
        // 以数据流形式上传Object
        PutObjectResponse putObjectResponseFromInputStream =
                client.putObject("bucketName", "inputStream-objectKey", inputStream);
        // 以二进制串上传Object
        PutObjectResponse putObjectResponseFromByte =
                client.putObject("bucketName", "byte-objectKey", byte0);
        // 以字符串上传Object
        PutObjectResponse putObjectResponseFromString =
                client.putObject("bucketName", "string-objectKey", "hello world");

        // 打印ETag
        System.out.println(putObjectFromFileResponse.getETag());
        System.out.println(putObjectResponseFromInputStream.getETag());
        System.out.println(putObjectResponseFromByte.getETag());
        System.out.println(putObjectResponseFromString.getETag());

        // 关闭客户端
        client.shutdown();
    }

    // 上传object时设置文件元信息meta
    public static void putObjectWithMeta() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 获取指定文件
        File file = new File("/path/to/file.zip");

        // 初始化上传输入流
        ObjectMetadata meta = new ObjectMetadata();
        // 设置ContentLength大小
        meta.setContentLength(1000);
        // 设置ContentType
        meta.setContentType("application/json");
        // 设置cache-control
        meta.setCacheControl("no-cache");
        // 设置x-bce-content-crc32
        meta.setxBceCrc("crc");

        // 设置自定义元数据name的值为my-data
        meta.addUserMetadata("name", "my-data");

        // 上传Object
        client.putObject("bucketName", "objectKey", file, meta);

        // 关闭客户端
        client.shutdown();
    }

}
