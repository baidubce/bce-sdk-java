/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.media.model;

public class JobStatus {
    private Integer total   = null;

    private Integer running = null;

    private Integer pending = null;

    private Integer success = null;

    private Integer failed  = null;

    /**
     * 队列中的任务总数
     **/
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 队列中运行中的任务总数
     **/
    public Integer getRunning() {
        return running;
    }

    public void setRunning(Integer running) {
        this.running = running;
    }

    /**
     * 队列中排队中的任务总数
     **/
    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    /**
     * 队列中已执行成功的任务总数
     **/
    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    /**
     * 队列中执行失败的任务总数
     **/
    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobStatus {\n");

        sb.append("    total: ").append(total).append("\n");
        sb.append("    running: ").append(running).append("\n");
        sb.append("    pending: ").append(pending).append("\n");
        sb.append("    success: ").append(success).append("\n");
        sb.append("    failed: ").append(failed).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
