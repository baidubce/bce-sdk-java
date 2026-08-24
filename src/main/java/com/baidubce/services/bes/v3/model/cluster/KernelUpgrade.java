package com.baidubce.services.bes.v3.model.cluster;

import java.util.List;

import lombok.Data;

@Data
public class KernelUpgrade {
    private String kernel;
    private List<String> upgradeModes;
}
