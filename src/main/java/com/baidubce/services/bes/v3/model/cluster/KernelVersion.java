package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

import java.util.List;

@Data
public class KernelVersion {
    private String engine;
    private String engineType;
    private String version;
    private String status;
    private List<String> features;
    private List<String> scenes;
}
