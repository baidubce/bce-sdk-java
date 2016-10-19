package com.baidubce.services.doc.ut;

import com.baidubce.services.doc.model.CreateDocumentResponse;
import com.baidubce.services.doc.AbstractDocTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class CreateDocumentTest extends AbstractDocTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void uploadSmallFileTest() throws FileNotFoundException {
        File file = new File("/Users/baidu/Downloads/Python代码编码和vim工具配置.pptx");
        String title = "Python代码编码和vim工具配置";
        CreateDocumentResponse response = docClient.createDocument(file, title);
        String docId = response.getDocumentId();
        System.out.println("docId = " + docId);
    }

    @Test
    public void createPrivateDocumentTest() throws FileNotFoundException {
        File file = new File("/Users/baidu/Downloads/Python代码编码和vim工具配置.pptx");
        String title = "Python代码编码和vim工具配置";
        CreateDocumentResponse response = docClient.createDocument(file, title, "pptx", null, "PRIVATE");
        String docId = response.getDocumentId();
        System.out.println("docId = " + docId);
    }


}
