package com.baidubce.services.oos.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.oos.model.Task;
import com.baidubce.services.oos.model.Template;
import com.baidubce.services.oos.model.common.KVModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseExecutionRequest extends AbstractBceRequest {
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

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
