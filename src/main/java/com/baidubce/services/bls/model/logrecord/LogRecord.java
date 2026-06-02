package com.baidubce.services.bls.model.logrecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogRecord {
    private long timestamp;

    private String message;
}
