/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.ocr.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by lvsiyuan on 16/11/23.
 */
public class IdcardRecognitionResponse extends AbstractBceResponse {

    /*
     * The unique log id.
     */
    @JsonProperty("log_id")
    private BigInteger logId;

    public BigInteger getLogId() {
        return logId;
    }

    public void setLogId(BigInteger logId) {
        this.logId = logId;
    }

    /*
     * The number represents image direction. (-1/0/1/2/3)
     */
    private Integer direction;

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    /*
     * The number of words detected.
     */
    @JsonProperty("words_result_num")
    private Long resultNum;

    public Long getResultNum() {
        return resultNum;
    }

    public void setResultNum(Long resultNum) {
        this.resultNum = resultNum;
    }

    /*
     * The words detected.
     */
    @JsonProperty("words_result")
    private Map<String, IdcardWordsResult> result;

    public Map<String, IdcardWordsResult> getResult() {
        return result;
    }

    public void setResult(Map<String, IdcardWordsResult> result) {
        this.result = result;
    }
}
