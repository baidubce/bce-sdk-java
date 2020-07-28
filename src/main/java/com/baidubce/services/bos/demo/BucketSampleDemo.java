package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.BucketSummary;

import java.util.List;

/**
 * 关于Bucket的创建、删除、列举、是否存在的demo
 */
public class BucketSampleDemo {
    // 创建Bucket
    public static void createBucket() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        client.createBucket("create-bucketName");                    // 指定Bucket名称

        // 关闭客户端
        client.shutdown();
    }

    // 删除Bucket
    public static void deleteBucket () {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        client.deleteBucket("delete-bucketName");                  // 指定Bucket名称

        // 关闭客户端
        client.shutdown();
    }

    // 列举Bucket
    public static void listBuckets () {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 获取用户的Bucket列表
        List<BucketSummary> buckets = client.listBuckets().getBuckets();

        // 遍历Bucket
        for (BucketSummary bucket : buckets) {
            System.out.println(bucket.getName());
        }

        // 关闭客户端
        client.shutdown();
    }

    // 查询Bucket是否存在
    public static void doesBucketExist () {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 获取Bucket的存在信息
        boolean exists = client.doesBucketExist("isExist-bucketName");    //指定Bucket名称

        // 输出结果
        if (exists) {
            System.out.println("Bucket exists");
        } else {
            System.out.println("Bucket not exists");
        }

        // 关闭客户端
        client.shutdown();
    }
}
