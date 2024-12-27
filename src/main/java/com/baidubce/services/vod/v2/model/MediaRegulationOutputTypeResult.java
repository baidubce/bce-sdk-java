package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaRegulationOutputTypeResult {

    private String type;
    private List<MediaRegulationOutputTypeResultItem> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MediaRegulationOutputTypeResultItem> getItems() {
        return items;
    }

    public void setItems(List<MediaRegulationOutputTypeResultItem> items) {
        this.items = items;
    }

}
