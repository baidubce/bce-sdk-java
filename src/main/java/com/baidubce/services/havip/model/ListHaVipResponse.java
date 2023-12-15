/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip.model;

import java.util.List;

import com.baidubce.model.ListResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListHaVipResponse extends ListResponse {
    private List<HaVip> haVips;

    @Override
    public String toString() {
        return "ListHaVipResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "haVips=" + haVips +
                '}';
    }
}
