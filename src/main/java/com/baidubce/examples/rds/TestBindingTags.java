package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsBindingTagsRequest;
import com.baidubce.services.rds.model.RdsTag;
import com.baidubce.services.rds.model.Resource;


import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;


public class TestBindingTags {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsBindingTagsRequest request = new RdsBindingTagsRequest();
        RdsTag tag = new RdsTag();
        tag.setTagValue("testValue");
        tag.setTagKey("testKey");
        List<RdsTag> tags = new ArrayList<>();
        tags.add(tag);
        String instanceId = "rds-lZIdjcC3";
        Resource resource = new Resource(instanceId, tags);
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        request.setResources(resources);
        AbstractBceResponse response = rdsClient.bindingTags(request);
        print("bindingTags", response);
    }
}
