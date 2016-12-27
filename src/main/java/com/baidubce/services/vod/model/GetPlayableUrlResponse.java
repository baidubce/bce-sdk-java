/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.vod.model;

import com.baidubce.model.AbstractBceResponse;

public class GetPlayableUrlResponse extends AbstractBceResponse {
    private Boolean success;
    private PlayableUrl result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PlayableUrl getResult() {
        return result;
    }

    public void setResult(PlayableUrl result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetPlayableUrlResponse { \n");
        sb.append(" success = ").append(success).append("\n");
        sb.append(" result = ").append(result).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}