package com.baidubce.services.oos.model.response;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateListResponse extends AbstractBceResponse {
    private boolean success;
    private String msg;
    private Integer code;
    private TemplatePageModel result;
}
