package com.baidubce.services.vod.model;

/**
 * Created by baidu on 16/1/7.
 */
public class VodError {
    private String code = null;
    private String message = null;

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
        StringBuilder sb = new StringBuilder();
        sb.append("class VodError {\n");
        sb.append("    code: ").append(code).append("\n");
        sb.append("    message: ").append(message).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
