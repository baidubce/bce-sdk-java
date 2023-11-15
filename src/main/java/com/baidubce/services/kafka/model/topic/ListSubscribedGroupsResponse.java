package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListSubscribedGroupsResponse extends AbstractBceResponse {

    private List<String> groups;
}
