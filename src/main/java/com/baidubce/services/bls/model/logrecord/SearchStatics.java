package com.baidubce.services.bls.model.logrecord;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class SearchStatics {
    private long interval;

    private String startTime;

    private String endTime;

    private List<Bucket> histogram;

}
