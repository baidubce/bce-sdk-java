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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * The request of text to audio.
 */
public class Text2AudioRequest extends AbstractBceRequest {

    /**
     * 文字内容，支持多段文字，最多100段文字，每段文字最大长度2048
     */
    private List<String> texts;
    /**
     * 语速，取值0-15，默认为5中语速
     */
    private int spd = 5;
    /**
     * 音调，取值0-15，默认为5中语调
     */
    private int pit = 5;
    /**
     * 音量，取值0-15，默认为5中音量
     */
    private int vol = 5;
    /**
     * 发音人类型
     */
    private Per per;
    /**
     * 保存的bucket，若不填默认使用MatlibConfig的bucket
     */
    private String bucket;
    /**
     * 试听，true表示为试听请求，将直接返回音频base64，不做存储
     */
    private boolean audition;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public Text2AudioRequest() {
    }

    public Text2AudioRequest(List<String> texts, Per per) {
        this.texts = texts;
        this.per = per;
    }

    public Text2AudioRequest(List<String> texts, int spd, int pit, int vol, Per per) {
        this.texts = texts;
        this.spd = spd;
        this.pit = pit;
        this.vol = vol;
        this.per = per;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getPit() {
        return pit;
    }

    public void setPit(int pit) {
        this.pit = pit;
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public Per getPer() {
        return per;
    }

    public void setPer(Per per) {
        this.per = per;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public boolean isAudition() {
        return audition;
    }

    public void setAudition(boolean audition) {
        this.audition = audition;
    }

}
