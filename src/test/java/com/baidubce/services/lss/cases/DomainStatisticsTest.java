package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.GetAllDomainsBandwidthResponse;
import com.baidubce.services.lss.model.GetAllDomainsBandwidthResponseV2;
import com.baidubce.services.lss.model.GetAllDomainsPlayCountResponse;
import com.baidubce.services.lss.model.GetAllDomainsStatisticsRequest;
import com.baidubce.services.lss.model.GetAllDomainsStatisticsRequestV2;
import com.baidubce.services.lss.model.GetAllDomainsTrafficResponse;
import com.baidubce.services.lss.model.GetDomainStatisticsRequest;
import com.baidubce.services.lss.model.GetDomainStatisticsResponse;
import com.baidubce.services.lss.model.GetDomainSummaryStatisticsRequest;
import com.baidubce.services.lss.model.GetDomainSummaryStatisticsResponse;
import com.baidubce.services.lss.model.GetOneDomainBandwidthResponse;
import com.baidubce.services.lss.model.GetOneDomainBandwidthResponseV2;
import com.baidubce.services.lss.model.GetOneDomainPlayCountResponse;
import com.baidubce.services.lss.model.GetOneDomainStatisticsRequest;
import com.baidubce.services.lss.model.GetOneDomainStatisticsRequestV2;
import com.baidubce.services.lss.model.GetOneDomainTrafficResponse;
import com.baidubce.services.lss.model.GetStreamStatisticsRequest;
import com.baidubce.services.lss.model.GetStreamStatisticsResponse;
import com.baidubce.services.lss.model.ListDomainStatisticsRequest;
import com.baidubce.services.lss.model.ListDomainStatisticsResponse;
import com.baidubce.services.lss.model.ListRealtimeStreamStatisticsRequest;
import com.baidubce.services.lss.model.ListRealtimeStreamStatisticsResponse;
import com.baidubce.services.lss.model.ListStreamResponse;
import com.baidubce.services.lss.model.ListStreamStatisticsRequest;
import com.baidubce.services.lss.model.ListStreamStatisticsResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by wuyafei on 16/10/19.
 */
public class DomainStatisticsTest extends AbstractLssTest {

    private String domain = "zhengfengplay.baidudomainbcd.com";

    private String app = "live";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testGetDomainStatistics() {
        GetDomainStatisticsRequest request = new GetDomainStatisticsRequest();
        request.withDomain(domain).withStartDate("20161018").withEndDate("20161019").withAggregate(Boolean.TRUE);
        GetDomainStatisticsResponse response = lssClient.getDomainStatistics(request);
        assertEquals(response.getDomain(), domain);
        assertEquals(response.getAggregate().getDownstreamInByte() == 0, true);
        assertEquals(response.getAggregate().getPlayCount() == 0, true);
        assertNull(response.getStatistics());
        request.setAggregate(Boolean.FALSE);
        response = lssClient.getDomainStatistics(request);
        assertNull(response.getAggregate());
        assertEquals(response.getStatistics().size(), 0);
    }

    @Test
    public void testGetDomainSummaryStatistics() {
        GetDomainSummaryStatisticsRequest request = new GetDomainSummaryStatisticsRequest();
        request.withStartTime("2016-10-18T01:00:00Z").withEndTime("2016-10-19T00:00:00Z");
        GetDomainSummaryStatisticsResponse response = lssClient.getDomainSummaryStatistics(request);
        assertEquals(response.getSummary().getPlayCount() == 0, true);
        assertEquals(response.getSummary().getDurationInMinute() == 0, true);
        assertEquals(response.getSummary().getDownStreamInByte() == 0, true);
        assertEquals(response.getEndTime(), "2016-10-19T00:00:00Z");
    }

