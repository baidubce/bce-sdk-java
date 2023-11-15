package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.ListResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetClusterNodesResponse extends ListResponse {

    private List<Node> nodes;
}
