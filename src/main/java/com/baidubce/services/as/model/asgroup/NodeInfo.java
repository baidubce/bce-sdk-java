package com.baidubce.services.as.model.asgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xucongyang on 2018/06/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
public class NodeInfo {
    private String spec = "";
    private int cpuCount;
    private int memoryCapacityInGB;
    private String sysDiskType;
    private int sysDiskInGB;
    // 本地盘
    private List<EphemeralDisk> ephemeralDisks;
    private int instanceType;
    private String gpuCard = "";
    private int gpuCount;
    // fpga卡的属性
    private String fpgaCard = "";
    // fpga卡的数量
    private int fpgaCount;
    private Boolean containsFpga = false;
    // 昆仑卡的属性
    private String kunlunCard = "";
    // 昆仑卡的数量
    private int kunlunCount;
    private String imageType;
    private String imageId;
    private String osType;
    private String osName;
    private String osVersion;
    private String osArch;
    private String securityGroupId;
    private String adminPass;
    // 只用于VO 返回模版详情用. 密码类型: "0"(随机); "1"(用户自定义)
    private String adminPassType;
    private String securityGroupName;
    private int totalCount;
    private String aspId = "";
    private String productType ;
    private int priorities;
    private String zoneSubnet;
    private String bidModel;
    private double bidPrice;

    @Data
    @ToString
    public static class EphemeralDisk {
        private String storageType;
        private int sizeInGB;
    }
}
