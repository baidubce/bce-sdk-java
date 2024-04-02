package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Style {
    private String displayType;
    private String nullPointMode;
    private int threshold;
    private int decimals;
    private boolean isEdit;
    private String unit;
}
