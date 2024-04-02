package com.baidubce.services.doc.ut;

import com.baidubce.services.doc.AbstractDocTest;
import com.baidubce.services.doc.model.GetDocumentResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GetDocumentTest extends AbstractDocTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDocumentTest()  {
        String docId = "doc-ginrck7f8uwbumv";
        GetDocumentResponse response = docClient.getDocument(docId);
        System.out.println(response.toString());
    }


}
