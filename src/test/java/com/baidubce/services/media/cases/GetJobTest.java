package com.baidubce.services.media.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.GetJobRequest;
import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.CreateJobResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.Target;

public class GetJobTest extends AbstractMediaTest {
	
    public String          pipelineForJob = null;
    public int                  capacityNumber = 2;
    public String          presetNameSystem = "bce.audio_mp3_320kbps";
    public String          prefix = AbstractMediaTest.PRE_NAME + "queryjob";
    
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
     //   super.tearDown();
    	ListPipelinesResponse resp = pipelineClient.listPipelines();
        String name;
        for (PipelineStatus pipe : resp.getPipelines()) {
        	name = pipe.getPipelineName();     
        	if (name.startsWith(prefix)) {
        		while(true){
        			try{
        				pipelineClient.deletePipeline(name);
        				break;
        			}catch (Exception e){
        				if (e.getMessage().contains("pending/running")){
        					Thread.sleep(5000);
        				}else{
        					break;
        				}
        					
        			}
        		}
        		    		
               }
        	}        
    }

    @Rule
    public ExpectedException BceEx = ExpectedException.none();
   
    /**
     * test get job successfully  with function getJob(String jobId)
    */
    @Test
    public void testGetJobIDNormal() {
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY;
    	targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        jobId = jobResp.getJobId();
        GetJobResponse resp = jobClient.getJob(jobId);
    	assertEquals(resp.getJobId(),jobId);
    }
    
    /**
     * test get job successfully  with function getJob(GetJobRequest request)
    */
    @Test
    public void testGetJobIDNormal1() {
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY;
    	targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        jobId = jobResp.getJobId();
        GetJobRequest request = new GetJobRequest();
        request.setJobId(jobId);
        assertEquals(request.getJobId(),jobId);      
        GetJobResponse resp = jobClient.getJob(request);
    	assertEquals(resp.getJobId(),jobId);
    }  
        
    /**
     * test method of  GetJobResponse
    */
    @Test
    public void testMethodOfGetJobResponse() {
    	pipelineName  =  pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY;
    	targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        jobId = jobResp.getJobId();
        GetJobResponse resp0 = jobClient.getJob(jobId);
        GetJobResponse resp = new GetJobResponse();
        resp.setJobId(resp0.getJobId());
        resp.setJobStatus(resp0.getJobStatus());
        resp.setPipelineName(resp0.getPipelineName());
        resp.setSource(resp0.getSource());
        resp.setTarget(resp0.getTarget());
        resp.setStartTime(resp0.getStartTime());
        resp.setEndTime(resp0.getEndTime());        
    	assertEquals(resp.getJobId(),jobId);
    	assertTrue(resp.getTarget().toString().contains(targetKey));
    	assertTrue(resp.getSource().toString().contains(sourceKey)); 
    	assertEquals(resp.getJobStatus(),resp0.getJobStatus());
    	assertEquals(resp.getTarget().getPresetName(),presetName);
    	assertEquals(resp.getPipelineName(),pipelineName);
     	assertEquals(resp.getStartTime(),resp0.getStartTime());
    	assertEquals(resp.getStartTime(),resp0.getStartTime());
    	assertTrue(resp0.getMetadata().toString().contains("bceRequestId"));
    	assertTrue(resp0.toString().contains("class GetJobResponse"));    	
    }  
    
    /**
     * test get job JobId = ""  with function getJob(String jobId)
    */
    @Test
    public void testGetJobIDEmpty() {
    	jobId = "";  
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("jobId should NOT be null or empty string");
        GetJobResponse resp = jobClient.getJob(jobId);
    } 
    
    /**
     * test get job JobId = null  with function getJob(String jobId)
    */
    @Test
    public void testGetJobIDNull() {
    	jobId = null;  
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("jobId should NOT be null or empty string");
        GetJobResponse resp = jobClient.getJob(jobId);
    } 
    
    /**
     * test get job JobId  not exist  with function getJob(String jobId)
    */
    @Test
    public void testJobIDNotExist() {
    	jobId = "qatest";  
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("requested job does not exist");
        GetJobResponse resp = jobClient.getJob(jobId);
    }    
   
}
