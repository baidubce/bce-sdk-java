package com.baidubce.services.bcm.model.alarm;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class AlarmConfig extends AbstractBceResponse {
    private String alarmDescription;
    private String alarmName;
    private String aliasName;
    private String userId;
    private String scope;
    private String region;
    private AlarmLevel level;
    private MonitorObject monitorObject;
    private Set<String> alarmActions;
    private Set<String> okActions;
    private Set<String> insufficientActions;
    private String srcName;
    private String srcType;
    private AlarmType type = AlarmType.NORMAL;
    private List<String> eventTypeList;
    private int insufficientCycle;
    private int maxRepeatCount;
    private int repeatAlarmCycle;
    private String callbackUrl = "";
    private String callbackToken = "";
    private List<List<AlarmRule>> rules;
}


