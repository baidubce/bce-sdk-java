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

/**
 * Reprsents the anti-leech settings. Currently only refer & single ip restriction are supported.
 */
public class AntiLeech {
    private Refer refer = null;

    private IP ip = null;

    /**
     * Returns the refer
     *
     * @return the refer
     */
    public Refer getRefer() {
        return refer;
    }

    /**
     * Sets the refer
     *
     * @param refer the refer
     */
    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    /**
     * Sets the refer and returns this object.
     *
     * @param refer the refer
     * @return this object
     */
    public AntiLeech withRefer(Refer refer) {
        this.refer = refer;
        return this;
    }

    /**
     * Returns the IP
     *
     * @return the IP
     */
    public IP getIp() {
        return ip;
    }

    /**
     * Sets the IP.
     *
     * @param ip the IP
     */
    public void setIp(IP ip) {
        this.ip = ip;
    }

    /**
     * Sets the IP and returns this object
     *
     * @param ip the IP
     * @return this object
     */
    public AntiLeech withIp(IP ip) {
        this.ip = ip;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class AntiLeech {\n");
        sb.append("    refer: ").append(refer).append("\n");
        sb.append("    ip: ").append(ip).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
