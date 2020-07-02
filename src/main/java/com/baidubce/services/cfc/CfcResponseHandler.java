/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cfc.model.CfcInvokeResult;
import com.baidubce.services.cfc.model.CfcMetaData;
import com.baidubce.services.cfc.model.GetInvokeResponse;
import com.baidubce.util.LengthCheckInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Responsible for handling httpResponses from all CFC service calls
 */
public class CfcResponseHandler implements HttpResponseHandler {
    private static Logger logger = LoggerFactory.getLogger(CfcResponseHandler.class);
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (!(response instanceof GetInvokeResponse)) {
            return false;
        }
        CfcInvokeResult object = new CfcInvokeResult();
        CfcMetaData objectMetadata = object.getObjectMetadata();
        objectMetadata.setBceRequestId(httpResponse.getHeader(Headers.BCE_REQUEST_ID));
        objectMetadata.setContentLength(httpResponse.getHeaderAsLong(Headers.CONTENT_LENGTH));
        objectMetadata.setContentType(httpResponse.getHeader(Headers.CONTENT_TYPE));
        objectMetadata.setServer(httpResponse.getHeader(Headers.SERVER));
        objectMetadata.setBceLogResult(httpResponse.getHeader(Headers.BCE_LOG_RESULT));

        object.setObjectMetadata(objectMetadata);
        InputStream content = httpResponse.getContent();
        if (content != null) {
            if (objectMetadata.getContentLength() > 0) {
                content = new LengthCheckInputStream(content, objectMetadata.getContentLength(),
                        LengthCheckInputStream.INCLUDE_SKIPPED_BYTES);
            }
            object.setContent(new CfcInputStream(content, httpResponse.getHttpResponse()));
        }

        ((GetInvokeResponse) response).setInvoke(object);
        return true;
    }


}
