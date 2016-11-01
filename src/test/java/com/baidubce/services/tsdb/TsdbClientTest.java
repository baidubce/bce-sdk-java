package com.baidubce.services.tsdb;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.model.Aggregator;
import com.baidubce.services.tsdb.model.Datapoint;
import com.baidubce.services.tsdb.model.Filters;
import com.baidubce.services.tsdb.model.GetMetricsResponse;
import com.baidubce.services.tsdb.model.GetTagsResponse;
import com.baidubce.services.tsdb.model.Group;
import com.baidubce.services.tsdb.model.GroupBy;
import com.baidubce.services.tsdb.model.Query;
import com.baidubce.services.tsdb.model.QueryDatapointsResponse;
import com.baidubce.services.tsdb.model.ValueFilter;
import com.baidubce.services.tsdb.model.WriteDatapointsRequest;
import com.baidubce.util.JsonUtils;

public class TsdbClientTest {
    // qa-sandbox
    private static final String AK = "7b852806a751453caa6babd00bbcfaa5";
    private static final String SK = "146f895ad3c54552bf2a51721f7304b8";
    private static final String ENDPOINT = "http://testoutersdk.tsdb.iot.gz.baidubce.com:8011";
    private static final String METRIC = "test_java_sdk";
    private static final String TAG_KEY = "tag1";
    private static final String TAG_VALUE = "value1";
    private static final byte[] BYTE_VALUE_1 = new byte[] {0x01, 0x02, 0x00, 0x03};
    private static final byte[] BYTE_VALUE_2 = new byte[] {0x11, 0x12, 0x00, 0x13};
    private static final double DOUBLE_VALUE_1 = 1.1;
    private static final double DOUBLE_VALUE_2 = 1.2;
    private static final double DOUBLE_VALUE_3 = 1.3;
    private static final long LONG_VALUE_1 = 11;
    private static final long LONG_VALUE_2 = 12;
    private static final long LONG_VALUE_3 = 13;
    private static final String STRING_VALUE_1 = "str1";
    private static final String STRING_VALUE_2 = "str2";

