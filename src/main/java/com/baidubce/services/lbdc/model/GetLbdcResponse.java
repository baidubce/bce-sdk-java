/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.lbdc.model;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLbdcResponse extends BaseBceResponse {
    /**
     * 集群id
     */
    private String id;

    /**
     * 集群名称
     */
    private String name;

    /**
     * 集群类型
     */
    private String type;

    /**
     * 集群状态
     */
    private String status;

    /**
     * 集群性能容量
     */
    private String ccuCount;

    /**
     * 集群创建时间
     */
    private String createTime;

    /**
     * 集群失效时间
     */
    private String expireTime;

    /**
     * 并发连接数
     */
    private String totalConnectCount;

    /**
     * 新建连接速度
     */
    private String newConnectCps;

    /**
     * 网络输入带宽
     */
    private String networkInBps;

    /**
     * 网络输出带宽
     */
    private String networkOutBps;

    /**
     * https的qps
     */
    private String httpsQps;

    /**
     * http的qps
     */
    private String httpQps;

    /**
     * http新建速度
     */
    private String httpNewConnectCps;

    /**
     * https新建速度
     */
    private String httpsNewConnectCps;

    /**
     * ssl新建速度
     */
    private String sslNewConnectCps;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setCcuCount(String ccuCount) {
        this.ccuCount = ccuCount;
    }

    public String getCcuCount() {
        return this.ccuCount;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public void setTotalConnectCount(String totalConnectCount) {
        this.totalConnectCount = totalConnectCount;
    }

    public String getTotalConnectCount() {
        return this.totalConnectCount;
    }

    public void setNewConnectCps(String newConnectCps) {
        this.newConnectCps = newConnectCps;
    }

    public String getNewConnectCps() {
        return this.newConnectCps;
    }

    public void setNetworkInBps(String networkInBps) {
        this.networkInBps = networkInBps;
    }

    public String getNetworkInBps() {
        return this.networkInBps;
    }

    public void setNetworkOutBps(String networkOutBps) {
        this.networkOutBps = networkOutBps;
    }

    public String getNetworkOutBps() {
        return this.networkOutBps;
    }

    public void setHttpsQps(String httpsQps) {
        this.httpsQps = httpsQps;
    }

    public String getHttpsQps() {
        return this.httpsQps;
    }

    public void setHttpQps(String httpQps) {
        this.httpQps = httpQps;
    }

    public String getHttpQps() {
        return this.httpQps;
    }

    public void setHttpNewConnectCps(String httpNewConnectCps) {
        this.httpNewConnectCps = httpNewConnectCps;
    }

    public String getHttpNewConnectCps() {
        return this.httpNewConnectCps;
    }

    public void setHttpsNewConnectCps(String httpsNewConnectCps) {
        this.httpsNewConnectCps = httpsNewConnectCps;
    }

    public String getHttpsNewConnectCps() {
        return this.httpsNewConnectCps;
    }

    public void setSslNewConnectCps(String sslNewConnectCps) {
        this.sslNewConnectCps = sslNewConnectCps;
    }

    public String getSslNewConnectCps() {
        return this.sslNewConnectCps;
    }

    @Override
    public String toString() {
        return "GetLbdcResponse{"
                + "id=" + id + "\n"
                + "name=" + name + "\n"
                + "type=" + type + "\n"
                + "status=" + status + "\n"
                + "ccuCount=" + ccuCount + "\n"
                + "createTime=" + createTime + "\n"
                + "expireTime=" + expireTime + "\n"
                + "totalConnectCount=" + totalConnectCount + "\n"
                + "newConnectCps=" + newConnectCps + "\n"
                + "networkInBps=" + networkInBps + "\n"
                + "networkOutBps=" + networkOutBps + "\n"
                + "httpsQps=" + httpsQps + "\n"
                + "httpQps=" + httpQps + "\n"
                + "httpNewConnectCps=" + httpNewConnectCps + "\n"
                + "httpsNewConnectCps=" + httpsNewConnectCps + "\n"
                + "sslNewConnectCps=" + sslNewConnectCps + "\n"
                + "}";
    }

}