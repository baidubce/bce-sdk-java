package com.baidubce.services.oos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateControl {
    private double ratio;
    private int count;
}
