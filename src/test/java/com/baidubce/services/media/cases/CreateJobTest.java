package com.baidubce.services.media.cases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.Clip;
import com.baidubce.services.media.model.CodecOptions;
import com.baidubce.services.media.model.CreateJobRequest;
import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.CreatePresetRequest;
import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.Encryption;
import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.GetWaterMarkResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.CreateJobResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.Source;
import com.baidubce.services.media.model.Target;
import com.baidubce.services.media.model.Video;

public class CreateJobTest extends AbstractMediaTest {
    
    public String         pipelineForJob = null;
    // the capacity is for the pipeline for job test
    public int            capacityNumber = 2;
    public String         watermarkId = null;
    // presetNameSystem
    public String         presetNameSystem = "bce.audio_mp3_320kbps";
    public String         presetNameVideoSystem = "bce.video_flv_640x480_620kbps";
    public String         presetNameAudioSystem = "bce.audio_hls_128kbps";
    public String         prefix = AbstractMediaTest.PRE_NAME + "createjob";
    public String         presetName = null;

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
     * test create job successfully with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */
    @Test
    public void testCreateJobNormal() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
               && jobId.trim().length() > 0);
    } 
    
    /**
     * test create job successfully with function  createJob(CreateJobRequest request)
     */
    @Test
    public void testCreateJobNormal1() {
        pipelineName = pipelineForJob;
        CreateJobRequest request = new CreateJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(AbstractMediaTest.SOURCE_KEY);
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(AbstractMediaTest.TARGET_KEY);
        presetName = presetNameSystem;
        target.setPresetName(presetName);
        request.setTarget(target);
        request.setSource(source);          
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(request);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    }
    

    /**
     * test create job with containser a-hls
     */
    @Test
    public void testCreateJobPresetContainerA_hls() {
        presetName = pipelineForJob;
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
        pipelineName = pipelineForJob;
        CreateJobRequest request = new CreateJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(AbstractMediaTest.SOURCE_KEY);
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(AbstractMediaTest.TARGET_KEY);
        target.setPresetName(presetName);
        request.setTarget(target);
        request.setSource(source);          
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(request);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);        
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
    }  
     
    /**
     * test create job with waterMark
     */
    @Test 
    public void testCreateJobWithWaterMark() throws InterruptedException {
         
        String bucket = AbstractMediaTest.SOURCE_BUCKET;
        String key = AbstractMediaTest.SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
               key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();      
        presetName = pipelineForJob;
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
        presetClient.createPreset(presetName, container, clip, audio, video, encryption, watermarkId);        
        checkPresetExist(presetName, true);                
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;
        targetKey = AbstractMediaTest.TARGET_KEY1;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
               && jobId.trim().length() > 0); 
        GetJobResponse resp = jobClient.getJob(jobId);
        String jobStatus = resp.getJobStatus();
        while (jobStatus.equals("PENDING") || jobStatus.equals("RUNNING") ) {    
            Thread.sleep(5000);    
            resp = jobClient.getJob(jobId);
            jobStatus = resp.getJobStatus();
        }        
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId, false); 
        assertEquals(jobStatus, "SUCCESS");           
    }
      
    /**
     * test create job with waterMark and alignment
     */
    @Test 
    public void testCreateJobWithWaterMarkAndAlignment() throws InterruptedException {
          
        String bucket = AbstractMediaTest.SOURCE_BUCKET;
        String key = AbstractMediaTest.SOURCE_LOGO;
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket, key, 
                horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, 
                verticalOffsetInPixel);         
        watermarkId = response.getWatermarkId();      
        presetName = pipelineForJob;
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
        presetClient.createPreset(presetName, container, clip, audio, video, encryption, watermarkId);        
        checkPresetExist(presetName, true);                
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;
        targetKey = AbstractMediaTest.TARGET_KEY1;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
               && jobId.trim().length() > 0); 
        GetJobResponse resp = jobClient.getJob(jobId);
        String jobStatus = resp.getJobStatus();
        while (jobStatus.equals("PENDING") || jobStatus.equals("RUNNING") ) {    
            Thread.sleep(5000);    
            resp = jobClient.getJob(jobId);
            jobStatus = resp.getJobStatus();
        }        
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId, false); 
        assertEquals(jobStatus, "SUCCESS");   
          
    } 
    /**
     * test create job with waterMark and alignment
     */
    @Test 
    public void testCreateJobWithWaterMarkAndAlignmentHorizontalOffsetExceed() throws InterruptedException {
           
        String bucket = AbstractMediaTest.SOURCE_BUCKET;
        String key = AbstractMediaTest.SOURCE_LOGO;
        String horizontalAlignment = "right";
        String verticalAlignment = "bottom";
        int horizontalOffsetInPixel = 640;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
               key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, 
               verticalOffsetInPixel); 
        watermarkId = response.getWatermarkId();      
        presetName = pipelineForJob;
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
        presetClient.createPreset(presetName, container, clip, audio, video, encryption, watermarkId);        
        checkPresetExist(presetName, true);                
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;
        targetKey = AbstractMediaTest.TARGET_KEY1;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
              && jobId.trim().length() > 0); 
        GetJobResponse resp = jobClient.getJob(jobId);
        String jobStatus = resp.getJobStatus();
        while (jobStatus.equals("PENDING") || jobStatus.equals("RUNNING") ) {    
            Thread.sleep(5000);    
            resp = jobClient.getJob(jobId);
            System.out.println(resp);
            jobStatus = resp.getJobStatus();
        }        
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId, false); 
        assertEquals(jobStatus, "FAILED");            
    }  
    /**
     * test create job with waterMark VerticalOffsetExceed
     */
    @Test
    public void testCreateJobWithWaterMarkAndAlignmentVerticalOffsetExceed() throws InterruptedException {
            
        String bucket = AbstractMediaTest.SOURCE_BUCKET;
        String key = AbstractMediaTest.SOURCE_LOGO;
        int horizontalOffsetInPixel = 10;
        int verticalOffsetInPixel = 1000;
        String horizontalAlignment = "right";
        String verticalAlignment = "bottom";           
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
               key, horizontalAlignment, verticalAlignment, horizontalOffsetInPixel, 
               verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();           
        presetName = pipelineForJob;
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
        presetClient.createPreset(presetName, container, clip, audio, video, encryption, watermarkId);        
        checkPresetExist(presetName, true);                
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;
        targetKey = AbstractMediaTest.TARGET_KEY1;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
              && jobId.trim().length() > 0); 
        GetJobResponse resp = jobClient.getJob(jobId);
        String jobStatus = resp.getJobStatus();
        while (jobStatus.equals("PENDING") || jobStatus.equals("RUNNING") ) {    
            Thread.sleep(5000);    
            resp = jobClient.getJob(jobId);
            jobStatus = resp.getJobStatus();
        }        
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        System.out.println(res);
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId, false); 
        assertEquals(jobStatus, "FAILED");            
    }         
        
       
    /**
     * test create job with waterMark HorizontalOffsetExceed
     */
    @Test
    public void testCreateJobWithWaterMarkHorizontalOffsetExceed() throws InterruptedException {
          
        String bucket = AbstractMediaTest.SOURCE_BUCKET;
        String key = AbstractMediaTest.SOURCE_LOGO;
        int horizontalOffsetInPixel = 640;
        int verticalOffsetInPixel = 10;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
            key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();      
        presetName = pipelineForJob;
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
        presetClient.createPreset(presetName, container, clip, audio, video, encryption, watermarkId);        
        checkPresetExist(presetName, true);                
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;
        targetKey = AbstractMediaTest.TARGET_KEY1;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
              && jobId.trim().length() > 0); 
        GetJobResponse resp = jobClient.getJob(jobId);
        String jobStatus = resp.getJobStatus();
        while (jobStatus.equals("PENDING") || jobStatus.equals("RUNNING") ) {    
            Thread.sleep(5000);    
            resp = jobClient.getJob(jobId);
            System.out.println(resp);
            jobStatus = resp.getJobStatus();
        }        
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId, false); 
        assertEquals(jobStatus, "FAILED");            
    }   
  
    /**
     * test create job with waterMark VerticalOffsetExceed
     */
    @Test
    public void testCreateJobWithWaterMarkVerticalOffsetExceed() throws InterruptedException {
           
        String bucket = AbstractMediaTest.SOURCE_BUCKET;
        String key = AbstractMediaTest.SOURCE_LOGO;
        int horizontalOffsetInPixel = 10;
        int verticalOffsetInPixel = 1000;      
        CreateWaterMarkResponse response =  mediaClient.createWaterMark(bucket,
                key, horizontalOffsetInPixel, verticalOffsetInPixel);
        watermarkId = response.getWatermarkId();           
        presetName = pipelineForJob;
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
        presetClient.createPreset(presetName, container, clip, audio, video, encryption, watermarkId);        
        checkPresetExist(presetName, true);                
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;
        targetKey = AbstractMediaTest.TARGET_KEY1;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
               && jobId.trim().length() > 0); 
        GetJobResponse resp = jobClient.getJob(jobId);
        String jobStatus = resp.getJobStatus();
        while (jobStatus.equals("PENDING") || jobStatus.equals("RUNNING") ) {    
            Thread.sleep(5000);    
            resp = jobClient.getJob(jobId);
            jobStatus = resp.getJobStatus();
        }        
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        GetWaterMarkResponse res = mediaClient.getWaterMark(watermarkId); 
        System.out.println(res);
        mediaClient.deleteWaterMark(watermarkId);
        checkWaterMarkExist(watermarkId, false); 
        assertEquals(jobStatus, "FAILED");            
    }  
      
    /**
     * test method of CreateJobRequest with function  createJob(CreateJobRequest request)
     */
    @Test
    public void testMethodOfCreateJobRequest() {
        pipelineName = pipelineForJob;
        CreateJobRequest request = new CreateJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(AbstractMediaTest.SOURCE_KEY);
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(AbstractMediaTest.TARGET_KEY);
        presetName = presetNameSystem;
        target.setPresetName(presetName);
        request.setTarget(target);
        assertEquals(request.getPipelineName(), pipelineName);
        assertEquals(request.getTarget().getPresetName(), presetName);       
        assertEquals(request.getSource().toString(), source.toString());
        assertEquals(request.getTarget().toString(), target.toString());
   
    }   
      
    /**
     * test  delete pipelineName with job running with function deletePipeline(String pipelineName)
     */         
    @Test   
    public void testDeletePipelineJobRunning() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);  
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipeline has pending/running jobs"); 
        pipelineClient.deletePipeline(pipelineName);

    } 
   
    /**
     * test create job capacity exceeds with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */  
    @Test
    public void testCreateJobCapacityExceeds() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        for (int i = 0; i < capacityNumber; i++) {
            jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
            jobId = jobResp.getJobId();
            assertTrue("Invalid job ID: " + jobId, jobId != null
                   && jobId.trim().length() > 0);           
        }
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipeline is full");
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
       
    }   
 
 
    /**
     * test create job preset deleted with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */  

    @Test
    public void testCreateJobPresetDeleted() {
        presetName = pipelineForJob;
        CreatePresetRequest request = new CreatePresetRequest();
        request.setPresetName(presetName);
        request.setDescription("test");
        request.setContainer("mp3");
        Clip clip = new Clip();
        clip.setStartTimeInSecond(10);
        clip.setDurationInSecond(100);
        request.setClip(clip);

        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        request.setAudio(audio);
        presetClient.createPreset(request);
        checkPresetExist(presetName, true);
        pipelineName = pipelineForJob;
        CreateJobRequest req = new CreateJobRequest();
        req.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(AbstractMediaTest.SOURCE_KEY);
        req.setSource(source);
        Target target = new Target();
        target.setTargetKey(AbstractMediaTest.TARGET_KEY);
        target.setPresetName(presetName);
        req.setTarget(target);
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(req);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
               && jobId.trim().length() > 0);
        System.out.println(jobId);
        presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("preset has been deleted");
        jobResp = jobClient.createJob(req);
    }
        
    /**
     * test create job preset = '' function createJob(String pipelineName, String sourceKey, 
     * String targetKey, String presetName)
     */ 
    @Test
    public void testCreateJobPresetEmpty() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = "";
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("presetName should NOT be null or empty string");
        CreateJobResponse jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);       
    }
         
    /**
     * test create job preset = null with function createJob(String pipelineName, String sourceKey, 
     * String targetKey, String presetName)
     */ 
    @Test
    public void testCreateJobPresetNull() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = null;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("presetName should NOT be null or empty string");
        CreateJobResponse jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);       
    }
  
    /**
     * test create job preset not exist with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */ 
    @Test
    public void testCreateJobPresetNotExist() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = "notexistname";
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("preset does not exist");
        CreateJobResponse jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
    }
     
    
    /**
     * test create job pipeline deleted with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */ 
 
    @Test
    public void testCreateJobPipelineDeleted() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        pipelineClient.deletePipeline(pipelineName); 
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipeline has been deleted");
        CreateJobResponse jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);       
    }
    
    /**
     * test create job pipeline not exists with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */ 
    @Test
    public void testCreateJobPipelineNotExist() {
        pipelineName = convertName(prefix);
        System.out.println(pipelineName);
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("pipeline does not exist");
        CreateJobResponse jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);       
    }
 
    /**
     * test create job pipeline = ""  with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */ 
    @Test 
    public void testCreateJobPipelineEmpty() {
        pipelineName = "";
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("pipelineName should NOT be null or empty string");
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);   
    } 
  
    /**
     * test create job pipeline = null  with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */ 
    @Test
    public void testCreateJobPipelineNull() {
        pipelineName = null;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp;
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("pipelineName should NOT be null or empty string");
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);   
    } 
    
    /**
     * test create job souceBucket = '' with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */  
    @Test
    public void testCreateJobSourceKeyEmpty() {
        pipelineName = pipelineForJob;
        sourceKey = "";
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("sourceKey should NOT be null or empty string");
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);   
    } 
    
    /**
     * test create job souceBucket = null with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */  
    @Test
    public void testCreateJobSourceKeyNull() {
        pipelineName = pipelineForJob;
        sourceKey = null;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("sourceKey should NOT be null or empty string");
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);   
    } 
    
    /**
     * test create job source file not exist with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */
    @Test
    public void testCreateJobSourceKeyNotExist() {
        pipelineName = pipelineForJob;
        sourceKey = "notexist.mp3";
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        bceEx.expect(com.baidubce.BceServiceException.class);
        bceEx.expectMessage("bos object: notexist.mp3 does not exist");
        CreateJobResponse jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName); 
    }       
     
     /**
      * test create job targetBucket = '' with function createJob(String pipelineName, 
      * String sourceKey, String targetKey, String presetName)
      */  
    @Test
    public void testCreateJobTargetKeyEmpty() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = "";
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("targetKey should NOT be null or empty string");
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);   
    } 
     
    /**
     * test create job targetBucket = null with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */  
    @Test
    public void testCreateJobTargetKeyNull() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = null;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        bceEx.expect(IllegalArgumentException.class);
        bceEx.expectMessage("targetKey should NOT be null or empty string");
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);   
    } 
        
    /**
     * test create job Source File Contains Chinese letter with function createJob(String 
     * pipelineName, String sourceKey, String targetKey, String presetName)
     */  
    @Test
    public void testCreateJobSourceKeyContainsChineseChar() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY1;
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName); 
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    } 
     
     /**
      * test create job Source File Contains Special letter with function createJob(String 
      * pipelineName, String sourceKey, String targetKey, String presetName)
      */      
    @Test 
    public void testCreateJobSourceKeyContainsSpecialLetter() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY2;
        System.out.println(sourceKey);
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName); 
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    }      
     
    /**
     * test create job target File Contains Chinese letter with function createJob(String 
     * pipelineName, String sourceKey, String targetKey, String presetName)
     */     
     
    @Test
    public void testCreateJobTargetKeyContainsChineseLetter() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = "测试.mp3";
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName); 
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    } 
     
    /**
     * test create job target File Contains Special letter with function createJob(String 
     * pipelineName, String sourceKey, String targetKey, String presetName)
     */       
     
    @Test
    public void testCreateJobTargetKeyContainsSpecialLetter() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = "test#@$%^()&test.mp3";
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName); 
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    }
     
    /**
     * test create job source File long name with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */ 
    @Test
    public void testCreateJobSourceKeyLongName() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY3;
        System.out.println(sourceKey);
        targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName); 
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    }
     
    /**
     * test create job target File long name with function createJob(String pipelineName, 
     * String sourceKey, String targetKey, String presetName)
     */ 
    @Test
    public void testCreateJobTargetKeyLongName() {
        pipelineName = pipelineForJob;;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = "testlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtest"
                + "longtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtest"
                + "longtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtest"
                + "longtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtestlongtest"
                + "longtestlongtestlongtestlong.mp3";
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName); 
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    }
     
    /**
     * test create job preset video with function createJob(String pipelineName, String sourceKey, 
     * String targetKey, String presetName)
     */
    @Test
    public void testCreateJobPresetVideo() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = "test.flv";
        presetName = presetNameVideoSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
    } 
      
    /**
     * test create job preset audio with function createJob(String pipelineName, String sourceKey, 
     * String targetKey, String presetName)
     */
    @Test
    public void testCreateJobPresetAudio() {
        pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY;
        targetKey = "test.flv";
        presetName = presetNameVideoSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey, presetName);
        jobId = jobResp.getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                 && jobId.trim().length() > 0);
    }                
}
