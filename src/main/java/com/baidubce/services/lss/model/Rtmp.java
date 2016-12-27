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

public class Rtmp {
    /**
     * 是否缓存一个gop
     */
    private Boolean gopCache = null;

    public Boolean getGopCache() {
        return gopCache;
    }

    public void setGopCache(Boolean gopCache) {
        this.gopCache = gopCache;
    }

    public Rtmp withGopCache(Boolean gopCache) {
        this.gopCache = gopCache;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Rtmp {\n");
        sb.append("    gopCache: ").append(gopCache).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
