package com.baidubce.services.doc.ut;

import com.baidubce.services.doc.AbstractDocTest;
import com.baidubce.services.doc.model.ListNotificationsResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListNotificationTest extends AbstractDocTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listNotificationTest()  {
        ListNotificationsResponse response = docClient.listNotifications();
        System.out.println(response.toString());
    }


}
