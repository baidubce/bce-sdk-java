/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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

import com.baidubce.model.AbstractBceResponse;
import java.util.List;

/**
 * The response of changing the instance payment to prepaid.
 */
public class ReleasePrepaidInstanceResponse extends AbstractBceResponse {
    /**
     * Details of failed resource release.
     */
    private InstanceDeleteResultModel successResources;
    /**
     * Details of successfully released resources.
     */
    private InstanceDeleteResultModel failResources;
    /**
     * Instance release result identifier. If<code>true</code>, Release successful.
     */
    private Boolean instanceRefundFlag ;

    public InstanceDeleteResultModel getSuccessResources() {
        return successResources;
    }

    public void setSuccessResources(InstanceDeleteResultModel successResources) {
        this.successResources = successResources;
    }

    public InstanceDeleteResultModel getFailResources() {
        return failResources;
    }

    public void setFailResources(InstanceDeleteResultModel failResources) {
        this.failResources = failResources;
    }

    public boolean isInstanceRefundFlag() {
        return instanceRefundFlag;
    }

    public void setInstanceRefundFlag(boolean instanceRefundFlag) {
        this.instanceRefundFlag = instanceRefundFlag;
    }

    @Override
    public String toString() {
        return "ReleasePrepaidInstanceResponse{" +
                "successResources=" + successResources +
                ", failResources=" + failResources +
                ", instanceRefundFlag=" + instanceRefundFlag +
                ", metadata=" + metadata +
                '}';
    }

    static class InstanceDeleteResultModel {
        /**
         * instanceId.
         */
        private String instanceId ;
        /**
         * eip.
         */
        private String eip ;
        /**
         * instance snapshotId list.
         */
        private List<String> insnapIds;
        /**
         * snapshotId list.
         */
        private List<String> snapshotIds;
        /**
         * volumeId list
         */
        private List<String> volumeIds;

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public String getEip() {
            return eip;
        }

        public void setEip(String eip) {
            this.eip = eip;
        }

        public List<String> getInsnapIds() {
            return insnapIds;
        }

        public void setInsnapIds(List<String> insnapIds) {
            this.insnapIds = insnapIds;
        }

        public List<String> getSnapshotIds() {
            return snapshotIds;
        }

        public void setSnapshotIds(List<String> snapshotIds) {
            this.snapshotIds = snapshotIds;
        }

        public List<String> getVolumeIds() {
            return volumeIds;
        }

        public void setVolumeIds(List<String> volumeIds) {
            this.volumeIds = volumeIds;
        }


        @Override
        public String toString() {
            return "InstanceDeleteResultModel{" +
                    "instanceId='" + instanceId + '\'' +
                    ", eip='" + eip + '\'' +
                    ", insnapIds=" + insnapIds +
                    ", snapshotIds=" + snapshotIds +
                    ", volumeIds=" + volumeIds +
                    '}';
        }
    }
}
