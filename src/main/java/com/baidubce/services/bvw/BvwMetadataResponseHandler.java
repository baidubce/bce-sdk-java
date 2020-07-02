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

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.model.media.MediaBaseResponse;
import com.baidubce.services.bvw.model.notification.NotificationBaseResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowBaseResponse;

/**
 * The bvw metadata response handler.
 */
public class BvwMetadataResponseHandler implements HttpResponseHandler {

    /**
     * Bvw Implementation of HttpResponseHandler. When server return a empty body,
     * wo should set content-length to 0 and transfer-encoding to null.
     *
     * @param httpResponse The http response
     * @param response     The bce response
     * @return If there is a next handle
     * @throws Exception JsonMappingException or JsonParseException
     */
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (response.getMetadata() instanceof BvwResponseMetadata) {
            BvwResponseMetadata metadata = (BvwResponseMetadata) response.getMetadata();
            if (null != httpResponse.getHeader("x-bce-error-code")) {
                metadata.setBceErrorCode(httpResponse.getHeader("x-bce-error-code"));
            }
            if (null != httpResponse.getHeader("x-bce-error-message")) {
                metadata.setBceErrorMessage(httpResponse.getHeader("x-bce-error-message"));
            }
        }
        if (!(response instanceof NotificationBaseResponse
            || response instanceof MediaBaseResponse
            || response instanceof WorkflowBaseResponse)) {
            return false;
        }
        if ((null != response.getMetadata().getContentType()
                && response.getMetadata().getContentType().toLowerCase().contains("text/plain"))
                && -1 == response.getMetadata().getContentLength()) {
            response.getMetadata().setContentType(null);
            response.getMetadata().setContentLength(0);
            response.getMetadata().setTransferEncoding(null);
        }
        return false;
    }

}
