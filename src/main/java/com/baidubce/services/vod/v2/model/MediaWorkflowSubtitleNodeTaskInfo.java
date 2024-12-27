package com.baidubce.services.vod.v2.model;

public class MediaWorkflowSubtitleNodeTaskInfo {

    private String nodeId;
    private String nodeName;
    private MediaSubtitleTaskInfo subtitleTaskInfo;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public MediaSubtitleTaskInfo getSubtitleTaskInfo() {
        return subtitleTaskInfo;
    }

    public void setSubtitleTaskInfo(MediaSubtitleTaskInfo subtitleTaskInfo) {
        this.subtitleTaskInfo = subtitleTaskInfo;
    }

}
