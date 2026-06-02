/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * The request for rebooting the instances.
 */
public class RebootInstancesRequest extends AbstractBceRequest {

    /**
     * The ids of instance.
     */
    private List<String> instanceIds;

    /**
     * The option param to reboot the instance forcibly, default value is false.
     * If <code>true</code>, it will stop the instance just like power off immediately
     * and it may result int losing important data which have not written to disk.
     */
    private boolean forceStop = false;

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    /**
     * Configure instanceId for the request.
     *
     * @param instanceIds The id of the instance.
     * @return RebootInstanceRequest with instanceId.
     */
    public RebootInstancesRequest withInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
        return this;
    }


    public boolean isForceStop() {
        return forceStop;
    }

    public void setForceStop(boolean forceStop) {
        this.forceStop = forceStop;
    }

    /**
     * Configure forceStop for the request.
     *
     * @param forceStop The option param to reboot the instance forcibly, default value is false.
     *                  If <code>true</code>, it will stop the instance just like power off immediately
     *                  and it may result int losing important data which have not written to disk.
     * @return RebootInstanceRequest with forceStop.
     */
    public RebootInstancesRequest withForceStop(boolean forceStop) {
        this.forceStop = forceStop;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return RebootInstanceRequest with credentials.
     */
    @Override
    public RebootInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
