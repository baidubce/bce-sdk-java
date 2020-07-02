/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotshc.model.ai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * unit response content
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessQueryContent {

    private int error_code = 0;
    private String err_msg = "";
    private String sessionId = "";
    // 技能id
    private String origin = "";
    private String detail = null;
    private ActionType action_type;
    private TtsResp ttsResp;
    private List<String> query;
    private String intent;
    private List<Slot> slots;
    private List<CustomReply> custom_reply;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Slot {
        private String name;
        private String value;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomReply {
        private String type;
        private Object value;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TtsResp {
        private String text;
        private String param;
    }

    public enum ActionType {
        asr_none, asrnlp_none, asrnlp_tts, asrnlp_url, asrnlp_mix
    }
}
