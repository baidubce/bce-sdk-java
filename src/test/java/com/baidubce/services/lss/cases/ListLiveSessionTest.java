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
import com.baidubce.services.lss.model.ListSessionsRequest;
import com.baidubce.services.lss.model.ListSessionsResponse;
import com.baidubce.services.lss.model.LivePreset;
import com.baidubce.services.lss.model.LivePublishInfo;
import com.baidubce.services.lss.model.LiveSession;
import com.baidubce.services.lss.model.Notification;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.Video;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class ListLiveSessionTest extends AbstractLssTest {
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
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionNormal1() {
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        int flag = 0;
        ListSessionsResponse resp = lssClient.listSessions();
        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionNormal2() {
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        int flag = 0;
        ListSessionsRequest request = new ListSessionsRequest();
        ListSessionsResponse resp = lssClient.listSessions(request);

        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionReady1() {
        String status = "READY";
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        int flag = 0;
        ListSessionsResponse resp = lssClient.listSessions(status);
        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionReady2() {
        String status = "READY";
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        int flag = 0;
        ListSessionsRequest request = new ListSessionsRequest();
        request.setStatus(status);
        ListSessionsResponse resp = lssClient.listSessions(request);
        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionPaused1() {
        String status = "PAUSED";
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        lssClient.pauseSession(sessionId);
        GetSessionResponse response1 = lssClient.getSession(sessionId);
        assertEquals(response1.getStatus(), "PAUSED");
        int flag = 0;
        ListSessionsResponse resp = lssClient.listSessions(status);

        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionPaused2() {
        String status = "PAUSED";
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        lssClient.pauseSession(sessionId);
        GetSessionResponse response1 = lssClient.getSession(sessionId);
        assertEquals(response1.getStatus(), "PAUSED");
        int flag = 0;
        ListSessionsRequest request = new ListSessionsRequest();
        request.setStatus(status);
        ListSessionsResponse resp = lssClient.listSessions(request);
        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionCount() {
        int count = 0;
        int count1 = -1;
        ListSessionsResponse resp = lssClient.listSessions();
        count = resp.getSessions().size();
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId1 = response.getSessionId();
        response = lssClient.createSession(description, presetName, notification, null, null, publish);
        String sessionId2 = response.getSessionId();
        lssClient.pauseSession(sessionId2);
        int flag1 = 0;
        int flag2 = 0;
        resp = lssClient.listSessions();
        count1 = resp.getSessions().size();

        for (LiveSession session : resp.getSessions()) {
            String sessionId3 = session.getSessionId();
            if (sessionId3.equals(sessionId1)) {
                flag1 = 1;
            }
            if (sessionId3.equals(sessionId2)) {
                flag2 = 1;
            }
        }
        assertEquals(flag1, 1);
        assertEquals(flag2, 1);
        assertEquals(count1, count + 2);
    }


    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testListSessionInfo() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        ListSessionsResponse resp = lssClient.listSessions();
        int flag = 0;
        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {
                flag = 1;
                assertEquals(session.getPreset(), presetName);
                assertEquals(session.getDescription(), description);
                assertEquals(session.getNotification(), notification);

                assertEquals(session.getStatus(), "READY");
            }
        }
        assertEquals(flag, 1);
    }



    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testClosedSessionNotList() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        lssClient.deleteSession(sessionId);
        ListSessionsResponse resp = lssClient.listSessions();
        int flag = 1;
        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testRequestNull() {

        ListSessionsRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter request should NOT be null");
        lssClient.listSessions(request);
    }


    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testStatusNull() {

        String status = null;
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse response = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = response.getSessionId();
        lssClient.pauseSession(sessionId);
        GetSessionResponse response1 = lssClient.getSession(sessionId);
        assertEquals(response1.getStatus(), "PAUSED");
        int flag = 0;
        ListSessionsResponse resp = lssClient.listSessions(status);
        for (LiveSession session : resp.getSessions()) {
            String sessionId1 = session.getSessionId();
            if (sessionId1.equals(sessionId)) {

                flag = 1;
            }
        }
        assertEquals(flag, 1);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testStatusEmpty() {

        String status = "";
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("The parameter status should NOT be empty string");
        lssClient.listSessions(status);
    }

    /**
     *ListSessionsResponse listSessions()
     */
    @Test
    public void testStatusStringNull() {

        String status = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("No enum constant");
        lssClient.listSessions(status);
    }



}
