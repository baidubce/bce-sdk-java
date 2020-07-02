/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.as.model.asgroup;

import com.baidubce.model.ListResponse;
import com.baidubce.services.as.model.NodeModel;

import java.util.List;

/**
 * The response of getting the asNode list.
 */
public class ListAsNodeResponse extends ListResponse {
    /**
     * node information
     */
    private List<NodeModel> result;

    public List<NodeModel> getResult() {
        return result;
    }

    public void setResult(List<NodeModel> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ListAsNodeResponse{" +
                "result=" + result +
                '}';
    }

}
