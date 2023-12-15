package com.baidubce.services.vpn.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class BatchCreateSslVpnUserResponse extends AbstractBceResponse {
    private List<String> sslVpnUserIds;
}
