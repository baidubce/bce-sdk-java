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
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.GetObjectResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.util.LengthCheckInputStream;
import com.google.common.base.CharMatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Map;

public class BosObjectResponseHandler implements HttpResponseHandler {

    private static Logger logger = LoggerFactory.getLogger(BosObjectResponseHandler.class);
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (!(response instanceof GetObjectResponse)) {
            return false;
        }
        BosObject object = new BosObject();
        ObjectMetadata objectMetadata = object.getObjectMetadata();
        objectMetadata.setContentLength(httpResponse.getHeaderAsLong(Headers.CONTENT_LENGTH));
        objectMetadata.setContentType(httpResponse.getHeader(Headers.CONTENT_TYPE));
        objectMetadata.setContentEncoding(httpResponse.getHeader(Headers.CONTENT_ENCODING));
        objectMetadata.setContentMd5(httpResponse.getHeader(Headers.CONTENT_MD5));
        objectMetadata.setExpires(httpResponse.getHeader(Headers.EXPIRES));
        objectMetadata.setObjectType(httpResponse.getHeader(Headers.BCE_OBJECT_TYPE));
        objectMetadata.setAppendOffset(httpResponse.getHeaderAsLong(Headers.BCE_NEXT_APPEND_OFFSET));
        objectMetadata.setContentDisposition(httpResponse.getHeader(Headers.CONTENT_DISPOSITION));
        objectMetadata.setCacheControl(httpResponse.getHeader(Headers.CACHE_CONTROL));
        objectMetadata.setxBceCrc(httpResponse.getHeader(Headers.BCE_CONTENT_CRC32));
        objectMetadata.setRestore(httpResponse.getHeader(Headers.BCE_RESTORE));
        // set whatever the BOS server returns if not null
        String storageClass = httpResponse.getHeader(Headers.BCE_STORAGE_CLASS);
        if (storageClass == null) {
            storageClass = BosClient.STORAGE_CLASS_STANDARD;
        }
        objectMetadata.setStorageClass(storageClass);
        String eTag = httpResponse.getHeader(Headers.ETAG);
        if (eTag != null) {
            objectMetadata.setETag(CharMatcher.is('"').trimFrom(eTag));
        }
        objectMetadata.setContentLength(objectMetadata.getContentLength());
        String contentRange = httpResponse.getHeader(Headers.CONTENT_RANGE);
        objectMetadata.setContentRange(contentRange);
        if (contentRange != null) {
            int pos = contentRange.lastIndexOf('/');
            if (pos >= 0) {
                try {
                    objectMetadata.setInstanceLength(Long.parseLong(contentRange.substring(pos + 1)));
                } catch (NumberFormatException e) {
                    logger.warn("Fail to parse length from " + Headers.CONTENT_RANGE + ": " + contentRange, e);
                }
            }
        }
        objectMetadata.setLastModified(httpResponse.getHeaderAsRfc822Date(Headers.LAST_MODIFIED));
        objectMetadata.setBceContentSha256(httpResponse.getHeader(Headers.BCE_CONTENT_SHA256));

        for (Map.Entry<String, String> header : httpResponse.getHeaders().entrySet()) {
            String key = header.getKey();
            if (key.startsWith(Headers.BCE_USER_METADATA_PREFIX)) {
                key = key.substring(Headers.BCE_USER_METADATA_PREFIX.length());
                objectMetadata.addUserMetadata(URLDecoder.decode(key, DEFAULT_ENCODING),
                        URLDecoder.decode(header.getValue(), DEFAULT_ENCODING));
            }
        }

        InputStream content = httpResponse.getContent();
        if (content != null) {
            if (objectMetadata.getContentLength() >= 0) {
                content = new LengthCheckInputStream(content, objectMetadata.getContentLength(),
                        LengthCheckInputStream.INCLUDE_SKIPPED_BYTES);
            }
            object.setObjectContent(new BosObjectInputStream(content, httpResponse.getHttpResponse()));
        }

        ((GetObjectResponse) response).setObject(object);
        return true;
    }

}
