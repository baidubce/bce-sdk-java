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

package com.baidubce.services.cdn.model.cache;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * @author yixing
 *
 */
public class PrefetchTask extends JsonObject {


    /**
     * 必选项，String类型，表示需要prefetch的URL。
     */
    private String url;

    /**
     * 可选项，Int类型，表示prefetch的限速，单位为byte/s，默认为0（不限制）
     */
    private int speed = 0;

    /**
     * 可选项，Timestamp类型，UTC 时间，startTime必须是大于当前时间，并且在24小时内的时间，否则失败。
     * 格式为：2016-04-16Z23:00:00T, 默认为立即执行
     */
    private String startTime;

    
    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url
     * @return returns this object
     */
    public PrefetchTask withUrl(String url) {
        setUrl(url);
        return this;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public PrefetchTask withSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public PrefetchTask withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }
}
