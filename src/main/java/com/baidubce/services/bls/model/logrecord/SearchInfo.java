package com.baidubce.services.bls.model.logrecord;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchInfo {
    private QueryType queryType;

    private long took;

    private long hits;

    private boolean isTruncated;

    private int pageNo;

    private int totalPage;

}
