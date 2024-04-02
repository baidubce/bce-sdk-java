package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import redis.clients.jedis.Jedis;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SesConf;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.DeleteVerifiedEmailRequest;
import com.baidubce.services.ses.model.GetVerifiedEmailResponse;

@RunWith(Parameterized.class)
public class SesClientDeleteVerifiedEmailNorQaTest {
	private SesClient client;
	private Jedis jedis;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public String inEmailAddr;
	@Parameter(2)
	public String inStatus;
	@Parameter(3)
	public int inStatusCode;
	@Parameter(4)
	public String inMailAddrCert;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ 0, "wanglinqing01@baidu.com", "success:none", 0, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 1, "wanglinqing01@baidu.com", "failed:none", 1, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 2, "wanglinqing01@baidu.com", "temporaryfailure:none", 2, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 3, "wanglinqing01@baidu.com", "pending:none", 3, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 4, "wanglinqing01@baidu.com", "notstarted:none", 4, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 5, "wanglinqing01@baidu.com", "none:none", 5, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 6, "wanglinqingNotExist@baidu.com", "none:none", 5, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },

		});
	}


	@Before
	public void setUp() throws InterruptedException {
		this.client = new SesClient(
				new SesClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SesConf.SES_AK,
										SesConf.SES_SK)).withEndpoint(
								SesConf.SES_ENDPOINT));
		this.jedis = new Jedis(SesConf.SES_REDIS_HOST, SesConf.SES_REDIS_PORT);
		switch(tag){
		case 6:{
			jedis.hdel(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr);
			jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr);
			break;
		}
		default:{
			jedis.hset(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr, inMailAddrCert);
			jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr, inStatus);
		}
		}
	}

	@After
	public void tearDown() {
		this.client.shutdown();
		jedis.hdel(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr);
		this.jedis.quit();
		}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void test() throws InterruptedException {
		switch(tag){
		case 6:{
			expectedException.expect(BceServiceException.class);
	    	expectedException.expectMessage("input identity not verified");
	    	this.client.deleteVerifiedEmail(inEmailAddr);
	    	Thread.sleep(1000);
			}
		default:{
			this.client.deleteVerifiedEmail(inEmailAddr);
			Thread.sleep(1000);

			expectedException.expect(BceServiceException.class);
	    	expectedException.expectMessage("mail addr not verified");
			GetVerifiedEmailResponse gResponse = this.client
					.getVerifiedEmail(inEmailAddr);	
			Thread.sleep(1000);
			}
		}
	}
	@Test
	public void testRequest() throws InterruptedException {
		switch(tag){
		case 6:{
			expectedException.expect(BceServiceException.class);
	    	expectedException.expectMessage("input identity not verified");
	    	this.client.deleteVerifiedEmail(new DeleteVerifiedEmailRequest().withEmailAddress(inEmailAddr));
	    	Thread.sleep(1000);
		}
		default:{
			 this.client.deleteVerifiedEmail(new DeleteVerifiedEmailRequest().withEmailAddress(inEmailAddr));
			 Thread.sleep(1000);

			 expectedException.expect(BceServiceException.class);
		     expectedException.expectMessage("mail addr not verified");
			 GetVerifiedEmailResponse gResponse = this.client
					 .getVerifiedEmail(inEmailAddr);
			 Thread.sleep(1000);
		}		
		}		
	}
}
