package com.baidubce.services.media.cases;

import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.baidubce.services.media.model.GetMediaInfoOfFileRequest;
import com.baidubce.services.media.model.GetMediaInfoOfFileResponse;

public class QueryMediaInfoTest extends AbstractMediaTest {
    
    @Rule
    public ExpectedException myExp = ExpectedException.none();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        
    }

    @Test
    public void testQueryMediaInfoEnglishName() {

        String key = "mediainfotest.mp4";
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
        System.out.println("\nkey = " + key + " returns: \n" + resp);
        assertEquals(bucket, resp.getBucket());
        assertEquals(key, resp.getKey());
    }
    
    @Test
    public void testQueryMediaInfoObjectContainFolder() {

        String key = "media/info/mediainfotest.mp4";
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
        System.out.println("\nkey = " + key + " returns: \n" + resp);
        assertEquals(bucket, resp.getBucket());
        assertEquals(key, resp.getKey());
    }
    
    @Test
    public void testQueryMediaInfoObjectChineseName() {

        String key = "媒体信息测试.mp3";
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
        System.out.println("\nkey = " + key + " returns: \n" + resp);
        assertEquals(bucket, resp.getBucket());
        assertEquals(key, resp.getKey());
    }
    
    @Test
    public void testQueryMediaInfoIncludeSpecialChars() {

        String key = "《媒体信息_测试》.mp3";
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
        System.out.println("\nkey = " + key + " returns: \n" + resp);
        assertEquals(bucket, resp.getBucket());
        assertEquals(key, resp.getKey());
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
    @Test
    public void testQueryMediaInfoObjectNotExist() throws ClassNotFoundException {
        expMsg = "bos object: not_exist.mp3 does not exist";
        String key = "not_exist.mp3";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
    @Test
    public void testQueryMediaInfoBucketNotExist() throws ClassNotFoundException {
        expMsg = "doesn't exist or isn't in bj region";
        bucket = "no-input";
        String key = "mediainfotest.mp4";
        myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
        myExp.expectMessage(expMsg);
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
    @Test
    public void testQueryMediaInfoBucketIsEmpty() throws ClassNotFoundException {
        expMsg = "The parameter bucket should NOT be null or empty string";
        bucket = "";
        String key = "mediainfotest.mp4";
        myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
        myExp.expectMessage(expMsg);
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
    @Test
    public void testQueryMediaInfoBucketIsNull() throws ClassNotFoundException {
        expMsg = "The parameter bucket should NOT be null or empty string";
        bucket = null;
        String key = "mediainfotest.mp4";
        myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
        myExp.expectMessage(expMsg);
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(bucket, key);
    }
    
    @Test
    /**
     * GetMediaInfoOfFileResponse getMediaInfoOfFile(GetMediaInfoOfFileRequest request)
     */
    public void testQueryMediaInfoNormal() throws ClassNotFoundException {
        String key = "mediainfotest.mp4";
        GetMediaInfoOfFileRequest request = new GetMediaInfoOfFileRequest();
        request.setBucket(bucket);
        request.setKey(key);
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(request);
        System.out.println("\nkey = " + key + " returns: \n" + resp);
        assertEquals(bucket, resp.getBucket());
        assertEquals(key, resp.getKey());
    }
    
    @Test
    /**
     * GetMediaInfoOfFileResponse getMediaInfoOfFile(GetMediaInfoOfFileRequest request)
     */
    public void testQueryMediaInfoDurationInSecondAndContainer() throws ClassNotFoundException {
        String key = "mediainfotest.mp4";
        GetMediaInfoOfFileRequest request = new GetMediaInfoOfFileRequest();
        request.setBucket(bucket);
        request.setKey(key);
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(request);
        System.out.println("\nkey = " + key + " returns: \n" + resp);
        System.out.println(resp);
        assertTrue(resp.getContainer().toString().contains("mp4"));
        assertEquals(resp.getDurationInSecond().toString(), "220");
        System.out.println(resp.getDurationInSecond().toString());
    }

    @Test
    public void testQueryMediaInfoDarRotate() throws ClassNotFoundException {
        // in wangxiaoming04's bucket
        String key = "rotate_dar/vertical_dar_rotate.mp4";
        GetMediaInfoOfFileRequest request = new GetMediaInfoOfFileRequest();
        request.setBucket(bucket);
        request.setKey(key);
        GetMediaInfoOfFileResponse resp = mediaInfoClient.getMediaInfoOfFile(request);
        assertEquals(resp.getVideo().getRotate(), new Integer(270));
        assertEquals(resp.getVideo().getDar(), "9:16");
    }

}

