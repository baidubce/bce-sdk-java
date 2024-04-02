package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.Notification;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class ListLiveNotificationTest extends AbstractLssTest {
    String notification = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);

    }

    @After
    public void tearDown() throws Exception {
        ListNotificationsResponse resp = lssClient.listNotifications();
        String name = null;
        for (Notification noti : resp.getNotifications()) {
            name = noti.getName();
            if (name.startsWith(preNotification)) {
                lssClient.deleteNotification(name);
            }
        }
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    /**
     *  ListNotificationsResponse listNotifications()
     */
    @Test
    public void testListNotificationCount() {
        int count = 0;
        ListNotificationsResponse resp = lssClient.listNotifications();
        System.out.println(resp);
        count = resp.getNotifications().size();
        String notification1 = convertName(preNotification);
        lssClient.createNotification(notification1, END_POINT);
        String notification2 = convertName(preNotification);
        lssClient.createNotification(notification2, END_POINT);
        resp = lssClient.listNotifications();
        System.out.println(resp);
        int count1 = -1;
        count1 = resp.getNotifications().size();
        assertEquals(count + 2, count1);
        int flag1 = 0;
        int flag2 = 0;
        String name = null;
        for (Notification noti : resp.getNotifications()) {
            name = noti.getName();
            if (name.equals(notification1)) {
                flag1 = 1;
            }
            if (name.equals(notification2)) {
                flag2 = 1;
            }
        }
        assertEquals(flag1, 1);
        assertEquals(flag2, 1);
    }

    /**
     *  ListNotificationsResponse listNotifications()
     */
    @Test
    public void testListNotificationInfo() {

        ListNotificationsResponse resp = lssClient.listNotifications();
        int flag = 0;
        String name = null;
        for (Notification noti : resp.getNotifications()) {
            name = noti.getName();
            if (name.equals(notification)) {
                flag = 1;
                assertEquals(noti.getEndpoint(), END_POINT);
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *  ListNotificationsResponse listNotifications()
     */
    @Test
    public void testDeletedNotificationNotList() {
        lssClient.deleteNotification(notification);
        ListNotificationsResponse resp = lssClient.listNotifications();
        int flag = 1;
        String name = null;
        for (Notification noti : resp.getNotifications()) {
            name = noti.getName();
            if (name.equals(notification)) {
                flag = 0;
            }
        }
        assertEquals(flag, 1);
    }
}
