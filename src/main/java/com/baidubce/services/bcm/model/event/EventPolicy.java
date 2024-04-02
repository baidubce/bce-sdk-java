package com.baidubce.services.bcm.model.event;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class EventPolicy extends AbstractBceRequest {

    private String accountId;
    private String serviceName;
    private String name;
    private BlockStatus blockStatus;
    private EventFilter eventFilter = new EventFilter();
    private EventResourceFilter resource = new EventResourceFilter();
    private Set<String> incidentActions = new HashSet<String>();

    @Override
    public EventPolicy withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
