package com.baidubce.services.bcm.model.action;

import lombok.Data;

import java.sql.Time;

@Data
public class ActionDisableTime {
    private Time from;
    private Time to;
}