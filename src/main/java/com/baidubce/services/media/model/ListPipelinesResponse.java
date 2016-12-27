/*
 * Copyright 2015 Baidu, Inc.
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

package com.baidubce.services.media.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

public class ListPipelinesResponse extends AbstractBceResponse {
    private List<PipelineStatus> pipelines = new ArrayList<PipelineStatus>();

    /**
     * list of pipeline information
     **/
    public List<PipelineStatus> getPipelines() {
        return pipelines;
    }

    public void setPipelines(List<PipelineStatus> pipelines) {
        this.pipelines = pipelines;
    }

    @Override
    public String toString() {
        return "ListPipelinesResponse [pipelines=" + pipelines + "]";
    }
    
}
