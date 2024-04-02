package com.baidubce.services.oos.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.oos.model.Operator;
import com.baidubce.services.oos.model.Property;
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
public class BaseTemplateRequest extends AbstractBceRequest {
    private String id;
    private String name;
    private String ref;
    private TemplateType type = TemplateType.INDIVIDUAL;
    private String description;
    private List<KVModel> tags;
    private boolean linear;

    private List<LinkModel> links;
    private List<Operator> operators;
    private List<Property> properties;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
