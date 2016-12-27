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

import static com.baidubce.util.Validate.checkStringNotEmpty;

public class Source {
    private String sourceKey = null;

    /**
     * 原始文件的BOS Key
     **/
    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        checkStringNotEmpty(sourceKey, "The parameter sourceKey should NOT be null or empty string.");
        this.sourceKey = sourceKey;
    }

    public Source withSourceKey(String sourceKey) {
        checkStringNotEmpty(sourceKey, "The parameter sourceKey should NOT be null or empty string.");
        this.sourceKey = sourceKey;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Source {\n");

        sb.append("    sourceKey: ").append(sourceKey).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
