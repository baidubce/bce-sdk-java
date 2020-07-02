/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.media;

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Batch delete media request.
 */
public class MediaBatchDeleteRequest extends AbstractBceRequest {

    /**
     * The medias, multiple media id spilt with comma.
     * eg. "mda-xxx,mda-yyy,mda-zzz"
     */
    private String mediaIds;

    /**
     *
     * @param mediaIdList
     * @return
     */
    public static MediaBatchDeleteRequest of(List<String> mediaIdList) {
        MediaBatchDeleteRequest batchDeleteRequest = new MediaBatchDeleteRequest();
        StringBuilder mediaIds = new StringBuilder();
        for (String mediaId : mediaIdList) {
            mediaIds.append(mediaId).append(",");
        }
        mediaIds.deleteCharAt(mediaIds.lastIndexOf(","));
        batchDeleteRequest.setMediaIds(mediaIds.toString());
        return batchDeleteRequest;
    }

    @Override
    public MediaBatchDeleteRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(String mediaIds) {
        this.mediaIds = mediaIds;
    }

}
