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

package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ListRealtimeStreamStatisticsRequest extends AbstractBceRequest {

    private String playDomain = null;

    private String app = null;

    public ListRealtimeStreamStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getPlayDomain() {
        return playDomain;
    }

    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    public ListRealtimeStreamStatisticsRequest withPlayDomain(String playDomain) {
        this.playDomain = playDomain;
        return this;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public ListRealtimeStreamStatisticsRequest withApp(String app) {
        this.app = app;
        return this;
    }

    @Override
    public String toString() {
        return "ListRealtimeStreamStatisticsRequest{"
                + "playDomain='" + playDomain + '\''
                + ", app='" + app + '\''
                + '}';
    }

}
