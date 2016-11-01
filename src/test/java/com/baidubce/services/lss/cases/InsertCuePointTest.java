package com.baidubce.services.lss.cases;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.CreateSessionResponse;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.ListSessionsResponse;
import com.baidubce.services.lss.model.LivePreset;
import com.baidubce.services.lss.model.LivePublishInfo;
import com.baidubce.services.lss.model.LiveSession;
import com.baidubce.services.lss.model.Notification;
import com.baidubce.services.lss.model.Rtmp;

/**
 * Created by shidaiting01 on 2016/4/7.
 */
public class InsertCuePointTest extends AbstractLssTest {
    private String sessionId = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        String presetName = convertName(prePresetName);

        String description = preSessionDescription;
        Rtmp rtmp = new Rtmp();
        rtmp.setGopCache(true);

        lssClient.createForwardOnlyPreset(presetName, description, null, rtmp, null, null);
        System.out.println(presetName);

        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        publish.setPullUrl("rtmp://10.216.121.59/vod/sample.flv");

        System.out.println(presetName);
        CreateSessionResponse resp = lssClient.createSession(description, presetName, null, null, null,
                publish);
        sessionId = resp.getSessionId();


    }

    @After
    public void tearDown() throws Exception {
        ListSessionsResponse resp1 = lssClient.listSessions("ONGOING");
        for (LiveSession session : resp1.getSessions()) {
            if (session.getDescription().equals(preSessionDescription)) {
                lssClient.pauseSession(session.getSessionId());
            }
        }

        ListSessionsResponse resp2 = lssClient.listSessions();
        for (LiveSession session : resp2.getSessions()) {
            if (session.getDescription().equals(preSessionDescription)) {
                lssClient.deleteSession(session.getSessionId());
            }
        }

        ListPresetsResponse resp = lssClient.listPresets();
        String name;
        for (LivePreset preset : resp.getPresets()) {
            name = preset.getName();

            if (name.startsWith(prePresetName)) {
                lssClient.deletePreset(name);
            }
        }

        ListNotificationsResponse resp4 = lssClient.listNotifications();
        for (Notification notification : resp4.getNotifications()) {
            name = notification.getName();
            if (name.startsWith(preNotification)) {
                lssClient.deleteNotification(name);
            }
        }
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    @Test
    public void testNormal() {
        bceEx.expect(com.baidubce.BceServiceException.class);
        System.out.println(lssClient.insertCuePoint(sessionId, "callback", new HashMap<String, String>()));

    }
}
