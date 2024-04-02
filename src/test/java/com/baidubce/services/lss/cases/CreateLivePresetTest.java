package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.Capture;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.CreatePresetRequest;
import com.baidubce.services.lss.model.GetPresetResponse;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.LivePreset;
import com.baidubce.services.lss.model.LiveThumbnail;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.Target;
import com.baidubce.services.lss.model.Video;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class CreateLivePresetTest extends AbstractLssTest {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        ListPresetsResponse resp = lssClient.listPresets();
        String presetName = null;
        for (LivePreset preset : resp.getPresets()) {
            presetName = preset.getName();
            if (presetName.startsWith(prePresetName)) {
                System.out.println(presetName);
                lssClient.deletePreset(presetName);
            }
        }
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    @Test
    public void testNormal1() {
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

    @Test
    public void testCreatePresetNormalAllParams2() {
        CreatePresetRequest request = new CreatePresetRequest();
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
        LiveThumbnail thumbnail = new LiveThumbnail();
        Target target = new Target();
        Capture capture = new Capture();
        target.setFormat("jpg");
        target.setHeightInPixel(640);
        target.setWidthInPixel(640);
        target.setSizingPolicy("stretch");
        capture.setMode("manual");
        capture.setStartTimeInSecond(5);
        capture.setIntervalInSecond(1);
        capture.setEndTimeInSecond(10);
        thumbnail.setTarget(target);
        thumbnail.setCapture(capture);
        request.setName(presetName);
        request.setDescription(description);
        request.setAudio(audio);
        request.setVideo(video);
        request.setHls(hls);
        request.setRtmp(rtmp);
        request.setThumbnail(thumbnail);
        lssClient.createPreset(request);
        GetPresetResponse response = lssClient.getPreset(presetName);
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


    @Test
    public void testForwardOnlyNormal1() {
        String presetName = convertName(prePresetName);
        String description = "test";
        Hls hls = new Hls();
        hls.setSegmentTimeInSecond(6);
        hls.setSegmentListSize(6);
        hls.setAdaptive(false);
        Rtmp rtmp = new Rtmp();
        rtmp.setGopCache(true);

        LiveThumbnail thumbnail = new LiveThumbnail();
        Target target = new Target();
        Capture capture = new Capture();
        target.setFormat("jpg");
        target.setHeightInPixel(640);
        target.setWidthInPixel(640);
        target.setSizingPolicy("stretch");
        capture.setMode("manual");
        capture.setStartTimeInSecond(5);
        capture.setIntervalInSecond(1);
        capture.setEndTimeInSecond(10);
        thumbnail.setTarget(target);
        thumbnail.setCapture(capture);
        lssClient.createForwardOnlyPreset(presetName, description, hls, rtmp, thumbnail, null);

        GetPresetResponse response = lssClient.getPreset(presetName);
        assertEquals("true", response.getForwardOnly().toString());
        assertEquals("test", response.getDescription());
        assertEquals("6", response.getHls().getSegmentTimeInSecond().toString());
        assertEquals("6", response.getHls().getSegmentListSize().toString());
        assertEquals("false", response.getHls().getAdaptive().toString());
        assertEquals("true", response.getRtmp().getGopCache().toString());

    }

    @Test
    public void testCreatePresetNormalAllParams3() {
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
        LiveThumbnail thumbnail = new LiveThumbnail();
        Target target = new Target();
        Capture capture = new Capture();
        target.setFormat("jpg");
        target.setHeightInPixel(640);
        target.setWidthInPixel(640);
        target.setSizingPolicy("stretch");
        capture.setMode("manual");
        capture.setStartTimeInSecond(5);
        capture.setIntervalInSecond(1);
        capture.setEndTimeInSecond(10);
        thumbnail.setTarget(target);
        thumbnail.setCapture(capture);
        lssClient.createPreset(presetName, description, audio, video, hls, rtmp, thumbnail, null);
        GetPresetResponse response = lssClient.getPreset(presetName);
        assertEquals("false", response.getForwardOnly().toString());
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

    @Test
    public void testCreatePresetForwardOnly1() {
        CreatePresetRequest request = new CreatePresetRequest();
        String presetName = convertName(prePresetName);
        String description = "test";
        Hls hls = new Hls();
        hls.setSegmentTimeInSecond(6);
        hls.setSegmentListSize(6);
        hls.setAdaptive(false);
        Rtmp rtmp = new Rtmp();
        rtmp.setGopCache(true);
        request.setName(presetName);
        request.setDescription(description);
        request.setHls(hls);
        request.setRtmp(rtmp);
        request.setForwardOnly(true);
        lssClient.createPreset(request);
        GetPresetResponse response = lssClient.getPreset(presetName);
        assertEquals("true", response.getForwardOnly().toString());
        assertEquals("test", response.getDescription());
        assertEquals(null, response.getAudio());
        assertEquals(null, response.getVideo());
        assertEquals("6", response.getHls().getSegmentTimeInSecond().toString());
        assertEquals("6", response.getHls().getSegmentListSize().toString());
        assertEquals("false", response.getHls().getAdaptive().toString());
        assertEquals("true", response.getRtmp().getGopCache().toString());
    }
}
