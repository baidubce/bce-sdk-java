package com.baidubce.services.bes.v3.model.index;

import lombok.Data;

@Data
public class IsmForceMerge {
    private Boolean enabled;
    private String minIndexAge;
    private Integer maxNumSegments;
}
