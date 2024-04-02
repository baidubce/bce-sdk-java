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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.baidubce.services.media.model.UpdatePresetRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.ListPresetsResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

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
    public void testDeletePreset() {
        presetName = convertName("ForTestRemovePreset");
        createPreset();
        mediaClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        presetName = null;
    }

}
