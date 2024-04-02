package com.baidubce.services.doc.ut;

import com.baidubce.services.doc.AbstractDocTest;
import com.baidubce.services.doc.model.GetNotificationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetNotificationTest extends AbstractDocTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNotificationTest()  {
        String name = "testnotification";

        GetNotificationResponse response = docClient.getNotification(name);
        System.out.println(response.toString());
    }


}
