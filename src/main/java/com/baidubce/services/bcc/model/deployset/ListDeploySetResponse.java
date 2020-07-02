/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model.deployset;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response of the deploy set owned by the user.
 */
public class ListDeploySetResponse extends AbstractBceResponse {

    private List<DeploySetInfo> deploySets;

    public List<DeploySetInfo> getDeploySets() {
        return deploySets;
    }

    public void setDeploySets(List<DeploySetInfo> deploySets) {
        this.deploySets = deploySets;
    }

    @Override
    public String toString() {
        return "ListDeploySetResponse{" +
                "deploySets=" + deploySets +
                '}';
    }
}
