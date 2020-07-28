package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单拷贝、分块拷贝的demo
 */
public class CopyObjectDemo {
    // 小于5G的文件直接简单拷贝
    public static void copyObjectSimple() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 创建CopyObjectRequest对象
        CopyObjectRequest copyObjectRequest =
                new CopyObjectRequest("srcBucketName", "srcKey", "destBucketName", "destKey");

        // 也可以设置新的Metadata
        Map<String, String> userMetadata = new HashMap<String, String>();
        userMetadata.put("user-meta-key", "user-meta-value");
        ObjectMetadata meta = new ObjectMetadata();
        meta.setUserMetadata(userMetadata);
        copyObjectRequest.setNewObjectMetadata(meta);

        // 复制Object
        CopyObjectResponse copyObjectResponse = client.copyObject(copyObjectRequest);

        System.out.println("ETag: " + copyObjectResponse.getETag() + " LastModified: " + copyObjectResponse.getLastModified());

        // 关闭客户端
        client.shutdown();
    }

    // 大于5G的文件、网络差、需要支持断点拷贝的建议使用分块拷贝
    public static void copyObjectMultipart() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 第一步 init
        InitiateMultipartUploadRequest initiateMultipartUploadRequest =
                new InitiateMultipartUploadRequest("targetBucketName","targetObjectName");
        InitiateMultipartUploadResponse initiateMultipartUploadResponse =
                client.initiateMultipartUpload(initiateMultipartUploadRequest);

        // 第二步 分块拷贝
        long left_size=client.getObjectMetadata("sourceBucketName","sourceObjectName").getContentLength();
        long skipBytes = 0;
        int partNumber = 1;
        List<PartETag> partETags = new ArrayList<PartETag>();

        while (left_size > 0) {
            long partSize = 1024 * 1024 * 1L;
            if (left_size < partSize) {
                partSize = left_size;
            }
            UploadPartCopyRequest uploadPartCopyRequest = new UploadPartCopyRequest();
            uploadPartCopyRequest.setBucketName("targetBucketName");
            uploadPartCopyRequest.setKey("targetObjectName");
            uploadPartCopyRequest.setSourceBucketName("sourceBucketName");
            uploadPartCopyRequest.setSourceKey("sourceObjectName");
            uploadPartCopyRequest.setUploadId(initiateMultipartUploadResponse.getUploadId());
            uploadPartCopyRequest.setPartSize(partSize);
            uploadPartCopyRequest.setOffSet(skipBytes);
            uploadPartCopyRequest.setPartNumber(partNumber);
            UploadPartCopyResponse uploadPartCopyResponse = client.uploadPartCopy(uploadPartCopyRequest);
            // 将返回的PartETag保存到List中
            PartETag partETag = new PartETag(partNumber,uploadPartCopyResponse.getETag());
            partETags.add(partETag);
            left_size -= partSize;
            skipBytes += partSize;
            partNumber+=1;
        }

        // 第三步 complete
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest("targetBucketName", "targetObjectName", initiateMultipartUploadResponse.getUploadId(), partETags);
        CompleteMultipartUploadResponse completeMultipartUploadResponse =
                client.completeMultipartUpload(completeMultipartUploadRequest);

        // 关闭客户端
        client.shutdown();
    }
}
