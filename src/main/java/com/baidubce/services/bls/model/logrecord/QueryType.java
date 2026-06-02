package com.baidubce.services.bls.model.logrecord;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QueryType {
    @JsonProperty("match")
    Match,
    @JsonProperty("sql")
    SQL,
    @JsonProperty("match_and_sql")
    MatchAndSQL;
}
