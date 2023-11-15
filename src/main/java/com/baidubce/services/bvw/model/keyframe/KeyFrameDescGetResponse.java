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

import java.util.Collections;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * response of keyframe description
 */
public class KeyFrameDescGetResponse extends AbstractBceResponse {
    /**
     * mediaId in videoWorks
     */
    private String mediaId;
    /**
     * workflowInstance's id
     */
    private String instanceId;
    /**
     * keyframe's detail
     */
    private List<KeyFrameTaskVO> tasks;

    public static KeyFrameDescGetResponse of(String mediaId, String instanceId, List<KeyFrameTaskVO> tasks) {
        KeyFrameDescGetResponse keyFrameDescGetResponse = new KeyFrameDescGetResponse();
        keyFrameDescGetResponse.setMediaId(mediaId);
        keyFrameDescGetResponse.setInstanceId(instanceId);
        keyFrameDescGetResponse.setTasks(tasks);
        return keyFrameDescGetResponse;
    }

    public static KeyFrameDescGetResponse of(String mediaId, String instanceId) {
        return of(mediaId, instanceId, Collections.EMPTY_LIST);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<KeyFrameTaskVO> getTasks() {
        return tasks;
    }

    public void setTasks(List<KeyFrameTaskVO> tasks) {
        this.tasks = tasks;
    }
}
