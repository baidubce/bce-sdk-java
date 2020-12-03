package com.baidubce.services.vpn.model;

import com.baidubce.model.ListResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListVpnConnResponse extends ListResponse {

    private List<VpnConn> vpnConns;

}
