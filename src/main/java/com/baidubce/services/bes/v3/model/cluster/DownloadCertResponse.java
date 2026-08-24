package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bes.v3.model.BesV3ResponseMetadata;

import lombok.Data;

import java.io.InputStream;

@Data
public class DownloadCertResponse extends AbstractBceResponse {
    private InputStream content;

    public DownloadCertResponse() {
        this.metadata = new BesV3ResponseMetadata();
    }

    @Override
    public BesV3ResponseMetadata getMetadata() {
        return (BesV3ResponseMetadata) this.metadata;
    }
}
