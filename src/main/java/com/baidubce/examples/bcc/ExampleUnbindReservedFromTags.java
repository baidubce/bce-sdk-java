/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.bcc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.reversed.ReservedTagsRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleUnbindReservedFromTags {
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

        ReservedTagsRequest reservedTagsRequest = new ReservedTagsRequest();
        // 标签列表
        List<TagModel> changeTags = new ArrayList<TagModel>();
        TagModel tagModel = new TagModel().withTagKey("Key***").withTagValue("Value***");
        changeTags.add(tagModel);
        reservedTagsRequest.setChangeTags(changeTags);
        // 预留实例券ID列表，最多支持100个
        reservedTagsRequest.withInstanceIds(Arrays.asList("r-oFpMXKhv", "r-HrztSVk0"));

        bccClient.unbindReservedInstanceFromTags(reservedTagsRequest);
    }
}
