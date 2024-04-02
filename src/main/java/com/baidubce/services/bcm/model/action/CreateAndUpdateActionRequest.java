package com.baidubce.services.bcm.model.action;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class CreateAndUpdateActionRequest extends AbstractBceRequest{
    private String userId;
    private String productName;
    private String alias;
    private String source = "USER";
    private String type;
    private List<ActionDisableTime> disableTimes = new ArrayList<ActionDisableTime>();
    private List<ActionNotification> notifications;
    private List<ActionCallBack> actionCallBacks  = new ArrayList<ActionCallBack>();
    private List<Member> members;
    private Date lastModifiedDate;
    private String name;
    private List<ActionUserInfo> userInfos;
    private Map<String, List<ActionUserInfo>> groupInfos;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}