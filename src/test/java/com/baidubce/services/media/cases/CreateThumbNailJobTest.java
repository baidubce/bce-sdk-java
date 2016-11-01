package com.baidubce.services.media.cases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.CreateThumbnailJobRequest;
import com.baidubce.services.media.model.GetThumbnailJobResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.ThumbnailCapture;
import com.baidubce.services.media.model.ThumbnailSource;
import com.baidubce.services.media.model.ThumbnailTarget;

public class CreateThumbNailJobTest extends AbstractMediaTest {

    public String          pipelineForJob = null;
    public String          prefix = AbstractMediaTest.PRE_NAME + "createthumb";
    // the capacity is for the pipeline for job test
    public int             capacityNumber = 2;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        pipelineForJob = convertName(prefix);
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineForJob);
        request.setSourceBucket(AbstractMediaTest.SOURCE_BUCKET);
        request.setTargetBucket(AbstractMediaTest.TARGET_BUCKET); 
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(capacityNumber);
        request.setConfig(config);
        pipelineClient.createPipeline(request);  
        checkPipelineExist(pipelineForJob, true);
    }

    @After
    public void tearDown() throws Exception {
        ListPipelinesResponse resp = pipelineClient.listPipelines();
        String name;
        for (PipelineStatus pipe : resp.getPipelines()) {
            name = pipe.getPipelineName();
            if (name.startsWith(prefix)) {
                while (true) {
                    try {
                        pipelineClient.deletePipeline(name);
                        break;
                    } catch (Exception e) {
                        if (e.getMessage().contains("pending/running")) {
                            Thread.sleep(5000);                            
                        } else {
                            break;
                        }                           
                    }
                }                            
            }
        }
    }
    
    @Rule
    public ExpectedException bceEx = ExpectedException.none();     

    /**
     * test create Thumbnail job successfully with function createThumbnailJob(String pipelineName, String sourceKey, ThumbnailTarget target, ThumbnailCapture capture)
     */
    @Test
    public void testCreateThumbNailJobNormal1() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job successfully with function createThumbnailJob(String pipelineName, String sourceKey)
     */
    @Test
    public void testCreateThumbNailJobNormal2() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;              
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job successfully with function createThumbnailJob(CreateThumbnailJobRequest request)
     */
    @Test
    public void testCreateThumbNailJobNormal3() {  
        
        CreateThumbnailJobRequest request = new CreateThumbnailJobRequest();
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4; 
        request.setPipelineName(pipelineName);
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);
        request.setSource(source);       
        thumbNailJobId = mediaClient.createThumbnailJob(request).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }
        
    
    /**
     * test method of CreateThumbnailJobRequest
     */
    @Test
    public void testMethodOfCreateThumbnailJobRequest() {  
        
        CreateThumbnailJobRequest request = new CreateThumbnailJobRequest();
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4; 
        request.setPipelineName(pipelineName);
        assertEquals(request.getPipelineName(), pipelineName);        
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);
        assertEquals(source.getKey(), sourceKey);        
        request.setSource(source); 
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withStartTimeInSecond(0).withEndTimeInSecond(10).withIntervalInSecond(5); 
        request.setTarget(target);
        request.setCapture(capture);
        assertEquals(request.getTarget().toString(), target.toString());
        assertEquals(request.getCapture().toString(), capture.toString());        
        thumbNailJobId = mediaClient.createThumbnailJob(request).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }
         
    
    /**
     * test create Thumbnail job pipeline not exist
     */
    @Test
    public void testCreateThumbNailJobPipelineNotExist() {        
        pipelineName = convertName(prefix);       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();   
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipeline does not exist");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
    } 
    
    /**
     * test create Thumbnail job pipeline deleted
     */
    @Test
    public void testCreateThumbNailJobPipelineDeleted() {        
        pipelineName = pipelineForJob;      
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();  
        mediaClient.deletePipeline(pipelineName); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipeline has been deleted");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();
    } 
    
    /**
     * test create Thumbnail job pipeline null
     */
    @Test
    public void testCreateThumbNailJobPipelineNull() {        
        pipelineName = null;      
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();  
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("pipelineName should NOT be null or empty string");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
    } 
    
    /**
     * test create Thumbnail job pipeline empty
     */
    @Test
    public void testCreateThumbNailJobPipelineEmpty() {        
        pipelineName = "";
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();  
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("pipelineName should NOT be null or empty string");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
               sourceKey, target, capture).getJobId();              
    }
    
    /**
     * test create Thumbnail job sourceKey contains chinese char 
     */
    @Test
    public void testCreateThumbNailJobSourceKeyContainsChineseChar() {        
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY5;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();  
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job sourceKey contains special letter 
     */
    @Test
    public void testCreateThumbNailJobSourceKeyContainsSpecialLetter() {        
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY6;    
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
               sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job sourceKey long name 
     */
    @Test
    public void testCreateThumbNailJobSourceKeyLongName() {        
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY7;    
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
               sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job sourceKey under folder
     */
    @Test
    public void testCreateThumbNailJobSourceKeyUnderFolder() {        
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY8;    
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();  
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job sourceKey not exist
     */
    @Test
    public void testCreateThumbNailJobSourceKeyNotExist() {        
        pipelineName = pipelineForJob;
        sourceKey = "notexist.mp4";    
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("bos object: notexist.mp4 does not exist");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
               sourceKey, target, capture).getJobId();
    }
    
    
    /**
     * test create Thumbnail job sourceKey null
     */
    @Test
    public void testCreateThumbNailJobSourceKeyNull() {        
        pipelineName = pipelineForJob;
        sourceKey = null;  
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("source key should NOT be null or empty string");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();
    }
    
    /**
     * test create Thumbnail job sourceKey =""
     */
    @Test
    public void testCreateThumbNailJobSourceKeyEmpty() {        
        pipelineName = pipelineForJob;
        sourceKey = "";    
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("source key should NOT be null or empty string");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();
    }
    
    
    /**
     * test create Thumbnail job source=null
     */
    @Test
    public void testCreateThumbNailJobSourceNull() {  
        
        CreateThumbnailJobRequest request = new CreateThumbnailJobRequest();
        pipelineName = pipelineForJob;        
        request.setPipelineName(pipelineName);
        ThumbnailSource source = null;
        request.setSource(source);  
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("source should NOT be null");
        thumbNailJobId = mediaClient.createThumbnailJob(request).getJobId();              
   
    }
    
    /**
     * test create Thumbnail job with keyPrefix
     */
    @Test
    public void testCreateThumbNailJobKeyPrefix() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg")
                .withSizingPolicy("keep").withKeyPrefix("testKeyPrefix");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job with keyPrefix underFolder
     */
    @Test
    public void testCreateThumbNailJobKeyPrefixUnderFolder() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg")
                .withSizingPolicy("keep").withKeyPrefix("test/testKeyPrefix");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }
    /**
     * test create Thumbnail job format jpg
     */   
    @Test
    public void testCreateThumbNailJobFormatJpg() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job format png
     */ 
    @Test
    public void testCreateThumbNailJobFormatPng() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("png").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job format not support
     */ 
    @Test
    public void testCreateThumbNailJobFormatNotSupport() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("giff").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();  
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("target.Format from String value 'giff': value not one of declared Enum ");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
    }
    
    /**
     * test create Thumbnail job sizingPolicy:shrinkToFit
     */ 
    @Test
    public void testCreateThumbNailJobSizingPolicyShrinkToFit() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("shrinkToFit");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job sizingPolicy:stretch
     */ 
    @Test
    public void testCreateThumbNailJobSizingPolicyStretch() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("stretch");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job sizingPolicy:keep
     */ 
    @Test
    public void testCreateThumbNailJobSizingPolicyKeep() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job sizingPolicy:not set
     */ 
    @Test
    public void testCreateThumbNailJobSizingPolicyNotSet() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job sizingPolicy:not support
     */ 
    @Test
    public void testCreateThumbNailJobSizingPolicyNotSupported() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("test");
        ThumbnailCapture capture = new ThumbnailCapture();  
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("value not one of declared Enum instance names");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();    
    } 
    
    /**
     * test create Thumbnail job WidthInPixel:10
     */ 
    @Test
    public void testCreateThumbNailJobWidthInPixel10() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withWidthInPixel(10);
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job WidthInPixel:9
     */ 
    @Test
    public void testCreateThumbNailJobWidthInPixel9() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withWidthInPixel(9);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("widthInPixel=must be greater than or equal to 10");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                 sourceKey, target, capture).getJobId();
    } 
    
    /**
     * test create Thumbnail job WidthInPixel:2000
     */ 
    @Test
    public void testCreateThumbNailJobWidthInPixel2000() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withWidthInPixel(2000);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();  
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job WidthInPixel:2001
     */ 
    @Test
    public void testCreateThumbNailJobWidthInPixel2001() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withWidthInPixel(2001);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("widthInPixel=must be less than or equal to 2000");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();
    } 
    
    /**
     * test create Thumbnail job WidthInPixel:1000
     */ 
    @Test
    public void testCreateThumbNailJobWidthInPixel1000() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withWidthInPixel(1000);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                 sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job WidthInPixel:not set
     */ 
    @Test
    public void testCreateThumbNailJobWidthInPixelNotSet() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId(); 
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(thumbNailJobId);
        assertEquals(response.getTarget().getWidthInPixel().toString(), "600");
    } 
    
    /**
     * test create Thumbnail job heightInPixel:10
     */ 
    @Test
    public void testCreateThumbNailJobHeightInPixel10() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withHeightInPixel(10);
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                sourceKey, target, capture).getJobId();              
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                       && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job heightInPixel:9
     */ 
    @Test
    public void testCreateThumbNailJobHeightInPixel9() {    
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withHeightInPixel(9);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("heightInPixel=must be greater than or equal to 10");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                 sourceKey, target, capture).getJobId();
    } 
    
    /**
     * test create Thumbnail job heightInPixel:2000
     */ 
    @Test
    public void testCreateThumbNailJobHeightInPixel2000() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withHeightInPixel(2000);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();  
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job heightInPixel:2001
     */ 
    @Test
    public void testCreateThumbNailJobHeightInPixel2001() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withHeightInPixel(2001);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("heightInPixel=must be less than or equal to 2000");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName,
                  sourceKey, target, capture).getJobId();
    } 
    
    /**
     * test create Thumbnail job heightInPixel:1000
     */ 
    @Test
    public void testCreateThumbNailJobHeightInPixel1000() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withHeightInPixel(1000);
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job heightInPixel:not set
     */ 
    @Test
    public void testCreateThumbNailJobHeightInPixelNotSet() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg");
        ThumbnailCapture capture = new ThumbnailCapture(); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId(); 
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(thumbNailJobId);
        assertEquals(response.getTarget().getHeightInPixel().toString(), "450");
    }  
  
    /**
     * test create Thumbnail job mode:auto
     */ 
    @Test
    public void testCreateThumbNailJobModeAuto() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("auto"); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job mode:manual
     */ 
    @Test
    public void testCreateThumbNailJobModeMannul() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual").withStartTimeInSecond(10); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }  
    
    /**
     * test create Thumbnail job mode:except auto manual
     */ 
    @Test
    public void testCreateThumbNailJobModeNotSupported() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("test"); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("value not one of declared Enum instance names: [auto, manual, split]");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    } 
    
    /**
     * test create Thumbnail job startTimeInSecond:0
     */ 
    @Test
    public void testCreateThumbNailJobStartTimeInSecond0() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual").withStartTimeInSecond(0); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }   
    
    /**
     * test create Thumbnail job startTimeInSecond:999999999
     */ 
    @Test
    public void testCreateThumbNailJobStartTimeInSecond999999999() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual").withStartTimeInSecond(999999999); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job startTimeInSecond:1000000000
     */ 
    @Test
    public void testCreateThumbNailJobStartTimeInSecond1000000000() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual").withStartTimeInSecond(1000000000);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job startTimeInSecond:-1
     */ 
    @Test
    public void testCreateThumbNailJobStartTimeInSecond_1() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual").withStartTimeInSecond(-1);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("startTimeInSecond=must be greater than or equal to 0");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    }
    /**
     * test create Thumbnail job startTimeInSecond:auto
     */ 
    @Test
    public void testCreateThumbNailJobWithStartTimeInSecondInModeAuto() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("auto").withStartTimeInSecond(10);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("cannot specify start time, end time, interval or frame number in auto mode");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    }
    
    /**
     * test create Thumbnail job startTimeInSecond:manual
     */ 
    @Test
    public void testCreateThumbNailJobWithoutStartTimeInSecondInModeManual() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job endTimeInSecond:0
     */ 
    @Test
    public void testCreateThumbNailJobEndTimeInSecond0() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                 .withStartTimeInSecond(0).withEndTimeInSecond(0).withIntervalInSecond(5); 
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }   
    
    /**
     * test create Thumbnail job endTimeInSecond:999999999
     */ 
    @Test
    public void testCreateThumbNailJobEndTimeInSecond999999999() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                  .withStartTimeInSecond(999999998).withEndTimeInSecond(999999999).withIntervalInSecond(5);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job endTimeInSecond:1000000000
     */ 
    @Test
    public void testCreateThumbNailJobEndTimeInSecond1000000000() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                  .withStartTimeInSecond(999999998).withEndTimeInSecond(1000000000).withIntervalInSecond(5);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job endTimeInSecond:-1
     */ 
    @Test
    public void testCreateThumbNailJobEndTimeInSecond_1() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                  .withStartTimeInSecond(10).withEndTimeInSecond(-1).withIntervalInSecond(5);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("endTimeInSecond=must be greater than or equal to 0");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    }
    
    
    /**
     * test create Thumbnail job endTimeInSecond  less than startTimeInSecond
     */ 
    @Test
    public void testCreateThumbNailJobEndTimeInSecondLessThanstartTimeInSecond() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withStartTimeInSecond(10).withEndTimeInSecond(5).withIntervalInSecond(5);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("start time cannot larger than end time");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    }
    
    /**
     * test create Thumbnail job endTimeInSecond:auto
     */ 
    @Test
    public void testCreateThumbNailJobWithEndTimeInSecondInModeAuto() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("auto").withEndTimeInSecond(0);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("cannot specify start time, end time, interval or frame number in auto mode");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    }
    
    /**
     * test create Thumbnail job set endTimeInSecond not set IntervalInSecond
     */ 
    @Test
    public void testCreateThumbNailJobWithEndTimeInSecondWithoutIntervalInSecond() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withStartTimeInSecond(0).withEndTimeInSecond(10);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    
    /**
     * test create Thumbnail job set IntervalInSecond but no startTimeInSecond
     */ 
    @Test
    public void testCreateThumbNailJobWithIntervalInSecondWithoutStartTimeInSecond() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withEndTimeInSecond(10).withIntervalInSecond(5);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
    /**
     * test create Thumbnail job set IntervalInSecond but no endTimeInSecond
     */ 
    @Test
    public void testCreateThumbNailJobWithIntervalInSecondWithoutEndTimeInSecond() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withStartTimeInSecond(0).withIntervalInSecond(5);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    }
    
 
    /**
     * test create Thumbnail job IntervalInSecond:0
     */ 
    @Test
    public void testCreateThumbNailJobIntervalInSecond0() {
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withStartTimeInSecond(0).withEndTimeInSecond(10).withIntervalInSecond(0);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("intervalInSecond=must be greater than or equal to 1");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    } 
    
    /**
     * test create Thumbnail job IntervalInSecond:1
     */ 
    @Test
    public void testCreateThumbNailJobIntervalInSecond1() {
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withStartTimeInSecond(9).withEndTimeInSecond(10).withIntervalInSecond(1);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job IntervalInSecond:999999999
     */ 
    @Test
    public void testCreateThumbNailJobIntervalInSecond999999999() {
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                 .withStartTimeInSecond(9).withEndTimeInSecond(10).withIntervalInSecond(999999999);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job IntervalInSecond:-1
     */ 
    @Test
    public void testCreateThumbNailJobIntervalInSecond_1() {
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                 .withStartTimeInSecond(9).withEndTimeInSecond(10).withIntervalInSecond(-1);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("intervalInSecond=must be greater than or equal to 1");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();;
    } 
    
    /**
     * test create Thumbnail job IntervalInSecond:1000000000
     */ 
    @Test
    public void testCreateThumbNailJobIntervalInSecond1000000000() {
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual")
                .withStartTimeInSecond(9).withEndTimeInSecond(10).withIntervalInSecond(1000000000);
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
        assertTrue("Invalid thumbnail job ID: " + thumbNailJobId, thumbNailJobId != null
                && thumbNailJobId.trim().length() > 0);
    } 
    
    /**
     * test create Thumbnail job IntervalInSecond:auto
     */ 
    @Test
    public void testCreateThumbNailJobWithIntervalInSecondInModeAuto() {        
        pipelineName = pipelineForJob;       
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget();
        ThumbnailCapture capture = new ThumbnailCapture().withMode("auto").withIntervalInSecond(5);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("cannot specify start time, end time, interval or frame number in auto mode");
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
    } 
    
}
