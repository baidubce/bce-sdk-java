/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.cdn.model;

import java.util.Date;
import java.util.List;

/**
 * @author yixing
 *
 */
public class StatPvDetails extends JsonObject {
    private Date timestamp;
    private Integer pv;
    private Integer qps;
    private List<PvRegionData> regions;
    
    /**
     * @return timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    /**
     * @return pv
     */
    public Integer getPv() {
        return pv;
    }
    /**
     * @param pv
     */
    public void setPv(Integer pv) {
        this.pv = pv;
    }
    /**
     * @return qps
     */
    public Integer getQps() {
        return qps;
    }
    /**
     * @param qps
     */
    public void setQps(Integer qps) {
        this.qps = qps;
    }
    /**
     * @return regions
     */
    public List<PvRegionData> getRegions() {
        return regions;
    }
    /**
     * @param regions
     */
    public void setRegions(List<PvRegionData> regions) {
        this.regions = regions;
    }
}
