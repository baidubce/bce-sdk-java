package com.baidubce.services.bcm.model.event;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventFilter {
    private EventLevel eventLevel;
    private Set<String> eventTypeList;
    private Set<String> eventAliasNames = new HashSet<String>();
}
