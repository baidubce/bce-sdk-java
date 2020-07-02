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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * MVS search video by video request.
 */
public class SearchVideoByVideoRequest extends AbstractBceRequest {
    private String source;
    private String notification;
    private String description;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String source;
        private String notification;
        private String description;

        public Builder withSource(String source) {
            this.source = source;
            return this;
        }

        public Builder withNotification(String notification) {
            this.notification = notification;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SearchVideoByVideoRequest build() {
            SearchVideoByVideoRequest searchVideoByVideoRequest = new SearchVideoByVideoRequest();
            searchVideoByVideoRequest.setSource(this.source);
            searchVideoByVideoRequest.setNotification(this.notification);
            searchVideoByVideoRequest.setDescription(this.description);
            return searchVideoByVideoRequest;
        }
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SearchVideoByVideoRequest{" +
                "source='" + source + '\'' +
                ", notification='" + notification + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
