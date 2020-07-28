package com.baidubce.services.bos.demo;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MultipartUploadDemo {
    public static void main(String[] args) {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        String uploadId = initMultipartUpload(client);
        List<PartETag> partETags = uploadPart(client, uploadId);
        completePart(client, uploadId, partETags);

        // 关闭客户端
        client.shutdown();
    }

    // 初始化Multipart Upload
    public static String initMultipartUpload(BosClient client) {
        // 开始Multipart Upload
        InitiateMultipartUploadRequest initiateMultipartUploadRequest =
                new InitiateMultipartUploadRequest("bucketName", "objectKey");
        InitiateMultipartUploadResponse initiateMultipartUploadResponse =
                client.initiateMultipartUpload(initiateMultipartUploadRequest);

        // 打印UploadId，它是区分分块上传事件的唯一标识，在后面的操作中，我们将用到它。
        String uploadId = initiateMultipartUploadResponse.getUploadId();
        System.out.println("UploadId: " + uploadId);

        return uploadId;
    }

    // 分块上传
    public static List<PartETag> uploadPart(BosClient client, String uploadId) {
        // 设置每块为 5MB
        final long partSize = 1024 * 1024 * 5L;

        File partFile = new File("/path/to/file.zip");

        // 计算分块数目
        int partCount = (int) (partFile.length() / partSize);
        if (partFile.length() % partSize != 0){
            partCount++;
        }

        // 新建一个List保存每个分块上传后的ETag和PartNumber
        List<PartETag> partETags = new ArrayList<PartETag>();

        try {
            for(int i = 0; i < partCount; i++){
                // 获取文件流
                FileInputStream fis = new FileInputStream(partFile);

                // 跳到每个分块的开头
                long skipBytes = partSize * i;
                fis.skip(skipBytes);

                // 计算每个分块的大小
                long size = Math.min(partSize, partFile.length() - skipBytes);

                // 创建UploadPartRequest，上传分块
                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName("bucketName");
                uploadPartRequest.setKey("objectKey");
                uploadPartRequest.setUploadId(uploadId);
                uploadPartRequest.setInputStream(fis);
                uploadPartRequest.setPartSize(size);
                uploadPartRequest.setPartNumber(i + 1);
                UploadPartResponse uploadPartResponse = client.uploadPart(uploadPartRequest);

                // 将返回的PartETag保存到List中。
                partETags.add(uploadPartResponse.getPartETag());

                // 关闭文件
                fis.close();
            }
        } catch (IOException e) {
            throw new BceServiceException("", e);
        }


        // PartETag 对象，它是上传块的ETag与块编号（PartNumber）的组合，在后续完成分块上传的步骤中会用到它
        // 返回List<PartETag>
        return partETags;
    }

    // 完成分块上传
    public static void completePart(BosClient client, String uploadId, List<PartETag> partETags) {
        // 分块上传结束之后，当所有的数据Part验证通过后，BOS将把这些数据part组合成一个完整的Object
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest("bucketName", "objectKey", uploadId, partETags);
        CompleteMultipartUploadResponse completeMultipartUploadResponse =
                client.completeMultipartUpload(completeMultipartUploadRequest);

        // 打印Object的ETag
        System.out.println(completeMultipartUploadResponse.getETag());
    }

    // 取消分块上传
    public static void abortPartUploadDemo(BosClient client, String uploadId) {
        // 可以使用abortMultipartUpload方法取消分块上传
        AbortMultipartUploadRequest abortMultipartUploadRequest =
                new AbortMultipartUploadRequest("bucketName", "objectKey", uploadId);

        client.abortMultipartUpload(abortMultipartUploadRequest);
    }

    // 获取未完成的分块上传事件
    public static void listMultipartUploads(BosClient client) {
        ListMultipartUploadsRequest listMultipartUploadsRequest =
                new ListMultipartUploadsRequest("bucketName");

        // 获取Bucket内未完成的分块上传事件
        ListMultipartUploadsResponse listing = client.listMultipartUploads(listMultipartUploadsRequest);

        // 遍历所有上传事件
        for (MultipartUploadSummary multipartUpload : listing.getMultipartUploads()) {
            System.out.println("Key: " + multipartUpload.getKey() + " UploadId: " + multipartUpload.getUploadId());
        }
    }

    // 获取所有已上传的块信息
    public static void listParts(BosClient client, String uploadId) {
        ListPartsRequest listPartsRequest = new ListPartsRequest("bucketName", "objectKey", uploadId);

        // 获取上传的所有Part信息
        ListPartsResponse partListing = client.listParts(listPartsRequest);

        // 遍历所有Part
        for (PartSummary part : partListing.getParts()) {
            System.out.println("PartNumber: " + part.getPartNumber() + " ETag: " + part.getETag());
        }

        // 查看Object的存储类型
        String storageClass = partListing.getStorageClass();
        System.out.println("storage class: " + storageClass);
    }

    // 封装分块上传，一个接口完成分块上传
    public static void putSuperObject() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        File file = new File("/path/to/file.zip");
        PutSuperObjectRequest request = new PutSuperObjectRequest("bucketName", "objectKey", file);
        client.putSuperObjectFromFile(request);

        // 关闭客户端
        client.shutdown();
    }
}
