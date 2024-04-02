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
package com.baidubce.services.tsdb;

import static com.baidubce.services.tsdb.TsdbConstants.AggregatorName.AGGREGATOR_NAME_ADJACENT_UNIQUE;
import static com.baidubce.services.tsdb.TsdbConstants.AggregatorName.AGGREGATOR_NAME_RATE;
import static com.baidubce.services.tsdb.TsdbConstants.AggregatorName.AGGREGATOR_NAME_SUM;
import static com.baidubce.services.tsdb.TsdbConstants.FillType.FILL_TYPE_FIXED;
import static com.baidubce.services.tsdb.TsdbConstants.FillType.FILL_TYPE_LINEAR;
import static com.baidubce.services.tsdb.TsdbConstants.QueryOrder.ORDER_ASC;
import static com.baidubce.services.tsdb.TsdbConstants.QueryOrder.ORDER_DESC;
import static com.baidubce.services.tsdb.TsdbConstants.TimeUnit.TIME_UNIT_SECOND;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.model.Aggregator;
import com.baidubce.services.tsdb.model.Datapoint;
import com.baidubce.services.tsdb.model.FieldFilter;
import com.baidubce.services.tsdb.model.Fill;
import com.baidubce.services.tsdb.model.Filters;
import com.baidubce.services.tsdb.model.GetFieldsResponse;
import com.baidubce.services.tsdb.model.GetMetricsResponse;
import com.baidubce.services.tsdb.model.GetRowsWithSqlResponse;
import com.baidubce.services.tsdb.model.GetTagsResponse;
import com.baidubce.services.tsdb.model.Group;
import com.baidubce.services.tsdb.model.Group.TimeAndValue;
import com.baidubce.services.tsdb.model.Query;
import com.baidubce.services.tsdb.model.QueryDatapointsResponse;
import com.baidubce.services.tsdb.model.TagFilter;
import com.baidubce.services.tsdb.model.ValueFilter;
import com.baidubce.services.tsdb.model.WriteDatapointsRequest;
import com.baidubce.util.JsonUtils;

public class TsdbClientTest {
    // qa-sandbox
    private static final String AK = "your_ak";
    private static final String SK = "your_sk";
    private static final String ENDPOINT = "your_endpoint";
    private static final String database = "test";
    private static final String METRIC = "test_java_sdk";
    private static final String TAG_KEY = "tag1";
    private static final String TAG_VALUE = "value1";
    private static final byte[] BYTE_VALUE_1 = new byte[]{0x01, 0x02, 0x00, 0x03};
    private static final byte[] BYTE_VALUE_2 = new byte[]{0x11, 0x12, 0x00, 0x13};
    private static final double DOUBLE_VALUE_1 = 1.1;
    private static final double DOUBLE_VALUE_2 = 1.2;
    private static final double DOUBLE_VALUE_3 = 1.3;
    private static final long LONG_VALUE_1 = 11;
    private static final long LONG_VALUE_2 = 12;
    private static final long LONG_VALUE_3 = 13;
    private static final String STRING_VALUE_1 = "str1";
    private static final String STRING_VALUE_2 = "str2";
    private static final BigDecimal BIG_DECIMAL_VALUE_1 = new BigDecimal(11);
    private static final BigDecimal BIG_DECIMAL_VALUE_2 = new BigDecimal(12);
    private static final String FIELD_DEFAULT_NAME = "value";

