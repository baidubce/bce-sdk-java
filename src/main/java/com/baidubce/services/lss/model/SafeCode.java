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

public class SafeCode {
    private String playerType = null;

    private String safeCode = null;

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public SafeCode withPlayerType(String playerType) {
        this.playerType = playerType;
        return this;
    }

    public String getSafeCode() {
        return safeCode;
    }

    public void setSafeCode(String safeCode) {
        this.safeCode = safeCode;
    }

    public SafeCode withSafeCode(String safeCode) {
        this.safeCode = safeCode;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class SafeCode {\n");
        sb.append("    playerType: ").append(playerType).append("\n");
        sb.append("    safeCode: ").append(safeCode).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
