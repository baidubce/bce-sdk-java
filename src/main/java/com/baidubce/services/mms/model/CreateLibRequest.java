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
package com.baidubce.services.mms.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * MMS create lib request.
 */
public class CreateLibRequest extends AbstractBceRequest {

    private String name;

    private String description;

    /**
     *  scoreThreshold: image search score threshold, value is 0-100
     */
    private int scoreThreshold;

    /**
     *  video perhaps need.
     *  videoScoreThreshold: video score threshold, value is 0-100
     *  frameType: default is 0, mean use ffmpeg and 5s per frame; or 1, mean use mix strategy, 5s must has one frame.
     *  interval: mean how much second per frame
     */
    private int videoScoreThreshold;

    private int frameType;

    private int interval;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScoreThreshold() {
        return scoreThreshold;
    }

    public void setScoreThreshold(int scoreThreshold) {
        this.scoreThreshold = scoreThreshold;
    }

    public int getVideoScoreThreshold() {
        return videoScoreThreshold;
    }

    public void setVideoScoreThreshold(int videoScoreThreshold) {
        this.videoScoreThreshold = videoScoreThreshold;
    }

    public int getFrameType() {
        return frameType;
    }

    public void setFrameType(int frameType) {
        this.frameType = frameType;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
