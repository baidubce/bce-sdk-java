package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.GetPresetRequest;
import com.baidubce.services.lss.model.GetPresetResponse;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.LivePreset;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.Video;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class GetLivePresetTest extends AbstractLssTest {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        ListPresetsResponse resp = lssClient.listPresets();
        String presetName;
        for (LivePreset preset : resp.getPresets()) {
            presetName = preset.getName();
            if (presetName.startsWith(prePresetName)) {
                lssClient.deletePreset(presetName);
            }
        }
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    /**
     * GetPresetResponse getPreset(String presetName)
     */
    @Test
    public void testGetPresetNormal1() {
        String presetName = convertName(prePresetName);
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

        GetPresetResponse response = lssClient.getPreset(presetName);
        assertEquals(presetName, response.getName());
        assertEquals("test", response.getDescription());
        assertEquals("128000", response.getAudio().getBitRateInBps().toString());
        assertEquals("2", response.getAudio().getChannels().toString());
        assertEquals("44100", response.getAudio().getSampleRateInHz().toString());
        assertEquals("h264", response.getVideo().getCodec());
        assertEquals("high", response.getVideo().getCodecOptions().getProfile());
        assertEquals("1600000", response.getVideo().getBitRateInBps().toString());
        assertEquals("30.0", response.getVideo().getMaxFrameRate().toString());
        assertEquals("1280", response.getVideo().getMaxWidthInPixel().toString());
        assertEquals("720", response.getVideo().getMaxHeightInPixel().toString());
        assertEquals("stretch", response.getVideo().getSizingPolicy());
        assertEquals("6", response.getHls().getSegmentTimeInSecond().toString());
        assertEquals("6", response.getHls().getSegmentListSize().toString());
        assertEquals("true", response.getHls().getAdaptive().toString());
        assertEquals("true", response.getRtmp().getGopCache().toString());

    }

    /**
     * GetPresetResponse getPreset(GetPresetRequest request)
     */
    @Test
    public void testGetPresetNormal2() {
        String presetName = convertName(prePresetName);
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

        GetPresetRequest request = new GetPresetRequest();
        request.setName(presetName);
        GetPresetResponse response = lssClient.getPreset(request);
        assertEquals(presetName, response.getName());
        assertEquals("test", response.getDescription());
        assertEquals("128000", response.getAudio().getBitRateInBps().toString());
        assertEquals("2", response.getAudio().getChannels().toString());
        assertEquals("44100", response.getAudio().getSampleRateInHz().toString());
        assertEquals("h264", response.getVideo().getCodec());
        assertEquals("high", response.getVideo().getCodecOptions().getProfile());
        assertEquals("1600000", response.getVideo().getBitRateInBps().toString());
        assertEquals("30.0", response.getVideo().getMaxFrameRate().toString());
        assertEquals("1280", response.getVideo().getMaxWidthInPixel().toString());
        assertEquals("720", response.getVideo().getMaxHeightInPixel().toString());
        assertEquals("stretch", response.getVideo().getSizingPolicy());
        assertEquals("6", response.getHls().getSegmentTimeInSecond().toString());
        assertEquals("6", response.getHls().getSegmentListSize().toString());
        assertEquals("true", response.getHls().getAdaptive().toString());
        assertEquals("true", response.getRtmp().getGopCache().toString());

    }

    /**
     * GetPresetResponse getPreset(String presetName)
     */
    @Test
    public void testPresetNotExist() {
        String presetName = convertName(prePresetName);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("The requested preset does not exist");
        lssClient.getPreset(presetName);
    }

    /**
     * GetPresetResponse getPreset(String presetName)
     */
    @Test
    public void testPresetDeleted() {
        String presetName = convertName(prePresetName);
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

        lssClient.deletePreset(presetName);

        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("The requested preset does not exist");
        lssClient.getPreset(presetName);
    }

    /**
     * GetPresetResponse getPreset(String presetName)
     */
    @Test
    public void testPresetNameNull() {
        String presetName = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string");
        lssClient.getPreset(presetName);
    }

    /**
     * GetPresetResponse getPreset(request)
     */
    @Test
    public void testRequestNull() {
        GetPresetRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter request should NOT be null");
        lssClient.getPreset(request);
    }

    /**
     * GetPresetResponse getPreset(String presetName)
     */
    @Test
    public void testPresetNameEmpty() {
        String presetName = "";
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string");
        lssClient.getPreset(presetName);
    }

    /**
     * GetPresetResponse getPreset(String presetName)
     */
    @Test
    public void testPresetNameStringNull() {
        String presetName = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("The requested preset does not exist");
        lssClient.getPreset(presetName);
    }



}
