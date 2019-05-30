/*
 * Copyright (c) 2018-2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dumap.handler;

import java.io.InputStream;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.dumap.model.BaseDuMapResponse;
import com.google.common.io.ByteStreams;

import lombok.extern.slf4j.Slf4j;

/**
 * DuMap response json handler.
 */
@Slf4j
public class DuMapResponseHandler implements HttpResponseHandler {
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        InputStream content = httpResponse.getContent();
        BaseDuMapResponse duMapResponse = (BaseDuMapResponse) response;
        if (content != null) {
            duMapResponse.setPayload(new String(ByteStreams.toByteArray(content)));
            content.close();
        }
        return true;
    }
}
