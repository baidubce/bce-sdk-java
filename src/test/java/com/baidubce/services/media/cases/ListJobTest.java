package com.baidubce.services.media.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.Job;
import com.baidubce.services.media.model.ListJobsRequest;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.ListJobsResponse;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.CreateJobResponse;
import com.baidubce.services.media.model.PipelineConfig;

public class ListJobTest extends AbstractMediaTest {
	
    public String          pipelineForJob = null;
    public int             capacityNumber = 2;
    public String          presetNameSystem = "bce.audio_mp3_320kbps";
    public String          prefix = AbstractMediaTest.PRE_NAME + "listjob";
    
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
   //     super.tearDown();
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
     * test list job successfully  with function listJobs(String pipelineName)
    */
    @Test
    public void testListJobNormal() {
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY;
    	targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        String jobId1 = jobResp.getJobId();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        String jobId2 = jobResp.getJobId();
        ListJobsResponse resp = jobClient.listJobs(pipelineName);
        List<Job> jobs = resp.getJobs();
        int count = 0;
        count = jobs.size();
        assertEquals(count,2);;
        assertTrue(jobs.toString().contains(jobId1));
        assertTrue(jobs.toString().contains(jobId2));        
    }
    
    /**
     * test list job successfully  with function listJobs(ListJobsRequest request)
    */
    @Test
    public void testListJobNormal1() {
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY;
    	targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        String jobId1 = jobResp.getJobId();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        String jobId2 = jobResp.getJobId();
        ListJobsRequest request = new ListJobsRequest();
        request.setPipelineName(pipelineName);
        assertEquals(request.getPipelineName(),pipelineName);        
        ListJobsResponse respponse = jobClient.listJobs(request);
        ListJobsResponse resp = new ListJobsResponse();
        resp.setJobs(respponse.getJobs());
        List<Job> jobs = resp.getJobs();
        int count = 0;
        count = jobs.size();
        assertEquals(count,2);;
        assertTrue(jobs.toString().contains(jobId1));
        assertTrue(jobs.toString().contains(jobId2));        
    }   
 
    /**
     * test list equal get  with function listJobs(String pipelineName)
    */
    @Test
    public void testListJobIDEqualGetJobID() {
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY;
    	targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        String jobId = jobResp.getJobId();
        GetJobResponse resp = jobClient.getJob(jobId); 
        String jobId1 = resp.getJobId();       
        ListJobsResponse resp1 = jobClient.listJobs(pipelineName);
        List<Job> jobs = resp1.getJobs();      
        String jobId2 = jobs.get(0).getJobId();
        assertEquals(jobId1,jobId2);          
    }
    
    
    /**
     * test pipeline not exist with function listJobs(String pipelineName)
    */  
    @Test
    public void testListJobPipelineNotExist() {
    	pipelineName = convertName(prefix); 
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("pipeline does not exist");
    	ListJobsResponse resp = jobClient.listJobs(pipelineName);        
    }  
    
    /**
     * test pipeline = '' with function listJobs(String pipelineName)
    */ 
    @Test
    public void testListJobPipelineEmpty() {
    	pipelineName = ""; 
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("pipelineName should NOT be null or empty string");
    	ListJobsResponse resp = jobClient.listJobs(pipelineName);         
    } 
    
    /**
     * test pipeline = null with function listJobs(String pipelineName)
    */ 
    @Test
    public void testListJobPipelineNull() {
    	pipelineName = null; 
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("pipelineName should NOT be null or empty string");
    	ListJobsResponse resp = jobClient.listJobs(pipelineName);         
    } 
    
    /**
     * test list job pipeline deleted with function listJobs(String pipelineName)
    */
    @Test
    public void testListJobPipelineDeleted() throws InterruptedException {
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY;
    	targetKey = AbstractMediaTest.TARGET_KEY;
        presetName = presetNameSystem;
        CreateJobResponse jobResp = new CreateJobResponse();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        String jobId1 = jobResp.getJobId();
        jobResp = jobClient.createJob(pipelineName, sourceKey, targetKey,presetName);
        String jobId2 = jobResp.getJobId();
		while(true){
			try{
			    pipelineClient.deletePipeline(pipelineName); 
				break;
			}catch (Exception e){
				
				if (e.getMessage().contains("pending/running")){
					Thread.sleep(5000);
				}else{
					break;
				}	
				
			}
		}
            
        ListJobsResponse resp = jobClient.listJobs(pipelineName);
        List<Job> jobs = resp.getJobs();
        int count = 0;
        count = jobs.size();
        assertEquals(count,2);;
        assertTrue(jobs.toString().contains(jobId1));
        assertTrue(jobs.toString().contains(jobId2));        
    } 

}
