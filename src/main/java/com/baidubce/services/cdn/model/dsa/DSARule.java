package com.baidubce.services.cdn.model.dsa;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class DSARule extends JsonObject {
    private String type;
    private String value;

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type rule type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param type rule type
     * @return this object
     */
    public DSARule withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value config rule
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param value config rule
     * @return this object
     */
    public DSARule withValue(String value) {
        this.value = value;
        return this;
    }
}
