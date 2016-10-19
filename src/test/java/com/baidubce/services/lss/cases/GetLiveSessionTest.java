package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.AntiLeech;
import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.Auth;
import com.baidubce.services.lss.model.CodecOptions;
import com.baidubce.services.lss.model.CreateSessionResponse;
import com.baidubce.services.lss.model.Encryption;
import com.baidubce.services.lss.model.GetSecurityPolicyResponse;
import com.baidubce.services.lss.model.GetSessionRequest;
import com.baidubce.services.lss.model.GetSessionResponse;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.HlsEncryption;
import com.baidubce.services.lss.model.IP;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.ListSessionsResponse;
import com.baidubce.services.lss.model.LivePreset;
import com.baidubce.services.lss.model.LivePublishInfo;
import com.baidubce.services.lss.model.LiveSession;
import com.baidubce.services.lss.model.Notification;
import com.baidubce.services.lss.model.Refer;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.UpdateSecurityPolicyRequest;
import com.baidubce.services.lss.model.Video;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shidaiting01 on 2015/9/17.
 */
public class GetLiveSessionTest extends AbstractLssTest {
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

    /**
     *  GetSessionResponse getSession(String sessionId)
     */
    @Test
    public void testGetSessionNormal1() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionResponse resp1 = lssClient.getSession(sessionId);

        assertEquals(resp1.getPreset(), presetName);
        assertEquals(resp1.getDescription(), description);
        assertEquals(resp1.getNotification(), notification);

        assertEquals(resp1.getStatus(), "READY");
    }

    /**
     * GetSessionResponse getSession(GetSessionRequest request)
     */
    @Test
    public void testGetSessionNormal2() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionRequest request = new GetSessionRequest();
        request.setSessionId(sessionId);
        GetSessionResponse resp1 = lssClient.getSession(request);
        assertEquals(resp1.getPreset(), presetName);
        assertEquals(resp1.getDescription(), description);
        assertEquals(resp1.getNotification(), notification);

        assertEquals(resp1.getStatus(), "READY");
    }

    /**
     * GetSessionResponse getSession(String sessionId)
     */
    @Test
    public void testSessionIdNotExist() {

        String sessionId = "not_exist_session";
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.getSession(sessionId);
    }

    /**
     * GetSessionResponse getSession(String sessionId)
     */
    @Test
    public void testSessionIdNull() {

        String sessionId = null;
        bceEx.expect(IllegalArgumentException.class);
        lssClient.getSession(sessionId);
    }

    /**
     * GetSessionResponse getSession(String sessionId)
     */
    @Test
    public void testSessionIdStringNull() {

        String sessionId = "null";
        bceEx.expect(com.baidubce.BceServiceException.class);
        lssClient.getSession(sessionId);
    }

    /**
     * GetSessionResponse getSession(GetSessionRequest request)
     */
    @Test
    public void testRequestNull() {
        GetSessionRequest request = null;
        bceEx.expect(IllegalArgumentException.class);
        lssClient.getSession(request);

    }

    /**
     * GetSessionResponse getSession(String sessionId)
     */
    @Test
    public void testSessionIdEmpty() {
        String sessionId = "";
        bceEx.expect(IllegalArgumentException.class);
        lssClient.getSession(sessionId);
    }

    /**
     *  GetSessionResponse getSession(String sessionId)
     */
    @Test
    public void testSessionIdPaused() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        lssClient.pauseSession(sessionId);
        GetSessionResponse resp1 = lssClient.getSession(sessionId);
        assertEquals(resp1.getPreset(), presetName);
        assertEquals(resp1.getDescription(), description);
        assertEquals(resp1.getNotification(), notification);

        assertEquals(resp1.getStatus(), "PAUSED");
    }


    @Test
    public void testGetEncryptedSession1() {

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionResponse resp1 = lssClient.getSessionWithToken(sessionId, null);



        assertEquals(resp1.getPreset(), presetName);
        assertEquals(resp1.getDescription(), description);
        assertEquals(resp1.getNotification(), notification);

        assertEquals(resp1.getStatus(), "READY");

        GetSessionResponse resp2 = lssClient.getSessionWithToken(sessionId, 10);
        System.out.println(resp2.toString());
    }

    @Test
    public void testGetEncryptedSession2() {
        Auth auth = new Auth().withPlay(true).withPush(true);

        List<String> black = new ArrayList<String>();
        black.add("http://refer.demo.com");
        List<String> white = new ArrayList<String>();
        white.add("8.8.8.8");

        Refer refer = new Refer().withBlacklist(black);
        IP ip = new IP().withWhitelist(white);

        AntiLeech antiLeech = new AntiLeech().withRefer(refer).withIp(ip);

        // SafeCode safeCode = new SafeCode().withPlayerType("web").withSafeCode("code1");
        // List<SafeCode> safeCodes = new ArrayList<SafeCode>();
        // safeCodes.add(safeCode);

        HlsEncryption hlsEncryption = new HlsEncryption().withStrategy("PlayerBinding");
        Encryption encryption = new Encryption().withHls(hlsEncryption);

        lssClient.updateSecurityPolicy("default", auth, antiLeech, encryption);

        GetSecurityPolicyResponse response = lssClient.getSecurityPolicy("default");

        System.out.println(response.toString());
        assertEquals(response.getName(), "default");

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetName, notification, null, null,
                publish);
        String sessionId = resp.getSessionId();

        GetSessionResponse resp2 = lssClient.getSessionWithToken(sessionId, 10);
        System.out.println(resp2.toString());

        lssClient.updateSecurityPolicy(new UpdateSecurityPolicyRequest().withName("default"));
    }

    @Test
    public void testGetEncryptedSessionWithPresets() {
        Auth auth = new Auth().withPlay(true).withPush(true);
        List<String> black = new ArrayList<String>();
        black.add("http://refer.demo.com");
        List<String> white = new ArrayList<String>();
        white.add("8.8.8.8");
        Refer refer = new Refer().withBlacklist(black);
        IP ip = new IP().withWhitelist(white);
        AntiLeech antiLeech = new AntiLeech().withRefer(refer).withIp(ip);
        HlsEncryption hlsEncryption = new HlsEncryption().withStrategy("PlayerBinding");
        Encryption encryption = new Encryption().withHls(hlsEncryption);

        lssClient.updateSecurityPolicy("default", auth, antiLeech, encryption);
        GetSecurityPolicyResponse response = lssClient.getSecurityPolicy("default");
        System.out.println(response.toString());
        assertEquals(response.getName(), "default");

        String description = preSessionDescription;
        String notification = convertName(preNotification);
        lssClient.createNotification(notification, END_POINT);
        LivePublishInfo publish = new LivePublishInfo();
        publish.setRegion("bj");
        CreateSessionResponse resp = lssClient.createSession(description, presetList, notification, null,
                "lss_java_sdk", publish);
        String sessionId = resp.getSessionId();
        System.out.println(resp.toString());

        GetSessionResponse resp1 = lssClient.getSessionWithToken(sessionId, 10);
        System.out.println(resp1.toString());
        assertNotNull(resp1.getPlay().getRtmpUrls());
        assertNotNull(resp1.getPlay().getHlsUrls());
        assertNotNull(resp1.getPlay().getFlvUrls());
    }

}
