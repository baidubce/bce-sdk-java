package com.baidubce.services.media.cases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.Clip;
import com.baidubce.services.media.model.CodecOptions;
import com.baidubce.services.media.model.CreatePresetRequest;
import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.Encryption;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.Video;

public class CreatePresetTest extends AbstractMediaTest {
    
    public String          prefix = AbstractMediaTest.PRE_NAME + "createpreset";
    String watermarkId = null;

    @Rule
    public ExpectedException myExp = ExpectedException.none();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        for (GetPresetResponse preset : presetClient.listPresets().getPresets()) {
            GetPresetResponse resp = presetClient.getPreset(preset.getPresetName());
            System.out.println(resp);
            assertTrue("Failed to get preset: " + preset.getPresetName(), resp
                    .getPresetName().equals(preset.getPresetName()));
            presetName = preset.getPresetName();
            if (presetName.startsWith(prefix)) {
                presetClient.deletePreset(presetName);
                checkPresetExist(presetName, false);
            }
            /* Pattern pattern = Pattern.compile("^bce.");
             * Matcher m = pattern.matcher(presetName);
             * boolean result = m.find();
             * if (!result) {
             * System.out.println("delete preset " + presetName);
             *      presetClient.deletePreset(presetName);
             *      checkPresetExist(presetName, false);
             * }
             */
        }
        if (watermarkId != null) {
            mediaClient.deleteWaterMark(watermarkId);
            watermarkId = null;
        }
    }

    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCANormal() {
        presetName = convertName(prefix);
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
        checkPresetExist(presetName, true);
    }

    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetContainerA_hls() {
        presetName = convertName(prefix);
        String container = "a-hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("baseline");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(30f);
        video.setMaxWidthInPixel(4096);
        video.setMaxHeightInPixel(3072);
        video.setSizingPolicy("keep");
        presetClient.createPreset(presetName, container, audio, video);
        checkPresetExist(presetName, true);
    }    
    

    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCA40char() {
        presetName = convertName(prefix);
        presetName = presetName + "a";
        System.out.println(presetName.length());
        assertEquals(presetName.length(), 40);
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAOnlyRequiredParam() {
        presetName = convertName(prefix);
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        presetClient.createPreset(presetName, container, audio);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameNull() throws ClassNotFoundException {
        expMsg = "The parameter presetName should NOT be null or empty string";
        myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
        myExp.expectMessage(expMsg);
        presetName = null;
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameEmpty() throws ClassNotFoundException {
        expMsg = "The parameter presetName should NOT be null or empty string";
        myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
        myExp.expectMessage(expMsg);        
        presetName = "";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameWithChineseName() throws ClassNotFoundException {
        expMsg = "preset name must match pattern:[a-z][0-9a-z_]{0,39}";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        presetName = "创建模板";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameWithUpperLetter() throws ClassNotFoundException {
        expMsg = "preset name must match pattern:[a-z][0-9a-z_]{0,39}";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        presetName = "PRESETNAME";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameWithPureDigital() throws ClassNotFoundException {
        expMsg = "preset name must match pattern:[a-z][0-9a-z_]{0,39}";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = "1234567";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameWithFirstNotChar() throws ClassNotFoundException {
        expMsg = "preset name must match pattern:[a-z][0-9a-z_]{0,39}";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = "123preset";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameWithMidlined() throws ClassNotFoundException {
        expMsg = "preset name must match pattern:[a-z][0-9a-z_]{0,39}";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = "preset-1-2";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameWithBlank() throws ClassNotFoundException {
        expMsg = "preset name must match pattern:[a-z][0-9a-z_]{0,39}";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = "preset name";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAPresetNameMore40char() throws ClassNotFoundException {
        expMsg = "preset name must match pattern:[a-z][0-9a-z_]{0,39}";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String container = "mp4";
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAWithNullContainer() throws ClassNotFoundException {
        expMsg = "The parameter container should NOT be null";
        myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = null;
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAWithAudioBitRateInBpsAbnormal() throws ClassNotFoundException {
        expMsg = "audio.bitRateInBps:audio.bitRateInBps=must be greater than or equal to 1000";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "m4a";
        Audio audio = new Audio();
        audio.setBitRateInBps(10);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAWithAudioSampleRateInHzAbnormal() throws ClassNotFoundException {
        expMsg = "audio.sampleRateInHz should in [22050, 32000, 44100, 48000, 96000]";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "m4a";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(3200);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCAWithAudioChannelsAbnormal() throws ClassNotFoundException {
        expMsg = "audio.channels:audio.channels=must be less than or equal to 2";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(5);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio)
     */
    public void testCreatePresetPCADuplicateName() throws ClassNotFoundException {
        expMsg = "duplicated preset name";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        presetClient.createPreset(presetName, container, audio);
        checkPresetExist(presetName, true);
        presetClient.createPreset(presetName, container, audio);
    }
    
    @Test
    /**
     * createPreset(
     *       String presetName, String container, Clip clip, Audio audio, Encryption encryption)
     */
    public void testCreatePresetPCCAENormal() {        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        clip.setDurationInSecond(60);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("abcdefghij123456");
        presetClient.createPreset(presetName, container, clip, audio, encryption);
        checkPresetExist(presetName, true);
    }
    
    
    @Test
    /**
     * createPreset(
     *       String presetName, String container, Clip clip, Audio audio, Encryption encryption)
     */
    public void testCreatePresetPCCAEClipLackDurationInSecond() {        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("abcdefghij123456");
        presetClient.createPreset(presetName, container, clip, audio, encryption);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(
     *       String presetName, String container, Clip clip, Audio audio, Encryption encryption)
     */
    public void testCreatePresetPCCAEClipNull() {        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = null;
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("abcdefghij123456");
        presetClient.createPreset(presetName, container, clip, audio, encryption);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(
     *       String presetName, String container, Clip clip, Audio audio, Encryption encryption)
     */
    public void testCreatePresetPCCAEClipAbnormal() throws ClassNotFoundException {
        expMsg = "clip.durationInSecond:clip.durationInSecond=must be greater than or equal to 1";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        clip.setDurationInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("abcdefghij123456");
        presetClient.createPreset(presetName, container, clip, audio, encryption);
    }
    
    @Test
    /**
     * createPreset(
     *       String presetName, String container, Clip clip, Audio audio, Encryption encryption)
     */
    public void testCreatePresetPCCAEEncryptionAesKey() throws ClassNotFoundException {
        expMsg = "encryption.aesKey:encryption.aesKey=must match \"[a-zA-Z0-9]{16}\"";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("abcdefghij123");
        presetClient.createPreset(presetName, container, clip, audio, encryption);
    }
    
    @Test
    /**
     * createPreset(
     *       String presetName, String container, Clip clip, Audio audio, Encryption encryption)
     */
    public void testCreatePresetPCCAEEncryptionSteategyNull() throws ClassNotFoundException {
        expMsg = "encryption.strategy:encryption.strategy=may not be null";
        //myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        Encryption encryption = new Encryption();
        // encryption.setStrategy("Fixed");
        encryption.setAesKey("abcdefghij123456");
        presetClient.createPreset(presetName, container, clip, audio, encryption);
    }
    
    @Test
    /**
     * createPreset(
     *       String presetName, String container, Clip clip, Audio audio, Encryption encryption)
     */
    public void testCreatePresetPCCAEEncryptionSteategyInvalid() throws ClassNotFoundException {
        expMsg = "Could not read JSON: Can not construct instance";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(32000);
        audio.setChannels(2);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fix");
        encryption.setAesKey("abcdefghij123456");
        presetClient.createPreset(presetName, container, clip, audio, encryption);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoNormal() {        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("baseline");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(30f);
        video.setMaxWidthInPixel(4096);
        video.setMaxHeightInPixel(3072);
        video.setSizingPolicy("keep");
        
        presetClient.createPreset(presetName, container, audio, video);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoSizingPolicyNull() {    
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        video.setSizingPolicy(null);
        presetClient.createPreset(presetName, container, audio, video);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoCodecNull() throws ClassNotFoundException {

        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec(null);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(30f);
        presetClient.createPreset(presetName, container, audio, video);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoCodecOptionAbnormal() throws ClassNotFoundException {
        expMsg = "Could not read JSON";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("baseline1");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(30f);        
        presetClient.createPreset(presetName, container, audio, video);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoBitRateAbnormal() throws ClassNotFoundException {
        expMsg = "video.bitRateInBps:video.bitRateInBps=must be greater than or equal to 32000";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(1024);
        video.setMaxFrameRate(30f);        
        presetClient.createPreset(presetName, container, audio, video);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoMaxFrameRateAbnormal() throws ClassNotFoundException {
        expMsg = "video.maxFrameRate should in [10.0, 15.0, 20.0, 23.97, 24.0, 25.0, 29.97, 30.0, 50.0, 60.0]";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(21f);        
        presetClient.createPreset(presetName, container, audio, video);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoMaxWidthInPixelAbnormal() throws ClassNotFoundException {
        expMsg = "video.maxWidthInPixel:video.maxWidthInPixel=must be greater than or equal to 128";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        video.setMaxWidthInPixel(-1);
        presetClient.createPreset(presetName, container, audio, video);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Audio audio, Video video)
     */
    public void testCreatePresetPCAVVideoMaxHeightInPixelAbnormal() throws ClassNotFoundException {
        expMsg = "video.maxHeightInPixel:video.maxHeightInPixel=must be greater than or equal to 96";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String container = "hls";
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        video.setMaxHeightInPixel(95);
        presetClient.createPreset(presetName, container, audio, video);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption)
     */
    public void testCreatePresetPCCAVENormal() {        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        presetClient.createPreset(presetName, container, clip, audio, video, encryption);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption, String watermarkId)
     */
    public void testCreatePresetWithWaterMark() {
        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();        
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        video.setMaxHeightInPixel(1000);
        video.setMaxWidthInPixel(1000);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        presetClient.createPreset(presetName,
                container, clip, audio, video, encryption, watermarkId);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption, String watermarkId)
     */
    public void testCreatePresetWithWaterMarkDeleted() {
        
        String tempId = null;
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        tempId = response.getWatermarkId(); 
        mediaClient.deleteWaterMark(tempId);
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        myExp.expect(com.baidubce.BceServiceException.class);
        myExp.expectMessage("does not exist");        
        presetClient.createPreset(presetName,
                container, clip, audio, video, encryption, tempId);
    }
   
    @Test
    /**
     * createPreset(String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption, String watermarkId)
     */
    public void testCreatePresetWithWaterMarkNotExist() {
        
        String tempId = "wmk-notlymani6y5r0ey";
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        myExp.expect(com.baidubce.BceServiceException.class);
        myExp.expectMessage("does not exist"); 
        presetClient.createPreset(presetName,
                container, clip, audio, video, encryption, tempId);
    }
    
   
    @Test
    /**
     * createPreset(String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption, String watermarkId)
     */
    public void testCreatePresetWithWaterMarkNull() {
        
        watermarkId = null;
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");       
        presetClient.createPreset(presetName,
                container, clip, audio, video, encryption, watermarkId);
        checkPresetExist(presetName, true); 
    }
    
    @Test
    /**
     * createPreset(String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption, String watermarkId)
     */
    public void testCreatePresetWithWaterMarkEmpty() {
        
        String tempId = "";
        presetName = convertName(prefix);
        String container = "hls";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(50f);
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        myExp.expect(com.baidubce.BceServiceException.class);
        myExp.expectMessage("watermark:  does not exist");   
        presetClient.createPreset(presetName, container, clip, audio, video, encryption, tempId);
    }
    
    @Test
    /**
     * createPreset(String presetName, String container)
     */
    public void testCreatePresetPCNormal() {        
        presetName = convertName(prefix);
        String container = "hls";
        presetClient.createPreset(presetName, container);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String description, String container, boolean transmux, 
     *                     Clip clip, Audio audio, Video video, Encryption encryption)
     */
    public void testCreatePresetPDCTCAVENormal() {        
        presetName = convertName(prefix);
        String description = "test create preset";
        String container = "flv";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        clip.setDurationInSecond(60);
        
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        audio.setSampleRateInHz(44100);
        audio.setChannels(1);
        
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("main");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(24f);
        video.setMaxWidthInPixel(4096);
        video.setMaxHeightInPixel(96);
        video.setSizingPolicy("stretch");
        
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        
        presetClient.createPreset(presetName,
                description, container, true, clip, audio, video, encryption);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String description, String container, boolean transmux, 
     *                     Clip clip, Audio audio, Video video, Encryption encryption)
     */
    public void testCreatePresetPDCTCAVEDescWithChinese() {    
        presetName = convertName(prefix);
        String description = "这是一个描述信息，含有中文特殊字符！@￥%……&*（）。";
        String container = "flv";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("high");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(24f);
        
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        presetClient.createPreset(presetName, description, container, true, clip, audio, video, encryption);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String description, String container, boolean transmux, 
     *                     Clip clip, Audio audio, Video video, Encryption encryption)
     */
    public void testCreatePresetPDCTCAVEDescNull() {    
        presetName = convertName(prefix);
        String description = null;
        String container = "flv";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("high");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(29.97f);
        
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");
        presetClient.createPreset(presetName, description, container, false, clip, audio, video, encryption);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(String presetName, String description, String container, boolean transmux, 
     *                     Clip clip, Audio audio, Video video, Encryption encryption)
     */
    public void testCreatePresetPDCTCAVEDescMore128char() throws ClassNotFoundException {
        expMsg = "description:description=size must be between 0 and 128";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        
        presetName = convertName(prefix);
        String description = "test create preset20 test create preset20 test create "
                + "preset20 test create preset20 test create preset20 test create preset20 129";
        String container = "flv";
        Clip clip = new Clip();
        clip.setStartTimeInSecond(0);
        
        Audio audio = new Audio();
        audio.setBitRateInBps(1600);
        
        Video video = new Video();
        video.setCodec("h264");
        CodecOptions codecOptions = new CodecOptions();
        codecOptions.setProfile("main");
        video.setCodecOptions(codecOptions);
        video.setBitRateInBps(102400);
        video.setMaxFrameRate(29.97f);
        
        Encryption encryption = new Encryption();
        encryption.setStrategy("Fixed");
        encryption.setAesKey("ABCDabcd12345616");        
        presetClient.createPreset(presetName, description, container, true, clip, audio, video, encryption);
    }
    
    @Test
    /**
     * createPreset(CreatePresetRequest request)
     */
    public void testCreatePresetNormal() {        
        CreatePresetRequest request = new CreatePresetRequest();
        presetName = convertName(prefix);
        request.setPresetName(presetName);
        request.setDescription("test create preset");
        request.setContainer("m4a");
        Clip clip = new Clip();
        clip.setStartTimeInSecond(10);
        clip.setDurationInSecond(100);
        request.setClip(clip);
        
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        request.setAudio(audio);
        
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(64000);
        video.setMaxFrameRate(50f);
        video.setMaxWidthInPixel(4096);
        video.setMaxHeightInPixel(3072);
        video.setSizingPolicy("shrinkToFit");
        request.setVideo(video);

        presetClient.createPreset(request);
        checkPresetExist(presetName, true);
    }
    
    @Test
    /**
     * createPreset(CreatePresetRequest request)
     * param: presetname, container, transmux
     */
    public void testCreatePresetPCT() {        
        CreatePresetRequest request = new CreatePresetRequest();
        presetName = convertName(prefix);
        request.setPresetName(presetName);
        request.setContainer("m4a");
        request.setTransmux(true);        
        presetClient.createPreset(request);
        checkPresetExist(presetName, true);
    }
}
