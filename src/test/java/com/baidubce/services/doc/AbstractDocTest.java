package com.baidubce.services.doc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;


public abstract class AbstractDocTest {

    static final String DOC_END_POINT = "http://doc.bce-testinternal.baidu.com";

    // Linqing's account
    static String AK = "";
    static String SK = "";

    protected DocClient docClient;

    protected String mediaId;

    public void setUp() throws Exception {
        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);

        BceClientConfiguration docConfig =
                new BceClientConfiguration().withCredentials(cred).withEndpoint(DOC_END_POINT);


        docClient = new DocClient(docConfig);

    }

}
