/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.bvw.model.matlib.timeline;

import com.baidubce.services.bos.BosClient;
import lombok.Data;


/**
 * The model of timeline material.
 */
@Data
public class TimelineMaterial {

    /**
     * The start time of material
     */
    private Double start;
    /**
     * The end time of material
     */
    private Double end;
    /**
     * The show start time of material
     */
    private Double showStart;
    /**
     * The show end time of material
     */
    private Double showEnd;
    /**
     * The duration of material
     */
    private Double duration;
    /**
     * The x-position of material
     */
    private Double xpos;
    /**
     * The y-position of material
     */
    private Double ypos;
    /**
     * The show width of material
     */
    private Integer width;
    /**
     * The show height of material
     */
    private Integer height;
    /**
     * The mediainfo of material
     */
    private TimelineMediaInfo mediaInfo;
    /**
     * The type of material
     */
    private String type;
    /**
     * The uid of material
     */
    private String uid;
    /**
     * The name of material
     */
    private String name = "";
    /**
     * The extra info of material
     */
    private Object extInfo;


    /**
     * Calculate url for BOS object of timeline material.
     *
     * @param bosClient User's BOS client.
     */
    public void generalSignedUrl(BosClient bosClient) {
        if (mediaInfo != null) {
            mediaInfo.generalSignedUrl(bosClient);
        }
    }
}