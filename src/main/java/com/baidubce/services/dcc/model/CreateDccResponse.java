/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dcc.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Arrays;
import java.util.List;

/**
 * The response class of creating dcc.
 */
public class CreateDccResponse extends AbstractBceResponse {

    /**
     * The ids of the dedicated hosts which had been created.
     */
    private List<String> hostIds;

    public List<String> getHostIds() {
        return hostIds;
    }

    public void setHostIds(List<String> hostIds) {
        this.hostIds = hostIds;
    }

    @Override
    public String toString() {
        return "CreateDccResponse{ hostIds=" + Arrays.toString(hostIds.toArray()) + "}";
    }
}
