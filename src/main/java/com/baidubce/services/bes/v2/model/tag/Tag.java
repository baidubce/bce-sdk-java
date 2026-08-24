package com.baidubce.services.bes.v2.model.tag;

import lombok.Data;

/**
 * Tag key-value pair.
 */
@Data
public class Tag {
    private String tagKey;
    private String tagValue;
}
