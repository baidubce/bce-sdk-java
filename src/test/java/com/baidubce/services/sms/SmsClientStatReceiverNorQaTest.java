package com.baidubce.services.sms;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SmsConf;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.StatReceiverRequest;
import com.baidubce.services.sms.model.StatReceiverResponse;

@RunWith(Parameterized.class)
public class SmsClientStatReceiverNorQaTest {
	private SmsClient client;
	
	@Parameter(0)
	public String inPhoneNumber;
	@Parameter(1)
	public int expReceivedCount;
	
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
				{"13811112222",0},
//				{"14113705021",1},
		});
	}

	@Before
	public void setUp() throws InterruptedException {
		this.client = new SmsClient(
				new SmsClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SmsConf.SMS_AK,
										SmsConf.SMS_SK)).withEndpoint(
								SmsConf.SMS_ENDPOINT));
		Thread.sleep(1000);
	}

	@After
	public void tearDown() {
		this.client.shutdown();
	}

	@Test
	public void test() {
		StatReceiverResponse sResponse=client.statReceiver(new StatReceiverRequest().withPhoneNumber(inPhoneNumber));
		Assert.assertEquals(expReceivedCount, sResponse.getReceivedToday().intValue());
	}

}
