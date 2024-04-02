package com.baidubce.services.bcm.model.custom;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListCustomConfigResponse extends AbstractBceResponse {
    private String orderBy = "";
    private String order = "";
    private int pageNo = 1;
    private int pageSize = 0;
    private int totalCount = 0;
    private List<CustomAlarmConfig> result = new ArrayList();
}
