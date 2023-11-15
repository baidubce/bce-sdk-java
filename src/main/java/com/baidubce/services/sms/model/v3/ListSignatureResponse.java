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
package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsResponse;

import java.util.List;

public class ListSignatureResponse extends SmsResponse {

    public List<GetSignatureResponse> getSignatureApplies() {
        return signatureApplies;
    }

    public void setSignatureApplies(List<GetSignatureResponse> signatureApplies) {
        this.signatureApplies = signatureApplies;
    }

    private List<GetSignatureResponse> signatureApplies;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * The total count of the querying signatures.
     * TotalCount is null when byPage of the listSignatureRequest is false.
     */
    private int totalCount;

    @Override
    public String toString() {
        return "ListSignatureResponse [signatureList=" + signatureApplies + "]";
    }
}
