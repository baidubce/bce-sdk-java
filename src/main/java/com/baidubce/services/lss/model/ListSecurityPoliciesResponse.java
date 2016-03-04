/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.lss.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class ListSecurityPoliciesResponse extends AbstractBceResponse {
    private List<SecurityPolicy> securityPolicies = null;

    public List<SecurityPolicy> getSecurityPolicies() {
        return securityPolicies;
    }

    public void setSecurityPolicies(List<SecurityPolicy> securityPolicies) {
        this.securityPolicies = securityPolicies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListSecurityPoliciesResponse {\n");
        sb.append("    securityPolicies: ").append(securityPolicies).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
