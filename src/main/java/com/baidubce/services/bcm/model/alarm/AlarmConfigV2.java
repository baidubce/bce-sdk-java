package com.baidubce.services.bcm.model.alarm;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.CommonKV;
import lombok.Data;

import java.util.List;

@Data
public class AlarmConfigV2 extends AbstractBceResponse {
    private String userId;
    private String aliasName;
    private String alarmName;
    private String scope;
    private String region;
    private TargetType targetType;
    private String resourceType = "Instance";
    private AlarmLevel alarmLevel = AlarmLevel.CRITICAL;
    private List<String> targetInstanceGroups;
    private List<CommonKV> targetInstanceTags;
    private String callbackUrl;
    private String callbackToken;
    private int insufficientDataPendingPeriod;
    private int alarmRepeatInterval;
    private int alarmRepeatCount;

    private List<AlarmConfigPolicyV2> policies;
    private List<TargetInstance> targetInstances;
    private List<AlarmConfigActionV2> actions;

}
