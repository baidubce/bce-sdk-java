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
package com.baidubce.services.eipgroup.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;

/**
 * The request for list eip group.
 */
public class ListEipGroupRequest extends ListRequest {

    /**
     * The id of eip group.
     */
    private String id;

    /**
     * The name of eip group.
     */
    private String name;

    /**
     * The status of eip group.
     */
    private String status;

    /**
     * Configure id for the request.
     *
     * @param id The id of ListEipGroupRequest
     * @return ListEipGroupRequest with specific id
     */
    public ListEipGroupRequest withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Configure name for the request.
     *
     * @param name The name of ListEipGroupRequest
     * @return ListEipGroupRequest with specific name
     */
    public ListEipGroupRequest withNatId(String name) {
        this.name = name;
        return this;
    }

    /**
     * Configure status for the request.
     *
     * @param status The status of ListEipGroupRequest
     * @return ListEipGroupRequest with specific status
     */
    public ListEipGroupRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
