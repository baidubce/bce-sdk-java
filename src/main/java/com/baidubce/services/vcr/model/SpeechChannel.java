/*
 * Copyright 2017 Baidu, Inc.
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
package com.baidubce.services.vcr.model;

import java.util.List;

/**
 * Media speech result for a channel.
 */
public class SpeechChannel {
    private Integer channel;
    private List<SpeechStatement> statements;

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public List<SpeechStatement> getStatements() {
        return statements;
    }

    public void setStatements(List<SpeechStatement> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpeechChannel{");
        sb.append("channel=").append(channel);
        sb.append(", statements=").append(statements);
        sb.append('}');
        return sb.toString();
    }
}
