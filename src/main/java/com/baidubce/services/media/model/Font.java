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

import java.math.BigDecimal;

public class Font {
    /**
     * font family
     **/
    private String family = null;

    /**
     * font size
     **/
    private Integer sizeInPoint = null;

    private String color = null;

    private BigDecimal alpha = null;

    private Integer shadowX = null;

    private Integer shadowY = null;
    private String shadowColor = null;

    private String fontName = null;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getAlpha() {
        return alpha;
    }

    public void setAlpha(BigDecimal alpha) {
        this.alpha = alpha;
    }

    public Integer getShadowX() {
        return shadowX;
    }

    public void setShadowX(Integer shadowX) {
        this.shadowX = shadowX;
    }

    public Integer getShadowY() {
        return shadowY;
    }

    public void setShadowY(Integer shadowY) {
        this.shadowY = shadowY;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

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
