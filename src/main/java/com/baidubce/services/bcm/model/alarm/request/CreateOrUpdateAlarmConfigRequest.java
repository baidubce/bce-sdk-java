package com.baidubce.services.bcm.model.alarm.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.model.alarm.AlarmLevel;
import com.baidubce.services.bcm.model.alarm.AlarmRule;
import com.baidubce.services.bcm.model.alarm.AlarmType;
import com.baidubce.services.bcm.model.alarm.MonitorObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAlarmConfigRequest extends AbstractBceRequest {
    private String alarmDescription;
    private String alarmName;
    private String aliasName;
    private String userId;
    private String scope;
    private String region;
    private AlarmLevel level = AlarmLevel.CRITICAL;
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

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
