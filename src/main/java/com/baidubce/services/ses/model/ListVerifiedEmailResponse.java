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
package com.baidubce.services.ses.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Response object of listing verified Email. It contains list of the detail of verified email.
 */
public class ListVerifiedEmailResponse extends SesResponse {

    /**
     * List of verified email.
     * 
     * @see EmailDetailModel
     */
    private List<EmailDetailModel> details;

    public ListVerifiedEmailResponse withDetails(List<EmailDetailModel> details) {
        setDetails(details);
        return this;
    }

    public ListVerifiedEmailResponse withDetail(EmailDetailModel detail) {
        if (details == null) {
            details = new ArrayList<EmailDetailModel>();
        }
        details.add(detail);

        return this;
    }

    public List<EmailDetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<EmailDetailModel> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ListVerifiedEmailResponse [details=" + details + "]";
    }

}
