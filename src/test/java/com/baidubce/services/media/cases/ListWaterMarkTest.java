package com.baidubce.services.media.cases;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.ListWaterMarkRequest;
import com.baidubce.services.media.model.ListWaterMarkResponse;
import com.baidubce.services.media.model.WaterMark;

public class ListWaterMarkTest extends AbstractMediaTest {
    String watermarkId = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
     //   super.tearDown();
        if (watermarkId != null) {
            mediaClient.deleteWaterMark(watermarkId);
            watermarkId = null;
        }
    }

    @Rule
    public ExpectedException BceEx = ExpectedException.none();   
    
    /**
     * test list water mark normal with listWaterMark()
    */    
    @Test
    public void testListWaterMarkNormal() {
    	 
    	 int count = -1;
    	 count = mediaClient.listWaterMark().getWatermarks().size();
         String bucket = SOURCE_BUCKET;
         String key = SOURCE_LOGO;
         int horizontalOffsetInPixel = 20;
         int verticalOffsetInPixel = 10;      
         CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
         String tempWatermarkId1 = response.getWatermarkId();   
         checkWaterMarkExist(tempWatermarkId1,true);  
         response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
         String tempWatermarkId2 = response.getWatermarkId();   
         checkWaterMarkExist(tempWatermarkId2,true);
         
         ListWaterMarkResponse resp = mediaClient.listWaterMark();         
   	     List<WaterMark> watermarks = resp.getWatermarks();
   	     assertEquals(count+2,watermarks.size());
   	     Set<String> markId = new HashSet<String>();
         for (WaterMark wm : watermarks) {
       	    markId.add(wm.getWatermarkId());
          }
         assertTrue(markId.contains(tempWatermarkId1));
         assertTrue(markId.contains(tempWatermarkId2));
         mediaClient.deleteWaterMark(tempWatermarkId1);
         mediaClient.deleteWaterMark(tempWatermarkId2);
    }
    /**
     * test list water mark normal with listWaterMark(ListWaterMarkRequest request)
    */    
    @Test
    public void testListWaterMarkNormal1() {
    	 
    	 int count = -1;
         ListWaterMarkRequest request = new ListWaterMarkRequest();        
    	 count = mediaClient.listWaterMark(request).getWatermarks().size();
         String bucket = SOURCE_BUCKET;
         String key = SOURCE_LOGO;
         int horizontalOffsetInPixel = 20;
         int verticalOffsetInPixel = 10;      
         CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
         String tempWatermarkId1 = response.getWatermarkId();   
         checkWaterMarkExist(tempWatermarkId1,true);  
         response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
         String tempWatermarkId2 = response.getWatermarkId();   
         checkWaterMarkExist(tempWatermarkId2,true);
         ListWaterMarkResponse resp = mediaClient.listWaterMark(request);         
   	     List<WaterMark> watermarks = resp.getWatermarks();
   	     assertEquals(count+2,watermarks.size());
   	     Set<String> markId = new HashSet<String>();
         for (WaterMark wm : watermarks) {
       	    markId.add(wm.getWatermarkId());
          }
         assertTrue(markId.contains(tempWatermarkId1));
         assertTrue(markId.contains(tempWatermarkId2));
         mediaClient.deleteWaterMark(tempWatermarkId1);
         mediaClient.deleteWaterMark(tempWatermarkId2);
    }  
    /**
     * test method of ListWaterMarkResponse
    */
    @Test
    public void testMethodOfListWaterMarkResponse() {
 
         String bucket = SOURCE_BUCKET;
         String key = SOURCE_LOGO;
         int horizontalOffsetInPixel = 20;
         int verticalOffsetInPixel = 10;      
         CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel);
         watermarkId = response.getWatermarkId();   
         checkWaterMarkExist(watermarkId,true);                  
         ListWaterMarkResponse resp = mediaClient.listWaterMark();  
         ListWaterMarkResponse resp1 = new ListWaterMarkResponse();
         resp1.setWatermarks(resp.getWatermarks());
         
   	     List<WaterMark> watermarks = resp1.getWatermarks();
   	     Set<String> markId = new HashSet<String>();
         for (WaterMark wm : watermarks) {
       	    markId.add(wm.getWatermarkId());
          }
         assertTrue(markId.contains(watermarkId));
    } 
    
    
}
