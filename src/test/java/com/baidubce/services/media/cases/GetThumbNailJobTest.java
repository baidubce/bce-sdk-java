package com.baidubce.services.media.cases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.GetThumbnailJobRequest;
import com.baidubce.services.media.model.GetThumbnailJobResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.ThumbnailCapture;
import com.baidubce.services.media.model.ThumbnailTarget;

public class GetThumbNailJobTest extends AbstractMediaTest {
    public String               pipelineForJob = null;
    public int                  capacityNumber = 2;
    public String          prefix = AbstractMediaTest.PRE_NAME + "querythumb";
    
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

    /*
     *  test get job successfully with function getThumbnailJob(String jobId)
     */    
    @Test
    public void testGetThumbNailJobNormal1(){
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId(); 
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(thumbNailJobId);
    	assertEquals(response.getJobId(),thumbNailJobId); 
    }
    
    /*
     *  test get job successfully with function getThumbnailJob(GetThumbnailJobRequest request)
     */   
    @Test
    public void testGetThumbNailJobNormal2(){
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId(); 
        GetThumbnailJobRequest request =  new GetThumbnailJobRequest();
        request.setJobId(thumbNailJobId);
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(request);
    	assertEquals(response.getJobId(),thumbNailJobId);   	
    }
    
    /*
     *  test method of GetThumbnailJobResponse
     */    
    @Test
    public void testMethodOfGetThumbnailJobResponse(){
    	pipelineName = pipelineForJob;
    	sourceKey = AbstractMediaTest.SOURCE_KEY4;        
        ThumbnailTarget target = new ThumbnailTarget().withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture();        
        thumbNailJobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId(); 
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(thumbNailJobId);
        
        GetThumbnailJobResponse resp = new GetThumbnailJobResponse();
        resp.setJobId(response.getJobId());
        resp.setJobStatus(response.getJobStatus());
        resp.setPipelineName(response.getPipelineName());
        resp.setSource(response.getSource());
        resp.setTarget(response.getTarget());
        resp.setCapture(response.getCapture());       
    	assertEquals(resp.getJobId(),thumbNailJobId); 
    	assertEquals(resp.getPipelineName(),pipelineName); 
    	assertEquals(resp.getJobStatus(),response.getJobStatus());
    	assertTrue(resp.getSource().toString().contains("ThumbnailSource"));
    	assertTrue(resp.getTarget().toString().contains("ThumbnailTargetStatus"));
    	assertTrue(resp.getCapture().toString().contains("ThumbnailCapture"));
    }  
    
    /*
     *  test thumbNailJobId =null
     */    
    @Test
    public void testGetThumbNailJobThumbNailJobIdNull(){        
        thumbNailJobId = null; 
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("jobId should NOT be null or empty string");
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(thumbNailJobId);  	
    }
    
    /*
     *  test thumbNailJobId =""
     */    
    @Test
    public void testGetThumbNailJobThumbNailJobIdEmpty(){        
        thumbNailJobId = ""; 
    	BceEx.expect(java.lang.IllegalArgumentException.class);
    	BceEx.expectMessage("jobId should NOT be null or empty string");
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(thumbNailJobId);  	
    }
    
    /*
     *  test thumbNailJobId not exist
     */    
    @Test
    public void testGetThumbNailJobThumbNailJobIdNotExist(){        
        thumbNailJobId = "job-notexist4qkmxpsw"; 
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("The requested thumbnail does not exist");
        GetThumbnailJobResponse response = mediaClient.getThumbnailJob(thumbNailJobId);  	
    }    
	
}
