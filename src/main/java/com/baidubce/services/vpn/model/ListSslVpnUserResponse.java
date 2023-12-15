package com.baidubce.services.vpn.model;

import com.baidubce.model.ListResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListSslVpnUserResponse extends ListResponse {
    private List<SslVpnUserInfo> sslVpnUsers;

    @Override
    public String toString() {
        return "ListSslVpnUserResponse{" +
                "marker=" + getMarker() + "," +
                "isTruncated=" + getIsTruncated() + "," +
                "nextMarker=" + getNextMarker() + "," +
                "maxKeys=" + getMaxKeys() + "," +
                "sslVpnUsers=" + sslVpnUsers +
                '}';
    }
}