    private TsdbClient tsdbClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        tsdbClient = new TsdbClient(config, database);
    }

    private void writeDoubleDatapointsTest(long baseTimestamp) {
        writeDoubleDatapointsTest(baseTimestamp, FIELD_DEFAULT_NAME);
    }

    private void writeDoubleDatapointsTest(long baseTimestamp, String field) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .withField(field)
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
        writeBytesDatapointsTest(METRIC, baseTimestamp);
    }

    private void writeBytesDatapointsTest(String metric, long baseTimestamp) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(metric)
                .addTag(TAG_KEY, TAG_VALUE)
                .addBytesValue(baseTimestamp, BYTE_VALUE_1)
                .addBytesValue(baseTimestamp + 1, BYTE_VALUE_2));

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    private void writeBigDecimalDatapointsTest(String metric, long baseTimestamp) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(metric)
                .addTag(TAG_KEY, TAG_VALUE)
                .addBigDecimalValue(baseTimestamp, BIG_DECIMAL_VALUE_1)
                .addBigDecimalValue(baseTimestamp + 1, BIG_DECIMAL_VALUE_2));

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    @Test
    public void getMetricsTest() throws Exception {
        writeDoubleDatapointsTest(System.currentTimeMillis());

        GetMetricsResponse response = tsdbClient.getMetrics();

        assertThat(response, hasProperty("metrics"));
        assertThat(response.getMetrics(), is(notNullValue()));
        assertThat(response.getMetrics(), hasItem(METRIC));
    }

    @Test
    public void getTagsTest() throws Exception {
        writeDoubleDatapointsTest(System.currentTimeMillis());

        GetTagsResponse response = tsdbClient.getTags(METRIC);

        assertThat(response, hasProperty("tags"));
        assertThat(response.getTags(), is(notNullValue()));
        assertThat(response.getTags().get(TAG_KEY), is(notNullValue()));
        assertThat(response.getTags().get(TAG_KEY), hasItem(TAG_VALUE));
    }

    @Test
    public void getFieldsTest() throws Exception {
        String numberField = "numberfield";
        writeDoubleDatapointsTest(System.currentTimeMillis(), numberField);

        GetFieldsResponse response = tsdbClient.getFields(METRIC);

        assertThat(response, hasProperty("fields"));
        assertThat(response.getFields(), is(notNullValue()));
        assertThat(response.getFields().get(numberField), is(notNullValue()));
        assertThat(response.getFields().get(numberField).getType(), is("Number"));
    }

    @Test
    public void queryWithOffset() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4);
        queries.get(0).withOffset(1);
        queries.get(0).setAggregators(null);
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);
        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(4L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(3));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp + 1));
    }

    @Test
    public void queryDatapointsTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4);

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(4L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
        long lastTime = Long.MIN_VALUE;
        for (Group.TimeAndValue timeAndValue : response.getResults().get(0).getGroups().get(0).getTimeAndValueList()) {
            assertThat(timeAndValue.getTime(), greaterThan(lastTime));
            lastTime = timeAndValue.getTime();
        }
    }

    @Test
    public void queryDatapointsWithFieldTest() throws Exception {
        String field = "test";
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, field);
        writeDoubleDatapointsTest(baseTimestamp + 2, field);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4, field);

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getField(), equalTo(field));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(4L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
        long lastTime = Long.MIN_VALUE;
        for (Group.TimeAndValue timeAndValue : response.getResults().get(0).getGroups().get(0).getTimeAndValueList()) {
            assertThat(timeAndValue.getTime(), greaterThan(lastTime));
            lastTime = timeAndValue.getTime();
        }
    }

    @Test
    public void queryDatapointsByAscOrderTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4, null, ORDER_ASC);

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(4L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
        long lastTime = Long.MIN_VALUE;
        for (Group.TimeAndValue timeAndValue : response.getResults().get(0).getGroups().get(0).getTimeAndValueList()) {
            assertThat(timeAndValue.getTime(), greaterThan(lastTime));
            lastTime = timeAndValue.getTime();
        }
    }

    @Test
    public void queryDatapointsByDescOrderTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4, null, ORDER_DESC);

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(4L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
        long lastTime = Long.MAX_VALUE;
        for (Group.TimeAndValue timeAndValue : response.getResults().get(0).getGroups().get(0).getTimeAndValueList()) {
            assertThat(timeAndValue.getTime(), lessThan(lastTime));
            lastTime = timeAndValue.getTime();
        }
    }

    @Test
    public void queryDatapointsWithDoubleValueFilterEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.EQUAL, DOUBLE_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getDoubleValue(), closeTo(DOUBLE_VALUE_1, 0.001));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithDoubleValueFilterNotEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.NOT_EQUAL, DOUBLE_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        List<Double> doubleValues = Arrays.asList(values.get(0).getDoubleValue(), values.get(1).getDoubleValue());
        assertThat(doubleValues, containsInAnyOrder(closeTo(DOUBLE_VALUE_2, 0.001), closeTo(DOUBLE_VALUE_3, 0.001)));
    }

    @Test
    public void queryDatapointsWithDoubleValueFilterLessTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);

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

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.LESS_OR_EQUAL, DOUBLE_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getDoubleValue(), closeTo(DOUBLE_VALUE_1, 0.001));
    }

    @Test
    public void queryDatapointsWithDoubleValueFilterGreaterTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.GREATER, DOUBLE_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getDoubleValue(), closeTo(DOUBLE_VALUE_3, 0.001));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithDoubleValueFilterGreaterOrEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, DOUBLE_VALUE_1, DOUBLE_VALUE_2, DOUBLE_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.GREATER_OR_EQUAL, DOUBLE_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(2L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

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

    private void writeLongDatapointsTest(long baseTimestamp, String tagValue, Long... values) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, tagValue));
        for (Long value : values) {
            datapoints.get(0).addLongValue(baseTimestamp++, value);
        }

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    @Test
    public void queryDatapointsWithLongValueFilterEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.EQUAL, LONG_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getLongValue(), equalTo(LONG_VALUE_1));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithLongValueFilterNotEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.NOT_EQUAL, LONG_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        List<Long> longValues = Arrays.asList(values.get(0).getLongValue(), values.get(1).getLongValue());
        assertThat(longValues, containsInAnyOrder(equalTo(LONG_VALUE_2), equalTo(LONG_VALUE_3)));
    }

    @Test
    public void queryDatapointsWithLongValueFilterLessTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);

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

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.LESS_OR_EQUAL, LONG_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getLongValue(), equalTo(LONG_VALUE_1));
    }

    @Test
    public void queryDatapointsWithLongValueFilterGreaterTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.GREATER, LONG_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getLongValue(), equalTo(LONG_VALUE_3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void queryDatapointsWithLongValueFilterGreaterOrEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeLongDatapointsTest(baseTimestamp, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);

        List<Query> queries = createQueriesWithValueFilter(baseTimestamp, baseTimestamp + 3,
                ValueFilter.createValueFilter(ValueFilter.GREATER_OR_EQUAL, LONG_VALUE_2));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(2L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        List<Long> longValues = Arrays.asList(values.get(0).getLongValue(), values.get(1).getLongValue());
        assertThat(longValues, containsInAnyOrder(equalTo(LONG_VALUE_2), equalTo(LONG_VALUE_3)));
    }

    @Test
    public void queryDatapointsWithTagValueFilterLessTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        String tagValue = String.valueOf(LONG_VALUE_2);
        writeLongDatapointsTest(baseTimestamp, tagValue, LONG_VALUE_1, LONG_VALUE_2, LONG_VALUE_3);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 3)
                        .withValue(ValueFilter.createValueFilterOfTag(ValueFilter.EQUAL, TAG_KEY))));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(0L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));
        assertThat(response.getResults().get(0).getGroups().get(0).getTimeAndValueList().size(), equalTo(1));
        assertThat(response.getResults().get(0).getGroups().get(0).getTimeAndValueList().get(0).getLongValue(),
                equalTo(LONG_VALUE_2));
    }

    private void writeStringDatapointsTest(long baseTimestamp, String... values) {
        writeStringDatapointsTest(METRIC, baseTimestamp, values);
    }

    private void writeStringDatapointsTest(String metric, long baseTimestamp, String... values) {
        List<Datapoint> datapoints = Arrays.asList(new Datapoint().withMetric(metric).addTag(TAG_KEY, TAG_VALUE));
        for (String value : values) {
            datapoints.get(0).addStringValue(baseTimestamp++, value);
        }

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);
    }

    @Test
    public void queryDatapointsWithStringValueFilterEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        String metric = "stringMetric";
        writeStringDatapointsTest(metric, baseTimestamp, STRING_VALUE_1, STRING_VALUE_2);

        List<Query> queries = createQueriesWithValueFilter(metric, baseTimestamp, baseTimestamp + 2,
                ValueFilter.createValueFilter(ValueFilter.EQUAL, STRING_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(metric));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getStringValue(), equalTo(STRING_VALUE_1));
    }

    @Test
    public void queryDatapointsWithStringValueFilterNotEqualTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        String metric = "stringMetric";
        writeStringDatapointsTest(metric, baseTimestamp, STRING_VALUE_1, STRING_VALUE_2);

        List<Query> queries = createQueriesWithValueFilter(metric, baseTimestamp, baseTimestamp + 2,
                ValueFilter.createValueFilter(ValueFilter.NOT_EQUAL, STRING_VALUE_1));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(metric));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getStringValue(), equalTo(STRING_VALUE_2));
    }

    @Test
    public void queryBytesDatapointsTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        String metric = "bytesTest";
        writeBytesDatapointsTest(metric, baseTimestamp);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(metric)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 4)));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(metric));
        assertThat(response.getResults().get(0).getRawCount(), equalTo(2L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getBytesValue(), equalTo(BYTE_VALUE_1));
        assertThat(values.get(1).getBytesValue(), equalTo(BYTE_VALUE_2));
    }

    @Test
    public void queryBigDecimalDatapointsTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        String metric = "bigBigDecimalTest";
        writeBigDecimalDatapointsTest(metric, baseTimestamp);
        List<Query> queries = Arrays.asList(new Query()
                .withMetric(metric)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 4)));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(metric));
        assertThat(response.getResults().get(0).getRawCount(), equalTo(2L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        List<Group.TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getBigDecimalValue(), equalTo(BIG_DECIMAL_VALUE_1));
        assertThat(values.get(1).getBigDecimalValue(), equalTo(BIG_DECIMAL_VALUE_2));
    }

    @Test
    public void testGeneratePresignedUrlForQueryDatapoints() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp + 2);

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

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 2);

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(1L));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void testRateAggregator() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addLongValue(baseTimestamp, LONG_VALUE_1)
                .addLongValue(baseTimestamp + 1, LONG_VALUE_2)
                .addLongValue(baseTimestamp + 2, LONG_VALUE_3));

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);

        List<Query> queries = createQueriesWithRateAggregator(baseTimestamp, baseTimestamp + 2);
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getRawCount(), greaterThanOrEqualTo(2L));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getTimeAndValueList().size(), equalTo(2));
        Group.TimeAndValue timeAndValue = response.getResults().get(0).getGroups().get(0).getTimeAndValueList().get(0);
        assertThat(timeAndValue.getTime(), equalTo(baseTimestamp + 1));
        assertThat(timeAndValue.getDoubleValue(), closeTo((LONG_VALUE_2 - LONG_VALUE_1) * 1000.0, 0.001));
        timeAndValue = response.getResults().get(0).getGroups().get(0).getTimeAndValueList().get(1);
        assertThat(timeAndValue.getTime(), equalTo(baseTimestamp + 2));
        assertThat(timeAndValue.getDoubleValue(), closeTo((LONG_VALUE_3 - LONG_VALUE_2) * 1000.0, 0.001));
    }

    @Test
    public void testAdjacentUniqueAggregator() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addLongValue(baseTimestamp, LONG_VALUE_1)
                .addLongValue(baseTimestamp + 1, LONG_VALUE_1)
                .addLongValue(baseTimestamp + 2, LONG_VALUE_2)
                .addLongValue(baseTimestamp + 3, LONG_VALUE_1)
                .addLongValue(baseTimestamp + 4, LONG_VALUE_1)
                .addLongValue(baseTimestamp + 5, LONG_VALUE_3)
                .addLongValue(baseTimestamp + 6, LONG_VALUE_3));

        tsdbClient.writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints), false);

        List<Query> queries = createQueriesWithAdjacentUniqueAggregator(baseTimestamp, baseTimestamp + 6);
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), equalTo(1));

        assertThat(response.getResults().get(0).getGroups().get(0).getTimeAndValueList().size(), equalTo(4));
        Group.TimeAndValue timeAndValue = response.getResults().get(0).getGroups().get(0).getTimeAndValueList().get(0);
        assertThat(timeAndValue.getTime(), equalTo(baseTimestamp));
        assertThat(timeAndValue.getLongValue(), equalTo(LONG_VALUE_1));
        timeAndValue = response.getResults().get(0).getGroups().get(0).getTimeAndValueList().get(1);
        assertThat(timeAndValue.getTime(), equalTo(baseTimestamp + 2));
        assertThat(timeAndValue.getLongValue(), equalTo(LONG_VALUE_2));
        timeAndValue = response.getResults().get(0).getGroups().get(0).getTimeAndValueList().get(2);
        assertThat(timeAndValue.getTime(), equalTo(baseTimestamp + 3));
        assertThat(timeAndValue.getLongValue(), equalTo(LONG_VALUE_1));
        timeAndValue = response.getResults().get(0).getGroups().get(0).getTimeAndValueList().get(3);
        assertThat(timeAndValue.getTime(), equalTo(baseTimestamp + 5));
        assertThat(timeAndValue.getLongValue(), equalTo(LONG_VALUE_3));
    }

    @Test
    public void queryDatapointsWithFieldsTest() throws Exception {
        String field1 = "test1";
        String field2 = "test2";
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp, field1);
        writeDoubleDatapointsTest(baseTimestamp, field2);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4, Arrays.asList(field1, field2));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getFields(), equalTo(Arrays.asList(field1, field2)));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getDoubleValue(0), equalTo(DOUBLE_VALUE_1));
        assertThat(values.get(0).getDoubleValue(1), equalTo(DOUBLE_VALUE_1));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(1).getDoubleValue(0), equalTo(DOUBLE_VALUE_2));
        assertThat(values.get(1).getDoubleValue(1), equalTo(DOUBLE_VALUE_2));
    }

    @Test
    public void queryDatapointsWithTagsTest() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withTags(Collections.singletonList(TAG_KEY))
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 2)));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getField(), equalTo("value"));
        assertThat(response.getResults().get(0).getTags(), equalTo(Arrays.asList(TAG_KEY)));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getDoubleValue(0), equalTo(DOUBLE_VALUE_1));
        assertThat(values.get(0).getStringValue(1), equalTo(TAG_VALUE));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(1).getDoubleValue(0), equalTo(DOUBLE_VALUE_2));
        assertThat(values.get(1).getStringValue(1), equalTo(TAG_VALUE));
    }

    @Test
    public void queryDatapointsWithValueFilterTest() throws Exception {
        String field = "test";
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp, field);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4, Arrays.asList("value", field));
        for (Query query : queries) {
            query.getFilters().withValue(ValueFilter.createValueFilter(">", DOUBLE_VALUE_1));
        }

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getFields(), equalTo(Arrays.asList("value", field)));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(0).getDoubleValue(0), equalTo(DOUBLE_VALUE_2));
        assertThat(values.get(0).getDoubleValue(1), equalTo(DOUBLE_VALUE_2));
    }

    @Test
    public void queryDatapointsWithSpecificValueFilterTest() throws Exception {
        String field = "test";
        long baseTimestamp = System.currentTimeMillis();
        writeDoubleDatapointsTest(baseTimestamp);
        writeDoubleDatapointsTest(baseTimestamp, field);

        List<Query> queries = createQueries(baseTimestamp, baseTimestamp + 4, Arrays.asList("value", field));
        FieldFilter fieldFilter = new FieldFilter(field, ValueFilter.createValueFilter(">", DOUBLE_VALUE_1));
        for (Query query : queries) {
            query.getFilters().addField(fieldFilter);
        }

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getFields(), equalTo(Arrays.asList("value", field)));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(0).getDoubleValue(0), equalTo(DOUBLE_VALUE_2));
        assertThat(values.get(0).getDoubleValue(1), equalTo(DOUBLE_VALUE_2));
    }

    @Test
    public void queryDatapointsWithFill() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        double value1 = 1.0;
        double value2 = 2.0;
        double value3 = 3.0;
        writeDoubleDatapointsTest(baseTimestamp, value1);
        writeDoubleDatapointsTest(baseTimestamp + 2, value3);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 2))
                .withFill(new Fill()
                        .withType(FILL_TYPE_LINEAR)
                        .withInterval("1 milliseconds")));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(3));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getDoubleValue(0), equalTo(value1));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(1).getDoubleValue(0), equalTo(value2));
        assertThat(values.get(2).getTime(), equalTo(baseTimestamp + 2));
        assertThat(values.get(2).getDoubleValue(0), equalTo(value3));

    }

    @Test
    public void queryDatapointsWithFillTypeFixed() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        double value1 = 1.0;
        double value2 = 3.0;
        double value3 = 2.0;
        writeDoubleDatapointsTest(baseTimestamp, value1);
        writeDoubleDatapointsTest(baseTimestamp + 2, value3);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 2)
                        .addTagFilter(new TagFilter().withTag(TAG_KEY).addIn(TAG_VALUE)))
                .withFill(new Fill()
                        .withType(FILL_TYPE_FIXED)
                        .withInterval("1 milliseconds")
                        .withValue(value2)));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(3));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getDoubleValue(0), equalTo(value1));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(1).getDoubleValue(0), equalTo(value2));
        assertThat(values.get(2).getTime(), equalTo(baseTimestamp + 2));
        assertThat(values.get(2).getDoubleValue(0), equalTo(value3));

    }

    @Test
    public void queryWithOr() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        long value1 = 1;
        long value2 = 2;
        writeLongDatapointsTest(baseTimestamp, value1, value2);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withOr(Arrays.asList(new Filters()
                                .withAbsoluteStart(baseTimestamp)
                                .withAbsoluteEnd(baseTimestamp + 1)))
                ));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getLongValue(0), equalTo(value1));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(1).getLongValue(0), equalTo(value2));
    }

    @Test
    public void queryDatapointsWithMarker() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        double value1 = 1.0;
        double value2 = 3.0;
        double value3 = 2.0;
        writeDoubleDatapointsTest(baseTimestamp, value1);
        writeDoubleDatapointsTest(baseTimestamp + 1, value2);
        writeDoubleDatapointsTest(baseTimestamp + 2, value3);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 2))
                .withLimit(2));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));
        assertTrue(response.getResults().get(0).isTruncated());

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getDoubleValue(0), equalTo(value1));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(1).getDoubleValue(0), equalTo(value2));

        queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 2))
                .withLimit(2)
                .withMarker(response.getResults().get(0).getNextMarker()));

        response = tsdbClient.queryDatapoints(queries);
        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp + 2));
        assertThat(values.get(0).getDoubleValue(0), equalTo(value3));
    }

    @Test
    public void queryWithTagFiltersIn() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        long value1 = 1;
        long value2 = 2;
        writeLongDatapointsTest(baseTimestamp, value1, value2);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 1)
                        .addTagFilter(new TagFilter().withTag(TAG_KEY).addIn(TAG_VALUE)))
        );
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getLongValue(0), equalTo(value1));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 1));
        assertThat(values.get(1).getLongValue(0), equalTo(value2));
    }

    @Test
    public void queryWithTagFiltersNotIn() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        long value1 = 1;
        long value2 = 2;
        writeLongDatapointsTest(baseTimestamp, value1, value2);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 1)
                        .addTagFilter(new TagFilter().withTag(TAG_KEY).addNotIn(TAG_VALUE)))
        );
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(0));
    }

    @Test
    public void queryWithTagFiltersLike() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        long value1 = 1;
        long value2 = 2;
        String tagValue1 = "value_1-1";
        writeLongDatapointsTest(baseTimestamp, tagValue1, value1);
        String tagValue2 = "value_1-2";
        writeLongDatapointsTest(baseTimestamp + 1, tagValue2, value2);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 1)
                        .addTagFilter(new TagFilter().withTag(TAG_KEY).like("value\\__-1")))
        );
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(1));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getLongValue(0), equalTo(value1));
    }

    @Test
    public void deserializeQueryWithFill() {
        Query query = new Query().withFill(new Fill().withType(FILL_TYPE_FIXED).withValue(1));
        String json = JsonUtils.toJsonString(query);
        Query queryFromJson = JsonUtils.fromJsonString(json, Query.class);
        Fill fill = queryFromJson.getFill();
        assertThat(fill.getType(), equalTo(FILL_TYPE_FIXED));
        assertThat(fill.getValue().asLong(), equalTo(1L));
    }

    @Test
    public void deserializeQueryWithTagFilters() {
        Query query = new Query().withFilters(new Filters()
                .addTagFilter(new TagFilter()
                        .withTag(TAG_KEY)
                        .withIn(Arrays.asList(TAG_VALUE))));
        String json = JsonUtils.toJsonString(query);
        Query queryFromJson = JsonUtils.fromJsonString(json, Query.class);

        assertThat(queryFromJson.getFilters().getTags(), nullValue());
        List<TagFilter> tagFilters = queryFromJson.getFilters().getTagFilters();
        assertThat(tagFilters.size(), equalTo(1));
        assertThat(tagFilters.get(0).getTag(), equalTo(TAG_KEY));
        assertThat(tagFilters.get(0).getIn(), equalTo(Arrays.asList(TAG_VALUE)));
        assertThat(tagFilters.get(0).getNotIn(), equalTo(Collections.<String>emptyList()));
    }

    @Test
    public void deserializeQueryWithTags() {
        Query query = new Query().withFilters(new Filters().addTag(TAG_KEY, TAG_VALUE));
        String json = JsonUtils.toJsonString(query);
        Query queryFromJson = JsonUtils.fromJsonString(json, Query.class);

        assertThat(queryFromJson.getFilters().getTagFilters(), nullValue());
        Map<String, List<String>> tags = queryFromJson.getFilters().getTags();
        assertThat(tags.size(), equalTo(1));
        assertThat(tags.get(TAG_KEY), equalTo(Arrays.asList(TAG_VALUE)));
    }

    @Test
    public void queryWithNotExistTag() throws Exception {
        long baseTimestamp = System.currentTimeMillis();
        double value1 = 1.0;
        double value3 = 2.0;
        writeDoubleDatapointsTest(baseTimestamp, value1);
        writeDoubleDatapointsTest(baseTimestamp + 2, value3);

        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withTags(Collections.singletonList("notexisttag"))
                .withFilters(new Filters()
                        .withAbsoluteStart(baseTimestamp)
                        .withAbsoluteEnd(baseTimestamp + 2)));

        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        assertThat(response, hasProperty("results"));
        assertThat(response.getResults(), hasSize(1));
        assertThat(response.getResults().get(0).getMetric(), equalTo(METRIC));
        assertThat(response.getResults().get(0).getGroups().size(), greaterThanOrEqualTo(1));

        List<TimeAndValue> values = response.getResults().get(0).getGroups().get(0).getTimeAndValueList();
        assertThat(values.size(), equalTo(2));
        assertThat(values.get(0).getTime(), equalTo(baseTimestamp));
        assertThat(values.get(0).getDoubleValue(0), equalTo(value1));
        assertTrue(values.get(0).isNull(1));
        assertThat(values.get(1).getTime(), equalTo(baseTimestamp + 2));
        assertThat(values.get(1).getDoubleValue(0), equalTo(value3));
        assertTrue(values.get(1).getStringValue(1) == null);
    }

    @Test
    public void getRowWithSqlTest() {
        long baseTimestamp = System.currentTimeMillis();
        double value1 = 1.0;
        double value3 = 2.0;
        writeDoubleDatapointsTest(baseTimestamp, value1);
        writeDoubleDatapointsTest(baseTimestamp + 2, value3);

        GetRowsWithSqlResponse response = tsdbClient.getRowsWithSql("select timestamp, value from " + METRIC
                + " where timestamp >= " + baseTimestamp + " and timestamp <= " + (baseTimestamp + 2));
        assertThat(response.getColumns().size(), equalTo(2));
        assertThat(response.getColumns().get(0).getName(), equalTo("timestamp"));
        assertThat(response.getColumns().get(1).getName(), equalTo("value"));
        assertThat(response.getRows().size(), equalTo(2));
        assertThat(response.getRows().get(0).size(), equalTo(2));
        assertThat((Long) response.getRows().get(0).get(0), equalTo(baseTimestamp));
        assertThat((Double) response.getRows().get(0).get(1), equalTo(value1));
        assertThat(response.getRows().get(1).size(), equalTo(2));
        assertThat((Long) response.getRows().get(1).get(0), equalTo(baseTimestamp + 2));
        assertThat((Double) response.getRows().get(1).get(1), equalTo(value3));
    }

    @Test
    public void testParseCompressedResponse() {
        int compressCnt = 1000;
        long value = 7L;
        Long[] values = new Long[compressCnt];
        Arrays.fill(values, value);
        long timestamp = System.currentTimeMillis();
        writeLongDatapointsTest(timestamp, values);

        List<Query> queries = createQueries(timestamp, timestamp + compressCnt,
                Collections.singletonList(FIELD_DEFAULT_NAME));
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);
        List<List<JsonNode>> datapoints = response.getResults().get(0).getGroups().get(0).getValues();
        assertEquals(datapoints.size(), compressCnt);
        for (int i = 0; i < compressCnt; i++) {
            assertEquals(2, datapoints.get(i).size());
            assertEquals(timestamp + i, datapoints.get(i).get(0).asLong());
            assertEquals(value, datapoints.get(i).get(1).asLong());
        }
    }

    private List<Query> createQueries(long startTimestamp, long endTimestamp) {
        return createQueries(startTimestamp, endTimestamp, null, ORDER_ASC);
    }

    private List<Query> createQueries(long startTimestamp, long endTimestamp, String field) {
        return createQueries(startTimestamp, endTimestamp, field, ORDER_ASC);
    }

    private List<Query> createQueries(long startTimestamp, long endTimestamp, List<String> fields) {
        return Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFields(fields)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTimestamp)
                        .withAbsoluteEnd(endTimestamp)));
    }

    private List<Query> createQueries(long startTimestamp, long endTimestamp, String field, String order) {
        return Arrays.asList(new Query()
                .withMetric(METRIC)
                .withField(field)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTimestamp)
                        .withAbsoluteEnd(endTimestamp)
                        .addTag(TAG_KEY, TAG_VALUE))
                .withLimit(100)
                .addAggregator(new Aggregator()
                        .withName(AGGREGATOR_NAME_SUM)
                        .withSampling("1 second"))
                .withOrder(order));
    }

    private List<Query> createQueriesWithValueFilter(long startTimestamp, long endTimestamp, ValueFilter valueFilter) {
        return createQueriesWithValueFilter(METRIC, startTimestamp, endTimestamp, valueFilter);
    }

    private List<Query> createQueriesWithValueFilter(String metric, long startTimestamp, long endTimestamp,
        ValueFilter valueFilter) {
        return Arrays.asList(new Query()
                .withMetric(metric)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTimestamp)
                        .withAbsoluteEnd(endTimestamp)
                        .addTag(TAG_KEY, TAG_VALUE)
                        .withValue(valueFilter)));
    }

    private List<Query> createQueriesWithRateAggregator(long startTimestamp, long endTimestamp) {
        return Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTimestamp)
                        .withAbsoluteEnd(endTimestamp)
                        .addTag(TAG_KEY, TAG_VALUE))
                .addAggregator(new Aggregator()
                        .withName(AGGREGATOR_NAME_RATE)
                        .withTimeUnit(TIME_UNIT_SECOND)));
    }

    private List<Query> createQueriesWithAdjacentUniqueAggregator(long startTimestamp, long endTimestamp) {
        return Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(startTimestamp)
                        .withAbsoluteEnd(endTimestamp)
                        .addTag(TAG_KEY, TAG_VALUE))
                .addAggregator(new Aggregator()
                        .withName(AGGREGATOR_NAME_ADJACENT_UNIQUE)));
    }
}