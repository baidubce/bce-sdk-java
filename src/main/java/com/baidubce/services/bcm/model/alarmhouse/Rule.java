package com.baidubce.services.bcm.model.alarmhouse;

import lombok.Data;

@Data
public class Rule {
    private Integer seq;
    private String operator;
    private Long threshold;
}
