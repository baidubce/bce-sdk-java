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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import java.util.List;

/**
 * The model of material pair.
 */
@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
public class MediaPair {

    /**
     * The key of track
     */
    private String key;

    /**
     * The material of track
     */
    private List<TimelineMaterial> list;

    /**
     * The master flag
     */
    private String isMaster;

    /**
     * Calculate url for BOS object of material pair.
     *
     * @param bosClient User's BOS client.
     */
    public void generalSignedUrl(BosClient bosClient) {
        if (list != null) {
            for (TimelineMaterial material : list) {
                material.generalSignedUrl(bosClient);
            }
        }
    }

}