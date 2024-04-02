package com.baidubce.services.vca;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vca.model.AnalyzeRequest;
import com.baidubce.services.vca.model.AnalyzeResponse;
import com.baidubce.services.vca.model.QueryResultResponse;
import com.baidubce.services.vca.model.ResultItem;
import com.baidubce.services.vca.model.TagsResult;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * vca client test
 *
 * @author shunwei
 */
public class VcaClientTest {
    // qa-sandbox
    private static final String AK = "d47d0d6c80604f7f935e787254ff1980";
    private static final String SK = "b6a4e01c8e4f480fab67ac17992e4b88";
    private static final String ENDPOINT = "http://10.107.42.42:8756";

    private VcaClient vcaClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        vcaClient = new VcaClient(config);
    }

    @Test
    public void analyze() {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest().withMediaSource("vod://mda-hkuiyapdxwj4wap1")
                .withPreset("only_image_classify").withNotification("hou_dev");
        AnalyzeResponse response = vcaClient.analyze(analyzeRequest);
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void queryResult() {
        QueryResultResponse response = vcaClient.queryResult("vod://mda-hkuiyapdxwj4wap1");
        String status = response.getStatus();
        if ("FINISHED".equals(status)) {
            for (TagsResult tagsResult : response.getResults()) {
                String type = tagsResult.getType();
                for (ResultItem item : tagsResult.getResult()) {
                    String attribute = item.getAttribute();
                    Double confidence = item.getConfidence();
                    String source = item.getSource();
                    List<ResultItem.TimeInSeconds> time = item.getTime();
                }
            }
        }
        response.getResults();


        assertThat(response, is(notNullValue()));
        assertThat(response.getResults(), is(notNullValue()));
    }
}
