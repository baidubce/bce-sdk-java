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
 * Represent configuration for a hive step.
 * <p>
 * A hive step can be configured with name, actionOnFailure, script, input, output and arguments.
 * The essential options are script and actionOnFailure,
 * and the optional ones are name, input, output and arguments.
 */
public class HiveStepConfig extends StepConfig {
    private static final String HIVE_STEP = "Hive";
    private static final String SCRIPT = "script";
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String ARGUMENTS = "arguments";

    public HiveStepConfig() {
        this.setType(HIVE_STEP);
    }

    /**
     * Configure the input path of the hive step.
     *
     * @param input The input path of the hive step.
     *
     * @return HiveStepConfig
     */
    public HiveStepConfig withInput(String input) {
        this.addProperty(INPUT, input);
        return this;
    }

    /**
     * Configure the script path of the hive step.
     *
     * @param script The script path of the hive step.
     *
     * @return HiveStepConfig
     */
    public HiveStepConfig withScript(String script) {
        this.addProperty(SCRIPT, script);
        return this;
    }

    /**
     * Configure the output path of the hive step.
     *
     * @param output The output path of the hive step.
     *
     * @return HiveStepConfig
     */
    public HiveStepConfig withOutput(String output) {
        this.addProperty(OUTPUT, output);
        return this;
    }

    /**
     * Configure the arguments of the hive step.
     *
     * @param arguments The arguments of the hive step.
     *
     * @return HiveStepConfig
     */
    public HiveStepConfig withArguments(String arguments) {
        this.addProperty(ARGUMENTS, arguments);
        return this;
    }

    /**
     * Configure the name of the hive step.
     *
     * @param name The name of the hive step.
     *
     * @return HiveStepConfig
     */
    public HiveStepConfig withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * Configure the action on failure for the hive step.
     * This property is set to enum value:
     * "Continue": continue to execute other steps.
     * "TerminateCluster": terminate the cluster when this step fails.
     * "CancelAndWait": cancel the other pending steps and set the cluster's status to WAITING.
     *
     * @param actionOnFailure The action on step's failure.
     *
     * @return HiveStepConfig
     */
    public HiveStepConfig withActionOnFailure(String actionOnFailure) {
        this.setActionOnFailure(actionOnFailure);
        return this;
    }

    /**
     * Configure the additional file for the step.
     *
     * @param remote The remote file of the additional file.
     * @param local  The local file of the additional file.
     *
     * @return HiveStepConfig
     */
    public HiveStepConfig withAdditionalFile(String remote, String local) {
        this.addAdditionalFile(remote, local);
        return this;
    }
}
