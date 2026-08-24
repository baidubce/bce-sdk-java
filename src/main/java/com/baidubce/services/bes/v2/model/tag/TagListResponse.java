package com.baidubce.services.bes.v2.model.tag;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for listing tags, whose result is directly a list.
 */
@Data
public class TagListResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private List<Tag> result;
}
