package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for listing all optional inspection items.
 */
@Data
public class InspectItemListResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private List<Item> items;
    }

    @Data
    public static class Item {
        private String id;
        private String type;
    }
}
