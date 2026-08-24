package com.baidubce.services.bes.v3.model.index;

import lombok.Data;

import java.util.List;

@Data
public class IsmTemplate {
    private List<String> indexPatterns;
    private String priority;
}
