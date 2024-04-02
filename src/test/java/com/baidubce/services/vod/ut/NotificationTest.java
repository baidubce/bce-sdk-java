/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.vod.ut;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.CreateNotificationResponse;
import com.baidubce.services.vod.model.GetNotificationResponse;
import com.baidubce.services.vod.model.ListNotificationsResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotificationTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        vodClient.createNotification("gf_test_notification", "http://www.baidu.com");
    }

    @After
    public void tearDown() throws Exception {
        vodClient.deleteNotification("gf_test_notification");
    }

    @Test
    public void testCreateNotification() {
        CreateNotificationResponse res = vodClient.createNotification("gf_test_notification2", "http://www.baidu.com");
        vodClient.deleteNotification("gf_test_notification2");
    }

    @Test
    public void testCreateNotificationInvalidUrl() {
        try {
            CreateNotificationResponse res = vodClient.createNotification("gf_test_notification1", "www.baidu.com");
        } catch (Exception e) {
            assert (true);
            return;
        }
        assert (false);
    }

    @Test
    public void testCreateNotificationInvalidName() {
        try {
            CreateNotificationResponse res = vodClient.createNotification("gf_test_notification", "www.baidu.com");
        } catch (Exception e) {
            assert (true);
            return;
        } 
        assert (false);
    }

    @Test
    public void testQueryNotification() {
        GetNotificationResponse res = vodClient.getNotification("gf_test_notification");
        System.out.println(res);
    }

    @Test
    public void testListNotification() {
        ListNotificationsResponse res = vodClient.listNotifications();
        System.out.println(res);
    }

}
