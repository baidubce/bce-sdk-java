package com.baidubce.services.vodpro.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.vodpro.model.common.Path;

/**
 * Created on 17/8/24
 *
 * @author liumin08
 */
public class CreateMediaRequest extends AbstractBceRequest {

    private Path path;

    private String triggerName;

    private String notificationName;

    private String description;


    @Override
    public CreateMediaRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateMediaRequest{"
                + "path=" + path
                + ", triggerName='" + triggerName + '\''
                + ", notificationName='" + notificationName + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
