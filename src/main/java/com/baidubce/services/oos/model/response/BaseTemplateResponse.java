package com.baidubce.services.oos.model.response;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.oos.model.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseTemplateResponse extends AbstractBceResponse {
    private boolean success;
    private String msg;
    private Integer code;
    private Template result;
}
