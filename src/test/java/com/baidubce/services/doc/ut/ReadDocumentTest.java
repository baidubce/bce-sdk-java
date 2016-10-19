package com.baidubce.services.doc.ut;

import com.baidubce.services.doc.AbstractDocTest;
import com.baidubce.services.doc.model.ReadDocumentResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadDocumentTest extends AbstractDocTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readDocumentTest()  {
        String docId = "doc-ggesvh5y6v45mnv";
        ReadDocumentResponse response = docClient.readDocument(docId);
        System.out.println(response.toString());
    }

    @Test
    public void readDocumentWithExpiredTimeTest()  {
        String docId = "doc-ginrck7f8uwbumv";
        ReadDocumentResponse response = docClient.readDocument(docId, 60);
        System.out.println(response.toString());
    }


}
