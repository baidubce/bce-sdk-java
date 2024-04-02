package com.baidubce.services.bcm.model.event;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventPolicyResponse extends AbstractBceResponse {
    private String accountId;
    private String serviceName;
    private String name;
    private BlockStatus blockStatus;
    private EventFilter eventFilter = new EventFilter();
    private EventResourceFilter resource = new EventResourceFilter();
    private Set<String> incidentActions = new HashSet<String>();
}
