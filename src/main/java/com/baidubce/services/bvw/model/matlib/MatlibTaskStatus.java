package com.baidubce.services.bvw.model.matlib;


public enum MatlibTaskStatus {
    /**
     * vidpress生产中
     */
    VIDPRESS_MAKING("pre_making"),

    /**
     * 二次编辑合成中
     */
    EDITOR_MAKING("post_making"),

    /**
     * 预处理中
     */
    PREPROCESS("pre_process"),

    /**
     * 手动编辑中 (待合成)
     */
    EDITING("editing"),

    /**
     * 待入库
     */
    SUCCESS("success"),

    /**
     * 已入库
     */
    SAVED("saved"),

    /**
     * 已失败
     */
    FAILED("failed");

    private String externalDescription;

    MatlibTaskStatus(String externalDescription) {
        this.externalDescription = externalDescription;
    }

    @Override
    public String toString() {
        return externalDescription;
    }
}
