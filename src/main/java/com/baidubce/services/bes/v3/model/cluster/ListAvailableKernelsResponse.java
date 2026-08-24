package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListAvailableKernelsResponse extends AbstractBceResponse {
    private List<KernelVersion> versions;
}
