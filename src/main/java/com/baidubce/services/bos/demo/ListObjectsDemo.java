package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.ListObjectsRequest;
import com.baidubce.services.bos.model.ListObjectsResponse;

/**
 * 列出bucket下的objects、列出指定dir下的文件的demo
 */
public class ListObjectsDemo {
    // 简单快速列举出所需的文件，默认情况下，如果Bucket中的Object数量大于1000，则只会返回1000个Object
    public static void listObjectsSimple() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 获取指定Bucket下的所有Object信息
        ListObjectsResponse listing = client.listObjects("bucketName");

        // 遍历所有Object
        for (BosObjectSummary objectSummary : listing.getContents()) {
            System.out.println("ObjectKey: " + objectSummary.getKey());
        }

        // 关闭客户端
        client.shutdown();
    }

    // 设置参数，灵活列举
    public static void listObjectsWithArgs() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest("bucketName");
        // 指定最大返回条数为500
        listObjectsRequest.withMaxKeys(500);
        // 指定返回前缀为test的object
        listObjectsRequest.withPrefix("obj");
        // 从"object"其之后开始返回
        listObjectsRequest.withMarker("object");

        ListObjectsResponse listObjectsResponse = client.listObjects(listObjectsRequest);
        for(BosObjectSummary objectSummary :listObjectsResponse.getContents()) {
            System.out.println("ObjectKey:" + objectSummary.getKey());
        }

        // 关闭客户端
        client.shutdown();
    }

    // 分页列举所有的Object
    public static void listAllObjects() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 用户可设置每页最多500条记录
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest("bucketName");
        ListObjectsResponse listObjectsResponse;
        listObjectsRequest.withMaxKeys(500);
        boolean isTruncated = true;
        while (isTruncated) {
            listObjectsResponse = client.listObjects(listObjectsRequest);

            for(BosObjectSummary objectSummary :listObjectsResponse.getContents()) {
                System.out.println("ObjectKey:" + objectSummary.getKey());
            }
            isTruncated = listObjectsResponse.isTruncated();
            if (listObjectsResponse.getNextMarker() != null) {
                listObjectsRequest.withMarker(listObjectsResponse.getNextMarker());
            }
        }

        // 关闭客户端
        client.shutdown();
    }

    // 查看目录下的文件和子目录
    public static void listObjectsAndDir() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest("bucketName");

        // "/" 为文件夹的分隔符
        listObjectsRequest.setDelimiter("/");

        // 列出fun目录下的文件和文件夹
        listObjectsRequest.setPrefix("fun/");

        ListObjectsResponse listing = client.listObjects(listObjectsRequest);

        // 遍历Object
        System.out.println("Objects:");
        for (BosObjectSummary objectSummary : listing.getContents()) {
            System.out.println(objectSummary.getKey());
        }

        // 遍历CommonPrefix（文件夹）
        System.out.println("\nCommonPrefixs:");
        for (String commonPrefix : listing.getCommonPrefixes()) {
            System.out.println(commonPrefix);
        }

        // 关闭客户端
        client.shutdown();
    }
}