    private TsdbClient tsdbClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        tsdbClient = new TsdbClient(config);
    }

    private void writeDoubleDatapointsTest(long baseTimestamp) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(baseTimestamp, DOUBLE_VALUE_1)
                .addDoubleValue(baseTimestamp + 1, DOUBLE_VALUE_2));

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    private void writeDoubleDatapointsTest(long baseTimestamp, Double... values) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE));
        for (Double value : values) {
            datapoints.get(0).addDoubleValue(baseTimestamp++, value);
        }

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    private void writeBytesDatapointsTest(long baseTimestamp) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addBytesValue(baseTimestamp, BYTE_VALUE_1)
                .addBytesValue(baseTimestamp + 1, BYTE_VALUE_2));

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    @Test
    public void getMetricsTest() throws Exception {
        writeDoubleDatapointsTest(System.currentTimeMillis());
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        GetMetricsResponse response = tsdbClient.getMetrics();

        assertThat(response, hasProperty("metrics"));
        assertThat(response.getMetrics(), is(notNullValue()));
        assertThat(response.getMetrics(), hasItem(METRIC));
    }

    @Test
    public void getTagsTest() throws Exception {
        writeDoubleDatapointsTest(System.currentTimeMillis());
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        GetTagsResponse response = tsdbClient.getTags(METRIC);

        assertThat(response, hasProperty("tags"));
        assertThat(response.getTags(), is(notNullValue()));
        assertThat(response.getTags().get(TAG_KEY), is(notNullValue()));
        assertThat(response.getTags().get(TAG_KEY), hasItem(TAG_VALUE));
    }

    @Test
    public void queryDatapointsTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4);

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void queryDatapointsWithDoubleValueFilterEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.EQUAL, DOUBLE_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getDoubleValue(), closeTo(DOUBLE_VALUE_1, 0.001));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithDoubleValueFilterNotEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.NOT_EQUAL, DOUBLE_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        List<Double> doubleValues = Arrays.asList(values.get(0).getDoubleValue(), values.get(1).getDoubleValue());
        assertThat(doubleValues, containsInAnyOrder(closeTo(DOUBLE_VALUE_2, 0.001), closeTo(DOUBLE_VALUE_3, 0.001)));
    }

    @Test
    public void queryDatapointsWithDoubleValueFilterLessTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.LESS, DOUBLE_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(0L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));
        assertThat(response.getResults().get(0).getGroups().get(0).getTimeAndValueList().size(), equalTo(0));
    }

    @Test
    public void queryDatapointsWithDoubleValueFilterLessOrEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.LESS_OR_EQUAL, DOUBLE_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getDoubleValue(), closeTo(DOUBLE_VALUE_1, 0.001));
    }

    @Test
    public void queryDatapointsWithDoubleValueFilterGreaterTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.GREATER, DOUBLE_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getDoubleValue(), closeTo(DOUBLE_VALUE_3, 0.001));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithDoubleValueFilterGreaterOrEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.GREATER_OR_EQUAL, DOUBLE_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(2L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        List<Double> doubleValues = Arrays.asList(values.get(0).getDoubleValue(), values.get(1).getDoubleValue());
        assertThat(doubleValues, containsInAnyOrder(closeTo(DOUBLE_VALUE_2, 0.001), closeTo(DOUBLE_VALUE_3, 0.001)));
    }

    private void writeLongDatapointsTest(long baseTimestamp, Long... values) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE));
        for (Long value : values) {
            datapoints.get(0).addLongValue(baseTimestamp++, value);
        }

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    @Test
    public void queryDatapointsWithLongValueFilterEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.EQUAL, LONG_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getLongValue(), equalTo(LONG_VALUE_1));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithLongValueFilterNotEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.NOT_EQUAL, LONG_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        List<Long> longValues = Arrays.asList(values.get(0).getLongValue(), values.get(1).getLongValue());
        assertThat(longValues, containsInAnyOrder(equalTo(LONG_VALUE_2), equalTo(LONG_VALUE_3)));
    }

    @Test
    public void queryDatapointsWithLongValueFilterLessTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.LESS, LONG_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(0L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));
        assertThat(response.getResults().get(0).getGroups().get(0).getTimeAndValueList().size(), equalTo(0));
    }

    @Test
    public void queryDatapointsWithDoubleLongFilterLessOrEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.LESS_OR_EQUAL, LONG_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getLongValue(), equalTo(LONG_VALUE_1));
    }

    @Test
    public void queryDatapointsWithLongValueFilterGreaterTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.GREATER, LONG_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getLongValue(), equalTo(LONG_VALUE_3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithLongValueFilterGreaterOrEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                        ValueFilter.createValueFilter(ValueFilter.GREATER_OR_EQUAL, LONG_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(2L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Number"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        List<Long> longValues = Arrays.asList(values.get(0).getLongValue(), values.get(1).getLongValue());
        assertThat(longValues, containsInAnyOrder(equalTo(LONG_VALUE_2), equalTo(LONG_VALUE_3)));
    }

    private void writeStringDatapointsTest(long baseTimestamp, String... values) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint().withMetric(METRIC).addTag(TAG_KEY, TAG_VALUE));
        for (String value : values) {
            datapoints.get(0).addStringValue(baseTimestamp++, value);
        }

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    @Test
    public void queryDatapointsWithStringValueFilterEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeStringDatapointsTest(baseTimestamp, STRING_VALUE_1, STRING_VALUE_2);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 2,
                ValueFilter.createValueFilter(ValueFilter.EQUAL, STRING_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("String"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getStringValue(), equalTo(STRING_VALUE_1));
    }

    @Test
    public void queryDatapointsWithStringValueFilterNotEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeStringDatapointsTest(baseTimestamp, STRING_VALUE_1, STRING_VALUE_2);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 2,
                ValueFilter.createValueFilter(ValueFilter.NOT_EQUAL, STRING_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("String"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getStringValue(), equalTo(STRING_VALUE_2));
    }

    @Test
    public void queryBytesDatapointsTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeBytesDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 4)));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), equalTo(4L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(2));

        assertThat(response.getResults().get(0).getGroups().get(0).getGroupInfos().get(0).getType(), equalTo("Bytes"));
        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).isBytes(), equalTo(true));
        assertThat(values.get(0).getBytesValue(), equalTo(BYTE_VALUE_1));
        assertThat(values.get(1).isBytes(), equalTo(true));
        assertThat(values.get(1).getBytesValue(), equalTo(BYTE_VALUE_2));

        assertThat(response.getResults().get(0).getGroups().get(1).getGroupInfos().get(0).getType(), equalTo("Number"));
        values = response.getResults().get(0).getGroups().get(1).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).isDouble(), equalTo(true));
        assertThat(values.get(0).getDoubleValue(), equalTo(DOUBLE_VALUE_1));
        assertThat(values.get(1).isDouble(), equalTo(true));
        assertThat(values.get(1).getDoubleValue(), equalTo(DOUBLE_VALUE_2));
    }

    @Test
    public void testGeneratePresignedUrlForQueryDatapoints() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);
        Thread.sleep(1000);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4);
        URL url = tsdbClient.generatePresignedUrlForQueryDatapoints(queries);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        InputStream inputStream = null;
        try {
            inputStream = urlConnection.getInputStream();
            QueryDatapointsResponse response = JsonUtils.loadFrom(inputStream,
                    QueryDatapointsResponse.class);


            assertThat(response, hasProperty("results"));
            assertThat(response.getResults(), hasSize(1));
            assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
            assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
            assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Test
    public void testWriteGzip() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(baseTimestamp, 1.1)
                .addDoubleValue(baseTimestamp + 1, 1.2));

        tsdbClient.writeDatapoints(datapoints);
        // Sleep 1 second to ensure that the datapoints are writed into Tsdb.
        Thread.sleep(1000);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 2);

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
    }

    private List<Query> createQueries(long startTimestamp, long endTimestamp) {
        return Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTimestamp)
                        .withAbsoluteEnd(endTimestamp)
                        .addTag(TAG_KEY, TAG_VALUE))
                .addGroupBy(new GroupBy()
                        .withName(TsdbConstants.GROUP_BY_NAME_TIME)
                        .withTimeRangeSize("1 second")
                        .withGroupCount(1))
                .withLimit(100)
                .addAggregator(new Aggregator()
                        .withName(TsdbConstants.AGGREGATOR_NAME_SUM)
                        .withSampling("1 second")));
    }

    private List<Query> createQueriesWithValueFilter(long startTimestamp, long endTimestamp, ValueFilter valueFilter) {
        return Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTimestamp)
                        .withAbsoluteEnd(endTimestamp)
                        .addTag(TAG_KEY, TAG_VALUE)
                        .withValue(valueFilter)));
    }
}
