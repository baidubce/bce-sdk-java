package com.baidubce.services.bcm.model.event;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEventResponse extends AbstractBceResponse {
    private List<CloudEventData> content = new ArrayList<CloudEventData>();
    private int pageNumber;
    private int pageSize;
    private int pageElements;
    private boolean last;
    private boolean first;
    private int totalPages;
    private long totalElements;
}