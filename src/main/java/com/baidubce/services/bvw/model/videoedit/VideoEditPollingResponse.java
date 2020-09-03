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
package com.baidubce.services.bvw.model.videoedit;

import com.baidubce.model.AbstractBceResponse;

public class VideoEditPollingResponse extends AbstractBceResponse {
    /**
     * error number , 0 is OK
     */
    private int errorno;
    /**
     * error message
     */
    private String errmsg;
    /**
     * Create edit video's data content
     */
    private PollingDataModel data;

    public VideoEditPollingResponse() {
    }

    public VideoEditPollingResponse(int errorno, String errmsg, PollingDataModel data) {
        this.errorno = errorno;
        this.errmsg = errmsg;
        this.data = data;
    }

    public int getErrorno() {
        return errorno;
    }

    public void setErrorno(int errorno) {
        this.errorno = errorno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public PollingDataModel getData() {
        return data;
    }

    public void setData(PollingDataModel data) {
        this.data = data;
    }

}
