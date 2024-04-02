package com.baidubce.services.as.model.asgroup;

import lombok.Data;

@Data
public class BccNameConfig {
    private String bccName = "";
    private String bccHostname = "";
    private Boolean autoSeqSuffix = false;
    private Boolean openHostnameDomain = false;
}
