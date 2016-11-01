package com.baidubce.services.media.cases;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.CreateWaterMarkRequest;
import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.GetWaterMarkResponse;


public class CreateWaterMarkTest extends AbstractMediaTest {
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
     * test create water mark normal with function  CreateWaterMarkResponse createWaterMark(String bucket, String key, int horizontalOffsetInPixel, int verticalOffsetInPixel) 
     */  
    @Test
    public void testCreateWaterMarkNormal() {
        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
        CreateWaterMarkResponse res = new CreateWaterMarkResponse();
        res.setWatermarkId(watermarkId);
        assertEquals(watermarkId, res.getWatermarkId());
        
    }
    
    /**
     * test method of CreateWaterMarkRequest 
     */  
    @Test
    public void testMethodOfCreateWaterMarkRequest() {
          
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;
        String horizontalAlignment = "left";
        String verticalAlignment = "top"; 
        CreateWaterMarkRequest request = new CreateWaterMarkRequest();
        request.setBucket(bucket);
        request.setKey(key);
        request.setHorizontalOffsetInPixel(horizontalOffsetInPixel);
        request.setVerticalOffsetInPixel(verticalOffsetInPixel);
        request.setHorizontalAlignment(horizontalAlignment);
        request.setVerticalAlignment(verticalAlignment);
        assertEquals(request.getBucket(), bucket);
        assertEquals(request.getKey(), key);
        assertEquals(request.getVerticalOffsetInPixel().toString(),
                Integer.toString(verticalOffsetInPixel));
        assertEquals(request.getHorizontalOffsetInPixel().toString(),
                Integer.toString(horizontalOffsetInPixel));
        assertEquals(request.getVerticalAlignment(), verticalAlignment);
        assertEquals(request.getHorizontalAlignment(), horizontalAlignment);
    } 
    
