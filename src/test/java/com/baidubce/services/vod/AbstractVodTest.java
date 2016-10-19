package com.baidubce.services.vod;

import java.io.File;
import java.io.FileNotFoundException;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.vod.VodClient;
import com.baidubce.services.vod.model.CreateMediaResourceResponse;

public abstract class AbstractVodTest {

//    static final String VOD_END_POINT = "http://vod.bce-testinternal.baidu.com";
    static final String VOD_END_POINT = "http://vod.bce-testinternal.baidu.com";
    static final String BOS_END_POINT = "http://10.105.97.15";

    // Linqing's account
    static String AK = "27add1b94dd5485d916cc866190be704";
    static String SK = "49ce4909b87441e794c24dd673a6fa17";

    protected VodClient vodClient;

    protected String mediaId;

    public void setUp() throws Exception {
        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);

        BceClientConfiguration vodConfig =
                new BceClientConfiguration().withCredentials(cred).withEndpoint(VOD_END_POINT);

        BosClientConfiguration bosConfig =
                new BosClientConfiguration().withCredentials(cred).withEndpoint(BOS_END_POINT);

        vodClient = new VodClient(vodConfig, bosConfig);

    }

    protected void createSampleMedia() throws FileNotFoundException, InterruptedException {
        File file = new File("/Users/hdp/Movies/short.mp4");
        String title = "short mp4";
        String description = "490KB";
        CreateMediaResourceResponse response = vodClient.createMediaResource(title, description, file);
        mediaId = response.getMediaId();
        String status;
        do {
            Thread.sleep(2000);
            status = vodClient.getMediaResource(mediaId).getStatus();
        } while (!status.equals("PUBLISHED"));
    }
}
