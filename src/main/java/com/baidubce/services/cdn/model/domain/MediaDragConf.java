package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class MediaDragConf extends JsonObject {
    private MediaDrag mp4;
    private MediaDrag flv;

    /**
     * @return mp4
     */
    public MediaDrag getMp4() {
        return mp4;
    }

    /**
     * @param mp4 Drag settings for mp4 type pseudo flow
     */
    public void setMp4(MediaDrag mp4) {
        this.mp4 = mp4;
    }

    /**
     * @param mp4 Drag settings for mp4 type pseudo flow
     * @return this object
     */
    public MediaDragConf withMp4(MediaDrag mp4) {
        this.mp4 = mp4;
        return this;
    }

    /**
     * @return flv
     */
    public MediaDrag getFlv() {
        return flv;
    }

    /**
     * @param flv Drag settings for flv type pseudo flow
     */
    public void setFlv(MediaDrag flv) {
        this.flv = flv;
    }

    /**
     * @param flv Drag settings for flv type pseudo flow
     * @return this object
     */
    public MediaDragConf withFlv(MediaDrag flv) {
        this.flv = flv;
        return this;
    }
}
