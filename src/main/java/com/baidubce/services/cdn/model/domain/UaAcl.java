package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class UaAcl extends JsonObject {
    /**
     * 可选  ua黑名单列表，单个ua长度限制为 1~200 字符
     */
    private List<String> blackList;
    /**
     * 可选 ua白名单列表，单个ua长度限制为 1~200 字符
     */
    private List<String> whiteList;

    public UaAcl() {
    }

    public UaAcl addBlackList(String entry) {
        if (blackList == null) {
            blackList = new ArrayList<String>();
        }
        blackList.add(entry);
        return this;
    }

    public UaAcl addWhiteList(String entry) {
        if (whiteList == null) {
            whiteList = new ArrayList<String>();
        }
        whiteList.add(entry);
        return this;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}
