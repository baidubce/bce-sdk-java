/*
 * Copyright 2019-2021 Baidu, Inc.
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
package com.baidubce.services.bvw.model.matlib;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.HashMap;

/**
 * The response of getting material fpreset rom material library.
 */
@Data
public class MaterialPresetGetResponse extends AbstractBceResponse {

    /**
     * The material preset id
     */
    private String id;
    /**
     * The statue of processing media source, contains PROCESSING/FAILED/FINISHED.
     */
    private String status;
    /**
     * The user id
     */
    private String userId;
    /**
     * The material preset title.
     */
    private String title;
    /**
     * The material preset tag.
     */
    private String tag;
    /**
     * The material preset type.
     */
    private String type;
    /**
     * The source type.
     */
    private String sourceType;
    /**
     * The ids of preview materials.
     */
    private HashMap<String, String> previewMaterialIds;
    /**
     * The buckets of preview materials.
     */
    private HashMap<String, String> previewBucket;
    /**
     * The keys of preview materials.
     */
    private HashMap<String, String> previewKeys;
    /**
     * The signed urls of preview materials.
     */
    private HashMap<String, String> previewUrls;
    /**
     * The id of material.
     */
    private String materialId;
    /**
     * The bucket of material.
     */
    private String bucket;
    /**
     * The key of material.
     */
    private String key;
    /**
     * The url of material.
     */
    private String sourceUrl;
    /**
     * The config of material preset.
     */
    private String config;
    /**
     * The create time  of material preset.
     */
    private String createTime;
    /**
     * The update time  of material preset.
     */
    private String updateTime;
}
