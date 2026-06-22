package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Response of listing enterprise security groups.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsEnterpriseSecurityGroupResponse extends AbstractBceResponse {

    private List<EnterpriseSecurityGroup> result;

    private int pageNo;

    private int pageSize;

    private int totalCount;

    private String order;

    private String orderBy;

    public List<EnterpriseSecurityGroup> getResult() {
        return result;
    }

    public void setResult(List<EnterpriseSecurityGroup> result) {
        this.result = result;
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public static class EnterpriseSecurityGroup {

        private String esgId;

        private String esgUuid;

        private String name;

        private String desc;

        private int associateNum;

        private String createdTime;

        private String updatedTime;

        public String getEsgId() {
            return esgId;
        }

        public void setEsgId(String esgId) {
            this.esgId = esgId;
        }

        public String getEsgUuid() {
            return esgUuid;
        }

        public void setEsgUuid(String esgUuid) {
            this.esgUuid = esgUuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getAssociateNum() {
            return associateNum;
        }

        public void setAssociateNum(int associateNum) {
            this.associateNum = associateNum;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }
    }
}
