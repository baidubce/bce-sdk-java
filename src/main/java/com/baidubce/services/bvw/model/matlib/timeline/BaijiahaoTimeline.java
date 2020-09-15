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
package com.baidubce.services.bvw.model.matlib.timeline;

import com.baidubce.services.bos.BosClient;
import lombok.Data;
import java.util.List;


/**
 * The model of timeline information.
 */
@Data
public class BaijiahaoTimeline {


    /**
     * The video track of timeline.
     */
    private List<MediaPair> video;
    /**
     * The audio track of timeline.
     */
    private List<MediaPair> audio;
    /**
     * The sticker track of timeline.
     */
    private List<MediaPair> sticker;
    /**
     * The subtitle track of timeline.
     */
    private List<Object> subtitle;

    /**
     * Calculate url for BOS object of timeline.
     *
     * @param bosClient User's BOS client.
     */
    public void generalSignedUrl(BosClient bosClient) {
        if (video != null) {
            for (MediaPair pair : video) {
                pair.generalSignedUrl(bosClient);
            }
        }
        if (audio != null) {
            for (MediaPair pair : audio) {
                pair.generalSignedUrl(bosClient);
            }
        }
        if (sticker != null) {
            for (MediaPair pair : sticker) {
                pair.generalSignedUrl(bosClient);
            }
        }
    }

}