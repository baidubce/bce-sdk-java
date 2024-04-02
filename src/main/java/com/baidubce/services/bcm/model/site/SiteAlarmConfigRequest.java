package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * create by pangyangyang on 2023/12/13
 */
@Data
public class SiteAlarmConfigRequest extends AbstractBceRequest {

    private String taskId;
    private String comment = "";
    private String userId;
    private String alarmName;
    private String aliasName;
    private String namespace = "BCM_SITE";
    private AlarmLevel level;
    private Boolean actionEnabled;
    private Boolean policyEnabled;
    private Set<String> incidentActions;
    private Set<String> resumeActions;
    private Set<String> insufficientActions;
    private int insufficientCycle;
    private List<SiteAlarmRule> rules;
    private String region = "bj";
    private String callbackUrl = "";
    private String callbackToken = "";
    private String protocolType;
    private String cycle;
    private String method;
    private String siteMonitor;
    private String tag = "";
    private String srcType = "SITE";
    private int repeatAlarmCycle;
    private int maxRepeatCount;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
