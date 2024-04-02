package com.baidubce.services.bcm.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MapListResponse extends AbstractBceResponse {
    Map<String, List<String>> result = new HashMap<String, List<String>>();
}