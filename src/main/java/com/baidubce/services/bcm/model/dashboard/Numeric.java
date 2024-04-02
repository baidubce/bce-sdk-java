package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Numeric {
    private double avg;
    private double cnt;
    private double max;
    private double min;
    private double sum;
}
