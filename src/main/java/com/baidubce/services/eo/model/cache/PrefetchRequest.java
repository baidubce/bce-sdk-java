package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.EoRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * The request for prefetching resources into cache.
 *
 */
public class PrefetchRequest extends EoRequest {
    /**
     * The prefetch task list.
     */
    private List<PrefetchTask> tasks;

    /**
     * The site to prefetch.
     */
    private String site;

    /**
     * @param task
     * @return this object
     */
    public PrefetchRequest addTask(PrefetchTask task) {
        if (tasks == null) {
            tasks = new ArrayList<PrefetchTask>();
        }
        tasks.add(task);
        return this;
    }

    /**
     * @return tasks
     */
    public List<PrefetchTask> getTasks() {
        return tasks;
    }

    /**
     * @param tasks
     */
    public void setTasks(List<PrefetchTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * @param site
     * @return this object
     */
    public PrefetchRequest withSite(String site) {
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
