/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cas.model.response.CertFileResponse;

/**
 * Reads the raw body of the CAS download interfaces into a {@link CertFileResponse}.
 *
 * <p>The handler must be registered before the json handler; it declines every response
 * that is not a {@link CertFileResponse} so that the json handler keeps working.
 */
public class CasBinaryResponseHandler implements HttpResponseHandler {

    private static final int BUFFER_SIZE = 8192;

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (!(response instanceof CertFileResponse)) {
            return false;
        }
        InputStream content = httpResponse.getContent();
        if (content == null) {
            return true;
        }
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] chunk = new byte[BUFFER_SIZE];
            int read;
            while ((read = content.read(chunk)) != -1) {
                buffer.write(chunk, 0, read);
            }
            ((CertFileResponse) response).setContent(buffer.toByteArray());
        } finally {
            content.close();
        }
        return true;
    }
}
