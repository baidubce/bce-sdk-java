/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.mms.model;

import java.util.Date;
import java.util.List;

/**
 * MMS search image by image response.
 */
public class SearchImageResponse extends MmsBaseResponse {

    private String lib;
    private String description;
    private String source;
    private Date createTime;
    private Date finishTime;

    private List<SearchTaskResult> results;

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public List<SearchTaskResult> getResults() {
        return results;
    }

    public void setResults(List<SearchTaskResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchImageResponse{" +
                "lib='" + lib + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", results=" + results +
                ", metadata=" + metadata +
                '}';
    }
}
