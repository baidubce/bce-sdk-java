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

/**
 * The model for datasource.
 */
public class DataSourceModel {
    /**
     * The name of container.
     */
    private String containerName;

    /**
     * The flag which indicates standard output.
     */
    private boolean isStdouterr;

    /**
     * The path of log file.
     */
    private String logFilePath;

    /**
     * The flag which indicates has pods log.
     */
    private boolean hasPodlogs;

    /**
     * The flag which indicates has pod metrics.
     */
    private boolean hasPodmetrics;

    /**
     * The id info.
     */
    private String id;

    /**
     * The pid info.
     */
    private String pid;

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public boolean getIsStdouterr() {
        return isStdouterr;
    }

    public void setIsStdouterr(boolean isStdouterr) {
        this.isStdouterr = isStdouterr;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public boolean isStdouterr() {
        return isStdouterr;
    }

    public void setStdouterr(boolean stdouterr) {
        isStdouterr = stdouterr;
    }

    public boolean isHasPodlogs() {
        return hasPodlogs;
    }

    public void setHasPodlogs(boolean hasPodlogs) {
        this.hasPodlogs = hasPodlogs;
    }

    public boolean isHasPodmetrics() {
        return hasPodmetrics;
    }

    public void setHasPodmetrics(boolean hasPodmetrics) {
        this.hasPodmetrics = hasPodmetrics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public DataSourceModel withContainerName(String containerName) {
        this.setContainerName(containerName);
        return this;
    }

    public DataSourceModel withIsStdouterr(boolean isStdouterr) {
        this.setIsStdouterr(isStdouterr);
        return this;
    }

    public DataSourceModel withLogFilePath(String logFilePath) {
        this.setLogFilePath(logFilePath);
        return this;
    }

    public DataSourceModel withId(String id) {
        this.setId(id);
        return this;
    }

    public DataSourceModel withHasPodlogs(boolean hasPodlogs) {
        this.setHasPodlogs(hasPodlogs);
        return this;
    }

    public DataSourceModel withHasPodmetrics(boolean hasPodmetrics) {
        this.setHasPodlogs(hasPodmetrics);
        return this;
    }

    public DataSourceModel withPid(String pid) {
        this.setPid(pid);
        return this;
    }



}
