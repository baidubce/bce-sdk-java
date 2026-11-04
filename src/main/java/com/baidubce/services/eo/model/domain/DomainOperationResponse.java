package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoResponse;

/**
 * The response of a domain operation (update config / start / stop / delete).
 */
public class DomainOperationResponse extends EoResponse {
    /**
     * The operation result. "OK" for all success, "PartialFailed" for partial success.
     */
    private String status;

    /**
     * The number of domains to operate.
     */
    private Integer total;

    /**
     * The number of successfully operated domains.
     */
    private Integer success;

    /**
     * The error details when status is not "OK".
     */
    private String errMsg;

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return success
     */
    public Integer getSuccess() {
        return success;
    }

    /**
     * @param success
     */
    public void setSuccess(Integer success) {
        this.success = success;
    }

    /**
     * @return errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @param errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
