package com.baidubce.services.bls.model.logrecord;


import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryLogRecordResponse extends AbstractBceResponse {
    private String nextMarker;

    private DatasetScanInfo datasetScanInfo;

    private ResultSet resultSet;

}
