/*
 * Copyright 2017 Baidu, Inc.
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
package com.baidubce.services.vcr.model;

/**
 * Evidence location.
 */
public class Location {
    private Integer leftOffsetInPixel;
    private Integer topOffsetInPixel;
    private Integer widthInPixel;
    private Integer heightInPixel;

    public Integer getLeftOffsetInPixel() {
        return leftOffsetInPixel;
    }

    public void setLeftOffsetInPixel(Integer leftOffsetInPixel) {
        this.leftOffsetInPixel = leftOffsetInPixel;
    }

    public Integer getTopOffsetInPixel() {
        return topOffsetInPixel;
    }

    public void setTopOffsetInPixel(Integer topOffsetInPixel) {
        this.topOffsetInPixel = topOffsetInPixel;
    }

    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("leftOffsetInPixel=").append(leftOffsetInPixel);
        sb.append(", topOffsetInPixel=").append(topOffsetInPixel);
        sb.append(", widthInPixel=").append(widthInPixel);
        sb.append(", heightInPixel=").append(heightInPixel);
        sb.append('}');
        return sb.toString();
    }
}
