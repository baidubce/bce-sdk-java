package com.baidubce.services.bcc.model;

import lombok.Data;

@Data
public class Volume {
    private Boolean isSystemVolume;
    private Integer diskSizeInGB;
    private String volumeId;
}
