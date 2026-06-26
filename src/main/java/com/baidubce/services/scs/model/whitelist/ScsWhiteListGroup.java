package com.baidubce.services.scs.model.whitelist;

import java.util.List;

/**
 * Scs white list group.
 */
public class ScsWhiteListGroup {

    private String groupName;
    private List<String> ipList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }
}
