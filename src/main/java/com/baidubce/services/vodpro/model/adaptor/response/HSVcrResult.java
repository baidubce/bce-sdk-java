package com.baidubce.services.vodpro.model.adaptor.response;

import java.util.List;

/**
 * Created on 17/10/18
 *
 * @author liumin08
 */
public class HSVcrResult {
    private String status;
    private String dataId;
    private String level;
    private List<Evidence> evidences;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(List<Evidence> evidences) {
        this.evidences = evidences;
    }

    public Integer getLevelCode() {
        if (this.level.equals(Constants.RESULTLABEL_REJECT)) {
            return Constants.RESULTLABEL_REJECT_CODE;
        }
        if (this.level.equals(Constants.RESULTLABEL_NORMAL)) {
            return Constants.RESULTLABEL_NORMAL_CODE;
        }
        if (this.level.equals(Constants.RESULTLABEL_REVIEW)) {
            return Constants.RESULTLABEL_REVIEW_CODE;
        }
        return Constants.RESULTLABEL_INVALID_CODE;
    }

    public Integer getStatusCode() {
        if (this.status.equals(Constants.VIDEOSTATUS_SUCCESS)) {
            return Constants.VIDEOSTATUS_SUCCESS_CODE;
        }
        if (this.status.equals(Constants.VIDEOSTATUS_DUPLICATED_REQUEST)) {
            return Constants.VIDEOSTATUS_DUPLICATED_REQUEST_CODE;
        }
        if (this.status.equals(Constants.VIDEOSTATUS_INVALID_ARGUMENT)) {
            return Constants.VIDEOSTATUS_INVALID_ARGUMENT_CODE;
        }
        if (this.status.equals(Constants.VIDEOSTATUS_PARSE_ERROR)) {
            return Constants.VIDEOSTATUS_PARSE_ERROR_CODE;
        }
        if (this.status.equals(Constants.VIDEOSTATUS_TYPE_ERROR)) {
            return Constants.VIDEOSTATUS_TYPE_ERROR_CODE;
        }
        return Constants.VIDEOSTATUS_UNKOWN_STATUS_CODE;
    }

    @Override
    public String toString() {
        return "HSVcrResult{"
                + "status=" + status
                + ", dataId='" + dataId + '\''
                + ", level=" + level
                + ", evidences=" + evidences
                + '}';
    }
}
