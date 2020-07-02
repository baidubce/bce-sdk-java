package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainFileTrimResponse extends CdnResponse {
    private boolean fileTrim;

    /**
     * @return fileTrim
     */
    public boolean isFileTrim() {
        return fileTrim;
    }

    /**
     * @param fileTrim Whether to enable page optimization
     */
    public void setFileTrim(boolean fileTrim) {
        this.fileTrim = fileTrim;
    }
}
