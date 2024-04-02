package com.baidubce.services.bcm.model.siteonce;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteOnceRequest extends AbstractBceRequest {

    /**用户id*/
    private String userId;
    /**探测地址*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;
    /**探测点ip类型*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ipType;
    /**重新探测创建的任务属于同一个group*/
    private String groupId;
    /**探测点*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idc;
    /**任务协议类型*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SiteOnceProtocol protocolType;
    /**任务类型*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String taskType;
    /**超时时间*/
    private int timeout;

    /**探测任务普通配置*/
    private SiteOnceConfig onceConfig;
    /**是否开启高级配置*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean advancedFlag;
    /**高级配置内容*/
    private SiteAdvancedConfig advancedConfig = new SiteAdvancedConfig();
    @Override
    public SiteOnceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
