package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for log search, without the success/status/result envelope.
 */
@Data
public class LogSearchResponse extends AbstractBceResponse {
    private String level;
    private String host;
    private String time;
    private String content;
}
