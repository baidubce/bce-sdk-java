package com.baidubce.services.bes.v3.model.cluster;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class ListMigrationNodesResponse extends AbstractBceResponse {
    private List<MigrationNode> nodes;
}
