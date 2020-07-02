/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bvw;

import java.io.InputStream;

import com.baidubce.BceErrorResponse;
import com.baidubce.BceServiceException;
import com.baidubce.BceServiceException.ErrorType;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP error response handler for Baidu BCE responses.
 */
public class BvwErrorResponseHandler implements HttpResponseHandler {

    private static Logger logger = LoggerFactory.getLogger(BvwErrorResponseHandler.class);

    /**
     * Bvw Implementation of HttpResponseHandler.
     *
     * @param httpResponse The http response
     * @param response     The bce response
     * @return If there is a next handle
     * @throws Exception JsonMappingException or JsonParseException
     */
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (httpResponse.getStatusCode() / 100 == HttpStatus.SC_OK / 100) {
            // not an error
            return false;
        }
        BceServiceException bse = null;
        InputStream content = httpResponse.getContent();
        if (content != null && (response.getMetadata().getContentLength() > 0
                || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())
                && response.getMetadata().getContentType().toLowerCase().contains("application/json"))) {
            BceErrorResponse bceErrorResponse = JsonUtils.loadFrom(content, BceErrorResponse.class);
            if (bceErrorResponse.getMessage() != null) {
                bse = new BceServiceException(bceErrorResponse.getMessage());
                bse.setErrorCode(bceErrorResponse.getCode());
                bse.setRequestId(bceErrorResponse.getRequestId());
            }
            content.close();
        }
        if (bse == null) {
            bse = new BceServiceException(httpResponse.getStatusText());
            bse.setRequestId(response.getMetadata().getBceRequestId());
        }
        bse.setStatusCode(httpResponse.getStatusCode());
        if (bse.getStatusCode() >= 500) {
            bse.setErrorType(ErrorType.Service);
        } else {
            bse.setErrorType(ErrorType.Client);
        }
        logger.error("Response metadata: {}", response.getMetadata());
        throw bse;
    }

}
