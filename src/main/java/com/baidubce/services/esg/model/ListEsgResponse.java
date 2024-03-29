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
package com.baidubce.services.esg.model;

import java.util.List;

import com.baidubce.model.ListResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The response for listing enterprise security group request.
 */
@Getter
@Setter
public class ListEsgResponse extends ListResponse {

    /**
     * The list of enterprise security group detail model.
     */
    private List<EnterpriseSecurityGroup> enterpriseSecurityGroups;

    @Override
    public String toString() {
        return "ListEsgResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "enterpriseSecurityGroups=" + enterpriseSecurityGroups +
                '}';
    }
}
