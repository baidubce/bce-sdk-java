package com.baidubce.services.bes.v3.model.action;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListActionTypesResponse extends AbstractBceResponse {
    private List<ActionTypeItem> actionTypes;
}
