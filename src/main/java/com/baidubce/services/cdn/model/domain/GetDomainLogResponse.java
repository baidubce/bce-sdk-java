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

package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;
import com.baidubce.services.cdn.model.logmodel.LogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yixing
 *
 */
public class GetDomainLogResponse extends CdnResponse {
    private List<LogEntry> logs = new ArrayList<LogEntry>();

    /**
     * @return logs
     */
    public List<LogEntry> getLogs() {
        return logs;
    }

    /**
     * @param logs
     */
    public void setLogs(List<LogEntry> logs) {
        this.logs = logs;
    }
}


