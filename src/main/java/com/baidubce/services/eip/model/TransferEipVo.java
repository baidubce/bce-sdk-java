
package com.baidubce.services.eip.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferEipVo {
    /**
     * 转移ID
     */
    private String transferId;

    /**
     * 转移资源类型
     */
    private String transferType;

    /**
     * 发起方
     */
    private String userId;

    /**
     * 接收方
     */
    private String toUserId;

    /**
     * 资源ID
     */
    private String instanceId;

    /**
     * 资源名称
     */
    private String instanceName;

    /**
     * EIP地址
     */
    private String instanceIp;

    /**
     * 线路类型
     */
    private String instanceType;

    /**
     * 实例带宽
     */
    private Integer instanceBandwidth;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public TransferEipVo setTransferId(String transferId) {
        this.transferId = transferId;
        return this;
    }

    public String getTransferId() {
        return this.transferId;
    }

    public TransferEipVo setTransferType(String transferType) {
        this.transferType = transferType;
        return this;
    }

    public String getTransferType() {
        return this.transferType;
    }

    public TransferEipVo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public TransferEipVo setToUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public String getToUserId() {
        return this.toUserId;
    }

    public TransferEipVo setInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public TransferEipVo setInstanceName(String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    public String getInstanceName() {
        return this.instanceName;
    }

    public TransferEipVo setInstanceIp(String instanceIp) {
        this.instanceIp = instanceIp;
        return this;
    }

    public String getInstanceIp() {
        return this.instanceIp;
    }

    public TransferEipVo setInstanceType(String instanceType) {
        this.instanceType = instanceType;
        return this;
    }

    public String getInstanceType() {
        return this.instanceType;
    }

    public TransferEipVo setInstanceBandwidth(Integer instanceBandwidth) {
        this.instanceBandwidth = instanceBandwidth;
        return this;
    }

    public Integer getInstanceBandwidth() {
        return this.instanceBandwidth;
    }

    public TransferEipVo setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public TransferEipVo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public TransferEipVo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    @Override
    public String toString() {
        return "TransferEipVo{"
                + "transferId=" + transferId + "\n"
                + "transferType=" + transferType + "\n"
                + "userId=" + userId + "\n"
                + "toUserId=" + toUserId + "\n"
                + "instanceId=" + instanceId + "\n"
                + "instanceName=" + instanceName + "\n"
                + "instanceIp=" + instanceIp + "\n"
                + "instanceType=" + instanceType + "\n"
                + "instanceBandwidth=" + instanceBandwidth + "\n"
                + "status=" + status + "\n"
                + "createTime=" + createTime + "\n"
                + "updateTime=" + updateTime + "\n"
                + "}";
    }




}