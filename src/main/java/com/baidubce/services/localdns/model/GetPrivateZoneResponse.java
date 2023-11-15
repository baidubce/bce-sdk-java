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
package com.baidubce.services.localdns.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPrivateZoneResponse extends BaseBceResponse {
    /**
     * Zone的ID
     */
    private String zoneId;

    /**
     * Zone的名称
     */
    private String zoneName;

    /**
     * 含有的解析记录总数
     */
    private Integer recordCount;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 关联的Vpc列表
     */
    private List<Vpc> bindVpcs;

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getRecordCount() {
        return this.recordCount;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setBindVpcs(List<Vpc> bindVpcs) {
        this.bindVpcs = bindVpcs;
    }

    public List<Vpc> getBindVpcs() {
        return this.bindVpcs;
    }

    @Override
    public String toString() {
        return "GetPrivateZoneResponse{"
                + "zoneId=" + zoneId + "\n"
                + "zoneName=" + zoneName + "\n"
                + "recordCount=" + recordCount + "\n"
                + "createTime=" + createTime + "\n"
                + "updateTime=" + updateTime + "\n"
                + "bindVpcs=" + bindVpcs + "\n"
                + "}";
    }

    public static class Vpc {
        private String vpcId;
    
        private String vpcName;
    
        private String vpcRegion;
    
        public void setVpcId(String vpcId) {
            this.vpcId = vpcId;
        }
    
        public String getVpcId() {
            return this.vpcId;
        }
    
        public void setVpcName(String vpcName) {
            this.vpcName = vpcName;
        }
    
        public String getVpcName() {
            return this.vpcName;
        }
    
        public void setVpcRegion(String vpcRegion) {
            this.vpcRegion = vpcRegion;
        }
    
        public String getVpcRegion() {
            return this.vpcRegion;
        }
    
        @Override
        public String toString() {
            return "Vpc{"
                    + "vpcId=" + vpcId + "\n"
                    + "vpcName=" + vpcName + "\n"
                    + "vpcRegion=" + vpcRegion + "\n"
                    + "}";
        }
    }

}