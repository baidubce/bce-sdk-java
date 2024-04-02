package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.RecordingClipRequest;
import com.baidubce.services.lss.model.RecordingClipResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Unit test for clipping recording file.
 */
public class RecordingClipTest extends AbstractLssTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testRecordingClip() {
        String domain = "test.baidu.com";
        String app = "app";
        String stream = "stream";
        String sourceFile = "test.baidu.com/app/stream/records/test.m3u8";
        RecordingClipRequest request = new RecordingClipRequest();
        request.setPlayDomain(domain);
        request.setApp(app);
        request.setStream(stream);
        request.setSourceFile(sourceFile);
        RecordingClipResponse response = lssClient.recordingClip(request);
        assertNotNull(request);
        assertNotNull(response.getFileUrl());
    }
}