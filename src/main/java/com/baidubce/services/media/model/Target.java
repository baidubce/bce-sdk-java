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

import static com.baidubce.util.Validate.checkStringNotEmpty;

public class Target {
    private String targetKey = null;
    private String presetName = null;

    /**
     * 目标文件的BOS key
     **/
    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        checkStringNotEmpty(targetKey, "The parameter targetKey should NOT be null or empty string.");
        this.targetKey = targetKey;
    }

    public Target withTargetKey(String targetKey) {
        checkStringNotEmpty(targetKey, "The parameter targetKey should NOT be null or empty string.");
        this.targetKey = targetKey;
        return this;
    }

    /**
     * 输出处理的模板的presetName
     **/
    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
    }

    public Target withPresetName(String presetName) {
        checkStringNotEmpty(presetName, "The parameter presetName should NOT be null or empty string.");
        this.presetName = presetName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Target {\n");

        sb.append("    targetKey: ").append(targetKey).append("\n");
        sb.append("    presetName: ").append(presetName).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
