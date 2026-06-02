package com.baidubce.services.bls.model.logrecord;


import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryLogHistogramResponse extends AbstractBceResponse {
    private SearchInfo searchInfo;
    private SearchStatics searchStatics;
}
