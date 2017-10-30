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
package com.baidubce.services.bcc.model.securitygroup;

import com.baidubce.model.ListResponse;
import com.baidubce.services.bcc.model.SecurityGroupModel;

import java.util.List;

/**
 * The response for ListSecurityGroupsRequest.
 */
public class ListSecurityGroupsResponse extends ListResponse {

    /**
     * The list of SecurityGroup detail model.
     */
    private List<SecurityGroupModel> securityGroups;

    public List<SecurityGroupModel> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(List<SecurityGroupModel> securityGroups) {
        this.securityGroups = securityGroups;
    }
}
