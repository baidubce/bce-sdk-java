package com.baidubce.services.media.cases;


import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.DeletePipelineRequest;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineStatus;

public class DeletePipelineTest extends AbstractMediaTest {

    public String          prefix = AbstractMediaTest.PRE_NAME + "deletepipe";

    @Before
    public void setUp() throws Exception {
        super.setUp();

        }      

    @After
    public void tearDown() throws Exception {
     //   super.tearDown(); 
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
    * test  pipelineName = "" with function deletePipeline(String pipelineName)
    */       
    @Test   
    public void testDeletePipelineEmpty(){
    	pipelineName = "";
    	BceEx.expect(IllegalArgumentException.class);
    	BceEx.expectMessage("pipelineName should NOT be null or empty string");
    	pipelineClient.deletePipeline(pipelineName);  	   	
    }
    
    /**
    * test  pipelineName = null with function deletePipeline(String pipelineName)
    */       
    @Test   
    public void testDeletePipelineNull(){
    	pipelineName = null;
    	BceEx.expect(IllegalArgumentException.class);
    	BceEx.expectMessage("pipelineName should NOT be null or empty string");
    	pipelineClient.deletePipeline(pipelineName);  	   	
    }
    
    /**
    * test  pipelineName not exist with function deletePipeline(String pipelineName)
    */       
    @Test   
    public void testDeletePipelineNotExist(){
    	pipelineName = convertName(prefix); 
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("pipeline does not exist");
        pipelineClient.deletePipeline(pipelineName);  
    }
    
    
    /**
    * test  delete pipelineName normal with function deletePipeline(String pipelineName)
    */ 
    @Test    
    public void testDeletePipelineNormal(){
    	pipelineName = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName, AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);  
    	checkPipelineExist(pipelineName, true);
    	pipelineClient.deletePipeline(pipelineName);
    	checkPipelineExist(pipelineName, false);
    } 
    
    /**
    * test  delete pipelineName normal with function deletePipeline(DeletePipelineRequest request)
    */ 
    @Test     
    public void testDeletePipelineNormal1(){
    	pipelineName = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName, AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);  
    	checkPipelineExist(pipelineName, true);
    	DeletePipelineRequest request = new DeletePipelineRequest();
    	request.setPipelineName(pipelineName);
    	assertEquals(request.getPipelineName(),pipelineName);	
    	pipelineClient.deletePipeline(request);
    	checkPipelineExist(pipelineName, false);
    } 
    
    /**
    * test  delete pipelineName twice with function deletePipeline(String pipelineName)
    */ 
    @Test   
    public void testDeletePipelinetwice(){
    	pipelineName = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName, AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET, 5);  
    	checkPipelineExist(pipelineName, true);
    	pipelineClient.deletePipeline(pipelineName);
    	checkPipelineExist(pipelineName, false);
    	BceEx.expect(com.baidubce.BceServiceException.class);
    	BceEx.expectMessage("pipeline does not exist");
        pipelineClient.deletePipeline(pipelineName);  
    } 
}
