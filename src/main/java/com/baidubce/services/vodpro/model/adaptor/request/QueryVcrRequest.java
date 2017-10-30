package com.baidubce.services.vodpro.model.adaptor.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created on 17/10/10
 *
 * @author liumin08
 */
public class QueryVcrRequest extends AbstractBceRequest {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public QueryVcrRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
