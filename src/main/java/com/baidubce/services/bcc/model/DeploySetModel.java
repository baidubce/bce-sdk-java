package com.baidubce.services.bcc.model;

import lombok.Data;

@Data
public class DeploySetModel {
    private String deploysetId;
    private String name;
    private String description;
    private int concurrency;
    private String strategy;
}
