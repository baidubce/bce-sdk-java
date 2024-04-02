package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.CreateStreamResponse;
import com.baidubce.services.lss.model.GetStreamResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by wuyafei on 2017/3/13.
 */
public class UpdateStreamPresetsTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testUpdateStreamPresets() {
        String app = "test_update_presets";
        String stream = "test" + System.currentTimeMillis() / 1000;
        CreateStreamResponse response = lssClient.createStream(playDomain, app, stream);

        assertEquals(1, response.getPresets().values().size());
        assertEquals("lss.forward_only", response.getPresets().get("L0"));

        Map<String, String> newPresets = new HashMap<String, String>();
        newPresets.put("L1", "lss.lss_960x720");
        newPresets.put("L2", "lss.lss_1280x720");
        lssClient.updateStreamPresets(playDomain, app, stream, newPresets);

        GetStreamResponse streamResponse = lssClient.getStream(playDomain, app, stream);

        assertEquals(3, streamResponse.getPresets().size());
        assertEquals("lss.lss_1280x720", streamResponse.getPresets().get("L2"));
        assertEquals("lss.lss_960x720", streamResponse.getPresets().get("L1"));

        lssClient.deleteStream(playDomain, app, stream);
    }
}
