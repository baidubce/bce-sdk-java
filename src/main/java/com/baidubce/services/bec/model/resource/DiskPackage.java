package com.baidubce.services.bec.model.resource;

import lombok.Data;

/**
 * Bec passThrough disk packages.
 */
@Data
public class DiskPackage {

    /**
     * Disk type.
     */
    private String type;

    /**
     * Disk name.
     */
    private String name;

    /**
     * Disk code.
     */
    private String code;

    /**
     * Disk size.
     */
    private int sizeInGb;

}
