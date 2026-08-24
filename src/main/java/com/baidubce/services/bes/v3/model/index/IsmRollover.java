package com.baidubce.services.bes.v3.model.index;

import lombok.Data;

@Data
public class IsmRollover {
    private Boolean enabled;
    private String minIndexAge;
    private String minDocCount;
    private String minSize;
}
