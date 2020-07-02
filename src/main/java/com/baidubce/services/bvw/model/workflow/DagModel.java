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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The class of workflow param, is a directed acyclic graph(DAG).
 * DAG contains all of the job stages and the dependencies between them.
 */
public class DagModel {

    /**
     * Define all of the stages.
     * Structure: <stageName, stage>
     */
    private Map<String, StageModel> stages;
    /**
     * Define the dependencies between the stages.
     * Structure: <stageName, [stageName, stageName, ...]>
     */
    private Map<String, List<String>> dependencies;

    /**
     * Construct a dag with stages.
     * @param stages        The stage names and stages
     * @param dependencies  The dependencies between stages
     * @return A dag with specified stages
     */
    public static DagModel of(Map<String, StageModel> stages, Map<String, List<String>> dependencies) {
        DagModel dagModel = new DagModel();
        dagModel.setStages(stages);
        dagModel.setDependencies(dependencies);
        return dagModel;
    }

    public Map<String, StageModel> getStages() {
        return stages;
    }

    public void setStages(Map<String, StageModel> stages) {
        this.stages = stages;
    }

    public Map<String, List<String>> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Map<String, List<String>> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("stages", stages)
                .append("dependencies", dependencies)
                .toString();
    }

}
