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

public class Encryption {
    private String strategy = null;
    private String aesKey   = null;

    /**
     * Fixed-表示固定密钥加密
     **/
    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Encryption withStrategy(String strategy) {
        this.strategy = strategy;
        return this;
    }

    /**
     * AES128加密密钥
     **/
    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public Encryption withAesKey(String aesKey) {
        this.aesKey = aesKey;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Encryption {\n");

        sb.append("    strategy: ").append(strategy).append("\n");
        sb.append("    aesKey: ").append(aesKey).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