    @Test
    public void testGetAllDomainsPlayCount() {
        GetAllDomainsStatisticsRequest request = new GetAllDomainsStatisticsRequest();
        request.withStartTime("2016-10-17T16:00:00Z").withEndTime("2016-10-19T00:00:00Z").withTimeInterval("LONG_TERM");
        GetAllDomainsPlayCountResponse response = lssClient.getAllDomainsPlayCount(request);
        assertEquals(response.getTimeInterval(), "LONG_TERM");
        assertEquals(response.getFlvStatistics().size(), 2);
        assertEquals(response.getHlsStatistics().size(), 2);
        assertEquals(response.getRtmpStatistics().size(), 2);
        assertEquals(response.getFlvStatistics().get(0).getPlayCount() == 0, true);
        assertEquals(response.getHlsStatistics().get(0).getPlayCount() == 0, true);
        assertEquals(response.getRtmpStatistics().get(0).getPlayCount() == 0, true);
    }

    @Test
    public void testGetOneDomainPlayCount() {
        GetOneDomainStatisticsRequest request = new GetOneDomainStatisticsRequest();
        request.withDomain(domain).withStartTime("2016-09-18T00:00:00Z")
                .withEndTime("2016-09-18T08:00:00Z").withTimeInterval("MID_TERM");
        GetOneDomainPlayCountResponse response = lssClient.getOneDomainPlayCount(request);
        assertEquals(response.getFlvStatistics().size(), 8);
        assertEquals(response.getRtmpStatistics().get(0).getPlayCount() == 0, true);
        assertEquals(response.getHlsStatistics().get(0).getTimestamp(), "2016-09-18T00:00:00Z");
    }

    @Test
    public void testGetAllDomainsBandwidth() {
        GetAllDomainsStatisticsRequest request = new GetAllDomainsStatisticsRequest();
        request.withStartTime("2016-10-18T00:00:00Z")
                .withEndTime("2016-10-18T00:20:00Z").withTimeInterval("SHORT_TERM");
        GetAllDomainsBandwidthResponse response = lssClient.getAllDomainsBandwidth(request);
        assertEquals(response.getTimeInterval(), "SHORT_TERM");
        assertEquals(response.getStatistics().size(), 4);
        assertEquals(response.getStatistics().get(0).getBandwidthInBps() == 0, true);
        assertEquals(response.getStatistics().get(0).getTimestamp(), "2016-10-18T00:00:00Z");
    }

    @Test
    public void testGetOneDomainBandwidth() {
        GetOneDomainStatisticsRequest request = new GetOneDomainStatisticsRequest();
        request.withDomain(domain).withStartTime("2016-10-18T00:00:00Z")
                .withEndTime("2016-10-18T00:20:00Z").withTimeInterval("SHORT_TERM");
        GetOneDomainBandwidthResponse response = lssClient.getOneDomainBandwidth(request);
        assertEquals(response.getTimeInterval(), "SHORT_TERM");
        assertEquals(response.getStatistics().size(), 4);
        assertEquals(response.getStatistics().get(0).getBandwidthInBps() == 0, true);
        assertEquals(response.getStatistics().get(0).getTimestamp(), "2016-10-18T00:00:00Z");
    }

    @Test
    public void testGetAllDomainsTraffic() {
        GetAllDomainsStatisticsRequest request = new GetAllDomainsStatisticsRequest();
        request.withStartTime("2016-10-17T16:00:00Z").withEndTime("2016-10-20T04:00:00Z").withTimeInterval("LONG_TERM");
        GetAllDomainsTrafficResponse response = lssClient.getAllDomainsTraffic(request);
        assertEquals(response.getEndTime(), "2016-10-20T04:00:00Z");
        assertEquals(response.getStatistics().size(), 3);
        assertEquals(response.getStatistics().get(0).getTimestamp(), "2016-10-17T16:00:00Z");
        assertEquals(response.getStatistics().get(0).getDownstreamInByte() == 0, true);
    }

    @Test
    public void testGetAllDomainsBandwidthV2() {
        GetAllDomainsStatisticsRequestV2 request = new GetAllDomainsStatisticsRequestV2();
        request.withStartTime("2016-10-17T16:00:00Z").withEndTime("2016-10-20T04:00:00Z").withTimeInterval("LONG_TERM")
                .withAllDomain(true);
        GetAllDomainsBandwidthResponseV2 response = lssClient.getAllDomainsBandwidthV2(request);
        assertEquals(response.getEndTime(), "2016-10-20T04:00:00Z");
        assertEquals(response.getStatistics().size(), 3);
        assertEquals(response.getStatistics().get(0).getTimestamp(), "2016-10-17T16:00:00Z");
        assertEquals(response.getStatistics().get(0).getDownstreamInByte() == 0, true);
    }

