/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * The response for CreateInstanceRequest.
 */
@Data
public class CreateInstanceResponse extends AbstractBceResponse {
    /**
     * List of id of instances created.
     */
    private List<String> instanceIds;

    private List<String> warningList;

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    @Override
    public String toString() {
        return "CreateInstanceResponse{"
                + "instanceIds=" + instanceIds
                + '}';
    }
}
