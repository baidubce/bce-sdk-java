package com.baidubce.services.lss.cases;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lss.LssClient;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public abstract class AbstractLssTest {
//    static String   AK        = "5f7a9eda9d9b45eda8e64699b807f15a";
//    static String   SK        = "0f25bd473abe4151a11d97c6882b16f0";
//    static String   END_POINT = "http://lss.bce-testinternal.baidu.com";

    static String   AK        = "b24b86af9f1c443bb31b10e64e47f87b";
    static String   SK        = "90ecc503acb341d0ad0c7033e88c739b";
    static String   END_POINT = "http://lss.bj.baidubce.com";


    static  String    preNotification = "java_sdk_lss_notification";
    static  String    prePresetName  = "java_sdk_lss_preset";
    static  String    preSessionDescription = "java_sdk_session_description";

    LssClient lssClient = null;

    public void setUp() throws Exception {
        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(cred).withEndpoint(END_POINT)
                .withMaxConnections(10);
        lssClient = new LssClient(config);

    }

    protected String convertName(String key) {
        return (key + "_" + System.currentTimeMillis());
    }
}
