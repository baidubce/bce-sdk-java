/*
 * Copyright (c) 2018-2020 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model;

import lombok.ToString;
import java.util.Date;
import java.util.List;

/**
 * instance detail info model
 */
@ToString
public class InstanceModel {

    /**
     * The identified id of the instance
     */
    private String id;

    /**
     * The name of the instance.
     */
    private String name;

    /**
     * The hostname of the instance.
     */
    private String hostname;

    /**
     * The status of the instance,
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#InstanceStatus">BCE API doc</>
     */
    private String status;

    /**
     * The description of the instance.
     */
    private String desc;

    /**
     * The payment method of purchasing the instance,
     * see more detail in <a href = "https://bce.baidu.com/doc/BCC/API.html#Billing">BCE API doc</a>
     */
    private String paymentTiming;

    /**
     * The time when the instance was created
     */
    private Date createTime;

    /**
     * The time when the instance will be expired.
     * If it's Postpaid, it will not have expired time.
     */
    private Date expireTime;

    /**
     * The internal ip address for accessing.
     */
    private String internalIp;

    /**
     * The public ip address for accessing.
     */
    private String publicIp;

    /**
     * The available count of CPU within the instance
     */
    private int cpuCount;

    /**
     * The total size of memory in GB for the instance.
     */
    private int memoryCapacityInGB;

    /**
     * The total size of temporary volume in GB,exclude the system volume.
     */
    private int localDiskSizeInGB;

    /**
     * The id which was used to build the instance.
     */
    private String imageId;

    /**
     * The total bandwidth in Mbps for the instance.
     */
    private int networkCapacityInMbps;

    /**
     * the name of available zone
     */
    private String zoneName;

    /**
     * The list of bonded tags.
     */
    private List<TagModel> tags;

    /**
     * The policy of the instance placement
     * when "default",the instance has been placed by system auto, while "dedicatedHost", stands for been placed at dcc
     */
    private String placementPolicy;

    private String subnetId;

    private String vpcId;

    /**
     * The parameter to specified which kind of instance created.
     * see all of supported instance type in {@link com.baidubce.services.bcc.model.instance.InstanceType}
     */
    private String instanceType;

    /**
     * The gpu card info for creating gpu instance.
     */
    private String gpuCard;

    /**
     * The fpag card info for creating fpga instance.
     */
    private String fpgaCard;

    /**
     * The card count for creating GPU/FPGA instance
     */
    private int cardCount;

    /**
     * The id of the keypair
     */
    private String keypairId;

    /**
     * The name of the keypair
     */
    private String keypairName;

    /**
     * specified id of dedicated host when creating dedicated instance
     */
    private String dedicatedHostId;

    /**
     * whether the instacne is auto renew or not
     */
    private boolean autoRenew;

    /**
     * The address of the ipv6
     */
    private String ipv6;

    private Integer netEthQueueCount;

    /**
     * The instance of spec.
     */
    private String spec;
    private String roleName;
    private String createdFrom;
    private String isomerismCard;
    private String npuVideoMemory;
    private String imageName;
    private String imageType;
    private String hostId;
    private String switchId;
    private String rackId;
    private String deploysetId;
    private String osVersion;
    private String osArch;
    private String osName;
    private String hosteyeType;
    private List<DeploySetModel> deploysetList;
    private NicInfo nicInfo;
    private Integer deletionProtection;
    private List<Volume> volumes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getMemoryCapacityInGB() {
        return memoryCapacityInGB;
    }

    public void setMemoryCapacityInGB(int memoryCapacityInGB) {
        this.memoryCapacityInGB = memoryCapacityInGB;
    }

    public int getLocalDiskSizeInGB() {
        return localDiskSizeInGB;
    }

    public void setLocalDiskSizeInGB(int localDiskSizeInGB) {
        this.localDiskSizeInGB = localDiskSizeInGB;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getNetworkCapacityInMbps() {
        return networkCapacityInMbps;
    }

    public void setNetworkCapacityInMbps(int networkCapacityInMbps) {
        this.networkCapacityInMbps = networkCapacityInMbps;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    public String getPlacementPolicy() {
        return placementPolicy;
    }

    public void setPlacementPolicy(String placementPolicy) {
        this.placementPolicy = placementPolicy;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getGpuCard() {
        return gpuCard;
    }

    public void setGpuCard(String gpuCard) {
        this.gpuCard = gpuCard;
    }

    public String getFpgaCard() {
        return fpgaCard;
    }

    public void setFpgaCard(String fpgaCard) {
        this.fpgaCard = fpgaCard;
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public String getKeypairId() {
        return keypairId;
    }

    public void setKeypairId(String keypairId) {
        this.keypairId = keypairId;
    }

    public String getKeypairName() {
        return keypairName;
    }

    public void setKeypairName(String keypairName) {
        this.keypairName = keypairName;
    }

    public String getDedicatedHostId() {
        return dedicatedHostId;
    }

    public void setDedicatedHostId(String dedicatedHostId) {
        this.dedicatedHostId = dedicatedHostId;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getNetEthQueueCount() {
        return netEthQueueCount;
    }

    public void setNetEthQueueCount(Integer netEthQueueCount) {
        this.netEthQueueCount = netEthQueueCount;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    public String getIsomerismCard() {
        return isomerismCard;
    }

    public void setIsomerismCard(String isomerismCard) {
        this.isomerismCard = isomerismCard;
    }

    public String getNpuVideoMemory() {
        return npuVideoMemory;
    }

    public void setNpuVideoMemory(String npuVideoMemory) {
        this.npuVideoMemory = npuVideoMemory;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getSwitchId() {
        return switchId;
    }

    public void setSwitchId(String switchId) {
        this.switchId = switchId;
    }

    public String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }

    public String getDeploysetId() {
        return deploysetId;
    }

    public void setDeploysetId(String deploysetId) {
        this.deploysetId = deploysetId;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getHosteyeType() {
        return hosteyeType;
    }

    public void setHosteyeType(String hosteyeType) {
        this.hosteyeType = hosteyeType;
    }

    public List<DeploySetModel> getDeploysetList() {
        return deploysetList;
    }

    public void setDeploysetList(List<DeploySetModel> deploysetList) {
        this.deploysetList = deploysetList;
    }

    public NicInfo getNicInfo() {
        return nicInfo;
    }

    public void setNicInfo(NicInfo nicInfo) {
        this.nicInfo = nicInfo;
    }

    public Integer getDeletionProtection() {
        return deletionProtection;
    }

    public void setDeletionProtection(Integer deletionProtection) {
        this.deletionProtection = deletionProtection;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

}
