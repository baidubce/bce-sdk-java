package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.Video;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.CreateSessionResponse;
import com.baidubce.services.lss.model.GetSessionStatisticsResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by wuyafei on 16/7/11.
 */
public class GetSessionStatisticsTest extends AbstractLssTest {
    private String sessionId = null;
    private String presetName = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        presetName = convertName(prePresetName);
        String description = "test";
        Audio audio = new Audio();
        audio.setBitRateInBps(128000);
        audio.setChannels(2);
        audio.setSampleRateInHz(44100);
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("high");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(1600000);
        video.setMaxFrameRate(30f);
        video.setMaxHeightInPixel(720);
        video.setMaxWidthInPixel(1280);
        video.setSizingPolicy("stretch");
        Hls hls = new Hls();
        hls.setSegmentTimeInSecond(6);
        hls.setSegmentListSize(6);
        hls.setAdaptive(true);
        Rtmp rtmp = new Rtmp();
        rtmp.setGopCache(true);

        lssClient.createPreset(presetName, description, audio, video, hls, rtmp, null, null);

        CreateSessionResponse response = lssClient.createSession(null, presetName, null, null, null, null);
        sessionId = response.getSessionId();
    }

    @Test
    public void testGetSessionStatistics() {
        GetSessionStatisticsResponse response = lssClient.getSessionStatistics(sessionId,
                "20160705", "20160708", true);
        assertEquals(response.getStatistics(), null);
        assertTrue(response.getAggregate().getDownstreamInByte() == 0L);
        assertTrue(response.getAggregate().getDurationInMinute() == 0L);
        assertEquals(response.getStartDate(), "20160705");
        assertEquals(response.getEndDate(), "20160708");
        assertEquals(response.getSessionId(), sessionId);

        GetSessionStatisticsResponse response1 = lssClient.getSessionStatistics(sessionId,
                "20160705", "20160708", false);
        assertEquals(response1.getAggregate(), null);
        assertTrue(response1.getStatistics().size() == 0);

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        GetSessionStatisticsResponse response2 = lssClient.getSessionStatistics(sessionId,
                null, null, null);
        assertEquals(response2.getStartDate(), sdf.format(now));
        assertEquals(response2.getEndDate(), sdf.format(now));
        assertEquals(response2.getAggregate(), null);
        assertTrue(response2.getStatistics().size() == 0);
    }

    @After
    public void tearDown() throws Exception {
        lssClient.deleteSession(sessionId);
        lssClient.deletePreset(presetName);
    }

}
