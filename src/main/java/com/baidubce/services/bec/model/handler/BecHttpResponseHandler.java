/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.handler;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bec.model.Backends;
import com.baidubce.services.bec.model.blb.GetBecBlbBindingPodListWithStsResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The bec http response handler.
 */
public class BecHttpResponseHandler implements HttpResponseHandler {

    public static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (!response.getClass().equals(GetBecBlbBindingPodListWithStsResponse.class)) {
            return false;
        }
        InputStream content = httpResponse.getContent();
        if (content == null) {
            return false;
        }
        if (response.getMetadata().getContentLength() > 0
                || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
            String value = EntityUtils.toString(httpResponse.getHttpResponse().getEntity());
            JavaType javaType = getCollectionType(ArrayList.class, Backends.class);
            List<Backends> list = (List<Backends>) mapper.readValue(value, javaType);
            GetBecBlbBindingPodListWithStsResponse response1 = (GetBecBlbBindingPodListWithStsResponse) response;
            response1.setResult(list);
            return true;
        }
        content.close();
        return false;
    }


    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
