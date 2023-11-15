package com.baidubce.services.bos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Specifies a CustomHeader, consisting of one headerName and one headerValue.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomHeader {
    private String headerName;
    private String headerValue;
}
