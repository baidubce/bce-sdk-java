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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetJobRequest extends AbstractBceRequest {

    private String jobId = null;

    /**
     **/
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        checkStringNotEmpty(jobId, "The parameter jobId should NOT be null or empty string.");
        this.jobId = jobId;
    }

    public GetJobRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetJobRequest {\n");

        sb.append("    JobId: ").append(jobId).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
