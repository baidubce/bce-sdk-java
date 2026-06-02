package com.baidubce.services.bls;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bls.model.logrecord.*;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BlsClientTest {
    public static final Logger logger = LoggerFactory.getLogger(BlsClientTest.class);

    private static final String ak = "";

    private static final String sk = "";

    public static final String endpoint = "bls-log.bj.baidubce.com";

    private BlsClient blsClient;

    private static final String prject = "default";

    public static final String logStoreName = "bls-logstorename";

    public static final String logStreamName = "bls-logstreamname";

    public static final String startTime = "2025-03-17T02:04:05Z";

    public static final String endTime = "2025-03-17T15:04:05Z";


    @Before
    public void setUp() {
        BlsClientConfiguration config = new BlsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        blsClient = new BlsClient(config);
    }

    private void printResult(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPullLogRecord_200() {
        PullLogRecordRequest request = PullLogRecordRequest.builder().
                project(prject).
                logStoreName(logStoreName).
                startDateTime(startTime).
                endDateTime(endTime).
                limit(10).build();
        PullLogRecordResponse resp = blsClient.pullLogRecord(request);
        printResult("pullLogRecord", resp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPullLogRecord_exception() {
        PullLogRecordRequest request = PullLogRecordRequest.builder().
                logStoreName(logStoreName).
                endDateTime(endTime).
                limit(1000).build();
        PullLogRecordResponse resp = blsClient.pullLogRecord(request);
        printResult("pullLogRecord", resp);
    }

    @Test
    public void testQueryLogRecord_200() {
        QueryLogRecordRequest request1 = QueryLogRecordRequest.builder() .
                project(prject) .
                logStoreName(logStoreName).
                query("select count(1)").
                startDateTime(startTime).
                endDateTime(endTime).
                sort("desc").
                build();
        QueryLogRecordResponse resp1 = blsClient.queryLogRecord(request1);
        printResult("queryLogRecord", resp1);

        QueryLogRecordRequest request2 = QueryLogRecordRequest.builder() .
                project(prject) .
                logStoreName(logStoreName).
                query("match *").
                startDateTime(startTime).
                limit(10).
                marker("CJq1iKXaMhDcgJbAiKXaMhgK").
                endDateTime(endTime).build();
        QueryLogRecordResponse resp2 = blsClient.queryLogRecord(request2);
        printResult("queryLogRecord", resp2);
    }

    @Test(expected = BceServiceException.class)
    public void testQueryLogRecord_exception() {
        QueryLogRecordRequest request1 = QueryLogRecordRequest.builder() .
                project("unknown") .
                logStoreName(logStoreName).
                query("select count(1)").
                startDateTime(startTime).
                endDateTime(endTime).
                sort("desc").
                build();
        QueryLogRecordResponse resp1 = blsClient.queryLogRecord(request1);
        printResult("queryLogRecord", resp1);
    }

    @Test
    public void testQueryLogHistogram() {
        QueryLogHistogramRequest request = QueryLogHistogramRequest.builder().
                project(prject).
                logStoreName(logStoreName).
                logStreamName(logStreamName).
                query("match *").
                startDateTime(startTime).
                endDateTime(endTime).
                build();
        QueryLogHistogramResponse resp1 = blsClient.queryLogHistogram(request);
        printResult("queryLogHistogram", resp1);
    }

    @Test
    public void testPushLogRecord() {
        List<LogRecord> logRecords = new ArrayList<>();
        logRecords.add(new LogRecord(1742281309000L, "{\"key1\":\"this is yu?mdssssay\", \"key3\": \"inf\" }"));
        List<LogTag> logTags = new ArrayList<>();
        logTags.add(new LogTag("tag1", "valu"));
        logTags.add(new LogTag("tag2", "valu"));
        PushLogRecordRequest request = PushLogRecordRequest.builder().
                project(prject).
                logStoreName(logStoreName).
                logStreamName(logStreamName).
                type(LogType.JSON).
                logRecords(logRecords).
                logTags(logTags).
                build();

        blsClient.pushLogRecord(request);

    }


}
