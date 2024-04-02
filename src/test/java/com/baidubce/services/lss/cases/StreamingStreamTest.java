package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.StreamingStreamResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for get streaming stream.
 */
public class StreamingStreamTest extends AbstractLssTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testGetStreamingStreams() {
        String playDomain = "test.baidu.com";
        StreamingStreamResponse response = lssClient.listOngiongStream(playDomain);
        assertEquals(response.getStreams().size(), 0);
    }
}
