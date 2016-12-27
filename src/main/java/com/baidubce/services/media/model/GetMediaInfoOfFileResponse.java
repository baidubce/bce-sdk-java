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

import com.baidubce.model.AbstractBceResponse;

public class GetMediaInfoOfFileResponse extends AbstractBceResponse {
    private String bucket = null;
    private String key = null;
    private Long fileSizeInByte = null;

    private String  container        = null;
    private Integer durationInSecond = null;

    // public enum fileSizeInByteEnum { };

    private String etag = null;
    private String type = null;
    private VideoInfo video = null;
    private AudioInfo audio = null;

    /**
     * 文件所在的BOS的Bucket
     **/
    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * 文件的BOS的Key
     **/
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 文件的大小
     **/
    public Long getFileSizeInByte() {
        return fileSizeInByte;
    }

    public void setFileSizeInByte(Long fileSizeInByte) {
        this.fileSizeInByte = fileSizeInByte;
    }

    /**
     * 文件的MD5值
     **/
    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     * 文件类型
     **/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VideoInfo getVideo() {
        return video;
    }

    public void setVideo(VideoInfo video) {
        this.video = video;
    }

    public AudioInfo getAudio() {
        return audio;
    }

    public void setAudio(AudioInfo audio) {
        this.audio = audio;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Integer getDurationInSecond() {
        return durationInSecond;
    }

    public void setDurationInSecond(Integer durationInSecond) {
        this.durationInSecond = durationInSecond;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetMediaInfoOfFileResponse {\n");

        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    key: ").append(key).append("\n");
        sb.append("    fileSizeInByte: ").append(fileSizeInByte).append("\n");
        sb.append("    container: ").append(container).append("\n");
        sb.append("    durationInSecond: ").append(durationInSecond).append("\n");
        sb.append("    etag: ").append(etag).append("\n");
        sb.append("    type: ").append(type).append("\n");
        sb.append("    video: ").append(video).append("\n");
        sb.append("    audio: ").append(audio).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
