package com.baidubce.services.iothisk;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.iothisk.model.IotPkiManageDataResponse;

/**
 * HTTP body json response handler for Baidu BCE iot hisk responses.
 */
public class BceIotHiskJsonResponseHandler extends BceJsonResponseHandler {

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (response instanceof IotPkiManageDataResponse) {
            InputStream content = httpResponse.getContent();
            ((IotPkiManageDataResponse) response).setData(IOUtils.toByteArray(content));
            content.close();
            return true;
        }

        return super.handle(httpResponse, response);
    }
}
