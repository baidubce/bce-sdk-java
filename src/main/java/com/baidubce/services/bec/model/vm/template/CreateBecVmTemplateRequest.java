package com.baidubce.services.bec.model.vm.template;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.vm.DnsConfig;
import com.baidubce.services.bec.model.vm.NetworkConfig.Networks;
import com.baidubce.services.bec.model.vm.SystemVolumeConfig;
import com.baidubce.services.bec.model.vm.VolumeConfig;
import lombok.Data;

import java.util.List;

/**
 * The request for creating a newly vm template.
 */
@Data
public class CreateBecVmTemplateRequest extends AbstractBceRequest {

    /**
     * The name of the virtual machine template.
     */
    private String templateName;

    /**
     * Policy
     */
    private String policy;

    /**
     * Specifications.
     */
    private String spec;

    /**
     * The number of CPUs of the virtual machine instance.
     */
    private Integer cpu;

    /**
     * The memory of the virtual machine instance.
     */
    private Integer memory;

    /**
     * Image id.
     */
    private String imageId;

    /**
     * Image type（bcc,bec）
     * The subtypes becCommon, becCustom, and becGpu are unified as the request value bec,
     * and the background will distinguish them according to imageId.
     */
    private String imageType;

    /**
     * Whether to disable the intranet，default false.
     */
    private Boolean disableIntranet;

    /**
     * Whether public network is required.
     */
    private Boolean needPublicIp;

    /**
     * Whether public IPv6 is required, needPublicIp needs to be true.
     */
    private Boolean needIpv6PublicIp;

    /**
     * The bandwidth of the BEC virtual template.
     */
    private Integer bandwidth;

    /**
     * GPU configuration information of virtual machine.
     */
    private GpuRequest gpu;

    @Data
    public static class GpuRequest {
        private String type;

        private Integer num;
    }

    /**
     * Network information list.
     */
    private List<Networks> networksList;

    /**
     * DNS configuration.
     */
    private DnsConfig dnsConfig;

    /**
     * Data disk configuration list.
     */
    private List<VolumeConfig> dataVolumeList;

    /**
     * System disk configuration information.
     */
    private SystemVolumeConfig systemVolume;

    /**
     * Security group list.
     */
    private List<String> securityGroupIds;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CreateBecVmTemplateRequest with credentials.
     */
    public CreateBecVmTemplateRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
