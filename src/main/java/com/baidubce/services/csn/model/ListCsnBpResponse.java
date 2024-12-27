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
package com.baidubce.services.csn.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.baidubce.services.tag.model.Tag;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListCsnBpResponse extends BaseBceResponse {
    /**
     * 包含查询结果的列表
     */
    private List<CsnBp> csnBps;

    /**
     * 标记查询的起始位置，若结果列表为空，此项不存在
     */
    private String marker;

    /**
     * true表示后面还有数据，false表示已经是最后一页
     */
    private Boolean isTruncated;

    /**
     * 获取下一页所需要传递的marker值。当isTruncated为false时，该域不出现
     */
    private String nextMarker;

    /**
     * 每页包含的最大数量
     */
    private Integer maxKeys;

    @Getter
    @Setter
    @ToString
    public static class CsnBp {
        private String csnBpId;
    
        private String name;
    
        private String bandwidth;
    
        private String usedBandwidth;
    
        private String csnId;
    
        private String interworkType;
    
        private String interworkRegion;
    
        private String status;
    
        private String paymentTiming;
    
        private String expiredTime;
    
        private String createTime;

        private List<Tag> tags;
    }

}