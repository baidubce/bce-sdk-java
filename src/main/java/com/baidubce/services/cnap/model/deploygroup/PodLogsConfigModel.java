/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap.model.deploygroup;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * The model for pod logs config.
 */
public class PodLogsConfigModel {

    /**
     * The list of pod log config.
     */
    private List<LogConfigModel> logsConfig;

    public List<LogConfigModel> getLogsConfig() {
        return logsConfig;
    }

    public void setLogsConfig(List<LogConfigModel> logsConfig) {
        this.logsConfig = logsConfig;
    }

    public PodLogsConfigModel addLogConfigModel(LogConfigModel logConfigModel) {
        if (CollectionUtils.isEmpty(logsConfig)) {
            logsConfig = new LinkedList<LogConfigModel>();
        }
        logsConfig.add(logConfigModel);
        return this;

    }
}
