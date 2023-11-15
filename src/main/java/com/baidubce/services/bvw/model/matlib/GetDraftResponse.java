package com.baidubce.services.bvw.model.matlib;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.model.matlib.timeline.Timeline;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collections;
import java.util.List;

/**
 * Get a single draft response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GetDraftResponse extends AbstractBceResponse {
    private Timeline timeline = new Timeline();

    private List<GetMaterialResponse> resourceList = Collections.emptyList();

    private String title;

    private String ratio;

    private String coverBucket;

    private String coverKey;

    private String endpoint;

    private String lastUpdateTime;

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public List<GetMaterialResponse> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<GetMaterialResponse> resourceList) {
        this.resourceList = resourceList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getCoverBucket() {
        return coverBucket;
    }

    public void setCoverBucket(String coverBucket) {
        this.coverBucket = coverBucket;
    }

    public String getCoverKey() {
        return coverKey;
    }

    public void setCoverKey(String coverKey) {
        this.coverKey = coverKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
