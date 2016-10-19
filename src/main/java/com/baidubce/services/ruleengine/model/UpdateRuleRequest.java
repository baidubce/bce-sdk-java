package com.baidubce.services.ruleengine.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2016/10/9.
 */
public class UpdateRuleRequest extends GenericAccountRequest {
    private String description;
    private String select;
    private String from;
    private String where;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
