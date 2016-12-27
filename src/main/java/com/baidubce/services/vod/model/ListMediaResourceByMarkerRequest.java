/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.vod.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.Date;

/**
 * List the properties of all media resource managed by VOD service.
 */
public class ListMediaResourceByMarkerRequest extends AbstractBceRequest {

    /*
     * The marker returned by first query
     */
    private String marker;

    /*
     * The max size returned
     */
    private int maxSize;
    /*
     * The media status
     */
    private String status;
    /*
     * The begin date
     */
    private Date begin;
    /*
     * The end date
     */
    private Date end;
    /*
     * The title string for preifix search
     */
    private String title;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * set marker
     *
     * @param marker The marker.
     */
    public ListMediaResourceByMarkerRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * set maxSize
     *
     * @param maxSize The maxSize.
     */
    public ListMediaResourceByMarkerRequest withMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * set status
     *
     * @param status The status.
     */
    public ListMediaResourceByMarkerRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * set begin
     *
     * @param begin The begin date.
     */
    public ListMediaResourceByMarkerRequest withBegin(Date begin) {
        this.begin = begin;
        return this;
    }

    /**
     * set end
     *
     * @param end The end date.
     */
    public ListMediaResourceByMarkerRequest withEnd(Date end) {
        this.end = end;
        return this;
    }

    /**
     * set title
     *
     * @param title The title.
     */
    public ListMediaResourceByMarkerRequest withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