    /**
     * test create water mark normal with function  CreateWaterMarkResponse createWaterMark(String bucket, String key, int horizontalOffsetInPixel, int verticalOffsetInPixel) 
     */ 
    @Test
    public void testCreateWaterMarkNormal1() {
          
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;
        String horizontalAlignment = "right";
        String verticalAlignment = "bottom";
        CreateWaterMarkRequest request = new CreateWaterMarkRequest();
        request.setBucket(bucket);
        request.setKey(key);
        request.setHorizontalOffsetInPixel(horizontalOffsetInPixel);
        request.setVerticalOffsetInPixel(verticalOffsetInPixel);
        request.setHorizontalAlignment(horizontalAlignment);
        request.setVerticalAlignment(verticalAlignment);
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(request);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("right", res.getHorizontalAlignment());
        assertEquals("bottom", res.getVerticalAlignment());
    }  
    
  
    /**
     * test create water mark with bucket contains Chinese char 
     */
    @Test
    public void testBucketContainsChineseChar() {         
        String bucket = "test测试bucket";
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                 key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark with bucket not exist
     */
    @Test
    public void testBucketNotExist() {         
        String bucket = "bucketnotexist";
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark with bucket =''
     */
    @Test
    public void testBuckeEmpty() {         
        String bucket = "";
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        bceEx.expect(java.lang.IllegalArgumentException.class);
        bceEx.expectMessage("bucket should NOT be null or empty string");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark with bucket= null
     */
    @Test
    public void testBucketNull() {         
        String bucket = null;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        bceEx.expect(java.lang.IllegalArgumentException.class);
        bceEx.expectMessage("bucket should NOT be null");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark with bucket contais special char
     */
    @Test
    public void testBucketContainsSpecialChar() {         
        String bucket = "test#$@&*^test";
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark for key( jpg png bmp PBM、GIF、TIF) . 
     */
    @Test
    public void testKeySupportedJpg() {         
        String bucket = SOURCE_BUCKET;
        String key =  SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key,
                horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    } 
  
    /**
     * test create water mark for key( jpg png bmp PBM、GIF、TIF) . 
     */
    @Test
    public void testKeySupportedPng() {         
        String bucket = SOURCE_BUCKET;
        String key =  SOURCE_LOGO_PNG;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId(); 
        checkWaterMarkExist(watermarkId, true);
    }  
    
    /**
     * test create water mark for key( jpg png bmp PBM、GIF、TIF) . 
     */ 
    @Test
    public void testKeySupportedBmp() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO_BMP;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId(); 
        checkWaterMarkExist(watermarkId, true);
    }  
  
    /**
     * test create water mark for key( jpg png bmp PBM、GIF、TIF) . 
     */ 
    @Test
    public void testKeySupportedPbm() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO_PBM;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    }  
    
    /**
     * test create water mark for key( jpg png bmp PBM、GIF、TIF) . 
     */ 
    @Test
    public void testKeySupportedGif() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO_Gif;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    }  
    
    /**
     * test create water mark for key( jpg png bmp PBM、GIF、TIF) . 
     */ 
    @Test
    public void testKeySupportedTif() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO_TIF;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;            
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    } 
    
    /**
     * test create water mark for key not picture
     */ 
    @Test
    public void testKeyNotPicure() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_KEY;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("invalid watermark format");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, 
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark for key not jpg png bmp PBM、GIF、TIF
     */ 
    @Test
    public void testKeyPictureNotSupported() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO_PSD;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("invalid watermark format");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark for key not jpg png bmp PBM、GIF、TIF
     */ 
    @Test
    public void testKeyContainsChineseChar() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO1;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    } 
    /**
     * test create water mark for key contains special char
     */ 
    @Test
    public void testKeyContainsSpecialChar() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO2;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    }   
    /**
     * test create water mark for key under folder
     */ 
    @Test
    public void testKeyUnderFolder() {         
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO3;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, 
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    }  
    
    /**
     * test create water mark for key not exist
     */ 
    @Test
    public void testKeyNotExist() {         
        String bucket = SOURCE_BUCKET;
        String key = "notexist.jpg";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("notexist.jpg does not exist");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    }  
    /**
     * test create water mark for key=''
     */ 
    @Test
    public void testKeyEmpty() {         
        String bucket = SOURCE_BUCKET;
        String key = "";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        bceEx.expect(java.lang.IllegalArgumentException.class);
        bceEx.expectMessage("key should NOT be null or empty string");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    /**
     * test create water mark for key=''
     */ 
    @Test
    public void testKeyNull() {         
        String bucket = SOURCE_BUCKET;
        String key = null;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;    
        bceEx.expect(java.lang.IllegalArgumentException.class);
        bceEx.expectMessage("key should NOT be null");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, 
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    } 
    
    /**
     * test create water mark horizontalOffsetInPixel=0
     */
    @Test
    public void testHorizontalOffsetInPixel0() {
        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 0;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    }
    /**
     * test create water mark verticalOffsetInPixel=0
     */
    @Test
    public void testVerticalOffsetInPixel0() {
        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 0;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, 
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);
    }
    
    /**
     * test create water mark horizontalOffsetInPixel=-1
     */
    @Test
    public void testHorizontalOffsetInPixelNegative() {        
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = -1;
        int verticalOffsetInPixel = 10;   
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("horizontalOffsetInPixel=must be greater than or equal to 0");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, 
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    }
    /**
     * test create water mark verticalOffsetInPixel=-1
     */
    @Test
    public void testVerticalOffsetInPixelNegative() {     
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = -1;  
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("verticalOffsetInPixel=must be greater than or equal to 0");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
    }
    
    /**
     * test create water mark HorizontalOffsetInPixel not set
     */  
    @Test
    public void testHorizontalOffsetInPixelNull() {
          
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int verticalOffsetInPixel = 10;            
        CreateWaterMarkRequest request = new CreateWaterMarkRequest();
        request.setBucket(bucket);
        request.setKey(key);
        request.setVerticalOffsetInPixel(verticalOffsetInPixel);      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(request);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("0", res.getHorizontalOffsetInPixel().toString());
        assertEquals("10", res.getVerticalOffsetInPixel().toString());        
    } 
    /**
     * test create water mark verticalOffsetInPixel not set
     */  
    @Test
    public void testVerticalOffsetInPixelNull() {
          
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;         
        CreateWaterMarkRequest request = new CreateWaterMarkRequest();
        request.setBucket(bucket);
        request.setKey(key);
        request.setHorizontalOffsetInPixel(horizontalOffsetInPixel);      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(request);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("20", res.getHorizontalOffsetInPixel().toString());
        assertEquals("0", res.getVerticalOffsetInPixel().toString()); 
    } 
   
    
    /**
     * test create water mark twice: it is ok, the response watermarkId is different
     */  
    @Test
    public void testCreateWaterMarkTwice() {      
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true);    
        response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        assertNotEquals(response.getWatermarkId(), watermarkId);
        mediaClient.deleteWaterMark(response.getWatermarkId());
    }
    
    /**
     * test create water mark  horizontalAlignmen and verticalAlignment : createWaterMark(String bucket, String key, String horizontalAlignment, String verticalAlignment)
     */ 
    @Test
    public void testCreateWaterMarkWithHorizontalAlignmentAndVerticalAlignment1() {      
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "top";      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("left", res.getHorizontalAlignment());
        assertEquals("top", res.getVerticalAlignment());
    }
    
    /**
     * test create water mark  horizontalAlignmen and verticalAlignment : createWaterMark(String bucket, String key, String horizontalAlignment, String verticalAlignment)
     */ 
    @Test
    public void testCreateWaterMarkWithHorizontalAlignmentAndVerticalAlignment2() {      
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "center";
        String verticalAlignment = "center";      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("center", res.getHorizontalAlignment());
        assertEquals("center", res.getVerticalAlignment());
    }
    /**
     * test create water mark  horizontalAlignmen and verticalAlignment : createWaterMark(String bucket, String key, String horizontalAlignment, String verticalAlignment)
     */ 
    @Test
    public void testCreateWaterMarkWithHorizontalAlignmentAndVerticalAlignment3() {      
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "right";
        String verticalAlignment = "bottom";      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("right", res.getHorizontalAlignment());
        assertEquals("bottom", res.getVerticalAlignment());
    }

    /**
     * test create water mark  horizontalAlignmen null
     */ 
    @Test
    public void testCreateWaterMarkWithHorizontalAlignmentAndVerticalAlignmentNull() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = null;
        String verticalAlignment = null;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("left", res.getHorizontalAlignment());
        assertEquals("top", res.getVerticalAlignment());
        assertEquals("0", res.getHorizontalOffsetInPixel().toString());
        assertEquals("0", res.getVerticalOffsetInPixel().toString());
    }
 
    /**
     * test create water mark  horizontalAlignmen empty
     */ 
    @Test
    public void testCreateWaterMarkWithHorizontalAlignmentAndVerticalAlignmentEmpty() {      
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "";
        String verticalAlignment = "top";
        int flag = 0;
        try {
            mediaClient.createWaterMark(bucket,
                    key, horizontalAlignment, verticalAlignment);
        } catch (Exception e) {
            flag = 1;
        }
        assertEquals(1, flag);
        flag = 0;
        horizontalAlignment = "left";
        verticalAlignment = "";
        try {
            mediaClient.createWaterMark(bucket,
                    key, horizontalAlignment, verticalAlignment);
        } catch (Exception e) {
            flag = 1;
        }
        assertEquals(1, flag);
    }
    
    /**
     * test create water mark  horizontalAlignmen illegal
     */ 
    @Test
    public void testCreateWaterMarkHorizontalAlignmentIllegal() {      
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "test";
        String verticalAlignment = "top";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("value not one of declared Enum instance names: [left, center, right]");
        mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment);
    }
    
    /**
     * test create water mark  horizontalAlignmen illegal
     */ 
    @Test
    public void testCreateWaterMarkVerticalAlignmentIllegal() {      
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "test";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("value not one of declared Enum instance names: [top, center, bottom]");
        mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment);
    }
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkWithAlignmentAndOffset() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("left", res.getHorizontalAlignment());
        assertEquals("top", res.getVerticalAlignment());
        assertEquals("20", res.getHorizontalOffsetInPixel().toString());
        assertEquals("10", res.getVerticalOffsetInPixel().toString());
    }    
    
   
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkWithAlignmentAndHorizontalOffsetInPixel4096() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 4096;
        int verticalOffsetInPixel = 10;
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("left", res.getHorizontalAlignment());
        assertEquals("top", res.getVerticalAlignment());
        assertEquals("4096", res.getHorizontalOffsetInPixel().toString());
        assertEquals("10", res.getVerticalOffsetInPixel().toString());
    }
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkWithAlignmentAndHorizontalOffsetInPixel4097() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 4097;
        int verticalOffsetInPixel = 10;
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("horizontalOffsetInPixel=must be less than or equal to 4096");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
    }
    
    
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkWithAlignmentAndVerticalOffsetInPixel3072() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 3072;
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("left", res.getHorizontalAlignment());
        assertEquals("top", res.getVerticalAlignment());
        assertEquals("20", res.getHorizontalOffsetInPixel().toString());
        assertEquals("3072", res.getVerticalOffsetInPixel().toString());
    }
    /**
     * test create water mark  wiht function createWaterMark(
     *      String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *      int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkWithAlignmentAndVerticalOffsetInPixel3073() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 3073;
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("verticalOffsetInPixel=must be less than or equal to 3072");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
    }
    
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkVerticalAlignmentCenterAndVerticalOffsetInPixe0() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "center";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 0;
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("left", res.getHorizontalAlignment());
        assertEquals("center", res.getVerticalAlignment());
        assertEquals("20", res.getHorizontalOffsetInPixel().toString());
        assertEquals("0", res.getVerticalOffsetInPixel().toString());
    }
    
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkVerticalAlignmentCenterAndVerticalOffsetInPixeNot0() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "left";
        String verticalAlignment = "center";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("VerticalOffsetInPixel is only valid when the value of VerticalAlignment is top or bottom");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);   
    }
    
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkHorizontalAlignmentCenterAndHorizontalOffsetInPixel0() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "center";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 0;
        int verticalOffsetInPixel = 10;
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("center", res.getHorizontalAlignment());
        assertEquals("top", res.getVerticalAlignment());
        assertEquals("0", res.getHorizontalOffsetInPixel().toString());
        assertEquals("10", res.getVerticalOffsetInPixel().toString());
    }
    
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkHorizontalAlignmentCenterAndHorizontalOffsetInPixelNot0() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "center";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("only valid when the value of HorizontalAlignment is left or right");
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);   
    }
    
    /**
     * test create water mark  wiht function createWaterMark(
     *       String bucket, String key, String horizontalAlignment, String verticalAlignment,
     *       int horizontalOffsetInPixel, int verticalOffsetInPixel)
     */ 
    @Test
    public void testCreateWaterMarkAlignmentCenter() {
        String bucket = SOURCE_BUCKET;
        String key = SOURCE_LOGO;
        String horizontalAlignment = "center";
        String verticalAlignment = "center";
        int horizontalOffsetInPixel = 0;
        int verticalOffsetInPixel = 0;
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();
        checkWaterMarkExist(watermarkId, true); 
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        assertEquals("center", res.getHorizontalAlignment());
        assertEquals("center", res.getVerticalAlignment());
        assertEquals("0", res.getHorizontalOffsetInPixel().toString());
        assertEquals("0", res.getVerticalOffsetInPixel().toString());
    }
    
}

