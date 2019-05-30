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

public class Layout {
    /**
     * vertical alignment, options include top, center, bottom
     */
    private String verticalAlignment = null;

    /**
     * horizontal alignment, options include left, center, right
     */
    private String horizontalAlignment = null;

    /**
     * vertical offset in pixel
     */
    private Integer verticalOffsetInPixel = null;

    /**
     * horizontal offset in pixel
     */
    private Integer horizontalOffsetInPixel = null;

    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public Layout withVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    public Integer getVerticalOffsetInPixel() {
        return verticalOffsetInPixel;
    }

    public void setVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
    }

    public Layout withVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
        return this;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public Layout withHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public Integer getHorizontalOffsetInPixel() {
        return horizontalOffsetInPixel;
    }

    public void setHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
    }

    public Layout withHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Layout {\n");

        sb.append("    verticalAlignment: ").append(verticalAlignment).append("\n");
        sb.append("    horizontalAlignment: ").append(horizontalAlignment).append("\n");
        sb.append("    verticalOffsetInPixel: ").append(verticalOffsetInPixel).append("\n");
        sb.append("    horizontalOffsetInPixel: ").append(horizontalOffsetInPixel).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
