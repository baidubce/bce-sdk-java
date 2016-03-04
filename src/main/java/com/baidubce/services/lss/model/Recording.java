/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

public class Recording {
    /**
     * 使用的格式
     */
    private String format = null;

    /**
     * 多长时间的直播数据切割成一个视频
     */
    private Integer periodInMinute = null;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Recording withFormat(String format) {
        this.format = format;
        return this;
    }

    public Integer getPeriodInMinute() {
        return periodInMinute;
    }

    public void setPeriodInMinute(Integer periodInMinute) {
        this.periodInMinute = periodInMinute;
    }

    public Recording withPeriodInMinute(Integer periodInMinute) {
        this.periodInMinute = periodInMinute;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Recording {\n");
        sb.append("    format: ").append(format).append("\n");
        sb.append("    periodInMinute: ").append(periodInMinute).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
