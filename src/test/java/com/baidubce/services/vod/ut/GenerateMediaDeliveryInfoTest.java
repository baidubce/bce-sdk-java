package com.baidubce.services.vod.ut;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GenerateMediaDeliveryInfoResponse;
import com.baidubce.services.vod.model.GetPlayerCodeResponse;
import com.baidubce.services.vod.model.PlaybackCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class GenerateMediaDeliveryInfoTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gfxj1qfuis44avyt";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGenerateMediaDeliveryInfo() {
        GenerateMediaDeliveryInfoResponse response = vodClient.generateMediaDeliveryInfo(mediaId, null);
        System.out.println(response);
//        String htmlCode = null;
//        String flashCode = null;
//        String standalonePage = null;
//        for (PlaybackCode code : response.getCodes()) {
//            if (code.getCodeType().equals("html")) {
//                htmlCode = code.getSourceCode();
//            } else if (code.getCodeType().equals("flash")) {
//                flashCode = code.getSourceCode();
//
//            } else if (code.getCodeType().equals("url")) {
//                standalonePage = code.getSourceCode();
//            } else {
//                fail("Unexpected code = " + code);
//            }
//        }
    }
}
