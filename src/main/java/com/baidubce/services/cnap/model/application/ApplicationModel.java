package com.baidubce.services.cnap.model.application;


/**
 * The model for application.
 */
public class ApplicationModel {

    public static final int DEFAULT_DEPLOY_TYPE = 1;

    /**
     * The name of application.
     */
    private String name;

    /**
     * The description of application.
     */
    private String description = "";

    /**
     * The type of application. eg: 1 or 4.
     * 1 indicates simple application.
     * 4 indicates micro service application.
     */
    private int workloadType = WorkloadType.MICROSERVICE_APPLICATION;

    /**
     * The deploy type of application.eg: 1.
     * 1 indicates cce deploy.
     */
    private int deployType = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWorkloadType() {
        return workloadType;
    }

    public void setWorkloadType(int workloadType) {
        this.workloadType = workloadType;
    }

    public int getDeployType() {
        return deployType;
    }

    public void setDeployType(int deployType) {
        this.deployType = deployType;
    }


    public ApplicationModel withName(String name) {
        this.setName(name);
        return this;
    }

    public ApplicationModel withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public ApplicationModel withWorkloadType(int workloadType) {
        this.setWorkloadType(workloadType);
        return this;
    }

    public ApplicationModel withDeployType(int deployType) {
        this.setDeployType(deployType);
        return this;
    }

}
