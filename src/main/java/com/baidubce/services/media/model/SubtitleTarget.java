/*
 * Copyright 2020 Baidu, Inc.
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

import lombok.Data;

/**
 * Subtitle job target configuration.
 */
@Data
public class SubtitleTarget {

    /**
     * The key prefix of subtitle file in bos.
     */
    private String keyPrefix;

    /**
     * The format of subtitls, can be json, srt.
     */
    private List<String> formats;

    public SubtitleTarget withKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
        return this;
    }

    public SubtitleTarget withFormats(List<String> formats) {
        this.formats = formats;
        return this;
    }

    public SubtitleTarget addFormat(String format) {
        if (this.formats == null) {
            this.formats = new ArrayList<String>();
        }
        this.formats.add(format);
        return this;
    }


}
