package com.baidubce.services.media.cases;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.GetWaterMarkRequest;
import com.baidubce.services.media.model.GetWaterMarkResponse;

public class GetWaterMarkTest extends AbstractMediaTest {
    String watermarkId = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        if (watermarkId != null) {
            mediaClient.deleteWaterMark(watermarkId);
            watermarkId = null;
        }

    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();   
    
    /**
     * test get water mark normal with GetWaterMarkResponse getWaterMark(String watermarkId)
     */  
    
    @Test
    public void testGetWaterMarkNormal() {  
        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();   
        checkWaterMarkExist(watermarkId, true);           
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId);  
        assertEquals(res.getWatermarkId(), watermarkId);
    }   
 
    /**
     * test get water mark normal with  GetWaterMarkResponse getWaterMark(GetWaterMarkRequest request)
     */  
    
    @Test
    public void testGetWaterMarkNormal1() {  
        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, 
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();   
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkRequest request = new GetWaterMarkRequest();
        request.setWatermarkId(watermarkId);
        assertEquals(request.getWatermarkId(), watermarkId);
        GetWaterMarkResponse res = mediaClient.getWaterMark(request);  
        assertEquals(res.getWatermarkId(), watermarkId);
    }  
    
    /**
     * test method of GetWaterMarkResponse
     */  
    
    @Test
    public void testMethodOfGetWaterMarkResponse() {  
        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10; 
        String horizontalAlignment = "left";
        String verticalAlignment = "top";         
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();   
        checkWaterMarkExist(watermarkId, true);           
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        GetWaterMarkResponse res1 = new GetWaterMarkResponse();
        res1.setWatermarkId(res.getWatermarkId());
        res1.setCreateTime(res.getCreateTime());
        res1.setBucket(res.getBucket());
        res1.setKey(res.getKey());
        res1.setVerticalOffsetInPixel(res.getVerticalOffsetInPixel());
        res1.setHorizontalOffsetInPixel(res.getHorizontalOffsetInPixel());
        res1.setHorizontalAlignment(res.getHorizontalAlignment());
        res1.setVerticalAlignment(res.getVerticalAlignment());      
        assertEquals(res1.getWatermarkId(), watermarkId);
        assertEquals(res1.getCreateTime(), res.getCreateTime());
        assertEquals(res1.getBucket(), bucket);
        assertEquals(res1.getKey(), key);
        assertEquals(res1.getVerticalOffsetInPixel().toString(), Integer.toString(verticalOffsetInPixel));
        assertEquals(res1.getHorizontalOffsetInPixel().toString(), Integer.toString(horizontalOffsetInPixel));
        assertEquals(res1.getVerticalAlignment(), verticalAlignment);
        assertEquals(res1.getHorizontalAlignment(), horizontalAlignment);
    }   
     
    
    /**
     * test get water mark not exist
     */   
    @Test
    public void testWaterMarkIdNotExist() {   
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");       
        GetWaterMarkResponse response = mediaClient.getWaterMark("wmk-notlymani6y5r0ey");    
    }
    
    /**
     * test get water mark deleted
     */   
    @Test
    public void testWaterMarkIdDeleted() {  
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        String tempId = response.getWatermarkId();   
        checkWaterMarkExist(tempId, true);      
        mediaClient.deleteWaterMark(tempId);
        checkWaterMarkExist(tempId, false);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("does not exist");       
        mediaClient.getWaterMark(tempId);    
    }
 
 
    /**
     * test get water mark empty
     */   
    @Test
    public void testWaterMarkIdEmpty() {   
        bceEx.expect(java.lang.IllegalArgumentException.class);
        bceEx.expectMessage("watermarkId should NOT be null or empty string");       
        GetWaterMarkResponse response = mediaClient.getWaterMark(""); 
    }
    
    /**
     * test get water mark null
     */   
    @Test
    public void testWaterMarkIdNull() {   
        String tempId = null;
        bceEx.expect(java.lang.IllegalArgumentException.class);
        bceEx.expectMessage("watermarkId should NOT be null"); 
        GetWaterMarkResponse response = mediaClient.getWaterMark(tempId);    
    }
}
