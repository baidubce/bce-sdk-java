package com.baidubce.services.bcm.model.event;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformEventResponse extends AbstractBceResponse {
    private List<PlatformEventData> content = new ArrayList<PlatformEventData>();
    private int pageNumber;
    private int pageSize;
    private int pageElements;
    private boolean last;
    private boolean first;
    private int totalPages;
    private long totalElements;
}
