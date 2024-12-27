package com.baidubce.services.bec.model.vm;

import com.baidubce.services.bec.model.enums.DnsTypeEnum;
import lombok.Data;

@Data
public class DnsConfig {

    private DnsTypeEnum dnsType;

    private String dnsAddress;
}
