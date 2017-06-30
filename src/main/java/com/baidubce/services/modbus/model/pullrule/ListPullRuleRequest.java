package com.baidubce.services.modbus.model.pullrule;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListPullRuleRequest extends GenericAccountRequest {

    public String getParserObjectUuid() {
        return parserObjectUuid;
    }

    public void setParserObjectUuid(String parserObjectUuid) {
        this.parserObjectUuid = parserObjectUuid;
    }

    public Boolean isWithDevice() {
        return withDevice;
    }

    public void setWithDevice(Boolean withDevice) {
        this.withDevice = withDevice;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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

    private String parserObjectUuid; // list某个解析项目下的从站轮询设置

    private Boolean withDevice = false; // true则会返回对应device的信息

    private String order = "desc";

    private String orderBy = "createTime";

    private int pageNo = 1;

    private int pageSize = 50;

}
