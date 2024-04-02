package com.baidubce.services.oos.model;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.oos.model.common.KVModel;
import com.baidubce.services.oos.model.common.LinkModel;
import com.baidubce.services.oos.model.common.TemplateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template extends AbstractBceResponse {
    private String id;
    private String name;
    private String ref;
    private TemplateType type;
    private String description;
    private List<KVModel> tags;
    private boolean linear;

    private List<LinkModel> links;
    private List<Operator> operators;
    private List<Property> properties;
}
