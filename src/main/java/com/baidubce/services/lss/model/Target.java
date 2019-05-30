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

public class Target implements Serializable {
    private String format = null;

    private String sizingPolicy = null;

    private Integer widthInPixel = null;

    private Integer heightInPixel = null;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Target withFormat(String format) {
        this.format = format;
        return this;
    }

    public String getSizingPolicy() {
        return sizingPolicy;
    }

    public void setSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
    }

    public Target withSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
        return this;
    }

    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    public Target withWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
        return this;
    }

    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }

    public Target withHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Target {\n");
        sb.append("    format: ").append(format).append("\n");
        sb.append("    sizingPolicy: ").append(sizingPolicy).append("\n");
        sb.append("    widthInPixel: ").append(widthInPixel).append("\n");
        sb.append("    heightInPixel: ").append(heightInPixel).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
