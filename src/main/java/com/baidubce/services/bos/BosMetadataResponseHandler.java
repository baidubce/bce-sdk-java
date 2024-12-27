/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bos;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bos.model.BosResponseMetadata;

public class BosMetadataResponseHandler implements HttpResponseHandler {

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (response.getMetadata() instanceof BosResponseMetadata) {
            BosResponseMetadata metadata = (BosResponseMetadata) response.getMetadata();
            metadata.setBosDebugId(httpResponse.getHeader(Headers.BCE_DEBUG_ID));
            if (httpResponse.getHeader(Headers.BCE_NEXT_APPEND_OFFSET) != null) {
                metadata.setNextAppendOffset(
                        Long.parseLong(httpResponse.getHeader(Headers.BCE_NEXT_APPEND_OFFSET)));
            }
            if (httpResponse.getHeader(Headers.LOCATION) != null) {
                metadata.setLocation(httpResponse.getHeader(Headers.LOCATION));
            }
            if (httpResponse.getHeader(Headers.BCE_VERSION_ID) != null) {
                metadata.setxBceVersionId(httpResponse.getHeader(Headers.BCE_VERSION_ID));
            }
        }
        return false;
    }

}
