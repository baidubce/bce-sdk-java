package com.baidubce.services.evs.model;

import com.baidubce.model.AbstractBceResponse;

import java.io.Serializable;
import java.util.Collection;

public class DeviceTsStorePageListResponse extends AbstractBceResponse implements Serializable {

    private static final long serialVersionUID = -8737069930166501336L;

    private Collection<DeviceTsStoreListResponse> data;

    private Integer pageNo;

    private Integer pageSize;

    private Long totalCount;

    public Collection<DeviceTsStoreListResponse> getData() {
        return data;
    }

    public void setData(Collection<DeviceTsStoreListResponse> data) {
        this.data = data;
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

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceTsStorePageListResponse that = (DeviceTsStorePageListResponse) o;

        if (data != null ? !data.equals(that.data) : that.data != null) {
            return false;
        }
        if (pageNo != null ? !pageNo.equals(that.pageNo) : that.pageNo != null) {
            return false;
        }
        if (pageSize != null ? !pageSize.equals(that.pageSize) : that.pageSize != null) {
            return false;
        }
        return totalCount != null ? totalCount.equals(that.totalCount) : that.totalCount == null;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (pageNo != null ? pageNo.hashCode() : 0);
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        result = 31 * result + (totalCount != null ? totalCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceTsStorePageListResponse{" +
                "data=" + data +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                "} " + super.toString();
    }
}