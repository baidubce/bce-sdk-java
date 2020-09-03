/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.bvw.model.matlib;

/**
 * The  class to enumerate all the supported speech style in text to video.
 */
public enum Per {

    BASE_DUXIAOYU("基础语音库——度小宇", 1),
    BASE_DUXIAOMEI("基础语音库——度小美", 0),
    BASE_DUXIAOYAO("基础语音库——度逍遥", 3),
    BASE_DUYAYA("基础语音库——度丫丫", 4),
    QUALITY_DUXIAOYAO("精品语音库——度逍遥", 5003),
    QUALITY_DUXIAOLU("精品语音库——度小鹿", 5118),
    QUALITY_DUBOWEN("精品语音库——度博文", 106),
    QUALITY_DUXIAOTONG("精品语音库——度小童", 110),
    QUALITY_DUXIAOMENG("精品语音库——度小萌", 111),
    QUALITY_DUMIDUO("精品语音库——度米朵", 103),
    QUALITY_DUXIAOJIAO("精品语音库——度小娇", 5);

    String desc;
    int code;

    Per(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

}
