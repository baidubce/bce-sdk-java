package com.baidubce.services.bec.model.vo;

import com.baidubce.services.bec.model.network.SecurityGroupVo;
import com.baidubce.services.bec.model.vm.DnsConfig;
import com.baidubce.services.bec.model.vm.NetworkConfig;
import com.baidubce.services.bec.model.vm.SystemVolumeConfig;
import com.baidubce.services.bec.model.vm.VolumeConfig;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateRequest.GpuRequest;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Vm template
 */
@Data
public class VmTemplateVo {

    /**
     * The name of the virtual machine template.
     */
    private String templateName;

    /**
     * The id of the virtual machine template.
     */
    private String templateId;

    /**
     * Policy
     */
    private String policy;

    /**
     * The number of CPUs of the virtual machine instance.
     */
    private int cpu;

    /**
     * The memory of the virtual machine instance.
     */
    private int memory;

    /**
     * Specifications.
     */
    private String spec;

    /**
     * Specifications.
     */
    private String specType;

    /**
     * Cpu model.
     */
    private String cpuModel;

    /**
     * Cpu frequency.
     */
    private String cpuGHz;

    /**
     * GPU configuration information of virtual machine.
     */
    private GpuRequest gpu;

    /**
     * Name of os, eg:CentOS
     */
    private String osName;

    /**
     * Version of os, eg:7.1
     */
    private String osVersion;

    /**
     * Image type of os, eg:becCommon
     */
    private String osImageType;

    /**
     * Image name of os, eg:centos7.1
     */
    private String osImageName;

    /**
     * System disk configuration information.
     */
    private SystemVolumeConfig systemVolume;

    /**
     * Data disk configuration list.
     */
    private List<VolumeConfig> dataVolumeList;

    /**
     * Intra ip type, eg:ipv4
     */
    private String intraIpType;

    /**
     * Extra ip type, eg:ipv4
     */
    private String extraIpType;

    /**
     * Network configuration information list.
     */
    private List<NetworkConfig> networkConfigList;

    /**
     * DNS configuration.
     */
    private DnsConfig dnsConfig;

    /**
     * The bandwidth of the BEC virtual template.
     */
    private int bandwidth;

    /**
     * The id of image.
     */
    private String imageId;

    /**
     * The type of image, eg:bec
     */
    private String imageType;

    /**
     * Whether to disable the intranet.
     */
    private Boolean disableIntranet;

    /**
     * Is the template available.
     */
    private Boolean templateAvailable;

    /**
     * Whether public network is required.
     */
    private Boolean needPublicIp;

    /**
     * Whether IPV6 is required
     */
    private Boolean needIpv6PublicIp;

    /**
     * SecurityGroup id list.
     */
    private List<String> securityGroupIds;

    /**
     * SecurityGroups.
     */
    private List<SecurityGroupVo> securityGroups;

    /**
     * FlavorTypePrepay
     */
    private String flavorTypePrepay;

    /**
     * FlavorTypePostpay
     */
    private String flavorTypePostpay;

    /**
     * FlavorTypePostpayCpt1
     */
    private String flavorTypePostpayCpt1;

    /**
     * CreateTime
     */
    private Timestamp createTime;

    /**
     * UpdateTime
     */
    private Timestamp updateTime;
}
