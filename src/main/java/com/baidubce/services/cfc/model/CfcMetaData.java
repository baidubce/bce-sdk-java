/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the metadata of Baidu CFC. This includes custom
 * user-supplied metadata, as well as the standard HTTP headers that Baidu CFC
 * sends and receives (ContentLength, ContentType, Server, etc.).
 */
public class CfcMetaData {

    /**
     * represent the Bce request id
     */
    @JsonProperty(value = "BceRequestId")
    private String BceRequestId;

    /**
     * The length of the request body.
     */
    @JsonProperty(value = "ContentLength")
    private long ContentLength = -1;

    /**
     * The MIME type of the body of the request.
     */
    @JsonProperty(value = "ContentType")
    private String ContentType;

    /**
     * The date of the Bce request.
     */
    @JsonProperty(value = "Date")
    private String Date;

    /**
     * The server provided by the Bce.
     */
    @JsonProperty(value = "Server")
    private String Server;

    /**
     * The log of the CFC invoke calls.
     */
    @JsonProperty(value = "BceLogResult")
    private String BceLogResult;

    /**
     * Get the Bce request Id
     * @return The Bce request Id
     */
    public String getBceRequestId() {
        return BceRequestId;
    }

    /**
     * Set the Bce request Id
     * @param bceRequestId The Bce request Id
     */
    public void setBceRequestId(String bceRequestId) {
        BceRequestId = bceRequestId;
    }

    /**
     * Get the length of the request body.
     * @return The length of the request body.
     */
    public long getContentLength() {
        return ContentLength;
    }

    /**
     * Set the length of the request body.
     * @param contentLength The length of the request body.
     */
    public void setContentLength(long contentLength) {
        ContentLength = contentLength;
    }

    /**
     * Get the MIME type of the body of the request.
     * @return The MIME type of the body of the request.
     */
    public String getContentType() {
        return ContentType;
    }

    /**
     * Set the MIME type of the body of the request.
     * @param contentType The MIME type of the body of the request.
     */
    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    /**
     * Get the date of the Bce request.
     * @return The date of the Bce request.
     */
    public String getDate() {
        return Date;
    }

    /**
     * Set the date of the Bce request.
     * @param date The date of the Bce request.
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     * Get the server provided by the Bce.
     * @return The server provided by the Bce.
     */
    public String getServer() {
        return Server;
    }

    /**
     * Set the server provided by the Bce.
     * @param server The server provided by the Bce.
     */
    public void setServer(String server) {
        Server = server;
    }

    /**
     * Get the log of the CFC invoke calls.
     * @return The log of the CFC invoke calls.
     */
    public String getBceLogResult() {
        return BceLogResult;
    }

    /**
     * Set the log of the CFC invoke calls.
     * @param bceLogResult The log of the CFC invoke calls.
     */
    public void setBceLogResult(String bceLogResult) {
        BceLogResult = bceLogResult;
    }

    public CfcMetaData() {

    }

    public CfcMetaData(CfcMetaData other) {
        this.setBceRequestId(other.getBceRequestId());
        this.setContentLength(other.getContentLength());
        this.setContentType(other.getContentType());
        this.setBceLogResult(other.getBceLogResult());
        this.setDate(other.getDate());
        this.setServer(other.getServer());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("CfcMetadata [");
        if (this.ContentType != null) {
            builder.append("\nContent-Type=").append(this.ContentType);
        }
        if (this.Date != null) {
            builder.append(", \nDate=").append(this.Date);
        }
        if (this.Server != null) {
            builder.append(", \nServer=").append(this.Server);
        }
        if (this.BceLogResult != null) {
            builder.append(", \nX-Bce-Log-Result=").append(this.BceLogResult);
        }
        if (this.BceRequestId != null) {
            builder.append(", \nX-Bce-Request-Id=").append(this.BceRequestId);
        }
        builder.append(", \nContent-Length=").append(this.ContentLength);

        builder.append(']');
        return builder.toString();
    }
}
