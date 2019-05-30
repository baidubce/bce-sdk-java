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

public class Insert {
    /**
     * BOS bucket
     */
    private String bucket = null;

    /**
     * BOS key
     */
    private String key = null;

    /**
     * Insert type, options include video, image, audio, subtitle, text etc.
     */
    private String type = null;

    /**
     * Insert text content, can only be set when type is "text"
     */
    private String text;

    /**
     * text or subtitle font
     **/
    private Font font;

    /**
     * display timeline
     **/
    private Timeline timeline;

    /**
     * display position
     **/
    private Layout layout;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public Insert withBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Insert withKey(String key) {
        this.key = key;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Insert withType(String type) {
        this.type = type;
        return this;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Insert withText(String text) {
        this.text = text;
        return this;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Insert withFont(Font font) {
        this.font = font;
        return this;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Insert withTimeline(Timeline timeline) {
        this.timeline = timeline;
        return this;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Insert withLayout(Layout layout) {
        this.layout = layout;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Insert {\n");
        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    key: ").append(key).append("\n");
        sb.append("    type: ").append(type).append("\n");
        sb.append("    text: ").append(text).append("\n");
        sb.append("    font: ").append(font).append("\n");
        sb.append("    timeline: ").append(timeline).append("\n");
        sb.append("    layout: ").append(layout).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
