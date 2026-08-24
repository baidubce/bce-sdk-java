package com.baidubce.services.bes.v2.model.config;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Response for synonym dict list, whose result is directly a list (not wrapped in an object).
 */
@Data
public class SynonymDictListResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private List<SynonymDict> result;

    @Data
    public static class SynonymDict {
        @JsonProperty("synonym_dict_name")
        private String synonymDictName;
    }
}
