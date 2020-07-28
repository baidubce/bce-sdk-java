package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.AppendObjectRequest;
import com.baidubce.services.bos.model.AppendObjectResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * BOS以追加方式上传object的demo
 */
public class AppendObjectDemo {
    // 通过AppendObject方式上传示例
    public static void appendObject() throws FileNotFoundException {
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
        AppendObjectResponse appendObjectFromFileResponse =
                client.appendObject("bucketName", "file-objectKey", file);
        // 以数据流形式上传Object
        AppendObjectResponse appendObjectResponseFromInputStream =
                client.appendObject("bucketName", "inputStream-objectKey", inputStream);
        // 以二进制串上传Object
        AppendObjectResponse appendObjectResponseFromByte =
                client.appendObject("bucketName", "byte-objectKey", byte0);
        // 以字符串上传Object
        AppendObjectResponse appendObjectResponseFromString =
                client.appendObject("bucketName", "string-objectKey", "hello world");

        // 打印ETag
        System.out.println(appendObjectFromFileResponse.getETag());
        // 打印NextAppendOffset
        System.out.println(appendObjectFromFileResponse.getNextAppendOffset());
        // 打印ContentMd5
        System.out.println(appendObjectFromFileResponse.getContentMd5());

        // 追加上传的示例，需要在请求中加上下次追加写的位置
        Long nextAppendOffset = appendObjectFromFileResponse.getNextAppendOffset();
        AppendObjectRequest appendObjectFromFileRequest =
                new AppendObjectRequest("bucketName","file-objectKey",file);
        appendObjectFromFileRequest.setOffset(nextAppendOffset);
        AppendObjectResponse response = client.appendObject(appendObjectFromFileRequest);

        // 打印ETag
        System.out.println(appendObjectFromFileResponse.getETag());
        // 打印NextAppendOffset
        System.out.println(appendObjectFromFileResponse.getNextAppendOffset());
        // 打印ContentMd5
        System.out.println(appendObjectFromFileResponse.getContentMd5());

        // 关闭客户端
        client.shutdown();
    }
}
