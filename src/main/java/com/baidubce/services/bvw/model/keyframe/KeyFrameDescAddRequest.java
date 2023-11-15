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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 *  request model for add keyframe
 */
public class KeyFrameDescAddRequest extends AbstractBceRequest {

    private List<TaskAndKeyFrameDesc> tasks;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public static class TaskAndKeyFrameDesc {
        /**
         * taskIds in videoWorks
         */
        private List<String> taskIds;
        /**
         * keyframe's detail
         */
        private List<KeyFrameDescVO> keyFrameDesc;

        public List<String> getTaskIds() {
            return taskIds;
        }

        public void setTaskIds(List<String> taskIds) {
            this.taskIds = taskIds;
        }

        public List<KeyFrameDescVO> getKeyFrameDesc() {
            return keyFrameDesc;
        }

        public void setKeyFrameDesc(List<KeyFrameDescVO> keyFrameDesc) {
            this.keyFrameDesc = keyFrameDesc;
        }
    }

    public List<TaskAndKeyFrameDesc> getTasks() {
        return tasks;
    }

    public void setTasks(
            List<TaskAndKeyFrameDesc> tasks) {
        this.tasks = tasks;
    }
}
