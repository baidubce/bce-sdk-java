package com.baidubce.services.vod.v2.model;

public class MediaWorkflowAnalysisNodeTaskInfo {

    private String nodeId;
    private String nodeName;
    private MediaAnalysisTaskInfo analysisTaskInfo;

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

    public MediaAnalysisTaskInfo getAnalysisTaskInfo() {
        return analysisTaskInfo;
    }

    public void setAnalysisTaskInfo(MediaAnalysisTaskInfo analysisTaskInfo) {
        this.analysisTaskInfo = analysisTaskInfo;
    }

}
