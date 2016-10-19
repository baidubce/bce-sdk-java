package com.baidubce.services.media.cases;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.CreateThumbnailJobResponse;
import com.baidubce.services.media.model.GetThumbnailJobResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.ListThumbnailJobsRequest;
import com.baidubce.services.media.model.ListThumbnailJobsResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.ThumbnailCapture;
import com.baidubce.services.media.model.ThumbnailJobStatus;
import com.baidubce.services.media.model.ThumbnailTarget;

public class ListThumbNailJobTest extends AbstractMediaTest {
	
    public String          pipelineForJob = null;
    public int             capacityNumber = 2;
    public String          prefix = AbstractMediaTest.PRE_NAME + "listthumb";
    
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
     * test list thumbnailjob with function listThumbnailJob(String pipelineName)
    */
    @Test
    public void testListThumbNailJobNormal1() {
    	
    	pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        CreateThumbnailJobResponse jobResp = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture);
        String jobId1 = jobResp.getJobId();
        jobResp = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture);
        String jobId2 = jobResp.getJobId();        
        ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(pipelineName);
        List<ThumbnailJobStatus> jobs = resp.getThumbnails();
        int count = 0;
        count = jobs.size();
        assertEquals(count,2);
        assertTrue(jobs.toString().contains(jobId1));
        assertTrue(jobs.toString().contains(jobId2));        
    }
    
    /**
     * test list thumbnailjob with function listThumbnailJobs(ListThumbnailJobsRequest request)
    */
    @Test
    public void testListThumbNailJobNormal2() {
    	
    	pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        CreateThumbnailJobResponse jobResp = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture);
        String jobId1 = jobResp.getJobId();
        jobResp = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture);
        String jobId2 = jobResp.getJobId(); 
        ListThumbnailJobsRequest request = new ListThumbnailJobsRequest();
        request.setPipeline(pipelineName);     
        ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(request);
        List<ThumbnailJobStatus> jobs = resp.getThumbnails();
        int count = 0;
        count = jobs.size();
        assertEquals(count,2);
        assertTrue(jobs.toString().contains(jobId1));
        assertTrue(jobs.toString().contains(jobId2));        
    }
    
    /**
     * test list thumbnailjob pipelineName = null
    */
    @Test
    public void testListThumbNailJobPipelineNameNull() {
    	
    	pipelineName = null;
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("pipelineName should NOT be null or empty string");   
        ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(pipelineName);       
    }  
    
    /**
     * test list thumbnailjob pipelineName = ""
    */
    @Test
    public void testListThumbNailJobPipelineNameEmpty() {
    	
    	pipelineName = "";
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("pipelineName should NOT be null or empty string");   
        ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(pipelineName);       
    } 
    
    /**
     * test list thumbnailjob pipelineName deleted
     */
    @Test
    public void testListThumbNailJobPipelineNameDeleted()throws InterruptedException {
    	
    	pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        CreateThumbnailJobResponse jobResp = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture);
        String jobId1 = jobResp.getJobId();
        jobResp = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture);
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
        ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(pipelineName);
        List<ThumbnailJobStatus> jobs = resp.getThumbnails();
        int count = 0;
        count = jobs.size();
        assertEquals(count,2);
        assertTrue(jobs.toString().contains(jobId1));
        assertTrue(jobs.toString().contains(jobId2));        
    }
    
    /**
     * test list thumbnailjob pipelineName not exist
    */  
    @Test
    public void testListThumbNailJobPipelineNameNotExist() {
    	pipelineName = convertName(AbstractMediaTest.PRE_NAME); 
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("pipeline does not exist");
    	ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(pipelineName);       
    }  
     
    /**
     * test list equal get
    */
    @Test
    public void testListThumbNailJobEqualGetJobID() {
    	
    	pipelineName = pipelineForJob;
        sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        CreateThumbnailJobResponse jobResp = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture);
        String jobId = jobResp.getJobId();    
        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        String jobId1 = resp.getJobId();         
        ListThumbnailJobsResponse resp1 = mediaClient.listThumbnailJobs(pipelineName);
        List<ThumbnailJobStatus> jobs = resp1.getThumbnails();  
        String jobId2 = jobs.get(0).getJobId();
        assertEquals(jobId1,jobId2);          
    }
    
}
