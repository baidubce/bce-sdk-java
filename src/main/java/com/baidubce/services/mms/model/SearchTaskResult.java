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

package com.baidubce.services.mms.model;

import java.util.List;

/**
 * MMS search result.
 */
public class SearchTaskResult {

    private String id;

    private String name;

    private String source;

    private Float duration;

    private String description;

    private Float distance;

    private Float score;

    private List<VideoClip> clips;

    private List<MatchFrame> frames;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public List<VideoClip> getClips() {
        return clips;
    }

    public void setClips(List<VideoClip> clips) {
        this.clips = clips;
    }

    public List<MatchFrame> getFrames() {
        return frames;
    }

    public void setFrames(List<MatchFrame> frames) {
        this.frames = frames;
    }

    @Override
    public String toString() {
        return "SearchTaskResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", distance=" + distance +
                ", score=" + score +
                ", clips=" + clips +
                ", frames=" + frames +
                '}';
    }

    public static class VideoClip {

        private float inputStartTime;

        private float inputEndTime;

        private float outputStartTime;

        private float outputEndTime;

        public float getInputStartTime() {
            return inputStartTime;
        }

        public void setInputStartTime(float inputStartTime) {
            this.inputStartTime = inputStartTime;
        }

        public float getInputEndTime() {
            return inputEndTime;
        }

        public void setInputEndTime(float inputEndTime) {
            this.inputEndTime = inputEndTime;
        }

        public float getOutputStartTime() {
            return outputStartTime;
        }

        public void setOutputStartTime(float outputStartTime) {
            this.outputStartTime = outputStartTime;
        }

        public float getOutputEndTime() {
            return outputEndTime;
        }

        public void setOutputEndTime(float outputEndTime) {
            this.outputEndTime = outputEndTime;
        }

        @Override
        public String toString() {
            return "VideoClip{" +
                    "inputStartTime=" + inputStartTime +
                    ", inputEndTime=" + inputEndTime +
                    ", outputStartTime=" + outputStartTime +
                    ", outputEndTime=" + outputEndTime +
                    '}';
        }
    }

    public static class MatchFrame {

        private int position;

        private float timestamp;

        private float distance;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public float getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(float timestamp) {
            this.timestamp = timestamp;
        }

        public float getDistance() {
            return distance;
        }

        public void setDistance(float distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "MatchFrame{" +
                    "position=" + position +
                    ", timestamp=" + timestamp +
                    ", distance=" + distance +
                    '}';
        }
    }
}
