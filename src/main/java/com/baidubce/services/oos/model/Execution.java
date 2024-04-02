package com.baidubce.services.oos.model;

import com.baidubce.services.oos.model.common.KVModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Execution {
    private String id;
    private String description;
    private Template template;
    private long createdTimestamp;
    private long updatedTimestamp;
    private long finishedTimestamp;
    private String state;
    private Map<String, Object> properties;
    private List<Task> tasks;
    private List<KVModel> tags;
    private String trigger;
}
