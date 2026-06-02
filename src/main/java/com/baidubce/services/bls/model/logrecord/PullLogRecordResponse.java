package com.baidubce.services.bls.model.logrecord;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PullLogRecordResponse extends AbstractBceResponse {
    private String nextMarker;

    @JsonProperty("isTruncated")
    private boolean isTruncated;

    private List<LogRecord> result;
}
