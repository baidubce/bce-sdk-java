package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NodeAvailableSpec {
    private String type;
    private Integer nodeMinSize;
    private Integer nodeMaxSize;
    private List<String> nodeTypes;
    private String defaultNodeType;
    private List<String> diskTypes;
    private String defaultDiskType;
    private Map<String, Integer> diskMinSize;
    private Map<String, Integer> diskMaxSize;
}