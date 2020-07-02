package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class MediaDrag extends JsonObject {
    private List<String> fileSuffix;
    private String startArgName;
    private String endArgName;
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
