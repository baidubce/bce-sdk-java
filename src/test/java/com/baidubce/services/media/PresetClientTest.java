/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.ListPresetsResponse;

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
    }

    @Test
    public void testCreatePreset() {
        presetName = convertName("ForTestCreatePreset");
        createPreset();
    }

    @Test
    public void testGetPreset() {
        for (GetPresetResponse preset : mediaClient.listPresets().getPresets()) {
            GetPresetResponse resp = mediaClient.getPreset(preset.getPresetName());
            System.out.println(resp);
            assertTrue("Failed to get preset: " + preset.getPresetName(), resp
                    .getPresetName().equals(preset.getPresetName()));
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
