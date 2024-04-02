/*
 * Copyright 2018-2019 Baidu, Inc.
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

import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.INGEST_QUOTA_UNIT;
import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.QUERY_QUOTA_UNIT;
import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.STORAGE_QUOTA_UNIT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.hamcrest.CustomMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.model.CreateDatabaseRequest;
import com.baidubce.services.tsdb.model.CreateDatabaseResponse;
import com.baidubce.services.tsdb.model.DatabaseQuotaInfo;
import com.baidubce.services.tsdb.model.DeleteDatapointsRequest;
import com.baidubce.services.tsdb.model.DeleteDatapointsResponse;
import com.baidubce.services.tsdb.model.ExportDatapointsRequest;
import com.baidubce.services.tsdb.model.ExportDatapointsResponse;
import com.baidubce.services.tsdb.model.ExportTaskParams;
import com.baidubce.services.tsdb.model.GetDatabaseResponse;
import com.baidubce.services.tsdb.model.GetTaskRequest;
import com.baidubce.services.tsdb.model.GetTaskResponse;
import com.baidubce.services.tsdb.model.ListDatabaseResponse;
import com.baidubce.services.tsdb.model.MetricFields;
import com.baidubce.services.tsdb.model.RenewDatabaseRequest;
import com.baidubce.services.tsdb.model.RenewDatabaseResponse;
import com.baidubce.services.tsdb.model.ResizeDatabaseRequest;
import com.baidubce.services.tsdb.model.ResizeDatabaseResponse;
import com.baidubce.services.tsdb.model.TaskTagFilter;
import com.baidubce.services.tsdb.utils.QuotaCalculator;
import com.google.common.base.Objects;

/**
 * Unit test for tsdb client admin api.
 *
 * @author linpengxiang
 */
