package com.baidubce.services.bcc.model.volume;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class VolumePriceResponse extends AbstractBceResponse {
    private List<Price> price;

    @Data
    public static class Price {
        private String storageType;
        private Integer cdsSizeInGB;
        private double price;
        private String unit;
    }
}
