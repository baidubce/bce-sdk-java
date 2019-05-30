/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ruleengine;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.ruleengine.model.FeedMessageRequest;
import com.baidubce.services.ruleengine.model.FeedMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * client to rule data api
 *
 * Created by huangjiatian on 2019/03/07.
 */
public class RuleEngineDataClient extends AbstractRuleEngineBceClient {

    private static Logger logger = LoggerFactory.getLogger(RuleEngineDataClient.class);

    private static final String RULE_UUID_KEY = "ruleid";
    private static final String RULE_DATA = "ruledata";
    private static final String FEED = "feed";
    private static final String ENDPOINT = "iotredata.gz.baidubce.com";

    public RuleEngineDataClient(BceClientConfiguration configuration) {
        super(configuration.getEndpoint() == null ? configuration.withEndpoint(ENDPOINT) : configuration, HANDLERS);
    }

    public RuleEngineDataClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    public FeedMessageResponse feed(String uuid, List<String> msgs) {
        return feed(uuid, new FeedMessageRequest().withSimplePayloads(msgs));
    }

    public FeedMessageResponse feed(String uuid, FeedMessageRequest request) {
        return feed(uuid, request, true);
    }

    public FeedMessageResponse feed(String uuid, FeedMessageRequest request, boolean isGzip) {
        checkNotNull(uuid, "uuid should not be null.");
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, RULE_DATA, FEED);
        internalRequest.addParameter(RULE_UUID_KEY, uuid);

        if (isGzip) {
            byte[] bytes = toGzipBytes(request);
            fillInHeadAndBodyForGzip(internalRequest, bytes);

            FeedMessageResponse response = null;
            try {
                response = this.invokeHttpClient(internalRequest, FeedMessageResponse.class);
            } finally {
                try {
                    internalRequest.getContent().close();
                } catch (IOException e) {
                    logger.error("Request content close failed.", e);
                }
            }

            return response;
        } else {
            fillInHeadAndBody(request, internalRequest);
            return this.invokeHttpClient(internalRequest, FeedMessageResponse.class);
        }
    }
}
