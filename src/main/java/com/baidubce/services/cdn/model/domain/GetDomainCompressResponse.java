package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainCompressResponse extends CdnResponse {
    private CompressResponse compress;

    /**
     * @return compress
     */
    public CompressResponse getCompress() {
        return compress;
    }

    /**
     * @param compress Detailed configuration of page compression
     */
    public void setCompress(CompressResponse compress) {
        this.compress = compress;
    }
}
