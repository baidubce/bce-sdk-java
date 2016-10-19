package com.baidubce.services.doc.ut;

import com.baidubce.services.doc.AbstractDocTest;
import com.baidubce.services.doc.model.ListDocumentsResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListDocumentTest extends AbstractDocTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listDocumentTest()  {
        ListDocumentsResponse response = docClient.listDocuments();
        System.out.println(response.toString());
    }


}
