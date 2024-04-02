package com.baidubce.services.doc.ut;

import com.baidubce.services.doc.AbstractDocTest;
import com.baidubce.services.doc.model.CreateNotificationResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CreateNotificationTest extends AbstractDocTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createNotificationTest() throws FileNotFoundException {
        String url = "http://www.baidu.com";
        String title = "testnotification";
        CreateNotificationResponse response = docClient.createNotification(title, url);
        System.out.println(response.toString());
    }


}
