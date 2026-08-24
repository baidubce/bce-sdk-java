package com.baidubce.services.bes.v3;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bes.v3.model.BesV3Headers;
import com.baidubce.services.bes.v3.model.BesV3ResponseMetadata;
import com.baidubce.services.bes.v3.model.cluster.DownloadCertResponse;

public class BesCertResponseHandler implements HttpResponseHandler {

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (!(response instanceof DownloadCertResponse)) {
            return false;
        }

        DownloadCertResponse certResponse = (DownloadCertResponse) response;
        certResponse.setContent(httpResponse.getContent());

        BesV3ResponseMetadata metadata = certResponse.getMetadata();
        metadata.setCacheControl(httpResponse.getHeader(BesV3Headers.CACHE_CONTROL));
        metadata.setPragma(httpResponse.getHeader(BesV3Headers.PRAGMA));
        metadata.setxContentTypeOptions(httpResponse.getHeader(BesV3Headers.X_CONTENT_TYPE_OPTIONS));

        return true;
    }
}
