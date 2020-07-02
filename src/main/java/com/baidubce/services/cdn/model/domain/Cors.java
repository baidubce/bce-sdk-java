package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Cors extends JsonObject {
    private String allow;
    private List<String> originList;

    /**o
     * @return allow
     */
    public String getAllow() {
        return allow;
    }

    /**
     * @param allow whether cross-domain access is allowed
     */
    public void setAllow(String allow) {
        this.allow = allow;
    }

    /**
     * @return originList
     */
    public List<String> getOriginList() {
        return originList;
    }

    /**
     * @param originList List of domain names allowed across domains
     */
    public void setOriginList(List<String> originList) {
        this.originList = originList;
    }

    /**
     * @param allow whether cross-domain access is allowed
     * @return this object
     */
    public Cors withAllow(String allow) {
        this.allow = allow;
        return this;
    }

    /**
     * @param originList List of domain names allowed across domains
     * @return this object
     */
    public Cors withOriginList(List<String> originList) {
        setOriginList(originList);
        return this;
    }

    /**
     * @param origin domain's name
     * @return this object
     */
    public Cors addOriginList(String origin) {
        if (originList == null) {
            originList = new ArrayList<String>();
        }
        originList.add(origin);
        return this;
    }
}
