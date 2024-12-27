package com.baidubce.services.vod.v2.model;

public class MediaWorkflowBlackBorderDetectNodeTaskInfo {

    private String nodeId;
    private String nodeName;
    private MediaBlackBorderDetectTaskInfo blackBorderDetectTaskInfo;

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

    public MediaBlackBorderDetectTaskInfo getBlackBorderDetectTaskInfo() {
        return blackBorderDetectTaskInfo;
    }

    public void setBlackBorderDetectTaskInfo(MediaBlackBorderDetectTaskInfo blackBorderDetectTaskInfo) {
        this.blackBorderDetectTaskInfo = blackBorderDetectTaskInfo;
    }

}
