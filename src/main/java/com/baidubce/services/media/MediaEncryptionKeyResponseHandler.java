package com.baidubce.services.media;

import java.io.InputStream;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.media.model.GetTranscodingEncryptionKeyResponse;

import org.apache.commons.io.IOUtils;

public class MediaEncryptionKeyResponseHandler implements HttpResponseHandler {

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (!(response instanceof GetTranscodingEncryptionKeyResponse)) {
            return false;
        }
        InputStream inputStream = httpResponse.getContent();
        if (inputStream == null) {
            return false;
        }
        GetTranscodingEncryptionKeyResponse keyResponse = (GetTranscodingEncryptionKeyResponse) response;
        keyResponse.setEncryptionKey(IOUtils.toString(inputStream, "UTF-8"));
        inputStream.close();
        return true;
    }
    
}
