/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bmr.model;

/**
 * Represent configuration for a streaming step.
 * <p>
 * A streaming step can be configured with name, actionOnFailure, mapper, reducer,
 * input, output and arguments.
 * The essential options are mapper, input, output and actionOnFailure,
 * and the optional ones are name, reducer and arguments.
 */
public class StreamingStepConfig extends StepConfig {
    private static final String STREAMING_STEP = "Streaming";
    private static final String INPUT = "input";
    private static final String MAPPER = "mapper";
    private static final String OUTPUT = "output";
    private static final String ARGUMENTS = "arguments";
    private static final String REDUCER = "reducer";

    public StreamingStepConfig() {
        this.setType(STREAMING_STEP);
        this.addProperty(REDUCER, "");
    }

    /**
     * Configure the input for the step.
     *
     * @param input The input path for the step.
     *
     * @return StreamingStepConfig
     */
    public StreamingStepConfig withInput(String input) {
        this.addProperty(INPUT, input);
        return this;
    }

    /**
     * Configure the mapper program for the step.
     *
     * @param mapper The mapper program for the step.
     *
     * @return StreamingStepConfig
     */
    public StreamingStepConfig withMapper(String mapper) {
        this.addProperty(MAPPER, mapper);
        return this;
    }

    /**
     * Configure the output for the step.
     *
     * @param output The output path for the step.
     *
     * @return StreamingStepConfig
     */
    public StreamingStepConfig withOutput(String output) {
        this.addProperty(OUTPUT, output);
        return this;
    }

    /**
     * Configure the arguments for the step.
     *
     * @param arguments The arguments for the step.
     *
     * @return StreamingStepConfig
     */
    public StreamingStepConfig withArguments(String arguments) {
        this.addProperty(ARGUMENTS, arguments);
        return this;
    }

    /**
     * Configure the reducer program for the step.
     *
     * @param reducer The reducer program for the step.
     *
     * @return StreamingStepConfig
     */
    public StreamingStepConfig withReducer(String reducer) {
        this.addProperty(REDUCER, reducer);
        return this;
    }

    /**
     * Configure the name for the step.
     *
     * @param name The name for the step.
     *
     * @return StreamingStepConfig
     */
    public StreamingStepConfig withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * Configure the action on failure for the streaming step.
     * This property is set to enum value:
     * "Continue": continue to execute other steps.
     * "TerminateCluster": terminate the cluster when this step fails.
     * "CancelAndWait": cancel the other pending steps and set the cluster's status to WAITING.
     *
     * @param actionOnFailure The action on step's failure.
     *
     * @return StreamingStepConfig
     */
    public StreamingStepConfig withActionOnFailure(String actionOnFailure) {
        this.setActionOnFailure(actionOnFailure);
        return this;
    }
}
