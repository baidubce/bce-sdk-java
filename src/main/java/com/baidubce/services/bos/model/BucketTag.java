package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BucketTag {
    @JsonAlias({"tag_key"})
    private String tagKey;
    @JsonAlias({"tag_value"})
    private String tagValue;
}
