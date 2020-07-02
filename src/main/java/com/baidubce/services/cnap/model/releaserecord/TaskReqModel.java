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

package com.baidubce.services.cnap.model.releaserecord;

/**
 * The model for task.
 */
public class TaskReqModel {
    /**
     * The id of deploy group.
     */
    private String deployGroupID;

    /**
     * The operation after task. eg: 0 , 1.
     * 0 indicates continue after task.
     * 1 indicates pending after task.
     */
    private int pendAfterTask = 0;



    public String getDeployGroupID() {
        return deployGroupID;
    }

    public void setDeployGroupID(String deployGroupID) {
        this.deployGroupID = deployGroupID;
    }

    public int getPendAfterTask() {
        return pendAfterTask;
    }

    public void setPendAfterTask(int pendAfterTask) {
        this.pendAfterTask = pendAfterTask;
    }

    public TaskReqModel withDeployGroupID(String deployGroupID) {
        this.setDeployGroupID(deployGroupID);
        return this;
    }

    public TaskReqModel withPendAfterTask(int pendAfterTask) {
        this.setPendAfterTask(pendAfterTask);
        return this;
    }


}
