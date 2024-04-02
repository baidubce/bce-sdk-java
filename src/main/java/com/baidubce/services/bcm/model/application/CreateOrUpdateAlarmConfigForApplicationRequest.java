package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.model.alarm.ACAlarmType;
import com.baidubce.services.bcm.model.alarm.AlarmLevel;
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
public class CreateOrUpdateAlarmConfigForApplicationRequest extends AbstractBceRequest {

    private String alarmDescription;
    private String alarmName;
    private String userId;
    private String appName;
    private MonitorObjectType monitorObjectType;
    private ACMonitorObject monitorObject;
    private String srcName;

    private String srcType;
    private ACAlarmType type;
    private AlarmLevel level;
    private Boolean actionEnabled;
    private Boolean policyEnabled;
    private List<List<AppAlarmRule>> rules;
    private Set<String> incidentActions;
    private Set<String> resumeActions;
    private Set<String> insufficientActions;
    /**
     * 无数据报警判断周期
     */
    private int insufficientCycle;

    private int repeatAlarmCycle; // 重复提醒周期
    private int maxRepeatCount; // 重复提醒次数
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}