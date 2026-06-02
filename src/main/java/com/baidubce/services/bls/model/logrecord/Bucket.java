package com.baidubce.services.bls.model.logrecord;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Bucket {
    private long key;
    private long start;
    private long end;

    @JsonProperty("doc_count")
    private long docCount;
}
