package com.baidubce.services.vod.ut;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetPlayerCodeResponse;
import com.baidubce.services.vod.model.PlaybackCode;

public class GetPlayerCodeTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gaqi5dznriez85n3";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPlayerCode() {
        GetPlayerCodeResponse response = vodClient.getPlayerCode(mediaId, 160, 90, true);
        System.out.println(response);
        String htmlCode = null;
        String flashCode = null;
        String standalonePage = null;
        for (PlaybackCode code : response.getCodes()) {
            if (code.getCodeType().equals("html")) {
                htmlCode = code.getSourceCode();
            } else if (code.getCodeType().equals("flash")) {
                flashCode = code.getSourceCode();

            } else if (code.getCodeType().equals("url")) {
                standalonePage = code.getSourceCode();
            } else {
                fail("Unexpected code = " + code);
            }
        }
    }
}
