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
package com.baidubce.services.bos.model;

import java.util.List;

/**
 * The Conditon For Bucket Acl and Bucket Lifecycle.
 * conditonTime for Bucket Lifecycle, the time restrictions, Implemented by the defined dateGreaterThan.
 * referer for Bucket Acl, Identifies the referer that is granted access permission.
 * ipAddress for Bucket Acl, Identifies the ip that is granted access permission.
 */
public class Condition {

    /**
     * the conditonTime of Bucket Lifecycle.
     */
    private Time time;

    /**
     * the referer of Bucket Acl.
     */
    private Referer referer;

    /**
     * the ipAddress of Bucket Acl, just for Bucket Acl json
     */
    private List<String> ipAddress;

    /**
     * Gets time of Bucket Lifecycle.
     * @return time of Bucket Lifecycle.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets time of Bucket Lifecycle.
     * @param time The time of Bucket Lifecycle.
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * Sets time of Bucket Lifecycle.
     * @param time The time of Bucket Lifecycle.
     * @return this object.
     */
    public Condition withTime(Time time) {
        this.setTime(time);
        return this;
    }

    /**
     * Gets ipAddress of Bucket Acl, just for Bucket Acl json
     * @return ipAddress of Bucket Acl.
     */
    public List<String> getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets ipAddress of Bucket Acl, just for Bucket Acl json
     * @param ipAddress The ipAddress of Bucket Acl.
     */
    public void setIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Sets ipAddress of Bucket Acl, just for Bucket Acl json
     * @param ipAddress The ipAddress of Bucket Acl.
     * @return this object.
     */
    public Condition withIpAddress(List<String> ipAddress) {
        this.setIpAddress(ipAddress);
        return this;
    }
    /**
     * Gets referer of Bucket Acl.
     * @return referer of Bucket Acl.
     */
    public Referer getReferer() {
        return referer;
    }

    /**
     * Sets referer of Bucket Acl.
     * @param referer The referer of Bucket Acl.
     */
    public void setReferer(Referer referer) {
        this.referer = referer;
    }

    /**
     * Sets referer of Bucket Acl.
     * @param referer The referer of Bucket Acl.
     * @return this object.
     */
    public Condition withReferer(Referer referer) {
        this.setReferer(referer);
        return this;
    }

    @Override
    public String toString() {
        return "Condition{"
                + "time=" + time
                + ", referer=" + referer
                + ", ipAddress=" + ipAddress
                + '}';
    }
}

