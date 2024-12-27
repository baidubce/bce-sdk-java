package com.baidubce.services.bcm.model.alarmhouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class Action {
    private String type;
    private String name;
    private Long executedTime;
    private String alias;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> notifications;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> callBacks;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> members;
}
