package com.baidubce.services.cdn.model.stat;

public class PackageDetail {

    /**
     * 总容量
     */
    private Long capacity;

    /**
     * 已用量
     */
    private Long usedCapacity;

    /**
     * 状态 参考入参
     */
    private String status;

    /**
     * 包类型 如流量包
     */
    private String packageType;

    /**
     * 生效时间，UTC时间
     */
    private String activeTime;

    /**
     * 到期时间，UTC时间
     */
    private String expireTime;

    public PackageDetail() {
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(Long usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
