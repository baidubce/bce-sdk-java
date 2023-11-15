package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class MediaDrag extends JsonObject {

    /**
     * 百度智能云CDN支持MP4文件的伪流（pseudo-streaming）播放，
     * 通常这些文件拓展名为.mp4，.m4v，.m4a，因此这个fileSuffix值为文件拓展名集合，
     * 如： ["mp4", "m4v", "m4a"]，type为mp4，fileSuffix默认值为["mp4"]；type为flv，fileSuffix默认值为["flv"]
     * 可选
     */
    private List<String> fileSuffix;

    /**
     * start参数名称，默认为“start”，您可以自定义参数名称，但是要求不能和endArgName相同
     * 可选
     */
    private String startArgName;

    /**
     * end参数名称，默认为“end”，您可以自定义参数名称，但是要求不能和startArgName相同
     * 可选
     */
    private String endArgName;

    /**
     * mp4类型按秒进行拖拽，flv类型按字节进行拖拽。type为flv可选择的模式为“byteAV”或”byte”；type为mp4只能是"second"模式
     * 必选
     */
    private String dragMode;

    /**
     * @return fileSuffix
     */
    public List<String> getFileSuffix() {
        return fileSuffix;
    }

    /**
     * @param fileSuffix File extension
     */
    public void setFileSuffix(List<String> fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    /**
     * @param fileSuffix File extension list
     * @return this object
     */
    public MediaDrag withFileSuffix(List<String> fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }

    /**
     * @param suffix File extension
     * @return this object
     */
    public MediaDrag addFileSuffix(String suffix) {
        if (this.fileSuffix == null) {
            this.fileSuffix = new ArrayList<String>();
        }
        this.fileSuffix.add(suffix);
        return this;
    }

    /**
     * @return startArgName
     */
    public String getStartArgName() {
        return startArgName;
    }

    /**
     * @param startArgName Start parameter name
     */
    public void setStartArgName(String startArgName) {
        this.startArgName = startArgName;
    }

    /**
     * @param startArgName Start parameter name
     * @return this object
     */
    public MediaDrag withStartArgName(String startArgName) {
        this.startArgName = startArgName;
        return this;
    }

    /**
     * @return endArgName
     */
    public String getEndArgName() {
        return endArgName;
    }

    /**
     * @param endArgName End parameter name
     */
    public void setEndArgName(String endArgName) {
        this.endArgName = endArgName;
    }

    /**
     * @param endArgName End parameter name
     * @return this object
     */
    public MediaDrag withEndArgName(String endArgName) {
        this.endArgName = endArgName;
        return this;
    }

    /**
     * @return dragMode
     */
    public String getDragMode() {
        return dragMode;
    }

    /**
     * @param dragMode Drag method type
     */
    public void setDragMode(String dragMode) {
        this.dragMode = dragMode;
    }

    /**
     * @param dragMode Drag method type
     * @return this object
     */
    public MediaDrag withDragMode(String dragMode) {
        this.dragMode = dragMode;
        return this;
    }

}
