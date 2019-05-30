/*
 * Copyright 2015 Baidu, Inc.
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

package com.baidubce.services.lss.model;

import java.io.Serializable;

public class RealTimeSessionStatistics implements Serializable {
    private Long bandwidthInBps = null;

    private Long playCount = null;

    public Long getBandwidthInBps() {
        return bandwidthInBps;
    }

    public void setBandwidthInBps(Long bandwidthInBps) {
        this.bandwidthInBps = bandwidthInBps;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class RealTimeSessionStatistics {\n");
        sb.append("    bandwidthInBps: ").append(bandwidthInBps).append("\n");
        sb.append("    playCount: ").append(playCount).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
