/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ruleengine.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * feed method parameter
 *
 * Created by huangjiatian on 2019/03/07.
 */
public class FeedMessageRequest extends AbstractBceRequest {

    private List<FeedMessage> msgs;

    public FeedMessageRequest() {
        this.msgs = new ArrayList<FeedMessage>();
    }

    public FeedMessageRequest(List<FeedMessage> msgs) {
        this.msgs = msgs;
    }

    public void setMsgs(List<FeedMessage> msgs) {
        this.msgs = msgs;
    }

    public List<FeedMessage> getMsgs() {
        return msgs;
    }

    public FeedMessageRequest withPayloads(List<FeedMessage> payloads) {
        checkNotNull(payloads, "payloads should not be null.");
        msgs.addAll(payloads);

        return this;
    }

    public FeedMessageRequest withSimplePayloads(List<String> payloads) {
        checkNotNull(payloads, "payloads should not be null.");
        for (String payload : payloads) {
            msgs.add(new FeedMessage(payload));
        }

        return this;
    }

    @Override
    public FeedMessageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
