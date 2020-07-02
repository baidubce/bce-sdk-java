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

import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * List notificatio response.
 */
public class NotificationListResponse extends AbstractBceResponse {

    /**
     * The notification list.
     */
    private List<NotificationModel> notifications;

    public NotificationListResponse() {
        this.metadata = new BvwResponseMetadata();
    }

    public List<NotificationModel> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("notifications", notifications)
                .toString();
    }

}
