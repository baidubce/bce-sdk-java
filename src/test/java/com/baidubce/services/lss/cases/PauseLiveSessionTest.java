package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.CreateSessionResponse;
import com.baidubce.services.lss.model.GetSessionResponse;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.ListSessionsResponse;
import com.baidubce.services.lss.model.LivePreset;
import com.baidubce.services.lss.model.LivePublishInfo;
import com.baidubce.services.lss.model.LiveSession;
import com.baidubce.services.lss.model.Notification;
import com.baidubce.services.lss.model.PauseSessionRequest;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.Video;

/**
 * Created by shidaiting01 on 2015/9/18.
 */
public class PauseLiveSessionTest extends AbstractLssTest {
    private String presetName = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        presetName = convertName(prePresetName);
        String description = "test";
        Audio audio = new Audio();
        audio.setBitRateInBps(128000);
        audio.setChannels(2);
        audio.setSampleRateInHz(44100);
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("high");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(1600000);
        video.setMaxFrameRate(30f);
        video.setMaxHeightInPixel(720);
        video.setMaxWidthInPixel(1280);
        video.setSizingPolicy("stretch");
        Hls hls = new Hls();
        hls.setSegmentTimeInSecond(6);
        hls.setSegmentListSize(6);
        hls.setAdaptive(true);
        Rtmp rtmp = new Rtmp();
        rtmp.setGopCache(true);

        lssClient.createPreset(presetName, description, audio, video, hls, rtmp, null, null);

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

    /**
     * PauseSessionResponse pauseSession(String sessionId)
     */
    @Test
    public void testPauseSessionNormal1() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getStatus(), "READY");
        lssClient.pauseSession(sessionId);
        GetSessionResponse resp2 = lssClient.getSession(sessionId);
        assertEquals(resp2.getStatus(), "PAUSED");
    }

    /**
     * PauseSessionResponse pauseSession(request)
     */
    @Test
    public void testPauseSessionNormal2() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getStatus(), "READY");
        PauseSessionRequest request = new PauseSessionRequest();
        request.setSessionId(sessionId);
        lssClient.pauseSession(request);
        GetSessionResponse resp2 = lssClient.getSession(sessionId);
        assertEquals(resp2.getStatus(), "PAUSED");
    }

    /**
     * PauseSessionResponse pauseSession(sessionId)
     */
    @Test
    public void testSessionIdNull() {
        String sessionId = null;
        bceEx.expect(IllegalArgumentException.class);
        lssClient.pauseSession(sessionId);
    }

    /**
     * PauseSessionResponse pauseSession(sessionId)
     */
    @Test
    public void testRequestNull() {
        PauseSessionRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        lssClient.pauseSession(request);
    }

    /**
     * PauseSessionResponse pauseSession(sessionId)
     */
    @Test
    public void testSessionIdEmpty() {
        String sessionId = "";
        bceEx.expect(IllegalArgumentException.class);
        lssClient.pauseSession(sessionId);
    }

    /**
     * PauseSessionResponse pauseSession(sessionId)
     */
    @Test
    public void testSessionIdSringNull() {
        String sessionId = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.pauseSession(sessionId);
    }

    /**
     * PauseSessionResponse pauseSession(sessionId)
     */
    @Test
    public void testSessionIdNotExist() {
        String sessionId = "session_not_exist";
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.pauseSession(sessionId);
    }

    /**
     * PauseSessionResponse pauseSession(String sessionId)
     */
    @Test
    public void testPauseSessionPaused() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getStatus(), "READY");
        lssClient.pauseSession(sessionId);
        GetSessionResponse resp2 = lssClient.getSession(sessionId);
        assertEquals(resp2.getStatus(), "PAUSED");
        bceEx.expect(com.baidubce.BceServiceException.class);

        lssClient.pauseSession(sessionId);
    }

    /**
     * PauseSessionResponse pauseSession(String sessionId)
     */
    @Test
    public void testPauseSessionDeleted() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getStatus(), "READY");
        lssClient.deleteSession(sessionId);
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.pauseSession(sessionId);
    }

}
