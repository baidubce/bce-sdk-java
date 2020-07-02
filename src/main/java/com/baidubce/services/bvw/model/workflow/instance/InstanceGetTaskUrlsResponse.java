/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.workflow.instance;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import com.baidubce.services.bvw.model.workflow.StageType;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Get url resource from a instance.
 */
public class InstanceGetTaskUrlsResponse extends AbstractBceResponse {

    /**
     * The task urls resource from a instance.
     */
    private List<StageTaskUrl> stageTaskUrls;

    /**
     * The internal class for task url in getting task urls response.
     */
    public static class StageTaskUrl {

        /**
         * The task id.
         */
        private String taskId;
        /**
         * The stage name.
         */
        private String stageName;
        /**
         * The stage type.
         */
        private StageType stageType;
        /**
         * The target bucket name.
         */
        private String targetBucket;
        /**
         * The task running status.
         */
        private RunnableStatus status;
        /**
         * The domain list.
         */
        private List<Domain> domains;
        /**
         * The target keys.
         */
        private List<String> targetKeys;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }

        public StageType getStageType() {
            return stageType;
        }

        public void setStageType(StageType stageType) {
            this.stageType = stageType;
        }

        public String getTargetBucket() {
            return targetBucket;
        }

        public void setTargetBucket(String targetBucket) {
            this.targetBucket = targetBucket;
        }

        public RunnableStatus getStatus() {
            return status;
        }

        public void setStatus(RunnableStatus status) {
            this.status = status;
        }

        public List<Domain> getDomains() {
            return domains;
        }

        public void setDomains(
                List<Domain> domains) {
            this.domains = domains;
        }

        public List<String> getTargetKeys() {
            return targetKeys;
        }

        public void setTargetKeys(List<String> targetKeys) {
            this.targetKeys = targetKeys;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("taskId", taskId)
                    .append("stageName", stageName)
                    .append("stageType", stageType)
                    .append("targetBucket", targetBucket)
                    .append("status", status)
                    .append("domains", domains)
                    .append("targetKeys", targetKeys)
                    .toString();
        }

    }

    /**
     * The internal class for domain in task url.
     */
    public static class Domain {

        /**
         * The domain name.
         */
        private String domain;
        /**
         * The domain with https or not.
         */
        private boolean enableHttps;

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("domain", domain)
                    .append("enableHttps", enableHttps)
                    .toString();
        }

    }

    public InstanceGetTaskUrlsResponse() {
        this.metadata = new BvwResponseMetadata();
    }

    public List<StageTaskUrl> getStageTaskUrls() {
        return stageTaskUrls;
    }

    public void setStageTaskUrls(
            List<StageTaskUrl> stageTaskUrls) {
        this.stageTaskUrls = stageTaskUrls;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("stageTaskUrls", stageTaskUrls)
                .toString();
    }

}
