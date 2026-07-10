package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.EoRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * The request for purging cached resources.
 *
 */
public class PurgeRequest extends EoRequest {
    /**
     * The purge task list.
     */
    private List<PurgeTask> tasks;

    /**
     * The site to purge.
     */
    private String site;

    /**
     * @param task
     * @return this object
     */
    public PurgeRequest addTask(PurgeTask task) {
        if (tasks == null) {
            tasks = new ArrayList<PurgeTask>();
        }
        tasks.add(task);
        return this;
    }

    /**
     * @return tasks
     */
    public List<PurgeTask> getTasks() {
        return tasks;
    }

    /**
     * @param tasks
     */
    public void setTasks(List<PurgeTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * @param site
     * @return this object
     */
    public PurgeRequest withSite(String site) {
        this.site = site;
        return this;
    }

    /**
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }
}
