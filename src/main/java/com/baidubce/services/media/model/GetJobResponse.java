/*
 * Copyright 2015 Baidu, Inc.
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

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;

public class GetJobResponse extends AbstractBceResponse {
    private String    jobId        = null;
    private String    pipelineName = null;
    private Source    source       = null;
    private Target    target       = null;
    private String    jobStatus    = null;
    private Date      startTime    = null;
    private Date      endTime      = null;

    /**
     * 任务的唯一标识
     **/
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * 任务所属pipeline的名称
     **/
    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }


    /**
     * 输入的原始信息的集合
     **/
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * 输出信息的结合
     **/
    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    /**
     * 任务运行状态
     **/
    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * 任务开始处理的时间
     **/
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 任务完成处理的时间
     **/
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetJobResponse {\n");

        sb.append("    jobId: ").append(jobId).append("\n");
        sb.append("    pipelineName: ").append(pipelineName).append("\n");
        sb.append("    source: ").append(source).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("    jobStatus: ").append(jobStatus).append("\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
