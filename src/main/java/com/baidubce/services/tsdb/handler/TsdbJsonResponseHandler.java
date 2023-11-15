package com.baidubce.services.tsdb.handler;

import java.io.InputStream;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;

/**
 * Tsdb json response handler.
 */
public class TsdbJsonResponseHandler implements HttpResponseHandler {

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        InputStream content = httpResponse.getContent();
        if (content != null) {
            JsonUtils.load(content, response);
            content.close();
        }
        return true;
    }
}
