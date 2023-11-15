package com.baidubce.services.cdn.model.domain;

import java.util.ArrayList;
import java.util.List;

public class RetryOrigin {
    private List<Integer> codes;

    public RetryOrigin(List<Integer> codes) {
        this.codes = codes;
    }

    public RetryOrigin() {
    }

    public List getCodes() {
        return codes;
    }

    public void setCodes(List codes) {
        this.codes = codes;
    }

    public RetryOrigin addCode(Integer code) {
        if (codes == null) {
            codes = new ArrayList<Integer>();
        }
        codes.add(code);
        return this;
    }

}
