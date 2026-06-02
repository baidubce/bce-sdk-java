package com.baidubce.services.bls.model.logrecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private long executionTimeInMs;
    private long scanCount;
    private long scanBytes;
    private Histogram histogram;
}

