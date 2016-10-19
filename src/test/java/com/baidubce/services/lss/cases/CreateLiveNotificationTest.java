package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.CreateNotificationRequest;
import com.baidubce.services.lss.model.GetNotificationResponse;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.ListSessionsResponse;
import com.baidubce.services.lss.model.LiveSession;
import com.baidubce.services.lss.model.Notification;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class CreateLiveNotificationTest extends AbstractLssTest {
    @Before
    public void setUp() throws Exception {
        super.setUp();
        ListSessionsResponse resp1 = lssClient.listSessions("ONGOING");
        for (LiveSession session : resp1.getSessions()) {
            if (session.getDescription().equals(preSessionDescription)) {
                System.out.println(session.toString());
                lssClient.pauseSession(session.getSessionId());
            }
        }

        ListSessionsResponse resp2 = lssClient.listSessions();
        System.out.println(resp2);
        for (LiveSession session : resp2.getSessions()) {
            if (session.getDescription().equals(preSessionDescription)) {
                lssClient.deleteSession(session.getSessionId());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        ListNotificationsResponse resp = lssClient.listNotifications();
        String name = null;
        for (Notification notification : resp.getNotifications()) {
            name = notification.getName();
            if (name.startsWith(preNotification)) {
                lssClient.deleteNotification(name);
            }
        }
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    @Test
    public void testNormal1() {
        CreateNotificationRequest createNotificationRequest = new CreateNotificationRequest();
        String name = convertName(preNotification);
        createNotificationRequest.withName(name).withEndpoint("http://www.baidu.com");
        lssClient.createNotification(createNotificationRequest);
        GetNotificationResponse getNotificationResponse = lssClient.getNotification(name);
        assertEquals(getNotificationResponse.getEndpoint(), "http://www.baidu.com");
    }

    @Test
    public void testNormal2() {
        String name = convertName(preNotification);
        lssClient.createNotification(name, "http://www.baidu.com");
        GetNotificationResponse getNotificationResponse = lssClient.getNotification(name);
        assertEquals(getNotificationResponse.getEndpoint(), "http://www.baidu.com");
    }

    @Test
    public void testAbnormal1() {
        CreateNotificationRequest createNotificationRequest = new CreateNotificationRequest();
        createNotificationRequest.setName("notification_test_12_notification_test123");
        createNotificationRequest.setEndpoint("http://www.baidu.com");
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.createNotification(createNotificationRequest);
    }
}
