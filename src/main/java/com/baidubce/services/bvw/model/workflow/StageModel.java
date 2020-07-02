/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.workflow;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The stage in DAG.
 */
public class StageModel {

    /**
     * The stage id.
     */
    private String stageId;
    /**
     * The stage name, is unique in the current workflow.
     */
    private String name;
    /**
     * The stage param, is different in each type of stage, will
     * be deserialize to specific type of job.
     */
    private StageParamModel param;
    /**
     * The stage type.
     */
    private StageType type;

    /**
     * Construct a stage with name, param and type.
     *
     * @param name The stage name
     * @param param The stage param
     * @param type The stage type
     * @return A stage with specified name, param and type
     */
    public static StageModel of(String name, StageParamModel param, StageType type) {
        StageModel stageModel = new StageModel();
        stageModel.setName(name);
        stageModel.setParam(param);
        stageModel.setType(type);
        return stageModel;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StageParamModel getParam() {
        return param;
    }

    public void setParam(StageParamModel param) {
        this.param = param;
    }

    public StageType getType() {
        return type;
    }

    public void setType(StageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("stageId", stageId)
                .append("name", name)
                .append("param", param)
                .append("type", type)
                .toString();
    }

}
