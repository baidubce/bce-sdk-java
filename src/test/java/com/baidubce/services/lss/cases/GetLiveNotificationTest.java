package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.GetNotificationRequest;
import com.baidubce.services.lss.model.GetNotificationResponse;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.Notification;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class GetLiveNotificationTest extends AbstractLssTest {
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
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testGetNotificationNormal1() {
        GetNotificationResponse resp = lssClient.getNotification(notification);
        assertEquals(resp.getEndpoint(), END_POINT);
    }

    /**
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testGetNotificationNormal2() {
        GetNotificationRequest request = new GetNotificationRequest();
        request.withName(notification);
        GetNotificationResponse resp = lssClient.getNotification(request);
        assertEquals(resp.getEndpoint(), END_POINT);
    }

    /**
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testNotificationNotExist() {
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");
        lssClient.getNotification("not_exist_notification");
    }

    /**
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testNotificationNull() {
        notification = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string");
        lssClient.getNotification(notification);
    }

    /**
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testRequestNull() {
        GetNotificationRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter request should NOT be null");
        lssClient.getNotification(request);
    }

    /**
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testNotificationEmpty() {
        notification = "";
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string");
        lssClient.getNotification(notification);
    }

    /**
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testNotificationStringNull() {
        notification = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");
        lssClient.getNotification(notification);
    }

    /**
     * GetNotificationResponse getNotification(String name)
     */
    @Test
    public void testNotificationDeleted() {
        lssClient.deleteNotification(notification);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");
        lssClient.getNotification(notification);
    }
}
