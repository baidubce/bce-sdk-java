package com.baidubce.services.media.cases;

import com.baidubce.BceServiceException;
import com.baidubce.services.media.model.ListNotificationsResponse;
import com.baidubce.services.media.model.Notification;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * The test cases for creating notification
 */
public class CreateNotificationTest extends AbstractMediaTest {
    
    public String prefix = AbstractMediaTest.PRE_NAME ;

    @Before
    public void setUp() throws Exception {
        super.setUp();

    }      

    @After
    public void tearDown() throws Exception {
        ListNotificationsResponse resp = mediaClient.listNotification();
        String name;
        for (Notification notification : resp.getNotifications()) {
            name = notification.getName();
            if (name.startsWith(prefix)) {
                mediaClient.deleteNotification(name);
            }
        }     
    }


    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    /**
     * test create normal notification
     */
    @Test
    public void testCreateNotificationNormal() {
        String name = convertName(prefix);
        String endpoint = NOTIFICATION_ENDPOINT;
        mediaClient.createNotification(name, endpoint);
        checkNotificationExist(name, true);
    }


    /**
     * test create notification with long name
     */
    @Test
    public void testCreateNotificationIllegal() {
        String name = convertName(prefix) + "longlonglonglonglonglong";
        String endpoint = NOTIFICATION_ENDPOINT;
        bceEx.expect(BceServiceException.class);
        mediaClient.createNotification(name, endpoint);
    }

    
}
