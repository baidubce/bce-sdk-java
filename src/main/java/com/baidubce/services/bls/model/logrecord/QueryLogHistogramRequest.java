package com.baidubce.services.bls.model.logrecord;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryLogHistogramRequest extends AbstractBceRequest {
    private String project;

    private String logStoreName;

    private String logStreamName;

    private String query;

    private String startDateTime;

    private String endDateTime;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return null;
    }
}
