package com.baidubce.services.bcc.model.instance;

import lombok.Data;

@Data
public class InstanceRdmaTopoUO {

    /**
     * RDMA网卡Ip
     */
    private String rdmaIp;

    /**
     * RDMA网卡上联交换机名
     */
    private String switchName;

    /**
     * RDMA网卡上联交换机网口Id
     */
    private String switchPort;

    /**
     * RDMA网卡Mac地址
     */
    private String rdmaMac;

    /**
     * RDMA网卡网关Ip
     */
    private String rdmaGateway;

}
