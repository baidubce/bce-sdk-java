package com.baidubce.services.bec.model.vm;

import lombok.Data;

import java.util.List;

@Data
public class KeyConfig {

    private String type;

    private String adminPass;

    private List<String> bccKeyPairIdList;
}