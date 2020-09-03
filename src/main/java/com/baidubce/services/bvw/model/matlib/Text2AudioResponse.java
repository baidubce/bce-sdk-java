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

import com.baidubce.model.AbstractBceResponse;

import java.util.Arrays;
import java.util.List;

/**
 * The response of text to audio.
 */
public class Text2AudioResponse extends AbstractBceResponse {

    /**
     * 文字转语音的返回结果
     */
    private List<SingleText2AudioResponse> data;

    public List<SingleText2AudioResponse> getData() {
        return data;
    }

    public void setData(List<SingleText2AudioResponse> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Text2AudioResponse{" +
                "data=" + data +
                '}';
    }

    public static class SingleText2AudioResponse {
        /**
         * 输入的text
         */
        private String text;
        /**
         * 返回错误信息, 仅异常时提供
         */
        private String result;
        /**
         * 返回音频二进制信息, 仅异常时提供
         */
        private byte[] data;
        /**
         * 音频转存bos bucket
         */
        private String bucket;
        /**
         * 音频转存bos key
         */
        private String key;
        /**
         * 预览链接
         */
        private String preSignUrl;
        /**
         * 语音时长
         */
        private Integer duration;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPreSignUrl() {
            return preSignUrl;
        }

        public void setPreSignUrl(String preSignUrl) {
            this.preSignUrl = preSignUrl;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "SingleText2AudioResponse{" +
                    "text='" + text + '\'' +
                    ", result='" + result + '\'' +
                    ", data=" + Arrays.toString(data) +
                    ", bucket='" + bucket + '\'' +
                    ", key='" + key + '\'' +
                    ", preSignUrl='" + preSignUrl + '\'' +
                    ", duration=" + duration +
                    '}';
        }
    }

}
