package com.baidubce.services.lss.cases;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.DeleteNotificationRequest;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class DeleteLiveNotificationTest extends AbstractLssTest {
    String notification = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        lssClient.getNotification(notification);
    }

    @After
    public void tearDown() throws Exception {


    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    /**
     * DeleteNotificationResponse deleteNotification(String name)
     */
    @Test
    public void testDeleteNotificaitonNormal1() {
        lssClient.deleteNotification(notification);
    }

    /**
     * DeleteNotificationResponse deleteNotification(request)
     */
    @Test
    public void testDeleteNotificaitonNormal2() {

        DeleteNotificationRequest request = new DeleteNotificationRequest();
        request.withName(notification);
        lssClient.deleteNotification(request);
    }

    /**
     * DeleteNotificationResponse deleteNotification(String name)
     */
    @Test
    public void testNotificaitonNull() {
        notification = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string");
        lssClient.deleteNotification(notification);
    }

    /**
     * DeleteNotificationResponse deleteNotification(String name)
     */
    @Test
    public void testNotificaitonEmpty() {
        notification = "";
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter name should NOT be null or empty string");
        lssClient.deleteNotification(notification);
    }

    /**
     * DeleteNotificationResponse deleteNotification(String name)
     */
    @Test
    public void testRequestNull() {

        DeleteNotificationRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter request should NOT be null");
        lssClient.deleteNotification(request);
    }

    /**
     * DeleteNotificationResponse deleteNotification(String name)
     */
    @Test
    public void testNotificaitonStringNull() {
        notification = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");
        lssClient.deleteNotification(notification);
    }

    /**
     * DeleteNotificationResponse deleteNotification(String name)
     */
    @Test
    public void testNotificaitonDeleted() {
        lssClient.deleteNotification(notification);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");
        lssClient.deleteNotification(notification);
    }

    /**
     * DeleteNotificationResponse deleteNotification(String name)
     */
    @Test
    public void testNotificaitonNotExist() {
        notification = "not_exist_notification";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");
        lssClient.deleteNotification(notification);
    }
}
