package com.baidubce.services.vodpro.model.adaptor.response;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created on 17/10/10
 *
 * @author liumin08
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVcrResponse extends AbstractBceResponse {
    private String code;
    private String msg;
    private HSVcrResult result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HSVcrResult getResult() {
        return result;
    }

    public void setResult(HSVcrResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "QueryVcrResponse{"
                + "code='" + code + '\''
                + ", msg='" + msg + '\''
                + ", result=" + result
                + '}';
    }
}
