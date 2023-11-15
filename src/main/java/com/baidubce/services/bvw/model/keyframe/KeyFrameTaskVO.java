/*
 * Copyright (c) 2021 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bvw.model.keyframe;

import java.util.List;

/**
 * keyframe task vo
 */
public class KeyFrameTaskVO {
    /**
     * taskId in videoWorks
     */
    private String taskId;
    /**
     * taskStage's name in videoWorks
     */
    private String stageName;
    /**
     * bos bucket
     */
    private String targetBucket;
    /**
     * bos keyframe' thumbnail key in bucket
     */
    private List<String> targetKeys;
    /**
     * cdn's List
     */
    private List<CdnDomainVO> domains;
    /**
     * keyframe list
     */
    private List<KeyFrameDescVO> keyFrameDesc;
    /**
     * video's preSign url
     */
    private List<String> preSignedUrls;

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

    public String getTargetBucket() {
        return targetBucket;
    }

    public void setTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
    }

    public List<String> getTargetKeys() {
        return targetKeys;
    }

    public void setTargetKeys(List<String> targetKeys) {
        this.targetKeys = targetKeys;
    }

    public List<CdnDomainVO> getDomains() {
        return domains;
    }

    public void setDomains(List<CdnDomainVO> domains) {
        this.domains = domains;
    }

    public List<KeyFrameDescVO> getKeyFrameDesc() {
        return keyFrameDesc;
    }

    public void setKeyFrameDesc(List<KeyFrameDescVO> keyFrameDesc) {
        this.keyFrameDesc = keyFrameDesc;
    }

    public List<String> getPreSignedUrls() {
        return preSignedUrls;
    }

    public void setPreSignedUrls(List<String> preSignedUrls) {
        this.preSignedUrls = preSignedUrls;
    }
}
