package com.baidubce.services.media.cases;


import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


import com.baidubce.services.media.model.ListPipelinesRequest;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineStatus;


public class ListPipelineTest extends AbstractMediaTest {

    public String          prefix = AbstractMediaTest.PRE_NAME + "listpipeline";

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
     * get pipeline list  with function listPipelines() 
    */
    @Test
    public void testListPipeline() {
        int count0 = 0;
        ListPipelinesResponse  resp = pipelineClient.listPipelines();
        List<PipelineStatus> pipelines = resp.getPipelines();
        count0 = pipelines.size();
    	String pipelineName1 = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName1,AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5); 
    	checkPipelineExist(pipelineName1, true);
    	String pipelineName2 = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName2,AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5);  
    	checkPipelineExist(pipelineName2, true);
        resp = pipelineClient.listPipelines();
    	pipelines = resp.getPipelines();
    	int count1 = -1;
    	count1 = pipelines.size();
    	assertEquals(count0+2,count1);
    	String name;
    	int flag1 = 0;
    	int flag2 = 0;
        for (PipelineStatus pipe : resp.getPipelines()) {
        	name = pipe.getPipelineName();
        	if (name.equals(pipelineName1) ){
        		flag1 = 1;
        		
               }else if(name.equals(pipelineName2)){
            	   flag2 = 1;
            	   
               }
        	}
        
    	assertEquals(flag1,1); 
    	assertEquals(flag2,1);  	    	
    }


    /**
     * get pipeline list  with function listPipelines(ListPipelinesRequest request)
    */
    @Test
    public void testListPipeline1() {
    	ListPipelinesRequest request = new ListPipelinesRequest();
        int count0 = 0;
        ListPipelinesResponse  resp = pipelineClient.listPipelines(request);
        List<PipelineStatus> pipelines = resp.getPipelines();
        count0 = pipelines.size();
    	String pipelineName1 = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName1,AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5); 
    	checkPipelineExist(pipelineName1, true);
    	String pipelineName2 = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName2,AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5);  
    	checkPipelineExist(pipelineName2, true);
        resp = pipelineClient.listPipelines(request);
    	pipelines = resp.getPipelines();
    	int count1 = -1;
    	count1 = pipelines.size();
    	assertEquals(count0+2,count1);
    	String name;
    	int flag1 = 0;
    	int flag2 = 0;
        for (PipelineStatus pipe : resp.getPipelines()) {
        	name = pipe.getPipelineName();
        	if (name.equals(pipelineName1) ){
        		flag1 = 1;
        		
               }else if(name.equals(pipelineName2)){
            	   flag2 = 1;
            	   
               }
        	}
        
    	assertEquals(flag1,1); 
    	assertEquals(flag2,1);  	    	
    }  
    
    /**
     * get pipeline list  with function listPipelines() 
    */
    @Test
    public void testMethodOfListPipelinesRequest() {

    	String pipelineName1 = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName1,AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5);
    	checkPipelineExist(pipelineName1, true);
    	String pipelineName2 = convertName(prefix);
    	pipelineClient.createPipeline(pipelineName2,AbstractMediaTest.SOURCE_BUCKET, AbstractMediaTest.TARGET_BUCKET,5);  
    	checkPipelineExist(pipelineName2, true);
        ListPipelinesResponse  resp = pipelineClient.listPipelines();  	
    	ListPipelinesResponse  respTest = new ListPipelinesResponse();
    	respTest.setPipelines(resp.getPipelines());
    	assertEquals(respTest.getPipelines().size(),resp.getPipelines().size());
    	String name;
    	int flag1 = 0;
    	int flag2 = 0;
        for (PipelineStatus pipe : respTest.getPipelines()) {
        	name = pipe.getPipelineName();
        	if (name.equals(pipelineName1) ){
        		flag1 = 1;
        		
               }else if(name.equals(pipelineName2)){
            	   flag2 = 1;
            	   
               }
        	}
        
    	assertEquals(flag1,1); 
    	assertEquals(flag2,1); 
    }
    
}
