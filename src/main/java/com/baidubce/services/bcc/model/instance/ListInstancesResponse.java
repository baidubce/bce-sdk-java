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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.model.ListResponse;
import com.baidubce.services.bcc.model.InstanceModel;
import lombok.ToString;

import java.util.List;

/**
 * response model for query instance list
 */
@ToString
public class ListInstancesResponse extends ListResponse {

    /**
     * List of instance info
     */
    private List<InstanceModel> instances;

    public List<InstanceModel> getInstances() {
        return instances;
    }

    public void setInstances(List<InstanceModel> instances) {
        this.instances = instances;
    }

}
