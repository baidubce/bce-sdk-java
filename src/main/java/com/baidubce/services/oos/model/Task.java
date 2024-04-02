package com.baidubce.services.oos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private Integer loopIndex;
    private long revision;
    private long createdTimestamp;
    private long updatedTimestamp;
    private long finishedTimestamp;
    private String state;
    private Operator operator;
    private String reason;
    private Map<String, Object> initContext;
    private Map<String, Object> context;
    private Map<String, Object> outputContext;
    private int tries;
    private List<Task> children = new ArrayList<Task>();
    private List<Log> log;
}
