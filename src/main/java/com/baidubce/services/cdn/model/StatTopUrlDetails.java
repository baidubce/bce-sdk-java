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

import java.util.Date;
import java.util.List;

/**
 * @author yixing
 *
 */
public class StatTopUrlDetails {
    private Date timestamp;
    private List<KvCounter> counters;
    
    /**
     * @return timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    /**
     * @return counters
     */
    public List<KvCounter> getCounters() {
        return counters;
    }
    /**
     * @param counters
     */
    public void setCounters(List<KvCounter> counters) {
        this.counters = counters;
    }
}