    @Test
    public void testGetOneDomainTraffic() {
        GetOneDomainStatisticsRequest request = new GetOneDomainStatisticsRequest();
        request.withDomain(domain).withStartTime("2016-10-18T04:00:00Z").withTimeInterval("LONG_TERM");
        GetOneDomainTrafficResponse response = lssClient.getOneDomainTraffic(request);
        assertEquals(response.getDomain(), domain);
        assertEquals(response.getStatistics().get(0).getDownstreamInByte() == 0, true);
        assertEquals(response.getStatistics().get(0).getTimestamp(), "2016-10-18T16:00:00Z");

    }

    @Test
    public void testGetOneDomainsBandwidthV2() {
        GetOneDomainStatisticsRequestV2 request = new GetOneDomainStatisticsRequestV2();
        request.withStartTime("2016-10-17T16:00:00Z").withEndTime("2016-10-20T04:00:00Z").withTimeInterval("LONG_TERM")
                .withDomain(domain);
        GetOneDomainBandwidthResponseV2 response = lssClient.getOneDomainBandwidthV2(request);
        assertEquals(response.getEndTime(), "2016-10-20T04:00:00Z");
        assertEquals(response.getStatistics().size(), 3);
        assertEquals(response.getStatistics().get(0).getTimestamp(), "2016-10-17T16:00:00Z");
        assertEquals(response.getStatistics().get(0).getDownstreamInByte() == 0, true);
    }

    @Test
    public void testListDomainStatistics() {
        ListDomainStatisticsRequest request = new ListDomainStatisticsRequest();
        request.withStartTime("2016-10-18T04:00:00Z")
                .withEndTime("2016-10-20T04:00:00Z")
                .withKeywordType("domain")
                .withKeyword("test")
                .withOrderBy("duration desc");
        ListDomainStatisticsResponse response = lssClient.listDomainStatistics(request);
        assertEquals(response.getDomainStatisticsList().size(), 0);
    }

    @Test
    public void testListStreamStatistics() {
        ListStreamStatisticsRequest request = new ListStreamStatisticsRequest();
        request.withDomain(domain).withApp("test")
                .withStartTime("2016-10-18T04:00:00Z").withEndTime("2016-10-20T04:00:00Z")
                .withKeyword("stream").withOrderBy("downstream")
                .withPageNo(1).withPageSize(5);
        ListStreamStatisticsResponse response = lssClient.listStreamStatistics(request);
        assertEquals(response.getStreamStatisticsList().size(), 0);
        assertEquals(response.getTotalCount() == 0, true);
    }

    @Test
    public void testGetStreamStatistics() {
        GetStreamStatisticsRequest request = new GetStreamStatisticsRequest();
        ListStreamResponse listStreamResponse = lssClient.listStream(domain);
        if (listStreamResponse.getStreams().size() > 0) {
            String app = listStreamResponse.getStreams().get(0).getApp();
            String stream = listStreamResponse.getStreams().get(0).getPublish().getPushStream();
            request.withDomain(domain).withApp(app).withStream(stream).withAggregate(false)
                    .withStartDate("20161019").withEndDate("20161020");
            GetStreamStatisticsResponse response = lssClient.getStreamStatistics(request);
            assertEquals(response.getApp(), app);
            assertEquals(response.getStream(), stream);
            assertEquals(response.getStatistics().size(), 0);
        }
    }

    @Test
    public void testListRealtimeStreamStatistics() {
        ListRealtimeStreamStatisticsRequest request = new ListRealtimeStreamStatisticsRequest();
        request.withPlayDomain(domain).withApp(app);
        ListRealtimeStreamStatisticsResponse response = lssClient.listRealTimeStreamStatistics(request);

        assertEquals(response.getRealTimeStreamStatisticsList().size(), 0);
    }

}
