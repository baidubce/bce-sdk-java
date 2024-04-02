package com.baidubce.services.bcm.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListResponse<T> extends AbstractBceResponse {
    private List<T> result = new ArrayList<T>();
}