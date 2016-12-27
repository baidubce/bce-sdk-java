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
 * Represent configuration for a pig step.
 *
 * A pig step can be configured with name, actionOnFailure, script, input, output and arguments.
 * The essential options are script and actionOnFailure,
 * and the optional ones are name, input, output and arguments.
 */
public class PigStepConfig extends StepConfig {
    private static final String PIG_STEP = "Pig";
    private static final String SCRIPT = "script";
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String ARGUMENTS = "arguments";

    
    public PigStepConfig() {
        this.setType(PIG_STEP);
    }

    /**
     * Configure the input path of the pig step.
     *
     * @param input The input path of the pig step.
     * @return PigStepConfig
     */
    public PigStepConfig withInput(String input) {
        this.addProperty(INPUT, input);
        return this;
    }

    /**
     * Configure the script path of the pig step.
     *
     * @param script The script path of the pig step.
     * @return PigStepConfig
     */
    public PigStepConfig withScript(String script) {
        this.addProperty(SCRIPT, script);
        return this;
    }

    /**
     * Configure the output path of the pig step.
     *
     * @param output The output path of the pig step.
     * @return PigStepConfig
     */
    public PigStepConfig withOutput(String output) {
        this.addProperty(OUTPUT, output);
        return this;
    }

    /**
     * Configure the arguments of the pig step.
     *
     * @param arguments The arguments of the pig step.
     * @return PigStepConfig
     */
    public PigStepConfig withArguments(String arguments) {
        this.addProperty(ARGUMENTS, arguments);
        return this;
    }
    
    /**
     * Configure the name of the pig step.
     *
     * @param name The name of the pig step.
     * @return PigStepConfig
     */
    public PigStepConfig withName(String name) {
        this.setName(name);
        return this;
    }
    
    /**
     * Configure the action on failure for the pig step.
     * This property is set to enum value:
     *     "Continue": continue to execute other steps.
     *     "TerminateCluster": terminate the cluster when this step fails.
     *     "CancelAndWait": cancel the other pending steps and set the cluster's status to WAITING.
     *
     * @param actionOnFailure The action on step's failure.
     * @return PigStepConfig
     */
    public PigStepConfig withActionOnFailure(String actionOnFailure) {
        this.setActionOnFailure(actionOnFailure);
        return this;
    }
}
