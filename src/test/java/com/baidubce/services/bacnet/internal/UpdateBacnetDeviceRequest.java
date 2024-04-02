package com.baidubce.services.bacnet.internal;

import com.baidubce.model.GenericAccountRequest;

import java.util.Date;

/**
 * Created by yuanyoujun on 2017/10/18.
 */
public class UpdateBacnetDeviceRequest extends GenericAccountRequest {
    private String name;
    private String description;
    private int vendorId;
    private String vendorName;
    private int revision;
    private int version;
    private String typesSupported;
    private String servicesSupported;
    private Date lastActiveTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTypesSupported() {
        return typesSupported;
    }

    public void setTypesSupported(String typesSupported) {
        this.typesSupported = typesSupported;
    }

    public String getServicesSupported() {
        return servicesSupported;
    }

    public void setServicesSupported(String servicesSupported) {
        this.servicesSupported = servicesSupported;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}
