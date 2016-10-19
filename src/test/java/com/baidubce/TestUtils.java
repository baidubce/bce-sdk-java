/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce;

import org.hamcrest.CustomTypeSafeMatcher;

import java.util.Date;

public class TestUtils {
    public static TimeMatcher timeGapInSecondsLessThan(Date expectedTime, int deltaInSeconds) {
        return new TimeMatcher(expectedTime, deltaInSeconds, true);
    }

    public static TimeMatcher timeGapInSecondsGreaterThan(Date expectedTime, int deltaInSeconds) {
        return new TimeMatcher(expectedTime, deltaInSeconds, false);
    }

    static class TimeMatcher extends CustomTypeSafeMatcher<Date> {
        private Date expectedTime;
        private int deltaInMillis;
        private boolean lessThan;

        public TimeMatcher(Date expectedTime, int deltaInSeconds, boolean lessThan) {
            super(String.format("the gap to %s is %s %d seconds",
                    expectedTime,
                    lessThan ? "less than" : "greater than",
                    deltaInSeconds));
            this.expectedTime = expectedTime;
            this.deltaInMillis = deltaInSeconds * 1000;
            this.lessThan = lessThan;
        }

        @Override
        protected boolean matchesSafely(Date item) {
            long timeGapInMillis = Math.abs(item.getTime() - this.expectedTime.getTime());
            if (this.lessThan) {
                return timeGapInMillis < this.deltaInMillis;
            } else {
                return timeGapInMillis > this.deltaInMillis;
            }
        }
    }
}
