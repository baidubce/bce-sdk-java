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
package com.baidubce.services.bvw.model.notification;

import java.util.Date;

import com.baidubce.services.bvw.model.common.UtcTime;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The class of Notification model.
 */
public class NotificationModel {

    /**
     * The notification id.
     */
    private String notificationId;
    /**
     * The notification name.
     */
    private String name;
    /**
     * The notification endpoint.
     */
    private String endpoint;
    /**
     * The notification status.
     */
    private NotificationStatus status;
    /**
     * The create time of notification.
     */
    @UtcTime
    private Date createTime;
    /**
     * The update time of notification.
     */
    @UtcTime
    private Date updateTime;

    /**
     * Construct a notification model with specified parameters.
     *
     * @param response The getting notification response
     * @return A notification model
     */
    public static NotificationModel of(NotificationGetResponse response) {
        NotificationModel notification = new NotificationModel();
        notification.setNotificationId(response.getNotificationId());
        notification.setName(response.getName());
        notification.setEndpoint(response.getEndpoint());
        notification.setStatus(response.getStatus());
        notification.setCreateTime(response.getCreateTime());
        notification.setUpdateTime(response.getUpdateTime());
        return notification;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("notificationId", notificationId)
                .append("name", name)
                .append("endpoint", endpoint)
                .append("status", status)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .toString();
    }

}
