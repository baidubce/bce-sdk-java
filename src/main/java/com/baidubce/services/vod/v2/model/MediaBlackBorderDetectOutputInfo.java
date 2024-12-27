package com.baidubce.services.vod.v2.model;

public class MediaBlackBorderDetectOutputInfo {

    private MediaBlackBorderArea borderArea;
    private MediaBlackBorderVideoMetaInfo meta;

    public MediaBlackBorderArea getBorderArea() {
        return borderArea;
    }

    public void setBorderArea(MediaBlackBorderArea borderArea) {
        this.borderArea = borderArea;
    }

    public MediaBlackBorderVideoMetaInfo getMeta() {
        return meta;
    }

    public void setMeta(MediaBlackBorderVideoMetaInfo meta) {
        this.meta = meta;
    }

}
