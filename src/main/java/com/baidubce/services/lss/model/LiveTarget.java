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

public class LiveTarget {
    private String bosBucket = null;

    private String userDomain = null;

    public String getBosBucket() {
        return bosBucket;
    }

    public void setBosBucket(String bosBucket) {
        this.bosBucket = bosBucket;
    }

    public LiveTarget withBosBucket(String bosBucket) {
        this.bosBucket = bosBucket;
        return this;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public LiveTarget withUserDomain(String userDomain) {
        this.userDomain = userDomain;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveTarget {\n");
        sb.append("    bosBucket: ").append(bosBucket).append("\n");
        sb.append("    userDomain: ").append(userDomain).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
