package com.baidubce.services.lss.cases;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.DeletePresetRequest;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.Video;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class DeleteLivePresetTest extends AbstractLssTest {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    /**
     * DeletePresetResponse deletePreset(String presetName)
     */
    @Test
    public void testDeletePresetNormal1() {
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
    }

    /**
     * DeletePresetResponse deletePreset(DeletePresetRequest request)
     */
    @Test
    public void testDeletePresetNormal2() {
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

        DeletePresetRequest request = new DeletePresetRequest();

        request.setName(presetName);
        lssClient.deletePreset(request);

    }

    /**
     * DeletePresetResponse deletePreset(String presetName)
     */
    @Test
    public void testDeletePresetNotExist() {
        String presetName = convertName(prePresetName);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("The requested preset does not exist");
        lssClient.deletePreset(presetName);
    }

    /**
     * DeletePresetResponse deletePreset(String presetName)
     */
    @Test
    public void testDeletePresetDeleted() {
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
        lssClient.deletePreset(presetName);
    }

    /**
     * DeletePresetResponse deletePreset(String presetName)
     */
    @Test
    public void testDeleteSystemPreset() {
        String presetName = "lss.rtmp_hls_1280x720";

        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("The requested preset does not exist");
        lssClient.deletePreset(presetName);
    }

    /**
     * DeletePresetResponse deletePreset(String presetName)
     */
    @Test
    public void testPresetNameNull() {
        String presetName = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string.");
        lssClient.deletePreset(presetName);
    }

    /**
     * DeletePresetResponse deletePreset(request)
     */
    @Test
    public void testRequestNull() {
        DeletePresetRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter request should NOT be null");
        lssClient.deletePreset(request);
    }


    /**
     * DeletePresetResponse deletePreset(String presetName)
     */
    @Test
    public void testPresetNameEmpty() {
        String presetName = "";
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string.");
        lssClient.deletePreset(presetName);
    }

    /**
     * DeletePresetResponse deletePreset(String presetName)
     */
    @Test
    public void testPresetNameStringNull() {
        String presetName = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("The requested preset does not exist");
        lssClient.deletePreset(presetName);
    }

}
