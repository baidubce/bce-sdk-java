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
 * The stage param in stage.
 */
public class StageParamModel {

    /**
     * The input param, is serialize json of associate stage job input.
     */
    private String input;
    /**
     * The output param.
     */
    private String output;
    /**
     * The dynamic params, will pass-through in the process.
     * It is specified when processing media.
     */
    private String dynamicParams;

    /**
     * Construct a stage param with input json string.
     *
     * @param input The input json string
     * @return The Stage param with input
     */
    public static StageParamModel of(String input) {
        StageParamModel stageParamModel = new StageParamModel();
        stageParamModel.setInput(input);
        return stageParamModel;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getDynamicParams() {
        return dynamicParams;
    }

    public void setDynamicParams(String dynamicParams) {
        this.dynamicParams = dynamicParams;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("input", input)
                .append("output", output)
                .append("dynamicParams", dynamicParams)
                .toString();
    }

}
