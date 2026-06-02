package com.baidubce.services.bos.model;

import java.util.List;

public class GetNotificationResponse extends BosResponse {
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    private List<Notification> notifications;

}
