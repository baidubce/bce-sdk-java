package com.baidubce.services.tsdb.model;

/**
 * Task Error.
 *
 * @author zhangweiliang
 */
public class TaskError {

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
}
