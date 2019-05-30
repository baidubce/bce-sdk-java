package com.baidubce.services.iothisk.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * Represent the request for batch create client cert.
 */
public class BatchCreateClientCertRequest extends IotPkiManageRequest {

    /**
     * Cert group ID of the certs which will be created
     */
    private String groupId;

    /**
     * Device IDs of the certs which will be created
     */
    private List<String> deviceIds;

    /**
     * Validity period of sub certs in days.
     */
    private int duration;

    /**
     * Csr zip of the certs which will be created
     */
    private String csrs;

    /**
     * Create a batch create client cert request.
     * Recommend to use this method to create batch create request.
     *
     * @param groupId Cert group ID of the certs which will be created.
     * @param duration Validity period of sub certs in days.
     * @param deviceIdAndCsr A map which maps device ID and csr.
     * @return Batch create client cert request.
     * @throws Exception If input arguments are illegal.
     */
    public static BatchCreateClientCertRequest create(String groupId, int duration, Map<String, String> deviceIdAndCsr)
            throws Exception {
        checkNotNull(groupId);
        checkNotNull(deviceIdAndCsr);

        BatchCreateClientCertRequest request = new BatchCreateClientCertRequest()
                .withGroupId(groupId)
                .withDuration(duration);

        List<String> deviceIds = new ArrayList<String>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        for (Map.Entry<String, String> deviceIdAndCsrEntry : deviceIdAndCsr.entrySet()) {
            deviceIds.add(deviceIdAndCsrEntry.getKey());
            byte[] certContent = deviceIdAndCsrEntry.getValue().getBytes();
            zipOutputStream.putNextEntry(new ZipEntry(deviceIdAndCsrEntry.getKey()));
            zipOutputStream.write(certContent, 0, certContent.length);
            zipOutputStream.closeEntry();

        }
        zipOutputStream.finish();
        zipOutputStream.close();

        request.setCsrs(Base64.encodeBase64String(byteArrayOutputStream.toByteArray()));
        request.setDeviceIds(deviceIds);
        return request;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public BatchCreateClientCertRequest withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public BatchCreateClientCertRequest withDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BatchCreateClientCertRequest withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getCsrs() {
        return csrs;
    }

    public void setCsrs(String csrs) {
        this.csrs = csrs;
    }

    public BatchCreateClientCertRequest withCsrs(String csrs) {
        this.csrs = csrs;
        return this;
    }
}
