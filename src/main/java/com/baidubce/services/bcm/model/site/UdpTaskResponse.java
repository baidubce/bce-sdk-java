package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * create by pangyangyang on 2023/12/13
 */
@Data
public class UdpTaskResponse extends AbstractBceResponse {

    private int port;
    private int inputType;
    private int outputType;
    private String input;
    private String expectedOutput;

    private String userId;
    private String taskId;
    private String scope = "BCM_SITE";
    private String taskName;
    private String address;
    private String type;
    private Integer cycle;
    private String idc;
    private List<IDC> idcObjs;
    private int timeout;
    private String confResource;
    private Boolean advanceConfig;
    private String ipType = "ipv4";
}
