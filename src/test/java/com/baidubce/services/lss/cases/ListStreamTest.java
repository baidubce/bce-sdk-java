package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.ListStreamRequest;
import com.baidubce.services.lss.model.ListStreamResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by wuyafei on 16/10/14.
 */
public class ListStreamTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testListStreamWithLeastParams() {
        ListStreamResponse response = lssClient.listStream(playDomain);
        System.out.println(response);
        assertNull(response.getMarker());
        assertNotNull(response.getStreams());
    }

    @Test
    public void testListStream() {
        ListStreamRequest request = new ListStreamRequest();
        String status = "READY";
        request.withPlayDomain(playDomain).withMaxSize(1).withStatus(status);
        ListStreamResponse response = lssClient.listStream(request);
        // System.out.println(response);
        assertEquals(response.getStreams().size() <= 1, true);
        assertNull(response.getMarker());
        if (response.getStreams().size() == 1) {
            assertNotNull(response.getNextMarker());
            assertTrue(response.getIsTruncated());
            assertEquals(response.getStreams().get(0).getStatus(), status);
            request.withMarker(response.getNextMarker()).withMaxSize(3);
            response = lssClient.listStream(request);
            assertEquals(response.getStreams().size() <= 3, true);
            if (response.getStreams().size() != 0) {
                assertNotNull(response.getNextMarker());
            }
        }
    }
}
