package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Response of querying the recoverable time range (full + incremental) of a MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbGetRecoverableTimeRangeResponse extends GenericMongodbResponse {

    private List<RecoverableTimeRange> recovableTimeRange = new ArrayList<RecoverableTimeRange>();

    public List<RecoverableTimeRange> getRecovableTimeRange() {
        return recovableTimeRange;
    }

    public void setRecovableTimeRange(List<RecoverableTimeRange> recovableTimeRange) {
        this.recovableTimeRange = recovableTimeRange;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RecoverableTimeRange {
        private String startTime;

        private String endTime;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
