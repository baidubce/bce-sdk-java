/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.media;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * List media request.
 */
public class MediaListRequest extends AbstractBceRequest {

    /**
     * The page number of list by page request.
     */
    private int pageNo;
    /**
     * The number of element in each page.
     */
    private int pageSize;
    /**
     * The media status.
     */
    private MediaStatus status;
    /**
     * The latest instance status.
     */
    private RunnableStatus instanceStatus;
    /**
     * The media id.
     */
    private String mediaId;
    /**
     * The meida title, support fuzzy query.
     */
    private String title;
    /**
     * The begin time of creating media time, using UTC format.
     * eg. "2019-04-07T16:00:00Z"
     */
    private String beginTime;
    /**
     * The end time of creating media time, using UTC format.
     * eg. "2019-04-07T16:00:00Z"
     */
    private String endTime;
    /**
     * The order type, using comma to split multiple value.
     * Support type is "asc" / "desc" (case insensitive)
     */
    private String order;
    /**
     * The order column, using comma to split multiple column.
     * eg. order = "asc,desc" and orderBy = "createTime,status", means order by createTime asc
     * and order by status desc.
     */
    private String orderBy;

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo         The page number
     * @param pageSize       The page size
     * @param beginTime      The begin time of create media
     * @param endTime        The end time of create media
     * @param status         The media status
     * @param instanceStatus The instance status
     * @param mediaId        The media id
     * @param title          The media title
     * @param order          The order columns
     * @param orderBy        The order by type of columns
     * @return A listing media request
     */
    public static MediaListRequest of(int pageNo, int pageSize, String beginTime, String endTime, MediaStatus status,
                                      RunnableStatus instanceStatus, String mediaId, String title, String order,
                                      String orderBy) {
        MediaListRequest listRequest = new MediaListRequest();
        listRequest.setPageNo(pageNo);
        listRequest.setPageSize(pageSize);
        listRequest.setBeginTime(beginTime);
        listRequest.setEndTime(endTime);
        listRequest.setStatus(status);
        listRequest.setInstanceStatus(instanceStatus);
        listRequest.setMediaId(mediaId);
        listRequest.setTitle(title);
        listRequest.setOrder(order);
        listRequest.setOrderBy(orderBy);
        return listRequest;
    }

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo    The page number
     * @param pageSize  The page size
     * @param beginTime The begin time of create media
     * @param endTime   The end time of create media
     * @return A listing media request
     */
    public static MediaListRequest ofDate(int pageNo, int pageSize, String beginTime, String endTime) {
        return of(pageNo, pageSize, beginTime, endTime, null, null, null, null, null, null);
    }

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo   The page number
     * @param pageSize The page size
     * @param status   The media status
     * @return A listing media request
     */
    public static MediaListRequest of(int pageNo, int pageSize, MediaStatus status) {
        return of(pageNo, pageSize, null, null, status, null, null, null, null, null);
    }

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo         The page number
     * @param pageSize       The page size
     * @param instanceStatus The instance status
     * @return A listing media request
     */
    public static MediaListRequest of(int pageNo, int pageSize, RunnableStatus instanceStatus) {
        return of(pageNo, pageSize, null, null, null, instanceStatus, null, null, null, null);
    }

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo         The page number
     * @param pageSize       The page size
     * @param mediaId        The media id
     * @return A listing media request
     */
    public static MediaListRequest of(int pageNo, int pageSize, String mediaId) {
        return of(pageNo, pageSize, null, null, null, null, mediaId, null, null, null);
    }

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo         The page number
     * @param pageSize       The page size
     * @param title          The media title
     * @return A listing media request
     */
    public static MediaListRequest ofTitle(int pageNo, int pageSize, String title) {
        return of(pageNo, pageSize, null, null, null, null, null, title, null, null);
    }

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo         The page number
     * @param pageSize       The page size
     * @param order          The order columns
     * @param orderBy        The order by type of columns
     * @return A listing media request
     */
    public static MediaListRequest ofOrder(int pageNo, int pageSize, String order, String orderBy) {
        return of(pageNo, pageSize, null, null, null, null, null, null, order, orderBy);
    }

    /**
     * Construct a listing media request with specified parameters.
     *
     * @param pageNo         The page number
     * @param pageSize       The page size
     * @return A listing media request
     */
    public static MediaListRequest of(int pageNo, int pageSize) {
        return of(pageNo, pageSize, null, null, null, null, null, null, null, null);
    }

    @Override
    public MediaListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

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

    public MediaStatus getStatus() {
        return status;
    }

    public void setStatus(MediaStatus status) {
        this.status = status;
    }

    public RunnableStatus getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(RunnableStatus instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pageNo", pageNo)
                .append("pageSize", pageSize)
                .append("status", status)
                .append("instanceStatus", instanceStatus)
                .append("mediaId", mediaId)
                .append("title", title)
                .append("beginTime", beginTime)
                .append("endTime", endTime)
                .append("order", order)
                .append("orderBy", orderBy)
                .toString();
    }

}
