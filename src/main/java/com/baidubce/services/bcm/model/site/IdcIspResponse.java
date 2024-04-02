package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * create by pangyangyang on 2021/02/22
 */
@Data
public class IdcIspResponse extends AbstractBceResponse {


    private List<IDC> idcs;
    private Set<IDC> isps;
}
