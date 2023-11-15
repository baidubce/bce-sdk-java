package com.baidubce.services.bec.model.resource;

import lombok.Data;

/**
 * Disk.
 */
@Data
public class Disk {

    /**
     * Volume type.
     */
    private String volumeType;

    /**
     * Size.
     */
    private Integer sizeInGb;
}
