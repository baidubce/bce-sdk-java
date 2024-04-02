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

import com.baidubce.services.media.model.ListNotificationsResponse;
import com.baidubce.services.media.model.Notification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The tests of notification
 */
public class NotificationTest extends AbstractMediaTest {

    public String NOTIFICATION_ENDPOINT = "http://bce.baidu.com";

    public String name = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        name = convertName("testNotification");
        String endpoint = NOTIFICATION_ENDPOINT;
        mediaClient.createNotification(name, endpoint);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        mediaClient.deleteNotification(name);
    }

    @Test
    public void testListNotification() {
        ListNotificationsResponse resp = mediaClient.listNotification();
        for (Notification notifciation : resp.getNotifications()) {
            System.out.println(notifciation.getName());
        }
        assertTrue("list notification size should be greater than 0. ", resp.getNotifications().size() > 0);
    }

    @Test
    public void testQueryNotification() {
        assertEquals(mediaClient.getNotification(name).getEndpoint(), NOTIFICATION_ENDPOINT);
    }

    @Test
    public void testCreateNotification() {
        String name = convertName("testNotification");
        String endpoint = NOTIFICATION_ENDPOINT;
        mediaClient.createNotification(name, endpoint);
        assertEquals(mediaClient.getNotification(name).getEndpoint(), NOTIFICATION_ENDPOINT);
        mediaClient.deleteNotification(name);

    }

}
