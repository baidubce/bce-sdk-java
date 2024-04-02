package com.baidubce.services.bcm.model.action;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Action {
    private String productName;
    private String name;
    private String alias;
    private String source = "USER";
    private String type;
    private List<ActionDisableTime> disableTimes ;
    private List<ActionNotification> notifications;
    private List<ActionCallBack> actionCallBacks ;
    private List<Member> members;
    private List<ActionUserInfo> userInfos;
    private Map<String, List<ActionUserInfo>> groupInfos;
    private Date lastModifiedDate;
}