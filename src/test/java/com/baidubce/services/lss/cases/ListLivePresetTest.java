package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.LivePreset;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.Video;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class ListLivePresetTest extends AbstractLssTest {
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
                System.out.println(presetName);
                lssClient.deletePreset(presetName);
            }
        }
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    /**
     * ListPresetsResponse listPresets()
     */
    @Test
    public void testListPresetCount() {
        int count0 = 0;
        ListPresetsResponse  resp = lssClient.listPresets();
        List<LivePreset> presets = resp.getPresets();
        count0 = presets.size();
        String presetName1 = convertName(prePresetName);
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

        lssClient.createPreset(presetName1, description, audio, video, hls, rtmp, null, null);

        String presetName2 = convertName(prePresetName);
        lssClient.createPreset(presetName2, description, audio, video, hls, rtmp, null, null);

        resp = lssClient.listPresets();
        presets = resp.getPresets();
        int count1 = -1;
        count1 = presets.size();
        assertEquals(count0 + 2, count1);
        String presetName;
        int flag1 = 0;
        int flag2 = 0;
        for (LivePreset preset : resp.getPresets()) {
            presetName = preset.getName();
            if (presetName.equals(presetName1) ) {
                flag1 = 1;
            } else if (presetName.equals(presetName2)) {
                flag2 = 1;
            }
        }
        assertEquals(flag1, 1);
        assertEquals(flag2, 1);
    }

    /**
     * ListPresetsResponse listPresets()
     */
    @Test
    public void testListPresetInfo() {
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

        ListPresetsResponse response = lssClient.listPresets();
        int flag = 0;
        String name;
        for (LivePreset preset : response.getPresets()) {
            name = preset.getName();
            if (name.equals(presetName) ) {
                flag = 1;
                assertEquals("test", preset.getDescription());
                assertEquals("128000", preset.getAudio().getBitRateInBps().toString());
                assertEquals("2", preset.getAudio().getChannels().toString());
                assertEquals("44100", preset.getAudio().getSampleRateInHz().toString());
                assertEquals("h264", preset.getVideo().getCodec());
                assertEquals("high", preset.getVideo().getCodecOptions().getProfile());
                assertEquals("1600000", preset.getVideo().getBitRateInBps().toString());
                assertEquals("30.0", preset.getVideo().getMaxFrameRate().toString());
                assertEquals("1280", preset.getVideo().getMaxWidthInPixel().toString());
                assertEquals("720", preset.getVideo().getMaxHeightInPixel().toString());
                assertEquals("stretch", preset.getVideo().getSizingPolicy());
                assertEquals("6", preset.getHls().getSegmentTimeInSecond().toString());
                assertEquals("6", preset.getHls().getSegmentListSize().toString());
                assertEquals("true", preset.getHls().getAdaptive().toString());
                assertEquals("true", preset.getRtmp().getGopCache().toString());
            }
        }
        assertEquals(flag, 1);
    }

    /**
     * ListPresetsResponse listPresets()
     */
    @Test
    public void testDeletedPresetNotList() {
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

        ListPresetsResponse response = lssClient.listPresets();
        int flag = 1;
        String name;
        for (LivePreset preset : response.getPresets()) {
            name = preset.getName();
            if (name.equals(presetName)) {
                flag = 0;
            }
        }
        assertEquals(flag, 1);
    }

}
