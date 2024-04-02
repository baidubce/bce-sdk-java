package com.baidubce.services.oos.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateListRequest extends AbstractBceRequest {
    private boolean ascending;
    private String sort = "createTime";
    private int pageNo;
    private int pageSize;
    private String name;
    private String type;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
