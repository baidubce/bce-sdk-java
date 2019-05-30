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

package com.baidubce.services.media.model;

public class Font {
    /**
     * font family
     **/
    private String family = null;

    /**
     * font size
     **/
    private Integer sizeInPoint = null;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Font withFamily(String family) {
        this.family = family;
        return this;
    }

    public Integer getSizeInPoint() {
        return sizeInPoint;
    }

    public void setSizeInPoint(Integer sizeInPoint) {
        this.sizeInPoint = sizeInPoint;
    }

    public Font withSizeInPoint(Integer sizeInPoint) {
        this.sizeInPoint = sizeInPoint;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Font {\n");

        sb.append("    family: ").append(family).append("\n");
        sb.append("    sizeInPoint: ").append(sizeInPoint).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
