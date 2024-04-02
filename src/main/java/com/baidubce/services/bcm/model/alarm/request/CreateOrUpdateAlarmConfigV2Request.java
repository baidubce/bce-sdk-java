package com.baidubce.services.bcm.model.alarm.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.model.CommonKV;
import com.baidubce.services.bcm.model.alarm.AlarmConfigActionV2;
import com.baidubce.services.bcm.model.alarm.AlarmConfigPolicyV2;
import com.baidubce.services.bcm.model.alarm.AlarmLevel;
import com.baidubce.services.bcm.model.alarm.TargetInstance;
import com.baidubce.services.bcm.model.alarm.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAlarmConfigV2Request extends AbstractBceRequest {
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

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
