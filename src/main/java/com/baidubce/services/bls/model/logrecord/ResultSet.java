package com.baidubce.services.bls.model.logrecord;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultSet {
    private QueryType queryType;

    private List<String> columns;

    private List<String> columnTypes;

    private List<List<Object>> rows;

    private List<Map<String, String>> tags;
}
