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

package com.baidubce.services.cnap.model.cluster;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for import cluster.
 */
public class ImportClusterRequest extends AbstractBceRequest {

    /**
     * The workspace id list.
     */
    private List<Map<String, String>> workspaceList;

    /**
     * The cluster info.
     */
    private NewClusterModel cluster;

    public void setWorkspaceList(List<String> workspaceList) {
        if (CollectionUtils.isEmpty(workspaceList)) {
            return;
        }
        this.workspaceList = new LinkedList<Map<String, String>>();
        for (String workspaceID : workspaceList) {
            Map<String, String> info = new HashMap<String, String>();
            info.put("workspaceID", workspaceID);
            this.workspaceList.add(info);
        }
    }

    public NewClusterModel getCluster() {
        return cluster;
    }

    public void setCluster(NewClusterModel cluster) {
        this.cluster = cluster;
    }

    public ImportClusterRequest withWorkspaceList (List<String> workspaceList) {
        this.setWorkspaceList(workspaceList);
        return this;
    }

    public ImportClusterRequest withNewClusterModel (NewClusterModel model) {
        this.setCluster(model);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ImportClusterRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
