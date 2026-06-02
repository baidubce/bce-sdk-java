package com.baidubce.services.bls.model.logrecord;


import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PushLogRecordRequest extends AbstractBceRequest {
    private String project;

    private String logStoreName;

    private String logStreamName;

    private LogType type;

    private List<LogRecord> logRecords;

    private List<LogTag> logTags;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return null;
    }
}
