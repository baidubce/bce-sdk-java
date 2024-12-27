package com.baidubce.services.bcc.model.instance;

import com.baidubce.model.ListResponse;
import com.baidubce.services.bcc.model.RecycleInstanceModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Data
public class ListRecycleInstanceResponse extends ListResponse {
    private List<RecycleInstanceModel> instances;
}
