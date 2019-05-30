package com.baidubce.services.tsdb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Delete task result.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteTaskResult extends TaskResult {

    private long deletedDataPointsCount;

    private long deletedStoreBytes;

    private List<Failure> failures = Lists.newArrayList();

    public long getDeletedDataPointsCount() {
        return deletedDataPointsCount;
    }

    public void setDeletedDataPointsCount(long deletedDataPointsCount) {
        this.deletedDataPointsCount = deletedDataPointsCount;
    }

    public long getDeletedStoreBytes() {
        return deletedStoreBytes;
    }

    public void setDeletedStoreBytes(long deletedStoreBytes) {
        this.deletedStoreBytes = deletedStoreBytes;
    }

    public List<Failure> getFailures() {
        return failures;
    }

    public void setFailures(List<Failure> failures) {
        this.failures = failures;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Failure {
        private String errorCode;
        private String errorMessage;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}

