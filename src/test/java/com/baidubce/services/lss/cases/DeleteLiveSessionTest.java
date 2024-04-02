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
import com.baidubce.services.lss.model.DeleteSessionRequest;
import com.baidubce.services.lss.model.GetSessionResponse;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.ListPresetsResponse;
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
public class DeleteLiveSessionTest extends AbstractLssTest {
    private String sessionId = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        String presetName = convertName(prePresetName);
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

        description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
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

    /**
     *  DeleteSessionResponse deleteSession(String sessionId)
     */
    @Test
    public void testDeleteSessionNormal1() {
        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getStatus(), "READY");
        lssClient.deleteSession(sessionId);
    }

    /**
     *  DeleteSessionResponse deleteSession(DeleteSessionRequest request)
     */
    @Test
    public void testDeleteSessionNormal2() {
        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getStatus(), "READY");
        DeleteSessionRequest request = new DeleteSessionRequest();
        request.setSessionId(sessionId);
        lssClient.deleteSession(request);
    }

    /**
     *  DeleteSessionResponse deleteSession(String sessionId)
     */
    @Test
    public void tesSessionIdNull() {
        sessionId = null;
        bceEx.expect(IllegalArgumentException.class);
        lssClient.deleteSession(sessionId);
    }

    /**
     *  DeleteSessionResponse deleteSession(DeleteSessionRequest request)
     */
    @Test
    public void testRequestNull() {

        DeleteSessionRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        lssClient.deleteSession(request);

    }

    /**
     *  DeleteSessionResponse deleteSession(String sessionId)
     */
    @Test
    public void tesSessionIdStringNull() {
        sessionId = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.deleteSession(sessionId);
    }

    /**
     *  DeleteSessionResponse deleteSession(String sessionId)
     */
    @Test
    public void tesSessionIdEmpty() {
        sessionId = "";
        bceEx.expect(IllegalArgumentException.class);
        lssClient.deleteSession(sessionId);
    }

    /**
     *  DeleteSessionResponse deleteSession(String sessionId)
     */
    @Test
    public void tesSessionIdNotExist() {
        sessionId = "session_not_exist";
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.deleteSession(sessionId);
    }

    /**
     *  DeleteSessionResponse deleteSession(String sessionId)
     */
    @Test
    public void testDeleteSessionPaused() {
        lssClient.pauseSession(sessionId);
        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getStatus(), "PAUSED");
        lssClient.deleteSession(sessionId);
    }

    /**
     *  DeleteSessionResponse deleteSession(String sessionId)
     */
    @Test
    public void testDeleteSessionDeleted() {
        lssClient.deleteSession(sessionId);
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.deleteSession(sessionId);

    }

}
