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
 * The model for container lifecycle.
 */
public class LifecycleModel {
    /**
     * The post start handler.
     */
    private HandlerModel postStart;

    /**
     * The pre stop handler.
     */
    private HandlerModel preStop;

    public HandlerModel getPostStart() {
        return postStart;
    }

    public void setPostStart(HandlerModel postStart) {
        this.postStart = postStart;
    }

    public HandlerModel getPreStop() {
        return preStop;
    }

    public void setPreStop(HandlerModel preStop) {
        this.preStop = preStop;
    }

    public LifecycleModel withPostStart(HandlerModel postStart) {
        this.setPostStart(postStart);
        return this;
    }

    public LifecycleModel withPreStop(HandlerModel preStop) {
        this.setPreStop(preStop);
        return this;
    }
}
