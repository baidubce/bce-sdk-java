package com.baidubce.services.bec.model.resource;

import lombok.Data;

import java.util.List;

/**
 * Resource Package.
 */
@Data
public class ResourcePackage {

    /**
     * Resource package name.
     */
    private String name;

    /**
     * Resource cpu.
     */
    private Integer cpu;

    /**
     * Resource memory.
     */
    private Integer memory;

    /**
     * Resource gpu.
     */
    private Integer gpu;

    /**
     * Resource code.
     */
    private String code;

    /**
     * Inter bandwidth.
     */
    private Integer interBandwidth;

    /**
     * Inner bandwidth.
     */
    private Float innerBandwidth;

    /**
     * Cpu model.
     */
    private String cpuModel;

    /**
     * Data disk list.
     */
    private List<Disk> dataDiskList;

    /**
     * System disk.
     */
    private Disk systemDisk;

    /**
     * Storage partition.
     */
    private StoragePartition storagePartition;

    /**
     * Network packet data.
     */
    private Integer networkPacketData;
}
