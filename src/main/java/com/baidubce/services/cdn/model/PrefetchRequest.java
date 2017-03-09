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

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

public class PrefetchRequest extends AbstractBceRequest {
    private List<PrefetchTask> tasks;
    
    public PrefetchRequest() {
        tasks = new ArrayList<PrefetchTask>();
    }
    
    public List<PrefetchTask> getTasks() {
        return tasks;
    }
    
    public void setTasks(List<PrefetchTask> tasks) {
        this.tasks = tasks;
    }
    
    public PrefetchRequest withTasks(List<PrefetchTask> tasks) {
        setTasks(tasks);
        return this;
    }
    
    @Override
    public PrefetchRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
