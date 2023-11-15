package com.baidubce.services.evs.model;

public class DeviceTsStoreListRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -3736470262643576858L;

    private Long begin;

    private Long end;

    private Integer pageNo;

    private Integer pageSize;

    private String type;

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceTsStoreListRequest that = (DeviceTsStoreListRequest) o;

        if (begin != null ? !begin.equals(that.begin) : that.begin != null) {
            return false;
        }
        if (end != null ? !end.equals(that.end) : that.end != null) {
            return false;
        }
        if (pageNo != null ? !pageNo.equals(that.pageNo) : that.pageNo != null) {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }
        return pageSize != null ? pageSize.equals(that.pageSize) : that.pageSize == null;
    }

    @Override
    public int hashCode() {
        int result = begin != null ? begin.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (pageNo != null ? pageNo.hashCode() : 0);
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceTsStoreListRequest{" +
                "begin=" + begin +
                ", end=" + end +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", type='" + type + '\'' +
                '}';
    }
}
