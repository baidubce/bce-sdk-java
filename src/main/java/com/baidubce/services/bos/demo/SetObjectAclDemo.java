package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * BOS支持两种方式设置ACL。第一种是使用Canned Acl，第二种方式是上传一个ACL文件。
 */
public class SetObjectAclDemo {
    // 通过使用头域的"x-bce-acl"或者"x-bce-grant-permission'来设置object访问权限
    public static void setAclByCanned() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 1. 设置为public read
        SetObjectAclRequest setObjectAclRequest1 = new SetObjectAclRequest("bucketName","objectKey-publicRead", CannedAccessControlList.PublicRead);
        client.setObjectAcl(setObjectAclRequest1);

        // 2. 设置为user_id1、user_id2可访问
        String xBceGrantRead = "id=\"user_id1\""+",id=\"user_id2\"";
        SetObjectAclRequest setObjectAclRequest2 = new SetObjectAclRequest();
        setObjectAclRequest2.withBucketName("yourBucketName");
        setObjectAclRequest2.withKey("objectKey");
        setObjectAclRequest2.setxBceGrantRead(xBceGrantRead);
        client.setObjectAcl(setObjectAclRequest2);

        // 3. 设置为user_id1、user_id2有full control权限
        String xBceGrantFullControl = "id=\"user_id1\""+",id=\"user_id2\"";
        SetObjectAclRequest setObjectAclRequest3 = new SetObjectAclRequest();
        setObjectAclRequest3.withBucketName("yourBucketName");
        setObjectAclRequest3.withKey("objectKey");
        setObjectAclRequest3.setxBceGrantFullControl(xBceGrantFullControl);
        client.setObjectAcl(setObjectAclRequest3);

        // 关闭客户端
        client.shutdown();
    }

    // 通过setObjectAcl设置object访问权限
    public static void setAclByArgs() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        // 1. 以jason字符串方式
        String jsonObjectAcl =
                "{\"accessControlList\":["+
                "{\"grantee\":[{\"id\":\"*\"}], "+
                "\"permission\":[\"FULL_CONTROL\"]"+ "}]}";
        SetObjectAclRequest setObjectAclRequest1 = new SetObjectAclRequest("yourBucketName","objectKey",jsonObjectAcl);
        client.setObjectAcl(setObjectAclRequest1);

        // 2. 指定参数方式
        List<Grant> grants = new ArrayList<Grant>();
        List<Grantee> grantees = new ArrayList<Grantee>();
        List<Permission> permissions = new ArrayList<Permission>();
        // 授权给特定用户
        grantees.add(new Grantee("user_id1"));
        grantees.add(new Grantee("user_id2"));
        grantees.add(new Grantee("user_id3"));
        // 设置权限
        permissions.add(Permission.READ);
        grants.add(new Grant().withGrantee(grantees).withPermission(permissions));
        SetObjectAclRequest setObjectAclRequest2 = new SetObjectAclRequest("yourBucketName","objectKey", grants);
        client.setObjectAcl(setObjectAclRequest2);

        // 关闭客户端
        client.shutdown();
    }
}
