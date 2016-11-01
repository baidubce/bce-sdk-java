package com.baidubce.services.media.cases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.regex.*;

import ch.qos.logback.classic.turbo.TurboFilter;

import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.Clip;
import com.baidubce.services.media.model.CodecOptions;
import com.baidubce.services.media.model.CreatePresetRequest;
import com.baidubce.services.media.model.Encryption;
import com.baidubce.services.media.model.GetPresetRequest;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.ListPresetsResponse;
import com.baidubce.services.media.model.Video;
import com.baidubce.services.media.model.CodecOptions;
import com.baidubce.services.media.MediaClient;

public class QueryPresetTest extends AbstractMediaTest {

    public String          prefix = AbstractMediaTest.PRE_NAME + "querypreset";

	@Rule
	public ExpectedException myExp = ExpectedException.none();
	
	@Before
    public void setUp() throws Exception {
        super.setUp();
        presetName = convertName(prefix);
        String container = "hls";
        presetClient.createPreset(presetName, container);
        checkPresetExist(presetName, true);
    }

    @After
    public void tearDown() throws Exception {
    	for (GetPresetResponse preset : presetClient.listPresets().getPresets()) {
            GetPresetResponse resp = presetClient.getPreset(preset.getPresetName());
            System.out.println(resp);
            assertTrue("Failed to get preset: " + preset.getPresetName(), resp
                    .getPresetName().equals(preset.getPresetName()));
            presetName = preset.getPresetName();
        	if (presetName.startsWith(prefix)) {
        		presetClient.deletePreset(presetName);
                checkPresetExist(presetName, false);
            }
            /* Pattern pattern = Pattern.compile("^bce.");
             *  Matcher m = pattern.matcher(presetName);
             *  boolean result = m.find();
             *  if (!result) {
             *  	System.out.println("delete preset " + presetName);
             *  	presetClient.deletePreset(presetName);
             *  	checkPresetExist(presetName, false);
             *  }
             */
        }
    }
    
    @Test
    public void testQueryPresetExist(){
    	checkPresetExist(presetName, true);    	
    }
    
    @Test
    public void testQueryPresetDelete(){
    	presetClient.deletePreset(presetName);
        checkPresetExist(presetName, false);   	
    }
    
    @Test
    public void testQueryPresetParamNull() throws ClassNotFoundException{
    	expMsg = "The parameter presetName should NOT be null or empty string";
		myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
		myExp.expectMessage(expMsg);
		
    	presetName = null;
    	presetClient.getPreset(presetName); 	
    }
    
    @Test
    public void testQueryPresetParamEmpty() throws ClassNotFoundException{
    	expMsg = "The parameter presetName should NOT be null or empty string";
		myExp.expect((Class<? extends Throwable>) Class.forName(expParamException));
		myExp.expectMessage(expMsg);
		
    	presetName = "";
    	presetClient.getPreset(presetName); 	
    }
    
    @Test
    public void testQueryPresetNotExist() throws ClassNotFoundException{
    	expMsg = "The requested preset does not exist";
		myExp.expect((Class<? extends Throwable>) Class.forName(expServiceException));
		myExp.expectMessage(expMsg);
		
    	presetName = convertName(prefix);
    	presetClient.getPreset(presetName); 	
    }
    
    @Test
    /**
     * GetPresetResponse getPreset(GetPresetRequest request)
     */
    public void testQueryPresetNormal(){
    	GetPresetRequest request = new GetPresetRequest();
    	request.setPresetName(presetName);
    	GetPresetResponse resp = pipelineClient.getPreset(request);
    	assertEquals(resp.getPresetName(), presetName);    	
    }
}
