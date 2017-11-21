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

import java.util.List;

/**
 * Character result of a media with specific time.
 */
public class CharacterItem {
    private Integer timeInSeconds;
    private List<CharacterWord> words;

    public Integer getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(Integer timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public List<CharacterWord> getWords() {
        return words;
    }

    public void setWords(List<CharacterWord> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharacterItem{");
        sb.append("timeInSeconds=").append(timeInSeconds);
        sb.append(", words=").append(words);
        sb.append('}');
        return sb.toString();
    }
}
