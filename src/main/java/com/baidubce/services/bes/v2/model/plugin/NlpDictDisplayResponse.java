package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for viewing the nlp dict.
 */
@Data
public class NlpDictDisplayResponse extends AbstractBceResponse {
    private Boolean success;
    private String status;
    private Result result;

    @Data
    public static class Result {
        private String operationType;
        private String separator;
        private String fileName;
        private String dictContent;
    }
}
