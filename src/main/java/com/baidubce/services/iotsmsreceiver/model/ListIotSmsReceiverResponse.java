package com.baidubce.services.iotsmsreceiver.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/8/13.
 */
public class ListIotSmsReceiverResponse  extends AbstractBceResponse {
    private List<IotSmsReceiver> result;
    private int totalCount;
    private int pageNo;
    private int pageSize;

    public List<IotSmsReceiver> getResult() {
        return result;
    }

    public void setResult(List<IotSmsReceiver> result) {
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
