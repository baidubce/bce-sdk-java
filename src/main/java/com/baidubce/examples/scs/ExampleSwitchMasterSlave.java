/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsInstanceDetailRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailResponse;
import com.baidubce.services.scs.model.instance.ScsRedisNode;
import com.baidubce.services.scs.model.instance.ScsSwitchMasterSlaveRequest;
import com.baidubce.services.scs.model.instance.ScsSwitchMasterSlaveShard;
import com.baidubce.util.JsonUtils;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSwitchMasterSlave {
    public static void main(String[] args) {
        ScsClient client = createClient();

        ScsInstanceDetailRequest detailRequest = new ScsInstanceDetailRequest();
        detailRequest.setInstanceId(INSTANCE_ID);
        ScsInstanceDetailResponse detailResponse = client.getInstanceDetail(detailRequest);

        ScsRedisNode currentNode = null;
        for (ScsRedisNode node : detailResponse.getRedisList()) {
            if (node.getCacheInstanceType() == 2 && node.getIsReadOnly() == 0) {
                currentNode = node;
                break;
            }
        }

        if (currentNode == null) {
            System.out.println("No slave node found.");
            return;
        }
        ScsSwitchMasterSlaveRequest request = new ScsSwitchMasterSlaveRequest();
        ScsSwitchMasterSlaveShard shard = new ScsSwitchMasterSlaveShard();
        shard.setHashName(currentNode.getHashName());
        shard.setNodeShowId(currentNode.getNodeShowId());
        request.setInstanceId(INSTANCE_ID);
        request.setShards(Collections.singletonList(shard));
        System.out.println(JsonUtils.toJsonString(request));
        client.switchMasterSlave(request);
    }
}
