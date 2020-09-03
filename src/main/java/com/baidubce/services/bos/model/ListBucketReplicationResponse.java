/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bos.model;

import java.util.Arrays;
import java.util.List;

/**
 * Response for list bucket replication
 */
public class ListBucketReplicationResponse extends BosResponse {

    /**
     * The list of bucket replication
     */
    private List<Rule> rules;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "ListBucketReplicationResponse{" +
                "rules=" + rules +
                '}';
    }

    private static class Rule {

        private String status;

        private String[] resource;

        private Destination destination;

        private ReplicateHistory replicateHistory;

        private String replicateDeletes;

        private String id;

        private long createTime;

        private String destRegion;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String[] getResource() {
            return resource;
        }

        public void setResource(String[] resource) {
            this.resource = resource;
        }

        public Destination getDestination() {
            return destination;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }

        public ReplicateHistory getReplicateHistory() {
            return replicateHistory;
        }

        public void setReplicateHistory(ReplicateHistory replicateHistory) {
            this.replicateHistory = replicateHistory;
        }

        public String getReplicateDeletes() {
            return replicateDeletes;
        }

        public void setReplicateDeletes(String replicateDeletes) {
            this.replicateDeletes = replicateDeletes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDestRegion() {
            return destRegion;
        }

        public void setDestRegion(String destRegion) {
            this.destRegion = destRegion;
        }

        @Override
        public String toString() {
            return "Rule{" +
                    "status='" + status + '\'' +
                    ", resource=" + Arrays.toString(resource) +
                    ", destination=" + destination +
                    ", replicateHistory=" + replicateHistory +
                    ", replicateDeletes='" + replicateDeletes + '\'' +
                    ", id='" + id + '\'' +
                    ", createTime=" + createTime +
                    ", destRegion='" + destRegion + '\'' +
                    '}';
        }
    }

}
