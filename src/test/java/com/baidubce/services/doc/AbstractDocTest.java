package com.baidubce.services.doc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;


public abstract class AbstractDocTest {

    static final String DOC_END_POINT = "http://doc.bce-testinternal.baidu.com";

    // Linqing's account
    static String AK = "6107180b80ae42c0a821a5fe53ba615e";
    static String SK = "a88ed698c0fd4774a4f1bdcebcae5922";

    protected DocClient docClient;

    protected String mediaId;

    public void setUp() throws Exception {
        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);

        BceClientConfiguration docConfig =
                new BceClientConfiguration().withCredentials(cred).withEndpoint(DOC_END_POINT);


        docClient = new DocClient(docConfig);

    }

}
