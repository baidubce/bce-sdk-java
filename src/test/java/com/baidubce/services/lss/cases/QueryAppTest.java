package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.GetAppResponse;
import com.baidubce.services.lss.model.GetAppStreamResponse;
import com.baidubce.services.lss.model.ListAppResponse;
import com.baidubce.services.lss.model.ListAppStreamsResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wuyafei on 16/6/29.
 */
public class QueryAppTest extends AbstractLssTest {
    @Before
    public void setUp() throws Exception {
        AK = "6107180b80ae42c0a821a5fe53ba615e";
        SK = "a88ed698c0fd4774a4f1bdcebcae5922";
        END_POINT = "http://10.65.3.79:80";
        super.setUp();
    }

    @Test
    public void testQueryApp() {
        GetAppResponse response = lssClient.queryApp("test_app");
        assertEquals(response.getName(), "test_app");
    }

    @Test
    public void testQueryAppStream() {
        GetAppStreamResponse response = lssClient.queryAppStream("test_app", "teststream");
        assertEquals(response.getPublish().getPushStream(), "teststream");
    }

    @Test
    public void testListAppStreams() {
        ListAppStreamsResponse response = lssClient.listAppStreams("test_app");
        assertEquals(response.getSessions().size(), 2);

        if (response.getSessions().get(0).getStatus().equals("READY")) {
            lssClient.pauseAppStream("test_app", response.getSessions().get(0).getPublish().getPushStream());
        }
        ListAppStreamsResponse response1 = lssClient.listAppStreams("test_app", "READY");
        assertEquals(response1.getSessions().size(), 1);
    }

    @Test
    public void testListApp() {
        ListAppResponse response = lssClient.listApp();
        assertEquals(response.getApps().size(), 3);
    }
}
