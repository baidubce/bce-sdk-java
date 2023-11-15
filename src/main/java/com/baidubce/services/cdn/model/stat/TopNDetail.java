package com.baidubce.services.cdn.model.stat;

public class TopNDetail {
    private String name;
    private Long pv;
    private Long flow;

    /**
     * 文件大小 单位byte
     */
    private Long filesize;

    /**
     * 文件类型 url资源后缀
     */
    private String filetype;

    public TopNDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getFlow() {
        return flow;
    }

    public void setFlow(Long flow) {
        this.flow = flow;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
}
