/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.cdn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.cdn.model.cache.PrefetchTask;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yixing
 *
 */
public class PrefetchRequest extends AbstractBceRequest {
    private List<PrefetchTask> tasks;
    
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
     * @param tasks
     * @return returns this object
     */
    public PrefetchRequest withTasks(List<PrefetchTask> tasks) {
        setTasks(tasks);
        return this;
    }
    
    /**
     * @param task
     * @return returns this object
     */
    public PrefetchRequest addTask(PrefetchTask task) {
        if (tasks == null) {
            tasks = new ArrayList<PrefetchTask>();
        }
        tasks.add(task);
        return this;
    }
    
    /**
     * (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public PrefetchRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * (non-Javadoc)
     * @see Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
