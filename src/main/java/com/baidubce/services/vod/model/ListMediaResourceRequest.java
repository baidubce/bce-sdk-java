/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
public class ListMediaResourceRequest extends AbstractBceRequest {
    /*
     * The page number
     */
    private int pageNo;
    /*
     * The page size for each page
     */
    private int pageSize;
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

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
     * set pageNo
     *
     * @param pageNo The pageNo.
     */
    public ListMediaResourceRequest withPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    /**
     * set pageSize
     *
     * @param pageSize The pageSize.
     */
    public ListMediaResourceRequest withPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * set status
     *
     * @param status The status.
     */
    public ListMediaResourceRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * set begin
     *
     * @param begin The begin date.
     */
    public ListMediaResourceRequest withBegin(Date begin) {
        this.begin = begin;
        return this;
    }

    /**
     * set end
     *
     * @param end The end date.
     */
    public ListMediaResourceRequest withEnd(Date end) {
        this.end = end;
        return this;
    }

    /**
     * set title
     *
     * @param title The title.
     */
    public ListMediaResourceRequest withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
