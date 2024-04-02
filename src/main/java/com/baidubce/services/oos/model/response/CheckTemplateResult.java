package com.baidubce.services.oos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckTemplateResult {
    private boolean isValid;
    private String reason;
}
