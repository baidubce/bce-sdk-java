package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.CreateSessionResponse;
import com.baidubce.services.lss.model.GetSessionRequest;
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
import com.baidubce.services.lss.model.Watermarks;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class CreateLiveSessionTest extends AbstractLssTest {
    private String presetName = null;
    private String presetName1 = null;
    private List<String> presetList = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        presetName = convertName(prePresetName);
        presetName1 = convertName(prePresetName) + "_1";
        presetList = new ArrayList<String>();
        presetList.add(presetName);
        presetList.add(presetName1);
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
        lssClient.createPreset(presetName1, description, audio, video, hls, rtmp, null, null);
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
    public void testCreateSessionAllParam1() {
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        System.out.println(resp.toString());
        String sessionId = resp.getSessionId();

        assertEquals(resp.getPreset(), presetName);
        assertEquals(resp.getDescription(), description);
        assertEquals(resp.getNotification(), notification);
        assertEquals(resp.getSecurityPolicy(), "default");
        assertEquals(resp.getPublish().getRegion(), "bj");

        assertEquals(resp.getStatus(), "READY");

    }

    @Test
    public void testCreateSessionWithRecording() {
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null,
                "lss_java_sdk", publish);
        System.out.println(resp.toString());
        String sessionId = resp.getSessionId();

        assertEquals(resp.getPreset(), presetName);
        assertEquals(resp.getDescription(), description);
        assertEquals(resp.getNotification(), notification);
        assertEquals(resp.getSecurityPolicy(), "default");
        assertEquals(resp.getPublish().getRegion(), "bj");
        assertEquals(resp.getRecording(), "lss_java_sdk");

        assertEquals(resp.getStatus(), "READY");

    }

    @Test
    public void testCreateSessionWithPresets() {
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetList, notification, null,
                "lss_java_sdk", publish);
        System.out.println(resp.toString());
        assertEquals(resp.getDescription(), description);
        assertEquals(resp.getNotification(), notification);
        assertEquals(resp.getSecurityPolicy(), "default");
        assertEquals(resp.getPublish().getRegion(), "bj");
        assertEquals(resp.getRecording(), "lss_java_sdk");
        assertEquals(resp.getStatus(), "READY");

        String sessionId = resp.getSessionId();
        GetSessionRequest getSessionRequest = new GetSessionRequest();
        getSessionRequest.setSessionId(sessionId);
        GetSessionResponse response = lssClient.getSession(getSessionRequest);
        System.out.println(response.toString());
        assertEquals(response.getPresets().size(), 2);
        assertEquals(response.getPresets().get("L0"), presetName);
        assertEquals(response.getPresets().get("L1"), presetName1);

        System.out.println(lssClient.refreshSession(sessionId));
    }

    @Test
    public void testCreateSessionWithThumbnailAndWatermarks() {
        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        Watermarks watermarks = new Watermarks();
        watermarks.setImage(Arrays.asList("lss_sdk_java_imw"));
        watermarks.setTimestamp(Arrays.asList("lss_sdk_java_tsw"));
        CreateSessionResponse resp = lssClient.createSession(description, presetList, notification, null,
                "lss_java_sdk", publish, "lss_java_sdk_thumbnail", watermarks);
        System.out.println(resp.toString());
        String sessionId = resp.getSessionId();

        assertEquals(resp.getDescription(), description);
        assertEquals(resp.getNotification(), notification);
        assertEquals(resp.getSecurityPolicy(), "default");
        assertEquals(resp.getPublish().getRegion(), "bj");
        assertEquals(resp.getRecording(), "lss_java_sdk");
        assertEquals(resp.getThumbnail(), "lss_java_sdk_thumbnail");
        assertEquals(resp.getWatermarks().getImage().size(), 1);
        assertEquals(resp.getWatermarks().getImage().get(0), "lss_sdk_java_imw");
        assertEquals(resp.getWatermarks().getTimestamp().size(), 1);
        assertEquals(resp.getWatermarks().getTimestamp().get(0), "lss_sdk_java_tsw");
        assertEquals(resp.getPresets().size(), 2);
        assertEquals(resp.getPresets().get("L0"), presetName);
        assertEquals(resp.getPresets().get("L1"), presetName1);
        assertEquals(resp.getStatus(), "READY");

        GetSessionRequest getSessionRequest = new GetSessionRequest();
        getSessionRequest.setSessionId(sessionId);
        GetSessionResponse response = lssClient.getSession(getSessionRequest);
        System.out.println(response.toString());
        assertEquals(response.getThumbnail(), "lss_java_sdk_thumbnail");
        assertEquals(response.getWatermarks().getImage().size(), 1);
        assertEquals(response.getWatermarks().getImage().get(0), "lss_sdk_java_imw");
        assertEquals(response.getWatermarks().getTimestamp().size(), 1);
        assertEquals(resp.getWatermarks().getTimestamp().get(0), "lss_sdk_java_tsw");

    }

}
