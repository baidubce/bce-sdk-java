package com.baidubce.services.bmr.model;

/**
 * Represent configuration for a spark step.
 * <p>
 * A spark step can be configured with name, actionOnFailure, jar, submitOptions and arguments.
 * The essential options are mapper, input, output and actionOnFailure,
 * and the optional ones are name and arguments.
 */
public class SparkStepConfig extends StepConfig {
    private static final String SPARK_STEP = "Spark";
    private static final String JAR = "jar";
    private static final String SUBMIT_OPITONS = "submitOptions";
    private static final String ARGUMENTS = "arguments";

    public SparkStepConfig() {
        this.setType(SPARK_STEP);
    }

    /**
     * Configure the BOS path for step's .jar file.
     *
     * @param jar The BOS path for the step's .jar file.
     * @return SparkStepConfig
     */
    public SparkStepConfig withJar(String jar) {
        this.addProperty(JAR, jar);
        return this;
    }

    /**
     * Configure the submit options for the step.
     *
     * @param submitOptions The main class for the step.
     * @return SparkStepConfig
     */
    public SparkStepConfig withSubmitOptions(String submitOptions) {
        this.addProperty(SUBMIT_OPITONS, submitOptions);
        return this;
    }

    /**
     * Configure the arguments for the step.
     *
     * @param arguments The arguments for the step.
     * @return SparkStepConfig
     */
    public SparkStepConfig withArguments(String arguments) {
        this.addProperty(ARGUMENTS, arguments);
        return this;
    }

    /**
     * Configure the action on failure for the java step.
     * This property is set to enum value:
     * "Continue": continue to execute other steps.
     * "TerminateCluster": terminate the cluster when this step fails.
     * "CancelAndWait": cancel the other pending steps and set the cluster's status to WAITING.
     *
     * @param actionOnFailure The action on step's failure.
     * @return SparkStepConfig
     */
    public SparkStepConfig withActionOnFailure(String actionOnFailure) {
        this.setActionOnFailure(actionOnFailure);
        return this;
    }

    /**
     * Configure the name of the step.
     *
     * @param name The name of the step.
     * @return SparkStepConfig
     */
    public SparkStepConfig withName(String name) {
        this.setName(name);
        return this;
    }
}
