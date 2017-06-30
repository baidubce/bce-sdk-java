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

/**
 * @author yixing
 *
 */
public class StatUvDetails extends JsonObject {
    private Date timestamp;
    private Integer uv;
    
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
     * @return uv
     */
    public Integer getUv() {
        return uv;
    }
    /**
     * @param uv
     */
    public void setUv(Integer uv) {
        this.uv = uv;
    }
}
