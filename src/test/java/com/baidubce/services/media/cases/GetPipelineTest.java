package com.baidubce.services.media.cases;


import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.GetPipelineRequest;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.GetPipelineResponse;
import com.baidubce.services.media.model.PipelineStatus;

public class GetPipelineTest extends AbstractMediaTest {

    public String          prefix = AbstractMediaTest.PRE_NAME + "querypipeline";

    @Before
    public void setUp() throws Exception {
        super.setUp();

        }      

    @After
    public void tearDown() throws Exception {
    //     super.tearDown(); 
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
    public ExpectedException BceEx = ExpectedException.none();
    
    /**
     * test pipeline info  with getPipeline(String pipelineName)
    */
  
    @Test
    public void testGetPipeline(){
    	pipelineName = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName,"test", AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5);
    	checkPipelineExist(pipelineName, true);
    	GetPipelineResponse pipelineResponse = new GetPipelineResponse();
    	pipelineResponse = pipelineClient.getPipeline(pipelineName);
    	assertEquals(pipelineResponse.getState(),"ACTIVE");
    	assertEquals(pipelineResponse.getPipelineName(),pipelineName);
    	assertEquals(pipelineResponse.getDescription(),"test");
    	assertEquals(pipelineResponse.getSourceBucket(),AbstractMediaTest.SOURCE_BUCKET);
    	assertEquals(pipelineResponse.getTargetBucket(),AbstractMediaTest.TARGET_BUCKET);
    	assertTrue(pipelineResponse.getConfig().toString().contains("capacity: 5"));
    	assertTrue(pipelineResponse.getJobStatus().toString().contains("total"));
    	assertTrue(pipelineResponse.getClass().toString().contains("GetPipelineResponse"));
    	pipelineResponse.getLastUpdateTime();// can not be sure the assert. so not test this by now
    	assertTrue(pipelineResponse.getMetadata().toString().contains("bceRequestId"));
    	   	
    }
    
    /**
     * test pipeline info  with getPipeline(GetPipelineRequest request)
    */
  
    @Test
    public void testGetPipeline1(){
    	pipelineName = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName,"test", AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5);
    	checkPipelineExist(pipelineName, true);
    	GetPipelineResponse pipelineResponse = new GetPipelineResponse();
        GetPipelineRequest request = new GetPipelineRequest();
        request.setPipelineName(pipelineName);
    	pipelineResponse = pipelineClient.getPipeline(request);
    	assertEquals(pipelineResponse.getState(),"ACTIVE");
    	assertEquals(pipelineResponse.getPipelineName(),pipelineName);
    	assertEquals(pipelineResponse.getDescription(),"test");
    	assertEquals(pipelineResponse.getSourceBucket(),AbstractMediaTest.SOURCE_BUCKET);
    	assertEquals(pipelineResponse.getTargetBucket(),AbstractMediaTest.TARGET_BUCKET);
    	assertTrue(pipelineResponse.getConfig().toString().contains("capacity: 5"));
    	assertTrue(pipelineResponse.getJobStatus().toString().contains("total"));
    	assertTrue(pipelineResponse.getClass().toString().contains("GetPipelineResponse"));
    	pipelineResponse.getLastUpdateTime();// can not be sure the assert. so not test this by now
    	assertTrue(pipelineResponse.getMetadata().toString().contains("bceRequestId"));
    	   	
    }

    
    /**
     * test method of GetPipelineResponse
    */
    @Test
    public void testMethodOfGetPipelineResponse(){
    	pipelineName = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName,"test", AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5); 
    	checkPipelineExist(pipelineName, true);
    	GetPipelineResponse pipelineResponse = new GetPipelineResponse();
    	GetPipelineResponse pipelineResponse1 = new GetPipelineResponse();
    	pipelineResponse1 = pipelineClient.getPipeline(pipelineName);
    	pipelineResponse.setConfig(pipelineResponse1.getConfig());
    	pipelineResponse.setDescription(pipelineResponse1.getDescription());
    	pipelineResponse.setJobStatus(pipelineResponse1.getJobStatus());
    	pipelineResponse.setLastUpdateTime(pipelineResponse1.getLastUpdateTime());
    	pipelineResponse.setPipelineName(pipelineResponse1.getPipelineName());
    	pipelineResponse.setSourceBucket(pipelineResponse1.getSourceBucket());
    	pipelineResponse.setState(pipelineResponse1.getState());
    	pipelineResponse.setTargetBucket(pipelineResponse1.getTargetBucket());    	
    	assertEquals(pipelineResponse.getState(),"ACTIVE");
    	assertEquals(pipelineResponse.getPipelineName(),pipelineName);
    	assertEquals(pipelineResponse.getDescription(),"test");
    	pipelineResponse.getLastUpdateTime();// can not be sure the assert. so not test this by now
    	assertEquals(pipelineResponse.getSourceBucket(),AbstractMediaTest.SOURCE_BUCKET);
    	assertEquals(pipelineResponse.getTargetBucket(),AbstractMediaTest.TARGET_BUCKET);
    	assertTrue(pipelineResponse.getConfig().toString().contains("capacity: 5"));  
    	assertTrue(pipelineResponse.getJobStatus().toString().contains("total"));        	
    	assertTrue(pipelineResponse.getMetadata().toString().contains("bceRequestId"));
    } 
  
    
    /**
     * test deleted pipeline  with  getPipeline(String pipelineName)
    */
  
    @Test
    public void testGetPipelineDeleted(){
    	pipelineName = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName,"test", AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5);
    	checkPipelineExist(pipelineName, true);
    	pipelineClient.deletePipeline(pipelineName);
    	checkPipelineExist(pipelineName, false);
    	GetPipelineResponse pipelineResponse = new GetPipelineResponse();
        pipelineResponse = pipelineClient.getPipeline(pipelineName);
    	assertEquals(pipelineResponse.getState(),"INACTIVE");
    	assertEquals(pipelineResponse.getPipelineName(),pipelineName);
    	assertEquals(pipelineResponse.getDescription(),"test");
    	assertEquals(pipelineResponse.getSourceBucket(),AbstractMediaTest.SOURCE_BUCKET);
    	assertEquals(pipelineResponse.getTargetBucket(),AbstractMediaTest.TARGET_BUCKET);
    	assertTrue(pipelineResponse.getConfig().toString().contains("capacity: 5"));
    	assertTrue(pipelineResponse.getJobStatus().toString().contains("total"));
    	assertTrue(pipelineResponse.getClass().toString().contains("GetPipelineResponse"));
    	pipelineResponse.getLastUpdateTime();// can not be sure the assert. so not test this by now
    	assertTrue(pipelineResponse.getMetadata().toString().contains("bceRequestId"));
    	   	
    }

    
    /**
     * test pipeline name not exist  with  getPipeline(String pipelineName)
    */
  
    @Test
    public void testGetPipelineNotExist(){
    	pipelineName = convertName(prefix);
    	checkPipelineExist(pipelineName, false);
    	GetPipelineResponse pipelineResponse = new GetPipelineResponse();
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("pipeline does not exist");
    	pipelineResponse = pipelineClient.getPipeline(pipelineName);
    }
    
    /**
     * test pipeline = '' with  getPipeline(String pipelineName)
    */
  
    @Test
    public void testGetPipelineEmpty(){
    	pipelineName = "";
    	GetPipelineResponse pipelineResponse = new GetPipelineResponse();
    	BceEx.expect(IllegalArgumentException.class);
    	BceEx.expectMessage("pipelineName should NOT be null or empty string");
    	pipelineResponse = pipelineClient.getPipeline(pipelineName);
    }
    
}
