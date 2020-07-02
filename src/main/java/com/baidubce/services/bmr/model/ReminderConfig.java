package com.baidubce.services.bmr.model;

import java.util.List;

public class ReminderConfig {
    //    @NotNull
    private Boolean enabled;

    //    @NotEmpty
    private String action;

    private List<String> media;

    //    @NotNull
    //    @Min(1)
    private Integer duration;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
