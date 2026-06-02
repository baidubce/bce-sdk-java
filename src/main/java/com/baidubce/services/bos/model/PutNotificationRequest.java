package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"bucketName"})
public class PutNotificationRequest extends GenericBucketRequest {

    @JsonAlias({"notifications"})
    private List<Notification> notifications;

    public PutNotificationRequest() {
    }

    ;

    public PutNotificationRequest(String bucketName) {
        super(bucketName);
    }

    public PutNotificationRequest(String bucketName, List<Notification> notifications) {
        super(bucketName);
        this.notifications = notifications;
    }

    @Override
    public PutNotificationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public PutNotificationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
