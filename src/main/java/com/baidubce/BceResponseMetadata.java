/*
 * Copyright 2014 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents additional metadata included with a response from BCE.
 */
public class BceResponseMetadata implements Serializable {
    private String bceRequestId;

    private String bceContentSha256;

    private String contentDisposition;

    private String transferEncoding;

    private String contentEncoding;

    private long contentLength = -1;

    private String contentMd5;

    private String contentRange;

    private String contentType;

    private Date date;

    private String eTag;

    private Date expires;

    private Date lastModified;

    private String server;

    private String location;

    public String getSymlinkTarget() {
        return symlinkTarget;
    }

    public void setSymlinkTarget(String symlinkTarget) {
        this.symlinkTarget = symlinkTarget;
    }

    private String symlinkTarget;

    public String getBceRequestId() {
        return this.bceRequestId;
    }

    public void setBceRequestId(String bceRequestId) {
        this.bceRequestId = bceRequestId;
    }

    public String getBceContentSha256() {
        return this.bceContentSha256;
    }

    public void setBceContentSha256(String bceContentSha256) {
        this.bceContentSha256 = bceContentSha256;
    }

    public String getContentDisposition() {
        return this.contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentMd5() {
        return this.contentMd5;
    }

    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    public String getContentRange() {
        return this.contentRange;
    }

    public void setContentRange(String contentRange) {
        this.contentRange = contentRange;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public Date getExpires() {
        return this.expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getServer() {
        return this.server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTransferEncoding() {
        return transferEncoding;
    }

    public void setTransferEncoding(String transferEncoding) {
        this.transferEncoding = transferEncoding;
    }

    @Override
    public String toString() {
        return "BceResponseMetadata [\n  bceRequestId=" + bceRequestId
                + ", \n  bceContentSha256=" + bceContentSha256
                + ", \n  contentDisposition=" + contentDisposition
                + ", \n  contentEncoding=" + contentEncoding + ", \n  contentLength="
                + contentLength + ", \n  contentMd5=" + contentMd5
                + ", \n  contentRange=" + contentRange + ", \n  contentType="
                + contentType + ", \n  date=" + date + ", \n  eTag=" + eTag
                + ", \n  expires=" + expires + ", \n  lastModified=" + lastModified
                + ", \n  server=" + server + ", \n  location=" + location + "]";
    }

}
