package com.baidubce.services.media.cases;

import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.PipelineConfig;

/**
 * The test cases for creating pipeline
 */
public class CreatePipelineTest extends AbstractMediaTest {
    
    public String          prefix = AbstractMediaTest.PRE_NAME + "createpipe";

    @Before
    public void setUp() throws Exception {
        super.setUp();

    }      

    @After
    public void tearDown() throws Exception {
        ListPipelinesResponse resp = pipelineClient.listPipelines();
        String name;
        for (PipelineStatus pipe : resp.getPipelines()) {
            name = pipe.getPipelineName();
            if (name.startsWith(prefix)) {
                pipelineClient.deletePipeline(name);
            }
        }     
    }


    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    /**
     * test illegal Target bucket input with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testTargetBucketIllegal() {
        pipelineName = convertName(prefix);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        pipelineClient.createPipeline(pipelineName, AbstractMediaTest.SOURCE_BUCKET, "illegalbucket", 5);          
    }
     
    /**
     * test illegal Target bucket input with function createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testTargetBucketIllegal1() {
        pipelineName = convertName(prefix); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        pipelineClient.createPipeline(pipelineName, "for test", AbstractMediaTest
                .SOURCE_BUCKET, "illegalbucket", 5);         
    }
    
    /**
     * test illegal Target bucket input with function  createPipeline(CreatePipelineRequest request)
     */
    @Test
    public void testTargetBucketIllegal2() {
        
        pipelineName = convertName(prefix); 
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        request.setTargetBucket("illegalbucket");
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(5);
        request.setConfig(config);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        pipelineClient.createPipeline(request);
    }    

