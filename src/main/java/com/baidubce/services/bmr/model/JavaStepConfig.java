/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
 * Represent configuration for a custom jar step.
 *
 * A custom jar step can be configured with name, actionOnFailure, jar, main class and arguments.
 * The essential options are jar, main class and actionOnFailure,
 * and the optional ones are name and arguments.
 */
public class JavaStepConfig extends StepConfig {
    private static final String JAVA_STEP = "Java";
    private static final String JAR = "jar";
    private static final String MAIN_CLASS = "mainClass";
    private static final String ARGUMENTS = "arguments";

    public JavaStepConfig() {
        this.setType(JAVA_STEP);
    }

    /**
     * Configure the BOS path for step's .jar file.
     *
     * @param jar The BOS path for the step's .jar file.
     * @return JavaStepConfig
     */
    public JavaStepConfig withJar(String jar) {
        this.addProperty(JAR, jar);
        return this;
    }

    /**
     * Configure the main class for the step.
     *
     * @param mainClass The main class for the step.
     * @return JavaStepConfig
     */
    public JavaStepConfig withMainClass(String mainClass) {
        this.addProperty(MAIN_CLASS, mainClass);
        return this;
    }

    /**
     * Configure the arguments for the step.
     *
     * @param arguments The arguments for the step.
     * @return JavaStepConfig
     */
    public JavaStepConfig withArguments(String arguments) {
        this.addProperty(ARGUMENTS, arguments);
        return this;
    }

    /**
     * Configure the action on failure for the java step.
     * This property is set to enum value:
     *     "Continue": continue to execute other steps.
     *     "TerminateCluster": terminate the cluster when this step fails.
     *     "CancelAndWait": cancel the other pending steps and set the cluster's status to WAITING.
     *
     * @param actionOnFailure The action on step's failure.
     * @return JavaStepConfig
     */
    public JavaStepConfig withActionOnFailure(String actionOnFailure) {
        this.setActionOnFailure(actionOnFailure);
        return this;
    }

    /**
     * Configure the name of the step.
     *
     * @param name The name of the step.
     * @return JavaStepConfig
     */
    public JavaStepConfig withName(String name) {
        this.setName(name);
        return this;
    }
}
