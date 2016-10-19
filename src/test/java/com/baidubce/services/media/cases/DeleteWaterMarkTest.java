package com.baidubce.services.media.cases;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.Clip;
import com.baidubce.services.media.model.CreateJobResponse;
import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.DeleteWaterMarkRequest;
import com.baidubce.services.media.model.Encryption;
import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.Video;


public class DeleteWaterMarkTest extends AbstractMediaTest {
    String watermarkId = null;
    public String          prefix = AbstractMediaTest.PRE_NAME + "deletewm";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
      //  super.tearDown();
        if (watermarkId != null) {
            mediaClient.deleteWaterMark(watermarkId);
            watermarkId = null;
        }
    }

    @Rule
    public ExpectedException BceEx = ExpectedException.none();   

    /**
     * test delete water mark normal with function  deleteWaterMark(String watermarkId)
    */  
    @Test
    public void testDeleteWaterMarkNormal() {
    	
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId,true);        
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId,false); 
        watermarkId = null;
    }
    
    /**
     * test delete water mark normal with function deleteWaterMark(DeleteWaterMarkRequest request)
    */  
    @Test
    public void testDeleteWaterMarkNormal1() {
    	
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId,true); 
        DeleteWaterMarkRequest request = new DeleteWaterMarkRequest();
        request.setWatermarkId(watermarkId);
        assertEquals(request.getWatermarkId(),watermarkId);
        mediaClient.deleteWaterMark(request);
        checkWaterMarkExist(watermarkId,false); 
        watermarkId = null;
    }
    
    /**
     * test delete watermark used
    */
     @Test 
    public void testDeleteWaterMarkUsed() {
    	 
        String bucket = AbstractMediaTest.SOURCE_BUCKET;
        String key = AbstractMediaTest.SOURCE_LOGO;
        int horizontalOffsetInPixel = 10;
        int verticalOffsetInPixel = 20;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();      
    	presetName = convertName(prefix);
        String container = "flv";
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
        Encryption encryption = null;
        presetClient.createPreset(presetName, container, clip, audio, video, encryption,watermarkId);         
        checkPresetExist(presetName, true);         
        int flag = 0 ;       
    	try{
    		mediaClient.deleteWaterMark(watermarkId);   
    		flag = 0 ;
    	}catch (Exception e){
    		assertTrue(e.getMessage().contains("watermark is in use, please delete the relative presets first"));
    	    flag = 1; 
    	}finally{
    		assertEquals(flag,1);
    	} 	
    	System.out.println(flag);
		presetClient.deletePreset(presetName);
		checkPresetExist(presetName, false); 
		mediaClient.deleteWaterMark(watermarkId);
		checkWaterMarkExist(watermarkId,false); 
    	watermarkId = null;
    }   

    
    /**
     * test delete water mark  not exist
    */  
    @Test
    public void testDeleteWaterMarkNotExist() { 	
        String tempId = "wmk-notlymani6y5r0ey";
        checkWaterMarkExist(tempId,false); 
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("does not exist");
        mediaClient.deleteWaterMark(tempId);
    } 
    
    /**
     * test delete water mark  empty
    */  
    @Test
    public void testDeleteWaterEmpty() { 	
        String tempId = "";
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("watermarkId should NOT be null or empty string");   	
        mediaClient.deleteWaterMark(tempId);
    } 
    
    /**
     * test delete water mark null
    */  
    @Test
    public void testDeleteWaterNull() { 	
    	watermarkId = null;
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("watermarkId should NOT be null");   	
        mediaClient.deleteWaterMark(watermarkId);
    } 
    
    /**
     * test delete water mark twice
    */  
    @Test
    public void testDeleteWaterMarkTwice() {
    	
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId,true);        
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId,false); 
        String tempId = watermarkId;
        watermarkId = null;
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("does not exist");
        mediaClient.deleteWaterMark(tempId);        
    }  
    
}
