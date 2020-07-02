/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.mvs.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * MVS insert image response.
 */
public class InsertImageResponse extends AbstractBceResponse {
    private String status;
    private String lib;
    private String source;
    private String description;
    private MvsError error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MvsError getError() {
        return error;
    }

    public void setError(MvsError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "InsertImageResponse{" +
                "status='" + status + '\'' +
                ", lib='" + lib + '\'' +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                ", error=" + error +
                "} " + super.toString();
    }
}