public class TsdbClientAdminTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // Tsdb test ak and sk
    private static final String TEST_AK = "Your Ak";
    private static final String TEST_SK = "Your SK";

    private static final String ENDPOINT = "tsdb.gz.baidubce.com";
    private static final String TEST_DATABASE_ID = "Your_database_ID";
    private static final String TEST_TASK_ID = "Your_task_id";

    private TsdbAdminClient tsdbAdminClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_AK, TEST_SK))
                .withEndpoint(ENDPOINT)
                .withSocketTimeoutInMillis(120 * 1000);
        tsdbAdminClient = new TsdbAdminClient(config);
    }

    @Test
    public void createAndGetAndListDatabaseTest() {
        CreateDatabaseRequest request = buildDatabaseRequest();
        request.setCreateWithAutoRenew(true);
        String clientToken = UUID.randomUUID().toString().substring(0, 15);
        CreateDatabaseResponse response = tsdbAdminClient.createDatabase(request, clientToken);

        GetDatabaseResponse database = tsdbAdminClient.getDatabase(response.getDatabaseId());
        assertThat(database.getDatabaseName(), equalTo(request.getDatabaseName()));
        assertThat(database.getDescription(), equalTo(request.getDescription()));
        DatabaseQuotaInfo quota = database.getQuota();
        assertThat(quota.getIngestDataPointsMonthlyQuota(), equalTo(request.getIngestDataPointsMonthly()));
        assertThat(quota.getQueryUnitsMonthlyQuota(), equalTo(request.getQueryUnitsMonthly()));
        assertThat(quota.getStoreBytesQuota(), equalTo(request.getStoreBytesQuota()));
        assertThat(quota.getTimeSeriesQuota(), equalTo(request.getTimeSeriesQuota()));
        assertThat(quota.getLengthLimitMultipleQuota().getStringValueLimit(),
                equalTo(request.getLengthLimitMultipleQuota()));
        assertThat(quota.getLengthLimitMultipleQuota().getBytesValueLimit(),
                equalTo(request.getLengthLimitMultipleQuota()));
        assertThat(quota.getLengthLimitMultipleQuota().getBigDecimalValueLimit(),
                equalTo(request.getLengthLimitMultipleQuota()));

        ListDatabaseResponse databases = tsdbAdminClient.listDatabase();
        boolean hasItem = false;
        for (GetDatabaseResponse databaseInResponse : databases.getDatabases()) {
            if (databaseInResponse.getDatabaseId().equals(response.getDatabaseId())) {
                assertThat(databaseInResponse, equalTo(database));
                hasItem = true;
            }
        }
        assertThat(hasItem, equalTo(true));
    }

    @Test
    public void resizeDatabaseTest() {
        CreateDatabaseResponse databaseResponse = createDatabase();
        ResizeDatabaseRequest request = ResizeDatabaseRequest
                .builder()
                .databaseId(databaseResponse.getDatabaseId())
                .ingestDataPointsMonthly(INGEST_QUOTA_UNIT * 2)
                .queryUnitsMonthly(QUERY_QUOTA_UNIT * 11)
                .storeBytesQuota(STORAGE_QUOTA_UNIT * 2)
                .lengthLimitMultipleQuota(1)
                .build();
        ResizeDatabaseResponse resizeDatabaseResponse = tsdbAdminClient.resizeDatabase(request);
        Assert.assertNotNull(resizeDatabaseResponse.getOrderId());
        assertTrue(resizeDatabaseResponse.getCharge().doubleValue() > 0.5);

        GetDatabaseResponse database = tsdbAdminClient.getDatabase(databaseResponse.getDatabaseId());
        DatabaseQuotaInfo quota = database.getQuota();
        assertThat(quota.getIngestDataPointsMonthlyQuota(), equalTo(request.getIngestDataPointsMonthly()));
        assertThat(quota.getQueryUnitsMonthlyQuota(), equalTo(request.getQueryUnitsMonthly()));
        assertThat(quota.getStoreBytesQuota(), equalTo(request.getStoreBytesQuota()));
        assertThat(quota.getTimeSeriesQuota(), equalTo(request.getTimeSeriesQuota()));
        assertThat(quota.getLengthLimitMultipleQuota().getStringValueLimit(),
                equalTo(request.getLengthLimitMultipleQuota()));
        assertThat(quota.getLengthLimitMultipleQuota().getBytesValueLimit(),
                equalTo(request.getLengthLimitMultipleQuota()));
        assertThat(quota.getLengthLimitMultipleQuota().getBigDecimalValueLimit(),
                equalTo(request.getLengthLimitMultipleQuota()));
    }

    @Test
    public void renewDatabaseTest() {
        CreateDatabaseResponse databaseResponse = createDatabase();

        RenewDatabaseRequest renewDatabaseRequest = new RenewDatabaseRequest();
        renewDatabaseRequest.withDatabaseId(databaseResponse.getDatabaseId());
        renewDatabaseRequest.withPurchaseLength(1);
        RenewDatabaseResponse renewDatabaseResponse = tsdbAdminClient.renewDatabase(renewDatabaseRequest);
        Assert.assertNotNull(renewDatabaseResponse.getOrderId());

        Calendar expectDate = Calendar.getInstance();
        expectDate.setTime(databaseResponse.getExpiredTime());
        expectDate.add(Calendar.MONTH, 1);
        Assert.assertEquals(expectDate.getTime(), renewDatabaseResponse.getExpiredTime());
    }

    @Test
    public void deleteDatabaseWithUnexpiredDatabaseTest() {
        expectBceServiceException(400, "DeleteUnexpiredDatabaseFailed", null);

        CreateDatabaseRequest request = buildDatabaseRequest();
        String clientToken = UUID.randomUUID().toString().substring(0, 15);
        CreateDatabaseResponse response = tsdbAdminClient.createDatabase(request, clientToken);

        tsdbAdminClient.deleteDatabase(response.getDatabaseId());
    }

    @Test
    public void deleteDatapointsTest() {
        TaskTagFilter taskTagFilter = new TaskTagFilter();
        taskTagFilter.setTagKey("city");
        taskTagFilter.addIn("北京");

        Map<String, List<TaskTagFilter>> tags = new HashMap<String, List<TaskTagFilter>>();
        tags.put("pm25", Collections.singletonList(taskTagFilter));
        DeleteDatapointsRequest deleteRequest = new DeleteDatapointsRequest();
        deleteRequest.setMetricFieldsList(Collections.singletonList(
                new MetricFields("pm25",Collections.singletonList("value"))));
        deleteRequest.setDatabaseId(TEST_DATABASE_ID);
        deleteRequest.setTags(tags);
        DeleteDatapointsResponse deleteResponse = tsdbAdminClient.deleteDatapoints(deleteRequest);

        GetTaskRequest getTaskRequest = new GetTaskRequest();
        getTaskRequest.setDatabaseId(TEST_DATABASE_ID);
        getTaskRequest.setTaskId(deleteResponse.getTaskId());

        GetTaskResponse getTaskResponse = tsdbAdminClient.getTask(getTaskRequest);
        assertThat(getTaskResponse.getTaskId(), equalTo(deleteResponse.getTaskId()));
    }

    @Test
    public void exportDatapointsTest() {
        TaskTagFilter taskTagFilter = new TaskTagFilter();
        taskTagFilter.setTagKey("city");
        taskTagFilter.addIn("北京");

        Map<String, List<TaskTagFilter>> tags = new HashMap<String, List<TaskTagFilter>>();
        tags.put("pm25", Collections.singletonList(taskTagFilter));

        ExportDatapointsRequest exportRequest = new ExportDatapointsRequest();
        exportRequest.setDatabaseId(TEST_DATABASE_ID);
        exportRequest.setBosUrl("bos://tsdb-bos-bj/weather.csv");
        exportRequest.setFormat("csv");
        exportRequest.setSingleFile(true);
        exportRequest.setTags(tags);
        ExportDatapointsResponse exportResponse = tsdbAdminClient.exportDatapoints(exportRequest);

        GetTaskRequest getTaskRequest = new GetTaskRequest();
        getTaskRequest.setDatabaseId(TEST_DATABASE_ID);
        getTaskRequest.setTaskId(exportResponse.getTaskId());

        GetTaskResponse getTaskResponse = tsdbAdminClient.getTask(getTaskRequest);
        assertThat(getTaskResponse.getTaskId(), equalTo(exportResponse.getTaskId()));
        ExportTaskParams params = (ExportTaskParams) getTaskResponse.getParams();
        assertThat(params.getBosUrl(), equalTo("bos://tsdb-bos-bj/weather.csv"));
        assertThat(params.isSingleFile(), equalTo(true));
    }

    @Test
    public void getTaskTest() {
        GetTaskRequest getTaskRequest = new GetTaskRequest();
        getTaskRequest.setDatabaseId(TEST_DATABASE_ID);
        getTaskRequest.setTaskId(TEST_TASK_ID);
        GetTaskResponse response = tsdbAdminClient.getTask(getTaskRequest);
        assertThat(response.getTaskId(), equalTo(TEST_TASK_ID));
    }

    private void expectBceServiceException(final int statusCode, final String errorCode, final String errorMessage) {
        this.thrown.expect(new CustomMatcher<Throwable>(
                "BceServiceException [ statusCode=" + statusCode + ", errorCode=" + errorCode + "]") {
            @Override
            public boolean matches(Object item) {
                if (!(item instanceof BceServiceException)) {
                    return false;
                }
                BceServiceException bceServiceException = (BceServiceException) item;
                return bceServiceException.getStatusCode() == statusCode
                        && Objects.equal(bceServiceException.getErrorCode(), errorCode)
                        && (errorMessage == null || errorMessage.equals(bceServiceException.getErrorMessage()));
            }
        });
    }

    private CreateDatabaseResponse createDatabase() {
        CreateDatabaseRequest request = buildDatabaseRequest();
        String clientToken = UUID.randomUUID().toString().substring(0, 15);
        return tsdbAdminClient.createDatabase(request, clientToken);
    }

    private CreateDatabaseRequest buildDatabaseRequest() {
        return CreateDatabaseRequest.builder()
                .databaseName("database1sdktest" + new Random().nextInt(10000000))
                .description("test sdk")
                .ingestDataPointsMonthly(INGEST_QUOTA_UNIT)
                .queryUnitsMonthly(QUERY_QUOTA_UNIT * 10)
                .storeBytesQuota(STORAGE_QUOTA_UNIT)
                .timeSeriesQuota(QuotaCalculator.getTimeSeriesQuota(INGEST_QUOTA_UNIT))
                .lengthLimitMultipleQuota(1)
                .purchaseLength(1)
                .couponName("DAZXA6KTSNXWXQ9")
                .build();
    }

}
