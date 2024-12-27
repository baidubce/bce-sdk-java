package com.baidubce.services.vod.v2.model;

public class MediaWorkflowTranscodeNodeTaskInfo {

    private String nodeId;
    private String nodeName;
    private MediaTranscodeTaskInfo transcodeTaskInfo;

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

    public MediaTranscodeTaskInfo getTranscodeTaskInfo() {
        return transcodeTaskInfo;
    }

    public void setTranscodeTaskInfo(MediaTranscodeTaskInfo transcodeTaskInfo) {
        this.transcodeTaskInfo = transcodeTaskInfo;
    }

}
