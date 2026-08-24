package com.baidubce.services.bes.v3.model.cluster;

import java.util.List;

import lombok.Data;

@Data
public class VersionUpgrade {
    private String version;
    private List<String> upgradeModes;
}
