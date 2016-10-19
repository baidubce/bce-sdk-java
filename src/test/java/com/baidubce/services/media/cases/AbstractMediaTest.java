package com.baidubce.services.media.cases;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baidubce.conf.MediaConf;
import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.media.MediaClient;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.WaterMark;
import com.baidubce.conf.MediaConf;

public abstract class AbstractMediaTest {  
    MediaClient pipelineClient;
    MediaClient jobClient;
    MediaClient presetClient;
    MediaClient mediaInfoClient;
    MediaClient mediaClient;

    String          pipelineName    = null;
    String          jobId           = null;
    String          thumbNailJobId  = null;
    String          sourceBucket    = null;
    String          targetBucket    = null;
    String          sourceKey       = null;
    String          targetKey       = null;
    String          presetName      = null;
    
    String          bucket          = MediaConf.bucket;
    String			expMsg			= null;
    String			expServiceException = "com.baidubce.BceServiceException";
    String			expParamException = "java.lang.IllegalArgumentException";
    
    static String   SOURCE_BUCKET  = MediaConf.SOURCE_BUCKET;
    static String   TARGET_BUCKET  = MediaConf.TARGET_BUCKET;
    
    static String   SOURCE_LOGO = "logo.jpg";
    static String   SOURCE_LOGO1 = "测试.jpg";
    static String   SOURCE_LOGO2 = "t#$@%&^().jpg";
    static String   SOURCE_LOGO3 = "test/logo.jpg";
    static String   SOURCE_LOGO_PNG = "logo.png";
    static String   SOURCE_LOGO_BMP = "logo.bmp";
    static String   SOURCE_LOGO_PBM = "logo.pbm";
    static String   SOURCE_LOGO_Gif = "logo.gif";
    static String   SOURCE_LOGO_TIF = "logo.tif";
    static String   SOURCE_LOGO_PSD = "logo.psd";
    
    
    static String   SOURCE_KEY = "test.mp3";
    static String   SOURCE_KEY1 = "测试.mp3";
    static String   SOURCE_KEY2 = "test#@$%^()&test.mp3"; 
    static String   SOURCE_KEY3 = "testlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtest.mp3";
    static String   SOURCE_KEY4 = "test.mp4";
    static String   SOURCE_KEY5 = "测试.mp4";
    static String   SOURCE_KEY6 = "test#@$%^()&test.mp4";
    static String   SOURCE_KEY7 = "testlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlong.mp4";
    static String   SOURCE_KEY8 = "test/test.mp4";
    
    static String   TARGET_KEY = "target.mp3"; 
    static String   TARGET_KEY1 = "target.flv"; 
    static String   PRE_NAME = "mct_java_sdk_";   

    public void setUp() throws Exception {
        DefaultBceCredentials cred = new DefaultBceCredentials(MediaConf.BMC_AK,
                MediaConf.BMC_SK);
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(cred).withEndpoint(MediaConf.BMC_ENDPOINT)
                .withMaxConnections(10);
        pipelineClient = new MediaClient(config);
        jobClient = new MediaClient(config);
        presetClient = new MediaClient(config);
        mediaInfoClient = new MediaClient(config);
        
        mediaClient = new MediaClient(config);

    }

    /*
     * 1. pipeline don't support capital letters 2. in current version, pipeline
     * cannot be duplicated even after it is removed
     */
    protected String convertName(String key) {
        return (key + "_" + System.currentTimeMillis());
    }

    protected void checkPipelineExist(String expectedPipelineName,
            boolean expected) {
        ListPipelinesResponse resp = pipelineClient.listPipelines();
        System.err.println(resp);

        Set<String> names = new HashSet<String>();
        for (PipelineStatus pipe : resp.getPipelines()) {
            names.add(pipe.getPipelineName());
        }

        if (expected) {
            assertTrue("Failed to find expected pipeline: "
                    + expectedPipelineName + ". \n " + resp,
                    names.contains(expectedPipelineName));
        } else {
            assertFalse("The pipeline: " + expectedPipelineName
                    + " still exists. \n " + resp,
                    names.contains(expectedPipelineName));
        }
    }

    protected void checkPresetExist(String expectedPresetName, boolean expected) {
        GetPresetResponse resp = presetClient.getPreset(expectedPresetName);

        if (expected) {
        	assertTrue("Failed to find expected preset: " + expectedPresetName,
                    resp.getState().equalsIgnoreCase("ACTIVE"));
        } else {
        	assertTrue(
                    "The preset: " + expectedPresetName + " still exists.",
                    resp.getState().equalsIgnoreCase("INACTIVE"));
        }
    }
    
    protected void checkWaterMarkExist(String watermarkId, boolean expected){
    	
    	 List<WaterMark> watermarks = mediaClient.listWaterMark().getWatermarks();
    	 Set<String> markId = new HashSet<String>();
         for (WaterMark wm : watermarks) {
        	 markId.add(wm.getWatermarkId());
         }
    	if (expected){
    		assertTrue("Failed to find expected water mark id: "+watermarkId,
    				markId.contains(watermarkId));    		
    	}else{
    		assertFalse("Find expected water mark id: "+watermarkId,
    				markId.contains(watermarkId));       		
    	}
    }

}
