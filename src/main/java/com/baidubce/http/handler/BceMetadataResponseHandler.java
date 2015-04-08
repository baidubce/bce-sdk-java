/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.http.handler;

import com.baidubce.BceResponseMetadata;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.model.AbstractBceResponse;
import com.google.common.base.CharMatcher;

/**
 * HTTP response handler for Baidu BCE responses. Provides common utilities that other specialized BCE response
 * handlers need to share such as pulling common response metadata (ex: request IDs) out of headers.
 */
public class BceMetadataResponseHandler implements HttpResponseHandler {
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        BceResponseMetadata metadata = response.getMetadata();
        metadata.setBceRequestId(httpResponse.getHeader(Headers.BCE_REQUEST_ID));
        metadata.setBceContentSha256(httpResponse.getHeader(Headers.BCE_CONTENT_SHA256));
        metadata.setContentDisposition(httpResponse.getHeader(Headers.CONTENT_DISPOSITION));
        metadata.setContentEncoding(httpResponse.getHeader(Headers.CONTENT_ENCODING));
        metadata.setContentLength(httpResponse.getHeaderAsLong(Headers.CONTENT_LENGTH));
        metadata.setContentMd5(httpResponse.getHeader(Headers.CONTENT_MD5));
        metadata.setContentRange(httpResponse.getHeader(Headers.CONTENT_RANGE));
        metadata.setContentType(httpResponse.getHeader(Headers.CONTENT_TYPE));
        metadata.setDate(httpResponse.getHeaderAsRfc822Date(Headers.DATE));
        String eTag = httpResponse.getHeader(Headers.ETAG);
        if (eTag != null) {
            metadata.setETag(CharMatcher.is('"').trimFrom(eTag));
        }
        metadata.setExpires(httpResponse.getHeaderAsRfc822Date(Headers.EXPIRES));
        metadata.setLastModified(httpResponse.getHeaderAsRfc822Date(Headers.LAST_MODIFIED));
        metadata.setServer(httpResponse.getHeader(Headers.SERVER));
        return false;
    }
}
