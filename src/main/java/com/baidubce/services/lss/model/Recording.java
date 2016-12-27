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

public class Recording {

    private String name = null;

    private String description = null;

    private Bos bos = null;

    private String format = null;

    private String pattern = null;

    private Integer periodInMinute = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recording withName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recording withDescription(String description) {
        this.description = description;
        return this;
    }

    public Bos getBos() {
        return bos;
    }

    public void setBos(Bos bos) {
        this.bos = bos;
    }

    public Recording withBos(Bos bos) {
        this.bos = bos;
        return this;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Recording withPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

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
        sb.append("    name: ").append(name).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    bos: ").append(bos).append("\n");
        sb.append("    format: ").append(format).append("\n");
        sb.append("    pattern: ").append(pattern).append("\n");
        sb.append("    periodInMinute: ").append(periodInMinute).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
