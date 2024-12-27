/*
 * Copyright 2015 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.media;

import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.CodecOptions;
import com.baidubce.services.media.model.CreatePresetRequest;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.ListPresetsResponse;
import com.baidubce.services.media.model.UpdatePresetRequest;
import com.baidubce.services.media.model.Video;
import com.baidubce.services.media.model.VolumeAdjust;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * The tests of preset
 */
public class PresetClientTest extends AbstractMediaTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testListPresets() {
        ListPresetsResponse resp = mediaClient.listPresets();
        for (GetPresetResponse preset : resp.getPresets()) {
            System.out.println(preset.getPresetName());
        }
        assertTrue("list presets size should be greater than 0. ", resp.getPresets().size() > 0);
    }

    @Test
    public void testCreatePreset() {
        presetName = convertName("ForTestCreatePreset");
        createPreset();
    }

    @Test
    public void testCreateAudioInsertPreset() {
        CreatePresetRequest request = new CreatePresetRequest();
        request.setPresetName("test_1111_insert");
        request.setDescription("audio insert");
        request.setContainer("mp4");
        Audio audio = new Audio();
        audio.setBitRateInBps(128000);
        audio.setSampleRateInHz(44100);
        audio.setChannels(2);
        audio.setCodec("aac");
        List<Audio.InputIndex> inputIndices = new ArrayList<>();
        Audio.InputIndex inputIndex = new Audio.InputIndex();
        inputIndex.setInputIndex(2);
        inputIndices.add(inputIndex);
        audio.setMapping(inputIndices);
        request.setAudio(audio);
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("baseline");
        video.setCodecOptions(codecOptions);
        video.setRateControl("crf");
        video.setCrf(24);
        video.setSizingPolicy("keep");
        video.setAutoAdjustResolution(true);
        request.setVideo(video);
        mediaClient.createPreset(request);
        checkPresetExist("test_1111_insert", true);
    }

    @Test
    public void testCreateAudioMixPreset() {
        CreatePresetRequest request = new CreatePresetRequest();
        request.setPresetName("audio_mix_preset_3");
        request.setDescription("audio insert");
        request.setContainer("mp4");

        Audio audio = new Audio();
        Audio.Tracks firstTrack = new Audio.Tracks();
        firstTrack.setBitRateInBps(128000);
        firstTrack.setSampleRateInHz(44100);
        firstTrack.setChannels(2);
        firstTrack.setCodec("aac");
        VolumeAdjust firstTrackVolumeAdjust = new VolumeAdjust();
        firstTrackVolumeAdjust.setNorm(true);
        firstTrackVolumeAdjust.setGain(-5);
        firstTrack.setVolumeAdjust(firstTrackVolumeAdjust);
        Audio.InputIndex firstTrackInputIndex = new Audio.InputIndex();
        firstTrackInputIndex.setInputIndex(1);
        List<Audio.InputIndex> firstTrackindices = new ArrayList<>();
        firstTrackindices.add(firstTrackInputIndex);
        firstTrack.setMapping(firstTrackindices);

        Audio.Tracks secondTrack = new Audio.Tracks();
        secondTrack.setBitRateInBps(128000);
        secondTrack.setSampleRateInHz(44100);
        secondTrack.setChannels(2);
        secondTrack.setCodec("aac");
        VolumeAdjust secondTrackTrackVolumeAdjust = new VolumeAdjust();
        secondTrackTrackVolumeAdjust.setNorm(true);
        secondTrackTrackVolumeAdjust.setGain(-5);
        secondTrack.setVolumeAdjust(secondTrackTrackVolumeAdjust);
        Audio.InputIndex secondTrackInputIndex = new Audio.InputIndex();
        secondTrackInputIndex.setInputIndex(2);
        List<Audio.InputIndex> secondTrackindices = new ArrayList<>();
        secondTrackindices.add(secondTrackInputIndex);
        secondTrack.setMapping(secondTrackindices);

        List<Audio.Tracks> tracks = new ArrayList<>();
        tracks.add(firstTrack);
        tracks.add(secondTrack);
        audio.setMixAllTracks(false);
        audio.setTracks(tracks);
        request.setAudio(audio);
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("baseline");
        video.setCodecOptions(codecOptions);
        video.setRateControl("crf");
        video.setCrf(23);
        video.setSizingPolicy("keep");
        video.setAutoAdjustResolution(true);
        request.setVideo(video);
        mediaClient.createPreset(request);
        checkPresetExist("audio_mix_preset", true);
    }


    @Test
    public void testCreatePreset2() throws JsonProcessingException {
        presetName = convertName("PresetPlayback");
        createPresetWithPlaybackSpeed();
        GetPresetResponse resp = mediaClient.getPreset(presetName);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertNotNull("playbackSpeed should not be null. ", resp.getVideo().getPlaybackSpeed());
        // 测试完后，被创建的Preset会被自动清理掉。
    }

    @Test
    public void testCreatePreset3() {
        presetName = convertName("PresetWithAllParams");
        createPresetWithFullParams();
        // 测试完后，被创建的Preset会被自动清理掉。
    }

    @Test
    public void testUpdatePreset() {
        presetName = convertName("PresetForUpdate");
        String description = "update";
        createPreset();
        GetPresetResponse preset = mediaClient.getPreset(presetName);
        preset.setDescription(description);
        mediaClient.updatePreset(new UpdatePresetRequest().withPreset(preset));
        assertEquals(mediaClient.getPreset(presetName).getDescription(), description);
    }

    @Test
    public void testCreatePresetCrf() {
        presetName = convertName("PresetWithCrf");
        createPresetWithCrf();
        // 测试完后，被创建的Preset会被自动清理掉。
    }

    @Test
    public void testGetPreset() {
        for (GetPresetResponse preset : mediaClient.listPresets().getPresets()) {
            GetPresetResponse resp = mediaClient.getPreset(preset.getPresetName());
            System.out.println(resp);
            assertEquals("Failed to get preset: " + preset.getPresetName(),
                    resp.getPresetName(), preset.getPresetName());
        }
    }

    @Test
    public void testGetPresetWithName() {
        GetPresetResponse resp = mediaClient.getPreset("audio_mix_preset");
        System.out.println(resp);

    }

    @Test
    public void testDeletePreset() {
        presetName = convertName("ForTestRemovePreset");
        createPreset();
        mediaClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        presetName = null;
    }

}
