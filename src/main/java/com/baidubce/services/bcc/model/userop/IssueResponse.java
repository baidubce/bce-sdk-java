/*
 * Copyright 2025 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.userop;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class IssueResponse {
    /**
     * issue name
     */
    private String issueName;


    /**
     * issue effect
     */
    private String issueEffect;

    /**
     * issue description
     */
    private String issueDescription;

    /**
     * issue occur time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date issueOccurTime;


    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueName() {
        return this.issueName;
    }

    public void setIssueEffect(String issueEffect) {
        this.issueEffect = issueEffect;
    }

    public String getIssueEffect() {
        return this.issueEffect;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueDescription() {
        return this.issueDescription;
    }

    public void setIssueOccurTime(Date issueOccurTime) {
        this.issueOccurTime = issueOccurTime;
    }

    public Date getIssueOccurTime() {
        return this.issueOccurTime;
    }

    @Override
    public String toString() {
        return "IssueResponse{"
                + "issueName=" + issueName + "\n"
                + "issueEffect=" + issueEffect + "\n"
                + "issueDescription=" + issueDescription + "\n"
                + "issueOccurTime=" + issueOccurTime + "\n"
                + "}";
    }

}