    /**
     * test illegal Source bucket input with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testSourceBucketIllegal() {
        pipelineName = convertName(prefix); 
        bceEx.expect(com.baidubce.BceServiceException.class); 
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        pipelineClient.createPipeline(pipelineName, "illegalbucket", AbstractMediaTest.TARGET_BUCKET, 5);          
    }
     
    /**
     * test illegal Source bucket input with function createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testSourceBucketIllegal1() {
        pipelineName = convertName(prefix); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        pipelineClient.createPipeline(pipelineName, "for test", "illegalbucket", AbstractMediaTest.TARGET_BUCKET, 5);
    }   
    
    /**
     * test illegal Source bucket input with function  createPipeline(CreatePipelineRequest request)
     */
    @Test
    public void testSourceBucketillegal2() {
        pipelineName = convertName(prefix); 
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setSourceBucket("illegalbucket");
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET);
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(5);
        request.setConfig(config);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("doesn't exist or isn't in bj region");
        pipelineClient.createPipeline(request);          
    }
  
 
    /**
     * test create pipeline normal  with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testCreatePipelineNormal() {
        pipelineName = convertName(prefix); 
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
    }
     
    /**
     *test create pipeline normal with  function createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testCreatePipelineNormal1() {
        pipelineName = convertName(prefix); 
        pipelineClient.createPipeline(pipelineName, "for test", 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
    } 
    /**
     * test create pipeline normal with function void createPipeline(CreatePipelineRequest request)
     */
    @Test
    public void testCreatePipelineNormal2() {

        pipelineName = convertName(prefix);
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET);
        request.setDescription("test");   
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(5);
        request.setConfig(config);
        pipelineClient.createPipeline(request);  
        checkPipelineExist(pipelineName, true);
    }

    /**
     * test create pipeline normal with function createPipeline(
     *             String pipelineName, String sourceBucket, String targetBucket)
     */
    @Test
    public void testCreatePipelineNormal3() {
        pipelineName = convertName(prefix);
        pipelineClient.createPipeline(pipelineName, AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET);
        checkPipelineExist(pipelineName, true);
        assertEquals(pipelineClient.getPipeline(pipelineName)
                .getConfig().getCapacity().longValue(), 20);
    }
       
    
    /**
     * test duplicate name for pipeline  with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     * just test this function, it will call the other two
     */
    @Test
    public void testPipelineDuplicateName() {
        pipelineName = convertName(prefix); 
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("duplicated pipeline name");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
    }
     
    /**
     * test just digit name for pipeline  with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     * just test this function, it will call the other two
     */
    @Test
    public void testPipelineJustDigit() {
        
        pipelineName = "123456";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
     
    } 
 
    /**
     * test chinese name for pipeline with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineWithChinese() {
        
        pipelineName = "测试"; 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
     
    }
    
    /**
     * test chinese name for pipeline with function createPipeline(CreatePipelineRequest request)
     */
    @Test  
    public void testPipelineWithChinese2() {

        pipelineName = "测试";       
        CreatePipelineRequest request = new CreatePipelineRequest();
        bceEx.expect(com.baidubce.BceServiceException.class);
        request.setPipelineName(pipelineName);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET);
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(5);
        request.setConfig(config);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(request);          
    } 
   
    /**
     * test pipeline name contain upper letters with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineWithCaptialLetters() {
        
        pipelineName = "testABC"; 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
     
    }
    
    /**
     * test  pipeline name starts with digit with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineStartsWithDigit() {
        
        pipelineName = "123test"; 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
     
    }

    /**
     * test  pipeline name starts with '_' with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineStartsWith_() {
        
        pipelineName = "_test123"; 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
     
    }

    /**
     * test  pipeline name contains "-"  with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineContainsHyphen() {
        
        pipelineName = "test-123"; 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
     
    }
      
    /**
     * test  pipeline name contains blank  with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineContainsBlank() {
        
        pipelineName = "test 123"; 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
     
    }
    
    /**
     * test  pipeline name with 40 letters with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineLetters40() {
        pipelineName = convertName(prefix);
        pipelineName = pipelineName + "012";
        assertEquals(pipelineName.length(), 40);
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
    } 
    /**
     * test  pipeline name more than 40 letters with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineLettersMoreThan40() {
        pipelineName = convertName(prefix);
        pipelineName = pipelineName + "test";
        assertEquals(pipelineName.length(), 41);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipelineName=must match");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, false);
    }
  
    /**
     *test  capacity:0 letters  with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineCapacity0() {
        pipelineName = convertName(prefix);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("must be greater than or equal");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 0);
        checkPipelineExist(pipelineName, false);
    }

    /**
     *test  capacity:-1 letters  with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineCapacity_1() {
        pipelineName = convertName(prefix);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("must be greater than or equal");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, -1);
        checkPipelineExist(pipelineName, false);
    }
    
    /**
     *test  capacity:100 letters with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineCapacity100() {
        pipelineName = convertName(prefix);   
        int flag = 0;
        int used = 0;
        try {
            pipelineClient.createPipeline(pipelineName, 
                    AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 101);
        } catch (Exception e) {
            flag = 1; 
            Pattern p = Pattern.compile("used:(\\d{1,4})");
            Matcher m = p.matcher(e.getMessage());
            if (m.find()) {
                used = Integer.valueOf(m.group(1));
            }        
        } finally {
            assertEquals(flag, 1);           
        }
        checkPipelineExist(pipelineName, false);
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 100 - used);
        checkPipelineExist(pipelineName, true);
    }  

    /**
     *test  capacity:100 letters with function createPipeline(String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */
    @Test
    public void testPipelineCapacity101() {
        pipelineName = convertName(prefix);   
        int flag = 0;
        int used = 0;
        try {
            pipelineClient.createPipeline(pipelineName, 
                    AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 101);
        } catch (Exception e) {
            flag = 1; 
            Pattern p = Pattern.compile("used:(\\d{1,4})");
            Matcher m = p.matcher(e.getMessage());
            if (m.find()) {
                used = Integer.valueOf(m.group(1));
            }        
        } finally {
            assertEquals(flag, 1);           
        }
        checkPipelineExist(pipelineName, false);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("total pipeline size exceed");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 100 - used + 1);
    } 
    

    /**
     * test  description length:128letters with function  createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */   
    
    @Test
    public void testPipelineDescriptionLength128() {
           
        pipelineName = convertName(prefix);
        String description = "testlengthtestlengthtestlengthtestlengthtestlength"
                + "testlengthtestlengthtestlengthtestlengthtestlengthtestlengthtestlength12345678";
        pipelineClient.createPipeline(pipelineName, description, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
    }  

    /**
     * test  description length: more than 128 letters with function  createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */   
    
    @Test
    public void testPipelineDescriptionLengthMoreThan128() {
           
        pipelineName = convertName(prefix);
        String description = "testlengthtestlengthtestlengthtestlengthtestlengthtestlength" 
                + "testlengthtestlengthtestlengthtestlengthtestlengthtestlength123456789";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("description=size must be between 0 and 128");
        pipelineClient.createPipeline(pipelineName, description, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, false);
    }  
    

    /**
     * test description = '' with function  createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */   
    
    @Test
    public void testPipelineDescriptionEmpty() {
           
        pipelineName = convertName(prefix);
        String description = "";
        pipelineClient.createPipeline(pipelineName, description, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
    }  
    
    /**
     * test without description  with function  createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */   
    @Test
    public void testPipelineDescriptionNone() {
        pipelineName = convertName(prefix);
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET);   
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(20);
        request.setConfig(config);
        pipelineClient.createPipeline(request); 
        checkPipelineExist(pipelineName, true);
    } 

    
    /**
     * test description with special letters with function  createPipeline( String pipelineName, String description, String sourceBucket, String targetBucket, int capacity)
     */   
    
    @Test
    public void testPipelineDescriptionWithSpecialLetter() {
           
        pipelineName = convertName(prefix);
        String description = "test#$%&^*测试·~>/?()13~ sdljf_ = +";
        pipelineClient.createPipeline(pipelineName, description, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
    }  
   
    /**
     * test t source bucket = '' with function  createPipeline( String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */   
    @Test
    public void testPipelineSourceBucketEmpty() {
           
        pipelineName = convertName(prefix);
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("sourceBucket should NOT be null or empty string"); 
        pipelineClient.createPipeline(pipelineName, "", AbstractMediaTest.TARGET_BUCKET, 5);
    } 
    
    /**
     * test source bucket = null with function  createPipeline( String pipelineName,  String sourceBucket, String targetBucket, int capacity)
     */    
    @Test
    public void testPipelineSourceBucketNull() {
           
        pipelineName = convertName(prefix);
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("sourceBucket should NOT be null or empty string"); 
        pipelineClient.createPipeline(pipelineName, null, AbstractMediaTest.TARGET_BUCKET, 5);
    } 
    
    /**
     * test without source bucket with function  createPipeline(CreatePipelineRequest request)
     */ 
    @Test
    public void testPipelineWithoutSourceBucket() {
        pipelineName = convertName(prefix);
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET); 
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(20);
        request.setConfig(config);
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("sourceBucket should NOT be null or empty string");           
        pipelineClient.createPipeline(request); 
    } 
    
    
    /**
     * test target bucket = '' with function  createPipeline( String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */   
    @Test
    public void testPipelineTargetBucketEmpty() {
           
        pipelineName = convertName(prefix);
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("targetBucket should NOT be null or empty string"); 
        pipelineClient.createPipeline(pipelineName, AbstractMediaTest.SOURCE_BUCKET, "", 5);
    } 
 
    /**
     * test target bucket = null with function  createPipeline( String pipelineName,  String sourceBucket, String targetBucket, int capacity)
     */   
    @Test
    public void testPipelineTargetBucketNull() {
           
        pipelineName = convertName(prefix);
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("targetBucket should NOT be null or empty string"); 
        pipelineClient.createPipeline(pipelineName, AbstractMediaTest.SOURCE_BUCKET, null, 5);
    } 
    
    /**
     * test without target bucket with function  createPipeline(CreatePipelineRequest request)
     */     
    @Test
    public void testPipelineWithoutTargetBucket() {
        pipelineName = convertName(prefix);
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(20);
        request.setConfig(config);
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("targetBucket should NOT be null or empty string");           
        pipelineClient.createPipeline(request); 
    } 
    
    
    /**
     * test pipeline = "" with function  createPipeline( String pipelineName,  String sourceBucket, String targetBucket, int capacity)
     */      
    @Test
    public void testPipelineEmpty() {
           
        pipelineName = ""; 
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("pipelineName should NOT be null or empty string");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
    }  
  
    /**
     * test pipeline = null with function  createPipeline( String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */      
    @Test
    public void testPipelineNull() {
           
        pipelineName = null; 
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("pipelineName should NOT be null or empty string");
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
    }  
 
    /**
     * test without capacity with function  createPipeline(CreatePipelineRequest request)
     */     
    @Test
    public void testPipelineWithoutCapacity() {
        pipelineName = convertName(prefix);
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET); 
        PipelineConfig config = new PipelineConfig();
        request.setConfig(config);          
        pipelineClient.createPipeline(request); 
        checkPipelineExist(pipelineName, true);
    }  
        
    /**
     * test pipelineName deleted before with function  createPipeline( String pipelineName, String sourceBucket, String targetBucket, int capacity)
     */      
    @Test
    public void testPipelineDeletedBefore() {
           
        pipelineName = convertName(prefix); 
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        pipelineClient.deletePipeline(pipelineName);
        pipelineClient.createPipeline(pipelineName, 
                AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);
        checkPipelineExist(pipelineName, true);
    }  
 

    /**
     * test method of CreatePipelineRequest
     */      
    @Test
    public void testMethodOfCreatePipelineRequest() {
        
        CreatePipelineRequest request = new CreatePipelineRequest();
        pipelineName = convertName(prefix);
        request.setPipelineName(pipelineName);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET); 
        request.setDescription("test");
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(20);
        request.setConfig(config);
        assertEquals(request.getPipelineName(), pipelineName);
        assertEquals(request.getDescription(), "test");
        assertEquals(request.getSourceBucket(), AbstractMediaTest.SOURCE_BUCKET);
        assertEquals(request.getTargetBucket(), AbstractMediaTest.TARGET_BUCKET);
        assertEquals(request.getConfig().toString(), config.toString());
    }      
      
    
}
