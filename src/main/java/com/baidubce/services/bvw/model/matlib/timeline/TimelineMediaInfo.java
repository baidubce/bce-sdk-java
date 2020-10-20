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
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.baidubce.services.bvw.constants.MatlibConstants.PREFIX_360P;
import static com.baidubce.services.bvw.constants.MatlibConstants.PREFIX_AUDIO;
import static com.baidubce.services.bvw.constants.MatlibConstants.SUFFIX_AUDIO;
import static com.baidubce.services.bvw.constants.MatlibConstants.SUFFIX_VIDEO;


/**
 * The model of material mediainfo.
 */
@Data
public class TimelineMediaInfo {

    /**
     * The file type of material
     */
    private String fileType;
    /**
     * The source type of material
     */
    private String sourceType;
    /**
     * The source url of material
     */
    private String sourceUrl;
    /**
     * The url of material's audio
     */
    private String audioUrl;
    /**
     * The BOS bucket of material
     */
    private String bucket;
    /**
     * The BOS key of material
     */
    private String key;
    /**
     * The BOS key of material's audio
     */
    private String audioKey;
    /**
     * The instanceId of material (for preprocessing)
     */
    private String instanceId;
    /**
     * The cover of material
     */
    private String coverImage;
    /**
     * The duration of material
     */
    private Double duration;
    /**
     * The width of material
     */
    private Integer width;
    /**
     * The height of material
     */
    private Integer height;
    /**
     * The error message of material
     */
    private String errmsg;
    /**
     * The status of material
     */
    private String status;
    /**
     * The progress of material
     */
    private String progress;
    /**
     * The action of material
     */
    private String action;
    /**
     * The size of material
     */
    private Integer size;
    /**
     * The name of material
     */
    private String name = "";
    /**
     * The thumbnail prefix of material
     */
    private String thumbnailPrefix;
    /**
     * The thumbnail keys of material
     */
    private List<String> thumbnailKeys;
    /**
     * The thumbnail urls of material
     */
    private List<String> thumbnailList = new ArrayList<String>();
    /**
     * The subtitle keys of material
     */
    private List<String> subtitleKeys;
    /**
     * The media id of material
     */
    private String mediaId = "";

    /**
     *  offstandard is true when material has not been preprocessed
     */
    private boolean offstandard;

    /**
     * Calculate url for BOS object of material mediainfo.
     *
     * @param bosClient User's BOS client.
     */
    public void generalSignedUrl (BosClient bosClient) {

        sourceUrl = "";
        audioUrl = "";
        if (!StringUtils.isBlank(bucket) && !StringUtils.isBlank(key)) {
            String tmpKey = addResolutionPrefix(fileType,  key, offstandard);
            sourceUrl = bosClient.generatePresignedUrl(bucket, tmpKey, 7200).toString();
            if (StringUtils.isBlank(audioKey)) {
                audioKey = addResolutionPrefix("audio", key);
            }
            audioUrl = bosClient.generatePresignedUrl(bucket, audioKey, 7200).toString();
        }
        if (!StringUtils.isBlank(bucket) && thumbnailKeys != null) {
            thumbnailPrefix = thumbnailPrefix == null ? "" : thumbnailPrefix;
            thumbnailList = new ArrayList<String>();
            for (String key : thumbnailKeys) {
                String thumbnailUrl = bosClient.generatePresignedUrl(bucket,
                            thumbnailPrefix + key, 7200).toString();
                thumbnailList.add(thumbnailUrl);
            }
        }
        // 设置封面图
        coverImage = "";
        if ("image".equals(fileType)) {
            coverImage = sourceUrl;
        } else if ("video".equals(fileType)
                    && thumbnailList != null
                    && thumbnailList.size() > 0) {
            int index = thumbnailList.size() * 2 / 3;
            coverImage = thumbnailList.get(index);
        }
    }

    /**
     * add prefix for object name
     *
     * @param type file type.
     * @param key object key.
     * @param offstandard whether or not been preprocessed.
     */
    private String addResolutionPrefix(String type, String key, Boolean offstandard) {
        if (offstandard) {
            return key;
        }
        return addResolutionPrefix(type, key);
    }

    /**
     * add prefix for object name
     *
     * @param type file type.
     * @param key object key.
     */
    private String addResolutionPrefix(String type, String key) {
        if (type == null) {
            return key;
        }
        if ("video".equals(type)) {
            return addResolutionPrefix(PREFIX_360P, SUFFIX_VIDEO, key);
        } else if ("audio".equals(type)) {
            return addResolutionPrefix(PREFIX_AUDIO, SUFFIX_AUDIO, key);
        } else {
            return key;
        }
    }

    /**
     * add prefix for object name
     *
     * @param prefix prefix of key.
     * @param suffix suffix of key.
     * @param key object key.
     */
    private String addResolutionPrefix(String prefix, String suffix, String key) {
        if (key == null) {
            return null;
        }
        String result = key;
        if (prefix != null) {
            int index = result.lastIndexOf(".");
            if (-1 != index) {
                result = result.substring(0, index);
            }
            index = result.lastIndexOf("/");
            if (-1 == index) {
                result = prefix + "/" + result;
            } else if (0 == index) {
                result = prefix + "/" + result.substring(1);
            } else {
                result = result.substring(0, index + 1) + prefix + "/"
                            + result.substring(index + 1);
            }
            result = result + suffix;
        }
        return result;
    }

}