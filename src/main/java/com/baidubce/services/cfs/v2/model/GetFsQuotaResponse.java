/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.cfs.v2.model;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFsQuotaResponse extends BaseBceResponse {
    /**
     * total
     */
    private Integer total;

    /**
     * created
     */
    private Integer created;

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getCreated() {
        return this.created;
    }

    @Override
    public String toString() {
        return "GetFsQuotaResponse{"
                + "total=" + total + "\n"
                + "created=" + created + "\n"
                + "}";
    }

}