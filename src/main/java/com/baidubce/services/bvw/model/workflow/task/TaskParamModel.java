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
package com.baidubce.services.bvw.model.workflow.task;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The task param of the stage.
 */
public class TaskParamModel {

    /**
     * The stage task input param.
     */
    private String input;
    /**
     * The stage task output param.
     */
    private TaskBaseOutputModel output;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public TaskBaseOutputModel getOutput() {
        return output;
    }

    public void setOutput(TaskBaseOutputModel output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("input", input)
                .append("output", output)
                .toString();
    }

}
