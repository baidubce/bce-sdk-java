package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ScopeValue {
    private String name;
    private String value;
    private boolean hasChildren;
}
