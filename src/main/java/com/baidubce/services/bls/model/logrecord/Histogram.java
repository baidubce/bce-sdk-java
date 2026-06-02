package com.baidubce.services.bls.model.logrecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Histogram {
    private long interval;

    private String startTime;

    private String endTime;

    private List<Long> counts;
}
