package com.baidubce.services.ruleengine.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2016/10/9.
 */
public class RuleTestRequest extends GenericAccountRequest {
    private String select;
    private String where;
    private String message;

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
