package com.baidubce.services.bmr.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Reminder extends ReminderConfig {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC") private Date
            lastDateTime;

    public Date getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(Date lastDateTime) {
        this.lastDateTime = lastDateTime;
    }
}
