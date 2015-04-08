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

import com.baidubce.http.BceHttpResponse;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;

import java.io.InputStream;

/**
 * HTTP body json response handler for Baidu BCE responses.
 */
public class BceJsonResponseHandler implements HttpResponseHandler {
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        InputStream content = httpResponse.getContent();
        if (content != null) {
            if (response.getMetadata().getContentLength() > 0) {
                JsonUtils.load(content, response);
            }
            content.close();
        }
        return true;
    }
}
