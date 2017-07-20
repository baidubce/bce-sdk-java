package com.baidubce.services.iotalarm.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
public class CommonResponse extends AbstractBceResponse {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
