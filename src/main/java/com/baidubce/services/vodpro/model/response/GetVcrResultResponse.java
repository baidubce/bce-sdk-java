package com.baidubce.services.vodpro.model.response;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created on 17/8/24
 *
 * @author liumin08
 */
public class GetVcrResultResponse extends AbstractBceResponse {

    private String mediaId;
    private String source;
    private String status;
    private String preset;
    private String label;
    private String createTime;
    private String finishTime;
    private List<Result> results;
    private Error error;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "GetVcrResultResponse{"
                + "mediaId='" + mediaId + '\''
                + ", source='" + source + '\''
                + ", status='" + status + '\''
                + ", preset='" + preset + '\''
                + ", label='" + label + '\''
                + ", createTime='" + createTime + '\''
                + ", finishTime='" + finishTime + '\''
                + ", results=" + results
                + ", error=" + error
                + '}';
    }



    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String type;
        private List<Item> items;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "Result{"
                    + "type='" + type + '\''
                    + ", items=" + items
                    + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String target;
        private String timeInSeconds;
        private String confidence;
        private String label;
        private String startTimeInSeconds;
        private String endTimeInSeconds;
        private String extra;

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getTimeInSeconds() {
            return timeInSeconds;
        }

        public void setTimeInSeconds(String timeInSeconds) {
            this.timeInSeconds = timeInSeconds;
        }

        public String getConfidence() {
            return confidence;
        }

        public void setConfidence(String confidence) {
            this.confidence = confidence;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getStartTimeInSeconds() {
            return startTimeInSeconds;
        }

        public void setStartTimeInSeconds(String startTimeInSeconds) {
            this.startTimeInSeconds = startTimeInSeconds;
        }

        public String getEndTimeInSeconds() {
            return endTimeInSeconds;
        }

        public void setEndTimeInSeconds(String endTimeInSeconds) {
            this.endTimeInSeconds = endTimeInSeconds;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        @Override
        public String toString() {
            return "Item{"
                    + "target='" + target + '\''
                    + ", timeInSeconds='" + timeInSeconds + '\''
                    + ", confidence='" + confidence + '\''
                    + ", label='" + label + '\''
                    + ", startTimeInSeconds='" + startTimeInSeconds + '\''
                    + ", endTimeInSeconds='" + endTimeInSeconds + '\''
                    + ", extra='" + extra + '\''
                    + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Error {
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Error{"
                    + "code='" + code + '\''
                    + ", message='" + message + '\''
                    + '}';
        }
    }
}
