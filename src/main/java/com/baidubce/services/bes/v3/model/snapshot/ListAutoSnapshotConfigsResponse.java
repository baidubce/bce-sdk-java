package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListAutoSnapshotConfigsResponse extends AbstractBceResponse {
    private List<AutoSnapshotConfigItem> configList;
}
