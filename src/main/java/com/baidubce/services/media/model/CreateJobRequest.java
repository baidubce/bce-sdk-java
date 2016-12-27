/*
 * Copyright 2015 Baidu, Inc.
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
 
package com.baidubce.services.media.model;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.baidubce.util.Validate.checkNotNull;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateJobRequest extends AbstractBceRequest {

    private String pipelineName = null;
    private Source source       = null;
    private Target target       = null;

    /**
     * 任务所属的pipelineName
     **/
    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
    }

    public CreateJobRequest withPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
        return this;
    }

    /**
     * 输入的原始信息的集合
     **/
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        checkNotNull(source, "The parameter source should NOT be null.");
        this.source = source;
    }

    public CreateJobRequest withSource(Source source) {
        checkNotNull(source, "The parameter source should NOT be null.");
        this.source = source;
        return this;
    }

    /**
     * 输出信息的结合
     **/
    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        checkNotNull(target, "The parameter target should NOT be null.");
        this.target = target;
    }

    public CreateJobRequest withTarget(Target target) {
        checkNotNull(target, "The parameter target should NOT be null.");
        this.target = target;
        return this;
    }

    public CreateJobRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateJobRequest {\n");

        sb.append("    pipelineName: ").append(pipelineName).append("\n");
        sb.append("    source: ").append(source).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
