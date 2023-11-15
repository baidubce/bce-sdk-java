/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.dns.model;

import java.util.List;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddLineGroupRequest extends BaseBceRequest {
    /**
     * 线路组名称，长度不超过12个字符。
     */
    private String name;

    /**
     * 解析线路。
     */
    private List<String> lines;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getLines() {
        return this.lines;
    }

    @Override
    public String toString() {
        return "AddLineGroupRequest{"
                + "name=" + name + "\n"
                + "lines=" + lines + "\n"
                + "}";
    }

}