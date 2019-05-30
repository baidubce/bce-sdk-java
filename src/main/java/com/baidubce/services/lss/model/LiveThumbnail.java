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

package com.baidubce.services.lss.model;

import java.io.Serializable;

public class LiveThumbnail implements Serializable {
    private Target target = null;

    private Capture capture = null;

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public LiveThumbnail withTarget(Target target) {
        this.target = target;
        return this;
    }

    public Capture getCapture() {
        return capture;
    }

    public void setCapture(Capture capture) {
        this.capture = capture;
    }

    public LiveThumbnail withCapture(Capture capture) {
        this.capture = capture;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveThumbnail {\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("    capture: ").append(capture).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
