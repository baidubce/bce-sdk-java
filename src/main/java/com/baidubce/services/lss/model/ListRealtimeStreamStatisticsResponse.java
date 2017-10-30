package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by zhengfeng on 17/10/17.
 */
public class ListRealtimeStreamStatisticsResponse extends AbstractBceResponse {

    List<RealTimeStreamStatistics> realTimeStreamStatisticsList = null;

    public List<RealTimeStreamStatistics> getRealTimeStreamStatisticsList() {
        return realTimeStreamStatisticsList;
    }

    public void setRealTimeStreamStatisticsList(List<RealTimeStreamStatistics> realTimeStreamStatisticsList) {
        this.realTimeStreamStatisticsList = realTimeStreamStatisticsList;
    }

    @Override
    public String toString() {
        return "ListRealtimeStreamStatisticsResponse{"
                + "realTimeStreamStatisticsList=" + realTimeStreamStatisticsList
                + '}';
    }

}
