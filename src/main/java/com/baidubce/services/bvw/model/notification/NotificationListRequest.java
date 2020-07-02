package com.baidubce.services.bvw.model.notification;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Desc:
 *
 * @author guichen01
 * create date: 2019-07-23
 */
public class NotificationListRequest extends AbstractBceRequest {

    /**
     * The notification status.
     */
    private NotificationStatus status;

    /**
     * Construct a listing notification request with specified parameters.
     * @param status The notification status
     * @return A listing notification request
     */
    public static NotificationListRequest of(NotificationStatus status) {
        NotificationListRequest listRequest = new NotificationListRequest();
        listRequest.setStatus(status);
        return listRequest;
    }

    @Override
    public NotificationListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

}
