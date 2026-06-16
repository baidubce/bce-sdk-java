package com.baidubce.services.et.model;

import java.util.List;
import lombok.ToString;

@ToString
public class RemoveEtChannelUsersRequest extends EtChannelIdRequest {
    private List<String> authorizedUsers;

    public List<String> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(List<String> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }
}
