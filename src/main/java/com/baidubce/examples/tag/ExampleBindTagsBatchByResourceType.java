package com.baidubce.examples.tag;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.model.ResourceType;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.TagsOperationRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleBindTagsBatchByResourceType {
    public static void main(String[] args) {
        // 设置您的ak、sk和要访问的endpoint
        String endpoint = "bcc.bj.baidubce.com";
        String ak = "ak";
        String sk = "sk";
        // 设置默认配置
        BceClientConfiguration bccClientConfiguration = new BccClientConfiguration()
                .withProtocol(Protocol.HTTP)
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);
        // 创建bcc client
        BccClient bccClient = new BccClient(bccClientConfiguration);
        TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
        // 标签列表
        List<TagModel> changeTags = new ArrayList<TagModel>();
        TagModel tagModel = new TagModel().withTagKey("Key-example").withTagValue("Value");
        changeTags.add(tagModel);
        tagsOperationRequest.setTags(changeTags);
        // 预留实例券ID列表
        tagsOperationRequest.setResourceIds(Arrays.asList("r-oFpMXKhv", "r-HrztSVk0"));

        tagsOperationRequest.setResourceType(ResourceType.bccri.name());
        bccClient.bindTagsBatchByResourceType(tagsOperationRequest);
    }
}
