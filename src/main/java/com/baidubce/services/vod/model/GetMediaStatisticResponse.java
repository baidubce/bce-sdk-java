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
package com.baidubce.services.vod.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.Date;
import java.util.List;

/*
 * Gets the properties of specific media resource managed by VOD service.
 */
public class GetMediaStatisticResponse extends AbstractBceResponse {

    private String mediaId;


    private Date startTime;

    private Date endTime;

    private MediaStatisticsElement aggregate;

    private List<MediaStatisticsElement> statistics;


    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public MediaStatisticsElement getAggregate() {
        return aggregate;
    }

    public void setAggregate(MediaStatisticsElement aggregate) {
        this.aggregate = aggregate;
    }

    public List<MediaStatisticsElement> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<MediaStatisticsElement> statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GetMediaResourceResponse { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("  startTime = ").append(startTime).append("\n");
        sb.append("  endTime = ").append(endTime).append("\n");
        if (aggregate != null) {
            sb.append(aggregate).append("\n");
        }
        if (statistics != null) {
            for (MediaStatisticsElement item : statistics) {
                sb.append(item).append("\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

}