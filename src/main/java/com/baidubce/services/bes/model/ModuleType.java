package com.baidubce.services.bes.model;

public enum ModuleType {
    ES_NODE("es_node"),
    KIBANA("kibana"),
    ES_DEDICATED_MASTER("es_dedicated_master");

    private String moduleType;

    ModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleType() {
        return moduleType;
    }
}
