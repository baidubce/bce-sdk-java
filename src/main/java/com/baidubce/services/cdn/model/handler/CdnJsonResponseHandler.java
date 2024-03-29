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
package com.baidubce.services.cdn.model.handler;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cdn.model.DescribeIpsResponse;
import com.baidubce.services.cdn.model.util.IpDetail;
import com.baidubce.util.JsonUtils;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.util.List;

/**
 * HTTP body json response handler for Baidu BCE responses.
 */
public class CdnJsonResponseHandler implements HttpResponseHandler {
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        InputStream content = httpResponse.getContent();

        if (content != null) {
            if (response.getMetadata().getContentLength() > 0
                    || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
                try {
                    if (response.getClass().equals(DescribeIpsResponse.class)) {
                        String value = EntityUtils.toString(httpResponse.getHttpResponse().getEntity());
                        List<IpDetail> list = (List<IpDetail>) JsonUtils.fromJsonString(value, List.class);
                        DescribeIpsResponse response1 = (DescribeIpsResponse) response;
                        response1.setIpInfo(list);
                    }
                    JsonUtils.load(content, response);
                } catch (Exception e) {}
            }
            content.close();
        }

        return true;
    }
}
