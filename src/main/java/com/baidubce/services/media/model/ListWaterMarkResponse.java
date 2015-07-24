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

package com.baidubce.services.media.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class ListWaterMarkResponse extends AbstractBceResponse {
    private List<WaterMark> watermarks = new ArrayList<WaterMark>();

    public List<WaterMark> getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(List<WaterMark> watermarks) {
        this.watermarks = watermarks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ListWaterMarkResponse {\n");
        sb.append("    watermarks: ").append(watermarks).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
