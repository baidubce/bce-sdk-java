package com.baidubce.services.vod.v2.model;

public class MediaWorkflowThumbnailNodeTaskInfo {

    private String nodeId;
    private String nodeName;
    private MediaThumbnailTaskInfo thumbnailTaskInfo;

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

    public MediaThumbnailTaskInfo getThumbnailTaskInfo() {
        return thumbnailTaskInfo;
    }

    public void setThumbnailTaskInfo(MediaThumbnailTaskInfo thumbnailTaskInfo) {
        this.thumbnailTaskInfo = thumbnailTaskInfo;
    }

}
